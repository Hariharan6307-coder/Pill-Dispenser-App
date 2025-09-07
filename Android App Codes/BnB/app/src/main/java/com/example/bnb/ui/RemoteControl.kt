package com.example.bnb

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteControlActivity : AppCompatActivity() {

    private lateinit var btnFlipFlap: Button
    private lateinit var btnRotate: Button
    private lateinit var btnBuzz: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remote_control)

        // Initialize buttons
        btnFlipFlap = findViewById(R.id.flipflap)
        btnRotate = findViewById(R.id.rotate)
        btnBuzz = findViewById(R.id.buzz)

        // Set click listeners
        btnFlipFlap.setOnClickListener {
            callFlipFlap()
        }

        btnRotate.setOnClickListener {
            callRotate()
        }

        btnBuzz.setOnClickListener {
            callBuzz()
        }
    }

    private fun callFlipFlap() {
        RetroClient.apiService.flipFlap().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val message = response.body()?.message ?: "Success"
                    Toast.makeText(this@RemoteControlActivity, message, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@RemoteControlActivity, "Failed to flip flap", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(this@RemoteControlActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun callRotate() {
        RetroClient.apiService.rotate().enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {
                    val message = response.body()?.message ?: "Success"
                    Toast.makeText(this@RemoteControlActivity, message, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@RemoteControlActivity, "Failed to rotate", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Toast.makeText(this@RemoteControlActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun callBuzz() {
        Toast.makeText(this, "Buzz functionality not implemented yet", Toast.LENGTH_SHORT).show()
    }
}