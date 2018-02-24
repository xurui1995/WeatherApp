package xur.com.weatherapp.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

/**
 * Created by xur on 2018/2/7.
 */
val View.ctx: Context
    get() = context

fun ViewGroup.inflate(layoutId: Int): View {
    return LayoutInflater.from(context).inflate(layoutId, this, false)
}

var TextView.textColor: Int
    get() = currentTextColor
    set(value) = setTextColor(value)

fun View.slideExit() {
    if (translationY == 0f) animate().translationY(-height.toFloat())
}
fun View.slideEnter() {
    if (translationY < 0f) animate().translationY(0f)
}
