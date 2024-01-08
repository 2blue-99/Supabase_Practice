package com.example.supabasepractice.appui

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.supabasepractice.MainViewModel
import com.example.supabasepractice.screen.LoginScreen
import com.example.supabasepractice.screen.MainScreen
import com.example.supabasepractice.screen.navigateLoginScreen
import com.example.supabasepractice.screen.navigateMainScreen
import com.example.supabasepractice.state.ScreenState

@Composable
fun SupaUi(
    viewModel: MainViewModel = viewModel()
){
    val loginState = viewModel.loginState.observeAsState()
    Log.e("TAG", "SupaUi: $loginState", )
    val navController = rememberNavController()

    Scaffold { padding ->
        NavHost(
            modifier = Modifier.padding(padding),
            navController = navController,
            startDestination = ScreenState.Login.name,
        ){
            composable(route = ScreenState.Login.name){
                LoginScreen(){
                    navController.navigateMainScreen()
                }
            }

            composable(route = ScreenState.Main.name){
                MainScreen{
                    navController.navigateLoginScreen()
                }
            }
        }
    }

}