package xur.com.weatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import xur.com.weatherapp.R
import xur.com.weatherapp.adapters.ForecastListAdapter
import xur.com.weatherapp.domain.commands.RequestForecastCommand
import xur.com.weatherapp.domain.model.Forecast

class MainActivity : AppCompatActivity() {
/*    private val items = listOf(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Foggy - 21/8",
            "Wed 6/25 - Cloudy - 22/17",
            "Thurs 6/26 - Rainy - 18/11",
            "Fri 6/27 - Foggy - 21/10",
            "Sat 6/28 - TRAPPED IN WEATHERSTATION - 23/18",
            "Sun 6/29 - Sunny - 20/7"
    )*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val forecastList = find<RecyclerView>(R.id.forecast_list)
        forecastList.layoutManager = LinearLayoutManager(this)
/*        forecastList.adapter = ForecastListAdapter(items)

        val url = "http://api.openweathermap.org/data/2.5/forecast/daily?" +
                "APPID=15646a06818f61f7b8d7823ca833e1ce&q=94043&mode=json&units=metric&cnt=7"*/

        doAsync {
            val result = RequestForecastCommand("94043").execute()
            uiThread { forecastList.adapter =  ForecastListAdapter(result, object : ForecastListAdapter.OnItemClickListener{
                override fun invoke(forecast: Forecast) {
                    toast(forecast.date)
                }
            })}
        }
    }
}
