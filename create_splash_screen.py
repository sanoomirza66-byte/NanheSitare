content = '''package com.nanhesitare.app.ui.screens

import android.media.MediaPlayer
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onAnimationFinish: () -> Unit) {
    val scale = remember { Animatable(0.2f) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        // Play welcome sound if exists in res/raw (kids_cheer.mp3)
        try {
            val soundId = context.resources.getIdentifier("kids_cheer", "raw", context.packageName)
            if (soundId != 0) {
                val player = MediaPlayer.create(context, soundId)
                player?.start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        // Zoom-in effect
        scale.animateTo(
            targetValue = 1.25f,
            animationSpec = tween(durationMillis = 1100, easing = FastOutSlowInEasing)
        )
        scale.animateTo(
            targetValue = 1.0f,
            animationSpec = tween(durationMillis = 350)
        )
        delay(600)
        onAnimationFinish()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(Color(0xFFFFEB3B), Color(0xFFFF9800), Color(0xFFFF5722))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.scale(scale.value)
        ) {
            Text(
                text = "⭐✨⭐",
                fontSize = 42.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "ننھے ستارے",
                fontSize = 54.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
            Text(
                text = "Nanhe Sitare",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A237E)
            )
        }
    }
}
'''

import os
os.makedirs("app/src/main/java/com/nanhesitare/app/ui/screens", exist_ok=True)
with open("app/src/main/java/com/nanhesitare/app/ui/screens/SplashScreen.kt", "w", encoding="utf-8") as f:
    f.write(content)

print("SUCCESS: SplashScreen.kt with Zoom-in and Sound support created!")
