package com.example.quizzapp.data

data class Question(
    val questionText: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val correctAnswer: Int,
    val categotyId: Int
)

data class Category(
    val id: Int,
    val categoryTitle: String,
)