package com.sk.movieshare.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel  @Inject constructor(): ViewModel(){
    var showLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()


    var handleErrorLiveData by mutableStateOf(Throwable())

    override fun onCleared() {
        super.onCleared()
    }

    fun showLoading() {
        showLoadingLiveData.value = true
    }

    fun hideLoading() {
        showLoadingLiveData.value = false
    }

    fun handleError(throwable: Throwable) {
        handleErrorLiveData = throwable
    }


}