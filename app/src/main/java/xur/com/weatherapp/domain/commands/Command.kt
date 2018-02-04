package xur.com.weatherapp.domain.commands

/**
 * Created by xur on 2018/2/4.
 */
interface Command<out T> {
    fun execute(): T
}