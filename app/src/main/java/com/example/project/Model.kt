package com.example.project

import androidx.lifecycle.ViewModel

class Model:ViewModel() {
    var number=0
    var name=""
    var url=""
    var numberQuestion=0

    var category:ArrayList<String> = ArrayList()
    var result:ArrayList<Question> = ArrayList()

    fun plus(){
        number++
    }

    override fun onCleared() {
        super.onCleared()

    }



}