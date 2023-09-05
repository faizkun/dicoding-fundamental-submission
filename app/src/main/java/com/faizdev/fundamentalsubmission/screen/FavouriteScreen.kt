package com.faizdev.fundamentalsubmission.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.faizdev.component.FavouriteItem
import com.faizdev.database.Favourite
import com.faizdev.database.GitSearchDatabase
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouriteScreen(
    navController: NavController

) {

    val context = LocalContext.current
    val getData = GitSearchDatabase.getDatabase(context)
    val scope = rememberCoroutineScope()

    val favourite by getData.favouriteDao().getAllFavourite().collectAsState(emptyList())

    Scaffold(


        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "User Bookmarks",
                        color = MaterialTheme.colorScheme.background

                    )

                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.onSurface
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home_screen") }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            tint = MaterialTheme.colorScheme.background,
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onSurface),
        ) {
            LazyColumn(){
                items(favourite){
                    FavouriteItem(it.imageUrl, it.username, onDelete = {
                        scope.launch {
                            GitSearchDatabase.getDatabase(context).favouriteDao().deleteFavourite(
                                favourite = Favourite(
                                    id = it.id,
                                    imageUrl = it.imageUrl,
                                    username = it.username

                                )

                            )
                            Toast.makeText(context, "Bookmark Deleted", Toast.LENGTH_SHORT).show()

                        }
                    })


                }
            }

        }
    }
}

@Preview
@Composable
fun FavouritePreview() {
    FavouriteScreen(rememberNavController())
}