package furhatos.app.quiz.flow.main
// Add these imports at the top of the file
import furhatos.app.quiz.setting.GameMetrics
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// Add this function to handle saving results
fun saveResultsToFile(participantId: String, gameMetrics: GameMetrics, score: Int) {
    // Create Participants directory if it doesn't exist
    val participantsDir = File("Participants")
    if (!participantsDir.exists()) {
        participantsDir.mkdir()
    }

    // Create file with timestamp
    val timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"))
    val resultsFile = File(participantsDir, "Participant-${participantId}_$timestamp.txt")

    resultsFile.printWriter().use { writer ->
        writer.println("=== PARTICIPANT $participantId RESULTS ===")
        writer.println("Date: $timestamp")
        writer.println("Total Game Time: ${(System.currentTimeMillis() - gameMetrics.gameStartTime) / 1000.0} seconds")
        writer.println("Final Score: $score")
        writer.println()

        // Print results for each round
        gameMetrics.roundMetrics.forEach { (roundIndex, roundMetrics) ->
            writer.println("ROUND ${roundIndex + 1}")
            writer.println("Round Time: ${(roundMetrics.roundEndTime - roundMetrics.roundStartTime) / 1000.0} seconds")
            writer.println("Correct Answers: ${roundMetrics.correctAnswers}/${roundMetrics.totalQuestions}")
            println("ROUND ${roundIndex + 1}")
            println("Round Time: ${(roundMetrics.roundEndTime - roundMetrics.roundStartTime) / 1000.0} seconds")
            println("Correct Answers: ${roundMetrics.correctAnswers}/${roundMetrics.totalQuestions}")

            // Print details for each question in the round
            roundMetrics.questionMetrics.forEach { (questionIndex, questionMetrics) ->
                writer.println("\nQuestion $questionIndex:")
                writer.println("  Time Taken: ${questionMetrics.timeTaken} seconds")
                writer.println("  Correct: ${questionMetrics.isCorrect}")
                writer.println("  Attempts: ${questionMetrics.attempts}")
                writer.println("  Hints Used: ${questionMetrics.hintsUsed}")
                writer.println("  Repeat Requests: ${questionMetrics.repeatRequests}")
                println("\nQuestion $questionIndex:")
                println("  Time Taken: ${questionMetrics.timeTaken} seconds")
                println("  Correct: ${questionMetrics.isCorrect}")
                println("  Attempts: ${questionMetrics.attempts}")
                println("  Hints Used: ${questionMetrics.hintsUsed}")
                println("  Repeat Requests: ${questionMetrics.repeatRequests}")
            }
            writer.println()
            println()
        }

        // Calculate and write overall statistics
        val totalQuestions = gameMetrics.roundMetrics.values.sumOf { it.questionMetrics.size }
        val totalCorrect = gameMetrics.roundMetrics.values.sumOf { round ->
            round.questionMetrics.count { it.value.isCorrect }
        }
        val totalHints = gameMetrics.roundMetrics.values.sumOf { round ->
            round.questionMetrics.values.sumOf { it.hintsUsed }
        }
        val averageTimePerQuestion = gameMetrics.roundMetrics.values.flatMap {
            it.questionMetrics.values.map { it.timeTaken }
        }.average()

        writer.println("=== OVERALL STATISTICS ===")
        writer.println("Total Questions Answered: $totalQuestions")
        writer.println("Total Correct Answers: $totalCorrect")
        writer.println("Success Rate: ${(totalCorrect.toDouble() / totalQuestions * 100).toInt()}%")
        writer.println("Total Hints Used: $totalHints")
        writer.println("Average Time per Question: ${String.format("%.1f", averageTimePerQuestion)} seconds")

        println("\n=== OVERALL STATISTICS ===")
        println("Total Questions Answered: $totalQuestions")
        println("Total Correct Answers: $totalCorrect")
        println("Success Rate: ${(totalCorrect.toDouble() / totalQuestions * 100).toInt()}%")
        println("Total Hints Used: $totalHints")
        println("Average Time per Question: ${String.format("%.1f", averageTimePerQuestion)} seconds")
    }
}