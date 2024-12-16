package furhatos.app.quiz.flow.main

import furhatos.app.quiz.*
import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.questions.QuestionSet
import furhatos.app.quiz.questions.questionsRound1
import furhatos.app.quiz.setting.*
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.No
import furhatos.nlu.common.RequestRepeat
import furhatos.nlu.common.Yes
import furhatos.records.Location
import furhatos.skills.emotions.UserGestures

val AskQuestion: State = state(parent = Parent) {
    var questionSet: QuestionSet? = null
    var failedAttempts = 0

    onEntry {
        questionSet = questions
        failedAttempts = 0

        // Set speech rec phrases based on the current question's answers
        if (questionSet != null) {
            furhat.setSpeechRecPhrases(questionSet!!.current.speechPhrases)
        }

        // Ask the question followed by the options
        if (questionSet != null) {
            furhat.ask(questionSet!!.current.text + " " + questionSet!!.current.getOptionsString())
        }
    }

    // Here we re-state the question
    onReentry {
        failedAttempts = 0
        if (questionSet != null) {
            val greeting = utterance {
                + Gestures.Smile(strength = 2.0, duration = 6.0)
                + "Oh absolutely! Let me repeat, ${questionSet!!.current.text} ${questionSet!!.current.getOptionsString()}"
            }
            furhat.ask(greeting)
        }
    }

    // User is answering with any of the alternatives
    onResponse<AnswerOption> {
        val answer = it.intent

        // If the user answers correct, we up the user's score and congratulates the user
        if (answer.correct) {
            furhat.gesture(Gestures.Nod)
            users.current.quiz.score++

            if (users.current.quiz.score == 1) {
                val correct = utterance {
                    + Gestures.Smile(strength = 2.0, duration = 6.0)
                    + "You nailed it! That was the ${furhat.voice.emphasis("right")}  answer, you now have a score of ${users.current.quiz.score}"
                }
                furhat.say(correct)
            } else if (users.current.quiz.score == 2) {
                val correct = utterance {
                    + glance(Location.LEFT)
                    + "Are you an AI expert? That was the ${furhat.voice.emphasis("right")}  answer, you now have a score of ${users.current.quiz.score}"
                }
                furhat.say(correct)
            } else {
                val correct = utterance {
                    + Gestures.Smile(strength = 2.0, duration = 6.0)
                    + "Nice! That was the ${furhat.voice.emphasis("right")}  answer, you now have a score of ${users.current.quiz.score}"
                }
                furhat.say(correct)
            }
        } else {
            furhat.gesture(Gestures.Shake)
            val bad1 = utterance {
                + Gestures.BrowFrown(strength = 2.0, duration = 6.0)
                + "Sorry! That was ${furhat.voice.emphasis("not")} correct\"}"
            }
            furhat.say(bad1)

            // Keep track of what users answered what question so that we don't ask the same user
            if (questionSet != null) {
                users.current.quiz.questionsAsked.add(questionSet!!.current.text)
            }

            val availableUsers = questionSet?.current?.let { it1 -> users.notQuestioned(it1.text) }
            if (availableUsers != null) {
                if (!availableUsers.isEmpty()) {
                    furhat.attend(availableUsers.first())
                    shouldChangeUser = false
                    furhat.ask("Maybe you know the answer?")
                }
            }
        }

        // Check if the game has ended and if not, goes to a new question
        if (++rounds >= maxQuestions) {
            val correct2 = utterance {
                + Gestures.BigSmile(strength = 2.0, duration = 2.0)
                + "That was the last question!"
            }
            furhat.say(correct2)
            furhat.say("Your final score is ${users.current.quiz.score}")
            val correct3 = utterance {
                + Gestures.Smile(strength = 2.0, duration = 3.0)
                + "It looks like you're enjoying the game. I'm glad about it! See you around!"
            }
            furhat.say(correct3)

            goto(NewGame)
        } else {
            goto(NewQuestion)
        }
    }

    onResponse<RequestHint> {
        furhat.say("Okay, let me help you!")
        print("Scenario " + scenario)
        when (scenario) {
            0 -> { // No Confusion
                print("INSIDEEE")
                furhat.ask(questionSet!!.current.noConfusionHint)
            }
            1 -> { // Productive Confusion
                furhat.ask(questionSet!!.current.productiveConfusionHint)
            }
            2 -> { // Unproductive Confusion
                furhat.ask(questionSet!!.current.unproductiveConfusionHint)
            }
        }
    }

    onResponse<RequestRepeat> {
        reentry()
    }

    onResponse<RequestRepeatQuestion> {
        furhat.gesture(Gestures.BrowRaise)
        if (questionSet != null) {
            furhat.ask(questionSet!!.current.text)
        }
    }

    // The user wants to hear the options again
    onResponse<RequestRepeatOptions> {
        furhat.gesture(Gestures.Surprise)
        random(
                {
                    if (questionSet != null) {
                        furhat.ask("They are ${questionSet!!.current.getOptionsString()}")
                    }
                },
                {
                    if (questionSet != null) {
                        furhat.ask(questionSet!!.current.getOptionsString())
                    }
                }
        )
    }

    // If we don't get any response, we assume the user was too slow
    onNoResponse {
        random(
                { furhat.say("Too slow! Here comes the next question") },
                { furhat.say("A bit too slow amigo! Get ready for the next question") }
        )
        goto(NewQuestion)
    }

    /* If we get a response that doesn't map to any alternative or any of the above handlers,
        we track how many times this has happened in a row and give them two more attempts and
        finally moving on if we still don't get it.
     */
    onResponse {
        failedAttempts++
        when (failedAttempts) {
            1 -> furhat.ask("I didn't get that, sorry. Try again!")
            2 -> {
                furhat.say("Sorry, I still didn't get that")
                if (questionSet != null) {
                    furhat.ask("The options are ${questionSet!!.current.getOptionsString()}")
                }
            }
            else -> {
                furhat.say("Still couldn't get that. Let's try a new question")
                shouldChangeUser = false
                goto(NewQuestion)
            }
        }
    }
}

val NewQuestion = state(parent = Parent) {

    var questionSet: QuestionSet? = null

    onEntry {
        questionSet = questions
        // Ask new question
        questionSet?.next()
        goto(AskQuestion)
    }
}