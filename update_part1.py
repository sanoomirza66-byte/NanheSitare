import os

files = {}

# 1. Models, Comprehensive Stories & Complete Data
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
        "urdu_letters" to "مکمل اردو قاعدہ", "english_letters" to "انگریزی ABC",
        "numbers" to "گنتی (1 تا 20)", "shapes" to "شکلیں", "colors" to "رنگ", "quiz" to "کوئز",
        "parent_gate_title" to "والدین کا گیٹ", "parent_gate_desc" to "آگے بڑھنے کے لیے سوال حل کریں:",
        "submit" to "تصدیق کریں", "privacy_policy" to "پرائیویسی پالیسی",
        "privacy_content" to "ننھے ستارے ایپ بچوں کا کوئی بھی ذاتی ڈیٹا، نام یا لوکیشن جمع نہیں کرتی۔ یہ مکمل آف لائن اور محفوظ ہے۔",
        "flip_cards" to "کارڈ ملاؤ کھیل", "play_again" to "دوبارہ کھیلیں", "congrats" to "شاباش! جیت گئے! 🌟"
    )
    private val englishStrings = mapOf(
        "app_name" to "Nanhe Sitare",
        "tagline" to "Play, Learn and Shine!",
        "drawing_studio" to "Drawing Studio 🎨",
        "coloring_book" to "Coloring Book 🖍️",
        "cartoon_friends" to "Cartoon Friends 🦁",
        "learning" to "Learning Fun 📚",
        "stories" to "Bedtime Stories 📖",
        "games" to "Brain Games 🧩",
        "parent_section" to "Parents Corner 🛡️",
        "clear" to "Clear", "undo" to "Undo", "redo" to "Redo",
        "save" to "Save", "eraser" to "Eraser", "brush_size" to "Brush Size",
        "saved_msg" to "Saved to gallery! 🎉", "confirm_clear" to "Clear canvas?",
        "yes" to "Yes", "no" to "No",
        "urdu_letters" to "Urdu Qaida", "english_letters" to "English ABC",
        "numbers" to "Numbers 1-20", "shapes" to "Shapes", "colors" to "Colors", "quiz" to "Quiz",
        "parent_gate_title" to "Parental Gate", "parent_gate_desc" to "Solve to continue:",
        "submit" to "Verify", "privacy_policy" to "Privacy Policy",
        "privacy_content" to "Nanhe Sitare does not collect personal data. It is 100% offline and safe.",
        "flip_cards" to "Memory Match Game", "play_again" to "Play Again", "congrats" to "Bravo! You Won! 🌟"
    )
}

object AppData {
    val animals = listOf(
        CartoonAnimal("lion", "ببر شیر (شیرو)", "Sheru the Lion", "🦁", "دھاڑتا ہے: گرارر!", "Roars: Roaaar!", "السلام علیکم! میں شیرو ہوں، جنگل کا بہادر اور طاقتور بادشاہ!", "Hello! I am Sheru the brave lion, king of the forest!", "شیر دن کے وقت آرام کرتا ہے اور شام کو چہل قدمی کرتا ہے۔", "Lions love resting in the shade and running at dusk.", Color(0xFFFFB74D)),
        CartoonAnimal("rabbit", "خرگوش (بنٹی)", "Bunty Rabbit", "🐰", "اچھلتا ہے: تھپ تھپ!", "Hops: Hop hop!", "ہیلو پیارے دوستو! میں بنٹی خرگوش ہوں۔ میٹھی لال گاجریں مجھے بہت پسند ہیں!", "Hi friends! I am Bunty the rabbit. I love sweet red carrots!", "خرگوش کے کان بہت تیز سنتے ہیں اور یہ اونچی چھلانگ لگا سکتا ہے۔", "Rabbits have very sharp ears and can leap super high.", Color(0xFFF8BBD0)),
        CartoonAnimal("panda", "پانڈا (پپو)", "Pappu Panda", "🐼", "چبواتا ہے: کرنچ کرنچ!", "Munches: Crunch crunch!", "آداب! میں پپو پانڈا ہوں۔ ٹھنڈے پہاڑوں میں رہنا اور بانس کھانا میرا کام ہے!", "Greetings! I am Pappu Panda. I love cold mountains and bamboo!", "پانڈا دن کا زیادہ تر وقت ہرا بانس کھا کر ہنسی خوشی گزارتا ہے۔", "Pandas spend most of their day happily chewing fresh bamboo.", Color(0xFFCFD8DC)),
        CartoonAnimal("parrot", "طوطا (مٹھو)", "Mitthu Parrot", "🦜", "بولتا ہے: ٹیں ٹیں!", "Chirps: Squawk squawk!", "میاں مٹھو چوری کھاؤ! میں سبز پروں اور سرخ چونچ والا پیارا طوطا ہوں!", "I am Mitthu the clever parrot with a red beak and green wings!", "طوطے انسانوں کی آوازوں کی خوبصورت نقل اتار سکتے ہیں۔", "Parrots can learn and mimic human voices remarkably well.", Color(0xFFA5D6A7)),
        CartoonAnimal("cat", "بلی (مانو)", "Mano the Cat", "🐱", "بولتی ہے: میاؤں میاؤں!", "Meows: Meow meow!", "پیاری مانو بلی، کٹوری سے میٹھا دودھ پی کر میاؤں میاؤں کہتی ہوں!", "I am Mano the kitten. I love warm milk and chasing balls of yarn!", "بلی اندھیرے میں بھی انسانوں سے چھ گنا زیادہ صاف دیکھ سکتی ہے۔", "Cats have amazing night vision and soft quiet paws.", Color(0xFFFFF59D)),
        CartoonAnimal("elephant", "ہاتھی (جمبو)", "Jumbo Elephant", "🐘", "چنگھاڑتا ہے: پاں پاں!", "Trumpets: Pawooo!", "نمسکار! میں ننھا جمبو ہاتھی ہوں۔ میری لمبی سونڈ پانی کا فوارہ بن جاتی ہے!", "Hello! I am Jumbo the elephant. My trunk is like a fun water shower!", "ہاتھی دنیا کا سب سے بڑا مگر دل کا انتہائی نرم اور وفادار جانور ہے۔", "Elephants are the largest land animals and are very kind and loyal.", Color(0xFF90CAF9))
    )

    // تفصیلی ۲ صفحات پر مشتمل کہانیاں
    val stories = listOf(
        Story(
            id = 1,
            titleUr = "۱. کچھوا اور خرگوش کی تاریخی دوڑ",
            titleEn = "1. The Great Race of Tortoise and Hare",
            icon = "🐢🐰",
            moralUr = "سبق: غرور کا سر نیچا ہوتا ہے، اور جو مستقل محنت کرتا ہے وہ ہمیشہ جیت جاتا ہے۔",
            moralEn = "Moral: Pride comes before a fall. Slow and steady wins the race.",
            contentUr = "ایک سرسبز اور خوبصورت جنگل میں بنٹی نامی ایک تیز رفتار خرگوش رہتا تھا۔ وہ ہر وقت اپنی تیز رفتاری پر اتراتا اور دوسرے جانوروں کا مذاق اڑاتا۔ ایک روز اس کی نظر چنتو کچھوے پر پڑی جو آہستہ آہستہ چل رہا تھا۔ خرگوش ہنس کر بولا: 'چنتو بھائی! تم اتنی سست رفتار سے چلو گے تو شام تک بھی درخت تک نہیں پہنچ پاؤ گے!'\\n\\nکچھوے کو اس کا مذاق اچھا نہ لگا۔ اس نے مسکرا کر کہا: 'بنٹی بھائی، اگر تمہیں اتنا گھمنڈ ہے تو آؤ میرے ساتھ دوڑ لگا لو، دیکھتے ہیں کون پہلے ندی کے اس پار سرخ جھنڈے تک پہنچتا ہے!' تمام جانور جمع ہو گئے اور دوڑ شروع ہو گئی۔\\n\\nسیٹی بجتے ہی خرگوش ہوا کی رفتار سے آگے نکل گیا اور پلک جھپکتے ہی نظروں سے اوجھل ہو گیا۔ آدھے راستے پر پہنچ کر اس نے پیچھے مڑ کر دیکھا تو کچھوے کا دور دور تک کوئی نام و نشان نہ تھا۔ خرگوش نے سوچا: 'کچھوا تو ابھی کوسوں دور ہو گا۔ کیوں نہ اس گھنے برگد کے درخت کی ٹھنڈی چھاؤں میں تھوڑی دیر آنکھیں موند لوں؟ جیسے ہی وہ قریب آئے گا میں ایک چھلانگ لگا کر جیت جاؤں گا۔'\\n\\nٹھنڈی ہوا کے جھونکوں نے خرگوش کو گہری نیند سلا دیا۔ ادھر چنتو کچھوا بنا رکے، بغیر ہمت ہارے، مسلسل اپنے ننھے قدم اٹھاتا رہا۔ اس نے سوئے ہوئے خرگوش کو پیچھے چھوڑ دیا اور ندی کے کنارے سرخ جھنڈے کے پاس پہنچ گیا۔ جب خرگوش کی آنکھ کھلی اور اس نے دیکھا تو کچھوا جیت کی لکیر پار کر چکا تھا اور سب جانور تالیاں بجا رہے تھے۔ خرگوش شرم سے پانی پانی ہو گیا اور اس نے کبھی غرور نہ کرنے کا عہد کیا۔",
            contentEn = "Once in a lush green forest, Bunty the speedy hare mocked Chintu the slow tortoise. The tortoise challenged him to a race to the river flag. The race started, and the hare dashed far ahead. Seeing the tortoise far behind, the hare decided to take a nap under a shady banyan tree. The tortoise never stopped, taking steady steps hour after hour. When the hare woke up, the tortoise had crossed the finish line! The hare learned that slow and steady effort always triumphs over arrogance."
        ),
        Story(
            id = 2,
            titleUr = "۲. ہوشیار اور عقلمند پیاسا کوا",
            titleEn = "2. The Clever Thirsty Crow",
            icon = "🦅💧",
            moralUr = "سبق: جہاں چاہ وہاں راہ! عقل اور صبر سے ہر بڑی مشکل حل ہو جاتی ہے۔",
            moralEn = "Moral: Where there is a will, there is a way. Patience and wit solve all problems.",
            contentUr = "موسم گرما کی ایک شدید گرم دوپہر تھی، سورج آگ برسا رہا تھا اور جنگل کے تمام پرندے پیاس سے نڈھال تھے۔ ایک کالا کوا صبح سے پانی کی تلاش میں مارا مارا پھر رہا تھا لیکن تمام ندی نالے خشک ہو چکے تھے۔ کوے کی زبان پیاس کے مارے باہر نکل رہی تھی اور اسے لگ رہا تھا کہ اب اس کے پروں میں اڑنے کی طاقت نہیں رہی۔\\n\\nاچانک اسے ایک پرانے باغ میں ایک مٹی کا گھڑا نظر آیا۔ کوا امید بھری نظروں سے خوش ہو کر گھڑے کی منڈیر پر جا بیٹھا۔ جب اس نے اندر جھانکا تو دیکھا کہ گھڑے کے پیندے میں تھوڑا سا پانی تو موجود تھا، لیکن پانی کی سطح اتنی نیچے تھی کہ کوے کی چونچ وہاں تک پہنچ ہی نہیں سکتی تھی۔\\n\\nپہلے تو کوا مایوس ہوا، پھر اس نے اپنی عقلمند آنکھیں گھمائیں تو دیکھا کہ پاس ہی زمین پر چھوٹے چھوٹے کنکر بکھرے پڑے ہیں۔ کوے کے ذہن میں ایک شاندار ترکیب آئی۔ اس نے ایک کنکر چونچ میں اٹھایا اور گھڑے میں گرا دیا۔ پھر دوسرا، پھر تیسرا۔ کوا بنا تھکے ایک ایک کنکر اٹھاتا اور گھڑے میں ڈالتا رہا۔\\n\\nکنکر نیچے بیٹھتے گئے اور آہستہ آہستہ پانی اوپر کی طرف چڑھتا گیا۔ آخر کار پانی گھڑے کے بالکل کنارے تک آ پہنچا! کوے نے اللہ کا شکر ادا کیا، جی بھر کر میٹھا اور ٹھنڈا پانی پیا، اور تر و تازہ ہو کر اونچی پرواز کرتے ہوئے خوشی کے گیت گانے لگا۔",
            contentEn = "On a scorching summer day, a thirsty crow searched everywhere for water. He found a pitcher in a garden, but the water level was too low for his beak to reach. Instead of despairing, he noticed small pebbles on the ground. Patiently, he picked up pebbles one by one and dropped them into the pitcher. As the stones settled, the water rose to the brim. The crow drank happily and flew away full of energy."
        ),
        Story(
            id = 3,
            titleUr = "۳. ننھی چیونٹی اور سفید کبوتر",
            titleEn = "3. The Little Ant and The Kind Dove",
            icon = "🐜🕊️",
            moralUr = "سبق: نیکی کا بدلہ نیکی ہے۔ دوسروں کی مدد کرو گے تو وقت پر تمہاری بھی مدد ہوگی۔",
            moralEn = "Moral: A good deed is never lost. Kindness is rewarded with kindness.",
            contentUr = "ایک شفاف اور تیز ندی کے کنارے اونچے درخت پر ایک سفید کبوتر کا گھونسلا تھا۔ ایک ننھی چیونٹی ندی کے کنارے پانی پینے کے لیے پتھر پر چڑھی تو اچانک اس کا پاؤں پھسل گیا اور وہ شور مچاتی تیز لہروں کے درمیان گر پڑی۔ چیونٹی بہتی جا رہی تھی اور ڈوبنے کے خوف سے ہاتھ پاؤں مار رہی تھی۔\\n\\nدرخت کی شاخ پر بیٹھے رحم دل کبوتر نے جب چیونٹی کو بے بسی کی حالت میں دیکھا تو اس کا دل بھر آیا۔ اس نے فوراً درخت سے ایک چوڑا پتہ توڑا اور احتیاط سے چیونٹی کے بالکل قریب پانی میں گرا دیا۔ چیونٹی نے ہمت کی اور پتے پر چڑھ گئی۔ ہوا نے پتے کو کنارے لگا دیا اور ننھی چیونٹی کی جان بچ گئی۔ اس نے آسمان کی طرف دیکھ کر کبوتر کا شکریہ ادا کیا۔\\n\\nکچھ ہی دن گزرے تھے کہ جنگل میں ایک بے رحم شکاری غلیل لے کر آیا۔ اس نے درخت پر بیٹھے اسی سفید کبوتر کو دیکھ کر اپنا نشانہ باندھا۔ کبوتر بے خبر تھا، مگر پاس موجود ننھی چیونٹی نے شکاری کو دیکھ لیا۔ وقت ضائع کیے بغیر چیونٹی تیزی سے شکاری کے ننگے پاؤں پر چڑھی اور اتنی زور سے کاٹا کہ شکاری کے منہ سے زوردار چیخ نکل گئی اور اس کا نشانہ چوک گیا! پتھر کی آواز سن کر کبوتر ہوشیار ہو گیا اور فوراً اڑ گیا۔ ننھی چیونٹی نے اپنی جان بچانے والے کبوتر کی جان بچا کر دوستی کا حق ادا کر دیا۔",
            contentEn = "A little ant slipped into a rushing stream and was about to drown. A kind dove sitting on a tree saw her and dropped a large leaf into the water. The ant climbed onto the leaf and floated safely to shore. A few days later, a hunter aimed his slingshot at the dove. Seeing this, the brave ant crawled quickly and bit the hunter's foot hard. The hunter screamed in pain, spoiling his aim, and the dove flew away to safety."
        ),
        Story(
            id = 4,
            titleUr = "۴. سچے چرواہے کا اخلاقی سبق",
            titleEn = "4. The Shepherd Boy and the Truth",
            icon = "🐑🧒",
            moralUr = "سبق: سچ میں برکت اور جھوٹ میں رسوائی ہے۔ جھوٹے پر سچ بولنے کے باوجود کوئی اعتبار نہیں کرتا۔",
            moralEn = "Moral: A liar will not be believed, even when he speaks the truth.",
            contentUr = "ایک سرسبز وادی کے گاؤں میں راجو نامی ایک نوجوان بکریاں چراتا تھا۔ پہاڑوں پر تنہا رہ کر وہ اکثر بور ہو جاتا تھا، اس لیے اس نے گاؤں والوں کے ساتھ ایک برا مذاق کرنے کی سوچی۔ ایک دن وہ اونچی چٹان پر کھڑا ہو کر چلاّنے لگا: 'بچاؤ! بچاؤ! بھیڑیا آ گیا! میری بکریوں کو بچاؤ!'\\n\\nگاؤں کے کسان اپنے کھیت چھوڑ کر لاٹھیاں اور کلہاڑیاں لے کر پہاڑ کی طرف دوڑے۔ جب وہ ہانپتے کانپتے پہنچے تو دیکھا کہ راجو درخت کے نیچے بیٹھا ہنس رہا ہے اور وہاں کوئی بھیڑیا نہیں ہے۔ گاؤں والے غصے سے واپس لوٹ گئے۔ راجو کو اس شرارت میں بڑا مزا آیا اور اس نے دو دن بعد دوبارہ یہی جھوٹ دہرایا۔ لوگ پھر مدد کو دوڑے لیکن پھر راجو کا قہقہہ سن کر مایوس لوٹے۔\\n\\nایک شام جب سورج ڈھل رہا تھا، اچانک جنگل کی جھاڑیوں سے ایک بڑا اور خوفناک خونخوار بھیڑیا سچ مچ نکل آیا! اس نے بکریوں پر حملہ کر دیا۔ راجو خوف سے کانپنے لگا اور درخت پر چڑھ کر چیخ چیخ کر پکارنے لگا: 'خدارا مدد کرو! اس بار سچ میں بھیڑیا آیا ہے!' لیکن گاؤں والوں نے سنا تو ایک دوسرے سے کہا: 'راجو پھر مذاق کر رہا ہے، کوئی جانے کی ضرورت نہیں۔' کوئی مدد کو نہ آیا اور بھیڑیا کئی بکریاں اٹھا کر لے گیا۔ راجو روتا ہوا گاؤں لوٹا اور اس نے اپنے آنسوؤں سے سچے دل سے توبہ کی کہ وہ زندگی بھر کبھی جھوٹ نہیں بولے گا۔",
            contentEn = "A shepherd boy named Raju guarded sheep on a hillside. Feeling bored, he shouted falsely that a wolf was attacking. The villagers ran to his aid with sticks, only to be laughed at. He repeated the cruel trick again. One evening, a real wolf emerged and attacked the sheep. Raju screamed in terror for help, but the villagers assumed it was another prank and ignored him. The wolf scattered the flock. Raju wept in sorrow and vowed never to speak a lie again."
        ),
        Story(
            id = 5,
            titleUr = "۵. مٹھو طوطا اور شکاری کا جال",
            titleEn = "5. Wise Mitthu and the Hunter's Net",
            icon = "🦜🌳",
            moralUr = "سبق: اتحاد میں برکت اور بڑی سے بڑی مصیبت کو ٹالنے کی بے مثال طاقت ہوتی ہے۔",
            moralEn = "Moral: Unity is strength. Together, we can conquer any trap.",
            contentUr = "ایک خوبصورت اور پھلوں سے لدے باغ میں طوطوں کا ایک بڑا خاندان رہتا تھا جس کے بزرگ اور دانا سردار کا نام مٹھو تھا۔ مٹھو ہمیشہ اپنے نوجوان ساتھیوں کو محتاط رہنے کی تلقین کرتا تھا۔ ایک صبح ایک مکار شکاری نے باغ کی گھاس پر گندم اور مکئی کے سنہرے دانے بکھیرے اور اس کے اوپر باریک جال بچھا کر درخت کے پیچھے چھپ گیا۔\\n\\nجب نوجوان طوطوں نے دانے دیکھے تو خوشی سے چہچہانے لگے اور دانے چگنے کے لیے اترنے لگے۔ مٹھو نے دور سے خبردار کیا: 'ٹھہرو! جنگل کے ویرانے میں اتنے ڈھیر سارے دانے کسی چال کا حصہ ہو سکتے ہیں، نیچے مت جاؤ!' مگر بھوکے طوطوں نے اس کی بات نہ مانی اور نیچے اتر آئے۔ دانہ چگتے ہی جال کھنچ گیا اور تمام طوطے اس میں بری طرح پھنس گئے۔\\n\\nشکاری کو لاٹھی تھامے جھاڑیوں سے نکلتے دیکھ کر تمام طوطے خوف سے پھڑپھڑانے اور رونے لگے۔ اس کڑے وقت میں دانا مٹھو نے تحمل سے کہا: 'اگر تم الگ الگ ہاتھ پاؤں مارو گے تو شکاری تمہیں قید کر لے گا۔ میری ایک بات مانو: جب میں تین گنوں تو سب مل کر ایک ہی سمت میں پوری طاقت سے اپنے پر پھڑپھڑاؤ!'\\n\\nجیسے ہی مٹھو نے 'تین' کہا، تمام طوطوں نے ایک جان ہو کر اڑان بھری۔ ان کے متفقہ زور سے پورا بھاری جال زمین سے اکھڑ گیا اور وہ جال سمیت آسمان میں اڑ نکلے! شکاری حیران و پریشان منہ دیکھتا رہ گیا۔ طوطے ندی پار اپنے دوست چوہے کے پاس گئے جس نے اپنے تیز دانتوں سے جال کے دھاگے کاٹ کر سب کو بحفاظت آزاد کر دیا۔",
            contentEn = "A flock of parrots lived under the guidance of wise Mitthu. A cunning hunter laid grain over a hidden net. Tempted by the feast, the young parrots landed and were caught. Seeing the hunter approach, they panicked. Mitthu commanded them: 'Do not pull separately! On the count of three, flap your wings together in one direction!' With united strength, the parrots lifted the entire net into the sky and escaped to their mouse friend who chewed the net and freed them all."
        )
    )
}
"""

print("PART 1 COMPLETED SUCCESSFULLY!")
