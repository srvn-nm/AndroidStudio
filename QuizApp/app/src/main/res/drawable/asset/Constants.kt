package com.amirahmadadibi.projects.quizapp

class Constants {
    companion object {

        fun getQuestions(): ArrayList<Question> {
            var questionList = ArrayList<Question>()

            val question1 = Question(
                1, "این پرچم متعلق به چه کشوری است  ؟‌", R.drawable.ic_flag_of_argentina,
                "Iran", "Arabia", "Turkey", "Argentina", 4
            )

            val question2 = Question(
                2, "این پرچم متعلق به چه کشوری است  ؟", R.drawable.ic_flag_of_brazil,
                "آمریکا", "برزیل", "افغانستان", "Argentina", 2
            )

            val question3 = Question(
                3, "این پرچم متعلق به چه کشوری است  ؟‌", R.drawable.ic_flag_of_india,
                "فرانسه", "دانمارک", "Turkey", "هند", 4
            )

            val question4 = Question(
                4, "این پرچم متعلق به چه کشوری است  ؟‌", R.drawable.ic_flag_of_germany,
                "Iran", "آلمان", "Turkey", "Argentina", 2
            )

            val question5 = Question(
                5, "این پرچم متعلق به چه کشوری است  ؟‌", R.drawable.ic_flag_of_denmark,
                "Iran", "دانمارک", "Turkey", "Argentina", 2
            )

            val question6 = Question(
                6, "این پرچم متعلق به چه کشوری است  ؟‌", R.drawable.ic_flag_of_new_zealand,
                "نیوزیلند", "دانمارک", "Turkey", "Argentina", 1
            )

            val question7 = Question(
                7, "این پرچم متعلق به چه کشوری است  ؟‌", R.drawable.ic_flag_of_kuwait,
                "Iran", "دانمارک", "کویت", "Argentina", 3
            )
            questionList.add(question1)
            questionList.add(question2)
            questionList.add(question3)
            questionList.add(question4)
            questionList.add(question5)
            questionList.add(question6)
            questionList.add(question7)
            return questionList

        }
    }
}