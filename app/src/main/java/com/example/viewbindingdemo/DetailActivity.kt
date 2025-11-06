package com.example.viewbindingdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.viewbindingdemo.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val email = intent.getStringExtra("email")
        val avatarRes = intent.getIntExtra("avatar", R.drawable.avatar1)

        binding.tvNameDetail.text = name
        binding.tvPhoneDetail.text = phone
        binding.tvEmailDetail.text = email
        binding.avatarDetail.setImageResource(avatarRes)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
        return true
    }
}
