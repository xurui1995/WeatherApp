package xur.com.weatherapp.extensions

import kotlin.reflect.KProperty

/**
 * Created by xur on 2018/2/8.
 */

object DelegatesExt {
    fun <T> notNullSingleValue() = NotNullSingleValueVar<T>()
}
class NotNullSingleValueVar<T> {
    private var value: T? = null
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value?: throw IllegalStateException("${property.name} not initialized")
    }
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("${property.name} already initialized")
    }
}