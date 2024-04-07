package com.example.catalist.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.catalist.R
import com.example.catalist.core.compose.BoxForStates
import com.example.catalist.core.compose.NoCatIdFound
import com.example.catalist.repository.Repository
import com.example.catalist.core.theme.CatalistTheme

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.details(route : String, navController : NavController) {

    composable(route, arguments = listOf(
        navArgument("id") {
            type = NavType.StringType
            nullable = false
        }
    )
    ){
        navBackStackEntry -> val catId = navBackStackEntry.arguments?.getString("id") ?: throw IllegalStateException("Missing ID")
        val catDetailsViewModel = viewModel<CatDetailsViewModel>(
            factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CatDetailsViewModel(catId = catId) as T
            }
        },)
        val state by catDetailsViewModel.state1.collectAsState()

        val cat = Repository.getById(catId)
        if(cat!=null) {
            CatDetailsScreen(
                state = state,
                clicked = {
                    navController.navigateUp()
                })
        }
        else
            NoCatIdFound(catId)
    }
}
@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalMaterial3Api
@Composable
fun CatDetailsScreen(
    state: CatDetailsState,
    clicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            Column(modifier = Modifier.background(color = Color(0xFFFFFBFE))) {
                Row {
                    CenterAlignedTopAppBar(
                        title = { Text(text = "Details", fontWeight = FontWeight.Bold) },
                        navigationIcon = {

                            Icon(
                                modifier = Modifier.padding(start = 16.dp).
                                    clickable(onClick = clicked),
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

                if(state.cat !=null) {
                    Text(
                        modifier = Modifier
                            .padding(12.dp),
                        text = "About ${state.cat?.name}",
                        fontSize = 24.sp,
                        color = Color.hsl(185F, 0.61F, 0.5F),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "(Also known as '${state.cat?.alternativeName}')",
                        fontSize = 16.sp,
                        color = Color.hsl(185F, 0.25F, 0.55F),
                        fontWeight = FontWeight.Bold
                    )
                    Image(
                        painter = painterResource(id = R.drawable.images),
                        contentDescription = "image of ${state.cat?.name}",
                        modifier = Modifier
                            .size(180.dp),
                    )
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth(),
                    )
                    {

                        MyTitle(text = "Description and Basic Information")
                        Spacer(modifier = Modifier.height(16.dp))
                        MyRow(text1 = "Description:", text2 = state.cat.description)
                        MyRow(text1 = "Temperament:", text2 = state.cat.temperament)
                        MyRow(text1 = "Life span:", text2 = "${state.cat.lifeSpan} years")
                        MyRow(text1 = "Weight:", text2 = " ${state.cat.weight} lbs")
                        val rare = state.cat.rare
                        MyRow(text1 = "Rare:", text2 = if (rare == 0) "Not Rare" else "Rare")

                        Spacer(modifier = Modifier.height(16.dp))

                        MyBehaviour(text = "Adaptability", number = state.cat.adaptability)
                        MyBehaviour(
                            text = "Grooming",
                            number = state.cat.grooming ?: 0
                        )
                        MyBehaviour(text = "Stranger Friendly", number = state.cat.strangerFriendly)
                        MyBehaviour(text = "Dog Friendly", number = state.cat.dogFriendly )
                        MyBehaviour(text = "Energy Level", number = state.cat.energyLevel )

                        MyTitle(text = "Originates from")
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.Top
                        )
                        {
                            MyText(text = state.cat.origin)
                        }
                        MyTitle(text = "Link to Wikipedia page")
                        MyText(text = state.cat.link)


                    }
                }
                else
                {
                    if (state.isLoading) {
                        BoxForStates("Loading...")
                        CircularProgressIndicator()
                    }
                    else if (state.error != null)
                        BoxForStates("Error: ${state.error.message}")
                    else
                        NoCatIdFound(state.catId)
                }

            }
        }
    )
}
@Composable
private fun MyText(text : String)
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
private fun MyBehaviour(text : String, number : Int)
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
    val lightness = when (prog) {
        1F -> 0.5F
        0.8F -> 0.44F
        0.6F -> 0.38F
        0.4F -> 0.27F
        else -> 0.16F
    }
    LinearProgressIndicator(
        progress = prog,
        modifier = Modifier.padding(16.dp),
        color = Color.hsl(185F, 0.55F, lightness),
        trackColor = Color.hsl(185F, 0.05F, 0.55F),
    )
}
@Composable
private fun MyTitle(text : String)
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
private fun MyRow(text1 : String, text2 : String)
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
            state = CatDetailsState(cat = Repository.allData().first(), catId = "1"),
            clicked = {}
        )
    }
}