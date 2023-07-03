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
    var id1:Int = 0
    lateinit  var title1:String
    lateinit var body1:String
    var userId1 :Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer {response ->
            val userId2 = response.userId
            val id2 = response.id
            val title2 = response.title
            val body2 = response.body
            Log.d("Response userId", userId2.toString())
            Log.d("Response id", id2.toString())
            Log.d("Response title", title2)
            Log.d("Response body", body2)
            userId1 = userId2
            id1 = id2
            title1 = title2
            body1 = body2


        })

        setContent {
            RetrofitLearningTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(userId = userId1, id = id1, title = title1, body = body1)
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