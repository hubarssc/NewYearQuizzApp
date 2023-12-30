package com.example.quizzapp.di

import com.example.quizzapp.data.Category
import com.example.quizzapp.data.Question
import com.example.quizzapp.domain.HomePageRepository
import com.example.quizzapp.domain.HomePageRepositoryImpl
import com.example.quizzapp.domain.QuestionRepository
import com.example.quizzapp.domain.QuestionRepositoryImpl
import com.example.quizzapp.model.QuestionDataSource.categories
import com.example.quizzapp.model.QuestionDataSource.questions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideDataSource(): ArrayList<Question> {
        return questions
    }

    @Provides
    fun provideCategories(): ArrayList<Category> {
        return categories
    }

    @Provides
    fun provideQuestionRepository(
        dataSource: ArrayList<Question>,
    ): QuestionRepository {
        return QuestionRepositoryImpl(dataSource)
    }

    @Provides
    fun provideHomePageRepository(
        categories: ArrayList<Category>
    ): HomePageRepository {
        return HomePageRepositoryImpl(categories)
    }
}