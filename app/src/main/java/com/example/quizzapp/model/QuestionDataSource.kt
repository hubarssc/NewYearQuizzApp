package com.example.quizzapp.model

import com.example.quizzapp.data.Category
import com.example.quizzapp.data.Question

object QuestionDataSource { //TODO: Add Room database
    val questions = arrayListOf<Question>(
        Question(
            "Who was the first man in space? 1",
            "Yuri Gagarin",
            "Neil Armstrong",
            "Vitaly Arisov",
            "Viktor Gagarin",
            0,
            0
        ),
        Question(
            "What is DI library from list below 2",
            "Retrofit",
            "Room",
            "Hilt",
            "Glide",
            2,
        2
        ),
        Question(
            "What langs are used for Android development? 3",
            "C++",
            "Kotlin",
            "Java",
            "All above",
            3,
        2
        ),
        Question(
            "Who was the first man in space? 4",
            "Yuri Gagarin",
            "Neil Armstrong",
            "Vitaly Arisov",
            "Viktor Gagarin",
            0,
            2
        ),
        Question(
            "What is DI library from list below 5",
            "Retrofit",
            "Room",
            "Hilt",
            "Glide",
            2,
            0
        ),
        Question(
            "What langs are used for Android development? 6",
            "C++",
            "Kotlin",
            "Java",
            "All above",
            3,
            0
        ),
        Question(
            "Who was the first man in space? 7",
            "Yuri Gagarin",
            "Neil Armstrong",
            "Vitaly Arisov",
            "Viktor Gagarin",
            0,
            1
        ),
        Question(
            "What is DI library from list below 8",
            "Retrofit",
            "Room",
            "Hilt",
            "Glide",
            2,
            1
        ),
        Question(
            "What langs are used for Android development? 9",
            "C++",
            "Kotlin",
            "Java",
            "All above",
            3,
            1
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