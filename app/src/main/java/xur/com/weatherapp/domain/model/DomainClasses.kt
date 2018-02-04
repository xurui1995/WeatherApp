package xur.com.weatherapp.domain.model

/**
 * Created by xur on 2018/2/4.
 */
class ForecastList(val city: String, val country: String, val dailyForecast: List<Forecast>)
data class Forecast(val date: String, val description: String, val high: Int, val low: Int)