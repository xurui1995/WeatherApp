package xur.com.weatherapp.extensions

import java.text.DateFormat
import java.util.*

/**
 * Created by xur on 2018/2/22.
 */
fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}