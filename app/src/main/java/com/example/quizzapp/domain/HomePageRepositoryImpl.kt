package com.example.quizzapp.domain

import com.example.quizzapp.data.Category

class HomePageRepositoryImpl(
    private val categoriesSource: ArrayList<Category>,
) : HomePageRepository {

    override fun getCategories(): ArrayList<Category> {
        return categoriesSource
    }
}