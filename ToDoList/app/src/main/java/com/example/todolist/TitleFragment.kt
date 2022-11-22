package com.example.todolist

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todolist.databinding.FragmentTitleBinding

@SuppressLint("StaticFieldLeak")
lateinit var binding: FragmentTitleBinding

class TitleFragment : Fragment() {
    private var mText: CharSequence = "Loading Notes..."
    private var mIndex = 0
    private var mDelay: Long = 150
    private lateinit var mHandler: Handler

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTitleBinding.inflate(inflater, container, false)

        binding.textView2.text=""

        mHandler = Handler()
        mHandler.removeCallbacks(characterAdder)
        mHandler.postDelayed(characterAdder, mDelay)


        return binding.root
    }

    private val characterAdder: Runnable = object : Runnable {
        override fun run() {
            binding.textView2.text = mText.subSequence(0, mIndex++)
            if (mIndex <= mText.length) {
                mHandler.postDelayed(this, mDelay)
            }
        }
    }

}