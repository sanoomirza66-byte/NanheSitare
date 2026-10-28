package com.nanhesitare.app
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nanhesitare.app.data.AppLanguage
import com.nanhesitare.app.data.AppStrings
import com.nanhesitare.app.ui.screens.*
import java.util.Locale

enum class Screen { HOME, DRAWING, COLORING, FRIENDS, LEARNING, STORIES, GAMES, PARENT }

class MainActivity : ComponentActivity(), TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tts = TextToSpeech(this, this)
        setContent {
            var currentScreen by remember { mutableStateOf(Screen.HOME) }
            var currentLanguage by remember { mutableStateOf(AppLanguage.URDU) }
            val direction = if (currentLanguage == AppLanguage.URDU) LayoutDirection.Rtl else LayoutDirection.Ltr

            CompositionLocalProvider(LocalLayoutDirection provides direction) {
                Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFFFF9E6)) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Row(
                            modifier = Modifier.fillMaxWidth().background(Color(0xFFFF6F00)).padding(horizontal = 14.dp, vertical = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (currentScreen != Screen.HOME) {
                                Button(
                                    onClick = { currentScreen = Screen.HOME },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Text("← ہوم", color = Color(0xFFFF6F00), fontSize = 16.sp)
                                }
                            } else {
                                Text("⭐ " + AppStrings.t("app_name", currentLanguage), color = Color.White, fontSize = 22.sp)
                            }
                            Button(
                                onClick = { currentLanguage = if (currentLanguage == AppLanguage.URDU) AppLanguage.ENGLISH else AppLanguage.URDU },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD54F)),
                                shape = RoundedCornerShape(18.dp)
                            ) {
                                Text(if (currentLanguage == AppLanguage.URDU) "English 🌐" else "اردو 🇵🇰", color = Color.Black)
                            }
                        }

                        Box(modifier = Modifier.weight(1f)) {
                            when (currentScreen) {
                                Screen.HOME -> HomeScreen(currentLanguage) { currentScreen = it }
                                Screen.DRAWING -> DrawingStudioScreen(currentLanguage)
                                Screen.COLORING -> ColoringBookScreen(currentLanguage)
                                Screen.FRIENDS -> CartoonFriendsScreen(currentLanguage) { t, l -> speakOut(t, l) }
                                Screen.LEARNING -> LearningScreen(currentLanguage) { t, l -> speakOut(t, l) }
                                Screen.STORIES -> StoriesScreen(currentLanguage)
                                Screen.GAMES -> MemoryGameScreen(currentLanguage)
                                Screen.PARENT -> ParentSectionScreen(currentLanguage)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) tts?.language = Locale("ur", "PK")
    }

    private fun speakOut(text: String, langCode: String) {
        tts?.language = if (langCode == "ur") Locale("ur", "PK") else Locale.US
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "NanheSitare")
    }

    override fun onDestroy() {
        tts?.stop()
        tts?.shutdown()
        super.onDestroy()
    }
}
