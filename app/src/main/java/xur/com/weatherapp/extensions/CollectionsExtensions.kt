package xur.com.weatherapp.extensions

/**
 * Created by xur on 2018/2/11.
 */
fun <K, V: Any> Map<K, V?>.toVarargArray(): Array<out Pair<K, V>> =
        map({ Pair(it.key, it.value!!) }).toTypedArray()