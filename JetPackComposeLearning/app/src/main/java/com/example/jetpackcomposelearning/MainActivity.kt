package com.example.jetpackcomposelearning

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            UserList()
//            JetPackComposeLearningTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
////                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    Greeting("Kitten")
//                }
//            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val context = LocalContext.current
//    match parent setting
//    Surface(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
    Text(text = "Hello $name ^.^",
        fontSize = 20.sp,
        color = colorResource(id = R.color.purple_700),
        fontFamily = FontFamily.Cursive,
        modifier = Modifier.clickable {
            Toast.makeText(
                context, "Meow! You just click on the sleeping cat ^>.<^", Toast.LENGTH_LONG
            ).show()
        })
//    }
}

@Composable
fun UserCard(name: String) {
    Card(
        elevation = 4.dp, modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        //horizontally
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(12.dp)
                .border(width = 1.dp, color = Color.Gray)
                .padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.download),
                contentDescription = "",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            //vertically
            Column {
                Greeting(name = name)
                Button(onClick = {
                    //action
                }) {
                    Text(text = "View Profile")
                }
            }
        }
    }
}


data class User(val id: Int)

////changing the list in runtime
//val users = mutableListOf(
//    User(1), User(2), User(3), User(4), User(5), User(6), User(7), User(8), User(9), User(10)
//)

@Composable
fun UserList(users: List<User>) {
    //to display a list that is scrollable vertically
//    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
//        for (i in 1..10) {
//            UserCard("Kitten number $i")
//        }
//    }
    val name = remember {
        mutableStateOf("Sarvin")
    }
    //having recycler view model in the lazy column
    LazyColumn {
        //adding multiple to the list. by using item we can add a single item
        items((users)) { user ->
            UserCard("Kitten number ${user.id}")
//            UserList()
        }
    }
}

@Composable
fun MainContent(){

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
//    JetPackComposeLearningTheme {
    //Greeting("Kitten")
//    UserList()
//    }
}