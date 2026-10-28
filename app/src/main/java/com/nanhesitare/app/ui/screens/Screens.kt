package com.nanhesitare.app.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import kotlinx.coroutines.delay

// 1. Drawing Studio
@Composable
fun DrawingStudioScreen(lang: AppLanguage) {
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
        Box(modifier = Modifier.weight(1f).fillMaxWidth().padding(8.dp).clip(RoundedCornerShape(16.dp)).background(Color.White)
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
            colors.forEach { c -> Box(modifier = Modifier.size(34.dp).clip(CircleShape).background(c).clickable { color = c; isEraser = false }) }
        }
    }
}

// 2. Coloring Book
@Composable
fun ColoringBookScreen(lang: AppLanguage) {
    val paths = remember { mutableStateListOf<DrawPath>() }
    var color by remember { mutableStateOf(Color(0xFFFF9800)) }
    val colors = listOf(Color(0xFFFF5722), Color(0xFFFFEB3B), Color(0xFF4CAF50), Color(0xFF00BCD4), Color(0xFF9C27B0))
    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
        Box(modifier = Modifier.weight(1f).fillMaxWidth().clip(RoundedCornerShape(16.dp)).background(Color.White)
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

// 3. Cartoon Friends
@Composable
fun CartoonFriendsScreen(lang: AppLanguage, onSpeak: (String, String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize().padding(12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        items(AppData.animals) { a ->
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = a.themeColor.copy(alpha = 0.25f))) {
                Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(a.emoji, fontSize = 40.sp)
                    Spacer(modifier = Modifier.width(12.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(if (lang == AppLanguage.URDU) a.urduName else a.englishName, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Text(if (lang == AppLanguage.URDU) a.soundDesc else a.soundDescEn, fontSize = 13.sp, color = Color(0xFFD84315))
                    }
                    Button(onClick = { onSpeak(if (lang == AppLanguage.URDU) a.speechTextUr else a.speechTextEn, if (lang == AppLanguage.URDU) "ur" else "en") }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6F00)), shape = RoundedCornerShape(12.dp)) {
                        Text("🔊", fontSize = 18.sp)
                    }
                }
            }
        }
    }
}

// 4. Learning
@Composable
fun LearningScreen(lang: AppLanguage, onSpeak: (String, String) -> Unit) {
    var tab by remember { mutableIntStateOf(0) }
    val tabs = listOf(AppStrings.t("urdu_letters", lang), AppStrings.t("english_letters", lang), AppStrings.t("numbers", lang), AppStrings.t("quiz", lang))
    Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
        TabRow(selectedTabIndex = tab) { tabs.forEachIndexed { i, t -> Tab(selected = tab == i, onClick = { tab = i }, text = { Text(t, fontSize = 14.sp) }) } }
        Spacer(modifier = Modifier.height(10.dp))
        when (tab) {
            0 -> {
                val list = listOf("ا" to "انار", "ب" to "بلی", "پ" to "پتنگ", "ت" to "تتلی", "ٹ" to "ٹماٹر", "ج" to "جہاز", "چ" to "چڑیا", "د" to "درخت", "ر" to "ریل", "س" to "سیب", "ش" to "شیر", "ط" to "طوطا", "ک" to "کتاب", "گ" to "گڑیا", "م" to "مچھلی")
                LazyVerticalGrid(columns = GridCells.Fixed(3), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(list) { (ch, w) -> Card(modifier = Modifier.fillMaxWidth().height(85.dp).clickable { onSpeak("$ch سے $w", "ur") }, shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) { Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) { Text(ch, fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E88E5)); Text(w, fontSize = 13.sp, color = Color.Gray) } } }
                }
            }
            1 -> {
                val list = listOf("A" to "Apple", "B" to "Ball", "C" to "Cat", "D" to "Duck", "E" to "Elephant", "F" to "Fish", "G" to "Giraffe", "H" to "Horse", "I" to "Ice cream", "J" to "Juice", "K" to "Kite", "L" to "Lion")
                LazyVerticalGrid(columns = GridCells.Fixed(3), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(list) { (ch, w) -> Card(modifier = Modifier.fillMaxWidth().height(85.dp).clickable { onSpeak("$ch for $w", "en") }, shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) { Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) { Text(ch, fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color(0xFF43A047)); Text(w, fontSize = 13.sp, color = Color.Gray) } } }
                }
            }
            2 -> {
                val list = (1..20).map { "$it" to when(it) { 1->"ایک"; 2->"دو"; 3->"تین"; 4->"چار"; 5->"پانچ"; 6->"چھ"; 7->"سات"; 8->"آٹھ"; 9->"نو"; 10->"دس"; else-> "$it" } }
                LazyVerticalGrid(columns = GridCells.Fixed(3), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(list) { (ch, w) -> Card(modifier = Modifier.fillMaxWidth().height(85.dp).clickable { onSpeak(w, "ur") }, shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) { Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) { Text(ch, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFFE65100)); Text(w, fontSize = 13.sp, color = Color.Gray) } } }
                }
            }
            3 -> {
                var ans by remember { mutableStateOf("") }
                Card(modifier = Modifier.fillMaxWidth().padding(12.dp), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                    Column(modifier = Modifier.padding(14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("کوئز: شیر کا کیا نام ہے؟ 🦁", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(10.dp))
                        listOf("شیرو", "مٹھو", "بنٹی").forEach { opt -> Button(onClick = { ans = if (opt == "شیرو") "شاباش! درست جواب! 🎉" else "دوبارہ کوشش کریں 😊" }, modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)) { Text(opt) } }
                        if (ans.isNotEmpty()) { Text(ans, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32), modifier = Modifier.padding(top = 6.dp)) }
                    }
                }
            }
        }
    }
}

// 5. Stories
@Composable
fun StoriesScreen(lang: AppLanguage) {
    var story by remember { mutableStateOf<Story?>(null) }
    if (story == null) {
        LazyColumn(modifier = Modifier.fillMaxSize().padding(12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(AppData.stories) { s ->
                Card(modifier = Modifier.fillMaxWidth().clickable { story = s }, shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                    Row(modifier = Modifier.padding(14.dp)) {
                        Text(s.icon, fontSize = 32.sp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column { Text(if (lang == AppLanguage.URDU) s.titleUr else s.titleEn, fontSize = 16.sp, fontWeight = FontWeight.Bold); Text(if (lang == AppLanguage.URDU) s.moralUr else s.moralEn, fontSize = 12.sp, color = Color(0xFF00897B)) }
                    }
                }
            }
        }
    } else {
        val s = story!!
        Column(modifier = Modifier.fillMaxSize().padding(14.dp).verticalScroll(rememberScrollState())) {
            Button(onClick = { story = null }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6F00))) { Text("← واپس", color = Color.White) }
            Spacer(modifier = Modifier.height(10.dp))
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFDE7))) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(s.icon + " " + if (lang == AppLanguage.URDU) s.titleUr else s.titleEn, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFFD84315))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(if (lang == AppLanguage.URDU) s.contentUr else s.contentEn, fontSize = 17.sp, lineHeight = 26.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(if (lang == AppLanguage.URDU) s.moralUr else s.moralEn, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
                }
            }
        }
    }
}

// 6. Memory Game
@Composable
fun MemoryGameScreen(lang: AppLanguage) {
    val items = listOf("🦁", "🐰", "🐼", "🦜", "🐱", "🐘")
    val cards = remember { mutableStateListOf<MemoryCard>().apply { (items + items).shuffled().forEachIndexed { i, e -> add(MemoryCard(i, e)) } } }
    var c1 by remember { mutableStateOf<Int?>(null) }
    var c2 by remember { mutableStateOf<Int?>(null) }
    LaunchedEffect(c1, c2) {
        if (c1 != null && c2 != null) {
            delay(700)
            if (cards[c1!!].content == cards[c2!!].content) { cards[c1!!] = cards[c1!!].copy(isMatched = true); cards[c2!!] = cards[c2!!].copy(isMatched = true) }
            else { cards[c1!!] = cards[c1!!].copy(isFlipped = false); cards[c2!!] = cards[c2!!].copy(isFlipped = false) }
            c1 = null; c2 = null
        }
    }
    Column(modifier = Modifier.fillMaxSize().padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(AppStrings.t("flip_cards", lang), fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFFC2185B))
        Spacer(modifier = Modifier.height(10.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(3), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            itemsIndexed(cards) { idx, card ->
                Card(
                    modifier = Modifier.fillMaxWidth().height(95.dp).clickable(!card.isFlipped && !card.isMatched && c2 == null) {
                        cards[idx] = card.copy(isFlipped = true)
                        if (c1 == null) c1 = idx else c2 = idx
                    },
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = if (card.isFlipped || card.isMatched) Color.White else Color(0xFFFF80AB))
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text(if (card.isFlipped || card.isMatched) card.content else "⭐", fontSize = if (card.isFlipped || card.isMatched) 36.sp else 24.sp) }
                }
            }
        }
    }
}

// 7. Parent Section
@Composable
fun ParentSectionScreen(lang: AppLanguage) {
    var unlocked by remember { mutableStateOf(false) }
    var ans by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().padding(14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        if (!unlocked) {
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                Column(modifier = Modifier.padding(18.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("🔒 " + AppStrings.t("parent_gate_title", lang), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("8 + 9 = ?", fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E88E5))
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(value = ans, onValueChange = { ans = it }, singleLine = true)
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(onClick = { if (ans.trim() == "17") unlocked = true }) { Text(AppStrings.t("submit", lang)) }
                }
            }
        } else {
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("🛡️ " + AppStrings.t("privacy_policy", lang), fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(AppStrings.t("privacy_content", lang), fontSize = 14.sp)
                }
            }
        }
    }
}
