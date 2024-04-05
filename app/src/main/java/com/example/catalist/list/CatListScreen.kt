package com.example.catalist.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.catalist.R
import com.example.catalist.core.compose.BoxForStates
import com.example.catalist.domain.CatData
import com.example.catalist.repository.Repository
import com.example.catalist.repository.SampleData
import com.example.catalist.core.theme.CatalistTheme

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.catsList(route : String, navController : NavController) {
    composable(route) {
        val catListViewModel = viewModel<CatListViewModel>()
        val state by catListViewModel.state1.collectAsState()
        CatListScreen(
            state = state,
            onClick = {
                navController.navigate("details/${it.id}")
            })
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalMaterial3Api
@Composable
fun CatListScreen(
    //items: List<CatData>,
    state: CatListState,
    onClick: (CatData) -> Unit,
)
{
    val keyboard = LocalSoftwareKeyboardController.current
    var query by remember {
        mutableStateOf("")
    }
    Scaffold(
        topBar = {
            Column (modifier = Modifier.background(color = Color(0xFFFFFBFE))){
                Row {

                    CenterAlignedTopAppBar(title = { Text(text = "Catalist", fontWeight = FontWeight.Bold)},
                        navigationIcon = {
                            Image(
                                painter = painterResource(id = R.drawable.images),
                                contentDescription = "Logo",
                            )
                        })
                }
                Spacer(modifier = Modifier.height(16.dp))

                DockedSearchBar(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    query = query,
                    onQueryChange = {
                        query = it
                     //   filteredItems = Repository.search(it)
                    },
                    onSearch = {
                        keyboard?.hide()
                    },
                    active = false,
                    onActiveChange = {},
                    placeholder = {
                        Text(
                            text = "Search...",
                            color = Color.Gray,
                            fontStyle = FontStyle.Italic
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                        )
                    },
                    trailingIcon = {
                        if (query.isNotEmpty()) {
                            IconButton(onClick = { query = ""
                             //   filteredItems = Repository.search("")
                                }){
                                Icon(Icons.Default.Clear, contentDescription = "Cancel")
                            }
                        }
                    }
                ) {

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
            )
            {
                Spacer(modifier = Modifier.height(16.dp))
                Spacer(modifier = Modifier.height(16.dp))
                if(state.cats.isEmpty())
                {
                    if (state.isLoading) {
                        BoxForStates("Loading...")
                        CircularProgressIndicator()
                    }
                    else if (state.error != null)
                        BoxForStates("Error: ${state.error.message}")
                    else
                        BoxForStates("No cats found")
                }
                else {
                    state.cats.forEach {
                        Column {
                            key(it.id)
                            {
                                CatListItem(
                                    data = it,
                                    onClick = {
                                        onClick(it)
                                    }
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        }
    )
}

@Composable
private fun CatListItem(
    data: CatData,
    onClick: () -> Unit,
    threeOfTemps: List<String> = remember {
        Repository.randomThreeTemeperaments(data.name)
    },
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .clickable {
                onClick()
            },
    ) {
        val altName = if(data.alternativeName == null) "" else " (" + data.alternativeName + ")"
        Text(
                modifier = Modifier.padding(all = 16.dp),
                text = data.name + altName,
                color = Color.hsl(185F, 0.61F, 0.5F),
                fontWeight = FontWeight.Bold,
            )

        Row {
            Text(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 16.dp)
                    .weight(weight = 1f),
                text = data.description.cutToLastDot(250),
            )

            Icon(
                modifier = Modifier.padding(end = 16.dp),
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
            )
        }
        Row {
            threeOfTemps.forEach {
                AssistChip(
                    modifier = Modifier
                        .padding(start = 12.dp, end = 6.dp, top = 8.dp, bottom = 8.dp),
                    onClick = {},
                    label = { Text(text = it,
                            color = Color.hsl(192F, 0.92F, 0.31F),
                        )
                    }
                )
            }
        }


    }
}
private fun String.cutToLastDot(maxLength: Int): String {
    return if (length <= maxLength) {
        this
    } else {
        val lastDotIndex = lastIndexOf('.')
        if (lastDotIndex != -1) {
            substring(0, lastDotIndex + 1) + "..."
        } else {
            substring(0, maxLength) + "..."
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PreviewCatListScreen() {
    CatalistTheme {
        CatListScreen(
            state = CatListState(cats = SampleData, isLoading = true),
            onClick = {}
        )
    }
}