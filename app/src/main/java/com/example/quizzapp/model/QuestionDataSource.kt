package com.example.quizzapp.model

import com.example.quizzapp.data.Category
import com.example.quizzapp.data.Question

object QuestionDataSource {
    val questions = arrayListOf<Question>(
        Question(
            "Who was the first man in space?",
            "Yuri Gagarin",
            "Neil Armstrong",
            "Vitaly Arisov",
            "Viktor Gagarin",
            1,
            0
        ),
        Question(
            "What is DI library from list below",
            "Retrofit",
            "Room",
            "Hilt",
            "Glide",
            3,
        2
        ),
        Question(
            "What langs are used for Android development?",
            "C++",
            "Kotlin",
            "Java",
            "All above",
            4,
        2
        )
    )

    val categories = arrayListOf<Category>(
        Category(
            id = 0,
            categoryTitle = "Space"
        ),
        Category(
            id = 1,
            categoryTitle = "Nature"
        ),
        Category(
            id = 2,
            categoryTitle = "IT"
        )
    )
}