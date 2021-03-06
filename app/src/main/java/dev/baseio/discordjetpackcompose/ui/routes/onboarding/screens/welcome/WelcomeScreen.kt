package dev.baseio.discordjetpackcompose.ui.routes.onboarding.screens.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.baseio.discordjetpackcompose.R
import dev.baseio.discordjetpackcompose.navigator.ComposeNavigator
import dev.baseio.discordjetpackcompose.navigator.DiscordScreen
import dev.baseio.discordjetpackcompose.ui.routes.onboarding.commonui.CenteredTitleSubtitle
import dev.baseio.discordjetpackcompose.ui.routes.onboarding.commonui.OnboardingScreensButton
import dev.baseio.discordjetpackcompose.ui.theme.DiscordColorProvider
import dev.baseio.discordjetpackcompose.ui.theme.onboarding_button_blue
import dev.baseio.discordjetpackcompose.ui.theme.onboarding_button_grey

@Composable
fun WelcomeScreen(composeNavigator: ComposeNavigator) {

    val sysUiController = rememberSystemUiController()
    val colors = DiscordColorProvider.colors
    SideEffect {
        sysUiController.setSystemBarsColor(color = colors.discordBackgroundOne)
        sysUiController.setNavigationBarColor(color = colors.background)
    }

    Column(
        Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(DiscordColorProvider.colors.background),
        verticalArrangement = Arrangement.SpaceAround
    ) {

        Header()
        Image(
            painter = painterResource(id = R.drawable.welcomelogo),
            contentDescription = null,
            modifier = Modifier.padding(32.dp)
        )

        CenteredTitleSubtitle(
            modifier = Modifier.padding(horizontal = 40.dp),
            title = R.string.welcome_to_discord,
            subtitle = R.string.welcome_message,
            subtitleFontSize = 14.sp
        )

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            OnboardingScreensButton(
                buttonTextProvider = {R.string.register},
                onClick = { composeNavigator.navigate(DiscordScreen.Register.name) }
            )
            Spacer(modifier = Modifier.size(2.dp))
            OnboardingScreensButton(
                buttonTextProvider = {R.string.login},
                buttonBackgroundColor = onboarding_button_grey,
                onClick = { composeNavigator.navigate(DiscordScreen.Login.name) }
            )
        }
    }
}

@Composable
private fun Header() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.discord_welcome_header_light),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth(0.5f),
            tint = DiscordColorProvider.colors.brand
        )
    }
}

