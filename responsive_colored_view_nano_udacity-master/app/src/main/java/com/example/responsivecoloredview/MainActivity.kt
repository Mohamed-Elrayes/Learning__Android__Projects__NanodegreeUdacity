package com.example.responsivecoloredview

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListener()
    }

    private fun setListener() {
        val clickableViews: List<Int> = listOf(
            R.id.box_one_txt,
            R.id.box_two_txt,
            R.id.box_three_txt,
            R.id.box_four_txt,
            R.id.box_five_txt,

            )
        for (item in clickableViews) {
            val layo = findViewById<TextView>(item);
            layo.setOnClickListener {
                makeColored(it)
            }
        }
    }

    fun makeColored(view: View) {
        when (view.id) {
            // Boxes using Color class colors for background
            R.id.box_one_txt -> view.setBackgroundColor(Color.DKGRAY)
            R.id.box_two_txt -> view.setBackgroundColor(Color.GRAY)
            // Boxes using Android color resources for background
            R.id.box_three_txt -> view.setBackgroundResource(android.R.color.holo_green_light)
            R.id.box_four_txt -> view.setBackgroundResource(android.R.color.holo_green_dark)
            R.id.box_five_txt -> view.setBackgroundResource(android.R.color.holo_green_light)
            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }
}