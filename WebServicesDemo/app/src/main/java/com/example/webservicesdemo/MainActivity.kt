package com.example.webservicesdemo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.webservicesdemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var users = ArrayList<User>()
    private var usersAdapter = UsersAdapter(users)
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initListeners()

    }

    private fun initListeners(){
       /* binding.btnSimpleRequest.setOnClickListener {
            WebThread(null).execute(
                *arrayOf("https://bitcode.in/")
            )
        }*/

        binding.btnGetAllUsers.setOnClickListener {
            WebThread(UsersHandler()).execute(null)
        }
    }

    private fun initRecyclerView(){
        binding.recyclerViewForUsers.adapter = usersAdapter
        binding.recyclerViewForUsers.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    inner class UsersHandler : Handler(){
        @SuppressLint("NotifyDataSetChanged")
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if(msg.obj != null){
                var users = msg.obj as ArrayList<User>
                this@MainActivity.users = users
                usersAdapter.notifyDataSetChanged()
            }
        }
    }
}