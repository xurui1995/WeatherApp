package xur.com.weatherapp.domain.commands

import xur.com.weatherapp.data.ForecastRequest
import xur.com.weatherapp.domain.mappers.ForecastDataMapper
import xur.com.weatherapp.domain.model.ForecastList

/**
 * Created by xur on 2018/2/4.
 */
class RequestForecastCommand(private val zipCode: String) : Command<ForecastList> {
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }

}