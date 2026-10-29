import re

content = '''package com.nanhesitare.app.data

data class QaidaItem(
    val letter: String,
    val word: String,
    val wordEn: String,
    val imageRes: String
)

data class AlphabetItem(
    val letter: String,
    val word: String,
    val imageRes: String
)

data class CountingItem(
    val number: Int,
    val numberUrdu: String,
    val wordUrdu: String,
    val wordEn: String
)

data class Story(
    val id: Int,
    val titleUrdu: String,
    val titleEn: String,
    val emoji: String,
    val moralUrdu: String,
    val moralEn: String,
    val storyUrdu: String,
    val storyEn: String
)

object AppData {

    // 1. مکمل اردو قاعدہ (37 حروف)
    val qaidaList = listOf(
        QaidaItem("ا", "انار", "Pomegranate", "ic_anar"),
        QaidaItem("ب", "بلی", "Cat", "ic_billi"),
        QaidaItem("پ", "پنکھا", "Fan", "ic_pankha"),
        QaidaItem("ت", "تتلی", "Butterfly", "ic_titli"),
        QaidaItem("ٹ", "ٹماٹر", "Tomato", "ic_tamatar"),
        QaidaItem("ث", "ثمر", "Fruit", "ic_samar"),
        QaidaItem("ج", "جہاز", "Aeroplane", "ic_jahaz"),
        QaidaItem("چ", "چڑیا", "Sparrow", "ic_chiriya"),
        QaidaItem("ح", "حلوہ", "Halwa", "ic_halwa"),
        QaidaItem("خ", "خرگوش", "Rabbit", "ic_khargosh"),
        QaidaItem("د", "درخت", "Tree", "ic_darakht"),
        QaidaItem("ڈ", "ڈول", "Bucket", "ic_dhol"),
        QaidaItem("ذ", "ذخیرہ", "Treasure", "ic_zakheera"),
        QaidaItem("ر", "ریل", "Train", "ic_rail"),
        QaidaItem("ڑ", "پہاڑ", "Mountain", "ic_pahar"),
        QaidaItem("ز", "زرافہ", "Giraffe", "ic_zarafa"),
        QaidaItem("ژ", "ژالہ باری", "Hailstorm", "ic_zhala"),
        QaidaItem("س", "سیب", "Apple", "ic_saib"),
        QaidaItem("ش", "شیر", "Lion", "ic_shair"),
        QaidaItem("ص", "صوفہ", "Sofa", "ic_sofa"),
        QaidaItem("ض", "ضعیف", "Old Man", "ic_zaeef"),
        QaidaItem("ط", "طوطا", "Parrot", "ic_tota"),
        QaidaItem("ظ", "ظروف", "Utensils", "ic_zaroof"),
        QaidaItem("ع", "عینک", "Glasses", "ic_ainak"),
        QaidaItem("غ", "غبارہ", "Balloon", "ic_ghubara"),
        QaidaItem("ف", "فوارہ", "Fountain", "ic_fowara"),
        QaidaItem("ق", "قلم", "Pen", "ic_qalam"),
        QaidaItem("ک", "کتاب", "Book", "ic_kitab"),
        QaidaItem("گ", "گائے", "Cow", "ic_gaye"),
        QaidaItem("ل", "لومڑی", "Fox", "ic_loomri"),
        QaidaItem("م", "مچھلی", "Fish", "ic_machli"),
        QaidaItem("ن", "نل", "Tap", "ic_nal"),
        QaidaItem("و", "ورق", "Leaf/Page", "ic_waraq"),
        QaidaItem("ہ", "ہاتھی", "Elephant", "ic_hathi"),
        QaidaItem("ء", "چائے", "Tea", "ic_chaye"),
        QaidaItem("ی", "یکہ", "Cart", "ic_yakka"),
        QaidaItem("ے", "لڑکے", "Boys", "ic_larkay")
    )

    // 2. مکمل انگریزی ABC (26 حروف)
    val alphabetList = listOf(
        AlphabetItem("A", "Apple", "ic_apple"),
        AlphabetItem("B", "Ball", "ic_ball"),
        AlphabetItem("C", "Cat", "ic_cat"),
        AlphabetItem("D", "Dog", "ic_dog"),
        AlphabetItem("E", "Elephant", "ic_elephant"),
        AlphabetItem("F", "Fish", "ic_fish"),
        AlphabetItem("G", "Grapes", "ic_grapes"),
        AlphabetItem("H", "Horse", "ic_horse"),
        AlphabetItem("I", "Ice Cream", "ic_icecream"),
        AlphabetItem("J", "Juice", "ic_juice"),
        AlphabetItem("K", "Kite", "ic_kite"),
        AlphabetItem("L", "Lion", "ic_lion"),
        AlphabetItem("M", "Mango", "ic_mango"),
        AlphabetItem("N", "Nest", "ic_nest"),
        AlphabetItem("O", "Orange", "ic_orange"),
        AlphabetItem("P", "Parrot", "ic_parrot"),
        AlphabetItem("Q", "Queen", "ic_queen"),
        AlphabetItem("R", "Rabbit", "ic_rabbit"),
        AlphabetItem("S", "Sun", "ic_sun"),
        AlphabetItem("T", "Tiger", "ic_tiger"),
        AlphabetItem("U", "Umbrella", "ic_umbrella"),
        AlphabetItem("V", "Van", "ic_van"),
        AlphabetItem("W", "Watch", "ic_watch"),
        AlphabetItem("X", "Xylophone", "ic_xylophone"),
        AlphabetItem("Y", "Yak", "ic_yak"),
        AlphabetItem("Z", "Zebra", "ic_zebra")
    )

    // 3. مکمل گنتی (1 تا 20)
    val countingList = listOf(
        CountingItem(1, "۱", "ایک", "One"),
        CountingItem(2, "۲", "دو", "Two"),
        CountingItem(3, "۳", "تین", "Three"),
        CountingItem(4, "۴", "چار", "Four"),
        CountingItem(5, "۵", "پانچ", "Five"),
        CountingItem(6, "۶", "چھ", "Six"),
        CountingItem(7, "۷", "سات", "Seven"),
        CountingItem(8, "۸", "آٹھ", "Eight"),
        CountingItem(9, "۹", "نو", "Nine"),
        CountingItem(10, "۱۰", "دس", "Ten"),
        CountingItem(11, "۱۱", "گیارہ", "Eleven"),
        CountingItem(12, "۱۲", "بارہ", "Twelve"),
        CountingItem(13, "۱۳", "تیرہ", "Thirteen"),
        CountingItem(14, "۱۴", "چودہ", "Fourteen"),
        CountingItem(15, "۱۵", "پندرہ", "Fifteen"),
        CountingItem(16, "۱۶", "سولہ", "Sixteen"),
        CountingItem(17, "۱۷", "سترہ", "Seventeen"),
        CountingItem(18, "۱۸", "اٹھارہ", "Eighteen"),
        CountingItem(19, "۱۹", "انیس", "Nineteen"),
        CountingItem(20, "۲۰", "بیس", "Twenty")
    )

    // 4. بڑی کہانیاں (مکمل 2 صفحات / تفصیلی پیراگراف)
    val storyList = listOf(
        Story(
            1,
            "۱. کچھوا اور خرگوش",
            "1. Tortoise and Hare",
            "🐢🐇",
            "سبق: غرور کا سر نیچا، اور مستقل مزاجی سے کامیابی ملتی ہے۔",
            "Moral: Slow and steady wins the race.",
            "ایک خوبصورت جنگل میں ایک تیز رفتار خرگوش رہتا تھا۔ اُسے اپنی تیز رفتاری پر بہت ناز تھا اور وہ اکثر سست رفتار کچھوے کا مذاق اڑاتا رہتا تھا۔ ایک دن تنگ آ کر کچھوے نے خرگوش سے کہا کہ چلو آج ایک دوڑ لگاتے ہیں، دیکھتے ہیں کون پہلے جھیل کے پار پہنچتا ہے۔ خرگوش ہنس پڑا اور اس نے چیلنج قبول کر لیا۔\n\nدوڑ شروع ہوتے ہی خرگوش بجلی کی تیزی سے بھاگا اور بہت آگے نکل گیا۔ آدھے راستے پر پہنچ کر خرگوش نے پیچھے مڑ کر دیکھا تو کچھوا کہیں نظر نہ آیا۔ خرگوش نے سوچا کہ کچھوا تو بہت پیچھے ہے، کیوں نہ گھنے درخت کی ٹھنڈی چھاؤں میں تھوڑی دیر سستا لوں۔ خرگوش بیٹھا اور ٹھنڈی ہوا سے اُس کی گہری آنکھ لگ گئی۔ دوسری طرف کچھوا بغیر رکے، آہستہ آہستہ مستقل مزاجی سے چلتا رہا۔ وہ خرگوش کے پاس سے گزرا مگر رکا نہیں۔ جب خرگوش کی آنکھ کھلی تو سورج ڈھل رہا تھا۔ وہ گھبرا کر منزل کی طرف دوڑا، لیکن تب تک کچھوا جیت کی لکیر پار کر چکا تھا۔ خرگوش شرم سے پانی پانی ہو گیا۔",
            "Once upon a time in a jungle, an arrogant hare made fun of a slow tortoise. The tortoise challenged him to a race. The hare dashed ahead and decided to take a nap under a shady tree. Meanwhile, the tortoise walked slowly and steadily without stopping. When the hare woke up, the tortoise had already crossed the finish line."
        ),
        Story(
            2,
            "۲. پیاسا کوا",
            "2. Thirsty Crow",
            "🦅💧",
            "سبق: جہاں چاہ وہاں راہ، محنت کبھی رائیگاں نہیں جاتی۔",
            "Moral: Where there is a will, there is a way.",
            "ایک دفعہ کا ذکر ہے کہ شدید گرمی کا موسم تھا اور دھوپ بہت تیز تھی۔ ایک کوا پیاس سے نڈھال ہو کر ادھر ادھر پانی کی تلاش میں اڑ رہا تھا۔ جنگل کے سب چشمے خشک ہو چکے تھے۔ اڑتے اڑتے وہ ایک باغ میں پہنچا جہاں ایک درخت کے نیچے مٹی کا ایک بڑا گھڑا رکھا ہوا تھا۔ کوا خوشی سے اڑ کر گھڑے کے کنارے پر جا بیٹھا۔ جب اس نے جھانک کر دیکھا تو گھڑے کے پیندے میں تھوڑا سا پانی تھا، لیکن اس کی چونچ پانی تک نہیں پہنچ پا رہی تھی۔\n\nکوے نے پہلے گھڑے کو گرانے کی کوشش کی مگر گھڑا وزنی تھا۔ اس نے ہمت نہ ہاری اور آس پاس دیکھا تو باغ میں چھوٹے چھوٹے کنکر اور پتھر بکھرے ہوئے ملے۔ کوے کے ذہن میں ایک شاندار ترکیب آئی۔ اس نے ایک ایک کنکر اٹھا کر گھڑے میں ڈالنا شروع کیا۔ جوں جوں کنکر اندر گرتے گئے، پانی آہستہ آہستہ اوپر آتا گیا۔ کچھ دیر کی محنت کے بعد پانی گھڑے کے منہ تک پہنچ گیا۔ کوے نے جی بھر کر ٹھنڈا پانی پیا، اپنی پیاس بجھائی اور اللّٰہ کا شکر ادا کرتے ہوئے خوشی سے آسمان کی طرف اڑ گیا۔",
            "On a very hot summer day, a thirsty crow was flying in search of water. He found a pitcher in a garden with very little water at the bottom. His beak could not reach it. He saw pebbles on the ground and started dropping them into the pitcher one by one. The water level rose to the top. The clever crow drank the water and flew away happily."
        ),
        Story(
            3,
            "۳. فاختہ اور چیونٹی",
            "3. Ant and Dove",
            "🕊️🐜",
            "سبق: نیکی کا بدلہ ہمیشہ نیکی ہوتا ہے۔",
            "Moral: One good deed deserves another.",
            "ایک دریا کے کنارے بڑے درخت پر ایک رحم دل فاختہ کا گھونسلا تھا۔ ایک دوپہر ایک ننھی چیونٹی دریا کے کنارے پانی پینے آئی، لیکن پاؤں پھسلنے کی وجہ سے وہ تیز لہروں میں گر گئی۔ چیونٹی پانی میں ڈوبنے لگی اور جان بچانے کے لیے ہاتھ پاؤں مارنے لگی۔ درخت پر بیٹھی فاختہ نے یہ منظر دیکھا تو اُسے چیونٹی پر بڑا ترس آیا۔ اس نے فوراً درخت سے ایک خشک پتہ توڑا اور پانی میں چیونٹی کے بالکل قریب گرا دیا۔ چیونٹی ہمت کر کے پتے پر چڑھ گئی اور پتہ تیرتا ہوا کنارے پر لگ گیا، یوں چیونٹی کی جان بچ گئی۔\n\nکچھ دنوں بعد ایک شکاری جنگل میں آیا اور اس نے درخت پر بیٹھی معصوم فاختہ پر اپنی بندوق کا نشانہ باندھ لیا۔ فاختہ اس خطرے سے بالکل بے خبر تھی۔ وہ ننھی چیونٹی پاس ہی موجود تھی، اس نے شکاری کو دیکھا تو فوراً رینگ کر شکاری کے ننگے پاؤں پر زور سے کاٹ لیا۔ شکاری درد سے چیخ اٹھا اور اس کا نشانہ چوک گیا۔ گولی کی آواز سن کر فاختہ چوکنا ہو گئی اور اڑ کر اپنی جان بچا لی۔ یوں ننھی چیونٹی نے فاختہ کے احسان کا بدلہ چکا دیا۔",
            "An ant fell into a river and was about to drown. A kind dove dropped a leaf into the water, saving the ant. A few days later, a hunter aimed at the dove. The ant bit the hunter's foot, his aim was spoiled, and the dove flew to safety."
        )
    )
}
'''

path = "app/src/main/java/com/nanhesitare/app/data/Models.kt"
with open(path, "w", encoding="utf-8") as f:
    f.write(content)

print("SUCCESS: Models.kt is fully updated!")
