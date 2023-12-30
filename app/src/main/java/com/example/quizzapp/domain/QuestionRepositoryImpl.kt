package com.example.quizzapp.domain

import com.example.quizzapp.data.Category
import com.example.quizzapp.data.Constants.MAX_QUESTIONS
import com.example.quizzapp.data.Question

class QuestionRepositoryImpl(
    private val dataSource: List<Question>,
) : QuestionRepository {

    override fun getQuestionsForQuizSession(category: Category): List<Question> {
        return dataSource.filter { it.categoryId == category.id }.take(MAX_QUESTIONS)
    }

}