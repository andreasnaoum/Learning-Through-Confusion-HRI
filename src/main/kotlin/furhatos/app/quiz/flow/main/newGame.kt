package furhatos.app.quiz.flow.main

import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.questions.*
import furhatos.app.quiz.setting.*
import furhatos.gestures.Gestures
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes

var maxRounds = 1

val NewGame: State = state(parent = Parent) {

    val PARTICIPANT_ID = "01" // Change this for each participant

    onEntry {
        playing = true
        delay(2000)

        // Initialize game metrics at the start
        users.current.quiz.gameMetrics.gameStartTime = System.currentTimeMillis()

        while (Rounds.currentRoundIndex < maxRounds) {
            // Initialize round metrics for each round
            val newRoundMetrics = RoundMetrics()
            newRoundMetrics.roundStartTime = System.currentTimeMillis()
            users.current.quiz.gameMetrics.roundMetrics[Rounds.currentRoundIndex] = newRoundMetrics

            if (Rounds.currentRoundIndex < 1) {
                val initial_speak = utterance {
                    + Gestures.Smile(strength=2.0, duration=1.5)
                    + "Let's the game begin!"
                }
                furhat.say(initial_speak)
            }
            delay(1000)
            val currentRound = Rounds.current
            furhat.say(currentRound.description)
            delay(1000)

            when (Rounds.currentRoundIndex) {
                0 -> {
                    val initial_speak = utterance {
                        +Gestures.Thoughtful(strength = 2.0, duration = 1.5)
                        +"Alright, ready for some challenging questions? Otherwise, I can repeat the information."
                    }
                    furhat.ask(initial_speak)
                }
                1 -> {
                    furhat.ask("This might be tricky, but I believe you can handle it! Ready?")
                }
                2 -> {
                    furhat.ask("This part is tricky, but let's push through! Ready?")
                }
            }
        }

        val gameMetrics = users.current.quiz.gameMetrics
        saveResultsToFile(PARTICIPANT_ID, gameMetrics, users.current.quiz.score)
    }

    onResponse<Yes> {
        when (Rounds.currentRoundIndex) {
            0 -> {
                val initial_speak = utterance {
                    + Gestures.Smile(strength=2.0, duration=1.5)
                    + "Let's start! Remember, you can always ask for a hint."
                }
                furhat.say(initial_speak)
                questions = QuestionSet(questionsRound1)
                questions!!.next()
                Rounds.next()
                goto(AskQuestion)
            }
            1 -> {
                questions = QuestionSet(questionsRound2)
                questions!!.next()
                Rounds.next()
                goto(AskQuestion)
            }
            2 -> {
                questions = QuestionSet(questionsRound3)
                questions!!.next()
                Rounds.next()
                goto(AskQuestion)
            }
        }
    }

    onResponse<No> {
        furhat.say("Let me repeat!")
        val currentRound = Rounds.current
        furhat.say(currentRound.description)
    }
}