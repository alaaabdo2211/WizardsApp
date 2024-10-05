package com.app.wizardsapp.ui.theme

import android.graphics.Paint.Align
import android.widget.Toast
import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.wizardsapp.data.remote.WizardsByIdModel
import com.app.wizardsapp.ui.theme.base.MainViewModel
import com.app.wizardsapp.utils.ApiState

@Composable

fun WizardDetails(navController: NavController, wizardId: String, wizardName: String) {
    val viewModel = hiltViewModel<MainViewModel>()
    val state by viewModel.wizardsByIdState.collectAsState()
    val wizardById = viewModel.wizardsByIdState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.fetchWizardById(wizardId)

    }

    when (wizardById) {
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
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = wizardName,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 20.sp,
                    color = Color.Black,
                )
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = "List of Elixirs: ",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 15.sp,
                    color = Color.Black,
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {

                    items(wizardById.data.elixirs) { item ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            shape = RoundedCornerShape(12.dp),

                            ) {

                            Row(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable {
                                        navController.navigate(Screens.Elixirs.name + "/${item.id}" + "/${item.name}")

                                    }, verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    modifier = Modifier.weight(3f),
                                    text = item.name,
                                    textAlign = TextAlign.Center,
                                    fontFamily = FontFamily.SansSerif,
                                    color = Color.Black,

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
                    text = wizardById.message,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 18.sp,
                    color = Color.Black,
                )

                Card(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(10.dp)
                        .wrapContentSize(),
                    shape = RoundedCornerShape(8.dp),

                    ) {

                    Button(modifier = Modifier
                        .wrapContentSize()
                        .background(Color.White)
                        .align(Alignment.CenterHorizontally),
                        onClick = {
                            viewModel.fetchWizardById(wizardId)
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