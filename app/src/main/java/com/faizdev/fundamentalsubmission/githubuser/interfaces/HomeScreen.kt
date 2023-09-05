package com.faizdev.fundamentalsubmission.githubuser.interfaces

import android.media.Image
import android.provider.ContactsContract.Intents.Insert
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.faizdev.database.Favourite
import com.faizdev.database.GitSearchDatabase
import com.faizdev.fundamentalsubmission.R
import com.faizdev.fundamentalsubmission.githubuser.dashboard.event.DashboardEvent
import com.faizdev.fundamentalsubmission.githubuser.dashboard.state.DashboardState
import com.faizdev.fundamentalsubmission.githubuser.ui.theme.poppins
import com.faizdev.fundamentalsubmission.viewmodel.DashboardViewmodel
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    dashboardViewmodel: DashboardViewmodel = viewModel(),
    navController: NavController,

    ) {
    val scope = rememberCoroutineScope()

    val checkState = remember {
        mutableStateOf(false)
    }

    var searchState by remember {
        mutableStateOf("")
    }

    var searchActiveState by remember {
        mutableStateOf(false)
    }


    val getUser by dashboardViewmodel.getUserState.collectAsState()
    LaunchedEffect(key1 = true) {
        dashboardViewmodel.onScreen(DashboardEvent.GetUser)
    }


    val getSearchUser by dashboardViewmodel.getSearchUserState.collectAsState()


    Scaffold(

        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("favourite_screen")
                },
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp, bottomStart = 30.dp)
            ) {
                Icon(

                    painter = painterResource(id = R.drawable.baseline_bookmark_24),
                    contentDescription = "add bookmark",

                    )

            }

        },


        topBar = {
            TopAppBar(


                title = {
                    Text(
                        text = "GitUserSearch",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.background,
                        fontFamily = poppins,
                        fontWeight = FontWeight.Bold,


                        )

                },

                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.onSurface),

                actions = {
                    IconButton(onClick = {
                        navController.navigate("setting_screen")
                    }) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.background
                        )


                    }
                },


                )
        }
    ) {
        Column(


            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.onSurface),


            ) {

            var nama by remember {
                mutableStateOf("")
            }
            val context = LocalContext.current



            SearchBar(
                query = searchState,
                onQueryChange = { value -> searchState = value },
                onSearch = { dashboardViewmodel.onScreen(DashboardEvent.GetSearch(searchState)) },
                active = searchActiveState,
                onActiveChange = { active ->
                    searchActiveState = active
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("Search Users") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },

                ) {

                when (val state = getSearchUser) {
                    is DashboardState.Error -> {
                        Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show()
                    }

                    DashboardState.Loading -> {
                        Spacer(modifier = Modifier.height(28.dp))
                        CircularProgressIndicator(
                            modifier = Modifier.align(alignment = CenterHorizontally)
                        )
                    }

                    is DashboardState.Success -> {
                        LazyColumn(content = {
                            items(state.listUser) {
                                UserScreenItem(
                                    image = it.avatar_url,
                                    name = it.login,
                                    userLink = it.html_url,
                                    onClick = { navController.navigate("detail_screen" + "/${it.login}") },
                                    onInsert = {
                                        scope.launch {
                                            GitSearchDatabase.getDatabase(context).favouriteDao()
                                                .insertFavourite(
                                                    Favourite(
                                                        id = it.id,
                                                        imageUrl = it.avatar_url,
                                                        username = it.login
                                                    )
                                                )
                                            Toast.makeText(
                                                context,
                                                "Bookmark Added",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    })
                            }
                        })
                    }

                    null -> {}

                }

            }

            when (val state = getUser) {
                is DashboardState.Error -> {
                    Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show()
                }

                DashboardState.Loading -> {
                    Spacer(modifier = Modifier.height(28.dp))
                    CircularProgressIndicator(
                        modifier = Modifier.align(alignment = CenterHorizontally)
                    )

                }

                is DashboardState.Success -> {
                    LazyColumn(content = {
                        items(state.listUser) {
                            UserScreenItem(
                                it.avatar_url, it.login, it.html_url, onClick =
                                { navController.navigate("detail_screen" + "/${it.login}") },
                                onInsert = {
                                    scope.launch {
                                        GitSearchDatabase.getDatabase(context).favouriteDao()
                                            .insertFavourite(
                                                Favourite(
                                                    id = it.id,
                                                    imageUrl = it.avatar_url,
                                                    username = it.login
                                                )

                                            )
                                        Toast.makeText(
                                            context,
                                            "Bookmark Added",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }

                                }
                            )

                        }
                    })
                }

                null -> {

                }
            }
        }
    }
}


@Composable
fun UserScreenItem(
    image: String,
    name: String,
    userLink: String,
    onClick: () -> Unit,
    onInsert: () -> Unit
) {
    val context = LocalContext.current
    var checked by remember {
        mutableStateOf(false) // initially checked
    }
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(start = 15.dp, end = 15.dp, top = 20.dp),

        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer),
        border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline),


        )
    {
        Row(modifier = Modifier.padding(15.dp)) {
            AsyncImage(
                model = image,
                contentDescription = "User Image",
                modifier = Modifier
                    .size(49.dp)
                    .clip(CircleShape)
                    .border(
                        BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
                        CircleShape
                    )


            )
            Spacer(modifier = Modifier.size(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.background,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.size(5.dp))
                Text(
                    text = userLink,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.surfaceVariant,
                )
            }


                Icon(
                    painter = painterResource(id = R.drawable.baseline_bookmark_24),
                    contentDescription = "",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterVertically)
                        .clickable {
                            onInsert()
                        },
                    tint = MaterialTheme.colorScheme.background
                )

            }

        }
    }


