package com.batuhanozdemir.hesapmakinesi

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.batuhanozdemir.hesapmakinesi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var process = ""
    private var isInProcess = false
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun calculate(view: View){
        if (isInProcess){
            val numbers = process.split(operator)
            val number1 = numbers[0].toDouble()
            val number2 = numbers[1].toDouble()
            var result = 0.0

            when(operator){
                "+" -> result = number1 + number2
                "-" -> result = number1 - number2
                "x" -> result = number1 * number2
                "÷" -> result = number1 / number2
                "%" -> result = number1 * (number2 / 100.0)
            }

            binding.subNumText.text = process

            if (result % 1 == 0.0){
                val resultInt = result.toInt()
                binding.resultText.text = resultInt.toString()
                process = resultInt.toString()
            }else{
                binding.resultText.text = result.toString()
                process = result.toString()
            }

            //process = ""
            isInProcess = false
        }

    }

    fun setProcess(view: View){
        if (!isInProcess){
            when (view.id){
                binding.buttonPlus.id -> operator = "+"
                binding.buttonMinus.id -> operator = "-"
                binding.buttonX.id -> operator = "x"
                binding.buttonSlash.id -> operator = "÷"
                binding.buttonPercent.id -> operator = "%"
            }
            process += operator
            binding.resultText.text = process
            isInProcess = true
        }

    }

    fun clear(view: View){

        if (process.isEmpty()){
            binding.subNumText.text = ""
            binding.buttonClear.text = "C"
        }else {
            binding.buttonClear.text = "AC"
        }

        process = ""
        binding.resultText.text = "0"
        isInProcess = false
    }

    fun remove(view: View){
        var numberStr = process

        if (numberStr.length > 1){
            numberStr = numberStr.substring(0,numberStr.length - 1)
        }else {
            numberStr = "0"
        }

        binding.resultText.text = numberStr

        process = numberStr

    }

    fun writeNumber(view: View){
        when (view.id){
            binding.button0.id -> process += "0"
            binding.button1.id -> process += "1"
            binding.button2.id -> process += "2"
            binding.button3.id -> process += "3"
            binding.button4.id -> process += "4"
            binding.button5.id -> process += "5"
            binding.button6.id -> process += "6"
            binding.button7.id -> process += "7"
            binding.button8.id -> process += "8"
            binding.button9.id -> process += "9"
            binding.buttonDot.id -> process += "."
        }
        binding.resultText.text = process
    }
}