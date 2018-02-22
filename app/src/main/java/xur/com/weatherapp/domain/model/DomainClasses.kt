package xur.com.weatherapp.domain.model

/**
 * Created by xur on 2018/2/4.
 */
class ForecastList(val id: Long,val city: String, val country: String, val dailyForecast: List<Forecast>) {
    val size: Int
        get() = dailyForecast.size

    operator fun get(position: Int): Forecast = dailyForecast[position]
}
data class Forecast(val id: Long, val date: Long, val description: String, val high: Int, val low: Int, val iconUrl: String)