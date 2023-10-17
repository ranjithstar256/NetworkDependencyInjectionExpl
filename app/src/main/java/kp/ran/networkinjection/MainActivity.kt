package kp.ran.networkinjection

import android.content.Context
import android.content.Intent
import android.icu.number.Scale
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.example.Articles
import dagger.hilt.android.AndroidEntryPoint
import kp.ran.networkinjection.ui.theme.NetworkInjectionTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NetworkInjectionTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Column() {
                        Text(text = "Latest NEWS", fontSize = 32.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                         val viewModel: MainViewModel by viewModels()
                        viewModel.getHeroList()
                        MovieList(applicationContext, viewModel.heroListResponse)
                    }
                }
            }
        }
    }
}

@Composable
fun MovieList(context: Context, movieList: List<Hero>) {
    var selectedIndex by remember { mutableStateOf(-1) }
    LazyColumn {

        itemsIndexed(items = movieList) {
                index, item ->
            MovieItem(context,movie = item, index, selectedIndex) { i ->
                selectedIndex = i
            }
        }
    }

}

@Composable
fun MovieItem(context: Context, movie: Hero, index: Int, selectedIndex: Int,
              onClick: (Int) -> Unit)
{
    val backgroundColor = if (index == selectedIndex) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.background

    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxSize()
            .selectable(true, true, null,
                onClick = {
                    Log.i("test123abc", "MovieItem: $index/n$selectedIndex")
                })
            .clickable { onClick(index) }
            .height(180.dp), shape = RoundedCornerShape(8.dp)
    ) {
        Surface(color = backgroundColor) {

            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            )
            {
                Image(
                    painter = rememberImagePainter(
                        data = movie.imageurl,
                        builder = {
                            scale(coil.size.Scale.FILL)
                            placeholder(android.R.drawable.ic_menu_myplaces)
                            transformations(CircleCropTransformation())
                        }
                    ),
                    contentDescription = movie.bio,
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.3f)
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                        .background(Color.Yellow)
                        .padding(20.dp)
                        .selectable(true, true, null,
                            onClick = {
                                context.startActivity(
                                    Intent(context, DisplayNews::class.java)
                                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                        .putExtra("desk", movie.bio.toString())
                                        .putExtra("urlToImage", movie.imageurl)
                                        .putExtra("title", movie.name)
                                )
                            })
                ) {
                    Text(
                        text = movie.name.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold
                    )
                    HtmlText(html = movie.bio.toString())
                }
            }
        }
    }
}
