package com.nanhesitare.app

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.concurrent.thread
import kotlin.math.sin

enum class AppScreen {
    SPLASH, HOME, URDU, ABC, GINTI, STORIES, STORY_DETAIL
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NanheSitareKidsApp()
        }
    }
}

// Bachon jesi khubsurat magical bell sound generate karne ka function
fun playKidsBellSound() {
    thread {
        try {
            val sampleRate = 44100
            val frequencies = doubleArrayOf(523.25, 659.25, 783.99, 1046.50) // C, E, G, C (Joyful Chime)
            val durationPerNote = 0.15
            val totalSamples = (sampleRate * durationPerNote * frequencies.size).toInt()
            val audioBuffer = ShortArray(totalSamples)

            var bufferIdx = 0
            for (freq in frequencies) {
                val noteSamples = (sampleRate * durationPerNote).toInt()
                for (i in 0 until noteSamples) {
                    val time = i.toDouble() / sampleRate
                    val decay = 1.0 - (i.toDouble() / noteSamples)
                    val sinVal = sin(2.0 * Math.PI * freq * time) * decay
                    audioBuffer[bufferIdx++] = (sinVal * Short.MAX_VALUE * 0.7).toInt().toShort()
                }
            }

            val track = AudioTrack.Builder()
                .setAudioAttributes(
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_GAME)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build()
                )
                .setAudioFormat(
                    AudioFormat.Builder()
                        .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                        .setSampleRate(sampleRate)
                        .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                        .build()
                )
                .setBufferSizeInBytes(totalSamples * 2)
                .setTransferMode(AudioTrack.MODE_STATIC)
                .build()

            track.write(audioBuffer, 0, totalSamples)
            track.play()
        } catch (_: Exception) {}
    }
}

@Composable
fun NanheSitareKidsApp() {
    var currentScreen by remember { mutableStateOf(AppScreen.SPLASH) }
    var selectedStory by remember { mutableStateOf<StoryItem?>(null) }

    // BACK BUTTON FIX: App band hone ke bajaye peechay jaye
    BackHandler(enabled = currentScreen != AppScreen.SPLASH) {
        when (currentScreen) {
            AppScreen.STORY_DETAIL -> currentScreen = AppScreen.STORIES
            AppScreen.HOME -> {
                // Home par back dabane se close ho sakti he
                android.os.Process.killProcess(android.os.Process.myPid())
            }
            else -> currentScreen = AppScreen.HOME
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // Har page ke peechay Nature Background (Trees, Nehar, Phool)
        Image(
            painter = painterResource(id = R.drawable.bg_nature),
            contentDescription = "Nature Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        when (currentScreen) {
            AppScreen.SPLASH -> {
                SplashScreen {
                    currentScreen = AppScreen.HOME
                }
            }
            AppScreen.HOME -> {
                HomeScreen(
                    onUrduClick = { currentScreen = AppScreen.URDU },
                    onAbcClick = { currentScreen = AppScreen.ABC },
                    onGintiClick = { currentScreen = AppScreen.GINTI },
                    onStoriesClick = { currentScreen = AppScreen.STORIES }
                )
            }
            AppScreen.URDU -> {
                LearningGridScreen("اردو قاعدہ", AppData.urduQaida) { currentScreen = AppScreen.HOME }
            }
            AppScreen.ABC -> {
                LearningGridScreen("English ABC", AppData.englishAbc) { currentScreen = AppScreen.HOME }
            }
            AppScreen.GINTI -> {
                LearningGridScreen("گنتی (Numbers)", AppData.ginti) { currentScreen = AppScreen.HOME }
            }
            AppScreen.STORIES -> {
                StoriesListScreen(AppData.stories, onBack = { currentScreen = AppScreen.HOME }) { story ->
                    selectedStory = story
                    currentScreen = AppScreen.STORY_DETAIL
                }
            }
            AppScreen.STORY_DETAIL -> {
                selectedStory?.let { story ->
                    StoryDetailScreen(story) { currentScreen = AppScreen.STORIES }
                }
            }
        }
    }
}

// SPLASH SCREEN: Nanhe Sitare ek dam Zoom in hoga aur bachon ki awaz ayegi
@Composable
fun SplashScreen(onFinish: () -> Unit) {
    var startAnim by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (startAnim) 1.2f else 0.1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "zoomAnim"
    )

    LaunchedEffect(Unit) {
        startAnim = true
        playKidsBellSound() // Kids sparkle/bell sound
        delay(2200)
        onFinish()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0x33000000)),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .scale(scale)
                .padding(24.dp),
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f)),
            elevation = CardDefaults.cardElevation(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("⭐", fontSize = 64.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    "Nanhe Sitare",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFFFF6F00)
                )
                Text(
                    "ننھے ستارے",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1976D2)
                )
            }
        }
    }
}

// HOME SCREEN
@Composable
fun HomeScreen(
    onUrduClick: () -> Unit,
    onAbcClick: () -> Unit,
    onGintiClick: () -> Unit,
    onStoriesClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(30.dp))
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
        ) {
            Text(
                "🌟 ننھے ستارے - Nanhe Sitare 🌟",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFD84315),
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        CategoryCard("اردو قاعدہ (Urdu)", "آ، ا، ب، پ، ت...", Color(0xFFFF7043), "📖", onUrduClick)
        Spacer(modifier = Modifier.height(16.dp))
        CategoryCard("English ABC", "A, B, C, D, E...", Color(0xFF42A5F5), "🔤", onAbcClick)
        Spacer(modifier = Modifier.height(16.dp))
        CategoryCard("گنتی (Numbers)", "1, 2, 3 / ۱, ۲, ۳...", Color(0xFF66BB6A), "🔢", onGintiClick)
        Spacer(modifier = Modifier.height(16.dp))
        CategoryCard("سبق آموز کہانیاں (Stories)", "بچوں کی 2 صفحات کی کہانیاں", Color(0xFFAB47BC), "📚", onStoriesClick)
    }
}

@Composable
fun CategoryCard(title: String, sub: String, color: Color, icon: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(95.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(icon, fontSize = 42.sp)
            Spacer(modifier = Modifier.width(18.dp))
            Column {
                Text(title, fontSize = 21.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text(sub, fontSize = 14.sp, color = Color.White.copy(alpha = 0.9f))
            }
        }
    }
}

// LEARNING GRID (Urdu, ABC, Ginti)
@Composable
fun LearningGridScreen(title: String, items: List<LearnItem>, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
        TopBar(title, onBack)
        Spacer(modifier = Modifier.height(8.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(items) { item ->
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.92f)),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(item.icon, fontSize = 38.sp)
                        Text(item.title, fontSize = 26.sp, fontWeight = FontWeight.ExtraBold, color = Color(0xFF1E88E5))
                        Text(item.subTitle, fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF424242))
                    }
                }
            }
        }
    }
}

// STORIES LIST
@Composable
fun StoriesListScreen(stories: List<StoryItem>, onBack: () -> Unit, onSelect: (StoryItem) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TopBar("بچوں کی کہانیاں", onBack)
        Spacer(modifier = Modifier.height(12.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            items(stories) { story ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onSelect(story) },
                    shape = RoundedCornerShape(18.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.92f)),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Row(modifier = Modifier.padding(18.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text("📖", fontSize = 36.sp)
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(story.title, fontSize = 19.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6A1B9A))
                            Text("صفحات: 2 (مکمل کہانی)", fontSize = 13.sp, color = Color.Gray)
                        }
                    }
                }
            }
        }
    }
}

// 2-PAGE STORY DETAIL WITH NEXT/PREV
@Composable
fun StoryDetailScreen(story: StoryItem, onBack: () -> Unit) {
    var currentPage by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        TopBar(story.title, onBack)
        Spacer(modifier = Modifier.height(10.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f)),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("صفحہ نمبر: ${currentPage + 1} / 2", fontWeight = FontWeight.Bold, color = Color.Gray)
                    Text("🌟", fontSize = 20.sp)
                }
                Divider(modifier = Modifier.padding(vertical = 10.dp))

                Text(
                    text = story.pages[currentPage].pageContent,
                    fontSize = 18.sp,
                    lineHeight = 30.sp,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.weight(1f)
                )

                if (currentPage == 1) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF9C4)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                    ) {
                        Text(
                            text = story.moral,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFE65100),
                            fontSize = 15.sp,
                            modifier = Modifier.padding(10.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { if (currentPage > 0) currentPage-- },
                enabled = currentPage > 0,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF9800))
            ) {
                Text("⬅️ پچھلا صفحہ")
            }

            Button(
                onClick = { if (currentPage < 1) currentPage++ },
                enabled = currentPage < 1,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text("اگلا صفحہ ➡️")
            }
        }
    }
}

@Composable
fun TopBar(title: String, onBack: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.Black)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF263238))
        }
    }
}
