package com.sk.nytarticlesdashboard

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Tab
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Tab
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sk.nytarticlesdashboard.ui.theme.NYTArticlesDashboardTheme
import kotlinx.coroutines.launch

class AuthActivity : ComponentActivity() {

    val tabs =
        listOf(
            TabItem(
                title = "Login",
                screen = { LoginScreen(content = "Account Page") }
            ),
            TabItem(
                title = "Register",
                screen = { RegisterScreen(content = "Favorite list") }
            ),
        )


    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val pagerState = rememberPagerState()
            val coroutineScope = rememberCoroutineScope()
            NYTArticlesDashboardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = { CenterAlignedTopAppBar(title = { Text(text = stringResource(id = R.string.app_name))}, colors = TopAppBarDefaults.centerAlignedTopAppBarColors (
                            containerColor = colorScheme.primary,
                            titleContentColor = colorScheme.onPrimary
                        )) },
                        content = {
                            Column (
                                modifier = Modifier.padding(it)
                            ) {
                                TabRow(
                                    selectedTabIndex = pagerState.currentPage,
                                    modifier = Modifier.padding(end = 100.dp)
                                ) {
                                    tabs.forEachIndexed { index, item ->
                                        Tab(
                                            selected = index == pagerState.currentPage,
                                            text = { Text(text = item.title) },
                                            onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                                        )
                                    }
                                }
                                HorizontalPager(
                                    pageCount = tabs.size,
                                    state = pagerState
                                ) {
                                    tabs[pagerState.currentPage].screen()
                                }
                            }
                        })
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting( modifier: Modifier = Modifier) {


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    content: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Image(
            painter = painterResource(id = R.drawable.new_york_times_t_icon),
            contentDescription = null,
            modifier = Modifier
                .size(width = 120.dp, 120.dp)
                .padding(top = 20.dp)
        )
        OutlinedTextField(value = "", onValueChange ={
            it.length
        } , label = { Text(text = "Email")}, modifier = Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
            .fillMaxWidth())

        OutlinedTextField(value = "", onValueChange ={
            it.length
        } , label = { Text(text = "Password")}, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password), modifier = Modifier
            .padding(bottom = 20.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth())

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onPrimary),
            modifier = Modifier
            .padding(top=20.dp,bottom = 20.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()) {

            Text(text = "Login")

        }
    }
}
@Composable
fun RegisterScreen(
    content: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = content )
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NYTArticlesDashboardTheme {
        Greeting()
    }
}