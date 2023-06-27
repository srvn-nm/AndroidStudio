package com.example.jetpackcomposelearning
import android.os.Bundle
import android.view.View
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(R.layout.home_fragment) {


    //using set content of compose
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<ComposeView>(R.id.compose_view).setContent {
            MainContent()
        }
    }
}
