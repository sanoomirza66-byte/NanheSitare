package com.nanhesitare.app

import android.media.AudioManager
import android.media.ToneGenerator
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

data class StoryItem(val title: String, val pages: List<String>)
enum class Screen { SPLASH, HOME, QAIDA, ABC, GINTI, STORIES, STORY_DETAIL }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { NanheSitareApp() }
    }
}

@Composable
fun NatureBackground() {
    Canvas(Modifier.fillMaxSize()) {
        val w = size.width; val h = size.height
        drawRect(Color(0xFF81D4FA))
        drawCircle(Color(0xFFFFEB3B), w * 0.1f, Offset(w * 0.85f, h * 0.12f))
        drawRect(Color(0xFF81C784), topLeft = Offset(0f, h * 0.45f), size = Size(w, h * 0.55f))
        drawRect(Color(0xFF4FC3F7), topLeft = Offset(w * 0.4f, h * 0.45f), size = Size(w * 0.2f, h * 0.55f))
        drawRect(Color(0xFF795548), topLeft = Offset(w * 0.08f, h * 0.42f), size = Size(20f, 60f))
        drawCircle(Color(0xFF388E3C), 40f, Offset(w * 0.08f + 10f, h * 0.40f))
        drawRect(Color(0xFF795548), topLeft = Offset(w * 0.85f, h * 0.42f), size = Size(20f, 60f))
        drawCircle(Color(0xFF388E3C), 40f, Offset(w * 0.85f + 10f, h * 0.40f))
        drawCircle(Color(0xFFE91E63), 10f, Offset(w * 0.2f, h * 0.7f))
        drawCircle(Color(0xFFFF9800), 10f, Offset(w * 0.75f, h * 0.75f))
    }
}

@Composable
fun TopBar(title: String, onBack: () -> Unit) {
    Row(Modifier.fillMaxWidth().statusBarsPadding().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
        Button(onClick = onBack, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))) {
            Text("⬅ واپس", color = Color.White)
        }
        Spacer(Modifier.width(10.dp))
        Card(shape = RoundedCornerShape(10.dp), modifier = Modifier.weight(1f)) {
            Text(title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B5E20), modifier = Modifier.padding(8.dp).fillMaxWidth(), textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun GridDisplay(title: String, items: List<Pair<String, String>>, onBack: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        TopBar(title, onBack)
        LazyVerticalGrid(GridCells.Fixed(3), contentPadding = PaddingValues(10.dp), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(items) { item ->
                Card(shape = RoundedCornerShape(12.dp), colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.92f))) {
                    Column(Modifier.padding(8.dp).fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(item.first, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1976D2))
                        Text(item.second, fontSize = 13.sp, color = Color(0xFF388E3C), fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
    }
}

@Composable
fun SplashScreen(onFinish: () -> Unit) {
    val scale = remember { Animatable(0f) }
    LaunchedEffect(Unit) {
        try { ToneGenerator(AudioManager.STREAM_MUSIC, 100).startTone(ToneGenerator.TONE_PROP_BEEP2, 400) } catch (_: Exception) {}
        scale.animateTo(1.2f, spring(Spring.DampingRatioMediumBouncy))
        scale.animateTo(1.0f, tween(150))
        delay(1200); onFinish()
    }
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.scale(scale.value)) {
            Text("⭐", fontSize = 80.sp)
            Spacer(Modifier.height(10.dp))
            Card(shape = RoundedCornerShape(16.dp)) {
                Text("نन्हे ستارے", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B5E20), modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun HomeScreen(onNavigate: (Screen) -> Unit) {
    Column(Modifier.fillMaxSize().statusBarsPadding().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Card(shape = RoundedCornerShape(16.dp), modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
            Text("🌟 نन्हे ستارے 🌟", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32), modifier = Modifier.padding(14.dp).fillMaxWidth(), textAlign = TextAlign.Center)
        }
        val menu = listOf(Triple("مکمل اردو قاعدہ", "📗", Screen.QAIDA), Triple("English ABC", "🔤", Screen.ABC), Triple("گنتی (1 تا 20)", "🔢", Screen.GINTI), Triple("کہانیاں", "📚", Screen.STORIES))
        LazyVerticalGrid(GridCells.Fixed(2), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(menu) { m ->
                Card(Modifier.height(120.dp).clickable { onNavigate(m.third) }, shape = RoundedCornerShape(16.dp)) {
                    Column(Modifier.fillMaxSize().padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Text(m.second, fontSize = 36.sp)
                        Text(m.first, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B5E20))
                    }
                }
            }
        }
    }
}

@Composable
fun StoryDetailScreen(story: StoryItem, onBack: () -> Unit) {
    var p by remember { mutableIntStateOf(0) }
    Column(Modifier.fillMaxSize()) {
        TopBar(story.title, onBack)
        Card(Modifier.fillMaxSize().padding(12.dp), shape = RoundedCornerShape(16.dp)) {
            Column(Modifier.padding(16.dp).fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {
                Column {
                    Text("صفحہ ${p + 1} از ${story.pages.size}", color = Color.Gray, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(10.dp))
                    Text(story.pages[p], fontSize = 17.sp, lineHeight = 28.sp, textAlign = TextAlign.Right)
                }
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(onClick = { if (p > 0) p-- }, enabled = p > 0) { Text("پچھلا") }
                    Button(onClick = { if (p < story.pages.size - 1) p++ }, enabled = p < story.pages.size - 1) { Text("اگلا") }
                }
            }
        }
    }
}

@Composable
fun NanheSitareApp() {
    var screen by remember { mutableStateOf(Screen.SPLASH) }
    var story by remember { mutableStateOf<StoryItem?>(null) }
    BackHandler(enabled = screen != Screen.HOME && screen != Screen.SPLASH) {
        screen = if (screen == Screen.STORY_DETAIL) Screen.STORIES else Screen.HOME
    }

    val qaida = remember { listOf("آ آم","ا انار","ب بلی","پ پتنگ","ت تتلی","ٹ ٹماٹر","ث ثمر","ج جہاز","چ چڑیا","ح حلوہ","خ خرگوش","د درخت","ڈ ڈولفن","ذ ذخیرہ","ر ریل","ڑ پہاڑ","ز زرافہ","ژ ژالہ","س سیب","ش شیر","ص صوفہ","ض ضعیف","ط طوطا","ظ ظروف","ع عینک","غ غبارہ","ف فوارہ","ق قلم","ک کتاب","گ گائے","ل لومڑی","م مور","ن نلکا","و ورزش","ہ ہاتھی","ء ہمزہ","ی یکہ").map { val s = it.split(" "); Pair(s[0], s[1]) } }
    val abc = remember { listOf("Apple","Ball","Cat","Dog","Elephant","Fish","Grapes","Horse","Ice Cream","Jug","Kite","Lion","Mango","Nest","Orange","Parrot","Queen","Rabbit","Sun","Tiger","Umbrella","Van","Watch","Xylophone","Yak","Zebra").map { Pair(it.first().toString(), it) } }
    val ginti = remember { val u = listOf("ایک","دو","تین","چار","پانچ","چھ","سات","آٹھ","نو","دس","گیارہ","بارہ","تیرہ","چودہ","پندرہ","سولہ","سترہ","اٹھارہ","انیس","بیس"); (1..20).map { Pair("$it", u[it-1]) } }
    val stories = remember { listOf(
        StoryItem("خرگوش اور کچھی کی دوڑ", listOf("ایک جنگل میں تیز رفتار خرگوش اور ایک کچھی رہتے تھے۔ خرگوش کو اپنی رفتار پر بڑا غرور تھا اور وہ کچھی کا مذاق اڑاتا تھا۔ کچھی نے تنگ آ کر خرگوش کو دوڑ کا مقابلہ کرنے کا کہا۔ دوڑ شروع ہوئی تو خرگوش تیزی سے بھاگ کر بہت آگے نکل گیا۔", "خرگوش نے سوچا کہ کچھی بہت پیچھے ہے، کیوں نہ درخت کے نیچے تھوڑی دیر سو لوں۔ وہ سو گیا مگر کچھی لگاتار چلتا رہا اور چوٹی پر پہنچ گیا۔ خرگوش جاگا تو کچھی جیت چکا تھا!\n\nسبق: غرور کا سر نیچا ہوتا ہے، مستقل محنت سے کامیابی ملتی ہے۔")),
        StoryItem("پیاسا کوا اور عقلمندی", listOf("سخت گرمی تھی اور ایک کوے کو پیاس لگی تھی۔ وہ پانی کی تلاش میں اڑتا رہا۔ آخر کار اسے باغ میں ایک مٹی کا گھڑا ملا۔ وہ فورا گھڑے پر بیٹھا تاکہ پانی پی سکے۔", "گھڑے میں پانی بہت تھوڑا تھا اور چونچ نیچے نہیں پہنچ پا رہی تھی۔ کوے نے عقل لڑائی اور آس پاس سے کنکر اٹھا کر گھڑے میں ڈالنے شروع کیے۔ پانی اوپر آ گیا، کوے نے پیٹ بھر کر پانی پیا اور اڑ گیا!\n\nسبق: جہاں چاہ وہاں راہ، عقل سے ہر مشکل آسان ہو جاتی ہے۔"))
    )}

    Box(Modifier.fillMaxSize()) {
        NatureBackground()
        when (screen) {
            Screen.SPLASH -> SplashScreen { screen = Screen.HOME }
            Screen.HOME -> HomeScreen { screen = it }
            Screen.QAIDA -> GridDisplay("مکمل اردو قاعدہ (37 حروف)", qaida) { screen = Screen.HOME }
            Screen.ABC -> GridDisplay("English ABC (A-Z)", abc) { screen = Screen.HOME }
            Screen.GINTI -> GridDisplay("گنتی (1 تا 20)", ginti) { screen = Screen.HOME }
            Screen.STORIES -> {
                Column(Modifier.fillMaxSize()) {
                    TopBar("کہانیاں", onBack = { screen = Screen.HOME })
                    stories.forEach { s ->
                        Card(Modifier.fillMaxWidth().padding(10.dp).clickable { story = s; screen = Screen.STORY_DETAIL }, shape = RoundedCornerShape(14.dp)) {
                            Text(s.title + " 📖", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1B5E20), modifier = Modifier.padding(16.dp))
                        }
                    }
                }
            }
            Screen.STORY_DETAIL -> story?.let { StoryDetailScreen(it) { screen = Screen.STORIES } }
        }
    }
}
