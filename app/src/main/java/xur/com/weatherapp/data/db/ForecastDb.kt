package xur.com.weatherapp.data.db

import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import xur.com.weatherapp.domain.model.ForecastList
import xur.com.weatherapp.extensions.clear
import xur.com.weatherapp.extensions.parseList
import xur.com.weatherapp.extensions.parseOpt
import xur.com.weatherapp.extensions.toVarargArray

/**
 * Created by xur on 2018/2/11.
 */
class ForecastDb(private val forecastDbHelper: ForecastDbHelper = ForecastDbHelper.instance,
                 private val dataMapper: DbDataMapper = DbDataMapper()) {
    fun requestForecastByZipCode(zipCode: Long, date: Long) = forecastDbHelper.use {
        val dailyRequest = "${DayForecastTable.CITY_ID} = ? AND ${DayForecastTable.DATE} >= ?"
        val dailyForecast = select(DayForecastTable.NAME)
                .whereSimple(dailyRequest, zipCode.toString(), date.toString())
                .parseList { DayForecast(HashMap(it)) }
        val city = select(CityForecastTable.NAME)
                .whereSimple("${CityForecastTable.ID} = ?", zipCode.toString())
                .parseOpt{CityForecast(HashMap(it), dailyForecast)}
        if (city != null) dataMapper.convertToDomain(city) else null

    }

    fun saveForecast(forecast: ForecastList) = forecastDbHelper.use {

        clear(CityForecastTable.NAME)
        clear(DayForecastTable.NAME)

        with(dataMapper.convertFromDomain(forecast)) {
            insert(CityForecastTable.NAME, *map.toVarargArray())
            dailyForecast.forEach { insert(DayForecastTable.NAME, *it.map.toVarargArray()) }
        }
    }
}