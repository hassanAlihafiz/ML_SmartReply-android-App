package com.example.smartreplyapp

import android.content.Context
import android.widget.Toast

fun Context.showToast(mess:String) {
    Toast.makeText(this,mess,Toast.LENGTH_SHORT).show()
}