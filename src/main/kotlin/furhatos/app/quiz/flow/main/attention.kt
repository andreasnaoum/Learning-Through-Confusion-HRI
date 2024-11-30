package furhatos.app.quiz.flow.main

import furhatos.app.quiz.flow.Parent
import furhatos.gestures.Gestures
import furhatos.flow.kotlin.*
import furhatos.app.quiz.flow.main.NewGame


val Attention = state(parent = Parent) {

    onEntry {
        val attention_test = utterance {
            + Gestures.Shake(strength=5.0, duration=1.5)
            + "Could you pay some attention to me?"
            + delay(1500)
            + Gestures.BrowRaise(strength=2.0, duration=2.0)
            + "I'm going to explain important information!"
            + delay(1500)
        }
        furhat.say(attention_test)

        while (!users.current.isAttendingFurhat) {
            delay(1000)
        }

        val back_test = utterance {
            + Gestures.Smile(strength=5.0, duration=5.0)
            + delay(1000)
            + "Great, I got you back!"
        }
        furhat.say(back_test)
//        goto(NewGame)
    }

}