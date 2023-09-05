package com.faizdev.fundamentalsubmission.githubuser.interfaces

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.faizdev.fundamentalsubmission.R
import com.faizdev.fundamentalsubmission.githubuser.ui.theme.poppins
import kotlinx.coroutines.delay


@Composable
fun SplashAnimation(navController: NavController) {
    LaunchedEffect(key1 = true){
        delay(3000)

    navController.navigate("home_screen")

    }

    SplashScreen()
    
}






@Composable
fun SplashScreen(
) {
    Column(
        modifier = Modifier

            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onSurface),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {


        Image(
            painter = painterResource(id = R.drawable.github_mark),
            contentDescription = "",
            modifier = Modifier.size(200.dp)


        )

        Spacer(modifier = Modifier.size(19.dp))
        Text(
            text = "GitUserSearch",
            color = MaterialTheme.colorScheme.background,
            fontSize = 23.sp,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            fontFamily = poppins



        )

    }

}



//@Preview
//@Composable
//fun SplashPreview() {
//    SplashScreen()
//
//}