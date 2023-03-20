package com.waroudi.tapalibrary.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.waroudi.tapalibrary.ui.base.BaseViewModel

class MainViewModel : BaseViewModel() {
    private val _progressCounter = MutableLiveData(0)
    val progressCounter: LiveData<Int> = _progressCounter

    fun updateProgress(add: Boolean) {
        _progressCounter.value?.let { oldValue ->
            _progressCounter.value = if (add) oldValue + 1 else (oldValue - 1).coerceAtLeast(0)
        }
    }
}