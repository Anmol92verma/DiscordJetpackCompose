package dev.baseio.discordjetpackcompose.ui.routes.dashboard.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.baseio.discordjetpackcompose.ui.theme.DiscordColorProvider
import dev.baseio.discordjetpackcompose.ui.theme.DiscordJetpackComposeTheme
import dev.baseio.discordjetpackcompose.ui.theme.DiscordSurface

@Composable
private fun CountIndicator(
    modifier: Modifier = Modifier,
    count: Int?,
    showCardBackground: Boolean = true,
    textSize: TextUnit = 8.sp,
    forceCircleShape: Boolean = true,
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = count != null && count > 0
    ) {
        val textSizeInDp = with(LocalDensity.current) { textSize.toDp() }
        DiscordSurface(
            shape = CircleShape,
            color = if (showCardBackground) DiscordColorProvider.colors.surface else Color.Transparent
        ) {
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .sizeIn(
                        minWidth = textSizeInDp * 2,
                        minHeight = textSizeInDp * 2,
                        maxWidth = if (forceCircleShape) textSizeInDp * 2 else Dp.Unspecified,
                        maxHeight = if (forceCircleShape) textSizeInDp * 2 else Dp.Unspecified,
                    )
                    .clip(CircleShape)
                    .background(DiscordColorProvider.colors.error),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = count?.toString() ?: "0",
                    fontSize = textSize,
                    color = Color.White,
                    fontWeight = FontWeight.Black,
                    maxLines = 1,
                    modifier = Modifier.padding(2.dp),
                    overflow = TextOverflow.Ellipsis,
                    softWrap = false
                )
            }
        }
    }
}

@Composable
fun CountIndicator(
    modifier: Modifier = Modifier,
    count: Int?,
    showCardBackground: Boolean = true,
    textSize: TextUnit = 8.sp,
    indicatorAlignment: Alignment = Alignment.BottomEnd,
    forceCircleShape: Boolean = true,
    content: @Composable BoxScope.() -> Unit = {}
) {
    Box(contentAlignment = indicatorAlignment) {
        content()
        CountIndicator(
            modifier = modifier,
            count = count,
            showCardBackground = showCardBackground,
            textSize = textSize,
            forceCircleShape = forceCircleShape,
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun CountIndicatorPreview() {
    DiscordJetpackComposeTheme {
        Column {
            CountIndicator(
                count = 99,
                forceCircleShape = true
            )
            CountIndicator(
                count = 99970,
                forceCircleShape = true
            )
            CountIndicator(
                count = 99970,
                forceCircleShape = !true
            )
            CountIndicator(
                count = 100,
                forceCircleShape = true
            ) {
                Surface(modifier = Modifier.size(48.dp), shape = CircleShape, color = Color.Green) {}
            }
            CountIndicator(
                count = 100,
                forceCircleShape = !true
            ) {
                Surface(modifier = Modifier.size(48.dp), shape = CircleShape, color = Color.Green) {}
            }
        }
    }
}