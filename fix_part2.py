import subprocess

part2_code = '''
@Composable
fun TopBar(title: String, onBack: () -> Unit) {
    Surface(shape = RoundedCornerShape(20.dp), color = Color.White.copy(alpha = 0.94f), shadowElevation = 6.dp, modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)) {
            IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color(0xFF1565C0)) }
            Text(text = title, fontSize = 21.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1565C0), modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.width(48.dp))
        }
    }
}

@Composable
fun HomeScreen(onNavigate: (AppScreen) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().statusBarsPadding().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(shape = RoundedCornerShape(24.dp), color = Color.White.copy(alpha = 0.94f), shadowElevation = 8.dp, modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(14.dp)) {
                Text("🌟 ننھے ستارے 🌟", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1565C0))
                Text("سیکھیں اور کھیلیں - بچوں کا تعلیمی ساتھی", fontSize = 14.sp, color = Color(0xFF555555))
            }
        }
        Spacer(modifier = Modifier.height(14.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(2), horizontalArrangement = Arrangement.spacedBy(14.dp), verticalArrangement = Arrangement.spacedBy(14.dp), modifier = Modifier.fillMaxWidth().weight(1f)) {
            item { MenuCard("اردو قاعدہ", "Urdu Qaida (37)", "📖", Color(0xFFFFE0B2), Color(0xFFFF9800)) { onNavigate(AppScreen.QAIDA) } }
            item { MenuCard("اے بی سی", "ABC (A-Z)", "🔤", Color(0xFFE1BEE7), Color(0xFFAB47BC)) { onNavigate(AppScreen.ABC) } }
            item { MenuCard("گنتی سیکھیں", "Numbers (1-20)", "🔢", Color(0xFFC8E6C9), Color(0xFF4CAF50)) { onNavigate(AppScreen.GINTI) } }
            item { MenuCard("کہانیاں", "Moral Stories", "📚", Color(0xFFFFCDD2), Color(0xFFE57373)) { onNavigate(AppScreen.STORIES) } }
        }
    }
}

@Composable
fun MenuCard(titleUrdu: String, titleEng: String, emoji: String, bgColor: Color, borderColor: Color, onClick: () -> Unit) {
    Surface(onClick = onClick, shape = RoundedCornerShape(22.dp), color = bgColor.copy(alpha = 0.95f), border = BorderStroke(2.dp, borderColor), shadowElevation = 5.dp, modifier = Modifier.fillMaxWidth().height(150.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.padding(12.dp)) {
            Text(text = emoji, fontSize = 40.sp)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = titleUrdu, fontSize = 19.sp, fontWeight = FontWeight.Bold, color = Color(0xFF263238), textAlign = TextAlign.Center)
            Text(text = titleEng, fontSize = 12.sp, color = Color(0xFF455A64), textAlign = TextAlign.Center)
        }
    }
}

val fullQaidaList = listOf(
    QaidaItem("ا", "انار", "Alif - Anaar", 0xFFFF5722), QaidaItem("ب", "بلی", "Bay - Billi", 0xFFE91E63), QaidaItem("پ", "پتنگ", "Pay - Patang", 0xFF9C27B0),
    QaidaItem("ت", "تتلی", "Tay - Titli", 0xFF673AB7), QaidaItem("ٹ", "ٹماٹر", "Ttay - Tamatar", 0xFF3F51B5), QaidaItem("ث", "ثمر", "Seh - Samar", 0xFF2196F3),
    QaidaItem("ج", "جہاز", "Jeem - Jahaz", 0xFF00BCD4), QaidaItem("چ", "چڑیا", "Chay - Chiriya", 0xFF009688), QaidaItem("ح", "حلوہ", "Hay - Halwa", 0xFF4CAF50),
    QaidaItem("خ", "خربوزہ", "Khay - Kharbooza", 0xFF8BC34A), QaidaItem("د", "درخت", "Daal - Darakht", 0xFFCDDC39), QaidaItem("ڈ", "ڈول", "Ddaal - Ddol", 0xFFFFEB3B),
    QaidaItem("ذ", "ذخیرہ", "Zaal - Zakheera", 0xFFFFC107), QaidaItem("ر", "ریل", "Ray - Rail", 0xFFFF9800), QaidaItem("ڑ", "آڑو", "Rray - Aaroo", 0xFFFF5722),
    QaidaItem("ز", "زرافہ", "Zay - Zarafa", 0xFF795548), QaidaItem("ژ", "ژالہ باری", "Zhay - Zhala Baari", 0xFF607D8B), QaidaItem("س", "سیب", "Seen - Saib", 0xFFE53935),
    QaidaItem("ش", "شیر", "Sheen - Shair", 0xFFD81B60), QaidaItem("ص", "صندوق", "Saad - Sandooq", 0xFF8E24AA), QaidaItem("ض", "ضعیف", "Zaad - Zaeef", 0xFF5E35B1),
    QaidaItem("ط", "طوطا", "Toyen - Tota", 0xFF3949AB), QaidaItem("ظ", "ظروف", "Zoyen - Zaroof", 0xFF1E88E5), QaidaItem("ع", "عینک", "Ain - Ainak", 0xFF039BE5),
    QaidaItem("غ", "غبارہ", "Ghain - Ghubara", 0xFF00ACC1), QaidaItem("ف", "فوارہ", "Fay - Fawara", 0xFF00897B), QaidaItem("ق", "قلم", "Qaaf - Qalam", 0xFF43A047),
    QaidaItem("ک", "کتاب", "Kaaf - Kitaab", 0xFF7CB342), QaidaItem("گ", "گڑیا", "Gaaf - Guriya", 0xFFC0CA33), QaidaItem("ل", "لومڑی", "Laam - Loomri", 0xFFFDD835),
    QaidaItem("م", "مچھلی", "Meem - Machli", 0xFFFFB300), QaidaItem("ن", "نل", "Noon - Nal", 0xFFFB8C00), QaidaItem("و", "ورق", "Wao - Waraq", 0xFFF4511E),
    QaidaItem("ہ", "ہاتھی", "Gol Choti Hay - Haathi", 0xFF6D4C41), QaidaItem("ء", "چائے", "Hamza - Chai", 0xFF546E7A), QaidaItem("ی", "یکہ", "Choti Yay - Yaka", 0xFF0288D1),
    QaidaItem("ے", "بڑے پاؤں", "Bari Yay - Paon", 0xFF2E7D32)
)

@Composable
fun QaidaScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().statusBarsPadding().padding(12.dp)) {
        TopBar(title = "اردو قاعدہ (پورے 37 حروف)", onBack = onBack)
        LazyVerticalGrid(columns = GridCells.Fixed(3), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxSize()) {
            items(fullQaidaList) { item ->
                Surface(shape = RoundedCornerShape(18.dp), color = Color.White.copy(alpha = 0.94f), border = BorderStroke(1.5.dp, Color(item.color)), shadowElevation = 4.dp) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(10.dp)) {
                        Text(text = item.letter, fontSize = 38.sp, fontWeight = FontWeight.Bold, color = Color(item.color))
                        Text(text = item.word, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color(0xFF263238))
                        Text(text = item.phonetic, fontSize = 10.sp, color = Color.Gray, textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}

val fullAbcList = listOf(
    AbcItem("A", "Apple", "سیب", 0xFFE53935), AbcItem("B", "Ball", "گیند", 0xFF1E88E5), AbcItem("C", "Cat", "بلی", 0xFFFF8F00),
    AbcItem("D", "Duck", "بطخ", 0xFF43A047), AbcItem("E", "Elephant", "ہاتھی", 0xFF5E35B1), AbcItem("F", "Fish", "مچھلی", 0xFF00ACC1),
    AbcItem("G", "Giraffe", "زرافہ", 0xFFFDD835), AbcItem("H", "Horse", "گھوڑا", 0xFF6D4C41), AbcItem("I", "Ice Cream", "آئس کریم", 0xFFD81B60),
    AbcItem("J", "Jug", "جگ", 0xFF3949AB), AbcItem("K", "Kite", "پتنگ", 0xFF00897B), AbcItem("L", "Lion", "شیر", 0xFFFF6F00),
    AbcItem("M", "Monkey", "بندر", 0xFF8D6E63), AbcItem("N", "Nest", "گھونسلا", 0xFF0288D1), AbcItem("O", "Owl", "الو", 0xFF546E7A),
    AbcItem("P", "Parrot", "طوطا", 0xFF2E7D32), AbcItem("Q", "Queen", "ملکہ", 0xFF8E24AA), AbcItem("R", "Rabbit", "خرگوش", 0xFFE91E63),
    AbcItem("S", "Sun", "سورج", 0xFFF57F17), AbcItem("T", "Train", "ریل گاڑی", 0xFF00838F), AbcItem("U", "Umbrella", "چھتری", 0xFF6A1B9A),
    AbcItem("V", "Violin", "باجا", 0xFFAD1457), AbcItem("W", "Watch", "گھڑی", 0xFF1565C0), AbcItem("X", "Xylophone", "جل ترنگ", 0xFF2E7D32),
    AbcItem("Y", "Yak", "پہاڑی بیل", 0xFFD84315), AbcItem("Z", "Zebra", "زیبرا", 0xFF37474F)
)

@Composable
fun AbcScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().statusBarsPadding().padding(12.dp)) {
        TopBar(title = "ABC Alphabet (A to Z)", onBack = onBack)
        LazyVerticalGrid(columns = GridCells.Fixed(3), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxSize()) {
            items(fullAbcList) { item ->
                Surface(shape = RoundedCornerShape(18.dp), color = Color.White.copy(alpha = 0.94f), border = BorderStroke(1.5.dp, Color(item.color)), shadowElevation = 4.dp) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(10.dp)) {
                        Text(text = item.letter, fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color(item.color))
                        Text(text = item.word, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF263238))
                        Text(text = item.urduMeaning, fontSize = 12.sp, color = Color.Gray)
                    }
                }
            }
        }
    }
}

val fullGintiList = listOf(
    GintiItem(1, "۱", "ایک", "One", 0xFFE53935), GintiItem(2, "۲", "دو", "Two", 0xFF1E88E5), GintiItem(3, "۳", "تین", "Three", 0xFF43A047),
    GintiItem(4, "۴", "چار", "Four", 0xFFFF8F00), GintiItem(5, "۵", "پانچ", "Five", 0xFF8E24AA), GintiItem(6, "۶", "چھ", "Six", 0xFF00ACC1),
    GintiItem(7, "۷", "سات", "Seven", 0xFFD81B60), GintiItem(8, "۸", "آٹھ", "Eight", 0xFF3949AB), GintiItem(9, "۹", "نو", "Nine", 0xFF2E7D32),
    GintiItem(10, "۱۰", "دس", "Ten", 0xFFF57F17), GintiItem(11, "۱۱", "گیارہ", "Eleven", 0xFF00897B), GintiItem(12, "۱۲", "بارہ", "Twelve", 0xFF6A1B9A),
    GintiItem(13, "۱۳", "تیرہ", "Thirteen", 0xFF1565C0), GintiItem(14, "۱۴", "چودہ", "Fourteen", 0xFFAD1457), GintiItem(15, "۱۵", "پندرہ", "Fifteen", 0xFFD84315),
    GintiItem(16, "۱۶", "سولہ", "Sixteen", 0xFF546E7A), GintiItem(17, "۱۷", "سترہ", "Seventeen", 0xFF43A047), GintiItem(18, "۱۸", "اٹھارہ", "Eighteen", 0xFF1E88E5),
    GintiItem(19, "۱۹", "انیس", "Nineteen", 0xFFE53935), GintiItem(20, "۲۰", "بیس", "Twenty", 0xFF8E24AA)
)

@Composable
fun GintiScreen(onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().statusBarsPadding().padding(12.dp)) {
        TopBar(title = "گنتی سیکھیں (1 سے 20)", onBack = onBack)
        LazyVerticalGrid(columns = GridCells.Fixed(3), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxSize()) {
            items(fullGintiList) { item ->
                Surface(shape = RoundedCornerShape(18.dp), color = Color.White.copy(alpha = 0.94f), border = BorderStroke(1.5.dp, Color(item.color)), shadowElevation = 4.dp) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(10.dp)) {
                        Text(text = "${item.urduDigit} / ${item.num}", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = Color(item.color))
                        Text(text = item.urduWord, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color(0xFF263238))
                        Text(text = item.engWord, fontSize = 12.sp, color = Color.Gray)
                    }
                }
            }
        }
    }
}

val storiesList = listOf(
    StoryItem(
        1, "کچھوے اور خرگوش کی دوڑ", "The Tortoise and the Hare", "غرور کا سر نیچا ہوتا ہے، اور مستقل مزاجی سے کام کرنے والا ہمیشہ جیتتا ہے۔", "🐢🐇",
        listOf(
            "ایک سرسبز جنگل میں ایک تیز رفتار خرگوش رہتا تھا۔ اسے اپنی رفتار پر بڑا غرور تھا۔ وہ سست رفتار جانوروں کا مذاق اڑاتا۔ ایک دن اس نے ایک کچھوے سے کہا: 'تم اتنی آہستہ چلتے ہو کہ شام تک دو قدم بھی نہیں بڑھ پاتے۔'",
            "کچھوے نے تحمل سے جواب دیا: 'خرگوش بھائی! رفتار اپنی جگہ، مگر مستقل مزاجی سب سے بڑی طاقت ہے۔ اگر تمہیں اپنی دوڑ پر اتنا ہی ناز ہے، تو آؤ میرے ساتھ دوڑ کا مقابلہ کر کے دیکھ لو!' خرگوش زور سے ہنسا اور بولا: 'منظور ہے!'",
            "اگلی صبح دوڑ شروع ہوئی۔ خرگوش بجلی کی رفتار سے آگے نکل گیا جبکہ کچھوا آہستہ آہستہ چلتا رہا۔ آدھے راستے پر خرگوش نے سوچا: 'کچھوا تو بہت پیچھے ہے، کیوں نہ درخت کی ٹھنڈی چھاؤں میں سو لوں؟' ہوا کے جھونکوں سے اس کی آنکھ لگ گئی۔",
            "کچھوا بغیر رکے چلتا رہا اور سوئے ہوئے خرگوش کے پاس سے گزر کر جیت کی لکیر تک پہنچ گیا۔ جب خرگوش کی آنکھ کھلی تو کچھوا جیت چکا تھا۔ خرگوش شرمندگی سے سر جھکا کر جنگل کی طرف بھاگ گیا۔"
        )
    ),
    StoryItem(
        2, "عقلمند پیاسا کوا", "The Clever Thirsty Crow", "جہاں چاہ وہاں راہ! عقل اور محنت سے ہر مشکل آسان ہو جاتی ہے۔", "🪨🐦",
        listOf(
            "گرمیوں کے ایک تپتے ہوئے دن ایک کوا شدید پیاسا تھا۔ وہ پانی کی تلاش میں اڑتا رہا لیکن کہیں پانی نہ ملا۔",
            "اچانک اسے ایک باغ میں مٹی کا گھڑا نظر آیا۔ کوا خوش ہو کر وہاں پہنچا مگر دیکھا کہ گھڑے کی تہہ میں بہت کم پانی ہے اور چونچ نہیں پہنچ پا رہی۔",
            "کوے نے ہمت نہ ہاری۔ پاس پڑی کنکریاں دیکھ کر اس نے ایک ایک کنکری گھڑے میں ڈالنا شروع کر دی۔",
            "کنکریاں نیچے بیٹھتی گئیں اور پانی اوپر آ گیا۔ کوے نے جی بھر کر پانی پیا، اللہ کا شکر ادا کیا اور خوشی سے اڑ گیا۔"
        )
    ),
    StoryItem(
        3, "شیر اور احسان شناس چوہا", "The Lion and the Mouse", "احسان کا بدلہ نیکی ہوتا ہے، اور کوئی بھی دوست چھوٹا نہیں ہوتا۔", "🦁🐭",
        listOf(
            "ایک جنگل میں شیر سو رہا تھا کہ ایک ننھا چوہا کھیل کود کرتے ہوئے اس کی پیٹھ پر چڑھ گیا۔ شیر جاگ گیا اور چوہے کو دبوچ لیا۔",
            "چوہا رو کر بولا: 'مجھے معاف کر دیں، کبھی میں بھی آپ کے کام آؤں گا۔' شیر کو ترس آ گیا اور اس نے چوہے کو چھوڑ دیا۔",
            "کچھ دن بعد شکاریوں نے شیر کو مضبوط جال میں قید کر لیا۔ شیر درد بھری دہاڑیں مارنے لگا۔",
            "چوہے نے آواز سن کر فورا اپنے تیز دانتوں سے جال کی رسیاں کاٹ دیں اور شیر کو آزاد کرا دیا۔ دونوں ہمیشہ کے لیے پکے دوست بن گئے۔"
        )
    )
)

@Composable
fun StoriesScreen(onBack: () -> Unit, onSelectStory: (StoryItem) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().statusBarsPadding().padding(12.dp)) {
        TopBar(title = "بچوں کی اخلاقی کہانیاں", onBack = onBack)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp), modifier = Modifier.fillMaxSize()) {
            items(storiesList) { story ->
                Surface(onClick = { onSelectStory(story) }, shape = RoundedCornerShape(22.dp), color = Color.White.copy(alpha = 0.94f), shadowElevation = 5.dp, border = BorderStroke(1.5.dp, Color(0xFF64B5F6))) {
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
                        Text(text = story.emoji, fontSize = 42.sp)
                        Spacer(modifier = Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = story.titleUrdu, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1565C0))
                            Text(text = story.titleEng, fontSize = 13.sp, color = Color(0xFF555555))
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(text = "مکمل کہانی پڑھنے کے لیے ٹیپ کریں 👉", fontSize = 11.sp, color = Color(0xFF2E7D32), fontWeight = FontWeight.Medium)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StoryDetailScreen(story: StoryItem, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().statusBarsPadding().padding(12.dp)) {
        TopBar(title = story.titleUrdu, onBack = onBack)
        Surface(shape = RoundedCornerShape(22.dp), color = Color.White.copy(alpha = 0.96f), shadowElevation = 6.dp, modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(18.dp)) {
                Text(text = story.emoji, fontSize = 48.sp, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = story.titleUrdu, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0D47A1), textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(14.dp))
                story.paragraphs.forEach { paragraph ->
                    Text(text = paragraph, fontSize = 17.sp, lineHeight = 28.sp, color = Color(0xFF263238), modifier = Modifier.padding(bottom = 12.dp), textAlign = TextAlign.Right)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Surface(shape = RoundedCornerShape(16.dp), color = Color(0xFFFFF9C4), border = BorderStroke(1.5.dp, Color(0xFFFFB300)), modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text(text = "💡 اخلاقی سبق:", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFFE65100), textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth())
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = story.moral, fontSize = 15.sp, fontWeight = FontWeight.Medium, color = Color(0xFF37474F), textAlign = TextAlign.Right, modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}
'''

with open("temp_part1.txt", "r", encoding="utf-8") as f:
    part1 = f.read()

full_file = part1 + part2_code
with open("app/src/main/java/com/nanhesitare/app/MainActivity.kt", "w", encoding="utf-8") as f:
    f.write(full_file)

print("✅ MainActivity.kt is successfully built!")
print("🚀 Committing and pushing...")
subprocess.run(["git", "add", "-A"])
subprocess.run(["git", "commit", "-m", "Clean complete update: 37 Qaida, ABC, Ginti, Stories, Sound and Nature BG"])
res = subprocess.run(["git", "push"])
if res.returncode == 0:
    print("\n🎉 SUCCESS! Push done. GitHub Actions build started!")
else:
    print("Push failed, please check network.")
