package xur.com.weatherapp.ui.activities

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import xur.com.weatherapp.R
import xur.com.weatherapp.extensions.ctx
import xur.com.weatherapp.extensions.slideEnter
import xur.com.weatherapp.extensions.slideExit
import xur.com.weatherapp.ui.App

/**
 * Created by xur on 2018/2/24.
 */
interface ToolbarManager {
    val toolbar: Toolbar

    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }

    fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.action_settings -> toolbar.ctx.startActivity<SettingActivity>()
                else -> App.instance.toast("Unknown option")
            }
            true
        }
    }
    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

    fun createUpDrawable() = DrawerArrowDrawable(toolbar.ctx).apply { progress = 1f }

    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }

}