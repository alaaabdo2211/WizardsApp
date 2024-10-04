package com.app.wizardsapp.ui.theme

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.wizardsapp.data.remote.WizardsModel
import com.app.wizardsapp.ui.theme.base.MainViewModel
import com.app.wizardsapp.utils.ApiState

@Composable
fun WizardsScreen(navController: NavController) {
    val context = LocalContext.current

    val viewModel = hiltViewModel<MainViewModel>()
    val state by viewModel.wizardsState.collectAsState()
    val wizardsList = viewModel.wizardsState.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.fetchWizards()

    }
    Text( modifier = Modifier.clickable {
        Toast.makeText(context,"Clicked" ,Toast.LENGTH_SHORT).show()

    },
        text = "Clickkkkkkkk",
        color = Color.Black,

        )
    when (wizardsList) {
        is ApiState.Loading -> {
            CircularProgressIndicator()
        }

        is ApiState.Success -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.White)
            ) {
                items(wizardsList.data) { item ->
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                        shape = RoundedCornerShape(12.dp),
                        onClick = {
//                            Toast.makeText(context,item.id ,Toast.LENGTH_SHORT).show()
                            navController.navigate(Screens.WizardDetails.name + "/${item.id}") }

                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp).clickable {
                                                            Toast.makeText(context,item.id ,Toast.LENGTH_SHORT).show()
                                navController.navigate(Screens.WizardDetails.name + "/${item.id}")

                    },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.weight(3f),
                                text = item.firstName ?: "Unknown Wizard",
                                color = Color.Black,

                            )
                        }
                    }
                }


            }
        }

        is ApiState.Error -> Text(text = wizardsList.message)
    }
}
