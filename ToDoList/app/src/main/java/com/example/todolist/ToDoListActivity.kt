package com.example.todolist

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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

    class CustomAdapter(var context: Context, var todoList:ArrayList<Todo>, var lineKey :Boolean):
        BaseAdapter(){
        var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        override fun getCount(): Int { return todoList.size }

        override fun getItem(position: Int): Any? { return  null }

        override fun getItemId(position: Int): Long { return 0 }

        @SuppressLint("SetTextI18n")
        override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {

            var view = view
            if(view == null){ view = layoutInflater.inflate(R.layout.list_item,viewGroup,false) }


            val idTextView = view?.findViewById<TextView>(R.id.taskId)
            val userIdTextView = view?.findViewById<TextView>(R.id.userId)
            val taskTextView = view?.findViewById<TextView>(R.id.titleText)
            val checkBoxForTaskSituation = view?.findViewById<CheckBox>(R.id.checkedTextView)

            idTextView!!.text = "Task Id :     ${todoList[position].id}"
            userIdTextView!!.text = "User Id :     ${todoList[position].userId}"
            taskTextView!!.text = todoList[position].title
            if(todoList[position].completed and lineKey){
                taskTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            }

            checkBoxForTaskSituation!!.isChecked = todoList[position].completed
            return view!!

        }

    }

}