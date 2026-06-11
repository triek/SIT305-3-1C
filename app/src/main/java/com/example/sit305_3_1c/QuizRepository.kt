package com.example.sit305_3_1c

object QuizRepository {
    val questions = listOf(
        QuizQuestion(
            title = "Android fundamentals",
            questionText = "Which file declares the activities that belong to an Android application?",
            answerOptions = listOf("AndroidManifest.xml", "strings.xml", "build.gradle.kts"),
            correctAnswerIndex = 0
        ),
        QuizQuestion(
            title = "Activity lifecycle",
            questionText = "Which lifecycle method is normally used to connect an Activity to its layout?",
            answerOptions = listOf("onPause()", "onCreate()", "onDestroy()"),
            correctAnswerIndex = 1
        ),
        QuizQuestion(
            title = "Views and layouts",
            questionText = "Which view is commonly used to let a user type their name?",
            answerOptions = listOf("TextView", "ProgressBar", "EditText"),
            correctAnswerIndex = 2
        ),
        QuizQuestion(
            title = "User interaction",
            questionText = "What is the purpose of setting an OnClickListener on a Button?",
            answerOptions = listOf("To handle taps", "To change the app icon", "To install the app"),
            correctAnswerIndex = 0
        ),
        QuizQuestion(
            title = "Quiz progress",
            questionText = "Which widget best communicates how far through the quiz the user is?",
            answerOptions = listOf("ImageView", "ProgressBar", "Toast"),
            correctAnswerIndex = 1
        )
    )
}
