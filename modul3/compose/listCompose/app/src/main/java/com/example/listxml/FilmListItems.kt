package com.example.listxml
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.listxml.data.Films


@Composable
fun FilmListItems(films: Films, navController: NavController) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xC6C4C2C2)),
        shape = RoundedCornerShape(16.dp),
    ) {
        Row {
            FilmImage(films = films,

            )
            Spacer(modifier = Modifier.width(8.dp))

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    Text(
                        text = films.title,
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 12.sp),
                        modifier = Modifier.weight(1f),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White
                    )
                    Text(
                        text = films.year,
                        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 12.sp),
                        color = Color.White

                    )
                }
                Text(
                    text = films.description,
                    style = TextStyle(fontSize = 10.sp),
                    color = Color.White
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                    .padding(top = 30.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Button(
                        onClick = {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(films.imdbUrl))
                            context.startActivity(intent)
                        },
                        modifier = Modifier.padding(top = 8.dp).weight(1f).wrapContentWidth(),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        Text(text = "IMDB",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 8.sp)
                        )
                    }
                    Spacer(modifier = Modifier.width(5.dp))

                    Button(
                        onClick = {
                            navController.navigate("detail/${films.title}")
                        },
                        modifier = Modifier.padding(top = 8.dp).weight(1f).wrapContentWidth(),
                        shape = RoundedCornerShape(16.dp),
                    ) {
                        Text(text = "Detail",
                            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 8.sp)
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun FilmImage(films: Films){
    Image(
        painter = painterResource(id = films.image),
        contentDescription = null,
        modifier = Modifier
            .size(150.dp)
            .padding(16.dp)
            .clip(RoundedCornerShape(16.dp)),
        contentScale = ContentScale.Crop,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun HomeContentPreview() {
    val dummyNavController = rememberNavController()
    HomeContent(navController = dummyNavController)
}
