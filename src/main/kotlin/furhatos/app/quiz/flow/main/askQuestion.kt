package furhatos.app.quiz.flow.main

import furhatos.app.quiz.*
import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.questions.QuestionSet
import furhatos.app.quiz.setting.*
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.RequestRepeat


val AskQuestion: State = state(parent = Parent) {
    var questionSet: QuestionSet? = null
    var failedAttempts = 0
//    var currentQuestionIndex = 1

    onEntry {
        questionSet = questions
        failedAttempts = 0

        val currentRoundMetrics = users.current.quiz.gameMetrics.roundMetrics.getOrPut(Rounds.currentRoundIndex - 1) {
            RoundMetrics().apply {
                roundStartTime = System.currentTimeMillis()
            }
        }

        val newQuestionMetrics = QuestionMetrics()
        newQuestionMetrics.startTime = System.currentTimeMillis()

        currentRoundMetrics.questionMetrics[questionSet!!.getCurrentQuestionNumber()] = newQuestionMetrics

//        currentRoundMetrics.questionMetrics[currentQuestionIndex] = newQuestionMetrics

        if (questionSet != null) {
            furhat.setSpeechRecPhrases(questionSet!!.current.speechPhrases)
            furhat.ask(questionSet!!.current.text + " " + questionSet!!.current.getOptionsString())
        }
    }

    onReentry {
        failedAttempts = 0

        // Track repeat request
        val currentMetrics = users.current.quiz.gameMetrics
            .roundMetrics[Rounds.currentRoundIndex - 1]!!
            .questionMetrics[questionSet!!.getCurrentQuestionNumber()]!!
//            .questionMetrics[currentQuestionIndex]!!
        currentMetrics.repeatRequests++

        if (questionSet != null) {
            val greeting = utterance {
                + Gestures.Smile(strength = 2.0, duration = 6.0)
                + "Oh absolutely! Let me repeat, ${questionSet!!.current.text} ${questionSet!!.current.getOptionsString()}"
            }
            furhat.ask(greeting)
        }
    }

    onResponse<AnswerOption> {
        val answer = it.intent
        val currentRoundMetrics = users.current.quiz.gameMetrics.roundMetrics[Rounds.currentRoundIndex - 1]!!
        val currentQuestionMetrics = currentRoundMetrics.questionMetrics[questionSet!!.getCurrentQuestionNumber()]!!
//        val currentQuestionMetrics = currentRoundMetrics.questionMetrics[currentQuestionIndex]!!

        // Record metrics
        currentQuestionMetrics.endTime = System.currentTimeMillis()
        currentQuestionMetrics.timeTaken = (currentQuestionMetrics.endTime - currentQuestionMetrics.startTime) / 1000.0
        currentQuestionMetrics.attempts = failedAttempts + 1
        currentQuestionMetrics.isCorrect = answer.correct

        if (answer.correct) {
            currentRoundMetrics.correctAnswers++
            furhat.gesture(Gestures.Nod)
            users.current.quiz.score++

            val scoreMessage = when (users.current.quiz.score) {
                1 -> "You nailed it! That was the ${furhat.voice.emphasis("right")} answer!"
                2 -> "Are you an AI expert? That was the ${furhat.voice.emphasis("right")} answer!"
                else -> "Nice! That was the ${furhat.voice.emphasis("right")} answer!"
            }

            furhat.say(scoreMessage)

        } else {
            furhat.gesture(Gestures.Shake)
            val bad1 = utterance {
                + Gestures.BrowFrown(strength = 2.0, duration = 6.0)
                + "Sorry! That was ${furhat.voice.emphasis("not")} correct."
            }
            furhat.say(bad1)

            if (questionSet != null) {
                users.current.quiz.questionsAsked.add(questionSet!!.current.text)
            }

        }

        if (!questions!!.hasMoreQuestions()) {
            currentRoundMetrics.roundEndTime = System.currentTimeMillis()
            currentRoundMetrics.totalRoundTime = (currentRoundMetrics.roundEndTime - currentRoundMetrics.roundStartTime) / 1000.0
            val roundSummary = "Round ${Rounds.currentRoundIndex} completed with ${currentRoundMetrics.correctAnswers} correct answers!"
            furhat.say(roundSummary)
            goto(NewGame)
        } else {
//            ++currentQuestionIndex
            goto(NewQuestion)
        }
    }

    onResponse<RequestHint> {
        val currentMetrics = users.current.quiz.gameMetrics
            .roundMetrics[Rounds.currentRoundIndex - 1]!!
            .questionMetrics[questionSet!!.getCurrentQuestionNumber()]!!
//            .questionMetrics[currentQuestionIndex]!!
        currentMetrics.hintsUsed++

        furhat.say("Okay, let me help you!")
        when (confusion_type) {
            0 -> furhat.ask(questionSet!!.current.noConfusionHint)
            1 -> furhat.ask(questionSet!!.current.productiveConfusionHint)
            2 -> furhat.ask(questionSet!!.current.unproductiveConfusionHint)
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

    onResponse<RequestRepeatOptions> {
        furhat.gesture(Gestures.Surprise)
        random(
            { furhat.ask("They are ${questionSet!!.current.getOptionsString()}") },
            { furhat.ask(questionSet!!.current.getOptionsString()) }
        )
    }


    // Disable this for unlimited time to response
//    onNoResponse {
//        random(
//            { furhat.say("Too slow! Here comes the next question") },
//            { furhat.say("A bit too slow amigo! Get ready for the next question") }
//        )
//        goto(NewQuestion)
//    }

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
        questionSet?.next()
        goto(AskQuestion)
    }
}