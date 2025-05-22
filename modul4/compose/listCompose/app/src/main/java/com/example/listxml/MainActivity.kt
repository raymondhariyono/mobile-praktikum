package com.example.listxml

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.listxml.data.DataProvider
import com.example.listxml.ui.theme.ListXMLTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListXMLTheme {
                GhibliFilms()
            }
        }
    }
}

@Composable
fun GhibliFilms () {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            HomeContent(navController)
        }
        composable(
            route = "detail/{title}",
            arguments = listOf(navArgument("title") { type = NavType.StringType }) // Ganti ke StringType
        ) { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")
            val film = DataProvider.filmList.find { it.title == title }
            if (film != null) {
                DetailContent(films = film)
            }
        }
    }
}


