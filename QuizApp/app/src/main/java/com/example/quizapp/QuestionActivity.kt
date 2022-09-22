package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.quizapp.databinding.ActivityQuestionBinding
import kotlin.properties.Delegates


class QuestionActivity : AppCompatActivity() {
    lateinit var binding: ActivityQuestionBinding
    var selectedOption = 0
    var currentQuestion by Delegates.notNull<Int>()
    var correctAnswer by Delegates.notNull<Int>()
    var correctAnswers = 0
    var wrongAnswers = 0
    private val questionList = Constant.getQuestions()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentQuestion = 1
        correctAnswer = loadQuestionData()
        val username = intent.getStringExtra("username")

        binding.button3.setOnClickListener {
            val intent = Intent(this@QuestionActivity,ResultActivity::class.java)
            intent.putExtra("username",username)
            intent.putExtra("wrong",wrongAnswers)
            intent.putExtra("correct",correctAnswers)
            intent.putExtra("questions",questionList.size)
            startActivity(intent)
        }
    }

    private fun loadQuestionData():Int{
        val question = questionList[currentQuestion - 1]
        binding.textViewTitle.text = question.question
        binding.imageView.setImageResource(question.image)
        binding.textViewQ1.text = question.option1
        binding.textViewQ2.text = question.option2
        binding.textViewQ3.text = question.option3
        binding.textViewQ4.text = question.option4
        binding.progressBar.progress = currentQuestion
        binding.textViewProgress.text = "$currentQuestion out of ${binding.progressBar.max}"
        defaultOptions()
        binding.button2.text = "SUBMIT"
        return question.correctAnswer
    }

    fun onOptionsClick(view: View){
        defaultOptions()
       val clickedOption =  view as TextView

        when(clickedOption.tag.toString()){
            "o1"->{
                selectedOption = 1
            }
            "o2"->{
                selectedOption = 2
            }
            "o3"->{
                selectedOption = 3
            }
            "o4"->{
                selectedOption = 4
            }
        }

        clickedOption.typeface = Typeface.DEFAULT_BOLD
        clickedOption.background = ContextCompat.getDrawable(this,R.drawable.options_background_selected)
        clickedOption.setTextColor(Color.parseColor("#000000"))
    }

    private fun defaultOptions(){
        val textViewOptions = ArrayList<TextView>()
        textViewOptions.add(binding.textViewQ1)
        textViewOptions.add(binding.textViewQ2)
        textViewOptions.add(binding.textViewQ3)
        textViewOptions.add(binding.textViewQ4)

        for (t in textViewOptions){
            t.typeface = Typeface.DEFAULT
            t.background = ContextCompat.getDrawable(this,R.drawable.options_background)
            t.setTextColor(Color.parseColor("#7a8089"))
        }
    }

    private fun setBackgroundTextView(optionId:Int, drawableIndex:Int){
        when(optionId){
            1->{
                binding.textViewQ1.background = ContextCompat.getDrawable(this,drawableIndex)
            }
            2->{
                binding.textViewQ2.background = ContextCompat.getDrawable(this,drawableIndex)
            }
            3->{
                binding.textViewQ3.background = ContextCompat.getDrawable(this,drawableIndex)
            }
            4->{
                binding.textViewQ4.background = ContextCompat.getDrawable(this,drawableIndex)
            }
        }
    }

    fun guide(view: View){
        Toast.makeText(this,"The capital of this country is ${questionList[currentQuestion - 1].guide}",Toast.LENGTH_LONG).show()
    }

    fun submit(view: View){
        if (selectedOption == 0 && currentQuestion < questionList.size){
            currentQuestion ++
            correctAnswer = loadQuestionData()
        }
        else{
            if (selectedOption == 0 && currentQuestion == questionList.size){
                binding.button2.visibility = View.GONE
                binding.imageButton.visibility = View.GONE
                binding.button3.visibility = View.VISIBLE
                return
            }
            checkAnswers()
            selectedOption = 0
            binding.button2.text = "NEXT Question!"
            if(currentQuestion == questionList.size){
                binding.button2.visibility = View.GONE
                binding.imageButton.visibility = View.GONE
                binding.button3.visibility = View.VISIBLE
            }
        }
    }

    private fun checkAnswers(){
        if (selectedOption != correctAnswer){
            setBackgroundTextView(selectedOption,R.drawable.options_background_wrong)
            setBackgroundTextView(correctAnswer,R.drawable.options_background_correct)
            binding.button2.setTextColor(Color.BLACK)
            wrongAnswers++

        }else{
            setBackgroundTextView(correctAnswer,R.drawable.options_background_correct)
            binding.button2.setTextColor(Color.BLACK)
            correctAnswers++
        }
    }
}