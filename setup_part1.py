import os

files = {}

# 1. زوم اینیمیشن اور بیک بٹن فکس
files["app/src/main/java/com/nanhesitare/app/MainActivity.kt"] = """package com.nanhesitare.app
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
"""

# 2. درخت، نہر اور پھولوں کا کارٹون بیک گراؤنڈ
files["app/src/main/java/com/nanhesitare/app/ui/screens/CartoonScenery.kt"] = """package com.nanhesitare.app.ui.screens
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

@Composable
fun CartoonBackground(content: @Composable BoxScope.() -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height
            drawRect(Color(0xFFFFFBEA))

            // پہاڑ
            val hill1 = Path().apply {
                moveTo(0f, h * 0.45f)
                quadraticBezierTo(w * 0.3f, h * 0.36f, w * 0.6f, h * 0.44f)
                quadraticBezierTo(w * 0.8f, h * 0.48f, w, h * 0.4f)
                lineTo(w, h); lineTo(0f, h); close()
            }
            drawPath(hill1, Color(0xFFC8E6C9))

            // نیلے پانی کی نہر (River)
            val river = Path().apply {
                moveTo(w * 0.32f, h)
                cubicTo(w * 0.45f, h * 0.85f, w * 0.22f, h * 0.7f, w * 0.48f, h * 0.58f)
                lineTo(w * 0.58f, h * 0.58f)
                cubicTo(w * 0.35f, h * 0.7f, w * 0.58f, h * 0.85f, w * 0.48f, h)
                close()
            }
            drawPath(river, Color(0xFF81D4FA))

            // درخت (Trees)
            drawRect(Color(0xFF8D6E63), topLeft = Offset(w * 0.06f, h * 0.72f), size = Size(16f, 60f))
            drawCircle(Color(0xFF4CAF50), radius = 32f, center = Offset(w * 0.06f + 8f, h * 0.72f))
            drawCircle(Color(0xFF81C784), radius = 24f, center = Offset(w * 0.06f + 20f, h * 0.73f))

            drawRect(Color(0xFF8D6E63), topLeft = Offset(w * 0.86f, h * 0.68f), size = Size(18f, 70f))
            drawCircle(Color(0xFF388E3C), radius = 36f, center = Offset(w * 0.86f + 9f, h * 0.68f))
            drawCircle(Color(0xFF66BB6A), radius = 26f, center = Offset(w * 0.86f - 8f, h * 0.70f))

            // پھول (Flowers)
            val flowerColors = listOf(Color(0xFFFF5252), Color(0xFFFFEB3B), Color(0xFFFF4081), Color(0xFFFF9800))
            listOf(Offset(w * 0.16f, h * 0.94f), Offset(w * 0.26f, h * 0.96f), Offset(w * 0.74f, h * 0.95f), Offset(w * 0.84f, h * 0.93f)).forEachIndexed { idx, pos ->
                drawCircle(flowerColors[idx % flowerColors.size], radius = 8f, center = pos)
                drawCircle(Color.White, radius = 3.5f, center = pos)
            }
        }
        content()
    }
}
"""

for path, code in files.items():
    os.makedirs(os.path.dirname(path), exist_ok=True)
    with open(path, "w", encoding="utf-8") as f:
        f.write(code)

print("SUCCESS_PART1")
