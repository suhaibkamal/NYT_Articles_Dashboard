package com.sk.nytarticlesdashboard.flow.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.sk.movieshare.base.BaseViewModel
import com.sk.nytarticlesdashboard.flow.auth.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class SplashViewModel @Inject constructor(
    private val repository: SplashRepository
): BaseViewModel()  {
    var pathState by mutableStateOf("")
    init {
        viewModelScope.launch {
            delay(2000)
            if(repository.isUserLoggedIn()){
                pathState = "Home"
            }else{
                pathState = "Login"
            }
        }

    }
}