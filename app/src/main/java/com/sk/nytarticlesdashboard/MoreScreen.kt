package com.sk.nytarticlesdashboard

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sk.nytarticlesdashboard.base.AppPrefrencesHelper
import com.sk.nytarticlesdashboard.flow.home.HomeViewModel
import com.sk.nytarticlesdashboard.ui.theme.NYTArticlesDashboardTheme

@Composable
fun MoreScreen(modifier: Modifier = Modifier,homeViewModel: HomeViewModel) {
    val context = LocalContext.current;
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.baseline_person),
                contentDescription = null,
                Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(10.dp)
                    .size(100.dp)
            )

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, end = 10.dp, bottom = 10.dp)
            ) {
                Text(
                    text = homeViewModel.userState.collectAsState().value.fullName,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxWidth()


                )

                Text(
                    text =  homeViewModel.userState.collectAsState().value.email, modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxWidth()

                )
            }
        }

        Text(
            text = stringResource(id = R.string.details),
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()


        )
        Text(
            text = stringResource(id = R.string.national_id), modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxWidth()

        )

        Text(
            text = homeViewModel.userState.collectAsState().value.nationalNumber, modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
                .fillMaxWidth()

        )
        Text(
            text = stringResource(id = R.string.phone_number), modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
                .fillMaxWidth()

        )
        Text(
            text = homeViewModel.userState.collectAsState().value.phone, modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
                .fillMaxWidth()

        )

        Text(
            text = stringResource(id = R.string.date_of_birth), modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
                .fillMaxWidth()

        )
        Text(
            text = homeViewModel.userState.collectAsState().value.dateOfBirth, modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
                .fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedButton(
            onClick = { basicAlert(context) },
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()) {

            Text(text = stringResource(id = R.string.language))

        }
        Divider()

        Button(
            onClick = { homeViewModel.logout()

                val intent = Intent(context,AuthActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                context.startActivity(intent)
                      },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onPrimary),
            modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()) {

            Text(text = stringResource(id = R.string.logout))

        }
    }

}

fun basicAlert(context: Context){

    val builder = AlertDialog.Builder(context)

    with(builder)
    {
        setTitle(context.getString(R.string.language))
        setMessage(context.getString(R.string.select_your_language_please))
        setPositiveButton(context.getString(R.string.arabic), DialogInterface.OnClickListener { dialogInterface, i ->
            AppPrefrencesHelper().savePreferredLanguage(context,"ar")
            restartTheApp(context)
        }).
        setNegativeButton(R.string.english, DialogInterface.OnClickListener { dialogInterface, i ->
            AppPrefrencesHelper().savePreferredLanguage(context,"en")
            restartTheApp(context)
        }).
        show()
    }


}

fun restartTheApp(context: Context) {
    val intent = Intent(context, SplashActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
    if (context is Activity) {
        (context as Activity).finish()
    }
    Runtime.getRuntime().exit(0)
}



@Preview(showBackground = true)
@Composable
fun MoreScreenPreview() {
    NYTArticlesDashboardTheme() {

    }
}