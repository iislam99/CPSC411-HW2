package com.example.hwk2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.lang.Double.parseDouble

class MainActivity : AppCompatActivity() {
    var cService : ClaimService = ClaimService(this)

    @SuppressLint("SetTextI18n")
    fun statusSuccess() {
        val titleView = findViewById<EditText>(R.id.claim_title)
        val dateView  = findViewById<EditText>(R.id.claim_date)
        val title = titleView.text.toString()

        val sView : TextView = findViewById(R.id.status_msg)
        sView.text = "Claim \"${title}\" was created and added to the database successfully."

        titleView.text.clear()
        dateView.text.clear()
    }

    @SuppressLint("SetTextI18n")
    fun statusFail() {
        val titleView = findViewById<EditText>(R.id.claim_title)
        val dateView  = findViewById<EditText>(R.id.claim_date)
        val title = titleView.text.toString()

        val sView : TextView = findViewById(R.id.status_msg)
        sView.text = "Claim \"${title}\" failed to create. Error occurred while adding to database."

        titleView.text.clear()
        dateView.text.clear()
    }

    fun isValidDate (date : String) : Boolean {
        lateinit var sub : String

        // Check date string length
        if (date.length != 10)
            return false

        // Check year
        try {
            sub = date.substring(0, 4)
        } catch (e: StringIndexOutOfBoundsException) {
            return false
        }
        try {
            val num = parseDouble(sub)
        } catch (e: NumberFormatException) {
            return false
        }

        // Check space after year
        try {
            sub = date[4].toString()
        } catch (e: StringIndexOutOfBoundsException) {
            return false
        }
        if (sub != " ")
            return false

        // Check month
        try {
            sub = date.substring(5, 7)
        } catch (e: StringIndexOutOfBoundsException) {
            return false
        }
        try {
            val num = parseDouble(sub)
        } catch (e: NumberFormatException) {
            return false
        }

        // Check hyphen after month
        try {
            sub = date[7].toString()
        } catch (e: StringIndexOutOfBoundsException) {
            return false
        }
        if (sub != "-")
            return false

        // Check day
        try {
            sub = date.substring(8)
        } catch (e: StringIndexOutOfBoundsException) {
            return false
        }
        try {
            val num = parseDouble(sub)
        } catch (e: NumberFormatException) {
            return false
        }

        return true
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Creating screen components
        val fldRowGenerator = ObjDetailScreenGenerator(this)
        val colView = fldRowGenerator.generate()
        setContentView(colView)

        // Assigning ID to button object to associate it with specific button
        val bView : Button = findViewById(R.id.add_btn)

        // ASSIGNING FUNCTION TO BUTTON
        // Add new Claim object to database if button clicked
        bView.setOnClickListener {
            // Reading inputted data
            val titleView = findViewById<EditText>(R.id.claim_title)
            val dateView  = findViewById<EditText>(R.id.claim_date)

            val title = titleView.text.toString()
            val date = dateView.text.toString()

            val sView : TextView = findViewById(R.id.status_msg)

            // CHECKING IF USER INPUT IS VALID

            // If title and date are not inputted
            if (title == "" && date == "")
                sView.text = "Claim failed to create. You must input both a title and date."

            // If only date is inputted
            else if (title == "")
                sView.text = "Claim failed to create. You must input a title."

            // If only title is inputted
            else if (date == "")
                sView.text = "Claim \"${title}\" failed to create. You must input a date."

            // If date not inputted in the correct format
            else if (!isValidDate(date))
                sView.text = "Claim failed to create. You must input a valid date in the format: yyyy MM-dd"

            // Input is valid; adding to database
            else {
                cService.addClaim(Claim(title, date))
            }
        }
    }
}