package com.example.quizzapp.model

import com.example.quizzapp.data.Question

object QuestionDataSource {
    val questions = arrayListOf<Question>(
        Question(
            "What does the fox say?",
            "1",
            "2",
            "3",
            "4",
            1
        ),
        Question(
            "Where does penguins live?",
            "1",
            "2",
            "3",
            "4",
            2
        ),
        Question(
            "Why do asking this?",
            "1",
            "2",
            "3",
            "4",
            4
        )
    )
}