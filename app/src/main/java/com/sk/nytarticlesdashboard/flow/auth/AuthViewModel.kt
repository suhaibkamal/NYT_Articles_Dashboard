package com.sk.nytarticlesdashboard.flow.auth

import android.text.TextUtils
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.sk.movieshare.base.BaseViewModel
import com.sk.nytarticlesdashboard.models.model.UserModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
@HiltViewModel
class AuthViewModel@Inject constructor(
    private val repository: AuthRepository):BaseViewModel() {


    var loginEmailState by mutableStateOf("")
    var loginEmailStateError by mutableStateOf(false)
    var loginPasswordStateError by mutableStateOf(false)
    var loginPasswordState by mutableStateOf("")
    var registerFullNameState by mutableStateOf("")
    var registerFullNameStateError by mutableStateOf(false)
    var registerEmailState by mutableStateOf("")
    var registerEmailStateError by mutableStateOf(false)
    var registerPhoneState by mutableStateOf("")
    var registerPhoneStateError by mutableStateOf(false)
    var registerNationalIDState by mutableStateOf("")
    var registerNationalIDStateError by mutableStateOf(false)
    var registerDateOfBirthState by mutableStateOf("Date Of Birth")
    var registerDateOfBirthStateError by mutableStateOf(false)
    var registerPasswordState by mutableStateOf("")
    var registerPasswordStateError by mutableStateOf(false)
    var authState by mutableStateOf(false)


        fun registerUser(){
            viewModelScope.launch {

                if(validateRegistration()){
                    val userModel=UserModel(
                    registerDateOfBirthState,
                    registerEmailState,
                    registerFullNameState,
                    registerNationalIDState,
                    registerPhoneState,
                    registerPasswordState)
                    repository.registerUser(userModel = userModel)

                    registerDateOfBirthState = "Date of Birth"
                    registerEmailState = ""
                    registerFullNameState = ""
                    registerNationalIDState = ""
                    registerPhoneState = ""
                    registerPasswordState = ""

                }else{
                   handleError(Throwable("error"))
                }



            }
        }

    fun login(){
        viewModelScope.launch {
            if(validateLogin()){
                withContext(Dispatchers.IO) {
                    val userModel: UserModel = repository.getUserById(loginEmailState)
                    if (userModel.password.equals(loginPasswordState)) {
                        authState = true
                        userModel.id?.let { repository.saveUserId(it) }
                    } else {

                    }
                }
            }else{

            }
        }
    }

    private fun validateLogin(): Boolean {
        var isValid = true
        if(TextUtils.isEmpty(loginEmailState)){
            loginEmailStateError = true;
            isValid = false
        }
        if(TextUtils.isEmpty(loginPasswordState)){
            loginPasswordStateError = true;
            isValid = false
        }

        return isValid
    }

    private fun validateRegistration(): Boolean {
        var isValid = true
        if(TextUtils.isEmpty(registerFullNameState)){
            registerFullNameStateError = true;
            isValid = false
        }
        if(TextUtils.isEmpty(registerEmailState)){
            registerEmailStateError = true;
            isValid = false
        }
        if(TextUtils.isEmpty(registerNationalIDState)){
            registerNationalIDStateError = true;
            isValid = false
        }

        if(TextUtils.isEmpty(registerPhoneState)){
            registerPhoneStateError = true;
            isValid = false
        }
        if(registerDateOfBirthState.equals("Date Of Birth")){
            registerDateOfBirthStateError = true;
            isValid = false
        }

        if(TextUtils.isEmpty(registerPasswordState)){
            registerPasswordStateError = true;
            isValid = false
        }
        return isValid
    }

}