import re

content = '''package com.nanhesitare.app.data

data class QaidaItem(val urdu: String, val title: String, val transliteration: String, val exampleWord: String, val emoji: String)
data class AlphabetItem(val letter: String, val word: String, val phonetic: String, val emoji: String)
data class NumberItem(val digit: Int, val urduDigit: String, val englishWord: String, val urduWord: String, val emoji: String)
data class Story(val id: Int, val titleUrdu: String, val titleEnglish: String, val emoji: String, val moralUrdu: String, val moralEnglish: String, val contentUrdu: String, val contentEnglish: String)

object AppData {
    val qaidaList = listOf(
        QaidaItem("ا", "الف", "Alif", "انار", "🍎"),
        QaidaItem("ب", "بے", "Bay", "بلی", "🐱"),
        QaidaItem("پ", "پے", "Pay", "پتنگ", "🪁"),
        QaidaItem("ت", "تے", "Tay", "تتلی", "🦋"),
        QaidaItem("ٹ", "ٹے", "Ttay", "ٹماٹر", "🍅"),
        QaidaItem("ث", "ثے", "Say", "ثمر", "🍇"),
        QaidaItem("ج", "جیم", "Jeem", "جہاز", "✈️"),
        QaidaItem("چ", "چے", "Chay", "چڑیا", "🐦"),
        QaidaItem("ح", "حے", "Badi Hay", "حلوہ", "🍲"),
        QaidaItem("خ", "خے", "Khay", "خرگوش", "🐰"),
        QaidaItem("د", "دال", "Daal", "درخت", "🌳"),
        QaidaItem("ڈ", "ڈال", "Ddaal", "ڈولفن", "🐬"),
        QaidaItem("ذ", "ذال", "Zaal", "ذخیرہ", "📦"),
        QaidaItem("ر", "رے", "Ray", "ریل گاڑی", "🚂"),
        QaidaItem("ڑ", "ڑے", "Rray", "پہاڑ", "⛰️"),
        QaidaItem("ز", "زے", "Zay", "زرافہ", "🦒"),
        QaidaItem("ژ", "ژے", "Zhay", "ژالہ باری", "🌨️"),
        QaidaItem("س", "سین", "Seen", "سیب", "🍎"),
        QaidaItem("ش", "شین", "Sheen", "شیر", "🦁"),
        QaidaItem("ص", "صاد", "Suad", "صوفہ", "🛋️"),
        QaidaItem("ض", "ضاد", "Zuad", "ضیاء", "☀️"),
        QaidaItem("ط", "طوئیں", "Toyen", "طوطا", "🦜"),
        QaidaItem("ظ", "ظوئیں", "Zoyen", "ظروف", "🏺"),
        QaidaItem("ع", "عین", "Ain", "عینک", "👓"),
        QaidaItem("غ", "غین", "Ghain", "غبارہ", "🎈"),
        QaidaItem("ف", "فے", "Fay", "فوارہ", "⛲"),
        QaidaItem("ق", "قاف", "Qaaf", "قلم", "🖊️"),
        QaidaItem("ک", "کاف", "Kaaf", "کتاب", "📖"),
        QaidaItem("گ", "گاف", "Gaaf", "گائے", "🐄"),
        QaidaItem("ل", "لام", "Laam", "لومڑی", "🦊"),
        QaidaItem("م", "میم", "Meem", "مچھلی", "🐟"),
        QaidaItem("ن", "نون", "Noon", "نلکا", "🚰"),
        QaidaItem("و", "واؤ", "Wao", "ورزش", "🏃"),
        QaidaItem("ہ", "چھوٹی ہے", "Choti Hay", "ہاتھی", "🐘"),
        QaidaItem("ء", "ہمزہ", "Hamza", "چائے", "☕"),
        QaidaItem("ی", "چھوٹی یے", "Choti Yay", "یکہ", "🛺"),
        QaidaItem("ے", "بڑی یے", "Badi Yay", "لڑکے", "👦")
    )

    val alphabetList = ('A'..'Z').map { char ->
        when (char) {
            'A' -> AlphabetItem("A", "Apple", "æp.əl", "🍎")
            'B' -> AlphabetItem("B", "Ball", "bɔːl", "⚽")
            'C' -> AlphabetItem("C", "Cat", "kæt", "🐱")
            'D' -> AlphabetItem("D", "Duck", "dʌk", "🦆")
            'E' -> AlphabetItem("E", "Elephant", "el.ɪ.fənt", "🐘")
            'F' -> AlphabetItem("F", "Fish", "fɪʃ", "🐟")
            'G' -> AlphabetItem("G", "Giraffe", "dʒɪˈrɑːf", "🦒")
            'H' -> AlphabetItem("H", "Horse", "hɔːs", "🐴")
            'I' -> AlphabetItem("I", "Ice Cream", "aɪs kriːm", "🍦")
            'J' -> AlphabetItem("J", "Juice", "dʒuːs", "🧃")
            'K' -> AlphabetItem("K", "Kite", "kaɪt", "🪁")
            'L' -> AlphabetItem("L", "Lion", "laɪ.ən", "🦁")
            'M' -> AlphabetItem("M", "Monkey", "mʌŋ.ki", "🐒")
            'N' -> AlphabetItem("N", "Nest", "nest", "🪺")
            'O' -> AlphabetItem("O", "Orange", "ɒr.ɪndʒ", "🍊")
            'P' -> AlphabetItem("P", "Parrot", "pær.ət", "🦜")
            'Q' -> AlphabetItem("Q", "Queen", "kwiːn", "👑")
            'R' -> AlphabetItem("R", "Rabbit", "ræb.ɪt", "🐰")
            'S' -> AlphabetItem("S", "Sun", "sʌn", "☀️")
            'T' -> AlphabetItem("T", "Tiger", "taɪ.ɡər", "🐯")
            'U' -> AlphabetItem("U", "Umbrella", "ʌmˈbrel.ə", "☂️")
            'V' -> AlphabetItem("V", "Van", "væn", "🚐")
            'W' -> AlphabetItem("W", "Watch", "wɒtʃ", "⌚")
            'X' -> AlphabetItem("X", "Xylophone", "zaɪ.lə.fəʊn", "🎼")
            'Y' -> AlphabetItem("Y", "Yacht", "jɒt", "⛵")
            else -> AlphabetItem("Z", "Zebra", "zeb.rə", "🦓")
        }
    }

    val numberList = listOf(
        NumberItem(1, "۱", "One", "ایک", "☝️"),
        NumberItem(2, "۲", "Two", "دو", "✌️"),
        NumberItem(3, "۳", "Three", "تین", "☘️"),
        NumberItem(4, "۴", "Four", "چار", "🍀"),
        NumberItem(5, "۵", "Five", "پانچ", "🖐️"),
        NumberItem(6, "۶", "Six", "چھ", "🎲"),
        NumberItem(7, "۷", "Seven", "سات", "🌈"),
        NumberItem(8, "۸", "Eight", "آٹھ", "🕷️"),
        NumberItem(9, "۹", "Nine", "نو", "🎈"),
        NumberItem(10, "۱۰", "Ten", "دس", "🔟"),
        NumberItem(11, "۱۱", "Eleven", "گیارہ", "⭐"),
        NumberItem(12, "۱۲", "Twelve", "بارہ", "🧁"),
        NumberItem(13, "۱۳", "Thirteen", "تیرہ", "🍭"),
        NumberItem(14, "۱۴", "Fourteen", "چودہ", "🍫"),
        NumberItem(15, "۱۵", "Fifteen", "پندرہ", "🍬"),
        NumberItem(16, "۱۶", "Sixteen", "سولہ", "🍓"),
        NumberItem(17, "۱۷", "Seventeen", "سترہ", "🍒"),
        NumberItem(18, "۱۸", "Eighteen", "اٹھارہ", "🍎"),
        NumberItem(19, "۱۹", "Nineteen", "انیس", "🌺"),
        NumberItem(20, "۲۰", "Twenty", "بیس", "🎉")
    )

    val storyList = listOf(
        Story(
            id = 1,
            titleUrdu = "۱. کچھوا اور خرگوش کی دوڑ",
            titleEnglish = "1. The Tortoise and the Hare",
            emoji = "🐢🐇",
            moralUrdu = "سبق: آہستہ لیکن مستقل مزاجی سے کام کرنے والا ہمیشہ جیت جاتا ہے۔",
            moralEnglish = "Moral: Slow and steady wins the race.",
            contentUrdu = """ایک خوبصورت اور سرسبز جنگل میں ایک تیز رفتار خرگوش رہتا تھا۔ خرگوش کو اپنی تیز رفتاری پر بہت گھمنڈ تھا اور وہ جنگل کے تمام جانوروں کا مذاق اڑاتا رہتا تھا۔ وہ اکثر آرام سے چلنے والے کچھوے کو دیکھ کر ہنستا کہ تم کتنی سستی سے چلتے ہو۔

ایک دن کچھوے نے تنگ آ کر خرگوش سے کہا: "اگر تمہیں اپنی رفتار پر اتنا ہی فخر ہے تو آؤ میرے ساتھ دوڑ لگا لو!" خرگوش زور سے ہنسا اور اس نے چیلنج قبول کر لیا۔ اگلے دن جنگل کے تمام جانور، جن میں بندر، لومڑی اور پرندے شامل تھے، یہ دلچسپ مقابلہ دیکھنے کے لیے ایک بڑی نہر کے کنارے جمع ہو گئے۔

لومڑی نے سیٹی بجا کر دوڑ شروع کروائی۔ خرگوش بجلی کی تیزی سے بھاگا اور پلک جھپکتے ہی نظروں سے اوجھل ہو گیا۔ کچھوا اپنی دھیمی مگر مستقل رفتار سے آگے بڑھتا رہا۔ آدھے راستے پر پہنچ کر خرگوش نے پیچھے مڑ کر دیکھا تو دور دور تک کچھوا دکھائی نہ دیا۔

خرگوش نے سوچا کہ کچھوا تو شام تک بھی یہاں نہیں پہنچ سکے گا، کیوں نہ میں ایک گھنے درخت کے سائے میں میٹھی نیند لے لوں۔ وہ درخت تلے لیٹ گیا اور ٹھنڈی ہوا کے جھونکوں سے اسے گہری نیند آ گئی۔

دوسری طرف کچھوا بغیر رکے چلتا رہا، چلتا رہا۔ وہ سوئے ہوئے خرگوش کے پاس سے خاموشی سے گزرا اور آگے نکل گیا۔ جب سورج غروب ہونے لگا تو خرگوش کی آنکھ کھلی۔ وہ ہڑبڑا کر اٹھا اور فنش لائن کی طرف دوڑا، لیکن وہاں پہنچ کر اس کے ہوش اڑ گئے! کچھوا پہلے ہی جیت چکا تھا اور سب جانور تالیاں بجا رہے تھے۔""",
            contentEnglish = """Once upon a time in a lush green forest, there lived a proud hare who bragged endlessly about his lightning speed. He continuously mocked a humble tortoise for his slow pace.

Tired of the insults, the tortoise finally challenged the hare to an official race. The hare laughed aloud and gladly accepted. All the forest animals gathered around the riverbank to witness this historic contest.

As the race began, the hare dashed forward in a flash, disappearing into the distance. The tortoise continued taking slow, measured steps forward. Reaching the midpoint, the hare looked back and saw no sign of the tortoise.

"The slowpoke won't be here anytime soon," the hare thought. "I can comfortably take a quick nap under this shady oak tree." The cool breeze soon lulled the arrogant hare into a deep sleep.

Meanwhile, the patient tortoise never stopped. He steadily marched on, passed the snoozing hare without disturbing him, and walked steadily toward the finish ribbon. By the time the hare awoke with the setting sun, it was too late—the tortoise had already won!"""
        ),
        Story(
            id = 2,
            titleUrdu = "۲. پیاسا کوا اور عقلمندی",
            titleEnglish = "2. The Thirsty Crow",
            emoji = "🦅💧",
            moralUrdu = "سبق: جہاں چاہ، وہاں راہ۔ عقل اور محنت سے ہر مشکل حل ہو جاتی ہے۔",
            moralEnglish = "Moral: Where there is a will, there is a way.",
            contentUrdu = """گرمیوں کا شدید ترین دن تھا اور سورج آگ برسا رہا تھا۔ جنگل کے تمام تالاب اور ندی نالے خشک ہو چکے تھے۔ ایک کوا سخت پیاس کی حالت میں پانی کی تلاش میں مارا مارا پھر رہا تھا۔ اس کا گلا پیاس کی شدت سے کانٹا بن چکا تھا۔

اڑتے اڑتے وہ ایک خوبصورت باغ میں پہنچا۔ وہاں ایک بڑے پھولوں والے درخت کے نیچے اسے ایک مٹی کا گھڑا نظر آیا۔ کوا خوشی سے چہکتا ہوا گھڑے کی منڈیر پر جا بیٹھا۔ لیکن جب اس نے اندر جھانکا تو اس کا دل ٹوٹ گیا؛ پانی بہت کم تھا اور گھڑے کی تہہ میں تھا جہاں اس کی چونچ نہیں پہنچ پا رہی تھی۔

کوے نے ہمت نہیں ہاری۔ اس نے ادھر ادھر دیکھا تو اسے گھڑے کے قریب سفید اور چھوٹے کنکریاں پڑی نظر آئیں۔ کوے کے ذہن میں ایک شاندار تدبیر آئی۔ اس نے اپنی چونچ سے ایک ایک کر کے کنکری اٹھانا شروع کی اور گھڑے میں ڈالنا شروع کر دیا۔

جیسے جیسے کنکریاں اندر گرتی گئیں، پانی آہستہ آہستہ اوپر آتا گیا۔ کوے نے گھنٹوں محنت کی اور تھکاوٹ کی پرواہ نہ کی۔ بالآخر پانی گھڑے کے بالکل منہ تک پہنچ گیا۔ عقلمند کوے نے جی بھر کر ٹھنڈا اور میٹھا پانی پیا، اللہ کا شکر ادا کیا اور خوشی خوشی آسمان کی طرف اڑ گیا۔""",
            contentEnglish = """It was a scorching summer afternoon, and the heat was unbearable. Water streams and ponds had completely dried up. A thirsty crow flew across the valley searching desperately for a single drop of water.

Exhausted, he landed in a vibrant garden where he noticed a clay pitcher resting beneath a flowering bush. Hopeful, the crow perched on the rim and peered inside. To his dismay, there was only a tiny puddle of water at the very bottom, far out of reach of his beak.

He tried pushing the pitcher over, but it was too heavy. Giving up was not an option. Looking around carefully, he noticed a scattered pile of smooth pebbles on the walkway. An ingenious idea struck his mind.

One by one, the determined crow picked up the pebbles and dropped them into the pitcher. With each pebble, the water level gradually rose. Through persistent effort, the water reached the top. The clever bird quenched his thirst and took off into the cool sky happily."""
        ),
        Story(
            id = 3,
            titleUrdu = "۳. چیونٹی اور فاختہ کی دوستی",
            titleEnglish = "3. The Ant and the Dove",
            emoji = "🐜🕊️",
            moralUrdu = "سبق: نیکی کا بدلہ نیکی ہے۔ دوسروں کی مدد کرو گے تو اللہ تمہاری مدد کرے گا۔",
            moralEnglish = "Moral: Kindness is always rewarded.",
            contentUrdu = """ایک شفاف اور تیز بہنے والی نہر کے کنارے گھنے درختوں کی چھاؤں میں ایک چھوٹی سی چیونٹی خوراک تلاش کر رہی تھی۔ چلتے چلتے اسے پیاس لگی تو وہ نہر کے کنارے پانی پینے کے لیے جھکی۔ اچانک تیز ہوا چلی اور اس کا پیر پھسلا، اور وہ تیز لہروں کے بیچ جا گری۔

چیونٹی نے خود کو بچانے کے لیے ہاتھ پاؤں مارے مگر پانی کا بہاؤ بہت تیز تھا اور وہ ڈوبنے کے قریب تھی۔ اسی درخت کی شاخ پر بیٹھی ایک رحم دل فاختہ یہ سارا منظر دیکھ رہی تھی۔ فاختہ کو ننھی چیونٹی پر بہت ترس آیا۔ اس نے فورا اپنی چونچ سے ایک بڑا پتہ توڑا اور چیونٹی کے بالکل قریب پانی میں گرا دیا۔

چیونٹی نے تمام طاقت جمع کی اور تیر کر پتے پر چڑھ گئی۔ ہوا کے رخ نے پتے کو بحفاظت نہر کے کنارے خشک گھاس پر پہنچا دیا۔ چیونٹی نے سر اٹھا کر فاختہ کا دل سے شکریہ ادا کیا۔

کچھ دنوں بعد ایک شکاری جنگل میں آیا۔ اس نے جال بچھایا اور فاختہ کو نشانہ بنانے کے لیے اپنی بندوق تان لی۔ فاختہ اس خطرے سے بالکل بے خبر گھونسلے میں بیٹھی تھی۔ چیونٹی نے یہ دیکھا تو وہ بجلی کی تیزی سے دوڑی اور شکاری کے ننگے پاؤں پر اتنے زور سے کاٹا کہ شکاری کے منہ سے چیخ نکل گئی اور اس کا نشانہ چوک گیا۔ بندوق کی آواز سن کر فاختہ ہوشیار ہو کر اڑ گئی اور اس طرح چیونٹی نے فاختہ کی جان بچا لی۔""",
            contentEnglish = """On a sunny afternoon beside a crystal-clear stream, a tiny ant was hunting for food. Feeling thirsty, she walked down to the water's edge to drink. A gust of wind suddenly blew her into the rushing stream!

The helpless ant paddled vigorously, but the water current was far too fierce, and she was on the verge of drowning. Perched up high on a leafy branch, a compassionate dove witnessed the life-and-death struggle. Feeling deep sympathy, the dove plucked a large green leaf and dropped it into the water right next to the ant.

The ant crawled onto the leaf, using it like a boat until it drifted safely to the grassy bank. She looked up and bowed to thank the kind bird.

A few days later, a hunter snuck into the woods, aiming his bow directly at the resting dove. Observing the grave danger, the brave ant scurried swiftly and bit the hunter's ankle with all her might! The hunter yelled in pain, spoiling his shot, and the dove flew away safely."""
        )
    )
}
'''

with open("app/src/main/java/com/nanhesitare/app/data/Models.kt", "w", encoding="utf-8") as f:
    f.write(content)

print("SUCCESS: Models.kt with full stories, qaida, and numbers updated!")
