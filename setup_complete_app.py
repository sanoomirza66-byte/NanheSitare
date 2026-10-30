import os

# 1. Directories ensure karen
dirs = [
    "app/src/main/res/drawable",
    "app/src/main/res/mipmap-anydpi-v26",
    "app/src/main/res/values",
    "app/src/main/java/com/nanhesitare/app",
    "app/src/main/java/com/nanhesitare/app/ui",
    "app/src/main/java/com/nanhesitare/app/data"
]
for d in dirs:
    os.makedirs(d, exist_ok=True)

# 2. App Icon Background & Foreground (Adaptive Icon)
with open("app/src/main/res/drawable/ic_launcher_background.xml", "w", encoding="utf-8") as f:
    f.write('''<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="108dp"
    android:height="108dp"
    android:viewportWidth="108"
    android:viewportHeight="108">
    <path
        android:fillColor="#4FC3F7"
        android:pathData="M0,0h108v108h-108z"/>
    <path
        android:fillColor="#81C784"
        android:pathData="M0,80 Q54,60 108,80 L108,108 L0,108 Z"/>
</vector>''')

with open("app/src/main/res/drawable/ic_launcher_foreground.xml", "w", encoding="utf-8") as f:
    f.write('''<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="108dp"
    android:height="108dp"
    android:viewportWidth="108"
    android:viewportHeight="108">
    <!-- Bright Golden Star for Nanhe Sitare -->
    <path
        android:fillColor="#FFD54F"
        android:strokeColor="#FFA000"
        android:strokeWidth="2"
        android:pathData="M54,18 L63,38 L85,40 L68,54 L73,76 L54,64 L35,76 L40,54 L23,40 L45,38 Z"/>
    <!-- Cute Smile -->
    <path
        android:strokeColor="#5D4037"
        android:strokeWidth="2"
        android:strokeLineCap="round"
        android:pathData="M48,50 Q54,55 60,50"/>
</vector>''')

with open("app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml", "w", encoding="utf-8") as f:
    f.write('''<?xml version="1.0" encoding="utf-8"?>
<adaptive-icon xmlns:android="http://schemas.android.com/apk/res/android">
    <background android:drawable="@drawable/ic_launcher_background"/>
    <foreground android:drawable="@drawable/ic_launcher_foreground"/>
</adaptive-icon>''')

# 3. Data File: Complete Urdu, ABC, Ginti, and Long Stories
with open("app/src/main/java/com/nanhesitare/app/data/AppData.kt", "w", encoding="utf-8") as f:
    f.write('''package com.nanhesitare.app.data

data class QaidaItem(val letter: String, val word: String, val icon: String)
data class AbcItem(val letter: String, val word: String, val icon: String)
data class GintiItem(val number: String, val urduWord: String, val engWord: String, val icon: String)
data class StoryItem(val title: String, val fullStory: String, val moral: String)

object AppData {
    val urduQaida = listOf(
        QaidaItem("آ", "آم", "🥭"), QaidaItem("ا", "انار", "🍎"), QaidaItem("ب", "بلی", "🐱"),
        QaidaItem("پ", "پتنگ", "🪁"), QaidaItem("ت", "تتلی", "🦋"), QaidaItem("ٹ", "ٹماٹر", "🍅"),
        QaidaItem("ث", "ثمر", "🍇"), QaidaItem("ج", "جہاز", "✈️"), QaidaItem("چ", "چاند", "🌙"),
        QaidaItem("ح", "حلوہ", "🍲"), QaidaItem("خ", "خرگوش", "🐰"), QaidaItem("د", "درخت", "🌳"),
        QaidaItem("ڈ", "ڈولفن", "🐬"), QaidaItem("ذ", "ذخیرہ", "📦"), QaidaItem("ر", "ریل گاڑی", "🚂"),
        QaidaItem("ڑ", "پہاڑ", "🏔️"), QaidaItem("ز", "زرافہ", "🦒"), QaidaItem("ژ", "ژالہ باری", "🌨️"),
        QaidaItem("س", "سیب", "🍏"), QaidaItem("ش", "شیر", "🦁"), QaidaItem("ص", "صوفہ", "🛋️"),
        QaidaItem("ض", "ضیافت", "🍽️"), QaidaItem("ط", "طوطا", "🦜"), QaidaItem("ظ", "ظروف", "🏺"),
        QaidaItem("ع", "عینک", "👓"), QaidaItem("غ", "غبارہ", "🎈"), QaidaItem("ف", "فوارہ", "⛲"),
        QaidaItem("ق", "قلم", "🖊️"), QaidaItem("ک", "کتاب", "📖"), QaidaItem("گ", "گائے", "🐄"),
        QaidaItem("ل", "لومڑی", "🦊"), QaidaItem("م", "مچھلی", "🐟"), QaidaItem("ن", "نلکا", "🚰"),
        QaidaItem("و", "ورزش", "⚽"), QaidaItem("ہ", "ہاتھی", "🐘"), QaidaItem("ء", "چائے", "☕"),
        QaidaItem("ی", "یکہ", "🐴"), QaidaItem("ے", "طوطے", "🦜")
    )

    val abcList = listOf(
        AbcItem("A", "Apple", "🍎"), AbcItem("B", "Ball", "⚽"), AbcItem("C", "Cat", "🐱"),
        AbcItem("D", "Dog", "🐶"), AbcItem("E", "Elephant", "🐘"), AbcItem("F", "Fish", "🐟"),
        AbcItem("G", "Grapes", "🍇"), AbcItem("H", "Horse", "🐴"), AbcItem("I", "Ice Cream", "🍦"),
        AbcItem("J", "Jug", "🫖"), AbcItem("K", "Kite", "🪁"), AbcItem("L", "Lion", "🦁"),
        AbcItem("M", "Monkey", "🐵"), AbcItem("N", "Nest", "🪺"), AbcItem("O", "Orange", "🍊"),
        AbcItem("P", "Parrot", "🦜"), AbcItem("Q", "Queen", "👑"), AbcItem("R", "Rabbit", "🐰"),
        AbcItem("S", "Sun", "☀️"), AbcItem("T", "Tiger", "🐯"), AbcItem("U", "Umbrella", "☂️"),
        AbcItem("V", "Van", "🚐"), AbcItem("W", "Watch", "⌚"), AbcItem("X", "Xylophone", "🎼"),
        AbcItem("Y", "Yak", "🐂"), AbcItem("Z", "Zebra", "🦓")
    )

    val gintiList = listOf(
        GintiItem("۱", "ایک", "One", "⭐"),
        GintiItem("۲", "دو", "Two", "⭐⭐"),
        GintiItem("۳", "تین", "Three", "⭐⭐⭐"),
        GintiItem("۴", "چار", "Four", "⭐⭐⭐⭐"),
        GintiItem("۵", "پانچ", "Five", "🖐️"),
        GintiItem("۶", "چھ", "Six", "🎲"),
        GintiItem("۷", "سات", "Seven", "🌈"),
        GintiItem("۸", "آٹھ", "Eight", "🐙"),
        GintiItem("۹", "نو", "Nine", "🎈"),
        GintiItem("۱۰", "دس", "Ten", "🔟"),
        GintiItem("۱۱", "گیارہ", "Eleven", "🌟"),
        GintiItem("۱۲", "بارہ", "Twelve", "🌟"),
        GintiItem("۱۳", "تیرہ", "Thirteen", "🌟"),
        GintiItem("۱۴", "چودہ", "Fourteen", "🌟"),
        GintiItem("۱۵", "پندرہ", "Fifteen", "🌟"),
        GintiItem("۱۶", "سولہ", "Sixteen", "🌟"),
        GintiItem("۱۷", "سترہ", "Seventeen", "🌟"),
        GintiItem("۱۸", "اٹھارہ", "Eighteen", "🌟"),
        GintiItem("۱۹", "انیس", "Nineteen", "🌟"),
        GintiItem("۲۰", "بیس", "Twenty", "🎯")
    )

    val stories = listOf(
        StoryItem(
            title = "پیاسا کوا اور عقل مندی",
            fullStory = """ایک دفعہ کا ذکر ہے کہ گرمیوں کے دن تھے اور دھوپ شدید تیز تھی۔ جنگل کے تمام پرندے اپنے گھونسلوں میں سستانے چلے گئے تھے کیونکہ پیاس اور گرمی سے سب کا برا حال تھا۔

ایک کوا بھی شدید پیاسا تھا اور پانی کی تلاش میں دور دراز علاقوں میں اڑ رہا تھا۔ اڑتے اڑتے اس کے پر تھک چکے تھے اور حلق سوکھ رہا تھا۔ آخر کار اسے ایک خوبصورت باغ میں ایک مٹکا نظر آیا۔ کوا خوش ہو کر فوراً اڑ کر مٹکے کی منڈیر پر جا بیٹھا۔

جب اس نے اندر جھانکا تو دیکھا کہ مٹکے میں پانی تو موجود ہے لیکن وہ اتنی گہرائی میں ہے کہ اس کی چونچ پانی تک نہیں پہنچ پا رہی تھی۔ کوے نے پانی پینے کی بہت کوشش کی لیکن ہر بار ناکام رہا۔ وہ چاہتا تو مایوس ہو کر اڑ جاتا، مگر اس نے ہمت نہ ہاری۔

کوے نے ادھر ادھر دیکھا تو باغ میں چھوٹے چھوٹے کنکریاں اور پتھر بکھرے ہوئے ملے۔ کوے کے ذہن میں ایک شاندار تدبیر آئی۔ اس نے ایک ایک کنکر چونچ میں اٹھایا اور مٹکے میں ڈالنا شروع کر دیا۔

جیسے جیسے پتھر نیچے جمع ہوتے گئے، پانی آہستہ آہستہ اوپر آتا گیا۔ کوے نے لگاتار محنت جاری رکھی یہاں تک کہ پانی مٹکے کے منہ تک پہنچ گیا۔ کوے نے پیٹ بھر کر ٹھنڈا میٹھا پانی پیا، اللہ کا شکر ادا کیا اور خوشی سے گیت گاتا ہوا اڑ گیا۔""",
            moral = "نتیجہ: محنت اور عقل مندی سے ہر مشکل حل ہو جاتی ہے۔"
        ),
        StoryItem(
            title = "شیر اور وفادار چوہا",
            fullStory = """ایک گھنے اور سرسبز جنگل میں ایک بڑا ہی بہادر اور طاقتور شیر رہا کرتا تھا۔ دوپہر کے وقت شیر ایک بڑے درخت کے سائے میں ٹھنڈی ہوا کے جھونکوں کا لطف لیتے ہوئے گہری نیند سو رہا تھا۔

اسی درخت کے نیچے ایک ننھا چوہا اپنے بل سے باہر نکلا۔ وہ بہت شرارتی تھا۔ شیر کو سوتا دیکھ کر وہ شیر کے اوپر چڑھ گیا اور اس کی پیٹھ پر کودنے لگا۔ چوہے کی اس حرکت سے شیر کی آنکھ کھل گئی۔

شیر نے غصے سے دہاڑتے ہوئے اپنے بھاری پنجے کے نیچے چوہے کو دبا لیا۔ شیر نے کہا: 'اے ننھی سی جان! تیری یہ ہمت کہ تو نے جنگل کے بادشاہ کی نیند خراب کی؟ اب میں تجھے کھا جاؤں گا!'

ننھا چوہا تھر تھر کانپنے لگا اور ہاتھ جوڑ کر بولا: 'اے جنگل کے بادشاہ! مجھے معاف فرما دیں۔ میں غلطی سے کھیل رہا تھا۔ اگر آپ مجھ پر رحم کریں گے تو زندگی میں کبھی نہ کبھی میں بھی آپ کے کام آ سکتا ہوں۔' شیر یہ سن کر ہنس پڑا کہ اتنا ننھا کیڑا میرے کیا کام آئے گا، لیکن اس نے رحم کھا کر چوہے کو آزاد کر دیا۔

کچھ مہینوں بعد جنگل میں شکاری آئے اور انہوں نے مضبوط رسیوں کا جال بچھا دیا۔ بدقسمتی سے شیر اس جال میں بری طرح پھنس گیا۔ شیر نے دھاڑنا شروع کیا مگر جتنا ہلتا جال اتنا ہی تنگ ہو جاتا۔

چوہے نے اپنے بل میں شیر کی دردناک دہاڑ سنی تو فوراً موقع پر پہنچا۔ اس نے بنا وقت ضائع کیے اپنے تیز دھار دانتوں سے رسیوں کو کترنا شروع کر دیا۔ تھوڑی ہی دیر میں پورا جال کٹ گیا اور شیر آزاد ہو گیا۔ شیر نے چوہے کا تہہ دل سے شکریہ ادا کیا اور اس دن کے بعد شیر اور چوہا بہترین دوست بن گئے۔""",
            moral = "نتیجہ: کسی کو حقیر یا کمزور نہ سمجھو، اور نیکی کبھی ضائع نہیں جاتی۔"
        ),
        StoryItem(
            title = "سچائی کی طاقت",
            fullStory = """ایک گاؤں میں اسلم نامی ایک باادب اور ذہین بچہ رہتا تھا۔ اس کے والدین نے اسے ہمیشہ سچ بولنے اور ایمانداری اختیار کرنے کی تلقین کی تھی۔ 

ایک دن اسلم اپنے دوستوں کے ساتھ گلی میں گیند کے ساتھ کرکٹ کھیل رہا تھا۔ اسلم نے زور دار شاٹ لگائی تو گیند سیدھی جا کر پڑوس والے قاسم صاحب کی بیٹھک کی کھڑکی کے شیشے پر لگی اور شیشہ جھنکار کے ساتھ ٹوٹ گیا۔

شیشہ ٹوٹنے کی آواز سن کر تمام بچے ڈر کے مارے گلی کے مختلف کونوں میں بھاگ گئے اور چھپ گئے۔ بچوں نے اسلم کو بھی آواز دی کہ 'اسلم بھاگ جاؤ ورنہ قاسم صاحب بہت ڈانٹیں گے!' مگر اسلم وہاں کھڑا رہا۔

قاسم صاحب غصے سے باہر نکلے اور گرج دار آواز میں پوچھا: 'یہ شیشہ کس نے توڑا ہے؟' اسلم نے سر جھکا کر ادب سے کہا: 'انکل! یہ مجھ سے غلطی ہوئی ہے، میں شاٹ لگا رہا تھا کہ گیند قابو سے باہر ہو گئی۔ میں معافی چاہتا ہوں اور اپنی جیب خرچ سے آپ کو نیا شیشہ لگوا کر دوں گا۔'

قاسم صاحب اسلم کی سچائی اور جرات دیکھ کر بہت متاثر ہوئے۔ ان کا غصہ غائب ہو گیا اور انہوں نے مسکرا کر اسلم کے سر پر شفقت سے ہاتھ پھیرا اور کہا: 'بیٹا! مجھے نقصان کا کوئی دکھ نہیں، مجھے خوشی ہے کہ تم نے جھوٹ کا سہارا نہیں لیا۔ تمہاری سچائی نے میرا دل جیت لیا ہے۔'""",
            moral = "نتیجہ: سچ بولنے والے کی ہمیشہ عزت ہوتی ہے اور سچ انسان کو سرخرو کرتا ہے۔"
        )
    )
}
''')

# 4. Nature Background Composable (Sky, Sun, River, Flowers, Green Hills)
with open("app/src/main/java/com/nanhesitare/app/ui/NatureBackground.kt", "w", encoding="utf-8") as f:
    f.write('''package com.nanhesitare.app.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path

@Composable
fun NatureBackground(content: @Composable BoxScope.() -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // 1. Sky Gradient
            drawRect(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF81D4FA), Color(0xFFE1F5FE))
                ),
                size = size
            )

            // 2. Bright Golden Sun
            drawCircle(
                color = Color(0xFFFFEB3B),
                radius = w * 0.12f,
                center = Offset(w * 0.85f, h * 0.10f)
            )

            // 3. Rolling Green Hills (Background)
            val hillBack = Path().apply {
                moveTo(0f, h * 0.70f)
                quadraticBezierTo(w * 0.4f, h * 0.58f, w, h * 0.68f)
                lineTo(w, h)
                lineTo(0f, h)
                close()
            }
            drawPath(hillBack, color = Color(0xFF81C784))

            // 4. Blue River / Stream
            val river = Path().apply {
                moveTo(w * 0.45f, h * 0.65f)
                quadraticBezierTo(w * 0.55f, h * 0.80f, w * 0.75f, h)
                lineTo(w * 0.95f, h)
                quadraticBezierTo(w * 0.70f, h * 0.78f, w * 0.55f, h * 0.65f)
                close()
            }
            drawPath(river, color = Color(0xFF29B6F6))

            // 5. Foreground Lush Green Field
            val hillFront = Path().apply {
                moveTo(0f, h * 0.78f)
                quadraticBezierTo(w * 0.5f, h * 0.88f, w, h * 0.76f)
                lineTo(w, h)
                lineTo(0f, h)
                close()
            }
            drawPath(hillFront, color = Color(0xFF4CAF50))

            // 6. Tree on the left
            drawRect(
                color = Color(0xFF795548),
                topLeft = Offset(w * 0.08f, h * 0.70f),
                size = androidx.compose.ui.geometry.Size(w * 0.04f, h * 0.15f)
            )
            drawCircle(
                color = Color(0xFF2E7D32),
                radius = w * 0.10f,
                center = Offset(w * 0.10f, h * 0.68f)
            )

            // 7. Small colorful flowers on the ground
            drawCircle(color = Color(0xFFFF4081), radius = 6f, center = Offset(w * 0.25f, h * 0.92f))
            drawCircle(color = Color(0xFFFFEB3B), radius = 6f, center = Offset(w * 0.35f, h * 0.95f))
            drawCircle(color = Color(0xFFFF5722), radius = 6f, center = Offset(w * 0.15f, h * 0.88f))
            drawCircle(color = Color(0xFFE040FB), radius = 6f, center = Offset(w * 0.85f, h * 0.93f))
        }

        // Child content on top of nature
        content()
    }
}
''')

# 5. UI Sound Helper (Synthesized cheerful kid/chime tone without needing external mp3)
with open("app/src/main/java/com/nanhesitare/app/ui/SoundEffect.kt", "w", encoding="utf-8") as f:
    f.write('''package com.nanhesitare.app.ui

import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import kotlin.concurrent.thread
import kotlin.math.sin

object SoundEffect {
    fun playCheerfulChime() {
        thread {
            try {
                val sampleRate = 44100
                val durationMs = 600
                val numSamples = sampleRate * durationMs / 1000
                val buffer = ShortArray(numSamples)

                val freqs = listOf(523.25, 659.25, 783.99, 1046.50) // C5, E5, G5, C6 (Kid-like happy chime)
                for (i in 0 until numSamples) {
                    val time = i.toDouble() / sampleRate
                    val noteIdx = (time * freqs.size / (durationMs / 1000.0)).toInt().coerceIn(0, freqs.size - 1)
                    val freq = freqs[noteIdx]
                    val envelope = 1.0 - (i.toDouble() / numSamples)
                    val sample = (sin(2.0 * Math.PI * freq * time) * 32767 * envelope * 0.6).toInt()
                    buffer[i] = sample.toShort()
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
                    .setBufferSizeInBytes(buffer.size * 2)
                    .setTransferMode(AudioTrack.MODE_STATIC)
                    .build()

                track.write(buffer, 0, buffer.size)
                track.play()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
''')

# 6. Screens Implementation (Qaida, ABC, Ginti, Stories, Home, Splash)
with open("app/src/main/java/com/nanhesitare/app/ui/AppScreens.kt", "w", encoding="utf-8") as f:
    f.write('''package com.nanhesitare.app.ui

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
''')

# 7. MainActivity with BackHandler (Fixes the App exit bug!)
with open("app/src/main/java/com/nanhesitare/app/MainActivity.kt", "w", encoding="utf-8") as f:
    f.write('''package com.nanhesitare.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.nanhesitare.app.ui.*

enum class CurrentScreen {
    SPLASH, HOME, QAIDA, ABC, GINTI, STORIES
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var screen by remember { mutableStateOf(CurrentScreen.SPLASH) }

            // Back button press handling: Home par le jaye ga bajaye app band hone ke!
            BackHandler(enabled = screen != CurrentScreen.HOME && screen != CurrentScreen.SPLASH) {
                screen = CurrentScreen.HOME
            }

            when (screen) {
                CurrentScreen.SPLASH -> SplashScreen { screen = CurrentScreen.HOME }
                CurrentScreen.HOME -> HomeScreen(
                    onNavigateQaida = { screen = CurrentScreen.QAIDA },
                    onNavigateAbc = { screen = CurrentScreen.ABC },
                    onNavigateGinti = { screen = CurrentScreen.GINTI },
                    onNavigateStories = { screen = CurrentScreen.STORIES }
                )
                CurrentScreen.QAIDA -> QaidaScreen(onBack = { screen = CurrentScreen.HOME })
                CurrentScreen.ABC -> AbcScreen(onBack = { screen = CurrentScreen.HOME })
                CurrentScreen.GINTI -> GintiScreen(onBack = { screen = CurrentScreen.HOME })
                CurrentScreen.STORIES -> StoriesScreen(onBack = { screen = CurrentScreen.HOME })
            }
        }
    }
}
''')

# 8. Update AndroidManifest to ensure clean config & new icon
with open("app/src/main/AndroidManifest.xml", "w", encoding="utf-8") as f:
    f.write('''<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="نانھے ستارے"
        android:roundIcon="@mipmap/ic_launcher"
        android:supportsRtl="true"
        android:theme="@android:style/Theme.Material.Light.NoActionBar">
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>''')

print("ALL FILES CREATED SUCCESSFULLY!")
