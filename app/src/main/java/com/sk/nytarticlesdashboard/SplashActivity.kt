package com.sk.nytarticlesdashboard

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
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
import com.sk.nytarticlesdashboard.base.AppPrefrencesHelper
import com.sk.nytarticlesdashboard.base.LocalHelper
import com.sk.nytarticlesdashboard.flow.splash.SplashViewModel
import com.sk.nytarticlesdashboard.ui.theme.NYTArticlesDashboardTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : ComponentActivity() {

    private val viewModel:SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocalHelper().setLocal(AppPrefrencesHelper().getPreferredLanguage(this), context = this)

        setContent {
            NYTArticlesDashboardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SplashScreenView(viewModel=viewModel)
                }
            }
        }


}

@Composable
fun SplashScreenView( modifier: Modifier = Modifier,viewModel:SplashViewModel) {

    Box(contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.new_york_times_t_icon),
            contentDescription = null,
            modifier = modifier.size(width = 120.dp, 120.dp)
        )
    }

    when(viewModel.pathState){
        "Login"->{
            val intent = Intent(this@SplashActivity,AuthActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }

        "Home"->{
            val intent = Intent(this@SplashActivity,HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun SplashPreview() {
    NYTArticlesDashboardTheme {
        SplashScreenView(viewModel=viewModel)
    }
}

}