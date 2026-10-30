package com.nanhesitare.app.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nanhesitare.app.data.AppData
import com.nanhesitare.app.data.StoryItem
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onSplashFinished: () -> Unit) {
    val scale = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        SoundEffect.playCheerfulChime()
        scale.animateTo(
            targetValue = 1.15f,
            animationSpec = tween(durationMillis = 900)
        )
        scale.animateTo(
            targetValue = 1.0f,
            animationSpec = tween(durationMillis = 300)
        )
        delay(1000)
        onSplashFinished()    }

    NatureBackground {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterVertizontally,
                modifier = Modifier.scale(scale.value)
            ) {
                Text(text = "⭐", fontSize = 70.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "نانھے ستارے",
                    fontSize = 46.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFD81B60)
                )
                Text(
                    text = "Nanhe Sitare",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF0D47A1)
                )
            }
        }
    }
}

@Composable
fun HomeScreen(
    onNavigateQaida: () -> Unit,
    onNavigateAbc: () -> Unit,
    onNavigateGinti: () -> Unit,
    onNavigateStories: () -> Unit
) {
    NatureBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(18.dp))
            Text(
                text = "⭐ نانھے ستارے ⭐",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF880E4F)
            )
            Text(
                text = "آؤ پڑھیں، سیکھیں اور سنیں!",
                fontSize = 18.sp,
                color = Color(0xFF2E7D32),
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(28.dp))

            MenuCard("📖 اردو قاعدہ", "حروفِ تہجی مکمل تصویروں کے ساتھ", Color(0xFFFF7043), onNavigateQaida)
            Spacer(modifier = Modifier.height(14.dp))
            MenuCard("🔤 English ABC", "Learn A to Z with Fun Words", Color(0xFF42A5F5), onNavigateAbc)
            Spacer(modifier = Modifier.height(14.dp))
            MenuCard("🔢 گنتی (Counting)", "۱ سے ۲۰ تک مکمل گنتی", Color(0xFF66BB6A), onNavigateGinti)
            Spacer(modifier = Modifier.height(14.dp))
            MenuCard("📚 دلچسپ کہانیاں", "سبق آموز اور اخلاقی بڑی کہانیاں", Color(0xFFAB47BC), onNavigateStories)
        }
    }
}

@Composable
fun MenuCard(title: String, subtitle: String, color: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.92f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = title, fontSize = 22.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = subtitle, fontSize = 13.sp, color = Color.White.copy(alpha = 0.9f))
        }
    }
}

@Composable
fun QaidaScreen(onBack: () -> Unit) {
    NatureBackground {
        Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
            TopHeader(title = "اردو قاعدہ (مکمل)", onBack = onBack)
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(bottom = 40.dp, top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(AppData.urduQaida) { item ->
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.92f)),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp).fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = item.icon, fontSize = 34.sp)
                            Text(text = item.letter, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFFD81B60))
                            Text(text = item.word, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color(0xFF455A64))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AbcScreen(onBack: () -> Unit) {
    NatureBackground {
        Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
            TopHeader(title = "English ABC (A-Z)", onBack = onBack)
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(bottom = 40.dp, top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(AppData.abcList) { item ->
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.92f)),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp).fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = item.icon, fontSize = 34.sp)
                            Text(text = item.letter, fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1976D2))
                            Text(text = item.word, fontSize = 15.sp, fontWeight = FontWeight.Medium, color = Color(0xFF37474F))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun GintiScreen(onBack: () -> Unit) {
    NatureBackground {
        Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
            TopHeader(title = "گنتی (۱ تا ۲۰)", onBack = onBack)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(bottom = 40.dp, top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(AppData.gintiList) { item ->
                    Card(
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.92f)),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp).fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertizontally) {
                                Text(text = item.number, fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "(${item.engWord})", fontSize = 16.sp, color = Color.Gray)
                            }
                            Text(text = item.urduWord, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFFE65100))
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = item.icon, fontSize = 16.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StoriesScreen(onBack: () -> Unit) {
    var selectedStory by remember { mutableStateOf<StoryItem?>(null) }

    NatureBackground {
        Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
            if (selectedStory == null) {
                TopHeader(title = "سبق آموز کہانیاں", onBack = onBack)
                LazyColumn(
                    contentPadding = PaddingValues(bottom = 40.dp, top = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(AppData.stories) { story ->
                        Card(
                            modifier = Modifier.fillMaxWidth().clickable { selectedStory = story },
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.93f)),
                            elevation = CardDefaults.cardElevation(4.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(18.dp),
                                verticalAlignment = Alignment.CenterVertizontally
                            ) {
                                Text(text = "📚", fontSize = 32.sp)
                                Spacer(modifier = Modifier.width(16.dp))
                                Column {
                                    Text(text = story.title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF4A148C))
                                    Text(text = story.moral, fontSize = 13.sp, color = Color(0xFF2E7D32))
                                }
                            }
                        }
                    }
                }
            } else {
                TopHeader(title = selectedStory!!.title, onBack = { selectedStory = null })
                Card(
                    modifier = Modifier.fillMaxSize().padding(bottom = 20.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.95f))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(18.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = selectedStory!!.fullStory,
                            fontSize = 18.sp,
                            lineHeight = 32.sp,
                            color = Color(0xFF212121),
                            textAlign = TextAlign.Right
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFFE8F5E9), shape = RoundedCornerShape(10.dp))
                                .padding(12.dp)
                        ) {
                            Text(
                                text = selectedStory!!.moral,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1B5E20),
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TopHeader(title: String, onBack: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertizontally
    ) {
        IconButton(onClick = onBack) {
            Text(text = "⬅️", fontSize = 24.sp)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0D47A1)
        )
    }
}
