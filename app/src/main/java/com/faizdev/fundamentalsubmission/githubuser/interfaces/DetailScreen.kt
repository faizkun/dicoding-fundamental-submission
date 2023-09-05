package com.faizdev.fundamentalsubmission.githubuser.interfaces


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.faizdev.component.DetailScreenItem
import com.faizdev.fundamentalsubmission.R
import com.faizdev.fundamentalsubmission.githubuser.dashboard.event.DetailEvent
import com.faizdev.fundamentalsubmission.githubuser.dashboard.event.TabEvent
import com.faizdev.fundamentalsubmission.githubuser.dashboard.state.DetailState
import com.faizdev.fundamentalsubmission.githubuser.dashboard.state.TabState
import com.faizdev.fundamentalsubmission.model.listTab
import com.faizdev.fundamentalsubmission.response.FollowerListResponseItem
import com.faizdev.fundamentalsubmission.response.FollowingListResponseItem
import com.faizdev.fundamentalsubmission.viewmodel.DetailViewModel
import com.faizdev.fundamentalsubmission.viewmodel.TabViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch


@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalPagerApi::class, ExperimentalPagerApi::class,
    ExperimentalFoundationApi::class,

    )
@Composable
fun DetailScreen(
    nama: String?,
    navController: NavController,
    detailViewModel: DetailViewModel = viewModel(),
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = true) {
        detailViewModel.onScreen(DetailEvent.GetDataUser(query = nama))
    }

    val getDetail by detailViewModel.getDetailUser.collectAsState()
    when (val state = getDetail) {
        is DetailState.Error -> {

        }

        DetailState.Loading -> {

//            Box(
//                modifier = Modifier
//                    .fillMaxSize(),
//            )
//            {
//                CircularProgressIndicator()
//            }
        }

        is DetailState.Success -> {
            Scaffold(

                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "${state.DetailList.login}'s profile",
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
                    Spacer(modifier = Modifier.size(19.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 18.dp)
                    ) {
                        AsyncImage(
                            model = state.DetailList.avatar_url,
                            contentDescription = "",
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(87.dp)
                                .border(
                                    BorderStroke(2.dp, MaterialTheme.colorScheme.outline),
                                    CircleShape
                                )

                        )
                        Column(
                            modifier = Modifier.padding(horizontal = 15.dp, vertical = 8.dp)
                        ) {
                            Text(
                                text = state.DetailList.name ?: "unknown name",
                                color = MaterialTheme.colorScheme.background,
                                fontSize = 20.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = state.DetailList.login ?: "unknown username",
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                fontSize = 15.sp,

                                )
                        }
                    }



                    Column() {
                        Spacer(modifier = Modifier.size(14.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 18.dp)

                        ) {

                            Text(
                                text = state.DetailList.bio ?: "",
                                color = MaterialTheme.colorScheme.background,
                                fontSize = 16.sp,
                                style = MaterialTheme.typography.bodyMedium

                            )

                        }

                    }

                    Spacer(modifier = Modifier.size(12.dp))
                    Divider(
                        color = Color.DarkGray

                    )

                    Spacer(modifier = Modifier.size(12.dp))
                    Row(modifier = Modifier.align(alignment = Alignment.CenterHorizontally)) {
                        Column(modifier = Modifier.weight(0.2f)) {
                            Text(
                                text = state.DetailList.followers.toString(),
                                color = MaterialTheme.colorScheme.background,
                                fontSize = 16.sp,

                                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                            )

                            Text(
                                text = "Followers",
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.background,
                                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                            )

                        }
                        Column(modifier = Modifier.weight(0.2f)) {
                            Text(
                                text = state.DetailList.following.toString(),
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.background,
                                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                            )
                            Text(
                                text = "Following",
                                fontSize = 18.sp,
                                color = MaterialTheme.colorScheme.background,
                                modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.size(19.dp))
                    TabRow(
                        selectedTabIndex = pagerState.currentPage,
                        modifier = Modifier
                            .fillMaxWidth(),
                        tabs = {
                            listTab.forEachIndexed { index, model ->
                                Tab(
                                    text = {
                                        Text(
                                            text = model.title,
                                            color = MaterialTheme.colorScheme.background
                                    ) },
                                    selected = pagerState.currentPage == index,
                                    onClick = {
                                        scope.launch {
                                            pagerState.animateScrollToPage(index)
                                        }
                                    }
                                )
                            }
                        },

                        contentColor = MaterialTheme.colorScheme.background,
                        backgroundColor = MaterialTheme.colorScheme.onSurface

                    )



                    TabOnScreen(
                        navController = navController,
                        pagerState = pagerState,
                        name = nama,
                        index = pagerState.currentPage,

                        )

                }
            }
        }

        null -> {

        }
    }


}

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalPagerApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun TabOnScreen(
    navController: NavController,
    pagerState: PagerState,
    name: String?,
    tabViewmodel: TabViewModel = viewModel(),
    index: Int,

    ) {
    val getFollower by tabViewmodel.getFollowerList.collectAsState()
    val getFollowing by tabViewmodel.getFollowingList.collectAsState()
    LaunchedEffect(key1 = index) {
        tabViewmodel.onScreen(TabEvent.GetFollowerList(name))
    }

    HorizontalPager(pageCount = 2, state = pagerState) { page ->
        when (page) {
            0 -> {
                Follower(getFollower = getFollower)
            }

            1 -> {
                LaunchedEffect(key1 = index) {
                    tabViewmodel.onScreen(TabEvent.GetFollowingList(name))
                }
                Following(getFollowing = getFollowing)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Follower(
    getFollower: TabState?,
) {


    when (getFollower) {
        is TabState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = getFollower.message)
            }
        }

        is TabState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is TabState.Success<*> -> {

            Column(
                modifier = Modifier
                    .fillMaxSize(),

                ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp),
                    content = {
                        items(getFollower.TabList) { response ->
                            when (response) {
                                is FollowerListResponseItem -> {
                                    DetailScreenItem(
                                        image = response.avatar_url,
                                        name = response.login,
                                        userLink = response.html_url,
                                        onClick = {},
                                        onInsert = {}
                                    )
                                }
                            }
                        }
                    }
                )
            }
        }

        null -> {}
    }


}


@Composable
fun Following(getFollowing: TabState?) {
    when (getFollowing) {
        is TabState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = getFollowing.message)
            }
        }

        is TabState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is TabState.Success<*> -> {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 10.dp),
                    content = {
                        items(getFollowing.TabList) { response ->
                            when (response) {
                                is FollowingListResponseItem -> {
                                    DetailScreenItem(
                                        image = response.avatar_url,
                                        name = response.login,
                                        userLink = response.html_url,
                                        onClick = {

                                        },
                                        onInsert = {}
                                    )
                                }
                            }
                        }
                    })
            }
        }

        null -> {}
    }
}