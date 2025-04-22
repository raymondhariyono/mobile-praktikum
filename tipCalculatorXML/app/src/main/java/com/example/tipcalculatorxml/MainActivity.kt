package com.example.tipcalculatorxml

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.tipcalculatorxml.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val tipOptions = arrayOf("15%", "18%", "20%")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.tipDropdown.setAdapter(adapter)

        val updateTip = {
            calculateTip()
        }

        binding.amountInput.setOnEditorActionListener { _, _, _ ->
            updateTip(); true
        }

        binding.tipDropdown.setOnItemClickListener { parent, view, position, id ->
            updateTip()
        }

        binding.roundUpSwitch.setOnCheckedChangeListener { _, _ ->
            updateTip()
        }
    }

    private fun calculateTip() {
        val input = binding.amountInput.text.toString()
        val amount = input.toDoubleOrNull()

        if (amount == null || amount <= 0) {
            binding.tipResult.text = "Tip Amount: $0.00"
            return
        }

        val selectedTip = binding.tipDropdown.text.toString().replace("%", "").toDouble()
        var tip = amount * (selectedTip / 100)

        if (binding.roundUpSwitch.isChecked) {
            tip = ceil(tip)
        }

        binding.tipResult.text = "Tip Amount: $${"%.2f".format(tip)}"
    }
}
