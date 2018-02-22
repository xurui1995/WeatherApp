package xur.com.weatherapp.domain.commands

import xur.com.weatherapp.domain.datasource.ForecastProvider
import xur.com.weatherapp.domain.model.ForecastList

/**
 * Created by xur on 2018/2/4.
 */
class RequestForecastCommand(
        private val zipCode: Long,
        private val forecastProvider: ForecastProvider = ForecastProvider()) :
    Command<ForecastList> {
    companion object {
        val DAYS = 7
    }

    override fun execute() = forecastProvider.requestByZipCode(zipCode, DAYS)

}