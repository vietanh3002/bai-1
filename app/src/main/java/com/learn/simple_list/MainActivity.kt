package com.learn.simple_list

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CalendarView
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var edtNumber: EditText
    lateinit var radioGroupType: RadioGroup
    lateinit var btnShow: Button
    lateinit var tvError: TextView
    lateinit var listView: ListView


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        edtNumber = findViewById(R.id.edtNumber)
        radioGroupType = findViewById(R.id.radioGroupType)
        btnShow = findViewById(R.id.btnShow)
        tvError = findViewById(R.id.tvError)
        listView = findViewById(R.id.listView)


        // Xử lý ẩn/hiện CalendarView
        btnShow.setOnClickListener {
            tvError.visibility = View.GONE  // Ẩn TextView lỗi
            val inputText = edtNumber.text.toString()

            // Kiểm tra dữ liệu hợp lệ
            if (inputText.isEmpty()) {
                tvError.text = "Vui lòng nhập số nguyên dương"
                tvError.visibility = View.VISIBLE
                return@setOnClickListener
            }

            val n = inputText.toIntOrNull()
            if (n == null || n <= 0) {
                tvError.text = "Vui lòng nhập số nguyên dương hợp lệ"
                tvError.visibility = View.VISIBLE
                return@setOnClickListener
            }

            // Kiểm tra loại số đã chọn
            val selectedTypeId = radioGroupType.checkedRadioButtonId
            val results = when (selectedTypeId) {
                R.id.radioEven -> getEvenNumbers(n)
                R.id.radioOdd -> getOddNumbers(n)
                R.id.radioSquare -> getSquareNumbers(n)
                else -> {
                    tvError.text = "Vui lòng chọn loại số"
                    tvError.visibility = View.VISIBLE
                    return@setOnClickListener
                }
            }

            // Hiển thị kết quả vào ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, results)
            listView.adapter = adapter
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        val evenNumbers = mutableListOf<Int>()
        for (i in 0..n step 2) {
            evenNumbers.add(i)
        }
        return evenNumbers
    }

    // Hàm lấy danh sách số lẻ từ 1 đến n
    private fun getOddNumbers(n: Int): List<Int> {
        val oddNumbers = mutableListOf<Int>()
        for (i in 1..n step 2) {
            oddNumbers.add(i)
        }
        return oddNumbers
    }

    // Hàm lấy danh sách số chính phương từ 0 đến n
    private fun getSquareNumbers(n: Int): List<Int> {
        val squareNumbers = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            squareNumbers.add(i * i)
            i++
        }
        return squareNumbers
    }
}