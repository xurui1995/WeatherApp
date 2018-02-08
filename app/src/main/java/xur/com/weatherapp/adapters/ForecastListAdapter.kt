package xur.com.weatherapp.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*
import xur.com.weatherapp.R
import xur.com.weatherapp.domain.model.Forecast
import xur.com.weatherapp.domain.model.ForecastList
import xur.com.weatherapp.ui.utils.ctx
import xur.com.weatherapp.ui.utils.inflate
/**
 * Created by xur on 2018/1/30.
 */
class ForecastListAdapter(private val weekForecast: ForecastList,
                          private val itemClick: (Forecast) -> Unit) :
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.item_forecast)
        return ViewHolder(view, itemClick)
    }

    override fun getItemCount(): Int = weekForecast.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    class ViewHolder(itemView: View, private val itemClick: (Forecast) -> Unit): RecyclerView.ViewHolder(itemView) {
        fun bindForecast(forecast: Forecast) {
            with(forecast) {
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text = date
                itemView.description.text = description
                itemView.maxTemperature.text = "${high}º"
                itemView.minTemperature.text = "${low}º"
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }
}