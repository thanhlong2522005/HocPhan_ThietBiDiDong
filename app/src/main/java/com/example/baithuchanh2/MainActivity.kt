package com.example.baithuchanh2

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.baithuchanh2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonTao.setOnClickListener {
            xuLyTaoDanhSach()
        }
    }

    private fun xuLyTaoDanhSach() {
        val soLuongString = binding.editTextSoLuong.text.toString().trim()

        binding.textViewThongBaoLoi.visibility = ViewGroup.GONE
        binding.layoutDanhSach.removeAllViews()

        val soLuong: Int? = soLuongString.toIntOrNull()

        if (soLuong != null && soLuong > 0) {
            taoVaHienThiDanhSach(soLuong)
        } else {
            hienThiThongBaoLoi()
        }
    }

    private fun hienThiThongBaoLoi() {
        binding.textViewThongBaoLoi.visibility = ViewGroup.VISIBLE
        binding.textViewThongBaoLoi.setTextColor(Color.RED)
        binding.textViewThongBaoLoi.text = "Dữ liệu bạn nhập không hợp lệ"
    }

    private fun taoVaHienThiDanhSach(n: Int) {
        for (i in 1..n) {
            val button = Button(this).apply {
                text = i.toString()
                layoutParams = LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(0, 0, 0, 8)
                }
                setBackgroundColor(Color.RED)
                setTextColor(Color.WHITE)
                gravity = Gravity.CENTER
            }
            binding.layoutDanhSach.addView(button)
        }
    }
}