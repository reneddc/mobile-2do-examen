package com.calyrsoft.frankyapp

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.calyr.data.GihubRepository
import com.calyr.network.GithubRemoteDataSource
import com.calyr.network.RetrofitBuilder
import com.calyrsoft.frankyapp.ui.theme.AppTheme
import com.google.android.material.chip.Chip
import com.google.android.material.color.MaterialColors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GithubChipActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GitUiwithChip(
                        modifier = Modifier.padding(innerPadding),
                        context = LocalContext.current
                    )
                }
            }
        }
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GitUiwithChip(modifier: Modifier = Modifier, context: Context) {

    val dataSource: GithubRemoteDataSource = GithubRemoteDataSource(
        RetrofitBuilder
    )
    val accounts = GihubRepository().getUsers()
    var urlImage by remember { mutableStateOf("") }
    var userId by remember { mutableStateOf("") }
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.github_ui_title)
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            value = userId, onValueChange = {
                userId = it

            })
        Button(onClick = {
            urlImage = ""
            val show = Toast.makeText(context, userId, Toast.LENGTH_LONG).show()
            CoroutineScope(Dispatchers.IO).launch {
                val response = dataSource.getAvatarInfo(userId)
                urlImage = response.url
            }

        }) {
            Text(text = stringResource(id = R.string.github_ui_button))
        }
        val listDesserts = listOf(
            "Apple", "Banana", "Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread", "Honeycomb",
            "Ice Cream Sandwich", "Jellybean", "KitKat", "Lollipop", "Marshmallow", "Nougat"
        )
        FlowColumn(
            Modifier
                .padding(20.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            maxItemsInEachColumn = 7,
        ) {
            repeat(listDesserts.size) {
                Box(
                    modifier = Modifier
                        .border(1.dp, Color.DarkGray, RoundedCornerShape(8.dp))
                        .padding(8.dp)
                ) {

                    Text(
                        text = listDesserts[it],
                        fontSize = 12.sp,
                        modifier = Modifier.padding(3.dp)
                    )
                }
            }
        }

        FlowRow(
            modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            maxItemsInEachRow = 2
        ) {
            val itemModifier = Modifier
                .padding(4.dp)
                .height(80.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Red)
            repeat(6) { item ->
                // if the item is the third item, don't use weight modifier, but rather fillMaxWidth
                if ((item + 1) % 3 == 0) {
                    Spacer(modifier = itemModifier.fillMaxWidth())
                } else {
                    Spacer(modifier = itemModifier.weight(0.5f))
                }
            }
        }

        val rows = 3
        val columns = 3
        FlowRow(
            modifier = Modifier.padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            maxItemsInEachRow = rows
        ) {
            val itemModifier = Modifier
                .padding(4.dp)
                .height(80.dp)
                .weight(1f)
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Blue)
            repeat(rows * columns) {
                Spacer(modifier = itemModifier)
            }
        }

        FlowRow{
            for( account in accounts ) {
                SuggestionChip(onClick = {
                    userId = account
                }, label = {
                    Text(text = account)
                })
            }
        }
        FlowRow{
            SuggestionChip(onClick = { /*TODO*/ }, label = {
                Text(text = accounts[1])
            })
            SuggestionChip(onClick = { /*TODO*/ }, label = {
                Text(text = accounts[2])
            })
            SuggestionChip(onClick = { /*TODO*/ }, label = {
                Text(text = accounts[3])
            })
        }
        FlowRow {
            repeat(times = accounts.size) {
                SuggestionChip(onClick = { /*TODO*/ }, label = {
                    Text(text = accounts[it])
                })
            }
        }

        AsyncImage(
            model = urlImage,
            contentDescription = null
        )
    }
}

