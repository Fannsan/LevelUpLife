package com.example.lab2.LevelUp

data class LevelUpUiState(
    var moodList: ArrayList<String> = ArrayList(
        arrayListOf("Excited", "Tired", "Anxious", "Irritated", "Unmotivated")
    ),

    var excitedList: ArrayList<String> = ArrayList(
        arrayListOf(
            "Get moving by running, walking, or biking." ,
                    "Share your excitement with someone by telling a friend or family member about it." ,
                    "Write down your thoughts and feelings in a journal or blog." ,
                    "Listen to uplifting music that makes you feel positive." ,
                    "Cook some new food or try a new recipe",
                    "Start planning or preparing for something you're looking forward to, like a trip or event." ,
                    "Engage in a creative activity like painting, writing, or dancing." ,
                    "Learn something new by taking a class",
                    "read a book on a topic that interests you." ,
                    "Do something you've always wanted to do but never had the courage to, like getting scuba certified or trying a new sport." ,
                    "Help someone else by volunteering or doing something kind for a friend or stranger." ,
                    "Meditate or practice yoga to help you feel centered and relaxed."
        )
    ),

    var tiredList: ArrayList<String> = ArrayList(
        arrayListOf(
                "Take a nap or rest for a short period of time to recharge your energy.",
                "Practice relaxation techniques such as deep breathing, progressive muscle relaxation, or visualization.",
                "Engage in light physical activity such as stretching or yoga to help loosen up tense muscles and increase blood flow.",
                "Listen to calming music or nature sounds to help you unwind and relax.",
                "Take a warm bath or shower to help ease tension and promote relaxation.",
                "Read a book or watch a movie or TV show that you find interesting and soothing.",
                "Go for a gentle walk outside to get some fresh air and sunlight.",
                "Spend time in nature by taking a hike, going to the beach, or sitting in a park to help reduce stress and boost your mood.",
                "Practice self-care by doing something you enjoy, such as cooking a nice meal, getting a massage, or taking a relaxing bubble bath.",
                "Reach out to a supportive friend or family member for emotional support and encouragement."

        )
    ),

var anxiousList: ArrayList<String> = ArrayList(
    arrayListOf("Practice deep breathing exercises to help calm your body and mind.",
            "Engage in mindfulness practices such as meditation or yoga to help you stay present and focused." ,
            "Write down your worries in a journal to help release them from your mind." ,
            "Engage in physical exercise to help release endorphins and reduce stress." ,
            "Listen to calming music or nature sounds to help you relax." ,
            "Practice progressive muscle relaxation techniques to help release tension in your body." ,
            "Use aromatherapy by burning essential oils or using a diffuser to help calm your senses." ,
            "Practice positive self-talk by reminding yourself that you can handle the situation and that your feelings are temporary." ,
            "Spend time in nature by taking a walk or spending time in a park to help reduce stress and anxiety." ,
            "Seek the support of a mental health professional, friend or family member for emotional support and guidance.")
),

var irritatedList: ArrayList<String> = ArrayList(
    arrayListOf("Take a break and step away from the situation that is causing the irritation.",
            "Practice deep breathing or other relaxation techniques to help calm your body and mind." ,
            "Engage in physical activity or exercise to help release pent-up frustration and stress." ,
            "Listen to music that helps you feel calm and relaxed." ,
            "Write down your thoughts and feelings in a journal or on a piece of paper to help release them from your mind." ,
            "Practice assertiveness by expressing your needs and feelings in a calm and respectful manner." ,
            "Practice empathy by trying to see the situation from the other person's perspective." ,
            "Engage in a relaxing activity such as taking a warm bath or reading a book." ,
            "Practice gratitude by focusing on things in your life that you are thankful for." ,
            "Seek the support of a friend or family member to talk through the situation and receive emotional support.")
)


){
    }

