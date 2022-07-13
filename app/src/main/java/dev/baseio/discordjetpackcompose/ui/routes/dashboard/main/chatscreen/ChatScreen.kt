package dev.baseio.discordjetpackcompose.ui.routes.dashboard.main.chatscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.AlternateEmail
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.VideoCall
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.baseio.discordjetpackcompose.R.string
import dev.baseio.discordjetpackcompose.navigator.ComposeNavigator
import dev.baseio.discordjetpackcompose.ui.components.DiscordAppBar
import dev.baseio.discordjetpackcompose.ui.components.DiscordScaffold
import dev.baseio.discordjetpackcompose.ui.routes.dashboard.components.OnlineIndicator
import dev.baseio.discordjetpackcompose.ui.theme.DiscordColorProvider
import dev.baseio.discordjetpackcompose.viewmodels.ChatScreenViewModel

@Composable
fun ChatScreen(
  modifier: Modifier = Modifier,
  composeNavigator: ComposeNavigator,
  viewModel: ChatScreenViewModel = hiltViewModel(),
  focusOpacity: Float,
  userName: State<String>
) {
  val scaffoldState = rememberScaffoldState()

  SideEffect {
    viewModel.fetchMessages()
  }

  DiscordScaffold(
    modifier = Modifier.clip(RoundedCornerShape(2)),
    navigator = composeNavigator,
    scaffoldState = scaffoldState,
    backgroundColor = DiscordColorProvider.colors.chatBackground,
    topAppBar = {
      ChatScreenAppBar(
        name = userName.value,
        isOnline = true,
        composeNavigator = composeNavigator
      )
    }
  ) {
    Box(
      modifier = modifier
        .fillMaxSize()
        .background(Color.Black.copy(alpha = focusOpacity))
    ) {
      ChatScreenContent(
        viewModel = viewModel,
        userName = userName
      )
    }
  }
}

@Composable
fun ChatScreenAppBar(
  name: String,
  isOnline: Boolean,
  composeNavigator: ComposeNavigator
) {
  DiscordAppBar(
    navigationIcon = {
      IconButton(onClick = { composeNavigator.navigateUp() }) {
        Icon(
          imageVector = Filled.Menu,
          contentDescription = stringResource(string.menu),
          modifier = Modifier.padding(start = 8.dp),
        )
      }
    },
    title = {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          imageVector = Filled.AlternateEmail,
          contentDescription = null,
          modifier = Modifier.padding(start = 8.dp),
        )
        Text(
          modifier = Modifier.padding(start = 8.dp, end = 8.dp).width(80.dp),
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          text = name,
          color = DiscordColorProvider.colors.onSurface
        )
        OnlineIndicator(
          modifier = Modifier,
          isOnline = isOnline
        )
      }
    },
    actions = {
      Icon(
        imageVector = Filled.Call,
        contentDescription = null,
        modifier = Modifier.padding(end = 16.dp),
      )
      Icon(
        imageVector = Filled.VideoCall,
        contentDescription = null,
        modifier = Modifier.padding(end = 16.dp),
      )
      Icon(
        imageVector = Filled.People,
        contentDescription = null,
        modifier = Modifier.padding(end = 16.dp),
      )
    },
    backgroundColor = DiscordColorProvider.colors.chatTopBar,
    elevation = 0.dp
  )
}