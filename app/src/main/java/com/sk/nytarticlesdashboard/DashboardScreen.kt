package com.sk.nytarticlesdashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sk.nytarticlesdashboard.flow.home.HomeViewModel
import com.sk.nytarticlesdashboard.ui.theme.NYTArticlesDashboardTheme



@Composable
fun DashboardScreen(modifier: Modifier = Modifier ,viewModel:HomeViewModel) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        /*   Image(
               painter = painterResource(id = R.drawable.baseline_3d_rotation_24),
               contentDescription = null,
               modifier = modifier.size(width = 250.dp, height = 250.dp)
           )*/

        items(viewModel.articlesState.size){
            ArticleCellView(viewModel.articlesState[it])
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    NYTArticlesDashboardTheme() {
        //DashboardScreen()
    }
}