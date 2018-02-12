package xur.com.weatherapp.domain.datasource

import xur.com.weatherapp.domain.model.ForecastList

/**
 * Created by xur on 2018/2/12.
 */
interface ForecastDataSource {
    fun requestForecastByZipCode(zipCode: Long, date: Long): ForecastList?
}