package xur.com.weatherapp.ui

import android.app.Application
import xur.com.weatherapp.ui.utils.DelegatesExt

/**
 * Created by xur on 2018/2/8.
 */
class App : Application() {
    companion object {
        var instance: App by DelegatesExt.notNullSingleValue()
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}