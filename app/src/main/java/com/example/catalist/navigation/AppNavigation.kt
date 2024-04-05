package com.example.catalist.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.catalist.details.CatDetailsScreen
import com.example.catalist.details.CatDetailsState
import com.example.catalist.details.details
import com.example.catalist.list.CatListScreen
import com.example.catalist.list.CatListState
import com.example.catalist.list.catsList
import com.example.catalist.repository.Repository

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "cats",
    ) {
        catsList(route = "cats", navController = navController)
        details(route = "details/{id}", navController = navController)

    }
}
