package furhatos.app.quiz.questions

/**
 * The questions are structured like
 *  -The question
 *  -The correct answer, followed by alternative pronounciations
 *  -A list of other answers followed by their alternatives
 */

val questionsRound1 = mutableListOf(

        Question(
                "If an AI learns to sort your photos into 'cats' and 'dogs' by looking at labeled pictures, what type of learning is this?",
                noconfusion = "When a computer isn’t told what’s in the pictures (no labels), it has to group them by itself. This type of learning relies on identifying patterns in the data, without any prior guidance or supervision about what the groups should represent.",
                productiveconfusion = "Imagine the computer has to figure out the groups by looking for patterns, without being told what a cat or a dog looks like. What type of learning could this be?",
                unproductiveconfusion = "It’s when the computer decides how to group data on its own. Maybe try trial and error?",
                answer = listOf("Supervised Learning", "Supervised"),
                alternatives = listOf(
                        listOf(
                                "Unsupervised Learning",
                                "Unsupervised",
                        ),
                        listOf(
                                "Reinforcement Learning",
                                "Reinforcement"
                        ),
                        listOf(
                                "Generative Learning",
                                "Generative"
                        )
                ),
        ),

        Question(
                "Which of these situations involves supervised learning?",
                noconfusion = "When you show a computer labeled examples—like the letter ‘a’ with the label alpha—you’re essentially supervising it by providing the correct answers to learn from",
                productiveconfusion = "This learning involves giving the computer clear examples with the answers already labeled so it can learn from them.",
                unproductiveconfusion = "This involves the computer learning with labeled data",
                answer = listOf(
                        "Training a model to recognize handwritten numbers by providing labeled examples of each number",
                        "Training",
                        "model",
                        "recognize handwritten numbers"
                ),
                alternatives = listOf(
                        listOf(
                                "Grouping customers based on their purchase history without knowing customer types in advance",
                                "Grouping",
                                "customers",
                                "purchase",
                                "history"
                        ),
                        listOf(
                                "Allowing a robot to learn the fastest way through a maze by trial and error",
                                "trial and error"
                        ),
                        listOf(
                                "Clustering data points without providing any labels",
                                "Clustering"
                        )
                )
        ),

        Question(
                "In which type of learning does the algorithm rely on labeled data to make predictions or decisions?",
                noconfusion = "This type of learning happens when a teacher provides both the data and the correct answers, giving the computer the supervision it needs to learn the relationship between them.",
                productiveconfusion = "The key here is that the computer needs both the data and the correct answers (labels) to learn from.",
                unproductiveconfusion = "It’s where the computer uses some kind of guidance to learn.",
                answer = listOf("Supervised learning", "Supervised"),
                alternatives = listOf(
                        listOf("Unsupervised learning", "Unsupervised"),
                        listOf("Transfer learning", "Transfer"),
                        listOf("Reinforcement learning", "Reinforcement"),
                )
        ),
)

val questionsRound2 = mutableListOf(
        Question(
                "What is the main goal of Reinforcement Learning?",
                noconfusion = "The main goal of RL is to make decisions that maximize the reward the agent receives over time.",
                productiveconfusion = "RL is like learning to ride a bike; the agent takes actions, sees what works as feedback, and improves to achieve a goal, like staying balanced.",
                unproductiveconfusion = "Reinforcement Learning is about learning and involves some kind of trial and error process.",
                answer = listOf(
                        "To maximize rewards over time by learning from feedback.",
                        "To maximize rewards",
                        "learning from feedback",
                        "rewards"
                ),
                alternatives = listOf(
                        listOf(
                                "To find an optimal way to solve all problems.",
                                "solve all problems",
                                "To find an optimal way",
                                "optimal way",
                                "problems"
                        ),
                        listOf(
                                "To create a perfect model of the environment before the agent starts learning.",
                                "perfect model",
                                "agent starts learning",
                                "environment",
                        ),
                        listOf(
                                "To follow a predefined sequence of steps without adapting.",
                                "without adapting",
                                "predefined sequence ",
                                "To follow"
                        )
                )
        ),

        Question(
                "What is the difference between an agent and its environment in Reinforcement Learning?",
                noconfusion = "The agent is the decision-maker, and the environment reacts to those decisions by giving feedback.",
                productiveconfusion = "Think of a robot exploring a room. The agent is the robot itself (its brain and sensors), and the environment is everything outside the robot (the walls, floor, obstacles).",
                unproductiveconfusion = "The agent and environment interact in ways that are system-dependent.",
                answer = listOf(
                        "The agent takes actions, and the environment provides feedback based on these actions.",
                        "agent takes actions",
                        "environment provides feedback",
                        "these actions"
                ),
                alternatives = listOf(
                        listOf(
                                "The agent creates the environment.",
                                "agent creates the environment",
                                "creates the environment",
                                "agent creates "
                        ),
                        listOf(
                                "The agent and the environment have the same functions and roles.",
                                "The agent and the environment",
                                "same functions and roles",
                                "roles",
                        ),
                        listOf(
                                "The environment learns from the agent’s actions, not the other way around.",
                                "environment learns",
                                "agent’s actions ",
                                "not the other way around"
                        )
                )
        ),

        Question(
                "What is a policy in Reinforcement Learning?",
                noconfusion = "A policy is the set of rules the agent follows to choose actions based on the situation.",
                productiveconfusion = "A policy is like a strategy in chess that tells you which move to make depending on the board's current state.",
                unproductiveconfusion = "A policy is like a strategy in chess that tells you which move to make depending on the board's current state.",
                answer = listOf(
                        "A rule or function that governs the agent’s actions in different situations",
                        "function that governs"
                ),
                alternatives = listOf(
                        listOf(
                                "A way to track the rewards the agent receives over time.",
                                "agent receives over time",
                                "way to track"
                        ),
                        listOf(
                                "A map of the environment that the agent navigates",
                                "map of the environment",
                                "agent navigates"
                        ),
                        listOf(
                                "A strategy the agent uses to maximize its penalty in a given environment.",
                                "A strategy the agent",
                                "uses to maximize",
                                "penalty"
                        ),
                )
        ),
)

val questionsRound3 = mutableListOf(

        Question(
                "What makes a Generative Model different from a Discriminative Model?",
                noconfusion = "The main difference is that a Generative Model wants to generate new data.",
                productiveconfusion = "Discriminate models focus mostly on classification tasks.",
                unproductiveconfusion = "Generative Models and Discriminative Models can be trained on the same dataset",
                answer = listOf("Generative models focus on creating new data", "new data"),
                alternatives = listOf(
                        listOf(
                                "Generative models are used only for classification tasks",
                                "classification tasks",
                        ),
                        listOf(
                                "Generative models only use labeled data",
                                "labeled data"
                        ),
                        listOf(
                                "Generative models can only be used in supervised learning",
                                "supervised learning"
                        )
                ),
        ),

        Question(
                "What is one of the goals of a Generative Model?",
                noconfusion = "When you show a computer labeled examples—like the letter ‘a’ with the label alpha—you’re essentially supervising it by providing the correct answers to learn from",
                productiveconfusion = "This learning involves giving the computer clear examples with the answers already labeled so it can learn from them.",
                unproductiveconfusion = "This involves the computer learning with labeled data",
                answer = listOf(
                        "To generate new data based on the patterns learned from the training data.",
                        "patterns",
                        "learned",
                ),
                alternatives = listOf(
                        listOf(
                                "To classify unseen data based on input features",
                                "classify",
                                "input features",
                        ),
                        listOf(
                                "To generate new data that is as similar as possible to the training data",
                                "similar",
                                "possible",
                        ),
                        listOf(
                                "To predict unseen data",
                                "unseen data",
                                "predict",
                        )
                )
        ),

        Question(
                "How does a Generative Model learn?",
                noconfusion = "It learns by calculating the underlying structure of what it's being trained on.",
                productiveconfusion = "It learns by approximating an imaginary dataset structure compared to the one it is trained on.",
                unproductiveconfusion = "It learns by understanding what should come next based on the dataset.",
                answer = listOf("By understanding the underlying distribution of the input data", "understanding"),
                alternatives = listOf(
                        listOf("By learning all the data points on the data it was trained on", "data points"),
                        listOf("By minimizing the model's output error over time", "minimizing"),
                        listOf("By learning the distribution of the data it generates", "distribution"),
                )
        ),


)