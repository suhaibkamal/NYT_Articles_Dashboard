package com.sk.nytarticlesdashboard

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sk.nytarticlesdashboard.ui.theme.NYTArticlesDashboardTheme

@Composable
fun MoreScreen(modifier: Modifier = Modifier) {

    Column(
        modifier = Modifier.fillMaxSize().padding(40.dp).verticalScroll(rememberScrollState()),
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
                    text = "User Name",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxWidth()


                )

                Text(
                    text = "email", modifier = Modifier
                        .padding(start = 10.dp)
                        .fillMaxWidth()

                )
            }
        }

        Text(
            text = "Details",
            fontSize = 20.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()


        )
        Text(
            text = "National ID", modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxWidth()

        )

        Text(
            text = "123456789", modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
                .fillMaxWidth()

        )
        Text(
            text = "Phone Number", modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
                .fillMaxWidth()

        )
        Text(
            text = "+962777940049", modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
                .fillMaxWidth()

        )

        Text(
            text = "Date of Birth", modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
                .fillMaxWidth()

        )
        Text(
            text = "DD-MM-YYYY", modifier = Modifier
                .padding(start = 10.dp, top = 10.dp)
                .fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(top=20.dp,bottom = 20.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()) {

            Text(text = "Language")

        }
        Divider()

        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary, contentColor = MaterialTheme.colorScheme.onPrimary),
            modifier = Modifier
                .padding(top=20.dp,bottom = 20.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth()) {

            Text(text = "Logout")

        }
    }

}


@Preview(showBackground = true)
@Composable
fun MoreScreenPreview() {
    NYTArticlesDashboardTheme() {
        MoreScreen()
    }
}