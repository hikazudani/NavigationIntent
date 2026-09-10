package br.edu.ifsp.scl.sc3038432.navigationintent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    var parameter by mutableStateOf("")
        private set

    fun updateParameter(value: String) {
        this.parameter = value
    }
}