package com.example.sourceofemulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sourceofemulator.databinding.ActivitySaludBinding

class ActivitySalud : AppCompatActivity() {
    private lateinit var binding: ActivitySaludBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.supportActionBar?.hide()
        binding = ActivitySaludBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.backActivity.setOnClickListener{onBackPressed()}
    binding.RecuclerView.layoutManager = LinearLayoutManager(this)
        val adapter = CustomAdapter()
        binding.RecuclerView.adapter = adapter

    }
}