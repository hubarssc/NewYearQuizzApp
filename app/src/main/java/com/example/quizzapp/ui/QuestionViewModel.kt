package com.example.quizzapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizzapp.data.Category
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

    private var questionsForSession:  MutableLiveData<ArrayList<Question>> = MutableLiveData<ArrayList<Question>>()

    private var activeQuestionNumber: Int = 0
    private var quizState: Array<Int> = Array(MAX_QUESTIONS) { NO_ANSWER_SELECTED }

    fun startQuizSession(
        category: Category = Category(
            id = 0,
            categoryTitle = "Space"
        )
    ) {
        questionsForSession.value = ArrayList(questionRepository.getQuestionsForQuizSession(category))
        getNextQuestion()
    }

    fun getNextQuestion() {
        isFirstQuestion.value = activeQuestionNumber == 0

        if (activeQuestionNumber < MAX_QUESTIONS) {
            activeQuestion.value = questionsForSession.value?.get(activeQuestionNumber)
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
        return QuizResult(calculateCorrectAnswers(), MAX_QUESTIONS)
    }

    private fun calculateCorrectAnswers(): Int {
        var correctAnswersAmount = 0

        questionsForSession.value?.let { questions ->
            for (questionId in questions.indices) {
                if (questions[questionId].correctAnswer == quizState[questionId]) {
                    correctAnswersAmount++
                }
            }
        }
        return correctAnswersAmount
    }
}