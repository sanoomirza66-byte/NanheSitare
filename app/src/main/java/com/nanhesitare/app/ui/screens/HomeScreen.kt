package com.nanhesitare.app.ui.screens
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nanhesitare.app.Screen
import com.nanhesitare.app.data.AppLanguage
import com.nanhesitare.app.data.AppStrings

@Composable
fun HomeScreen(lang: AppLanguage, onNavigate: (Screen) -> Unit) {
    val items = listOf(
        Triple("drawing_studio", "🎨", Screen.DRAWING),
        Triple("coloring_book", "🖍️", Screen.COLORING),
        Triple("cartoon_friends", "🦁", Screen.FRIENDS),
        Triple("learning", "📚", Screen.LEARNING),
        Triple("stories", "📖", Screen.STORIES),
        Triple("games", "🧩", Screen.GAMES),
        Triple("parent_section", "🛡️", Screen.PARENT)
    )
    val colors = listOf(
        Color(0xFFFF5252), Color(0xFF448AFF), Color(0xFFFFAB00),
        Color(0xFF00C853), Color(0xFFAA00FF), Color(0xFFFF4081), Color(0xFF607D8B)
    )

    Column(modifier = Modifier.fillMaxSize().padding(14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(22.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF3E0))) {
            Column(modifier = Modifier.padding(14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("🌟 " + AppStrings.t("app_name", lang) + " 🌟", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(0xFFE65100))
                Text(AppStrings.t("tagline", lang), fontSize = 16.sp, color = Color(0xFF5D4037))
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    listOf("🦁", "🐰", "🐼", "🦜", "🐱", "🐘").forEach { Text(it, fontSize = 32.sp) }
                }
            }
        }
        Spacer(modifier = Modifier.height(14.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(2), horizontalArrangement = Arrangement.spacedBy(12.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(items) { item ->
                val bg = colors[items.indexOf(item) % colors.size]
                Card(
                    modifier = Modifier.fillMaxWidth().height(120.dp).clickable { onNavigate(item.third) },
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = bg),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(modifier = Modifier.fillMaxSize().padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        Text(item.second, fontSize = 38.sp)
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(AppStrings.t(item.first, lang), fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}
