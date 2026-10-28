import os

files = {}

files["app/src/main/java/com/nanhesitare/app/data/Models.kt"] = """package com.nanhesitare.app.data
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

enum class AppLanguage { URDU, ENGLISH }

data class DrawPath(val path: Path, val color: Color, val strokeWidth: Float, val isEraser: Boolean = false)
data class CartoonAnimal(val id: String, val urduName: String, val englishName: String, val emoji: String, val soundDesc: String, val soundDescEn: String, val speechTextUr: String, val speechTextEn: String, val funFactUr: String, val funFactEn: String, val themeColor: Color)
data class Story(val id: Int, val titleUr: String, val titleEn: String, val icon: String, val moralUr: String, val moralEn: String, val contentUr: String, val contentEn: String)
data class MemoryCard(val id: Int, val content: String, val isFlipped: Boolean = false, val isMatched: Boolean = false)

object AppStrings {
    fun t(key: String, lang: AppLanguage): String {
        return if (lang == AppLanguage.URDU) urduStrings[key] ?: key else englishStrings[key] ?: key
    }
    private val urduStrings = mapOf(
        "app_name" to "ننھے ستارے",
        "tagline" to "کھیلو، سیکھو اور مسکراؤ!",
        "drawing_studio" to "ڈرائنگ اسٹوڈیو 🎨",
        "coloring_book" to "رنگ بھریں 🖍️",
        "cartoon_friends" to "کارٹون دوست 🦁",
        "learning" to "تعلیم و تربیت 📚",
        "stories" to "پیاری کہانیاں 📖",
        "games" to "ذہنی کھیل 🧩",
        "parent_section" to "والدین کے لیے 🛡️",
        "clear" to "صاف کریں", "undo" to "واپس", "redo" to "آگے",
        "save" to "محفوظ کریں", "eraser" to "مٹائیں", "brush_size" to "برش سائز",
        "saved_msg" to "تصویر گیلری میں محفوظ ہو گئی! 🎉",
        "confirm_clear" to "کیا آپ صفحہ صاف کرنا چاہتے ہیں؟", "yes" to "جی ہاں", "no" to "نہیں",
        "urdu_letters" to "اردو حروف", "english_letters" to "انگریزی ABC",
        "numbers" to "گنتی (1-20)", "shapes" to "شکلیں", "colors" to "رنگ", "quiz" to "کوئز",
        "parent_gate_title" to "والدین کا گیٹ", "parent_gate_desc" to "آگے بڑھنے کے لیے سوال حل کریں:",
        "submit" to "تصدیق کریں", "privacy_policy" to "پرائیویسی پالیسی",
        "privacy_content" to "ننھے ستارے ایپ بچوں کا کوئی بھی ذاتی ڈیٹا، نام یا لوکیشن جمع نہیں کرتی۔ یہ مکمل آف لائن ہے۔",
        "flip_cards" to "کارڈ ملاؤ کھیل", "play_again" to "دوبارہ کھیلیں", "congrats" to "شاباش! جیت گئے! 🌟"
    )
    private val englishStrings = mapOf(
        "app_name" to "Nanhe Sitare",
        "tagline" to "Play, Learn and Shine!",
        "drawing_studio" to "Drawing Studio 🎨",
        "coloring_book" to "Coloring Book 🖍️",
        "cartoon_friends" to "Cartoon Friends 🦁",
        "learning" to "Learning Fun 📚",
        "stories" to "Stories 📖",
        "games" to "Brain Games 🧩",
        "parent_section" to "Parents Corner 🛡️",
        "clear" to "Clear", "undo" to "Undo", "redo" to "Redo",
        "save" to "Save", "eraser" to "Eraser", "brush_size" to "Brush Size",
        "saved_msg" to "Saved! 🎉", "confirm_clear" to "Clear canvas?",
        "yes" to "Yes", "no" to "No",
        "urdu_letters" to "Urdu", "english_letters" to "ABC",
        "numbers" to "1-20", "shapes" to "Shapes", "colors" to "Colors", "quiz" to "Quiz",
        "parent_gate_title" to "Parental Gate", "parent_gate_desc" to "Solve to continue:",
        "submit" to "Verify", "privacy_policy" to "Privacy Policy",
        "privacy_content" to "Nanhe Sitare does not collect personal data. It is 100% offline and safe.",
        "flip_cards" to "Memory Match Game", "play_again" to "Play Again", "congrats" to "Bravo! You Won! 🌟"
    )
}

object AppData {
    val animals = listOf(
        CartoonAnimal("lion", "ببر شیر (شیرو)", "Sheru the Lion", "🦁", "دھاڑتا ہے: گرارر!", "Roars: Roaaar!", "میں شیرو ہوں، جنگل کا بہادر بادشاہ!", "I am Sheru the brave lion!", "شیر دن میں آرام کرتا ہے اور شام کو دوڑتا ہے۔", "Lions rest during the day.", Color(0xFFFFB74D)),
        CartoonAnimal("rabbit", "خرگوش (بنٹی)", "Bunty Rabbit", "🐰", "اچھلتا ہے: تھپ تھپ!", "Hops: Hop hop!", "ہیلو دوستو! میں بنٹی خرگوش ہوں۔ میٹھی گاجر کھاؤں!", "I am Bunty rabbit. I love sweet carrots!", "خرگوش کے کان بہت تیز سنتے ہیں۔", "Rabbits have very sharp ears.", Color(0xFFF8BBD0)),
        CartoonAnimal("panda", "پانڈا (پپو)", "Pappu Panda", "🐼", "چبواتا ہے: کرنچ!", "Munches: Crunch!", "آداب! میں پپو پانڈا ہوں۔ بانس کھانا مجھے پسند ہے!", "Greetings! I am Pappu Panda. I love bamboo!", "پانڈا سارا دن ہرا بانس کھاتا ہے۔", "Pandas love fresh green bamboo.", Color(0xFFCFD8DC)),
        CartoonAnimal("parrot", "طوطا (مٹھو)", "Mitthu Parrot", "🦜", "بولتا ہے: ٹیں ٹیں!", "Chirps: Squawk!", "میاں مٹھو چوری کھاؤ! میں سبز طوطا مٹھو ہوں!", "I am Mitthu the talking green parrot!", "طوطے انسانوں کی طرح بول سکتے ہیں۔", "Parrots can talk.", Color(0xFFA5D6A7)),
        CartoonAnimal("cat", "بلی (مانو)", "Mano the Cat", "🐱", "بولتی ہے: میاؤں!", "Meows: Meow meow!", "پیاری مانو بلی، دودھ کی پیالی پی کر میاؤں کہتی ہوں!", "I am Mano the kitten. I love milk!", "بلی اندھیرے میں بھی صاف دیکھ سکتی ہے۔", "Cats have super sharp vision.", Color(0xFFFFF59D)),
        CartoonAnimal("elephant", "ہاتھی (جمبو)", "Jumbo Elephant", "🐘", "چنگھاڑتا ہے: پاں پاں!", "Trumpets: Pawooo!", "میں جمبو ہاتھی ہوں۔ میری لمبی سونڈ ہے!", "Hello! I am Jumbo the gentle elephant!", "ہاتھی بہت پیارا اور رحم دل ہوتا ہے۔", "Elephants are kind animals.", Color(0xFF90CAF9))
    )

    val stories = listOf(
        Story(1, "۱. کچھوا اور خرگوش کی دوڑ", "1. Tortoise and Hare", "🐢🐰", "سبق: مستقل مزاجی سے کامیابی ملتی ہے۔", "Moral: Slow and steady wins.", "ایک جنگل میں خرگوش اور کچھوے کی دوڑ لگی۔ خرگوش راستے میں سو گیا جبکہ کچھوا چلتا رہا اور جیت گیا!", "The slow tortoise kept walking and beat the sleeping hare!"),
        Story(2, "۲. عقلمند پیاسا کوا", "2. Thirsty Crow", "🦅💧", "سبق: عقل سے ہر مشکل آسان ہوتی ہے۔", "Moral: Where there is a will, there is a way.", "پیاسے کوے نے گھڑے میں کنکر ڈال کر پانی اوپر لایا اور پیاس بجھائی۔", "A clever crow dropped pebbles into a pitcher to raise the water level."),
        Story(3, "۳. ننھی چیونٹی اور کبوتر", "3. Ant and Dove", "🐜🕊️", "سبق: نیکی کا بدلہ نیکی ہے۔", "Moral: Kindness is rewarded.", "کبوتر نے چیونٹی کو ڈوبنے سے بچایا اور چیونٹی نے شکاری کو کاٹ کر کبوتر کی جان بچائی۔", "A dove saved an ant, and later the ant bit a hunter to save the dove."),
        Story(4, "۴. سچا چرواہا", "4. Honest Shepherd", "🐑🧒", "سبق: سچ میں برکت اور جھوٹ میں تباہی ہے۔", "Moral: Honesty is the best policy.", "ایک لڑکا مذاق میں بھیڑیا آیا کا جھوٹ بولتا تھا۔ جب سچ میں بھیڑیا آیا تو کسی نے یقین نہ کیا اور نقصان ہو گیا۔", "A boy lied about a wolf. When a real wolf came, no one helped him."),
        Story(5, "۵. مٹھو طوطے کی ہوشیاری", "5. Wise Mitthu", "🦜🌳", "سبق: اتحاد میں طاقت ہے۔", "Moral: Unity is strength.", "طوطے جال میں پھنس گئے تو مٹھو کے کہنے پر سب مل کر پورا جال لے کر اڑ گئے۔", "A flock of parrots flew together with the net and escaped.")
    )
}
"""

files["app/src/main/java/com/nanhesitare/app/MainActivity.kt"] = """package com.nanhesitare.app
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nanhesitare.app.data.AppLanguage
import com.nanhesitare.app.data.AppStrings
import com.nanhesitare.app.ui.screens.*
import java.util.Locale

enum class Screen { HOME, DRAWING, COLORING, FRIENDS, LEARNING, STORIES, GAMES, PARENT }

class MainActivity : ComponentActivity(), TextToSpeech.OnInitListener {
    private var tts: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tts = TextToSpeech(this, this)
        setContent {
            var currentScreen by remember { mutableStateOf(Screen.HOME) }
            var currentLanguage by remember { mutableStateOf(AppLanguage.URDU) }
            val direction = if (currentLanguage == AppLanguage.URDU) LayoutDirection.Rtl else LayoutDirection.Ltr

            CompositionLocalProvider(LocalLayoutDirection provides direction) {
                Surface(modifier = Modifier.fillMaxSize(), color = Color(0xFFFFF9E6)) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        Row(
                            modifier = Modifier.fillMaxWidth().background(Color(0xFFFF6F00)).padding(horizontal = 14.dp, vertical = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            if (currentScreen != Screen.HOME) {
                                Button(
                                    onClick = { currentScreen = Screen.HOME },
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    Text("← ہوم", color = Color(0xFFFF6F00), fontSize = 16.sp)
                                }
                            } else {
                                Text("⭐ " + AppStrings.t("app_name", currentLanguage), color = Color.White, fontSize = 22.sp)
                            }
                            Button(
                                onClick = { currentLanguage = if (currentLanguage == AppLanguage.URDU) AppLanguage.ENGLISH else AppLanguage.URDU },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFD54F)),
                                shape = RoundedCornerShape(18.dp)
                            ) {
                                Text(if (currentLanguage == AppLanguage.URDU) "English 🌐" else "اردو 🇵🇰", color = Color.Black)
                            }
                        }

                        Box(modifier = Modifier.weight(1f)) {
                            when (currentScreen) {
                                Screen.HOME -> HomeScreen(currentLanguage) { currentScreen = it }
                                Screen.DRAWING -> DrawingStudioScreen(currentLanguage)
                                Screen.COLORING -> ColoringBookScreen(currentLanguage)
                                Screen.FRIENDS -> CartoonFriendsScreen(currentLanguage) { t, l -> speakOut(t, l) }
                                Screen.LEARNING -> LearningScreen(currentLanguage) { t, l -> speakOut(t, l) }
                                Screen.STORIES -> StoriesScreen(currentLanguage)
                                Screen.GAMES -> MemoryGameScreen(currentLanguage)
                                Screen.PARENT -> ParentSectionScreen(currentLanguage)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) tts?.language = Locale("ur", "PK")
    }

    private fun speakOut(text: String, langCode: String) {
        tts?.language = if (langCode == "ur") Locale("ur", "PK") else Locale.US
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "NanheSitare")
    }

    override fun onDestroy() {
        tts?.stop()
        tts?.shutdown()
        super.onDestroy()
    }
}
"""

files["app/src/main/java/com/nanhesitare/app/ui/screens/HomeScreen.kt"] = """package com.nanhesitare.app.ui.screens
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
"""

for path, code in files.items():
    folder = os.path.dirname(path)
    if folder:
        os.makedirs(folder, exist_ok=True)
    with open(path, "w", encoding="utf-8") as f:
        f.write(code)

print("PART 1 READY!")
