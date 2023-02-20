package com.example.aboutme

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding;
    val myname: MyName = MyName(name = "Mohamed Salah")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.myName = myname
        binding.btnTxt.setOnClickListener {
            addNickName(it)
        }

    }

    private fun addNickName(view: View) {
        binding.apply {
            myName?.nickName = nicknameIdEdit.text.toString()
            val editNickNameTxt: EditText = nicknameIdEdit
            val nickNameTxt: TextView = nickNameTxt
            nickNameTxt.text = editNickNameTxt.text
            invalidateAll()
            editNickNameTxt.visibility = View.GONE
            view.visibility = View.GONE
            nickNameTxt.visibility = View.VISIBLE
        }


        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            view.windowToken,
            0
        )

    }
}