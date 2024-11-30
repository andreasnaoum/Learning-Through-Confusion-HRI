package furhatos.app.quiz.flow.main

import furhatos.app.quiz.AnswerOption
import furhatos.app.quiz.DontKnow
import furhatos.app.quiz.RequestRepeatOptions
import furhatos.app.quiz.RequestRepeatQuestion
import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.questions.QuestionSet
import furhatos.app.quiz.setting.nextPlaying
import furhatos.app.quiz.setting.notQuestioned
import furhatos.app.quiz.setting.playing
import furhatos.app.quiz.setting.quiz
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.RequestRepeat

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
//            furhat.ask()
        }
    }

    // User is answering with any of the alternatives
    onResponse<AnswerOption> {
        val answer = it.intent

        // If the user answers correct, we up the user's score and congratulates the user
        if (answer.correct) {
            furhat.gesture(Gestures.Smile)
            users.current.quiz.score++
            random(
                    { furhat.say("Great! That was the ${furhat.voice.emphasis("right")}  answer, you now have a score of ${users.current.quiz.score}") },
                    { furhat.say("that was ${furhat.voice.emphasis("correct")}, you now have a score of ${users.current.quiz.score}") }
            )
            /*
            If the user answers incorrect, we give another user the chance of answering if one is present in the game.
            If we indeed ask another player, the furhat.ask() interrupts the rest of the handler.
             */
        } else {
            furhat.gesture(Gestures.BrowFrown)
            furhat.say("Sorry, that was ${furhat.voice.emphasis("not")} correct")

            // Keep track of what users answered what question so that we don't ask the same user
            if (questionSet != null) {
                users.current.quiz.questionsAsked.add(questionSet!!.current.text)
            }

            /* Find another user that has not answered this question and if so, asks them.
             For the flow of the skill, we will continue asking the new user the next question through the
             shouldChangeUser = false flag.
             */
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
            furhat.say("That was the last question")
            goto(NewGame)
        } else {
            goto(NewQuestion)
        }
    }

    // The users answers that they don't know
    onResponse<DontKnow> {
        furhat.say("Too bad. Here comes the next question")
        goto(NewQuestion)
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
        /*
            If more than one player, we determine what user to target next here, based on the shouldChangeUser boolean
         */
//        if (users.playing().count() > 1) {
//            if (shouldChangeUser) {
//                val nextUser = users.nextPlaying()
//                furhat.attend(nextUser)
//                random(
//                        { furhat.say("The next one is for you") },
//                        { furhat.say("For you now") },
//                        { furhat.say("Now for you") }
//                )
//            } else {
//                shouldChangeUser = true
//                random(
//                        { furhat.say("You get to continue") },
//                        { furhat.say("Next one coming up") },
//                        { furhat.say("Here's another one") }
//                )
//            }
//        }
//        if (!users.current.isAttendingFurhat) {
//            furhat.say {
//                random {
//                    block {
//                        +"But then I do want you to pay attention"
//                        +Gestures.BigSmile
//                    }
//                    +"Look at me, I'm captain now"
//                    +"Could you pay some attention to me"
//                }
//            }
//        }
        // Ask new question
        questionSet?.next()
        goto(AskQuestion)
    }
}