package com.example.a04_02_2026.model

data class QuizQuestion(
    val question: String,
    val variants: List<String>,
    val correctIndex: Int,
    val topic: String
)
