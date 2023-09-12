package com.example.quizzapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizResult(
    val correctAnswers: Int,
    val allAnswers: Int
) : Parcelable