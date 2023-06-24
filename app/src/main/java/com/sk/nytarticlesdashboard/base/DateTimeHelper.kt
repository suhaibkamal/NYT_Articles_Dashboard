package com.sk.nytarticlesdashboard.base

import android.text.format.DateUtils
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar

class DateTimeHelper {


    fun getRelativeTimeSpanString( srcDate:String):String {

        var result:String = ""
        val desiredFormat =  SimpleDateFormat(
            "yyyy-mm-dd");

        var dateInMillis:Long? = 0
        try {
            var date = desiredFormat.parse(srcDate);
            dateInMillis = date.getTime()
            val diff = Calendar.getInstance().timeInMillis - dateInMillis

            val oneSec = 1000L
            val oneMin: Long = 60 * oneSec
            val oneHour: Long = 60 * oneMin
            val oneDay: Long = 24 * oneHour
            val oneMonth: Long = 30 * oneDay
            val oneYear: Long = 365 * oneDay

            val diffMin: Long = diff / oneMin
            val diffHours: Long = diff / oneHour
            val diffDays: Long = diff / oneDay
            val diffMonths: Long = diff / oneMonth
            val diffYears: Long = diff / oneYear

            when {
                diffYears > 0 -> {
                    result += " $diffYears years ago"
                }
                diffMonths > 0 && diffYears < 1 -> {
                    result += " ${(diffMonths - diffYears / 12)} months ago "
                }
                diffDays > 0 && diffMonths < 1 -> {
                    result += " ${(diffDays - diffMonths / 30)} days ago "
                }
                diffHours > 0 && diffDays < 1 -> {
                    result += " ${(diffHours - diffDays * 24)} hours ago "
                }
                diffMin > 0 && diffHours < 1 -> {
                    result += " ${(diffMin - diffHours * 60)} min ago "
                }
                diffMin < 1 -> {
                    result += " just now"
                }
            }

        } catch ( ex:Exception) {
            ex.message?.let { Log.d("Exception while parsing date." , it) }
            ex.printStackTrace()
        }

        return result
    }
}