package xur.com.weatherapp.domain.datasource

import xur.com.weatherapp.data.db.ForecastDb
import xur.com.weatherapp.data.server.ForecastServer
import xur.com.weatherapp.domain.model.Forecast
import xur.com.weatherapp.domain.model.ForecastList
import xur.com.weatherapp.extensions.firstResult

/**
 * Created by xur on 2018/2/12.
 */
class ForecastProvider(private val sources: List<ForecastDataSource> = ForecastProvider.SOURCES) {
    companion object {
        val DAY_IN_MILLIS = 1000 * 60 * 60 * 24
        val SOURCES = listOf(ForecastDb(), ForecastServer())
    }

    fun requestByZipCode(zipCode: Long, days: Int): ForecastList = requestToSources {
        val res = it.requestForecastByZipCode(zipCode, todayTimeSpan())
        if (res != null && res.size >= days) res else null
    }
    fun requestForecast(id: Long): Forecast = requestToSources { it.requestDayForecast(id) }

    private fun todayTimeSpan() = System.currentTimeMillis() / DAY_IN_MILLIS * DAY_IN_MILLIS


    private fun <T : Any> requestToSources(f: (ForecastDataSource) -> T?): T = sources.firstResult { f(it) }
}