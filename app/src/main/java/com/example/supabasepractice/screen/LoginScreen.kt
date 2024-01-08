package com.example.supabasepractice.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.supabasepractice.MainViewModel
import com.example.supabasepractice.state.ScreenState
import io.github.jan.supabase.compose.auth.composable.rememberSignInWithGoogle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(
    viewModel: MainViewModel = viewModel(),
    navigation: () -> Unit
){
    val context = LocalContext.current

    viewModel.loginState.value.let {
        if(it==ScreenState.Main)
            navigation()
    }

    val authState = viewModel.getComposeAuth().rememberSignInWithGoogle(
        onResult = {result ->
            viewModel.checkGoogleLoginStatus(context, result)
        },
        fallback = {}
    )



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            authState.startFlow()
//            navigation()
        }) {
            Text(text = "Login")
        }
        Button(onClick = {
            viewModel.logout()
//            navigation()
        }) {
            Text(text = "Logout")
        }
        Button(onClick = {
            viewModel.read()
//            navigation()
        }) {
            Text(text = "Get")
        }
    }
}

fun NavController.navigateLoginScreen(){
    this.navigate(ScreenState.Login.name)
}