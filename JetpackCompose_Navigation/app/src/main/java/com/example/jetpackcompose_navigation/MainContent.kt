package com.example.jetpackcompose_navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun MainContent() {
    val navController = rememberNavController()
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = "home",
        ) {
            composable("home") {
                Home(navController)
            }
//            composable("work/{name}",
            composable("work?name={name}",
                arguments = listOf(navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "There is no name >-<"//nullable = true
                })
            ) {
                val name = it.arguments?.getString("name")
                Work(name = name)
            }
        }
    }
}

@Preview
@Composable
fun MainPreview() {
    MainContent()
}