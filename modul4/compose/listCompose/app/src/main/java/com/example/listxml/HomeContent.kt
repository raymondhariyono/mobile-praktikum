package com.example.listxml

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.listxml.data.DataProvider


@Composable
fun HomeContent(navController: NavController) {
    androidx.compose.foundation.layout.Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        val films = rememberSaveable { DataProvider.filmList }
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 20.dp)
        ) {
            items(
                items = films,
                itemContent = {
                    FilmListItems(films = it, navController = navController)
                }
            )
        }
    }
}
