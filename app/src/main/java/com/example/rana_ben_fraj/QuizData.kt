package com.example.rana_ben_fraj

data class QuizQuestion(
    val imageRes: Int,
    val question: String,
    val options: List<String>,
    val correctAnswer: String,
    val difficulty: String, // "easy", "medium", "hard"
    val explanation: String
)

val romanHeritageQuestions = listOf(
    // EASY: Identification (5 Questions)
    QuizQuestion(
        imageRes = R.drawable.el_jem_amphitheater,
        question = "Identify this iconic Roman amphitheater located in central Tunisia.",
        options = listOf("El Jem Amphitheater", "Carthage", "Dougga", "Sbeitla"),
        correctAnswer = "El Jem Amphitheater",
        difficulty = "easy",
        explanation = "The El Jem Amphitheater is one of the best-preserved Roman ruins in the world and could seat up to 35,000 spectators."
    ),
    QuizQuestion(
        imageRes = R.drawable.carthage,
        question = "Which famous ancient city features these massive Antonine Baths?",
        options = listOf("Uthina", "Carthage", "Bulla Regia", "Mactaris"),
        correctAnswer = "Carthage",
        difficulty = "easy",
        explanation = "The Baths of Antoninus in Carthage were the largest thermal baths built on African soil."
    ),
    QuizQuestion(
        imageRes = R.drawable.dogga,
        question = "Name this site known for its stunning Capitol and theater overlooking the valley.",
        options = listOf("Sbeitla", "Ammaedara", "Dougga", "Thuburbo Majus"),
        correctAnswer = "Dougga",
        difficulty = "easy",
        explanation = "Dougga (Thugga) is a UNESCO World Heritage site and is considered the best-preserved Roman small town in North Africa."
    ),
    QuizQuestion(
        imageRes = R.drawable.sbe_tla,
        question = "Which Roman site is unique for its three parallel temples dedicated to the Capitoline Triad?",
        options = listOf("Sbeitla", "El Jem", "Carthage", "Mactaris"),
        correctAnswer = "Sbeitla",
        difficulty = "easy",
        explanation = "Sbeitla (Sufetula) is famous for its Forum featuring three separate temples instead of one large Capitoline temple."
    ),
    QuizQuestion(
        imageRes = R.drawable.bulla_regia,
        question = "This site is famous for its unique underground Roman villas. What is it called?",
        options = listOf("Uthina", "Bulla Regia", "Ammaedara", "Dougga"),
        correctAnswer = "Bulla Regia",
        difficulty = "easy",
        explanation = "Bulla Regia features subterranean architecture designed to provide relief from the intense summer heat."
    ),

    // MEDIUM: Features and importance (5 Questions)
    QuizQuestion(
        imageRes = R.drawable.thuburbo_majus,
        question = "What was the primary function of the large forum found at Thuburbo Majus?",
        options = listOf("Military training", "Public gathering and commerce", "Religious sacrifice only", "Private residence"),
        correctAnswer = "Public gathering and commerce",
        difficulty = "medium",
        explanation = "Like most Roman forums, Thuburbo Majus's forum was the center of public life, serving as a marketplace and political meeting spot."
    ),
    QuizQuestion(
        imageRes = R.drawable.uthina,
        question = "The amphitheater at Uthina was partially built into a hill. Why was this common in Roman engineering?",
        options = listOf("For better acoustics", "To save on construction costs", "To hide from enemies", "For religious reasons"),
        correctAnswer = "To save on construction costs",
        difficulty = "medium",
        explanation = "Utilizing natural slopes significantly reduced the amount of masonry required to build the seating areas (cavea)."
    ),
    QuizQuestion(
        imageRes = R.drawable.mactaris,
        question = "Mactaris features a notable 'Schola Juventutis'. What was the primary purpose of this institution?",
        options = listOf("A training center for young men", "A public bathhouse", "A grain storage facility", "A temple for the elderly"),
        correctAnswer = "A training center for young men",
        difficulty = "medium",
        explanation = "The Schola Juventutis was an institution for the civic and military training of Roman youth."
    ),
    QuizQuestion(
        imageRes = R.drawable.mosaic,
        question = "What does the extreme abundance of high-quality Roman mosaics in Tunisia signify?",
        options = listOf("It was a poor province", "It was a major artistic and economic center", "It was only a military outpost", "It lacked local materials"),
        correctAnswer = "It was a major artistic and economic center",
        difficulty = "medium",
        explanation = "Africa Proconsularis was one of the wealthiest provinces, supporting world-class mosaic workshops."
    ),
    QuizQuestion(
        imageRes = R.drawable.ammaedara__ha_dra_,
        question = "Ammaedara was home to the Legio III Augusta. What does this indicate about its strategic role?",
        options = listOf("It was a luxury resort", "It was a vital military headquarters", "It was a small farming village", "It was a port city"),
        correctAnswer = "It was a vital military headquarters",
        difficulty = "medium",
        explanation = "Ammaedara served as a key strategic base for the Roman legion to control the region's frontiers and trade routes."
    ),

    // HARD: Dates and historical facts (5 Questions)
    QuizQuestion(
        imageRes = R.drawable.el_jem_amphitheater,
        question = "In which century was the construction of the massive El Jem Amphitheater initiated?",
        options = listOf("1st Century AD", "2nd Century AD", "3rd Century AD", "4th Century AD"),
        correctAnswer = "3rd Century AD",
        difficulty = "hard",
        explanation = "Construction began around 238 AD, under the proconsul Gordian, who later briefly became Emperor."
    ),
    QuizQuestion(
        imageRes = R.drawable.dogga,
        question = "Dougga was granted the status of a 'municipium' in 205 AD under which North African-born Emperor?",
        options = listOf("Augustus", "Septimius Severus", "Hadrian", "Marcus Aurelius"),
        correctAnswer = "Septimius Severus",
        difficulty = "hard",
        explanation = "Emperor Septimius Severus, born in Leptis Magna, granted Dougga the status of a municipium."
    ),
    QuizQuestion(
        imageRes = R.drawable.carthage,
        question = "After the Punic destruction in 146 BC, who was the first to successfully plan the Roman colony at Carthage?",
        options = listOf("Julius Caesar", "Augustus", "Gaius Gracchus", "Scipio Africanus"),
        correctAnswer = "Julius Caesar",
        difficulty = "hard",
        explanation = "Julius Caesar planned the colony (Colonia Iulia Concordia Carthago), though it was realized by Augustus after his death."
    ),
    QuizQuestion(
        imageRes = R.drawable.sbe_tla,
        question = "What major event led to the definitive fall of Sbeitla as a Byzantine administrative center in 647 AD?",
        options = listOf("A major earthquake", "The Arab conquest", "A plague outbreak", "A Vandal invasion"),
        correctAnswer = "The Arab conquest",
        difficulty = "hard",
        explanation = "The Battle of Sufetula in 647 AD was a pivotal moment in the Arab conquest of North Africa."
    ),
    QuizQuestion(
        imageRes = R.drawable.bulla_regia,
        question = "Bulla Regia was a royal residence for which Numidian king before the Roman period?",
        options = listOf("Massinissa", "Jugurtha", "Hiempsal", "Juba I"),
        correctAnswer = "Massinissa",
        difficulty = "hard",
        explanation = "King Massinissa established Bulla Regia as one of his royal residences during the Numidian Kingdom's height."
    )
)
