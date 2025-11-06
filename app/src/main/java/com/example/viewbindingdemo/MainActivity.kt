package com.example.viewbindingdemo


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewbindingdemo.adapter.ContactAdapter
import com.example.viewbindingdemo.databinding.ActivityMainBinding
import com.example.viewbindingdemo.model.Contact

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contacts = listOf(
            Contact("Võ Thành Long", "0901 234 567", R.drawable.avatar1),
            Contact("Trần Thị B", "0987 654 321", R.drawable.avatar2),
            Contact("Lê Văn C", "0912 345 678", R.drawable.avatar3),
            Contact("Phạm Minh D", "0934 567 890", R.drawable.avatar1),
            Contact("Hoàng Lan E", "0902 888 555", R.drawable.avatar2)
        )

        val adapter = ContactAdapter(contacts)
        binding.rvContacts.adapter = adapter
    }
}
