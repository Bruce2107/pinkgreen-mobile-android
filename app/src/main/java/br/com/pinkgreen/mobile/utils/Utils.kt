package br.com.pinkgreen.mobile.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Utils {
    inline fun <reified T> getJsonDataFromAsset(context: Context, fileName: String): T {
        val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }

        val gson = Gson()
        val type = object : TypeToken<T>() {}.type

        return gson.fromJson(jsonString, type)
    }
}