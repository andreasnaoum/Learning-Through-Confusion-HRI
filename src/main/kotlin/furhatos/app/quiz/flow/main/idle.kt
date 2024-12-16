package furhatos.app.quiz.flow.main

import furhatos.app.quiz.flow.Parent
import furhatos.app.quiz.questions.QuestionSet
import furhatos.app.quiz.setting.interested
import furhatos.app.quiz.setting.playing
import furhatos.app.quiz.setting.quiz
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.records.User

val Idle: State = state {
    onEntry {
        users.interested().forEach {
            furhat.attend(it)
            goto(QueryPerson(it))
        }
        if (users.playing().isNotEmpty()) {
            furhat.attendAll()
            goto(NewGame)
        }
    }

    onUserEnter(instant = true) {
        if (users.interested().count() == 1) {
            furhat.attend(it.id)
            furhat.say("Hey there!")
            furhat.gesture(Gestures.BigSmile)
            goto(QueryPerson(it))
        } else {
            furhat.glance(it.id, async=true)
        }
    }

    onUserLeave(instant = true) {
        if (users.count > 0) {
            furhat.attend(users.other)
        } else {
            furhat.attendNobody()
        }
    }

    onResponse{
        reentry()
    }

    onNoResponse {
        reentry()
    }
}

// Variables

val maxQuestions = 2
var shouldChangeUser = true
var playing = false
var attending = false
var questions: QuestionSet? = null

fun QueryPerson(user: User) = state(parent = Parent) {
    onEntry {
        if (!user.quiz.played) {
            furhat.ask("Do you want to learn more about AI and play a game?")
        } else {
            furhat.ask("Do you want to play again? Maybe you can beat your old score of ${user.quiz.lastScore}")
        }
    }

    onResponse<Yes> {
        user.quiz.playing = true
        val response_yes = utterance {
            + Gestures.BigSmile(strength=2.0, duration=2.0)
            + "Yay!"
            + delay(1500)
            + Gestures.BigSmile(strength=2.0, duration=2.0)
            + "That’s the spirit!"
            + Gestures.Wink(strength=2.0, duration=2.0)
            + delay(1500)
        }
        furhat.say(response_yes)
        furhat.gesture(Gestures.Smile(strength= 2.0, duration = 3.0))
        val greeting = utterance {
            + Gestures.Smile(strength = 2.0, duration = 6.0)
            +"I’m a personal tutor"
            + delay(1000)
            + "Today you have the opportunity to dive into the fascinating world of"
            + Gestures.Oh(strength = 5.0, duration = 5.0)
            + delay(2500)
            + furhat.voice.emphasis("Artificial Intelligence!")
        }
        furhat.say(greeting)
        furhat.gesture(Gestures.Smile(strength = 5.0, duration = 3.0))
//        furhat.say("AI is super exciting, and I promise you’re going to have a great time.")
        goto(Idle)
    }

    onResponse<No> {
        user.quiz.interested = false
        val response_no = utterance {
            + Gestures.ExpressSad(strength=2.0, duration=2.0)
            + "Oh well..."
            + delay(2000)
            + Gestures.Surprise(strength=2.0, duration=2.0)
            + "You lost an unique experience..."
        }
        furhat.say(response_no)
        goto(Idle)
    }
}
