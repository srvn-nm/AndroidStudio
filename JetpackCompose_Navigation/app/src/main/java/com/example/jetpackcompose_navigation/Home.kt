package com.example.jetpackcompose_navigation

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun Home(navController: NavHostController? = null) {
    Greeting2(navController)
}

data class Item(val id: Int)


@Composable
fun ItemCard(name: String, navController: NavHostController? = null) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
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
            //vertically
            Column {
                Greeting(name = name)
                Button(onClick = {
                    navController?.navigate("work?name=$name")
                }) {
                    Text(text = "View Profile")
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String) {
    val context = LocalContext.current
    Text(text = "$name ^.^",
        fontSize = 20.sp,
        color = colorResource(id = R.color.purple_700),
        fontFamily = FontFamily.Cursive,
        modifier = Modifier.clickable {
            Toast.makeText(
                context, "Meow! You just click on the ${name}!", Toast.LENGTH_LONG
            ).show()
        })
}


@Composable
fun ItemList(works: List<Item>, navController: NavHostController? = null) {
    LazyColumn {
        //adding multiple to the list. by using item we can add a single item
        items((works)) { work ->
            ItemCard("Item ${work.id}", navController)
        }
    }
}

@Composable
fun Greeting2(navController: NavHostController? = null) {
    val limitation = 3
    val context = LocalContext.current
    var textInput by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            keyboardActions = KeyboardActions(
                onSearch = {

                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Decimal

            ),
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                }
            },
            value = textInput,
            onValueChange = {
                if (it.length > limitation) {
                    Toast.makeText(
                        context,
                        "Meow! You just cross the limit of the numbers!",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    textInput = it
                }
            },
            label = {
                Text("Number of Works")
            }
        )
        val item = Item(1)
        val works = remember {
            mutableStateListOf(item)
        }
        var limit = 1
        if (textInput.isNotBlank()) {
            limit = textInput.toInt()
        }
        Box(modifier = Modifier.fillMaxSize()) {
            ItemList(works = works, navController)
            for (i in 2..limit) {
                works.add((Item(i)))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Greeting2()
}
