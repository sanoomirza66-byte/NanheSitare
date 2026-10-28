package com.nanhesitare.app.ui.screens
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

            // آسمان
            drawRect(Color(0xFFFFFBEA))

            // سرسبز پہاڑ
            val hillBack = Path().apply {
                moveTo(0f, h * 0.45f)
                quadraticBezierTo(w * 0.25f, h * 0.35f, w * 0.5f, h * 0.42f)
                quadraticBezierTo(w * 0.75f, h * 0.48f, w, h * 0.38f)
                lineTo(w, h); lineTo(0f, h); close()
            }
            drawPath(hillBack, Color(0xFFC8E6C9))

            val hillFront = Path().apply {
                moveTo(0f, h * 0.6f)
                quadraticBezierTo(w * 0.35f, h * 0.52f, w * 0.7f, h * 0.62f)
                lineTo(w, h * 0.58f); lineTo(w, h); lineTo(0f, h); close()
            }
            drawPath(hillFront, Color(0xFFA5D6A7))

            // نیلے پانی کی نہر (River)
            val river = Path().apply {
                moveTo(w * 0.35f, h)
                cubicTo(w * 0.45f, h * 0.85f, w * 0.25f, h * 0.72f, w * 0.48f, h * 0.58f)
                lineTo(w * 0.56f, h * 0.58f)
                cubicTo(w * 0.38f, h * 0.72f, w * 0.58f, h * 0.85f, w * 0.5f, h)
                close()
            }
            drawPath(river, Color(0xFF81D4FA))

            // کارٹون درخت (Trees)
            drawRect(Color(0xFF8D6E63), topLeft = Offset(w * 0.08f, h * 0.72f), size = Size(18f, 65f))
            drawCircle(Color(0xFF4CAF50), radius = 34f, center = Offset(w * 0.08f + 9f, h * 0.72f))
            drawCircle(Color(0xFF66BB6A), radius = 26f, center = Offset(w * 0.08f - 10f, h * 0.74f))
            drawCircle(Color(0xFF81C784), radius = 26f, center = Offset(w * 0.08f + 25f, h * 0.74f))

            drawRect(Color(0xFF8D6E63), topLeft = Offset(w * 0.85f, h * 0.68f), size = Size(20f, 75f))
            drawCircle(Color(0xFF388E3C), radius = 38f, center = Offset(w * 0.85f + 10f, h * 0.68f))
            drawCircle(Color(0xFF4CAF50), radius = 28f, center = Offset(w * 0.85f - 12f, h * 0.70f))
            drawCircle(Color(0xFF81C784), radius = 28f, center = Offset(w * 0.85f + 30f, h * 0.70f))

            // رنگ برنگے پھول (Flowers)
            val flowerColors = listOf(Color(0xFFFF5252), Color(0xFFFFEB3B), Color(0xFFFF4081), Color(0xFFFF9800))
            listOf(Offset(w * 0.18f, h * 0.94f), Offset(w * 0.28f, h * 0.96f), Offset(w * 0.72f, h * 0.95f), Offset(w * 0.82f, h * 0.93f)).forEachIndexed { idx, pos ->
                drawCircle(flowerColors[idx % flowerColors.size], radius = 9f, center = pos)
                drawCircle(Color.White, radius = 4f, center = pos)
            }
        }
        content()
    }
}
