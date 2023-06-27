package com.example.jetpackcomposelearning

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.jetpackcomposelearning.ui.theme.JetPackComposeLearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackComposeLearningTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Kitten")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val context = LocalContext.current
//    match parent setting
//    Surface(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
        Text(text = "Hello $name!", fontSize = 32.sp, color = colorResource(id = R.color.purple_700), fontFamily = FontFamily.Cursive, modifier = Modifier.clickable { Toast.makeText(context, "Meow! You just click on the sleeping cat ^>.<^", Toast.LENGTH_LONG ).show() })
//    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetPackComposeLearningTheme {
        Greeting("Kitten")
    }
}