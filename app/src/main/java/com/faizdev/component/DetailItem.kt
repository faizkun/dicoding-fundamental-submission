package com.faizdev.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.faizdev.fundamentalsubmission.R


@Composable
fun DetailScreenItem(
    image: String,
    name: String,
    userLink: String,
    onClick: () -> Unit,
    onInsert: () -> Unit
) {
    val context = LocalContext.current
    var checked by remember {
        mutableStateOf(true)
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
                    color = MaterialTheme.colorScheme.background,
                )
            }






        }
    }
}
