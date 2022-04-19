package com.example.justkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.justkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.viewModel = viewModel

        viewModel.quantity.observe(this) {
            binding.numberQuantity.text = it.toString()
        }
        viewModel.whippedCreamTopping.observe(this) {
            binding.whippedCreamCheckbox.isChecked = it
        }
        viewModel.chocolateTopping.observe(this) {
            binding.chocolateCheckbox.isChecked = it
        }
        viewModel.orderSummaryMessage.observe(this) {
            val message = "Name: " + binding.userName.text + it
            binding.priceTextView.text = message
        }
    }


}