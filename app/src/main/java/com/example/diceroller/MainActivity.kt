package com.example.diceroller

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.diceroller.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnRoll.setOnClickListener {
            lifecycleScope.launch {
                rollStart()
            }
        }

        binding.btnStop.setOnClickListener {
            lifecycleScope.launch {
                rollStop()
            }
        }
    }

    private suspend fun rollStart() {

        binding.btnRoll.isEnabled = false
        binding.btnRoll.visibility = View.GONE

        binding.btnStop.isEnabled = true
        binding.btnStop.visibility = View.VISIBLE

        Toast.makeText(this, "ROLL! clicked", Toast.LENGTH_SHORT).show()

    }

    private suspend fun rollStop() {

        binding.btnStop.isEnabled = false
        binding.btnStop.visibility = View.GONE

        binding.btnRoll.isEnabled = true
        binding.btnRoll.visibility = View.VISIBLE

        Toast.makeText(this, "STOP clicked", Toast.LENGTH_SHORT).show()

    }

}