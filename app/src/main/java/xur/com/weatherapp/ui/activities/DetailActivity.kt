package xur.com.weatherapp.ui.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import xur.com.weatherapp.R
/**
 * Created by xur on 2018/2/19.
 */
class DetailActivity: AppCompatActivity() {
    companion object {
        val ID = "DetailActivity:id"
        val CITY_NAME = "DetailActivity:cityName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        title = intent.getStringExtra(CITY_NAME)


    }
}