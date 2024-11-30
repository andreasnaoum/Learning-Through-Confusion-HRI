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

//    onUserGesture(UserGestures.Smile) {
//        if (!said_enjoy) {
//            furhat.say("It looks like you're enjoying the game. I'm glad about it!")
//            said_enjoy = true
//        }
//    }

//    onUserAttend(instant = true) {user ->

//        if (user.isAttendingFurhat()) {
//            println("User ${user.id} is now attending Furhat")
//        } else {
//            println("User ${user.id} is now attending somewhere else")
//            val attention_test = utterance {
//                + Gestures.Shake(strength=5.0, duration=1.5)
//                + "Could you pay some attention to me?"
//                + delay(1500)
//                + Gestures.BrowRaise(strength=2.0, duration=2.0)
//                + "I'm going to explain important information!"
//                + delay(1500)
//            }
//            furhat.say(attention_test)
//
//            while (!user.isAttendingFurhat()) {
//                delay(1000)
//            }
//
//            val back_test = utterance {
//                + Gestures.Smile(strength=5.0, duration=5.0)
//                + delay(1000)
//                + "Great, I got you back!"
//            }
//            furhat.say(back_test)
//        }
//    }

    onEntry {
        playing = true
        speaking = false
        delay(2000)

        while (Rounds.currentRoundIndex < 3) {

            if (Rounds.currentRoundIndex < 1) {
                speaking = true
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
//            if (!users.current.isAttendingFurhat) {
//                goto(Attention)
//            }
//            if (!users.current.isAttendingFurhat) {
//                val attention_test = utterance {
//                    + Gestures.Shake(strength=5.0, duration=1.5)
//                    + "Could you pay some attention to me?"
//                    + delay(1500)
//                    + Gestures.BrowRaise(strength=2.0, duration=2.0)
//                    + "I'm going to explain important information!"
//                    + delay(1500)
//                }
//                furhat.say(attention_test)
//
//                while (!users.current.isAttendingFurhat) {
//                    delay(1000)
//                }
//
//                val back_test = utterance {
//                    + Gestures.Smile(strength=5.0, duration=5.0)
//                    + delay(1000)
//                    + "Great, I got you back!"
//                }
//                furhat.say(back_test)
//            }
            when (scenario) {
                0 -> { // No Confusion
                    furhat.say(currentRound.noConfusion)
                }
                1 -> { // Productive Confusion
                    furhat.say(currentRound.productiveConfusion)
                }
                2 -> { // Unproductive Confusion
                    furhat.say(currentRound.unproductiveConfusion)
                }
            }
            delay(1000)
            when (Rounds.currentRoundIndex) {

                0 -> { // Questions for Round 1
                    val initial_speak = utterance {
                        + Gestures.Thoughtful(strength=2.0, duration=1.5)
                        + "Alright, ready for some challenging questions?"
                    }
                    furhat.say(initial_speak)
                    furhat.ask("Otherwise, I can repeat the information.")
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
        when (scenario) {
            0 -> { // No Confusion
                furhat.say(currentRound.noConfusion)
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
            1 -> { // Productive Confusion
                furhat.say(currentRound.productiveConfusion)
            }
            2 -> { // Unproductive Confusion
                furhat.say(currentRound.unproductiveConfusion)
            }
        }
    }
}