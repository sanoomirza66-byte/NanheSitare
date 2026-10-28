package com.nanhesitare.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nanhesitare.app.data.*
import kotlinx.coroutines.delay

@Composable
fun LearningScreen(lang: AppLanguage, onSpeak: (String, String) -> Unit) {
    CartoonBackground {
        var tab by remember { mutableIntStateOf(0) }
        val tabs = listOf(AppStrings.t("urdu_letters", lang), AppStrings.t("english_letters", lang), AppStrings.t("numbers", lang), AppStrings.t("quiz", lang))

        Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
            TabRow(selectedTabIndex = tab, containerColor = Color.White.copy(alpha = 0.85f)) {
                tabs.forEachIndexed { i, t -> Tab(selected = tab == i, onClick = { tab = i }, text = { Text(t, fontSize = 13.sp, fontWeight = FontWeight.Bold) }) }
            }
            Spacer(modifier = Modifier.height(10.dp))

            when (tab) {
                0 -> {
                    val fullUrduHaroof = listOf(
                        "ا" to "انار 🍎", "ب" to "بلی 🐱", "پ" to "پتنگ 🪁", "ت" to "تتلی 🦋", "ٹ" to "ٹماٹر 🍅", "ث" to "ثمر 🍇",
                        "ج" to "جہاز ✈️", "چ" to "چڑیا 🐦", "ح" to "حلوہ 🍲", "خ" to "خرگوش 🐰", "د" to "درخت 🌳", "ڈ" to "ڈولفین 🐬",
                        "ذ" to "ذخیرہ 📦", "ر" to "ریل 🚂", "ڑ" to "پہاڑ 🏔️", "ز" to "زرافہ 🦒", "ژ" to "ژالہ 🌨️", "س" to "سیب 🍏",
                        "ش" to "شیر 🦁", "ص" to "صابن 🧼", "ض" to "ضعیف 👴", "ط" to "طوطا 🦜", "ظ" to "ظروف 🏺", "ع" to "عینک 👓",
                        "غ" to "غبارہ 🎈", "ف" to "فوارہ ⛲", "ق" to "قلم ✒️", "ک" to "کتاب 📖", "گ" to "گڑیا 🪆", "ل" to "لومڑی 🦊",
                        "م" to "مچھلی 🐟", "ن" to "نلکا 🚰", "ں" to "ماں ❤️", "و" to "ورزش 🏃", "ہ" to "ہاتھی 🐘", "ھ" to "گھوڑا 🐎",
                        "ء" to "چائے ☕", "ی" to "یکہ 🐎", "ے" to "لڑکے 👦"
                    )
                    LazyVerticalGrid(columns = GridCells.Fixed(3), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(fullUrduHaroof) { (ch, w) ->
                            Card(modifier = Modifier.fillMaxWidth().height(88.dp).clickable { onSpeak("$ch سے $w", "ur") }, shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f))) {
                                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                    Text(ch, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E88E5))
                                    Text(w, fontSize = 13.sp, color = Color(0xFF37474F))
                                }
                            }
                        }
                    }
                }
                1 -> {
                    val fullABC = listOf(
                        "A" to "Apple 🍎", "B" to "Ball ⚽", "C" to "Cat 🐱", "D" to "Duck 🦆", "E" to "Elephant 🐘",
                        "F" to "Fish 🐟", "G" to "Giraffe 🦒", "H" to "Horse 🐎", "I" to "Ice Cream 🍦", "J" to "Juice 🧃",
                        "K" to "Kite 🪁", "L" to "Lion 🦁", "M" to "Monkey 🐒", "N" to "Nest 🪹", "O" to "Owl 🦉",
                        "P" to "Parrot 🦜", "Q" to "Queen 👑", "R" to "Rabbit 🐰", "S" to "Sun ☀️", "T" to "Tiger 🐅",
                        "U" to "Umbrella ☂️", "V" to "Van 🚐", "W" to "Watch ⌚", "X" to "Xylophone 🎵", "Y" to "Yak 🐂", "Z" to "Zebra 🦓"
                    )
                    LazyVerticalGrid(columns = GridCells.Fixed(3), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(fullABC) { (ch, w) ->
                            Card(modifier = Modifier.fillMaxWidth().height(88.dp).clickable { onSpeak("$ch for $w", "en") }, shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f))) {
                                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                    Text(ch, fontSize = 26.sp, fontWeight = FontWeight.Bold, color = Color(0xFF43A047))
                                    Text(w, fontSize = 12.sp, color = Color(0xFF37474F))
                                }
                            }
                        }
                    }
                }
                2 -> {
                    val urduDigits = listOf("۰","۱","۲","۳","۴","۵","۶","۷","۸","۹")
                    val urduWords = listOf("", "ایک", "دو", "تین", "چار", "پانچ", "چھ", "سات", "آٹھ", "نو", "دس", "گیارہ", "بارہ", "تیرہ", "چودہ", "پندرہ", "سولہ", "سترہ", "اٹھارہ", "انیس", "بیس")
                    val fullNumbers = (1..20).map { num ->
                        val uDigit = num.toString().map { urduDigits[it.digitToInt()] }.joinToString("")
                        Triple("$num / $uDigit", urduWords[num], "⭐".repeat(if (num <= 5) num else 5))
                    }
                    LazyVerticalGrid(columns = GridCells.Fixed(3), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(fullNumbers) { (digit, word, stars) ->
                            Card(modifier = Modifier.fillMaxWidth().height(92.dp).clickable { onSpeak(word, "ur") }, shape = RoundedCornerShape(14.dp), colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f))) {
                                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                                    Text(digit, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFFE65100))
                                    Text(word, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = Color(0xFF2E7D32))
                                    Text(stars, fontSize = 10.sp)
                                }
                            }
                        }
                    }
                }
                3 -> {
                    var ans by remember { mutableStateOf("") }
                    Card(modifier = Modifier.fillMaxWidth().padding(10.dp), shape = RoundedCornerShape(18.dp), colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f))) {
                        Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text("کوئز: جنگل کا بادشاہ کون ہے؟ 🦁", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(12.dp))
                            listOf("شیرو ببر شیر", "مٹھو طوطا", "بنٹی خرگوش").forEach { opt ->
                                Button(onClick = { ans = if (opt.contains("شیر")) "شاباش! بالکل درست جواب! 🎉" else "دوبارہ کوشش کریں 😊" }, modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)) { Text(opt) }
                            }
                            if (ans.isNotEmpty()) { Text(ans, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32), modifier = Modifier.padding(top = 8.dp)) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StoriesScreen(lang: AppLanguage) {
    CartoonBackground {
        var story by remember { mutableStateOf<Story?>(null) }
        if (story == null) {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(AppData.stories) { s ->
                    Card(modifier = Modifier.fillMaxWidth().clickable { story = s }, shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f))) {
                        Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                            Text(s.icon, fontSize = 34.sp)
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(if (lang == AppLanguage.URDU) s.titleUr else s.titleEn, fontSize = 17.sp, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(if (lang == AppLanguage.URDU) s.moralUr else s.moralEn, fontSize = 12.sp, color = Color(0xFF00897B))
                            }
                        }
                    }
                }
            }
        } else {
            val s = story!!
            Column(modifier = Modifier.fillMaxSize().padding(14.dp).verticalScroll(rememberScrollState())) {
                Button(onClick = { story = null }, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6F00)), shape = RoundedCornerShape(12.dp)) {
                    Text("← واپس کہانیوں پر جائیں", color = Color.White)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFFFFDE7).copy(alpha = 0.96f))) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(s.icon + " " + if (lang == AppLanguage.URDU) s.titleUr else s.titleEn, fontSize = 21.sp, fontWeight = FontWeight.Bold, color = Color(0xFFD84315))
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(if (lang == AppLanguage.URDU) s.contentUr else s.contentEn, fontSize = 17.sp, lineHeight = 28.sp, color = Color(0xFF263238))
                        Spacer(modifier = Modifier.height(14.dp))
                        Divider(color = Color(0xFFFFD54F))
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(if (lang == AppLanguage.URDU) s.moralUr else s.moralEn, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
                    }
                }
            }
        }
    }
}

@Composable
fun MemoryGameScreen(lang: AppLanguage) {
    CartoonBackground {
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
            Text(AppStrings.t("flip_cards", lang), fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFFC2185B))
            if (cards.all { it.isMatched }) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(AppStrings.t("congrats", lang), fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF388E3C))
                Button(onClick = { cards.clear(); (items + items).shuffled().forEachIndexed { i, e -> cards.add(MemoryCard(i, e)) } }) { Text(AppStrings.t("play_again", lang)) }
            }
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
}

@Composable
fun ParentSectionScreen(lang: AppLanguage) {
    CartoonBackground {
        var unlocked by remember { mutableStateOf(false) }
        var ans by remember { mutableStateOf("") }
        Column(modifier = Modifier.fillMaxSize().padding(14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            if (!unlocked) {
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f))) {
                    Column(modifier = Modifier.padding(18.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("🔒 " + AppStrings.t("parent_gate_title", lang), fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("8 + 9 = ?", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1E88E5))
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(value = ans, onValueChange = { ans = it }, singleLine = true)
                        Spacer(modifier = Modifier.height(10.dp))
                        Button(onClick = { if (ans.trim() == "17") unlocked = true }) { Text(AppStrings.t("submit", lang)) }
                    }
                }
            } else {
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f))) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("🛡️ " + AppStrings.t("privacy_policy", lang), fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(AppStrings.t("privacy_content", lang), fontSize = 14.sp, lineHeight = 22.sp)
                    }
                }
            }
        }
    }
}
