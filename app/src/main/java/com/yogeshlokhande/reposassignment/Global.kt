package com.yogeshlokhande.reposassignment

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*


fun View.isVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

fun Context.showToast(message:String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.showSnackbar(message:String){
    Snackbar.make(this,message,Snackbar.LENGTH_SHORT).show()
}

@SuppressLint("SimpleDateFormat")
fun getFormattedDate(date: String): String {
    val curFormater = SimpleDateFormat("yyyy-MM-dd")
    val dateObj: Date = curFormater.parse(date)

    val newDateStr: String = curFormater.format(dateObj)
    return newDateStr
}