package com.example.hwk2

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class ObjDetailScreenGenerator (val ctx : Context) {
    lateinit var layoutObj : LinearLayout

    @SuppressLint("SetTextI18n")
    fun generate() : LinearLayout {
        // 1. Create a LinearLayout object
        layoutObj = LinearLayout(ctx)
        val lParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        layoutObj.layoutParams = lParams
        layoutObj.orientation = LinearLayout.VERTICAL
        layoutObj.setBackgroundColor(Color.DKGRAY)

        // 2. Add Screen Instructions
        val iParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        iParams.topMargin = 5
        iParams.bottomMargin = 5

        var instructions = TextView(ctx)
        instructions.text = "Please Enter Claim Information"
        instructions.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
        instructions.setTypeface(Typeface.create("arial", Typeface.BOLD))
        instructions.gravity = Gravity.CENTER
        instructions.setTextColor(Color.BLUE)
        instructions.setBackgroundColor(Color.WHITE)
        layoutObj.addView(instructions, iParams)

        // 3. Add ObjDetailSection
        val fldRowGenerator = ObjDetailSectionGenerator(ctx)
        val colView = fldRowGenerator.generate()
        layoutObj.addView(colView)

        // 4. Create "Add" Button LinearLayout
        val aLayout = LinearLayout(ctx)
        val aParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        aParams.gravity = Gravity.RIGHT
        aLayout.layoutParams = aParams
        aLayout.orientation = LinearLayout.HORIZONTAL
        aLayout.setBackgroundColor(Color.GRAY)

        // 5. Create "Add" Button
        val addButton = Button(ctx)
        addButton.text = "Add"
        addButton.setId(R.id.add_btn)
        addButton.setBackgroundColor(Color.CYAN)
        val abParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        abParams.gravity = Gravity.BOTTOM
        abParams.topMargin = 5
        aLayout.addView(addButton, abParams)

        // Add button LinearLayout to outer LinearLayout
        layoutObj.addView(aLayout)

        // 6. Add Status Message
        // Status title
        val sLayout = LinearLayout(ctx)
        val stParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        sLayout.orientation = LinearLayout.HORIZONTAL

        val sParams = LinearLayout.LayoutParams(
            0,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        var status = TextView(ctx)
        status.text = "Status:"
        status.gravity = Gravity.CENTER
        status.setTextColor(Color.YELLOW)
        status.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)
        status.setTypeface(Typeface.create("arial", Typeface.BOLD))

        sLayout.layoutParams = stParams
        sParams.weight = 1.0F
        sParams.topMargin = 5
        sLayout.addView(status, sParams)

        // Status message area
        val smParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT)

        status = TextView(ctx)
        status.setId(R.id.status_msg)
        status.text = "Input both a title and date to create a claim."
        status.setTextColor(Color.WHITE)
        status.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16F)
        smParams.weight = 3.0F
        smParams.topMargin = 5
        sLayout.addView(status, smParams)

        layoutObj.addView(sLayout)

        return layoutObj
    }

}