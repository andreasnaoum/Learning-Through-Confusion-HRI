package furhatos.app.quiz.setting

import furhatos.records.Record
import furhatos.records.User
import furhatos.skills.UserManager


// Metrics data classes properly extending Record
class QuestionMetrics : Record() {
    var startTime: Long = 0
    var endTime: Long = 0
    var timeTaken: Double = 0.0
    var isCorrect: Boolean = false
    var attempts: Int = 0
    var hintsUsed: Int = 0
    var repeatRequests: Int = 0
}

class RoundMetrics : Record() {
    val questionMetrics: MutableMap<Int, QuestionMetrics> = mutableMapOf()
    var roundStartTime: Long = 0
    var roundEndTime: Long = 0
    var totalRoundTime: Double = 0.0
    var correctAnswers: Int = 0
    var totalQuestions: Int = 3
}

class GameMetrics : Record() {
    val roundMetrics: MutableMap<Int, RoundMetrics> = mutableMapOf()
    var gameStartTime: Long = 0
    var gameEndTime: Long = 0
    var totalGameTime: Double = 0.0
    var totalScore: Int = 0
}

// Modified SkillData class
class SkillData(
    var score: Int = 0,
    var lastScore: Int = 0,
    var interested: Boolean = true,
    var playing: Boolean = false,
    var played: Boolean = false,
    var attenting: Boolean = false,
    var questionsAsked: MutableList<String> = mutableListOf(),
) : Record() {
    // Initialize gameMetrics as a property
    var gameMetrics: GameMetrics = GameMetrics()
}

// The rest of your existing code remains the same
val User.quiz: SkillData
    get() = data.getOrPut(SkillData::class.qualifiedName, SkillData())

fun UserManager.interested() = list.filter {
    it.quiz.interested && !it.quiz.playing
}

fun UserManager.playing() = list.filter {
    it.quiz.playing
}

fun UserManager.attending() = list.filter {
    it.quiz.attenting
}

fun UserManager.notQuestioned(question: String) = list.filter {
    it.quiz.playing && !it.quiz.questionsAsked.contains(question)
}

fun UserManager.nextPlaying(): User {
    return list.filter {
        it.quiz.playing && it != current
    }.firstOrNull() ?: current
}

// Helper extension functions for metrics
fun SkillData.startNewGame() {
    gameMetrics = GameMetrics()
    gameMetrics.gameStartTime = System.currentTimeMillis()
}

fun SkillData.startNewRound(roundIndex: Int) {
    gameMetrics.roundMetrics[roundIndex] = RoundMetrics()
    gameMetrics.roundMetrics[roundIndex]!!.roundStartTime = System.currentTimeMillis()
}

fun SkillData.startNewQuestion(roundIndex: Int, questionIndex: Int) {
    val roundMetrics = gameMetrics.roundMetrics[roundIndex] ?: RoundMetrics()
    val newQuestionMetrics = QuestionMetrics()
    newQuestionMetrics.startTime = System.currentTimeMillis()
    roundMetrics.questionMetrics[questionIndex] = newQuestionMetrics
    gameMetrics.roundMetrics[roundIndex] = roundMetrics
}

//fun SkillData.startNewQuestion(roundIndex: Int, questionIndex: Int) {
//    val roundMetrics = gameMetrics.roundMetrics[roundIndex] ?: RoundMetrics()
//    roundMetrics.questionMetrics[questionIndex] = QuestionMetrics(
//        startTime = System.currentTimeMillis()
//    )
//    gameMetrics.roundMetrics[roundIndex] = roundMetrics
//}

fun SkillData.endQuestion(roundIndex: Int, questionIndex: Int, isCorrect: Boolean, attempts: Int) {
    val questionMetrics = gameMetrics.roundMetrics[roundIndex]?.questionMetrics?.get(questionIndex)
    questionMetrics?.let {
        it.endTime = System.currentTimeMillis()
        it.timeTaken = (it.endTime - it.startTime) / 1000.0
        it.isCorrect = isCorrect
        it.attempts = attempts
    }
}

fun SkillData.endRound(roundIndex: Int) {
    val roundMetrics = gameMetrics.roundMetrics[roundIndex]
    roundMetrics?.let {
        it.roundEndTime = System.currentTimeMillis()
        it.totalRoundTime = (it.roundEndTime - it.roundStartTime) / 1000.0
        it.correctAnswers = it.questionMetrics.count { entry -> entry.value.isCorrect }
    }
}

fun SkillData.endGame() {
    gameMetrics.gameEndTime = System.currentTimeMillis()
    gameMetrics.totalGameTime = (gameMetrics.gameEndTime - gameMetrics.gameStartTime) / 1000.0
    gameMetrics.totalScore = score
}

//// User variables
//class SkillData(
//        var score : Int = 0,
//        var lastScore : Int = 0,
//        var interested : Boolean = true,
//        var playing: Boolean = false,
//        var played : Boolean = false,
//        var attenting: Boolean = false,
//        var questionsAsked : MutableList<String> = mutableListOf()
//) : Record()
//
//val User.quiz : SkillData
//    get() = data.getOrPut(SkillData::class.qualifiedName, SkillData())
//
//// Custom user getters for convenience
//fun UserManager.interested() = list.filter {
//    it.quiz.interested && !it.quiz.playing
//}
//
//fun UserManager.playing() = list.filter {
//    it.quiz.playing
//}
//
//fun UserManager.attending() = list.filter {
//    it.quiz.attenting
//}
//
//fun UserManager.notQuestioned(question: String) = list.filter {
//    it.quiz.playing && !it.quiz.questionsAsked.contains(question)
//}
//
//fun UserManager.nextPlaying() : User {
//    val nextPlayer = list.filter {
//        it.quiz.playing && it != current
//    }.first()
//    if (nextPlayer == null) {
//        return current
//    }
//    else {
//        return nextPlayer
//    }
//}

