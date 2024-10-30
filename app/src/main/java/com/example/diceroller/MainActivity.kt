package com.example.diceroller

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.diceroller.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var diceViewer: List<ImageView>

    private var isLoopWorking by Delegates.notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        isLoopWorking = false

        diceViewer = listOf(binding.diceView1,
            binding.diceView2, binding.diceView3,
            binding.diceView4, binding.diceView5)

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

        isLoopWorking = true

        while (isLoopWorking) {
            delay(100)
            for (i in 0..4) {
                when ((1..6).random()) {
                    1 -> diceViewer[i].setImageResource(R.drawable.dice_one)
                    2 -> diceViewer[i].setImageResource(R.drawable.dice_two)
                    3 -> diceViewer[i].setImageResource(R.drawable.dice_three)
                    4 -> diceViewer[i].setImageResource(R.drawable.dice_four)
                    5 -> diceViewer[i].setImageResource(R.drawable.dice_five)
                    6 -> diceViewer[i].setImageResource(R.drawable.dice_six)
                }
            }
        }
    }

    private suspend fun rollStop() {

        binding.btnStop.isEnabled = false
        binding.btnStop.visibility = View.GONE

        binding.btnRoll.isEnabled = true
        binding.btnRoll.visibility = View.VISIBLE

        Toast.makeText(this, "STOP clicked", Toast.LENGTH_SHORT).show()

        isLoopWorking = false
    }
}