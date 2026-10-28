package com.nanhesitare.app.data
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
