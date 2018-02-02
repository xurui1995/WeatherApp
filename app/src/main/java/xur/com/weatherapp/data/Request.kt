package xur.com.weatherapp.data

import android.util.Log
import java.net.URL

/**
 * Created by xur on 2018/2/2.
 */
class Request(private val url: String) {
    fun run() {
        val forecastJson = URL(url).readText()
        Log.d(javaClass.simpleName, forecastJson)
    }
}