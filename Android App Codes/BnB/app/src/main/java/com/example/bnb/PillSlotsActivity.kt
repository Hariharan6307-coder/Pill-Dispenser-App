package com.example.bnb

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bnb.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class PillSlotsActivity : AppCompatActivity() {

    private lateinit var timeInput: TextInputEditText
    private lateinit var pillName1: TextInputEditText
    private lateinit var dose1: TextInputEditText
    private lateinit var pillName2: TextInputEditText
    private lateinit var dose2: TextInputEditText
    private lateinit var pillName3: TextInputEditText
    private lateinit var dose3: TextInputEditText
    private lateinit var btnSave: MaterialButton
    private lateinit var home_btn: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pill_slots)

        // Bind views
        timeInput = findViewById(R.id.time)
        pillName1 = findViewById(R.id.pillname1)
        dose1 = findViewById(R.id.dose1)
        pillName2 = findViewById(R.id.pillname2)
        dose2 = findViewById(R.id.dose2)
        pillName3 = findViewById(R.id.pillname3)
        dose3 = findViewById(R.id.dose3)
        btnSave = findViewById(R.id.btnSave)
        home_btn = findViewById(R.id.home_icon)

        // Handle Save button click
        btnSave.setOnClickListener {
            val time = timeInput.text?.toString()?.trim()
            val med1 = pillName1.text?.toString()?.trim()
            val d1 = dose1.text?.toString()?.trim()
            val med2 = pillName2.text?.toString()?.trim()
            val d2 = dose2.text?.toString()?.trim()
            val med3 = pillName3.text?.toString()?.trim()
            val d3 = dose3.text?.toString()?.trim()

            // Build result string
            val result = """
                Time: $time
                $med1 - $d1
                $med2 - $d2
                $med3 - $d3
            """.trimIndent()

            // Show as a Toast for now
            Toast.makeText(this, result, Toast.LENGTH_LONG).show()
        }

        home_btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
