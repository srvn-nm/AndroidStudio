package com.example.jetpackcompose_navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class Home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                    Greeting2()
            }
        }
    }

data class Item(val id: Int)


@Composable
fun ItemCard(name: String) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
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
//            Image(
//                painter = painterResource(id = R.drawable.download),
//                contentDescription = "",
//                modifier = Modifier
//                    .size(120.dp)
//                    .clip(CircleShape),
//                contentScale = ContentScale.Crop
//            )
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


@Composable
fun ItemList(works: List<Item>) {
    LazyColumn {
        //adding multiple to the list. by using item we can add a single item
        items((works)) { work ->
            ItemCard("Item ${work.id}")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting2() {
    var textInput by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = textInput,
            onValueChange ={
                textInput = it
            },
            label = {
                Text("Number of Works")
            })
        val item = Item(1)
        val items = remember {
            mutableStateListOf(item)
        }
        Box(modifier = Modifier.fillMaxSize()) {
            ItemList(works = items)
            for (i in 2..textInput.toInt()){
                items.add((Item(i)))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
        Greeting2()
}