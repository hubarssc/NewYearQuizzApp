package com.example.quizzapp.domain

import com.example.quizzapp.data.Category
import com.example.quizzapp.data.Question

interface QuestionRepository {
    fun getQuestionsForQuizSession(category: Category): List<Question>
}