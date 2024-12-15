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
                "If an AI learns to sort your photos into 'cats' and 'dogs' by looking at labeled pictures, what type of learning is this? 🐶🐱",
                answer = listOf("Supervised Learning", "Supervised"),
                noconfusion = "",
                productiveconfusion = "",
                unproductiveconfusion = "",
                alternatives = listOf(
                        listOf("Unsupervised Learning"),
                        listOf("Reinforcement Learning"),
                        listOf("Generative Learning")
                )
        ),

//        Question(
//                "In supervised learning, what does the model use to make predictions? 📊",
//                answer = listOf("Labeled data"),
//                alternatives = listOf(
//                        listOf("Unlabeled data"),
//                        listOf("Random guesses"),
//                        listOf("Predefined instructions")
//                )
//        ),
//
//        Question(
//                "What is the main goal of unsupervised learning? 🤔",
//                answer = listOf("To find patterns in data without labels"),
//                alternatives = listOf(
//                        listOf("To classify data into predefined categories"),
//                        listOf("To make predictions with labeled data"),
//                        listOf("To create new labels for the data")
//                )
//        ),
//
//        Question(
//                "In supervised learning, what does the model learn from the labeled data? 📚",
//                answer = listOf("The relationship between input and output"),
//                alternatives = listOf(
//                        listOf("Only the input values"),
//                        listOf("Only the output labels"),
//                        listOf("Random patterns")
//                )
//        )
)

val questionsRound3 = mutableListOf(
//        Question(
//                "What does AI stand for? 🤖",
//                answer = listOf("Artificial Intelligence", "AI"),
//                alternatives = listOf(
//                        listOf("Awesome Intelligence"),
//                        listOf("Amazing Insights"),
//                        listOf("Advanced Innovation")
//                )
//        ),
//
//        Question(
//                "If an AI learns to sort your photos into 'cats' and 'dogs' by looking at labeled pictures, what type of learning is this? 🐶🐱",
//                answer = listOf("Supervised Learning", "Supervised"),
//                alternatives = listOf(
//                        listOf("Unsupervised Learning"),
//                        listOf("Reinforcement Learning"),
//                        listOf("Generative Learning")
//                )
//        ),

        Question(
                "In supervised learning, what does the model use to make predictions? 📊",
                noconfusion = "",
                productiveconfusion = "",
                unproductiveconfusion = "",
                answer = listOf("Labeled data"),
                alternatives = listOf(
                        listOf("Unlabeled data"),
                        listOf("Random guesses"),
                        listOf("Predefined instructions")
                )
        ),

//        Question(
//                "What is the main goal of unsupervised learning? 🤔",
//                answer = listOf("To find patterns in data without labels"),
//                alternatives = listOf(
//                        listOf("To classify data into predefined categories"),
//                        listOf("To make predictions with labeled data"),
//                        listOf("To create new labels for the data")
//                )
//        ),
//
//        Question(
//                "In supervised learning, what does the model learn from the labeled data? 📚",
//                answer = listOf("The relationship between input and output"),
//                alternatives = listOf(
//                        listOf("Only the input values"),
//                        listOf("Only the output labels"),
//                        listOf("Random patterns")
//                )
//        )
)

//val questionsAI = mutableListOf(
//        // Super simple starter question
//        Question("What does AI stand for? 🤖",
//                answer = listOf("Artificial Intelligence", "AI"),
//                alternatives = listOf(listOf("Awesome Intelligence"), listOf("Amazing Insights"), listOf("Advanced Innovation"))),
//
//        // Relatable example
//        Question("If an AI learns to sort your photos into 'cats' and 'dogs' by looking at labeled pictures, what type of learning is this? 🐶🐱",
//                answer = listOf("Supervised Learning", "Supervised"),
//                alternatives = listOf(listOf("Unsupervised Learning"), listOf("Reinforcement Learning"), listOf("Generative Learning"))),
//
//        // Fun analogy
//        Question("If an AI groups similar songs together on a playlist without labels, what type of learning is it using? 🎶",
//                answer = listOf("Unsupervised Learning", "Unsupervised"),
//                alternatives = listOf(listOf("Supervised Learning"), listOf("Reinforcement Learning"), listOf("Clustering"))),
//
//        // Accessible and simple
//        Question("What kind of AI recommends movies on Netflix based on what you've already watched? 🎥🍿",
//                answer = listOf("Supervised Learning", "Supervised"),
//                alternatives = listOf(listOf("Unsupervised Learning"), listOf("Reinforcement Learning"), listOf("Decision Trees"))),
//
//        // Playful and beginner-friendly
//        Question("If an AI learns from trial and error, like a robot learning to play soccer, what type of learning is it using? ⚽🤖",
//                answer = listOf("Reinforcement Learning", "Reinforcement"),
//                alternatives = listOf(listOf("Supervised Learning"), listOf("Unsupervised Learning"), listOf("Clustering"))),
//
//        // Relatable shopping example
//        Question("When an AI groups customers with similar shopping habits to suggest products, what type of learning is this? 🛍️",
//                answer = listOf("Unsupervised Learning", "Unsupervised"),
//                alternatives = listOf(listOf("Supervised Learning"), listOf("Reinforcement Learning"), listOf("Transfer Learning"))),
//
//        // Fun and engaging
//        Question("Which learning type uses labeled data, like 'This is a pizza, this is a burger'? 🍕🍔",
//                answer = listOf("Supervised Learning", "Supervised"),
//                alternatives = listOf(listOf("Unsupervised Learning"), listOf("Reinforcement Learning"), listOf("Generative Learning"))),
//
//        // Accessible with a touch of humor
//        Question("If an AI creates funny captions for photos without help, what type of AI might it be? 📸😂",
//                answer = listOf("Generative AI", "Generative"),
//                alternatives = listOf(listOf("Supervised Learning"), listOf("Clustering"), listOf("Reinforcement Learning"))),
//
//        // Real-world application
//        Question("What type of learning helps AI create clusters of similar-looking shirts when shopping online? 👕🛒",
//                answer = listOf("Unsupervised Learning", "Clustering"),
//                alternatives = listOf(listOf("Supervised Learning"), listOf("Reinforcement Learning"), listOf("Decision Trees"))),
//
//        // Easy-to-grasp comparison
//        Question("If an AI predicts tomorrow’s weather based on labeled historical data, what learning type is it using? 🌦️",
//                answer = listOf("Supervised Learning", "Supervised"),
//                alternatives = listOf(listOf("Unsupervised Learning"), listOf("Reinforcement Learning"), listOf("Neural Networks"))),
//
//        // Fun and intuitive
//        Question("If an AI groups your vacation photos into 'beach,' 'mountains,' and 'city' without labels, what learning type is this? 🌴⛰️🏙️",
//                answer = listOf("Unsupervised Learning", "Unsupervised"),
//                alternatives = listOf(listOf("Supervised Learning"), listOf("Reinforcement Learning"), listOf("Generative AI"))),
//
//        // Accessible reinforcement learning example
//        Question("When an AI wins a video game by trying, failing, and improving its strategy, what learning type is this? 🎮",
//                answer = listOf("Reinforcement Learning", "Reinforcement"),
//                alternatives = listOf(listOf("Supervised Learning"), listOf("Unsupervised Learning"), listOf("Transfer Learning"))),
//
//        // Engaging with a bit of challenge
//        Question("What does clustering mean in AI? 🤔",
//                answer = listOf("Grouping similar items together", "Grouping", "Clusters"),
//                alternatives = listOf(listOf("Classifying items"), listOf("Predicting outcomes"), listOf("Finding rewards"))),
//
//        // Fun finale
//        Question("If an AI can recognize your face in a photo, what kind of learning is behind it? 📸🙂",
//                answer = listOf("Supervised Learning", "Supervised"),
//                alternatives = listOf(listOf("Unsupervised Learning"), listOf("Reinforcement Learning"), listOf("Decision Trees")))
//)
//
//val questionsEnglish = mutableListOf(
//        // Super simple starter question
//        Question("What does AI stand for? 🤖",
//                answer = listOf("Artificial Intelligence", "AI"),
//                alternatives = listOf(listOf("Awesome Intelligence"), listOf("Amazing Insights"), listOf("Advanced Innovation"))),
//
//        // Relatable example
//        Question("If an AI learns to sort your photos into 'cats' and 'dogs' by looking at labeled pictures, what type of learning is this? 🐶🐱",
//                answer = listOf("Supervised Learning", "Supervised"),
//                alternatives = listOf(listOf("Unsupervised Learning"), listOf("Reinforcement Learning"), listOf("Generative Learning"))),
//
//        // Fun analogy
//        Question("If an AI groups similar songs together on a playlist without labels, what type of learning is it using? 🎶",
//                answer = listOf("Unsupervised Learning", "Unsupervised"),
//                alternatives = listOf(listOf("Supervised Learning"), listOf("Reinforcement Learning"), listOf("Clustering"))),
//
//        // Accessible and simple
//        Question("What kind of AI recommends movies on Netflix based on what you've already watched? 🎥🍿",
//                answer = listOf("Supervised Learning", "Supervised"),
//                alternatives = listOf(listOf("Unsupervised Learning"), listOf("Reinforcement Learning"), listOf("Decision Trees"))),
//
//        // Playful and beginner-friendly
//        Question("If an AI learns from trial and error, like a robot learning to play soccer, what type of learning is it using? ⚽🤖",
//                answer = listOf("Reinforcement Learning", "Reinforcement"),
//                alternatives = listOf(listOf("Supervised Learning"), listOf("Unsupervised Learning"), listOf("Clustering"))),
//
//        // Relatable shopping example
//        Question("When an AI groups customers with similar shopping habits to suggest products, what type of learning is this? 🛍️",
//                answer = listOf("Unsupervised Learning", "Unsupervised"),
//                alternatives = listOf(listOf("Supervised Learning"), listOf("Reinforcement Learning"), listOf("Transfer Learning"))),
//
//        // Fun and engaging
//        Question("Which learning type uses labeled data, like 'This is a pizza, this is a burger'? 🍕🍔",
//                answer = listOf("Supervised Learning", "Supervised"),
//                alternatives = listOf(listOf("Unsupervised Learning"), listOf("Reinforcement Learning"), listOf("Generative Learning"))),
//
//        // Accessible with a touch of humor
//        Question("If an AI creates funny captions for photos without help, what type of AI might it be? 📸😂",
//                answer = listOf("Generative AI", "Generative"),
//                alternatives = listOf(listOf("Supervised Learning"), listOf("Clustering"), listOf("Reinforcement Learning"))),
//
//        // Real-world application
//        Question("What type of learning helps AI create clusters of similar-looking shirts when shopping online? 👕🛒",
//                answer = listOf("Unsupervised Learning", "Clustering"),
//                alternatives = listOf(listOf("Supervised Learning"), listOf("Reinforcement Learning"), listOf("Decision Trees"))),
//
//        // Easy-to-grasp comparison
//        Question("If an AI predicts tomorrow’s weather based on labeled historical data, what learning type is it using? 🌦️",
//                answer = listOf("Supervised Learning", "Supervised"),
//                alternatives = listOf(listOf("Unsupervised Learning"), listOf("Reinforcement Learning"), listOf("Neural Networks"))),
//
//        // Fun and intuitive
//        Question("If an AI groups your vacation photos into 'beach,' 'mountains,' and 'city' without labels, what learning type is this? 🌴⛰️🏙️",
//                answer = listOf("Unsupervised Learning", "Unsupervised"),
//                alternatives = listOf(listOf("Supervised Learning"), listOf("Reinforcement Learning"), listOf("Generative AI"))),
//
//        // Accessible reinforcement learning example
//        Question("When an AI wins a video game by trying, failing, and improving its strategy, what learning type is this? 🎮",
//                answer = listOf("Reinforcement Learning", "Reinforcement"),
//                alternatives = listOf(listOf("Supervised Learning"), listOf("Unsupervised Learning"), listOf("Transfer Learning"))),
//
//        // Engaging with a bit of challenge
//        Question("What does clustering mean in AI? 🤔",
//                answer = listOf("Grouping similar items together", "Grouping", "Clusters"),
//                alternatives = listOf(listOf("Classifying items"), listOf("Predicting outcomes"), listOf("Finding rewards"))),
//
//        // Fun finale
//        Question("If an AI can recognize your face in a photo, what kind of learning is behind it? 📸🙂",
//                answer = listOf("Supervised Learning", "Supervised"),
//                alternatives = listOf(listOf("Unsupervised Learning"), listOf("Reinforcement Learning"), listOf("Decision Trees")))
//)