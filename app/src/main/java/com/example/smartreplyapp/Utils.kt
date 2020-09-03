package com.example.smartreplyapp

import android.widget.EditText

object Utils {

    fun getText(editText: EditText) = editText.text.toString().trim()
    fun validate(vararg input:String):Boolean{
        for(str in input){
            if(str.isNullOrEmpty())
            {
                return false
            }

        }
        return true
    }
}