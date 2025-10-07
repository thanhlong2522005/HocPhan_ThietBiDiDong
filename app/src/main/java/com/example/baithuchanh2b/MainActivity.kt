package com.example.baithuchanh2b

import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.baithuchanh2b.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonKiemTra.setOnClickListener {
            kiemTraEmail()
        }
    }

    private fun kiemTraEmail() {
        val email = binding.editTextEmail.text.toString().trim()

        binding.textViewKetQua.setTextColor(Color.BLACK)

        var message: String
        var textColor: Int

        if (email.isNullOrEmpty()) {
            message = "Email không hợp lệ"
            textColor = Color.RED

        } else if (!email.contains("@")) {
            message = "Email không đúng định dạng"
            textColor = Color.RED

        } else {
            message = "Bạn đã nhập email hợp lệ"
            textColor = Color.BLUE
        }

        hienThiKetQua(message, textColor)
    }

    private fun hienThiKetQua(message: String, color: Int) {
        binding.textViewKetQua.text = message
        binding.textViewKetQua.setTextColor(color)
    }
}