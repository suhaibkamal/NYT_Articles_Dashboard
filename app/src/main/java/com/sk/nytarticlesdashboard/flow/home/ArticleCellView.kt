package com.sk.nytarticlesdashboard.flow.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.sk.nytarticlesdashboard.R
import com.sk.nytarticlesdashboard.base.DateTimeHelper
import com.sk.nytarticlesdashboard.models.model.ArticleCellModel
import com.sk.nytarticlesdashboard.ui.theme.NYTArticlesDashboardTheme


@Composable
fun ArticleCellView(articleCellModel: ArticleCellModel) {

    Column {
        Row(Modifier.fillMaxWidth()) {
            if(!articleCellModel.imageUrl.isNullOrEmpty()) {
                AsyncImage(
                    model = articleCellModel.imageUrl,
                    contentDescription = null,
                    Modifier
                        .padding(10.dp)
                        .size(100.dp)
                )
            }else{

                Image(
                    painter = painterResource(id = R.drawable.baseline_image_24),
                    contentDescription = null,
                    Modifier
                        .padding(10.dp)
                        .size(100.dp)
                )
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, end = 10.dp, bottom = 10.dp)
            ) {
                Text(
                    text = articleCellModel.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier
                        .fillMaxWidth()


                )
                Spacer(modifier = Modifier.height(35.dp))
                Text(
                    text = DateTimeHelper().getRelativeTimeSpanString(articleCellModel.date), modifier = Modifier
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


    }
}
