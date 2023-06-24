package com.sk.nytarticlesdashboard.flow.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.sk.nytarticlesdashboard.ui.theme.NYTArticlesDashboardTheme


@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(modifier: Modifier = Modifier ,viewModel:HomeViewModel) {

    val articles by viewModel.articles.collectAsState()
    val search by viewModel.searchText.collectAsState()

    val swipeToRefreshState = rememberSwipeRefreshState( isRefreshing = viewModel.showLoading.collectAsState(
        initial = false
    ).value)

    Column(modifier = modifier) {
        OutlinedTextField(value = search, onValueChange = {
            viewModel.onSearchTextChange(it)
        },
            label = { Text(text = "Search") }, modifier = Modifier
                .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
                .fillMaxWidth()
        )

        SwipeRefresh(state = swipeToRefreshState, onRefresh = { viewModel.loadArticlesList() }) {


            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxSize()
            ) {
                /*   Image(
                   painter = painterResource(id = R.drawable.baseline_3d_rotation_24),
                   contentDescription = null,
                   modifier = modifier.size(width = 250.dp, height = 250.dp)
               )*/


                    items(articles.size) {
                        ArticleCellView(articles[it])
                    }

            }
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