package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var prevValue: Int = 0
    private var currentValue: Int = 0
    private var operand: OPERAND = OPERAND.NONE

    enum class ACTION {
        DELETE, APPEND, PREPEND, FLUSH
    }

    enum class OPERAND {
        PLUS, MINUS, MULTIPLY, DIVIDE, NONE
    }

    fun setValue(action: ACTION, value: Int = 0) {
        when (action) {
            ACTION.FLUSH -> {
                currentValue = 0
                if (value == 1) {
                    prevValue = 0
                    operand = OPERAND.NONE
                }
            }
            ACTION.APPEND -> {
                currentValue = currentValue * 10 + value
            }
            ACTION.DELETE -> {
                if (currentValue > 0) {
                    currentValue /= 10
                } else {
                    currentValue = 0
                }
            }
            ACTION.PREPEND -> {
                if (value == 0) {
                    if (currentValue != 0) {
                        currentValue = -currentValue
                    }
                }
            }
        }

        findViewById<TextView>(R.id.textView).text = currentValue.toString()
    }

    private fun commitOperand(value: OPERAND) {
        operand = value
        prevValue = currentValue
        setValue(ACTION.FLUSH)
    }

    private fun executeCalculation() {
        val result: Int
        when (operand) {
            (OPERAND.NONE) -> {
            }
            (OPERAND.PLUS) -> {
                result = prevValue + currentValue
                prevValue = 0
                currentValue = result
            }
            (OPERAND.MINUS) -> {
                result = prevValue - currentValue
                prevValue = 0
                currentValue = result
            }
            (OPERAND.MULTIPLY) -> {
                result = prevValue * currentValue
                prevValue = 0
                currentValue = result
            }
            (OPERAND.DIVIDE) -> {
                result = prevValue / currentValue
                prevValue = 0
                currentValue = result
            }
            else -> {}
        }
            findViewById<TextView>(R.id.textView).text = currentValue.toString()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextView>(R.id.textView).text = currentValue.toString()


        findViewById<Button>(R.id.button_0).setOnClickListener { setValue(ACTION.APPEND, 0) }
        findViewById<Button>(R.id.button_1).setOnClickListener { setValue(ACTION.APPEND, 1) }
        findViewById<Button>(R.id.button_2).setOnClickListener { setValue(ACTION.APPEND, 2) }
        findViewById<Button>(R.id.button_3).setOnClickListener { setValue(ACTION.APPEND, 3) }
        findViewById<Button>(R.id.button_4).setOnClickListener { setValue(ACTION.APPEND, 4) }
        findViewById<Button>(R.id.button_5).setOnClickListener { setValue(ACTION.APPEND, 5) }
        findViewById<Button>(R.id.button_6).setOnClickListener { setValue(ACTION.APPEND, 6) }
        findViewById<Button>(R.id.button_7).setOnClickListener { setValue(ACTION.APPEND, 7) }
        findViewById<Button>(R.id.button_8).setOnClickListener { setValue(ACTION.APPEND, 8) }
        findViewById<Button>(R.id.button_9).setOnClickListener { setValue(ACTION.APPEND, 9) }

        findViewById<Button>(R.id.button_ce).setOnClickListener { setValue(ACTION.FLUSH, 0) }
        findViewById<Button>(R.id.button_c).setOnClickListener { setValue(ACTION.FLUSH, 1) }

        findViewById<Button>(R.id.button_bs).setOnClickListener { setValue(ACTION.DELETE) }

        findViewById<Button>(R.id.button_sign).setOnClickListener { setValue(ACTION.PREPEND, 0) }

        findViewById<Button>(R.id.button_plus).setOnClickListener { commitOperand(OPERAND.PLUS) }
        findViewById<Button>(R.id.button_minus).setOnClickListener { commitOperand(OPERAND.MINUS) }
        findViewById<Button>(R.id.button_mul).setOnClickListener { commitOperand(OPERAND.MULTIPLY) }
        findViewById<Button>(R.id.button_div).setOnClickListener { commitOperand(OPERAND.DIVIDE) }

        findViewById<Button>(R.id.button_equal).setOnClickListener { executeCalculation() }


    }
}