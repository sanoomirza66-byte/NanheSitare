package com.nanhesitare.app.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nanhesitare.app.data.*

@Composable
fun DrawingStudioScreen(lang: AppLanguage) {
    CartoonBackground {
        val paths = remember { mutableStateListOf<DrawPath>() }
        val undone = remember { mutableStateListOf<DrawPath>() }
        var color by remember { mutableStateOf(Color.Red) }
        var isEraser by remember { mutableStateOf(false) }
        val colors = listOf(Color.Red, Color(0xFFFF9800), Color(0xFFFFEB3B), Color.Green, Color.Blue, Color(0xFF9C27B0), Color.Black)

        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth().background(Color(0xFFEEEEEE)).padding(6.dp), horizontalArrangement = Arrangement.SpaceAround, verticalAlignment = Alignment.CenterVertically) {
                Button(onClick = { if (paths.isNotEmpty()) undone.add(paths.removeLast()) }, enabled = paths.isNotEmpty()) { Text("Undo", fontSize = 12.sp) }
                Button(onClick = { if (undone.isNotEmpty()) paths.add(undone.removeLast()) }, enabled = undone.isNotEmpty()) { Text("Redo", fontSize = 12.sp) }
                FilterChip(selected = isEraser, onClick = { isEraser = !isEraser }, label = { Text(AppStrings.t("eraser", lang), fontSize = 12.sp) })
                Button(onClick = { paths.clear(); undone.clear() }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) { Text(AppStrings.t("clear", lang), color = Color.White, fontSize = 12.sp) }
            }
            Box(modifier = Modifier.weight(1f).fillMaxWidth().padding(8.dp).clip(RoundedCornerShape(18.dp)).background(Color.White.copy(alpha = 0.95f))
                .pointerInput(isEraser, color) {
                    var cur = Path()
                    detectDragGestures(
                        onDragStart = { cur = Path().apply { moveTo(it.x, it.y) }; paths.add(DrawPath(cur, if (isEraser) Color.White else color, if (isEraser) 35f else 14f, isEraser)); undone.clear() },
                        onDrag = { ch, _ -> ch.consume(); cur.lineTo(ch.position.x, ch.position.y); if (paths.isNotEmpty()) paths[paths.lastIndex] = paths.last().copy(path = Path().apply { addPath(cur) }) }
                    )
                }
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    paths.forEach { drawPath(it.path, it.color, style = Stroke(it.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)) }
                }
            }
            Row(modifier = Modifier.fillMaxWidth().background(Color(0xFFF5F5F5)).padding(8.dp), horizontalArrangement = Arrangement.SpaceEvenly) {
                colors.forEach { c -> Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(c).clickable { color = c; isEraser = false }) }
            }
        }
    }
}

@Composable
fun ColoringBookScreen(lang: AppLanguage) {
    CartoonBackground {
        val paths = remember { mutableStateListOf<DrawPath>() }
        var color by remember { mutableStateOf(Color(0xFFFF9800)) }
        val colors = listOf(Color(0xFFFF5722), Color(0xFFFFEB3B), Color(0xFF4CAF50), Color(0xFF00BCD4), Color(0xFF9C27B0))
        Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
            Box(modifier = Modifier.weight(1f).fillMaxWidth().clip(RoundedCornerShape(18.dp)).background(Color.White.copy(alpha = 0.95f))
                .pointerInput(color) {
                    var cur = Path()
                    detectDragGestures(
                        onDragStart = { cur = Path().apply { moveTo(it.x, it.y) }; paths.add(DrawPath(cur, color, 24f)) },
                        onDrag = { ch, _ -> ch.consume(); cur.lineTo(ch.position.x, ch.position.y); if (paths.isNotEmpty()) paths[paths.lastIndex] = paths.last().copy(path = Path().apply { addPath(cur) }) }
                    )
                }
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    paths.forEach { drawPath(it.path, it.color, style = Stroke(it.strokeWidth, cap = StrokeCap.Round, join = StrokeJoin.Round)) }
                    val black = Color.Black
                    val st = Stroke(7f, cap = StrokeCap.Round, join = StrokeJoin.Round)
                    drawCircle(black, radius = 120f, center = center, style = st)
                    val earL = Path().apply { moveTo(center.x - 100f, center.y - 60f); lineTo(center.x - 120f, center.y - 180f); lineTo(center.x - 30f, center.y - 110f) }
                    val earR = Path().apply { moveTo(center.x + 100f, center.y - 60f); lineTo(center.x + 120f, center.y - 180f); lineTo(center.x + 30f, center.y - 110f) }
                    drawPath(earL, black, style = st); drawPath(earR, black, style = st)
                    drawCircle(black, radius = 10f, center = Offset(center.x - 40f, center.y - 20f))
                    drawCircle(black, radius = 10f, center = Offset(center.x + 40f, center.y - 20f))
                    drawCircle(Color.Red, radius = 12f, center = Offset(center.x, center.y + 20f))
                }
            }
            Row(modifier = Modifier.fillMaxWidth().padding(8.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) { colors.forEach { c -> Box(modifier = Modifier.size(34.dp).clip(CircleShape).background(c).clickable { color = c }) } }
                Button(onClick = { paths.clear() }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) { Text(AppStrings.t("clear", lang), color = Color.White) }
            }
        }
    }
}

@Composable
fun CartoonFriendsScreen(lang: AppLanguage, onSpeak: (String, String) -> Unit) {
    CartoonBackground {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(AppData.animals) { a ->
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))) {
                    Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(a.emoji, fontSize = 42.sp)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(if (lang == AppLanguage.URDU) a.urduName else a.englishName, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Text(if (lang == AppLanguage.URDU) a.soundDesc else a.soundDescEn, fontSize = 13.sp, color = Color(0xFFD84315))
                            Text(if (lang == AppLanguage.URDU) a.funFactUr else a.funFactEn, fontSize = 12.sp, color = Color(0xFF455A64))
                        }
                        Button(onClick = { onSpeak(if (lang == AppLanguage.URDU) a.speechTextUr else a.speechTextEn, if (lang == AppLanguage.URDU) "ur" else "en") }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6F00)), shape = RoundedCornerShape(12.dp)) {
                            Text("🔊 بولیں", color = Color.White, fontSize = 13.sp)
                        }
                    }
                }
            }
        }
    }
}
