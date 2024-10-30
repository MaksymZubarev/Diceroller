package com.example.diceroller

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.diceroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: DiceViewModel by viewModels()

    private lateinit var diceViewer: List<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate: activity = $this")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        diceViewer = listOf(
            binding.diceView1, binding.diceView2,
            binding.diceView3, binding.diceView4,
            binding.diceView5
        )

        // Спостерігаємо за змінами у значеннях кубиків
        viewModel.diceValues.observe(this, Observer { diceValues ->
            diceValues?.let {
                for (i in it.indices) {
                    diceViewer[i].setImageResource(getDiceImage(it[i]))
                }
            }
        })

        // Спостерігаємо за станом обертання
        viewModel.isRolling.observe(this, Observer { isRolling ->
            binding.btnRoll.isEnabled = !isRolling
            binding.btnRoll.visibility = if (isRolling) View.GONE else View.VISIBLE
            binding.btnStop.isEnabled = isRolling
            binding.btnStop.visibility = if (isRolling) View.VISIBLE else View.GONE
        })

        binding.btnRoll.setOnClickListener {
            Log.d(TAG, "onCreate: Rolling starts")
            viewModel.rollStart()
        }

        binding.btnStop.setOnClickListener {
            viewModel.rollStop()
        }
    }

    private fun getDiceImage(value: Int): Int {
        return when (value) {
            1 -> R.drawable.dice_one
            2 -> R.drawable.dice_two
            3 -> R.drawable.dice_three
            4 -> R.drawable.dice_four
            5 -> R.drawable.dice_five
            6 -> R.drawable.dice_six
            else -> R.drawable.dice_one // дефолтне значення
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "onDestroy: ")
    }

    companion object {
        val TAG = "XXXXXX"
    }
}