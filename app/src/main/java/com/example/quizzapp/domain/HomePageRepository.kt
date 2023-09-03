package com.example.quizzapp.domain

import com.example.quizzapp.data.Category

interface HomePageRepository {
    fun getCategories(): ArrayList<Category>
}
