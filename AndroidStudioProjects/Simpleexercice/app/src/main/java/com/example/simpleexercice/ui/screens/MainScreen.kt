package com.example.simpleexercice.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlaylistAddCircle
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import org.jetbrains.annotations.ApiStatus

enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    HOME("home", "Home", Icons.Default.Album, "Home"),
    PROFILE("profile", "Profile", Icons.Default.MusicNote, "Profile"),
    SETTINGS("settings", "Settings", Icons.Default.PlaylistAddCircle, "Settings")
//    SONGS("songs", "Songs", Icons.Default.MusicNote, "Songs"),
//    ALBUM("album", "Album", Icons.Default.Album, "Album"),
//    PLAYLISTS("playlist", "Playlist", Icons.Default.PlaylistAddCircle, "Playlist")
}

@Composable
fun ShowScore(modifier: Modifier = Modifier, mainScreenViewModel: MainScreenViewModel = viewModel()){
    Column(
        modifier = modifier.fillMaxSize(),
        // Centre horizontalement
        horizontalAlignment = Alignment.CenterHorizontally,
        // Centre verticalement
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
    ){
        Text(
            text = "Score: ${mainScreenViewModel.uiState.score}"
        )
        OutlinedButton(
            onClick = {mainScreenViewModel.decrementScore()}
        ){Text("-1")}

        OutlinedButton(onClick = {mainScreenViewModel.incrementScore()}) {
            Text("+1")
        }
    }
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmallNavigationExample(navigateBack: () -> Unit) {
    val backStack = rememberSaveable() { mutableStateListOf<Any>(Destination.HOME) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Small Top App Bar")
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
            )
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary,
                ) {
                    Destination.entries.forEachIndexed { index, destination ->
                        NavigationBarItem(
                            selected = true,
                            onClick = {
                                backStack.add(destination)
                            },
                            icon = {
                                Icon(
                                    destination.icon,
                                    contentDescription = destination.contentDescription
                                )
                            },
                            label = { Text(destination.label) }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            onBack = {backStack.removeLastOrNull()},
            modifier = Modifier.padding(innerPadding),
            entryProvider = { key ->
                when(key){
                    Destination.HOME -> NavEntry(key){
                        Box(
                            modifier = Modifier.padding(innerPadding)
                        ){
                            Text("Home")
                        }
                    }

                    Destination.SETTINGS -> NavEntry(key){
                        SettingsScreen(innerPadding)
                    }
                    else -> {
                        NavEntry(Unit){ Text("Unknown route")}
                    }
                }
            }
        )

//        Column(
//            modifier = Modifier.padding(innerPadding),
//        ) {
//            Text("Démo navigation")
//        }
        ShowScore()

    }
}

@Composable
fun SettingsScreen(innerPadding: PaddingValues){
    Box(
        modifier = Modifier.padding(innerPadding)
    ) {
        Text("SETTINGS")
    }

}