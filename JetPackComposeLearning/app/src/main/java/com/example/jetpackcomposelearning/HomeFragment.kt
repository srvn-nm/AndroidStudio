package com.example.jetpackcomposelearning
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment(R.layout.home_fragment) {


//    //using set content of compose in the xml
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        view.findViewById<ComposeView>(R.id.compose_view).setContent {
//            MainContent()
//        }
//    }

    //without xml file
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
//                MainContent()
                ComposeXml()
            }
        }
    }
}
