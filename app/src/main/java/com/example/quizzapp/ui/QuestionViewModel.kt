package com.example.quizzapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizzapp.data.Constants.MAX_QUESTIONS
import com.example.quizzapp.data.Constants.NO_ANSWER_SELECTED
import com.example.quizzapp.data.Question
import com.example.quizzapp.data.QuizResult
import com.example.quizzapp.domain.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(private val questionRepository: QuestionRepository) :
    ViewModel() {

    var activeQuestion: MutableLiveData<Question?> = MutableLiveData(null)
    var isLastQuestion: MutableLiveData<Boolean> = MutableLiveData(false)
    var isFirstQuestion: MutableLiveData<Boolean> = MutableLiveData(true)
    val selectedAnswer: MutableLiveData<Int> = MutableLiveData(NO_ANSWER_SELECTED)

    private var activeQuestionNumber: Int = 0
    private var quizState: Array<Int> = Array(MAX_QUESTIONS) { NO_ANSWER_SELECTED }

    fun getNextQuestion() {
        isFirstQuestion.value = activeQuestionNumber == 0

        if (activeQuestionNumber < MAX_QUESTIONS) {
            activeQuestion.value = questionRepository.getQuestion(activeQuestionNumber)
            activeQuestionNumber++
        }

        loadSelectedAnswerForQuestion()
        isLastQuestion.value = activeQuestionNumber == MAX_QUESTIONS
    }

    fun getPrevQuestion() {
        activeQuestionNumber -= 2
        if (activeQuestionNumber < 0) {
            activeQuestionNumber = 0
        }
        getNextQuestion()
    }

    fun updateQuizState(selectedIndx: Int) {
        quizState[activeQuestionNumber - 1] = selectedIndx
    }

    private fun loadSelectedAnswerForQuestion() {
        selectedAnswer.value = quizState[activeQuestionNumber - 1]
    }

    fun createQuizResult(): QuizResult {
        return QuizResult(10, 10)
    }
}