package com.example.quizzapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizzapp.domain.HomePageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val categoriesRepo: HomePageRepository
) : ViewModel() {
    val categories: MutableLiveData<ArrayList<String>> = MutableLiveData(arrayListOf())

    fun loadCategories() {
        viewModelScope.launch {
            delay(2_000)
            val data = categoriesRepo.getCategories()
            val mappedData = ArrayList(data.map { it.categoryTitle })
            categories.postValue(mappedData)
        }
    }
}

