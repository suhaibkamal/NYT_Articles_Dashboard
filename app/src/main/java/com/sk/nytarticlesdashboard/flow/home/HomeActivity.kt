package com.sk.nytarticlesdashboard.flow.home

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sk.nytarticlesdashboard.base.AppPrefrencesHelper
import com.sk.nytarticlesdashboard.base.LocalHelper
import com.sk.nytarticlesdashboard.ui.theme.NYTArticlesDashboardTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    private val viewModel:HomeViewModel by viewModels()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NYTArticlesDashboardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Scaffold(
                        topBar = { CenterAlignedTopAppBar(title = { Text(text = "Dashboard")}, colors = TopAppBarDefaults.centerAlignedTopAppBarColors (
                            containerColor = MaterialTheme.colorScheme.primary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary
                        )) },
                        content = {
                            MainScreen(modifier = Modifier.padding(paddingValues = it),viewModel)
                        })
                }
            }
        }
    }
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(
            LocalHelper.setLocale(newBase, AppPrefrencesHelper.getPreferredLanguage(newBase))
        )
    }

}


@Preview(showBackground = true)
@Composable
fun HomePreview() {
    NYTArticlesDashboardTheme {
       // MainScreen(viewModel = viewModel)
    }
}