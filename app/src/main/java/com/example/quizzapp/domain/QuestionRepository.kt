package com.example.quizzapp.domain

import com.example.quizzapp.data.Question

interface QuestionRepository {
    fun getQuestion(position: Int): Question
}