//package com.example.bnb
//
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import com.google.android.material.button.MaterialButton
//import android.widget.Button
//import android.widget.ImageButton
//import com.example.bnb.R
//
//
//
//class MainActivity : AppCompatActivity() {
//
////    private lateinit var rvMorningPills: RecyclerView
////    private lateinit var rvEveningPills: RecyclerView
////    private lateinit var rvNightPills: RecyclerView
////    private lateinit var btnPillSlots: MaterialButton
////
////    private lateinit var morningAdapter: PillAdapter
////    private lateinit var eveningAdapter: PillAdapter
////    private lateinit var nightAdapter: PillAdapter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        // Initialize views
////        rvMorningPills = findViewById(R.id.rvMorningPills)
////        rvEveningPills = findViewById(R.id.rvEveningPills)
////        rvNightPills = findViewById(R.id.rvNightPills)
////        btnPillSlots = findViewById(R.id.btnPillSlots)
//
////        setupRecyclerViews()
////        setupClickListeners()
//
//        val btn = findViewById<ImageButton>(R.id.pill_btn)
//
//        btn.setOnClickListener {
//            val intent = Intent(this, PillSlotsActivity::class.java)
//            startActivity(intent)
//        }
//    }
//
////    private fun setupRecyclerViews() {
////        // Morning pills
////        morningAdapter = PillAdapter(getMorningPills())
////        rvMorningPills.layoutManager = LinearLayoutManager(this)
////        rvMorningPills.adapter = morningAdapter
////
////        // Evening pills
////        eveningAdapter = PillAdapter(getEveningPills())
////        rvEveningPills.layoutManager = LinearLayoutManager(this)
////        rvEveningPills.adapter = eveningAdapter
////
////        // Night pills
////        nightAdapter = PillAdapter(getNightPills())
////        rvNightPills.layoutManager = LinearLayoutManager(this)
////        rvNightPills.adapter = nightAdapter
////    }
//
////    private fun getMorningPills(): List<Pill> {
////        return listOf(
////            Pill("Cetirizine Tablets", 2),
////            Pill("Cetirizine Tablets", 2),
////            Pill("Cetirizine Tablets", 2),
////            Pill("Cetirizine Tablets", 2)
////        )
////    }
////
////    private fun getEveningPills(): List<Pill> {
////        return listOf(
////            Pill("Cetirizine Tablets", 2),
////            Pill("Cetirizine Tablets", 2)
////        )
////    }
////
////    private fun getNightPills(): List<Pill> {
////        return listOf(
////            Pill("Cetirizine Tablets", 2),
////            Pill("Cetirizine Tablets", 2)
////        )
////    }
////
////    private fun setupClickListeners() {
////        btnPillSlots.setOnClickListener {
////            startActivity(Intent(this, PillSlotsActivity::class.java))
////        }
////    }
////}
//
//data class Pill(val name: String, val quantity: Int)}
package com.example.bnb

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pillBtn = findViewById<ImageButton>(R.id.pill_btn)

        pillBtn.setOnClickListener {
            val intent = Intent(this, PillSlotsActivity::class.java)
            startActivity(intent)
        }

        val controlBtn = findViewById<ImageButton>(R.id.remote)
        controlBtn.setOnClickListener {
            val intent = Intent(this, RemoteControlActivity::class.java)
            startActivity(intent)
        }
    }
}