package com.sk.nytarticlesdashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.sk.nytarticlesdashboard.ui.theme.NYTArticlesDashboardTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CustomSplashScreen")
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NYTArticlesDashboardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SplashScreenView()
                }
            }
        }

        lifecycleScope.launch {
            delay(3000)
            startActivity(Intent(this@SplashActivity,AuthActivity::class.java))

        }
}

@Composable
fun SplashScreenView( modifier: Modifier = Modifier) {

    Box(contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.new_york_times_t_icon),
            contentDescription = null,
            modifier = modifier.size(width = 120.dp, 120.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    NYTArticlesDashboardTheme {
        SplashScreenView()
    }
}

}