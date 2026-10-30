package com.nanhesitare.app.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

@Composable
fun NatureBackground(content: @Composable BoxScope.() -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // 1. Sky Gradient
            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF81D4FA), Color(0xFFE1F5FE))
                ),
                size = size
            )

            // 2. Bright Golden Sun
            drawCircle(
                color = Color(0xFFFFEB3B),
                radius = w * 0.12f,
                center = Offset(w * 0.85f, h * 0.10f)
            )

            // 3. Rolling Green Hills (Background)
            val hillBack = Path().apply {
                moveTo(0f, h * 0.70f)
                quadraticBezierTo(w * 0.4f, h * 0.58f, w, h * 0.68f)
                lineTo(w, h)
                lineTo(0f, h)
                close()
            }
            drawPath(hillBack, color = Color(0xFF81C784))

            // 4. Blue River / Stream
            val river = Path().apply {
                moveTo(w * 0.45f, h * 0.65f)
                quadraticBezierTo(w * 0.55f, h * 0.80f, w * 0.75f, h)
                lineTo(w * 0.95f, h)
                quadraticBezierTo(w * 0.70f, h * 0.78f, w * 0.55f, h * 0.65f)
                close()
            }
            drawPath(river, color = Color(0xFF29B6F6))

            // 5. Foreground Lush Green Field
            val hillFront = Path().apply {
                moveTo(0f, h * 0.78f)
                quadraticBezierTo(w * 0.5f, h * 0.88f, w, h * 0.76f)
                lineTo(w, h)
                lineTo(0f, h)
                close()
            }
            drawPath(hillFront, color = Color(0xFF4CAF50))

            // 6. Tree on the left
            drawRect(
                color = Color(0xFF795548),
                topLeft = Offset(w * 0.08f, h * 0.70f),
                size = androidx.compose.ui.geometry.Size(w * 0.04f, h * 0.15f)
            )
            drawCircle(
                color = Color(0xFF2E7D32),
                radius = w * 0.10f,
                center = Offset(w * 0.10f, h * 0.68f)
            )

            // 7. Small colorful flowers on the ground
            drawCircle(color = Color(0xFFFF4081), radius = 6f, center = Offset(w * 0.25f, h * 0.92f))
            drawCircle(color = Color(0xFFFFEB3B), radius = 6f, center = Offset(w * 0.35f, h * 0.95f))
            drawCircle(color = Color(0xFFFF5722), radius = 6f, center = Offset(w * 0.15f, h * 0.88f))
            drawCircle(color = Color(0xFFE040FB), radius = 6f, center = Offset(w * 0.85f, h * 0.93f))
        }

        // Child content on top of nature
        content()
    }
}
