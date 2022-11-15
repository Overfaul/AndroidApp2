package com.example.secondapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.secondapplication.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater) //initializing the binding class
        setContentView(binding.root)

        binding.btn0.setOnClickListener { setTextFields("0") }
        binding.btn1.setOnClickListener { setTextFields("1") }
        binding.btn2.setOnClickListener { setTextFields("2") }
        binding.btn3.setOnClickListener { setTextFields("3") }
        binding.btn4.setOnClickListener { setTextFields("4") }
        binding.btn5.setOnClickListener { setTextFields("5") }
        binding.btn6.setOnClickListener { setTextFields("6") }
        binding.btn7.setOnClickListener { setTextFields("7") }
        binding.btn8.setOnClickListener { setTextFields("8") }
        binding.btn9.setOnClickListener { setTextFields("9") }
        binding.minusBtn.setOnClickListener { setTextFields("-") }
        binding.plusBtn.setOnClickListener { setTextFields("+") }
        binding.slashBtn.setOnClickListener { setTextFields("/") }
        binding.multBtn.setOnClickListener { setTextFields("*") }
        binding.leftparBtn.setOnClickListener { setTextFields("(") }
        binding.rightparBtn.setOnClickListener { setTextFields(")") }

        binding.clearBtn.setOnClickListener {
            binding.mathOperation.text = ""
            // binding result text = ""
        }

        binding.backBtn.setOnClickListener {
            var str = binding.mathOperation.text.toString()
            if(str.isNotEmpty())
                binding.mathOperation.text = str.substring(0, str.length - 1)
            // binding result text = ""

        }

        binding.equalBtn.setOnClickListener {
            try {
                val intent = Intent(this, MainActivity2::class.java)
                var ex = ExpressionBuilder(binding.mathOperation.text.toString()).build()
                var result = ex.evaluate()
                print("$result")

                var longRes = result.toLong()
                if(result == longRes.toDouble()){
                    intent.putExtra("RESULT", longRes.toString());
                    startActivity(intent)
                }
            } catch (e: Exception){
                val intent = Intent(this, MainActivity2::class.java)
                Log.d("Error", "Smth went wrong")
                startActivity(intent)
            }
        }
    }

    fun setTextFields(str: String){
       binding.mathOperation.append(str)
    }
}