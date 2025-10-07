package com.example.baitaptuan2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextAge = findViewById<EditText>(R.id.editTextAge)
        val buttonCheck = findViewById<Button>(R.id.buttonCheck)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        buttonCheck.setOnClickListener {
            val name = editTextName.text.toString().trim()
            val ageString = editTextAge.text.toString().trim()

            if (name.isEmpty() || ageString.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ Họ và tên và Tuổi.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val age: Int = try {
                ageString.toInt()
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Tuổi phải là một số nguyên hợp lệ.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val classification: String
            if (age > 65) {
                classification = "Người già (>65)"
            } else if (age in 7..65) {
                classification = "Người lớn (7-65)"
            } else if (age in 2..6) {
                classification = "Trẻ em (2-6)"
            } else if (age >= 0 && age < 2) {
                classification = "Em bé (<2)"
            } else {
                classification = "Tuổi không hợp lệ (nhập tuổi >= 0)"
            }

            val resultText = "Họ và tên: $name\nTuổi: $age\nPhân loại: $classification"
            textViewResult.text = resultText

            Toast.makeText(this, "Xin chào $name, bạn là $classification!", Toast.LENGTH_LONG).show()
        }
    }
}