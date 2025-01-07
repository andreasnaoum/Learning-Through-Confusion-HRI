package furhatos.app.quiz.flow.main

object Rounds {

    var currentRoundIndex: Int = 0

    val rounds = listOf(
        Round(
            description = """
                Artificial Intelligence is a field of computer science focused on creating intelligent machines that can simulate human cognitive abilities through various techniques like machine learning, neural networks, and natural language processing to solve complex problems and make decisions across diverse applications.
                Let’s start with some basic questions for Artificial Intelligence. Remember, you can always ask for a hint to help you.
            """
        ),
        Round(
            description = """
                Now, we’ll be exploring the basics of supervised and unsupervised learning. 
                In supervised learning, the machine learns like a student with a teacher. It learns with examples that are already marked with labeled data as correct answers. In unsupervised learning, the machine works more like an explorer. It looks at data without any labels and tries to find patterns on its own without being told what groups to make.
                Let’s see how it works as we go through some questions. Remember, you can always ask for a hint to help you.
            """
        ),
        Round(
            description = """
                Let’s move on, now, we’re learning about reinforcement learning. 
                Imagine teaching a dog new tricks. When the dog does something right, you give it a reward; when it makes a mistake, you don't reward it. Over time, the dog learns which actions lead to treats and repeats those behaviors. This is exactly how reinforcement learning works! In reinforcement learning, an AI agent learns by interacting with an environment, just like the dog. When it takes an action, it gets feedback in the form of rewards or penalties.
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