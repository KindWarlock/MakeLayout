package com.example.makelayout

import android.app.ActionBar
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(applicationContext)
        layout.orientation = LinearLayout.VERTICAL

        val margin = 5
        val paramsPic = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT)
        paramsPic.weight = 1f // единичный вес
        paramsPic.setMargins(margin,margin,margin,margin)
        val paramsRow = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 0)
        paramsRow.weight = 1f // единичный вес

        val cardMap = mutableMapOf<Int, Int>()
        val colorListener = View.OnClickListener() {
            //Log.d("HELP", "${it.id}")
            if (it is ImageView) {
                if (it.tag == "open") {
                    it.setImageResource(R.drawable.c0)
                    it.tag = "closed"
                } else {
                    it.setImageResource(cardMap[it.id]!!)
                    it.tag = "open"

                }
            }
        }

        val catViews = ArrayList<ImageView>()
        for (i in 1..8) {
            val id = applicationContext.resources.getIdentifier("drawable/c$i",
                "drawable",
                applicationContext.packageName
            );
            for (j in 1..2) {
                catViews.add(
                    ImageView(applicationContext).apply {
                        //setImageResource(id)
                        //tag = "open"
                        setImageResource(R.drawable.c0)
                        tag = "closed"
                        setId(1234 + i * 10 + j)
                        layoutParams = paramsPic
                        setOnClickListener(colorListener)
                    })
                cardMap[catViews.last().id] = id
            }
        }

        catViews.shuffle()
        val rows = Array(4) { LinearLayout(applicationContext) }
        for (row in rows) {
            row.orientation = LinearLayout.HORIZONTAL
        }
        for ((count, view) in catViews.withIndex()) {
            val row: Int = count / 4
            rows[row].addView(view)
        }
        for (row in rows) {
            row.layoutParams = paramsRow
            layout.addView(row)
        }

        setContentView(layout)
    }
}