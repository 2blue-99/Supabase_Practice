package com.example.supabasepractice

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supabasepractice.database.SupabaseHelper
import com.example.supabasepractice.state.ScreenState
import io.github.jan.supabase.compose.auth.composable.NativeSignInResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    var _loginState = MutableLiveData<ScreenState>()
    val loginState: LiveData<ScreenState> get() = _loginState
    private val supabase = SupabaseHelper

    fun getComposeAuth() = supabase.getComposeAuth()

    fun checkGoogleLoginStatus(context: Context, result: NativeSignInResult){
        when(result){
            is NativeSignInResult.Success -> {
                Log.e("TAG", "checkGoogleLoginStatue: Success $context", )
                Log.e("TAG", "checkGoogleLoginStatue: Success $result", )
                _loginState.postValue(ScreenState.Main)
                Log.e("TAG", "checkGoogleLoginStatus: ${supabase.getToken()}", )
            }
            is NativeSignInResult.ClosedByUser -> {
                Log.e("TAG", "checkGoogleLoginStatue: ClosedByUser", )
            }
            is NativeSignInResult.Error -> {
                Log.e("TAG", "checkGoogleLoginStatue: Error ${result.message}")
            }
            is NativeSignInResult.NetworkError -> {
                Log.e("TAG", "checkGoogleLoginStatue: NetworkError", )
            }
        }
    }

    fun getToken(){
        Log.e("TAG", "getToken: $supabase.getToken()", )
    }


    fun read(){
        viewModelScope.launch{
            supabase.read()
        }
    }
    fun insert(){
        CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
            supabase.create()
        }
    }
    fun delete(){
        CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
            supabase.delete()
        }
    }
//    fun login(){
//        CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
//            supabase.login()
//        }
//    }
    fun logout(){
        CoroutineScope(Dispatchers.IO + SupervisorJob()).launch {
            supabase.logout()
        }
    }
}