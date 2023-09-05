package com.faizdev.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.faizdev.fundamentalsubmission.R


@Composable
fun FavouriteItem(image: String, name: String, onDelete: () -> Unit) {
    Spacer(modifier = Modifier.size(6.dp))
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
            Spacer(modifier = Modifier.size(11.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.background,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.onError,
                modifier = Modifier
                    .clickable { onDelete() },
                )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavouriteItemPreview() {
    FavouriteItem(image = "", name = "") {
        
    }
}


