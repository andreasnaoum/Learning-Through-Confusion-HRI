package furhatos.app.quiz.questions

/**
 * The questions are structured like
 *  -The question
 *  -The correct answer, followed by alternative pronounciations
 *  -A list of other answers followed by their alternatives
 */

val questionsRound1 = mutableListOf(

        Question(
                "What is the main goal of Artificial Intelligence?",
                noconfusion = "Look for the option that talks about copying human abilities. Regular computers can store, run, and connect, but only one option focuses on making computers act like human brains. Which one describes this human-like behavior?",
                productiveconfusion = "Look for the option that talks about copying human abilities. Regular computers can store, run, and connect, but only one option focuses on making computers act like human brains. Which one describes this human-like behavior?",
                unproductiveconfusion = "Look for the option that talks about copying human abilities. Regular computers can store, run, and connect, but only one option focuses on making computers act like human brains. Which one describes this human-like behavior?",
                answer = listOf("To make computers think", "think"),
                alternatives = listOf(
                        listOf(
                                "To store large amounts of data",
                                "data",
                        ),
                        listOf(
                                "To make computers run faster",
                                "faster"
                        ),
                        listOf(
                                "To connect devices to the internet",
                                "internet"
                        )
                ),
        ),

        Question(
                "Which of the following is the branch of Artificial Intelligence?",
                noconfusion = "Think carefully about which technology is designed to mimic human intelligence. Three of these options focus on building or analyzing fixed systems, while one specializes in creating programs that can actually learn over time. Which one has this unique 'learning' ability?",
                productiveconfusion = "Think carefully about which technology is designed to mimic human intelligence. Three of these options focus on building or analyzing fixed systems, while one specializes in creating programs that can actually learn over time. Which one has this unique 'learning' ability?",
                unproductiveconfusion = "Think carefully about which technology is designed to mimic human intelligence. Three of these options focus on building or analyzing fixed systems, while one specializes in creating programs that can actually learn over time. Which one has this unique 'learning' ability?",
                answer = listOf(
                        "Machine learning",
                        "Machine",
                        "learning"
                ),
                alternatives = listOf(
                        listOf(
                                "Cyber Forensics",
                                "Cyber",
                                "Forensics",
                        ),
                        listOf(
                                "Full Stack Development",
                                "Full",
                                "Stack",
                                "Development"
                        ),
                        listOf(
                                "Network Design",
                                "Network",
                                "Design"
                        )
                )
        ),

        Question(
                "Who is considered the Father of Artificial Intelligence?",
                noconfusion = "While Geoffrey Hinton revolutionized deep learning, Andrew Ng pioneered modern machine learning, and Alan Turing conceptualized computing intelligence, only John McCarthy actually established the field and name of 'Artificial Intelligence' at a historic 1956 conference. Which pioneer took this founding step?",
                productiveconfusion = "While Geoffrey Hinton revolutionized deep learning, Andrew Ng pioneered modern machine learning, and Alan Turing conceptualized computing intelligence, only John McCarthy actually established the field and name of 'Artificial Intelligence' at a historic 1956 conference. Which pioneer took this founding step?",
                unproductiveconfusion = "While Geoffrey Hinton revolutionized deep learning, Andrew Ng pioneered modern machine learning, and Alan Turing conceptualized computing intelligence, only John McCarthy actually established the field and name of 'Artificial Intelligence' at a historic 1956 conference. Which pioneer took this founding step?",
                answer = listOf("John McCarthy", "John", "McCarthy"),
                alternatives = listOf(
                        listOf("Geoffrey Hinton", "Geoffrey", "Hinton"),
                        listOf("Andrew Ng", "Andrew", "Ng"),
                        listOf("Alan Turing", "Alan", "Turing"),
                )
        ),
)

val questionsRound2 = mutableListOf(
        Question(
                "Which type of machine learning would be most appropriate for automatically identifying dogs and cats in photos?",
                noconfusion = "Consider which learning type specifically requires labeled training data to supervise the learning procedure. The computer needs to learn from photos that humans have already marked as dog or cat to learn the difference between them.",
                productiveconfusion = "Think about which type of machine learning needs already labeled images for training to recognize objects in pictures.",
                unproductiveconfusion = "Think about which type of machine learning for recognizing objects in pictures.",
                answer = listOf(
                        "Supervised learning",
                        "Supervised",
                ),
                alternatives = listOf(
                        listOf(
                                "Unsupervised learning",
                                "Unsupervised",
                        ),
                        listOf(
                                "Reinforcement learning",
                                "Reinforcement",
                        ),
                        listOf(
                                "Semisupervised learning",
                                "Semisupervised"
                        )
                )
        ),

        Question(
                "Which of these situations involves supervised learning?",
                noconfusion = "When you show a computer labeled examples, like the letter a with the label alpha, you’re essentially supervising it by providing the correct answers to learn from. The other options either work without any labels or through trial and error, which is different from supervised learning.",
                productiveconfusion = "Consider which situation is like having a teacher who can check if answers are right or wrong. Which option mentions having data that's already labeled with correct answers?",
                unproductiveconfusion = "Think about how a computer learns.",
                answer = listOf(
                        "Training a model to recognize handwritten numbers by providing examples of each number",
                        "Training a model",
                        "handwritten numbers",
                        "providing examples"
                ),
                alternatives = listOf(
                        listOf(
                                "Grouping customers based on their purchase history without knowing customer types in advance",
                                "Grouping customers",
                                "purchase history",
                                "customer types"
                        ),
                        listOf(
                                "Allowing a robot to learn the fastest way through a maze by trial and error",
                                "Allowing a robot",
                                "learn the fastest way",
                                "trial and error",
                        ),
                        listOf(
                                "Clustering data points without providing any labels",
                                "Clustering data",
                                "without providing",
                                "any labels"
                        )
                )
        ),

        Question(
                "Which of these situations uses unsupervised learning?",
                noconfusion = "Imagine a store looking at how customers shop. Some might buy only during sales, others prefer premium items, some shop weekly, others monthly. These shopping patterns emerge naturally, we don't tell the computer what types of shoppers to look for. The groups form based on similar behaviors, without any predefined categories.",
                productiveconfusion = "Which situation involves the computer grouping things without being told beforehand what the groups should be? Like organizing a closet your own way, without someone telling you how to categorize the items.",
                unproductiveconfusion = "Think about patterns.",
                answer = listOf(
                        "Grouping online shoppers into different customer types based on their browsing history without predefined categories",
                        "Grouping online",
                        "different customer",
                        "browsing history",
                        "without predefined categories"
                ),
                alternatives = listOf(
                        listOf(
                                "Teaching a computer to detect spam emails using examples of spam and non-spam messages",
                                "Teaching a computer",
                                "detect spam emails",
                                "spam and non-spam messages"
                        ),
                        listOf(
                                "Training an AI to classify medical images using a database of diagnosed patient scans",
                                "Training an AI",
                                "classify medical images"
                        ),
                        listOf(
                                "Predicting house prices using data from past sales with known prices",
                                "Predicting house prices",
                                "past sales with known prices",
                                "using data from past sales"
                        ),
                )
        ),
)

val questionsRound3 = mutableListOf(

        Question(
                "Which scenario best represents the reward mechanism in a reinforcement learning system?",
                noconfusion = "Imagine riding a bicycle on a windy day, you constantly adjust your balance based on wind gusts, your speed, and the road conditions. Each adjustment affects your stability, and you learn from each moment how to stay upright. Which scenario involves similar real-time learning and adaptation to changing conditions?",
                productiveconfusion = "Consider these questions. Which scenario involves learning through actual experience rather than from previous data? Does it get immediate feedback that helps it improve right away?  Can it get better during operation without being shown correct examples?",
                unproductiveconfusion = "Think about dynamic responses.",
                answer = listOf(
                        "A drone learning to maintain stability in varying wind conditions by receiving altitude and orientation feedback signals",
                        "A drone learning",
                        "maintain stability",
                        "wind conditions"
                ),
                alternatives = listOf(
                        listOf(
                                "A factory robot learning assembly patterns from a database of correct assemblies",
                                "A factory robot",
                        ),
                        listOf(
                                "A trading algorithm using historical price patterns to identify market trends",
                                "A trading algorithm"
                        ),
                        listOf(
                                "A quality control system detecting defects based on images of good and bad products",
                                "A quality control system"
                        )
                ),
        ),

        Question(
                "What is the difference between an agent and its environment in reinforcement learning?",
                noconfusion = "Imagine playing tennis with a wall. the agent decide how to hit the ball, while the wall and court (the environment) determine where the ball goes and if you scored. You make choices and learn, while the environment shows you what happens as a result. Which option describes this kind of relationship?",
                productiveconfusion = "The agent makes moves and the environment responds to moves.",
                unproductiveconfusion = "Think about interaction.",
                answer = listOf(
                        "The agent takes actions, and the environment provides feedback based on these actions. ",
                        "The agent takes actions",
                        "environment provides feedback",
                ),
                alternatives = listOf(
                        listOf(
                                "The agent collects labeled data while the environment provides unlabeled data.",
                                "The agent collects labeled data",
                                "environment provides unlabeled data",
                        ),
                        listOf(
                                "The agent stores data while the environment processes it",
                                "The agent stores data",
                                "environment processes it",
                        ),
                        listOf(
                                "The environment learns from the agent’s actions, not the other way around.",
                                "The environment learns",
                                "from the agent’s actions",
                                "not the other way around"
                        )
                )
        ),

        Question(
                "A smart traffic system uses reinforcement learning to control traffic lights in a city. Which description best matches how it actually works?",
                noconfusion = "Think of it like playing a game: you see the current traffic situation, you adjust the traffic lights, then you see if cars move faster. You keep trying different timings until you find what works best. Which option describes this complete cycle?",
                productiveconfusion = "Which option shows a system that's actively trying to improve things rather than just analyzing or storing information?",
                unproductiveconfusion = "Think about who's in control.",
                answer = listOf(
                        "The traffic light controller looks at how many cars are waiting and how long they've been waiting. It tries different signal timing patterns and learns from seeing how these changes affect the average waiting time.",
                        "The traffic light controller"
                ),
                alternatives = listOf(
                        listOf(
                                "The cameras watch traffic and predict busy periods based on past data. They get rewarded for guessing rush hours correctly.",
                                "The cameras watch traffic"
                        ),
                        listOf(
                                "The system records how long lights stay green and stores traffic data. It aims to save storage space efficiently.",
                                "The system records"
                        ),
                        listOf(
                                "The analyzer looks at different types of vehicles and groups similar traffic patterns together.",
                                "The analyzer looks"
                        ),
                )
        ),


)