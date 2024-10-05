package com.app.wizardsapp.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
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
                Text(
                    text = elixirsName,
                    color = Color.Black,
                )

                Text(
                    text = elixirsById.data.effect,
                    color = Color.Black,

                    )
                Text(
                    text = elixirsById.data.difficulty,
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
                                    text = item.name,
                                    color = Color.Black,

                                    )
                            }


                        }


                    }


                }
            }
        }


        is ApiState.Error -> Text(text = elixirsById.message)
    }
}