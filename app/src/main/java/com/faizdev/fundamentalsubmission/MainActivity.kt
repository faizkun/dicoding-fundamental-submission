package com.faizdev.fundamentalsubmission

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chibatching.kotpref.Kotpref
import com.example.compose.AppTheme
import com.faizdev.fundamentalsubmission.githubuser.interfaces.DetailScreen
import com.faizdev.fundamentalsubmission.githubuser.interfaces.HomeScreen
import com.faizdev.fundamentalsubmission.githubuser.interfaces.SplashAnimation
import com.faizdev.fundamentalsubmission.githubuser.interfaces.SplashScreen
import com.faizdev.fundamentalsubmission.screen.FavouriteScreen
import com.faizdev.fundamentalsubmission.screen.SettingScreen
import com.faizdev.kotpref.AppState


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Kotpref.init(this@MainActivity)
        setContent {
            AppTheme(useDarkTheme = AppState.theme) {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "splash_screen") {
                        composable(route = "splash_screen") {
                            SplashAnimation(navController = navController)
                        }
                        composable(route = "home_screen") {
                            HomeScreen(navController = navController)
                        }
                        composable(route = "favourite_screen") {
                            FavouriteScreen(navController = navController)
                        }
                        composable(route = "setting_screen") {
                            SettingScreen(navController = navController)
                        }
                        composable(route = "detail_screen" + "/{nama}") {
                            val counter = it.arguments?.getString("nama")


                            DetailScreen(navController = navController, nama = counter )
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        AppTheme {
            Greeting("Android")
        }
    }
}