package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.quizapp.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ActivityResultBinding
    private lateinit var username:String
    private var wrongs :Int = 0
    private var corrects:Int = 0
    private var questions:Int = 0
    private var musicPlayer: MediaPlayer?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        username = intent.getStringExtra("username").toString()
        wrongs = intent.getIntExtra("wrong",0)
        corrects = intent.getIntExtra("correct",0)
        questions = intent.getIntExtra("questions",0)
        showingResults()

    }

    private fun showingResults() {
        val result = 100*((3*corrects)-wrongs)/(3*questions)
            if (corrects > wrongs){
                if (musicPlayer == null){
                    musicPlayer = MediaPlayer.create(this@ResultActivity,R.raw.win)
                    musicPlayer!!.isLooping = true
                    musicPlayer!!.start()
                }else musicPlayer!!.start()
                binding.textView7.setTextColor(Color.parseColor("#5ded53"))
                if (corrects == questions){
                    binding.imageView4.setImageResource(R.drawable.ic_trophy)
                    binding.textView7.text = ""
                    binding.textView5.text = ""
                    binding.textView6.text = ""
                    binding.textView9.text = ""
                    binding.textView8.text = ""
                    binding.textView4.text = "\uD835\uDCD2\uD835\uDCF8\uD835\uDCF7\uD835\uDCF0\uD835\uDCFB\uD835\uDCEA\uD835\uDCFD\uD835\uDCFE\uD835\uDCF5\uD835\uDCEA\uD835\uDCFD\uD835\uDCF2\uD835\uDCF8\uD835\uDCF7\uD835\uDCFC, \uD835\uDD02\uD835\uDCF8\uD835\uDCFE \uD835\uDD00\uD835\uDCF2\uD835\uDCF7!"
                }else{
                    if (corrects != 0){
                        binding.textView9.text = "False ratio to true :⋟ ${wrongs*100/corrects}"
                    }
                    binding.textView8.text = "Pure result :⋟ $result"
                    binding.textView7.text = "Good job $username"
                    binding.textView5.text = "Correct Answers :⋟ $corrects"
                    binding.textView6.text = "Wrong Answers :⋟ $wrongs"
                    binding.imageView4.setImageResource(R.drawable.ic_trophy)
                    binding.textView4.text = "\uD835\uDCD2\uD835\uDCF8\uD835\uDCF7\uD835\uDCF0\uD835\uDCFB\uD835\uDCEA\uD835\uDCFD\uD835\uDCFE\uD835\uDCF5\uD835\uDCEA\uD835\uDCFD\uD835\uDCF2\uD835\uDCF8\uD835\uDCF7\uD835\uDCFC, \uD835\uDD02\uD835\uDCF8\uD835\uDCFE \uD835\uDD00\uD835\uDCF2\uD835\uDCF7!"
                }
            }else if (wrongs > corrects){
                if (musicPlayer == null){
                    musicPlayer = MediaPlayer.create(this@ResultActivity,R.raw.gameover)
                    musicPlayer!!.isLooping = true
                    musicPlayer!!.start()
                }else musicPlayer!!.start()
                if (corrects != 0){
                    binding.textView9.text = "False ratio to true :⋟ ${wrongs*100/corrects}"
                }
                if (corrects==0){
                    binding.textView9.text = ""
                }
                binding.textView8.text = "Pure result :⋟ $result"
                binding.textView7.setTextColor(Color.parseColor("#d13838"))
                binding.textView7.text = "Try harder $username"
                binding.textView5.text = "Correct Answers :⋟ $corrects"
                binding.textView6.text = "Wrong Answers :⋟ $wrongs"
            }else{
                if (corrects != 0){
                    binding.textView9.text = "False ratio to true :⋟ ${wrongs*100/corrects}"
                }
                if (corrects==0){
                    binding.textView9.text = ""
                }
                binding.textView8.text = "Pure result :⋟ $result"
                binding.textView7.text = "Try harder $username"
                binding.textView5.text = "Correct Answers :⋟ $corrects"
                binding.textView6.text = "Wrong Answers :⋟ $wrongs"
            }
    }

    fun returnToFirst(view: View){
        if (musicPlayer!=null){
            musicPlayer!!.stop()
            musicPlayer!!.release()
            musicPlayer=null
        }
        Handler().postDelayed({
            val intent2 = Intent(this@ResultActivity,MainActivity::class.java)
            startActivity(intent2)},1000)
    }

    override fun onBackPressed() {
        if (musicPlayer!=null){
            musicPlayer!!.stop()
            musicPlayer!!.release()
            musicPlayer=null
        }
        startActivity(Intent(this@ResultActivity,MainActivity::class.java))
    }
}