package com.faizdev.fundamentalsubmission.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.faizdev.fundamentalsubmission.R
import com.faizdev.kotpref.AppState
import com.faizdev.kotpref.Preferences


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingScreen(
    navController: NavController
) {
    Scaffold(


        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Settings",
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
                .background(color = MaterialTheme.colorScheme.onSurface),
        ) {

            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {}
                    .padding(start = 15.dp, end = 15.dp, top = 20.dp),

                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer),
                border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.outline),

                )
            {
                Row(
                    modifier = Modifier.padding(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                )
                {

                    Icon(
                        painter = painterResource(id = R.drawable.baseline_nights_stay_24),
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.background)

                    Spacer(modifier = Modifier.size(11.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Dark Mode",
                            color = MaterialTheme.colorScheme.background,
                            fontSize = 18.sp,
                        )
                    }

                    Switch(checked = !AppState.theme, onCheckedChange ={theme ->
                        AppState.theme = !theme
                        Preferences.theme = !theme
                    } )


                }
            }


        }
    }
}

@Preview
@Composable
fun SettingPreview() {
    SettingScreen(rememberNavController())
}
