package com.example.quizzapp.domain

import com.example.quizzapp.data.Category
import com.example.quizzapp.model.QuestionDataSource

interface HomePageRepository {
    fun getCategories(): ArrayList<Category>
}

