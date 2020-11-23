package com.example.hwk2

import android.content.Context
import android.graphics.Color
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout

class ValueColumnGenerator(val ctx : Context) {
    fun generate() : LinearLayout {
        val layoutObj = LinearLayout(ctx)
        val lParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutObj.layoutParams = lParams
        layoutObj.orientation = LinearLayout.VERTICAL
        layoutObj.setBackgroundColor(Color.BLUE)

        val vParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        vParams.topMargin = 5
        vParams.bottomMargin = 5

        var value = EditText(ctx)
        value.id = (R.id.claim_title)
        value.setHint("Enter title")
        value.setBackgroundColor(Color.LTGRAY)
        layoutObj.addView(value, vParams)

        value = EditText(ctx)
        value.id = (R.id.claim_date)
        value.setHint("Enter date as yyyy MM-dd")
        value.setBackgroundColor(Color.LTGRAY)
        layoutObj.addView(value, vParams)

        return layoutObj
    }
}