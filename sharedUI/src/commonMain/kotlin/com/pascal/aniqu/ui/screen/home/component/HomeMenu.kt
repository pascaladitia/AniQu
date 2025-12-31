package com.pascal.aniqu.ui.screen.home.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pascal.aniqu.ui.theme.Blue500
import org.jetbrains.compose.resources.painterResource
import androidx.compose.ui.tooling.preview.Preview
import aniqu.sharedui.generated.resources.Res
import aniqu.sharedui.generated.resources.logo

@Composable
fun HomeMenu(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Produk Investasi",
            style = MaterialTheme.typography.titleLarge,
            color = Blue500
        )

        Spacer(Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            HomeMenuItem(
                modifier = Modifier.weight(1f),
                label = "Reksa Dana"
            )

            Spacer(Modifier.width(8.dp))

            HomeMenuItem(
                modifier = Modifier.weight(1f),
                label = "Obligasi"
            )
        }
    }
}

@Composable
fun HomeMenuItem(
    modifier: Modifier = Modifier,
    label: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Blue500,
                        Blue500.copy(0.8f)
                    )
                ),
                RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(42.dp),
            painter = painterResource(Res.drawable.logo),
            contentDescription = null
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.titleLarge,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeMenuPreview() {
    HomeMenu()
}