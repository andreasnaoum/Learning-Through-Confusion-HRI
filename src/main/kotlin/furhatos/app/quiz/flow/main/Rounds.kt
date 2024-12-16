package furhatos.app.quiz.flow.main

object Rounds {

    var currentRoundIndex: Int = 0

    val rounds = listOf(
        Round(
            description = """
                Now, we’ll be exploring the basics of supervised and unsupervised learning. 
                In supervised learning, a machine learns from labeled data. In unsupervised learning, the machine doesn’t have any labels. 
                Let’s see how it works as we go through some questions. Remember, you can always ask for a hint to help you.
            """
        ),
        Round(
            description = """
                Now, we’ll be exploring the basics of reinforcement learning. 
                Reinforcement learning is the science of decision making. It is about learning the optimal behavior in an environment to obtain maximum reward.
                Let’s see how it works as we go through some questions. Remember, you can always ask for a hint to help you.
            """
        ),
        Round(
            description = """
                Let’s move on, now, we’re learning about Generative models. 
                Generative AI models can take inputs such as text, image, audio, video, and code and generate new content into any of the modalities mentioned.
                Let’s see how it works as we go through some questions. Remember, you can always ask for a hint to help you.
            """
        ),
    )

    val current: Round
        get() = rounds[currentRoundIndex]

    fun next() {
        currentRoundIndex++
//        if (currentRoundIndex > rounds.size) {
//            currentRoundIndex = 0 // Loop back to the first round if needed
//        }
    }

    fun reset() {
        currentRoundIndex = 0
    }
}

data class Round(
    val description: String,
)