package com.example.quizzapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizzapp.data.Constants.MAX_QUESTIONS
import com.example.quizzapp.data.Question
import com.example.quizzapp.domain.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(private val questionRepository: QuestionRepository) :
    ViewModel() {

    var activeQuestion: MutableLiveData<Question?> = MutableLiveData(null)
    var isLastQuestion: MutableLiveData<Boolean> = MutableLiveData(false)
    var isFirstQuestion: MutableLiveData<Boolean> = MutableLiveData(true)
    private var activeQuestionNumber: Int = 0

    fun getNextQuestion() {
        isFirstQuestion.value = activeQuestionNumber == 0

        if (activeQuestionNumber < MAX_QUESTIONS) {
            activeQuestion.value = questionRepository.getQuestion(activeQuestionNumber)
            activeQuestionNumber++
        }

        isLastQuestion.value = activeQuestionNumber == MAX_QUESTIONS
    }

    fun getPrevQuestion() {
        activeQuestionNumber -= 2
        if (activeQuestionNumber < 0) {
            activeQuestionNumber = 0
        }
        getNextQuestion()
    }
}