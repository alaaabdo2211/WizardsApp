package com.app.wizardsapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.wizardsapp.ui.theme.base.MainViewModel
import com.app.wizardsapp.utils.ApiState

@Composable

fun ElixirsScreen(navController: NavController, elixirsId: String, elixirsName: String) {
    val viewModel = hiltViewModel<MainViewModel>()
    val state by viewModel.elixirsByIdState.collectAsState()
    val elixirsById = viewModel.elixirsByIdState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.fetchElixirsById(elixirsId)

    }

    when (elixirsById) {
        is ApiState.Loading -> {
            CircularProgressIndicator()
        }

        is ApiState.Success -> {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(5.dp)

                ) {
                    Text(
                        text = "* " +
                                "Elixir Name: ",
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 18.sp,
                        color = Color.Black,
                    )

                    Text(
                        text = elixirsName,
                        color = Color.Gray,
                        fontSize = 18.sp,
                        fontFamily = FontFamily.SansSerif,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(5.dp)

                ) {
                    Text(
                        text = "* Elixir Effect : ",
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 18.sp,
                        color = Color.Black,
                    )


                    Text(
                        text = elixirsById.data.effect,
                        color = Color.Gray,
                        fontSize = 18.sp,
                        fontFamily = FontFamily.SansSerif,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(5.dp)
                ) {
                    Text(
                        text = "* Elixir difficulty: ",
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 18.sp,
                        color = Color.Black,
                    )


                    Text(
                        text = elixirsById.data.difficulty,
                        color = Color.Gray,
                        fontSize = 18.sp,
                        fontFamily = FontFamily.SansSerif,
                    )
                }



                Text(
                    modifier = Modifier.padding(10.dp),
                    text = " List of Ingredients: ",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 15.sp,
                    color = Color.Black,
                )


                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {

                    items(elixirsById.data.ingredients) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            shape = RoundedCornerShape(5.dp),

                            ) {

                            Row(
                                modifier = Modifier.padding(10.dp)
                            ) {
                                Text(
                                    modifier = Modifier.weight(3f),
                                    text = item.name,
                                    color = Color.Black,
                                    textAlign = TextAlign.Center,
                                    fontFamily = FontFamily.SansSerif,

                                    )
                            }


                        }


                    }


                }
            }
        }


        is ApiState.Error -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(10.dp)
            ) {
                Text(
                    text = elixirsById.message,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 18.sp,
                    color = Color.Black,
                )

                Card(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .wrapContentSize()
                        .padding(10.dp),
                    shape = RoundedCornerShape(8.dp),

                    ) {

                    Button(modifier = Modifier
                        .wrapContentSize()
                        .background(Color.White)
                        .align(Alignment.CenterHorizontally),
                        onClick = {
                            viewModel.fetchElixirsById(elixirsId)
                        }) {
                        Text(
                            text = "Refresh!",
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 18.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }

            }

        }
    }
}