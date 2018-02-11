package xur.com.weatherapp.data.db

import xur.com.weatherapp.domain.model.Forecast
import xur.com.weatherapp.domain.model.ForecastList

/**
 * Created by xur on 2018/2/11.
 */
class DbDataMapper {
    fun convertFromDomain(forecast: ForecastList) = with(forecast) {
        val daily = dailyForecast.map {
            convertDayFromDomain(id, it)
        }
        CityForecast(id, city, country, daily)
    }

    private fun convertDayFromDomain(cityId: Long, forecast: Forecast) = with(forecast) {
        DayForecast(date, description, high, low, iconUrl, cityId)
    }
    fun convertToDomain(forecast: CityForecast) = with(forecast) {
        val daily = dailyForecast.map { convertDayToDomain(it) }
        ForecastList(_id, city, country, daily)
    }
    private fun convertDayToDomain(dayForecast: DayForecast) = with(dayForecast) {
        Forecast(date, description, high, low, iconUrl)
    }
}