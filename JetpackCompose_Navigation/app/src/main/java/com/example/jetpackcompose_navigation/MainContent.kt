package com.example.jetpackcompose_navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun MainContent() {
    val navContoler = rememberNavController(navigators = )
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController =,
            startDestination =,
            builder =
        )
    }
}

@Preview
@Composable
fun MainPreview() {
    MainContent()
}