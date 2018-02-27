package xur.com.weatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread
import xur.com.weatherapp.R
import xur.com.weatherapp.adapters.ForecastListAdapter
import xur.com.weatherapp.domain.commands.RequestForecastCommand
import xur.com.weatherapp.extensions.DelegatesExt

class MainActivity : AppCompatActivity(), ToolbarManager {
    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    private val zipCode: Long by  DelegatesExt.preference(this, SettingActivity.ZIP_CODE,
            SettingActivity.DEFAULT_ZIP)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()

        forecastList.layoutManager = LinearLayoutManager(this)
        attachToScroll(forecastList)
    }

    override fun onResume() {
        super.onResume()
        loadForecast()
    }

    private fun loadForecast() {
        doAsync {
            val result = RequestForecastCommand(94043).execute()
            uiThread {
                val adapter = ForecastListAdapter(result) {
                    startActivity<DetailActivity>(DetailActivity.ID to it.id,
                            DetailActivity.CITY_NAME to result.city)
                }
                forecastList.adapter = adapter
                toolbarTitle  = "${result.city} (${result.country})"
            }
        }
    }
}
