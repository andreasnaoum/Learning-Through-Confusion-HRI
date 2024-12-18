package furhatos.app.quiz.questions

import furhatos.app.quiz.AnswerOption
import furhatos.nlu.EnumItem
import furhatos.nlu.TextBuilder
import java.util.*

class QuestionSet(val questions: List<Question>) {
    private var currentIndex: Int = -1  // Start at -1
    lateinit var current: Question

    fun next(): Boolean {
        currentIndex++
        if (currentIndex >= questions.size) {
            return false
        }
        current = questions[currentIndex]
        AnswerOption().forget()
        return true
    }

    // Add this to check if we have more questions
    fun hasMoreQuestions(): Boolean {
        return currentIndex + 1 <= questions.size - 1
    }

    // Add this to get current question number
    fun getCurrentQuestionNumber(): Int {
        return currentIndex + 1
    }
}

//class QuestionSet(val questions: List<Question>) {
//    var count: Int = 0
//    var current: Question = questions.first()
//
//    fun next() {
//        count++
//        if (count >= questions.size)
//            count = 0
//        current = questions[count]
//        AnswerOption().forget()
//    }
//}

/**
 * The question class gets the following parameters:
 * @text : The question as a String
 * @answer : A list containing the correct answer to the question, followed by alternative pronunciations of the correct answer
 * @alternatives A list, containing lists of other (wrong) answers. Every other answer is also followed by alternative pronunciations of the correct answer.
 */
class Question(
    val text: String,
    val noconfusion: String,
    val productiveconfusion: String,
    val unproductiveconfusion: String,
    answer: List<String>,
    alternatives: List<List<String>>
) {
    //All options, used to prime the NLU
    var options : MutableList<EnumItem> = mutableListOf()
    //Only the first option of the answers, these are correctly spelled, and not alternative.
    var primeoptions : MutableList<EnumItem> = mutableListOf()

    var noConfusionHint: String = noconfusion

    var productiveConfusionHint: String = productiveconfusion

    var unproductiveConfusionHint: String = unproductiveconfusion

    //init loads the first item of the list into primeoptions
    //And loads everything into options
    init {
        primeoptions.add(EnumItem(AnswerOption(true, answer.first()), answer.first()))
        answer.forEach {
            options.add(EnumItem(AnswerOption(true, it), it))
        }

        alternatives.forEach {
            primeoptions.add(EnumItem(AnswerOption(false, it.first()), it.first()))
            it.forEach {
                options.add(EnumItem(AnswerOption(false, it), it))
            }
        }

        options.shuffle()
        primeoptions.shuffle()
    }

    //Returns the well formatted answer options
    fun getOptionsString() : String {
        var text = TextBuilder()
        text.appendList(primeoptions.map { it.wordString }, "or")
        return text.toString()
    }

    //Returns the well formatted answer options
    val speechPhrases : List<String>
        get() = primeoptions.map { it.wordString ?: "" }

}

