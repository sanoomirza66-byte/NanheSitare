package com.nanhesitare.app
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nanhesitare.app.data.AppLanguage
import com.nanhesitare.app.data.AppStrings
import com.nanhesitare.app.ui.screens.*
import kotlinx.coroutines.delay
import java.util.Locale

enum class Screen { HOME, DRAWING, COLORING, FRIENDS, LEARNING, STORIES, GAMES, PARENT }

class MainActivity : ComponentActivity(), TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tts = TextToSpeech(this, this)
        setContent {
            var isSplash by remember { mutableStateOf(true) }
            val scale = remember { Animatable(0.2f) }
            LaunchedEffect(Unit) {
                scale.animateTo(1.15f, spring(Spring.DampingRatioMediumBouncy, Spring.StiffnessLow))
                scale.animateTo(1.0f, tween(300))
                delay(1200)
                isSplash = false
            }
            var currentScreen by remember { mutableStateOf(Screen.HOME) }
            var lang by remember { mutableStateOf(AppLanguage.URDU) }
            BackHandler(enabled = currentScreen != Screen.HOME) { currentScreen = Screen.HOME }
            val dir = if (lang == AppLanguage.URDU) LayoutDirection.Rtl else LayoutDirection.Ltr
            CompositionLocalProvider(LocalLayoutDirection provides dir) {
                if (isSplash) {
                    Box(modifier = Modifier.fillMaxSize().background(Color(0xFFFFF9E6)), contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.scale(scale.value)) {
                            Text("⭐ 🌟 ⭐", fontSize = 42.sp)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text("ننھے ستارے", fontSize = 44.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFF6F00))
                            Spacer(modifier = Modifier.height(6.dp))
                            Text("Nanhe Sitare", fontSize = 22.sp, color = Color(0xFF2E7D32), fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(10.dp))
                            Text("کھیلو، سیکھو اور مسکراؤ! ✨", fontSize = 16.sp, color = Color(0xFF5D4037))
                        }
                    }
                } else {
                    Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFFFF9E6)) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            Row(modifier = Modifier.fillMaxWidth().background(Color(0xFFFF6F00)).padding(horizontal = 14.dp, vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                if (currentScreen != Screen.HOME) {
                                    Button(onClick = { currentScreen = Screen.HOME }, colors = ButtonDefaults.buttonColors(containerColor = Color.White), shape = RoundedCornerShape(12.dp)) {
                                        Text("← ہوم", color = Color(0xFFFF6F00), fontSize = 16.sp)
                                    }
                                } else {
                                    Text("⭐ " + AppStrings.t("app_name", lang), color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                                }
                                Button(onClick = { lang = if (lang == AppLanguage.URDU) AppLanguage.ENGLISH else AppLanguage.URDU }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD54F)), shape = RoundedCornerShape(18.dp)) {
                                    Text(if (lang == AppLanguage.URDU) "English 🌐" else "اردو 🇵🇰", color = Color.Black, fontWeight = FontWeight.Bold)
                                }
                            }
                            Box(modifier = Modifier.weight(1f)) {
                                when (currentScreen) {
                                    Screen.HOME -> HomeScreen(lang) { currentScreen = it }
                                    Screen.DRAWING -> DrawingStudioScreen(lang)
                                    Screen.COLORING -> ColoringBookScreen(lang)
                                    Screen.FRIENDS -> CartoonFriendsScreen(lang) { t, l -> speakOut(t, l) }
                                    Screen.LEARNING -> LearningScreen(lang) { t, l -> speakOut(t, l) }
                                    Screen.STORIES -> StoriesScreen(lang)
                                    Screen.GAMES -> MemoryGameScreen(lang)
                                    Screen.PARENT -> ParentSectionScreen(lang)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    override fun onInit(s: Int) { if (s == TextToSpeech.SUCCESS) tts?.language = Locale("ur", "PK") }
    private fun speakOut(t: String, l: String) {
        tts?.language = if (l == "ur") Locale("ur", "PK") else Locale.US
        tts?.speak(t, TextToSpeech.QUEUE_FLUSH, null, "TTS")
    }
    override fun onDestroy() { tts?.stop(); tts?.shutdown(); super.onDestroy() }
}
