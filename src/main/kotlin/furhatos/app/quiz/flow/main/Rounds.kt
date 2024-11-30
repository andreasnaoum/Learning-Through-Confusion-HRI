package furhatos.app.quiz.flow.main

object Rounds {

    var currentRoundIndex: Int = 0

    val rounds = listOf(
        Round(
            description = "Let's start by understanding the basics of two important learning methods in AI: supervised and unsupervised learning.",
            noConfusion = "Supervised learning is when an AI is trained using labeled data. For example, imagine teaching a robot about fruits by showing it labeled pictures of apples or bananas. Unsupervised learning is when the AI figures things out without labels. It might group fruit pictures by color or shape.",
            productiveConfusion = "Supervised learning involves labels, like showing the robot a picture of an apple and telling it, 'This is an apple.' What do you think happens if there are no labels?",
            unproductiveConfusion = "Supervised learning uses labeled data. Unsupervised learning doesn’t use labels. Let's move on."
        ),
        Round(
            description = "In this round, we'll look at how these learning methods are applied in the real world.",
            noConfusion = "Supervised learning is used for tasks like spam detection, where you have labeled examples of spam and non-spam emails. Unsupervised learning can help group customers by their behavior when you don’t have predefined categories.",
            productiveConfusion = "Supervised learning helps with specific predictions. Can you think of where unsupervised learning might be used?",
            unproductiveConfusion = "Supervised learning is useful for specific tasks. Unsupervised learning is different. Let’s move on.",
            ),
        Round(
            description = "Now let's bring these concepts to life with some real-world scenarios.",
            noConfusion = "Supervised learning is like training a chatbot to recognize specific phrases. Unsupervised learning could help cluster news articles by topic.",
            productiveConfusion = "If you wanted a chatbot to recognize certain phrases, would that need labels? What about grouping news articles by topic?",
            unproductiveConfusion = "Supervised learning involves chatbots. Unsupervised learning can cluster data. Let’s move on.",
        )
    )

    val current: Round
        get() = rounds[currentRoundIndex]

    fun next() {
        currentRoundIndex++
        if (currentRoundIndex >= rounds.size) {
            currentRoundIndex = 0 // Loop back to the first round if needed
        }
    }

    fun reset() {
        currentRoundIndex = 0
    }
}

data class Round(
    val description: String,
    val noConfusion: String,
    val productiveConfusion: String,
    val unproductiveConfusion: String,
)