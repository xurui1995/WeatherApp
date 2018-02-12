package xur.com.weatherapp.data.server

import xur.com.weatherapp.domain.model.ForecastList
import java.util.*
import java.util.concurrent.TimeUnit
import xur.com.weatherapp.domain.model.Forecast as ModelForecast
/**
 * Created by xur on 2018/2/4.
 */
class ServerDataMapper {
    fun convertToDomain(zipCode: Long, forecast: ForecastResult): ForecastList {
        return ForecastList(zipCode, forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list))
    }

    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast> {
        return list.mapIndexed { index, forecast ->
            val dt = Calendar.getInstance().timeInMillis + TimeUnit.DAYS.toMillis(index.toLong())
            convertForecastItemToDomain(forecast.copy(dt = dt))
        }
    }

    private fun convertForecastItemToDomain(forecast: Forecast): ModelForecast {
        return ModelForecast(forecast.dt, forecast.weather[0].description,
                forecast.temp.max.toInt(), forecast.temp.min.toInt(), generateIconUrl(forecast.weather[0].icon))
    }

    private fun generateIconUrl(iconCode: String): String = "http://openweathermap.org/img/w/$iconCode.png"
}