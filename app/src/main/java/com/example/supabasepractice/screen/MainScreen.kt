package com.example.supabasepractice.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.supabasepractice.MainViewModel
import com.example.supabasepractice.state.ScreenState

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = MainViewModel(),
    navigation: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { viewModel.read() }) {
            Text(text = "Read")
        }
        Button(onClick = { viewModel.insert() }) {
            Text(text = "Insert")
        }
        Button(onClick = { viewModel.delete() }) {
            Text(text = "Delete")
        }
        Button(onClick = { navigation() }) {
            Text(text = "Logout")
        }
    }
}

fun NavController.navigateMainScreen(){
    this.navigate(ScreenState.Main.name)
}