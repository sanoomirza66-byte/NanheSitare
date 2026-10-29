package com.nanhesitare.app

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

enum class Screen { SPLASH, HOME, QAIDA, ABC, GINTI, STORIES, POEMS }
data class StoryItem(val title: String, val moral: String, val content: String)
data class PoemItem(val title: String, val lyrics: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { NanheSitareApp() }
    }
}

@Composable
fun NanheSitareApp() {
    var currentScreen by remember { mutableStateOf(Screen.SPLASH) }
    var selectedStory by remember { mutableStateOf<StoryItem?>(null) }
    var selectedPoem by remember { mutableStateOf<PoemItem?>(null) }
    val toneGen = remember { try { ToneGenerator(AudioManager.STREAM_MUSIC, 90) } catch (e: Exception) { null } }

    fun playClick() { try { toneGen?.startTone(ToneGenerator.TONE_PROP_BEEP, 80) } catch (e: Exception) {} }
    fun playChime() { try { toneGen?.startTone(ToneGenerator.TONE_CDMA_KEYPAD_VOLUME_KEY_LITE, 100) } catch (e: Exception) {} }

    BackHandler(enabled = currentScreen != Screen.HOME || selectedStory != null || selectedPoem != null) {
        if (selectedStory != null) selectedStory = null
        else if (selectedPoem != null) selectedPoem = null
        else if (currentScreen != Screen.HOME) currentScreen = Screen.HOME
    }

    when (currentScreen) {
        Screen.SPLASH -> SplashScreen({ playChime() }) { currentScreen = Screen.HOME }
        Screen.HOME -> HomeScreen { scr -> playClick(); currentScreen = scr }
        Screen.QAIDA -> GridDisplay("اردو قاعدہ (الف تا ے)", getUrduQaida(), { currentScreen = Screen.HOME }) { playClick() }
        Screen.ABC -> GridDisplay("انگریزی ABC (A to Z)", getEnglishAbc(), { currentScreen = Screen.HOME }) { playClick() }
        Screen.GINTI -> GridDisplay("گنتی (1 تا 20)", getGinti(), { currentScreen = Screen.HOME }) { playClick() }
        Screen.STORIES -> StoriesScreen(selectedStory, { s -> playClick(); selectedStory = s }, { if (selectedStory != null) selectedStory = null else currentScreen = Screen.HOME })
        Screen.POEMS -> PoemsScreen(selectedPoem, { p -> playClick(); selectedPoem = p }, { if (selectedPoem != null) selectedPoem = null else currentScreen = Screen.HOME })
    }
}

@Composable
fun NatureBackground() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height
        drawRect(brush = Brush.verticalGradient(listOf(Color(0xFF81D4FA), Color(0xFFE1F5FE), Color(0xFFE8F5E9))), size = size)
        drawCircle(color = Color(0xFFFFD54F), radius = w * 0.11f, center = Offset(w * 0.85f, h * 0.09f))
        drawCircle(color = Color.White.copy(0.9f), radius = 30.dp.toPx(), center = Offset(w * 0.22f, h * 0.07f))
        
        val hill1 = Path().apply {
            moveTo(0f, h * 0.72f); quadraticBezierTo(w * 0.35f, h * 0.65f, w * 0.70f, h * 0.73f); lineTo(w, h); lineTo(0f, h); close()
        }
        drawPath(hill1, color = Color(0xFF81C784))

        val river = Path().apply {
            moveTo(w * 0.46f, h * 0.73f); quadraticBezierTo(w * 0.40f, h * 0.84f, w * 0.24f, h); lineTo(w * 0.58f, h); close()
        }
        drawPath(river, color = Color(0xFF29B6F6))

        fun drawTree(x: Float, y: Float) {
            drawRect(Color(0xFF6D4C41), Offset(x - 5.dp.toPx(), y), Size(10.dp.toPx(), 28.dp.toPx()))
            drawCircle(Color(0xFF2E7D32), 20.dp.toPx(), Offset(x, y - 8.dp.toPx()))
        }
        drawTree(w * 0.15f, h * 0.73f); drawTree(w * 0.85f, h * 0.75f)

        listOf(Offset(w * 0.1f, h * 0.88f), Offset(w * 0.75f, h * 0.88f), Offset(w * 0.9f, h * 0.92f)).forEach {
            drawCircle(Color(0xFFE91E63), 5.dp.toPx(), it)
        }
    }
}

@Composable
fun SplashScreen(playChimeSound: () -> Unit, onFinish: () -> Unit) {
    val scale = remember { Animatable(0.2f) }
    LaunchedEffect(Unit) {
        playChimeSound()
        scale.animateTo(1.25f, spring(Spring.DampingRatioMediumBouncy, Spring.StiffnessLow))
        scale.animateTo(1.0f, tween(350))
        delay(1600)
        onFinish()
    }
    Box(modifier = Modifier.fillMaxSize()) {
        NatureBackground()
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Box(modifier = Modifier.scale(scale.value).shadow(10.dp, RoundedCornerShape(28.dp)).background(Color.White.copy(0.95f), RoundedCornerShape(28.dp)).border(3.dp, Color(0xFFFFB300), RoundedCornerShape(28.dp)).padding(32.dp), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("⭐ ✨ ⭐", fontSize = 26.sp)
                    Text("ننھے ستارے", fontSize = 40.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF1B5E20))
                    Text("بچوں کی پیاری دنیا", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFFE91E63))
                }
            }
        }
    }
}

@Composable
fun HomeScreen(onNavigate: (Screen) -> Unit) {
    val menu = listOf(
        Triple("اردو قاعدہ", "مکمل الف تا ے", Screen.QAIDA),
        Triple("انگریزی ABC", "مکمل A to Z الفاظ", Screen.ABC),
        Triple("گنتی سیکھیں", "1 تا 20 اردو اور انگریزی", Screen.GINTI),
        Triple("پیاری کہانیاں", "سبق آموز بڑی کہانیاں", Screen.STORIES),
        Triple("بچوں کی نظمیں", "دلچسپ اور سریلی نظمیں", Screen.POEMS)
    )
    Box(modifier = Modifier.fillMaxSize()) {
        NatureBackground()
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(modifier = Modifier.fillMaxWidth().shadow(4.dp, RoundedCornerShape(18.dp)).background(Color.White.copy(0.92f), RoundedCornerShape(18.dp)).padding(14.dp), contentAlignment = Alignment.Center) {
                Text("ننھے ستارے - Nanhe Sitare", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
            }
            Spacer(modifier = Modifier.height(14.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(menu.size) { i ->
                    val (title, sub, scr) = menu[i]
                    Box(modifier = Modifier.fillMaxWidth().shadow(3.dp, RoundedCornerShape(16.dp)).background(Color.White.copy(0.92f), RoundedCornerShape(16.dp)).clickable { onNavigate(scr) }.padding(16.dp)) {
                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            Text("👈", fontSize = 20.sp)
                            Column(horizontalAlignment = Alignment.End) {
                                Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B5E20))
                                Text(sub, fontSize = 12.sp, color = Color.Gray)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopBar(title: String, onBack: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier.size(40.dp).shadow(3.dp, CircleShape).background(Color(0xFF2E7D32), CircleShape).clickable { onBack() }, contentAlignment = Alignment.Center) {
            Text("←", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.width(10.dp))
        Box(modifier = Modifier.weight(1f).shadow(3.dp, RoundedCornerShape(14.dp)).background(Color.White.copy(0.92f), RoundedCornerShape(14.dp)).padding(8.dp), contentAlignment = Alignment.Center) {
            Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B5E20))
        }
    }
}

@Composable
fun GridDisplay(title: String, dataList: List<Triple<String, String, String>>, onBack: () -> Unit, playTone: () -> Unit) {
    var selectedItem by remember { mutableStateOf<Triple<String, String, String>?>(null) }
    Box(modifier = Modifier.fillMaxSize()) {
        NatureBackground()
        Column(modifier = Modifier.fillMaxSize()) {
            TopBar(title, onBack)
            LazyVerticalGrid(columns = GridCells.Fixed(3), contentPadding = PaddingValues(10.dp), modifier = Modifier.fillMaxSize()) {
                items(dataList.size) { idx ->
                    val item = dataList[idx]
                    Box(modifier = Modifier.padding(4.dp).aspectRatio(1f).shadow(3.dp, RoundedCornerShape(14.dp)).background(Color.White.copy(0.93f), RoundedCornerShape(14.dp)).clickable { playTone(); selectedItem = item }, contentAlignment = Alignment.Center) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(item.first, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFFD81B60))
                            Text(item.second, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = Color(0xFF37474F))
                        }
                    }
                }
            }
        }
        selectedItem?.let { sel ->
            Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(0.45f)).clickable { selectedItem = null }, contentAlignment = Alignment.Center) {
                Box(modifier = Modifier.fillMaxWidth(0.85f).shadow(10.dp, RoundedCornerShape(20.dp)).background(Color.White, RoundedCornerShape(20.dp)).clickable(false) {}.padding(20.dp), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(sel.first, fontSize = 60.sp, fontWeight = FontWeight.Bold, color = Color(0xFFD81B60))
                        Text(sel.second, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B5E20))
                        if (sel.third.isNotEmpty()) Text(sel.third, fontSize = 15.sp, color = Color.Gray, textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(14.dp))
                        Box(modifier = Modifier.shadow(2.dp, RoundedCornerShape(10.dp)).background(Color(0xFF2E7D32), RoundedCornerShape(10.dp)).clickable { selectedItem = null }.padding(horizontal = 20.dp, vertical = 6.dp)) {
                            Text("بہت خوب (OK)", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StoriesScreen(selectedStory: StoryItem?, onSelect: (StoryItem) -> Unit, onBack: () -> Unit) {
    val stories = getLongStories()
    Box(modifier = Modifier.fillMaxSize()) {
        NatureBackground()
        if (selectedStory == null) {
            Column(modifier = Modifier.fillMaxSize()) {
                TopBar("سبق آموز کہانیاں", onBack)
                LazyColumn(modifier = Modifier.fillMaxSize().padding(12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(stories.size) { i ->
                        val story = stories[i]
                        Box(modifier = Modifier.fillMaxWidth().shadow(3.dp, RoundedCornerShape(14.dp)).background(Color.White.copy(0.92f), RoundedCornerShape(14.dp)).clickable { onSelect(story) }.padding(14.dp)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                Text("📖", fontSize = 24.sp)
                                Column(horizontalAlignment = Alignment.End) {
                                    Text(story.title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B5E20))
                                    Text("نتیجہ: " + story.moral, fontSize = 12.sp, color = Color(0xFFD81B60))
                                }
                            }
                        }
                    }
                }
            }
        } else {
            Column(modifier = Modifier.fillMaxSize()) {
                TopBar(selectedStory.title, onBack)
                Box(modifier = Modifier.fillMaxSize().padding(12.dp).shadow(4.dp, RoundedCornerShape(16.dp)).background(Color.White.copy(0.95f), RoundedCornerShape(16.dp)).padding(16.dp)) {
                    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                        Text(selectedStory.title, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32), modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(selectedStory.content, fontSize = 16.sp, lineHeight = 26.sp, color = Color(0xFF263238), textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth())
                        Spacer(modifier = Modifier.height(14.dp))
                        Box(modifier = Modifier.fillMaxWidth().background(Color(0xFFFFF3E0), RoundedCornerShape(10.dp)).padding(10.dp)) {
                            Text("💡 نتیجہ: " + selectedStory.moral, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFFE65100), textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PoemsScreen(selectedPoem: PoemItem?, onSelect: (PoemItem) -> Unit, onBack: () -> Unit) {
    val poems = getPoemsList()
    Box(modifier = Modifier.fillMaxSize()) {
        NatureBackground()
        if (selectedPoem == null) {
            Column(modifier = Modifier.fillMaxSize()) {
                TopBar("بچوں کی پیاری نظمیں", onBack)
                LazyColumn(modifier = Modifier.fillMaxSize().padding(12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    items(poems.size) { i ->
                        val poem = poems[i]
                        Box(modifier = Modifier.fillMaxWidth().shadow(3.dp, RoundedCornerShape(14.dp)).background(Color.White.copy(0.92f), RoundedCornerShape(14.dp)).clickable { onSelect(poem) }.padding(14.dp)) {
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                Text("🎵", fontSize = 24.sp)
                                Text(poem.title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B5E20))
                            }
                        }
                    }
                }
            }
        } else {
            Column(modifier = Modifier.fillMaxSize()) {
                TopBar(selectedPoem.title, onBack)
                Box(modifier = Modifier.fillMaxSize().padding(12.dp).shadow(4.dp, RoundedCornerShape(16.dp)).background(Color.White.copy(0.95f), RoundedCornerShape(16.dp)).padding(16.dp)) {
                    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(selectedPoem.title, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color(0xFFD81B60), textAlign = TextAlign.Center)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(selectedPoem.lyrics, fontSize = 17.sp, lineHeight = 30.sp, fontWeight = FontWeight.Medium, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}

fun getUrduQaida(): List<Triple<String, String, String>> = listOf(
    Triple("ا", "انار", "الف سے انار - میٹھا اور مزیدار"), Triple("ب", "بلی", "بے سے بلی"), Triple("پ", "پتنگ", "پے سے پتنگ"),
    Triple("ت", "تتلی", "تے سے تتلی"), Triple("ٹ", "ٹماٹر", "ٹے سے ٹماٹر"), Triple("ث", "ثمر", "ثے سے ثمر"),
    Triple("ج", "جہاز", "جیم سے جہاز"), Triple("چ", "چڑیا", "چے سے چڑیا"), Triple("ح", "حلوہ", "حے سے حلوہ"),
    Triple("خ", "خربوزہ", "خے سے خربوزہ"), Triple("د", "درخت", "دال سے درخت"), Triple("ڈ", "ڈول", "ڈال سے ڈول"),
    Triple("ذ", "ذخیرہ", "ذال سے ذخیرہ"), Triple("ر", "ریل", "رے سے ریل گاڑی"), Triple("ڑ", "آڑو", "ڑے سے آڑو"),
    Triple("ز", "زرافہ", "زے سے زرافہ"), Triple("ژ", "ژالہ باری", "ژے سے ژالہ باری"), Triple("س", "سیب", "سین سے سیب"),
    Triple("ش", "شیر", "شین سے شیر"), Triple("ص", "صابن", "صاد سے صابن"), Triple("ض", "ضعیف", "ضاد سے ضعیف"),
    Triple("ط", "طوطا", "طوئیں سے طوطا"), Triple("ظ", "ظروف", "ظوئیں سے برتن"), Triple("ع", "عینک", "عین سے عینک"),
    Triple("غ", "غبارہ", "غین سے غبارہ"), Triple("ف", "فوارہ", "فے سے فوارہ"), Triple("ق", "قلم", "قاف سے قلم"),
    Triple("ک", "کتاب", "کاف سے کتاب"), Triple("گ", "گائے", "گاف سے گائے"), Triple("ل", "لومڑی", "لام سے لومڑی"),
    Triple("م", "مچھلی", "میم سے مچھلی"), Triple("ن", "نل", "نون سے نل"), Triple("و", "ورق", "واؤ سے ورق"),
    Triple("ہ", "ہاتھی", "ہے سے ہاتھی"), Triple("ھ", "جھولا", "دو چشمی ھ"), Triple("ء", "چائے", "ہمزہ"),
    Triple("ی", "یکہ", "چھوٹی یے"), Triple("ے", "طوطے", "بڑی یے")
)

fun getEnglishAbc(): List<Triple<String, String, String>> = listOf(
    Triple("A", "Apple 🍎", "A for Apple"), Triple("B", "Ball ⚽", "B for Ball"), Triple("C", "Cat 🐱", "C for Cat"),
    Triple("D", "Dog 🐶", "D for Dog"), Triple("E", "Elephant 🐘", "E for Elephant"), Triple("F", "Fish 🐟", "F for Fish"),
    Triple("G", "Grapes 🍇", "G for Grapes"), Triple("H", "Horse 🐴", "H for Horse"), Triple("I", "Ice Cream 🍦", "I for Ice Cream"),
    Triple("J", "Juice 🧃", "J for Juice"), Triple("K", "Kite 🪁", "K for Kite"), Triple("L", "Lion 🦁", "L for Lion"),
    Triple("M", "Mango 🥭", "M for Mango"), Triple("N", "Nest 🪺", "N for Nest"), Triple("O", "Orange 🍊", "O for Orange"),
    Triple("P", "Parrot 🦜", "P for Parrot"), Triple("Q", "Queen 👑", "Q for Queen"), Triple("R", "Rabbit 🐰", "R for Rabbit"),
    Triple("S", "Sun ☀️", "S for Sun"), Triple("T", "Tiger 🐯", "T for Tiger"), Triple("U", "Umbrella ☂️", "U for Umbrella"),
    Triple("V", "Van 🚐", "V for Van"), Triple("W", "Watch ⌚", "W for Watch"), Triple("X", "Xylophone 🎼", "X for Xylophone"),
    Triple("Y", "Yak 🐂", "Y for Yak"), Triple("Z", "Zebra 🦓", "Z for Zebra")
)

fun getGinti(): List<Triple<String, String, String>> = (1..20).map { n ->
    val u = listOf("", "ایک", "دو", "تین", "چار", "پانچ", "چھ", "سات", "آٹھ", "نو", "دس", "گیارہ", "بارہ", "تیرہ", "چودہ", "پندرہ", "سولہ", "سترہ", "اٹھارہ", "انیس", "بیس")[n]
    val e = listOf("", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen", "Twenty")[n]
    Triple("$n", u, "$n - $e")
}

fun getLongStories(): List<StoryItem> = listOf(
    StoryItem("پیاسا کوا اور کنکر", "عقل اور محنت سے ہر مشکل آسان ہو جاتی ہے۔", "شدید گرمیوں کا زمانہ تھا۔ سورج آگ برسا رہا تھا اور جنگل کے تمام پرندے پیاس سے نڈھال تھے۔ ایک کوا پانی کی تلاش میں دور دور تک اڑتا رہا لیکن کہیں بھی پانی کا نام و نشان نہیں تھا۔\n\nکوا اڑتے اڑتے ایک باغ میں پہنچا جہاں اسے پانی کا ایک گھڑا نظر آیا۔ کوا خوش ہو کر فوراً گھڑے پر جا بیٹھا۔\n\nجب اندر دیکھا تو پانی بہت نیچے تھا اور اس کی چونچ پانی تک نہیں پہنچ سکتی تھی۔ کوے نے ہمت نہ ہاری اور سوچنے لگا۔\n\nاچانک اس کی نظر پاس پڑے کنکروں پر پڑی۔ اس نے ایک ایک کنکر چونچ سے اٹھا کر گھڑے میں ڈالنا شروع کیا۔ پانی آہستہ آہستہ اوپر آ گیا۔ کوے نے جی بھر کر پانی پیا اور اڑ گیا۔"),
    StoryItem("خرگوش اور کچھوے کی دوڑ", "غرور کا سر نیچا ہوتا ہے اور مسلسل محنت سے جیت ہوتی ہے۔", "ایک جنگل میں ایک تیز رفتار خرگوش رہتا تھا۔ اسے اپنی تیزی پر بہت غرور تھا اور وہ سست کچھوے کا مذاق اڑاتا تھا۔\n\nکچھوے نے کہا: آؤ مقابلہ کر کے دیکھ لو! اگلے دن دوڑ شروع ہوئی۔ خرگوش تیزی سے بھاگا اور بہت آگے نکل گیا۔ اس نے سوچا کچھوا تو بہت پیچھے ہے، کیوں نہ درخت کے نیچے تھوڑا آرام کر لوں؟ وہ سو گیا۔\n\nکچھوا بغیر رکے چلتا رہا اور سوئے ہوئے خرگوش سے آگے نکل کر جیت گیا۔ خرگوش جب جاگا تو شرمندہ ہو گیا۔"),
    StoryItem("شیر اور وفادار چوہا", "کسی کو چھوٹا مت سمجھو، کمزور بھی بڑے وقت کام آ سکتا ہے۔", "ایک شیر سو رہا تھا کہ ایک چوہا اس پر اچھل کود کرنے لگا۔ شیر نے غصے میں چوہے کو پکڑ لیا۔ چوہے نے معافی مانگی اور کہا شاید میں کبھی آپ کے کام آؤں۔ شیر ہنس کر اسے چھوڑ دیا۔\n\nکچھ دن بعد شکاریوں نے شیر کو جال میں قید کر لیا۔ چوہے نے شیر کی آواز سنی اور دوڑ کر آیا۔ چوہے نے تیز دانتوں سے جال کی رسیاں کتر دیں اور شیر آزاد ہو گیا۔ دونوں اچھے دوست بن گئے۔"),
    StoryItem("لالچی کتے کا سبق", "لالچ بری بلا ہے، جو ہے اس پر شکر کرو۔", "ایک کتے کو گوشت کا ایک ٹکڑا ملا۔ وہ اسے منہ میں دبا کر ندی کے پل سے گزر رہا تھا۔ ندی کے صاف پانی میں اس نے اپنا عکس دیکھا۔\n\nاس نے سمجھا کہ پانی میں دوسرا کتا ہے جس کے پاس بھی گوشت ہے۔ اس نے وہ بھی چھیننے کے لیے منہ کھول کر بھونکا، جس سے اس کا اپنا گوشت کا ٹکڑا بھی پانی میں گر کر بہہ گیا۔")
)

fun getPoemsList(): List<PoemItem> = listOf(
    PoemItem("بلبل کا بچہ", "بلبل کا بچہ، کھاتا تھا کھچڑی\nپیتا تھا پانی!\n\nگانا سناتا تھا، سب کو ہنساتا تھا\nتھا بڑا پیارا، آنکھوں کا تارا!\n\nپر جو نکل آئے، اڑ گیا پھُر سے\nبلبل کا بچہ!"),
    PoemItem("مچھلی جل کی رانی ہے", "مچھلی جل کی رانی ہے\nجیون اس کا پانی ہے!\n\nہاتھ لگاؤ تو ڈر جائے گی\nباہر نکالو تو مر جائے گی!\nپانی میں ڈالو تو تیر جائے گی!"),
    PoemItem("تتلی اڑی بس پہ چڑھی", "تتلی اڑی بس پر چڑھی\nسیٹ نہ ملی تو رونے لگی!\n\nڈرائیور نے بولا آجا میرے پاس\nتتلی بولی نا بابا نا!\nمیرا گھر ہے دور، جاؤں گی ضرور!"),
    PoemItem("Twinkle Twinkle Little Star", "Twinkle, twinkle, little star,\nHow I wonder what you are!\n\nUp above the world so high,\nLike a diamond in the sky!")
)
