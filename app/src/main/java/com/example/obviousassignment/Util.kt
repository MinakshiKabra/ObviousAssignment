package com.example.obviousassignment

import android.content.Context
import android.net.ConnectivityManager
import java.io.InputStream

class Util {

    companion object
    {
        fun readJSONFromAsset( context: Context): String? {
            var json: String? = null
            try {
                val  inputStream: InputStream = context.assets.open("data.json")
                json = inputStream.bufferedReader().use{it.readText()}

            } catch (ex: Exception) {
                ex.printStackTrace()
                return null
            }
            return json
        }
        fun isOnline(context: Context): Boolean {

            val conMgr = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return conMgr.activeNetworkInfo != null && conMgr.activeNetworkInfo!!.isAvailable && conMgr.activeNetworkInfo!!.isConnected
        }

    }


}