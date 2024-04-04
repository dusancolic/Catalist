package com.example.catalist.screen

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catalist.R
import com.example.catalist.repository.Repository
import com.example.catalist.ui.theme.CatalistTheme

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalMaterial3Api
@Composable
fun CatDetailsScreen(
    id: String
) {
    val item = Repository.getById(id)
    var number = 1
    Scaffold(
        topBar = {
            Column(modifier = Modifier.background(color = Color(0xFFFFFBFE))) {
                Row {
                    CenterAlignedTopAppBar(
                        title = { Text(text = "Details", fontWeight = FontWeight.Bold) },
                        navigationIcon = {
                            Icon(
                                modifier = Modifier.padding(start = 16.dp),
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                            )
                        }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Divider()
            }
        },
        content = {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .verticalScroll(scrollState)
                    .fillMaxSize()
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {
                Spacer(
                    modifier = Modifier.padding(12.dp)
                )

                Text(
                    modifier = Modifier
                        .padding(12.dp),
                    text = "About ${item?.name}",
                    fontSize = 24.sp,
                    color = Color.hsl(185F, 0.61F, 0.5F),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "(Also known as '${item?.alternativeName}')",
                    fontSize = 16.sp,
                    color = Color.hsl(185F, 0.25F, 0.55F),
                    fontWeight = FontWeight.Bold
                )
                Image(
                    painter = painterResource(id = R.drawable.images),
                    contentDescription = "image of ${item?.name}",
                    modifier = Modifier
                        .size(180.dp),
                )
                Card(modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),)
                {

                    MyTitle(text = "Description and Basic Information")
                    Spacer(modifier = Modifier.height(16.dp))
                    MyRow(text1 = "Description:", text2 = "${item?.description}")
                    MyRow(text1 = "Temperament:", text2 = "${item?.temperament}")
                    MyRow(text1 = "Life span:", text2 = "${item?.lifeSpan} years")
                    MyRow(text1 = "Weight:", text2 = " ${item?.weight} lbs")
                    val rare = item?.rare
                    if(rare == 0)
                        MyRow(text1 = "Rare:", text2 = "Not rare")
                    else
                        MyRow(text1 = "Rare:", text2 = "Rare")

                    Spacer(modifier = Modifier.height(16.dp))

                    MyBehaviour(text = "Adaptability", number = item?.adaptability?: 0)
                    MyBehaviour(text = "AffectionLevel", number = item?.affectionLevel?: 0)
                    MyBehaviour(text = "ChildFriendly", number = item?.childFriendly?: 0)
                    MyBehaviour(text = "DogFriendly", number = item?.dogFriendly?: 0)
                    MyBehaviour(text = "EnergyLevel", number = item?.energyLevel?: 0)

                    MyTitle(text = "Originates from")
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Top
                    )
                    {
                        MyText(text = "${item?.origin}")
                    }
                    MyTitle(text = "Link to Wikipedia page")
                    MyText(text = "${item?.link}")


                }

            }
        }
    )
}
@Composable
fun MyText(text : String)
{
    Text(
        modifier = Modifier.padding(
            start = 16.dp,
            bottom = 16.dp,
            top = 16.dp,
            end = 16.dp
        ),
        text = text,
        color = Color.hsl(192F, 0.92F, 0.31F),
        fontStyle = FontStyle.Italic,
    )
}

@Composable
fun MyBehaviour(text : String, number : Int)
{
    AssistChip(
        modifier = Modifier
            .padding(start = 16.dp, end = 6.dp),
        onClick = {},
        label = { Text(text = text,
            color = Color.hsl(192F, 0.92F, 0.31F),
        )
        }
    )
    val prog = number.toFloat() / 5
    LinearProgressIndicator(
        progress = prog,
        modifier = Modifier.padding(16.dp),
        color = if(prog == 1F)
            Color.hsl(185F, 0.55F, 0.5F)
        else if (prog == 0.8F)
            Color.hsl(185F, 0.55F, 0.44F)
        else if (prog == 0.6F)
            Color.hsl(185F, 0.55F, 0.38F)
        else if (prog == 0.4F)
            Color.hsl(185F, 0.55F, 0.27F)
        else
            Color.hsl(185F, 0.55F, 0.16F)
        ,
        trackColor = Color.hsl(185F, 0.05F, 0.55F),
    )
}
@Composable
fun MyTitle(text : String)
{
    Text(
        modifier = Modifier.padding(
            start = 16.dp,
            bottom = 16.dp,
            top = 16.dp,
            end = 16.dp
        ),
        text = text,
        fontSize = 20.sp,
        color = Color.hsl(185F, 0.61F, 0.5F),
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun MyRow(text1 : String, text2 : String)
{
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    )
    {
        Text(modifier = Modifier
            .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
            .weight(0.5f),
            fontSize = 14.sp,
            text = text1,
            color = Color.hsl(185F, 0.25F, 0.55F),
        )
        Text(
            modifier = Modifier
                .padding(end = 16.dp, top = 8.dp, bottom = 8.dp)
                .weight(1f),
            fontSize = 14.sp,
            text = text2,
            color = Color.Black,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewCatDetailsScreen() {
    CatalistTheme {
        CatDetailsScreen(
            id = "1",
        )
    }
}