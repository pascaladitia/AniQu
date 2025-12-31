package com.pascal.aniqu.ui.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.pascal.aniqu.ui.component.button.ButtonComponent
import com.pascal.aniqu.ui.theme.Blue500
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeAccount(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(12.dp))
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "2 Langkah Lagi",
            style = MaterialTheme.typography.titleMedium,
            color = Blue500
        )

        Spacer(Modifier.padding(vertical = 8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            HorizontalDivider(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 24.dp),
                thickness = 4.dp,
                color = Blue500
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                HomeAccountItem(
                    index = 1,
                    label = "Registrasi",
                    isCheck = true
                )

                HomeAccountItem(
                    index = 2,
                    label = "Registrasi"
                )

                HomeAccountItem(
                    index = 3,
                    label = "Registrasi"
                )
            }
        }

        Spacer(Modifier.padding(vertical = 8.dp))

        Text(
            text = buildAnnotatedString {
                append("Lakukan registrasi agar kamu dapat berinvestasi \ndi ")
                withStyle(
                    style = SpanStyle(color = Blue500)
                ) {
                    append("aniqu+")
                }
            },
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.inverseOnSurface,
            textAlign = TextAlign.Center
        )


        Spacer(Modifier.padding(vertical = 8.dp))

        ButtonComponent(
            text = "Lanjutkan Buka Rekening",
            onClick = {}
        )
    }
}

@Composable
fun HomeAccountItem(
    modifier: Modifier = Modifier,
    index: Int,
    label: String,
    isCheck: Boolean = false
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isCheck) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = null,
                tint = Blue500
            )
        } else {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.White)
                    .border(1.dp, Blue500, CircleShape)
                    .padding(2.dp)
                    .border(1.dp, Blue500, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$index",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Spacer(Modifier.padding(vertical = 4.dp))

        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            color = Blue500
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomeAccountPreview() {
    HomeAccount()
}