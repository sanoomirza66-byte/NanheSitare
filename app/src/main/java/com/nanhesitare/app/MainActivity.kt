package com.nanhesitare.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.nanhesitare.app.ui.*

enum class CurrentScreen {
    SPLASH, HOME, QAIDA, ABC, GINTI, STORIES
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var screen by remember { mutableStateOf(CurrentScreen.SPLASH) }

            // Back button press handling: Home par le jaye ga bajaye app band hone ke!
            BackHandler(enabled = screen != CurrentScreen.HOME && screen != CurrentScreen.SPLASH) {
                screen = CurrentScreen.HOME
            }

            when (screen) {
                CurrentScreen.SPLASH -> SplashScreen { screen = CurrentScreen.HOME }
                CurrentScreen.HOME -> HomeScreen(
                    onNavigateQaida = { screen = CurrentScreen.QAIDA },
                    onNavigateAbc = { screen = CurrentScreen.ABC },
                    onNavigateGinti = { screen = CurrentScreen.GINTI },
                    onNavigateStories = { screen = CurrentScreen.STORIES }
                )
                CurrentScreen.QAIDA -> QaidaScreen(onBack = { screen = CurrentScreen.HOME })
                CurrentScreen.ABC -> AbcScreen(onBack = { screen = CurrentScreen.HOME })
                CurrentScreen.GINTI -> GintiScreen(onBack = { screen = CurrentScreen.HOME })
                CurrentScreen.STORIES -> StoriesScreen(onBack = { screen = CurrentScreen.HOME })
            }
        }
    }
}
