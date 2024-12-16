package furhatos.app.quiz.flow.main

import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.questions.*
import furhatos.app.quiz.setting.playing
import furhatos.app.quiz.setting.attending
import furhatos.app.quiz.setting.quiz
import furhatos.gestures.Gestures

import furhatos.app.quiz.setting.scenario
import furhatos.flow.kotlin.*
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.skills.emotions.UserGestures
//import furhatos.app.quiz.flow.main.Attention


// The game logic inside the state
val NewGame = state(parent = Parent) {

    var speaking = false
    var said_enjoy = false

    onEntry {
        playing = true
        delay(2000)

        while (Rounds.currentRoundIndex < 3) {

            if (Rounds.currentRoundIndex < 1) {
                val initial_speak = utterance {
                    + Gestures.Smile(strength=2.0, duration=1.5)
                    + "We’ll be exploring basic AI concepts."
                }
                furhat.say(initial_speak)
            }
            delay(1000)
            val currentRound = Rounds.current
            furhat.say(currentRound.description)
            delay(1000)

//            when (scenario) {
//                0 -> { // No Confusion
//                    furhat.say(currentRound.noConfusion)
//                }
//                1 -> { // Productive Confusion
//                    furhat.say(currentRound.productiveConfusion)
//                }
//                2 -> { // Unproductive Confusion
//                    furhat.say(currentRound.unproductiveConfusion)
//                }
//            }
            delay(1000)
            when (Rounds.currentRoundIndex) {

                0 -> { // Questions for Round 1
                    val initial_speak = utterance {
                        + Gestures.Thoughtful(strength=2.0, duration=1.5)
                        + "Alright, ready for some challenging questions? Otherwise, I can repeat the information."
                    }
                    furhat.ask(initial_speak)
                }



                1 -> { // Questions for Round 2
                    furhat.ask("This might be tricky, but I believe you can handle it! Ready?")
                }

                2 -> { // Questions for Round 3
                    furhat.ask("This part is tricky, but let's push through! Ready?")
                }
            }
            speaking = false
        }
    }

    onResponse<Yes> {

        when (Rounds.currentRoundIndex) {

            0 -> { // Questions for Round 1
                val initial_speak = utterance {
                    + Gestures.Smile(strength=2.0, duration=1.5)
                    + "Let's start!"
                }
                furhat.say(initial_speak)

                questions = QuestionSet(questionsRound1)
                questions!!.next()
                Rounds.next()
                goto(AskQuestion)
            }

            1 -> { // Questions for Round 2
                questions = QuestionSet(questionsRound2)
                questions!!.next()
                Rounds.next()
                goto(AskQuestion)
            }

            2 -> { // Questions for Round 3
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