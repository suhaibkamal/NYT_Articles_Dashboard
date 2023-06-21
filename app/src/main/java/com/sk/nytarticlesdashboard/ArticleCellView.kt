package com.sk.nytarticlesdashboard
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sk.nytarticlesdashboard.ui.theme.NYTArticlesDashboardTheme

public class ArticleCellView {

    @Composable
    fun ArticleCellView(){

            Column {
                Row(Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_image_24),
                        contentDescription = null,
                        Modifier
                            .padding(10.dp)
                            .size(100.dp)
                    )

                    Column(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, end = 10.dp, bottom = 10.dp)
                    ) {
                        Text(
                            text = "article title",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier
                                .fillMaxWidth()


                        )
                        Spacer(modifier = Modifier.height(40.dp))
                        Text(
                            text = "1 day", modifier = Modifier
                                .fillMaxWidth()

                        )
                    }
                }
                Divider()
            }

    }
    @Preview(showBackground = true)
    @Composable
    fun CellPreview() {
        NYTArticlesDashboardTheme {

            ArticleCellView(
            )
        }
    }
}