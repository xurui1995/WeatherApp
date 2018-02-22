package xur.com.weatherapp.extensions

import android.content.Context
import android.support.v4.content.ContextCompat

/**
 * Created by xur on 2018/2/22.
 */
fun Context.color(res: Int): Int = ContextCompat.getColor(this, res)