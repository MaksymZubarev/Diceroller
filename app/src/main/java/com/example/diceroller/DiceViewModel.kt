package com.example.diceroller
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.diceroller.MainActivity.Companion.TAG

class DiceViewModel : ViewModel() {

    init {
        Log.d(TAG, "MyViewModel created: ${this}")
    }

    private val _diceValues = MutableLiveData<List<Int>>()
    val diceValues: LiveData<List<Int>> = _diceValues

    private val _isRolling = MutableLiveData(false)
    val isRolling: LiveData<Boolean> = _isRolling

    fun rollStart() {
        _isRolling.value = true

        viewModelScope.launch {
            while (_isRolling.value == true) {
//                Log.d(TAG, "onProcess: Rolling...")
                _diceValues.value = List(5) { (1..6).random() }
                delay(100)
            }
        }
    }

    fun rollStop() {
        _isRolling.value = false
        Log.d(TAG, "onFinish: Rolling is over")
    }
}