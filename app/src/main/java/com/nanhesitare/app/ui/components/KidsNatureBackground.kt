package com.nanhesitare.app.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp

@Composable
fun KidsNatureBackground(content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF87CEEB), // Sky Blue
                        Color(0xFFE0F7FA), // Soft Light Sky
                        Color(0xFFC8E6C9)  // Soft Meadow Green
                    )
                )
            )
    ) {
        // Nature Landscape Canvas (Canal/River, Hills, Trees, Flowers)
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            // 1. Distant Green Hills
            val hillPath = Path().apply {
                moveTo(0f, height * 0.78f)
                cubicTo(
                    width * 0.25f, height * 0.72f,
                    width * 0.5f, height * 0.82f,
                    width, height * 0.74f
                )
                lineTo(width, height)
                lineTo(0f, height)
                close()
            }
            drawPath(hillPath, Color(0xFF81C784).copy(alpha = 0.55f))

            // 2. Winding Blue Canal / River
            val riverPath = Path().apply {
                moveTo(width * 0.2f, height)
                cubicTo(
                    width * 0.35f, height * 0.88f,
                    width * 0.15f, height * 0.82f,
                    width * 0.45f, height * 0.75f
                )
                lineTo(width * 0.55f, height * 0.75f)
                cubicTo(
                    width * 0.3f, height * 0.84f,
                    width * 0.5f, height * 0.90f,
                    width * 0.45f, height
                )
                close()
            }
            drawPath(riverPath, Color(0xFF29B6F6).copy(alpha = 0.65f))

            // 3. Tree Foliage (Left Side)
            drawCircle(Color(0xFF388E3C).copy(alpha = 0.4f), radius = 90f, center = Offset(60f, height * 0.80f))
            drawCircle(Color(0xFF4CAF50).copy(alpha = 0.45f), radius = 70f, center = Offset(110f, height * 0.78f))

            // 4. Tree Foliage (Right Side)
            drawCircle(Color(0xFF2E7D32).copy(alpha = 0.4f), radius = 100f, center = Offset(width - 40f, height * 0.82f))
            drawCircle(Color(0xFF66BB6A).copy(alpha = 0.45f), radius = 75f, center = Offset(width - 110f, height * 0.80f))

            // 5. Colorful Little Flowers Dots
            val flowerColors = listOf(Color(0xFFFF4081), Color(0xFFFFEB3B), Color(0xFFFF9800), Color(0xFFE040FB))
            val spots = listOf(
                Offset(width * 0.1f, height * 0.94f),
                Offset(width * 0.18f, height * 0.96f),
                Offset(width * 0.65f, height * 0.93f),
                Offset(width * 0.82f, height * 0.95f),
                Offset(width * 0.9f, height * 0.91f)
            )
            spots.forEachIndexed { i, pos ->
                drawCircle(flowerColors[i % flowerColors.size], radius = 9f, center = pos)
                drawCircle(Color.White, radius = 4f, center = pos)
            }
        }

        // Overlay App Content smoothly on top
        content()
    }
}
