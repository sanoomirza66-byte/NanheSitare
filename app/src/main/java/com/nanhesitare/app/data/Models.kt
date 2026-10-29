package com.nanhesitare.app.data

data class LearnItem(val id: Int, val title: String, val subTitle: String, val details: String, val icon: String)
data class StoryPage(val pageNumber: Int, val pageContent: String)
data class StoryItem(val id: Int, val title: String, val pages: List<StoryPage>, val moral: String)

object AppData {
    // Mukammal Urdu Qaida (37 Huroof)
    val urduQaida = listOf(
        LearnItem(1, "آ", "آم", "آ سے آم - پھلوں کا بادشاہ", "🥭"),
        LearnItem(2, "ا", "انار", "ا سے انار - میٹھا اور سرخ", "🍎"),
        LearnItem(3, "ب", "بلی", "ب سے بلی - پیاری سی بلی", "🐱"),
        LearnItem(4, "پ", "پتنگ", "پ سے پتنگ - اونچی اڑے پتنگ", "🪁"),
        LearnItem(5, "ت", "تتلی", "ت سے تتلی - رنگ برنگی تتلی", "🦋"),
        LearnItem(6, "ٹ", "ٹماٹر", "ٹ سے ٹماٹر - لال لال ٹماٹر", "🍅"),
        LearnItem(7, "ث", "ثمر", "ث سے ثمر - مزیدار پھل", "🍇"),
        LearnItem(8, "ج", "جہاز", "ج سے جہاز - بادلوں میں اڑے", "✈️"),
        LearnItem(9, "چ", "چڑیا", "چ سے چڑیا - چوں چوں کرتی چڑیا", "🐦"),
        LearnItem(10, "ح", "حلوہ", "ح سے حلوہ - میٹھا اور گرم", "🍲"),
        LearnItem(11, "خ", "خرگوش", "خ سے خرگوش - سفید اور تیز خرگوش", "🐰"),
        LearnItem(12, "د", "درخت", "د سے درخت - سایہ دار درخت", "🌳"),
        LearnItem(13, "ڈ", "ڈولفن", "ڈ سے ڈولفن - پانی کی پیاری مچھلی", "🐬"),
        LearnItem(14, "ذ", "ذخیرہ", "ذ سے ذخیرہ - علم کا ذخیرہ", "📚"),
        LearnItem(15, "ر", "ریل گاڑی", "ر سے ریل - چھک چھک ریل گاڑی", "🚂"),
        LearnItem(16, "ڑ", "پہاڑ", "ڑ سے پہاڑ - اونچے اونچے پہاڑ", "⛰️"),
        LearnItem(17, "ز", "زرافہ", "ز سے زرافہ - لمبی گردن والا", "🦒"),
        LearnItem(18, "ژ", "ژالہ باری", "ژ سے ژالہ باری - اولے پڑنا", "🌨️"),
        LearnItem(19, "س", "سیب", "س سے سیب - صحت کے لیے بہترین", "🍏"),
        LearnItem(20, "ش", "شیر", "ش سے شیر - جنگل کا راجہ", "🦁"),
        LearnItem(21, "ص", "صابن", "ص سے صابن - ہاتھ صاف رکھیں", "🧼"),
        LearnItem(22, "ض", "ضعیف", "ض سے ضعیف - بزرگوں کی عزت کریں", "👴"),
        LearnItem(23, "ط", "طوطا", "ط سے طوطا - مٹھو مٹھو بولے", "🦜"),
        LearnItem(24, "ظ", "ظروف", "ظ سے ظروف - خوبصورت برتن", "🏺"),
        LearnItem(25, "ع", "عینک", "ع سے عینک - نظر کی عینک", "👓"),
        LearnItem(26, "غ", "غبارہ", "غ سے غبارہ - رنگ برنگا غبارہ", "🎈"),
        LearnItem(27, "ف", "فوارہ", "ف سے فوارہ - ٹھنڈے پانی کا فوارہ", "⛲"),
        LearnItem(28, "ق", "قلم", "ق سے قلم - لکھنے کا قلم", "✒️"),
        LearnItem(29, "ک", "کتاب", "ک سے کتاب - علم کا خزانہ", "📖"),
        LearnItem(30, "گ", "گائے", "گ سے گائے - دودھ دینے والی گائے", "🐄"),
        LearnItem(31, "ل", "لومڑی", "ل سے لومڑی - چالاک لومڑی", "🦊"),
        LearnItem(32, "م", "مچھلی", "م سے مچھلی - جل کی رانی ہے", "🐟"),
        LearnItem(33, "ن", "نلکا", "ن سے نلکا - صاف پانی پئیں", "🚰"),
        LearnItem(34, "و", "ورزش", "و سے ورزش - صحت مند جسم", "🏃"),
        LearnItem(35, "ہ", "ہاتھی", "ہ سے ہاتھی - بڑا سا ہاتھی", "🐘"),
        LearnItem(36, "ء", "ہمزہ", "ء سے چائے، گائے", "☕"),
        LearnItem(37, "ی", "یکہ", "ی سے یکہ - گھوڑا گاڑی", "🐎")
    )

    // Mukammal English ABC (A to Z)
    val englishAbc = listOf(
        LearnItem(1, "A", "Apple", "A for Apple - Sweet & Red", "🍎"),
        LearnItem(2, "B", "Ball", "B for Ball - Let's play", "⚽"),
        LearnItem(3, "C", "Cat", "C for Cat - Meow Meow", "🐱"),
        LearnItem(4, "D", "Dog", "D for Dog - Loyal Friend", "🐶"),
        LearnItem(5, "E", "Elephant", "E for Elephant - Big and Strong", "🐘"),
        LearnItem(6, "F", "Fish", "F for Fish - Swimming in water", "🐟"),
        LearnItem(7, "G", "Grapes", "G for Grapes - Juicy & Sweet", "🍇"),
        LearnItem(8, "H", "Horse", "H for Horse - Runs very fast", "🐎"),
        LearnItem(9, "I", "Ice Cream", "I for Ice Cream - Cold and Yummy", "🍦"),
        LearnItem(10, "J", "Jug", "J for Jug - Filled with water", "🥛"),
        LearnItem(11, "K", "Kite", "K for Kite - Flying in the sky", "🪁"),
        LearnItem(12, "L", "Lion", "L for Lion - King of the jungle", "🦁"),
        LearnItem(13, "M", "Mango", "M for Mango - King of fruits", "🥭"),
        LearnItem(14, "N", "Nest", "N for Nest - Little bird's home", "🪺"),
        LearnItem(15, "O", "Orange", "O for Orange - Tasty citrus fruit", "🍊"),
        LearnItem(16, "P", "Parrot", "P for Parrot - Green and sweet", "🦜"),
        LearnItem(17, "Q", "Queen", "Q for Queen - Wears a crown", "👑"),
        LearnItem(18, "R", "Rabbit", "R for Rabbit - Loves carrots", "🐰"),
        LearnItem(19, "S", "Sun", "S for Sun - Bright and warm", "☀️"),
        LearnItem(20, "T", "Tiger", "T for Tiger - Powerful and fast", "🐯"),
        LearnItem(21, "U", "Umbrella", "U for Umbrella - Saves from rain", "☂️"),
        LearnItem(22, "V", "Van", "V for Van - Taking kids to school", "🚐"),
        LearnItem(23, "W", "Watch", "W for Watch - Tells the time", "⌚"),
        LearnItem(24, "X", "Xylophone", "X for Xylophone - Musical instrument", "🎵"),
        LearnItem(25, "Y", "Yak", "Y for Yak - Living in the snow", "🐂"),
        LearnItem(26, "Z", "Zebra", "Z for Zebra - Black and white stripes", "🦓")
    )

    // Mukammal Ginti (1 se 20 tak)
    val ginti = listOf(
        LearnItem(1, "۱ - 1", "ایک (One)", "1 چاند آسمان پر چمکتا ہے", "🌙"),
        LearnItem(2, "۲ - 2", "دو (Two)", "2 آنکھیں جن سے ہم دیکھتے ہیں", "👀"),
        LearnItem(3, "۳ - 3", "تین (Three)", "3 رنگ ٹریفک لائٹ میں ہوتے ہیں", "🚦"),
        LearnItem(4, "۴ - 4", "چار (Four)", "4 پہیے گاڑی کے ہوتے ہیں", "🚗"),
        LearnItem(5, "۵ - 5", "پانچ (Five)", "5 انگلیاں ہاتھ کی", "🖐️"),
        LearnItem(6, "۶ - 6", "چھ (Six)", "6 ٹانگیں مکھی کی ہوتی ہیں", "🐝"),
        LearnItem(7, "۷ - 7", "سات (Seven)", "7 رنگ قوسِ قزح کے", "🌈"),
        LearnItem(8, "۸ - 8", "آٹھ (Eight)", "8 ٹانگیں آکٹوپس کی", "🐙"),
        LearnItem(9, "۹ - 9", "نو (Nine)", "9 ستارے رات کو چمکتے ہیں", "✨"),
        LearnItem(10, "۱۰ - 10", "دس (Ten)", "10 انگلیاں دونوں ہاتھوں کی", "👐"),
        LearnItem(11, "۱۱ - 11", "گیارہ (Eleven)", "11 کھلاڑی کرکٹ ٹیم کے", "🏏"),
        LearnItem(12, "۱۲ - 12", "بارہ (Twelve)", "12 گھنٹے گھڑی کے ڈائل پر", "⏰"),
        LearnItem(13, "۱۳ - 13", "تیرہ (Thirteen)", "13 چمکتے ہوئے جگنو", "🪲"),
        LearnItem(14, "۱۴ - 14", "چودہ (Fourteen)", "14 اگست آزادی کا دن", "🇵🇰"),
        LearnItem(15, "۱۵ - 15", "پندرہ (Fifteen)", "15 میٹھے اور مزیدار سیب", "🍎"),
        LearnItem(16, "۱۶ - 16", "سولہ (Sixteen)", "16 رنگین پنسلیں", "✏️"),
        LearnItem(17, "۱۷ - 17", "سترہ (Seventeen)", "17 خوبصورت تتلیاں", "🦋"),
        LearnItem(18, "۱۸ - 18", "اٹھارہ (Eighteen)", "18 سفید گلاب کے پھول", "🌹"),
        LearnItem(19, "۱۹ - 19", "انیس (Nineteen)", "19 چھوٹی مچھلیاں", "🐟"),
        LearnItem(20, "۲۰ - 20", "بیس (Twenty)", "20 سنہری ستارے", "⭐")
    )

    // Lambi 2-Pages Wali Kahaniyan
    val stories = listOf(
        StoryItem(
            id = 1,
            title = "پیاسا کوا (The Thirsty Crow)",
            pages = listOf(
                StoryPage(
                    1,
                    "ایک دفعہ کا ذکر ہے کہ گرمیوں کے دن تھے اور سخت دھوپ تھی۔ ایک کوا بہت پیاسا تھا اور پانی کی تلاش میں اِدھر اُدھر جنگل اور بستیوں میں اڑ رہا تھا۔ اس کا گلا سوکھ رہا تھا لیکن اسے کہیں پانی نظر نہیں آ رہا تھا۔

تھک ہار کر جب وہ ایک خوبصورت باغ میں پہنچا تو اسے درخت کے نیچے ایک مٹکا نظر آیا۔ کوا خوشی سے اڑ کر مٹکے کے پاس پہنچا اور اندر جھانکا۔ مٹکے میں پانی تو تھا لیکن پانی کی سطح اتنی نیچے تھی کہ کوے کی چونچ وہاں تک نہ پہنچ سکی۔"
                ),
                StoryPage(
                    2,
                    "کوے نے ہمت نہ ہاری اور سوچنے لگا کہ پانی تک کیسے پہنچا جائے۔ اچانک اس کی نظر مٹکے کے پاس پڑے ہوئے چھوٹے چھوٹے کنکروں پر پڑی۔ اس کے ذہن میں ایک بہترین ترکیب آئی!

اس نے اپنی چونچ سے ایک ایک کر کے کنکر اٹھائے اور مٹکے میں ڈالنا شروع کیے۔ جوں جوں کنکر نیچے بیٹھتے گئے، پانی آہستہ آہستہ اوپر آتا گیا۔ آخر کار پانی مٹکے کے منہ تک پہنچ گیا۔ ہوشیار کوے نے جی بھر کر ٹھنڈا پانی پیا، اللہ کا شکر ادا کیا اور خوشی سے آسمان میں اڑ گیا۔"
                )
            ),
            moral = "نتیجہ: عقل اور محنت سے ہر مشکل کا حل نکل آتا ہے۔"
        ),
        StoryItem(
            id = 2,
            title = "خرگوش اور کچھوا (The Hare & Tortoise)",
            pages = listOf(
                StoryPage(
                    1,
                    "ایک جنگل میں ایک تیز رفتار خرگوش رہتا تھا۔ اسے اپنی تیز رفتاری پر بہت غرور تھا اور وہ جنگل کے تمام چھوٹے جانوروں کا مذاق اڑایا کرتا تھا۔ ایک دن اس نے ایک سست رفتار کچھوے کو دیکھا اور ہنس کر بولا: 'تم کتنی سستی سے چلتے ہو! اگر تم سے دوڑ لگائی جائے تو میں آنکھ جھپکتے ہی جیت جاؤں گا۔'

کچھوے نے پرسکون انداز میں جواب دیا: 'غرور مت کرو، چلو آج ایک پہاڑی تک دوڑ لگا کر دیکھ لیتے ہیں کہ کون جیتتا ہے۔' خرگوش ہنسا اور اس نے چیلنج فوراً قبول کر لیا۔ جنگل کے باقی جانور دوڑ دیکھنے جمع ہو گئے۔"
                ),
                StoryPage(
                    2,
                    "دوڑ شروع ہوئی تو خرگوش بجلی کی تیزی سے بھاگا اور بہت آگے نکل گیا۔ آدھا راستہ طے کرنے کے بعد اس نے مڑ کر دیکھا تو کچھوا کہیں دور دور تک نظر نہ آیا۔ مغرور خرگوش نے سوچا: 'کچھوا تو شام تک بھی یہاں نہیں پہنچ سکے گا، کیوں نہ میں اس گھنے درخت کے نیچے تھوڑی دیر آرام کر لوں۔'

ٹھنڈی ہوا میں خرگوش کو گہری نیند آ گئی۔ دوسری طرف، کچھوا بنا رکے، آہستہ آہستہ لیکن لگاتار اپنی منزل کی طرف چلتا رہا۔ وہ سوئے ہوئے خرگوش کے پاس سے خاموشی سے گزرا اور پہاڑی کی فائنل لائن پار کر گیا۔ جب خرگوش کی آنکھ کھلی تو کچھوا جیت چکا تھا۔ خرگوش شرمندہ ہو گیا۔"
                )
            ),
            moral = "نتیجہ: مسلسل محنت اور استقلال ہمیشہ غرور پر فتح پاتے ہیں۔"
        )
    )
}
