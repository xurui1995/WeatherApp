package xur.com.weatherapp.data.server

import xur.com.weatherapp.data.db.ForecastDb
import xur.com.weatherapp.domain.datasource.ForecastDataSource
import xur.com.weatherapp.domain.model.ForecastList

/**
 * Created by xur on 2018/2/12.
 */
class ForecastServer(private val dataMapper: ServerDataMapper = ServerDataMapper(),
                     private val forecastDb: ForecastDb = ForecastDb()) : ForecastDataSource {
    override fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList? {
        val result = ForecastByZipCodeRequest(zipCode).execute()
        val converted = dataMapper.convertToDomain(zipCode, result)
        forecastDb.saveForecast(converted)
        return forecastDb.requestForecastByZipCode(zipCode, date)
    }

    override fun requestDayForecast(id: Long) = throw UnsupportedOperationException()
}