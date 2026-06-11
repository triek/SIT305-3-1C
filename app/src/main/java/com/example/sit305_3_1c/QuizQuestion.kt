package com.example.sit305_3_1c

data class QuizQuestion(
    val title: String,
    val questionText: String,
    val answerOptions: List<String>,
    val correctAnswerIndex: Int
)
