package xur.com.weatherapp.domain.commands

import xur.com.weatherapp.domain.datasource.ForecastProvider
import xur.com.weatherapp.domain.model.Forecast

/**
 * Created by xur on 2018/2/22.
 */
class RequestDayForecastCommand (
        val id: Long,
        private val forecastProvider: ForecastProvider = ForecastProvider()
) : Command<Forecast> {
    override fun execute() = forecastProvider.requestForecast(id)
}