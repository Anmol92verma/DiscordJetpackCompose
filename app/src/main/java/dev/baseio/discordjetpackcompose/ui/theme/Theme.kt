package dev.baseio.discordjetpackcompose.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController


private val LightColorPalette = DiscordColorPalette(
    primary = design_default_color_primary,
    primaryVariant = design_default_color_primary_variant,
    secondary = design_default_color_secondary,
    secondaryVariant = design_default_color_secondary_variant,
    background = design_default_color_background,
    secondaryBackground = design_default_color_secondary_background,
    onSecondaryBackground = design_default_color_on_secondary,
    surface = design_default_color_surface,
    error = design_default_color_error,
    onPrimary = design_dark_default_color_on_primary,
    onSecondary = design_default_color_on_secondary,
    onBackground = design_default_color_on_background,
    onSurface = design_default_color_on_surface,
    onError = design_default_color_on_error,
    appBarColor = design_default_app_bar_color_light,
    discordBackgroundOne = design_default_app_bar_color_light,
    linkColor = design_default_link_color,
    isLight = true
)

private val DarkColorPalette = DiscordColorPalette(
    primary = design_default_color_primary_dark,
    primaryVariant = design_dark_default_color_primary_variant,
    secondary = design_dark_default_color_secondary,
    secondaryVariant = design_dark_default_color_secondary_variant,
    background = design_dark_default_color_background,
    secondaryBackground = design_dark_default_color_secondary_background,
    onSecondaryBackground = design_dark_default_color_on_background,
    surface = design_dark_default_color_surface,
    error = design_dark_default_color_error,
    onPrimary = design_default_color_on_primary,
    onSecondary = design_dark_default_color_on_secondary,
    onBackground = design_dark_default_color_on_background,
    onSurface = design_dark_default_color_on_surface,
    onError = design_dark_default_color_on_error,
    appBarColor = design_default_app_bar_color_dark,
    discordBackgroundOne = design_default_app_bar_color_dark,
    linkColor = design_default_link_color,
    isLight = false
)

@Composable
fun DiscordJetpackComposeTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (isDarkTheme) DarkColorPalette else LightColorPalette
    val sysUiController = rememberSystemUiController()

    SideEffect {
        sysUiController.setSystemBarsColor(color = colors.appBarColor)
        sysUiController.setNavigationBarColor(color = colors.appBarColor)
    }

    ProvideDiscordColors(colors) {
        MaterialTheme(
            colors = debugColors(isDarkTheme),
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}

object DiscordColorProvider {
    val colors: DiscordColorPalette
        @Composable
        get() = LocalDiscordColor.current
}


val DiscordColorPalette.primarySurface: Color get() = if (isLight) primary else surface

fun DiscordColorPalette.contentColorFor(backgroundColor: Color): Color {
    return when (backgroundColor) {
        primary -> onPrimary
        primaryVariant -> onPrimary
        secondary -> onSecondary
        secondaryBackground -> onSecondaryBackground
        secondaryVariant -> onSecondary
        background -> onBackground
        surface -> onSurface
        error -> onError
        else -> Color.Unspecified
    }
}

/**
 * Discord custom Color Palette
 */
@Stable
class DiscordColorPalette(
    primary: Color = Color(0xFFBB86FC),
    primaryVariant: Color = Color(0xFF3700B3),
    secondary: Color = Color(0xFF03DAC6),
    secondaryVariant: Color = secondary,
    background: Color = Color(0xFF121212),
    secondaryBackground: Color,
    onSecondaryBackground: Color,
    surface: Color = Color(0xFF121212),
    error: Color = Color(0xFFCF6679),
    onPrimary: Color = Color.Black,
    onSecondary: Color = Color.Black,
    onBackground: Color = Color.White,
    onSurface: Color = Color.White,
    onError: Color = Color.Black,
    appBarColor: Color,
    discordBackgroundOne:Color,
    linkColor:Color,
    isLight: Boolean = false
) {
    var primary by mutableStateOf(primary, structuralEqualityPolicy())
        internal set
    var primaryVariant by mutableStateOf(primaryVariant, structuralEqualityPolicy())
        internal set
    var secondary by mutableStateOf(secondary, structuralEqualityPolicy())
        internal set
    var secondaryVariant by mutableStateOf(secondaryVariant, structuralEqualityPolicy())
        internal set
    var background by mutableStateOf(background, structuralEqualityPolicy())
        internal set
    var secondaryBackground by mutableStateOf(secondaryBackground, structuralEqualityPolicy())
        internal set
    var surface by mutableStateOf(surface, structuralEqualityPolicy())
        internal set
    var error by mutableStateOf(error, structuralEqualityPolicy())
        internal set
    var onPrimary by mutableStateOf(onPrimary, structuralEqualityPolicy())
        internal set
    var onSecondary by mutableStateOf(onSecondary, structuralEqualityPolicy())
        internal set
    var onSecondaryBackground by mutableStateOf(onSecondaryBackground, structuralEqualityPolicy())
        internal set
    var onBackground by mutableStateOf(onBackground, structuralEqualityPolicy())
        internal set
    var onSurface by mutableStateOf(onSurface, structuralEqualityPolicy())
        internal set
    var onError by mutableStateOf(onError, structuralEqualityPolicy())
        internal set
    var isLight by mutableStateOf(isLight, structuralEqualityPolicy())
        internal set
    var appBarColor by mutableStateOf(appBarColor, structuralEqualityPolicy())
        internal set
    var discordBackgroundOne by mutableStateOf(discordBackgroundOne, structuralEqualityPolicy())
        internal set
    var linkColor by mutableStateOf(linkColor, structuralEqualityPolicy())
        internal set


    fun update(other: DiscordColorPalette) {
        primary = other.primary
        primaryVariant = other.primaryVariant
        secondary = other.secondary
        secondaryVariant = other.secondaryVariant
        background = other.background
        secondaryBackground = other.secondaryBackground
        onSecondaryBackground = other.onSecondaryBackground
        surface = other.surface
        error = other.error
        onPrimary = other.onPrimary
        onSecondary = other.onSecondary
        onBackground = other.onBackground
        onSurface = other.onSurface
        onError = other.onError
        isLight = other.isLight
        appBarColor = other.appBarColor
        discordBackgroundOne = other.discordBackgroundOne
        linkColor = other.linkColor
    }
}

@Composable
fun ProvideDiscordColors(
    colors: DiscordColorPalette,
    content: @Composable () -> Unit
) {
    val colorPalette = remember { colors }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalDiscordColor provides colorPalette, content = content)
}

private val LocalDiscordColor = staticCompositionLocalOf<DiscordColorPalette> {
    error("No DiscordColorPalette provided")
}

/**
 * A Material [Colors] implementation which sets all colors to [debugColor] to discourage usage of
 * [DiscordColorProvider.colors] in preference to [DiscordColorProvider.colors].
 */
fun debugColors(
    darkTheme: Boolean,
    debugColor: Color = Color.Red
) = Colors(
    primary = debugColor,
    primaryVariant = debugColor,
    secondary = debugColor,
    secondaryVariant = debugColor,
    background = debugColor,
    surface = debugColor,
    error = debugColor,
    onPrimary = debugColor,
    onSecondary = debugColor,
    onBackground = debugColor,
    onSurface = debugColor,
    onError = debugColor,
    isLight = !darkTheme
)
