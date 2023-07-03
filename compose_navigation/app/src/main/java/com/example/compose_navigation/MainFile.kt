package com.example.compose_navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen(navController)
        }
        
        composable(NavigationItem.Details.route){
            val name = it.arguments?.getString("name")
            DetailsScreen(name = name)
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {

    val dummyValue = remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = dummyValue.value, onValueChange = {
                dummyValue.value = it
            })
            
            Button(onClick = {
                navController.navigate("details/${dummyValue.value}")
            }) {
                Text(text = "Meow!")
            }

        }

    }

}

@Composable
fun DetailsScreen(name:String?){
    Text(text = name.toString())
}
