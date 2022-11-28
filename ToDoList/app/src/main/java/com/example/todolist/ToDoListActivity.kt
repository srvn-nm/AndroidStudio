package com.example.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.ActivityToDoListBinding

class ToDoListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityToDoListBinding
    private lateinit var userIdList:ArrayList<Int>
    private lateinit var taskIdList:ArrayList<Int>
    private lateinit var taskTextList:ArrayList<String>
    private lateinit var taskStateList:ArrayList<String>
    private lateinit var userInfo:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityToDoListBinding.inflate(layoutInflater)
        userIdList = intent.getIntegerArrayListExtra("userIdList") as ArrayList<Int>
        taskIdList = intent.getIntegerArrayListExtra("taskIdList") as ArrayList<Int>
        taskTextList = intent.getStringArrayListExtra("taskTextList") as ArrayList<String>
        taskStateList = intent.getStringArrayListExtra("taskStateList") as ArrayList<String>
        userInfo = intent.getStringExtra("userInfo") as String
        setContentView(binding.root)
    }

    class CustomAdapter(private val dataSet: Array<String>) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView

            init {
                // Define click listener for the ViewHolder's View.
                textView = view.findViewById(R.id.textView)
            }
        }

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Create a new view, which defines the UI of the list item
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.activity_to_do_list, viewGroup, false)

            return ViewHolder(view)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.textView.text = dataSet[position]
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = dataSet.size

    }

}