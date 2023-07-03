package com.example.retrofitlearning

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitlearning.repository.Repository
import com.example.retrofitlearning.ui.theme.RetrofitLearningTheme
import kotlin.properties.Delegates

class MainActivity : ComponentActivity() {

    private lateinit var  viewModel: MainViewModel
    var id:Int = 0
    lateinit  var title:String
    lateinit var body:String
    var userId :Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer {response ->
            Log.d("Response userId", response.userId.toString())
            Log.d("Response id", response.id.toString())
            Log.d("Response title", response.title)
            Log.d("Response body", response.body)
            userId = response.userId
            id = response.id
            title = response.title
            body = response.body


        })

        setContent {
            RetrofitLearningTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(userId = userId, id = id, title = title, body = body)
                }
            }
        }

    }
}

@Composable
fun Greeting(userId:Int, id:Int, title:String, body:String, modifier: Modifier = Modifier) {
    Text(
        text = "UserId: $userId\nId: $id\nTitle: $title\nBody: $body",
        modifier = modifier
    )
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    RetrofitLearningTheme {
//        Greeting(userId = response.userId, id = response.id, title = response.title, body = response.body)
//    }
//}