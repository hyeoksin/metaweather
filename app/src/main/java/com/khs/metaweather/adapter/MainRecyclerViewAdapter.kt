package com.khs.metaweather.adapter

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.khs.metaweather.R
import com.khs.metaweather.model.MetaWeather
import kotlinx.android.synthetic.main.item_weather.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainRecyclerViewAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var context: Context?=null
    private var locationList: ArrayList<MetaWeather>?= arrayListOf()    // 날씨목록
    private var recyclerView:RecyclerView?=null
    private val ICON_URL = "https://www.metaweather.com/static/img/weather/png/64/"
    private val DEGREE="℃"

    constructor(context: Context?,list:ArrayList<MetaWeather>?,recyclerView: RecyclerView):this(){
        this.context = context
        this.locationList = list
        this.recyclerView = recyclerView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("DEBUG","size: "+locationList?.size)
        return locationList?.size!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var viewHolder = (holder as CustomViewHolder)
        viewHolder.bindItem(position,viewHolder)
    }

    inner class CustomViewHolder(view: View):RecyclerView.ViewHolder(view) {

        var cityNameTv:TextView = view.city_name

        fun bindItem(position: Int, viewHolder: CustomViewHolder) {
            var itemView = viewHolder.itemView
            val glide = Glide.with(context!!)
            var weather = locationList!![position].consolidatedWeather!!
            var format = SimpleDateFormat("yyyy-MM-dd")

            cityNameTv.text = locationList!![position].title                                                          // 도시이름

            /* 오늘 */
            glide.load(Uri.parse(ICON_URL+weather[0].weather_state_abbr+".png")).into(itemView.icon_today)    // 아이콘
            itemView.today_state.text = weather[0].weather_state_name                                                 // 날씨요약
            itemView.today_max_temp.text = weather[0].max_temp.toInt().toString()+DEGREE                              // 최고온도
            itemView.today_min_temp.text = weather[0].min_temp?.toInt().toString()+DEGREE                             // 최저온도

            /* 내일 */
            glide.load(Uri.parse(ICON_URL+weather[1].weather_state_abbr+".png")).into(itemView.icon_tomorrow)  // 아이콘
            itemView.tomorrow_state.text = weather[1].weather_state_name                                              // 날씨요약
            itemView.tomorrow_max_temp.text = weather[1].max_temp.toInt().toString()+DEGREE                           // 최고온도
            itemView.tomorrow_min_temp.text = weather[1].min_temp?.toInt().toString()+DEGREE                          // 최저온도

            /* The day after tomorrow */
            glide.load(Uri.parse(ICON_URL+weather[2].weather_state_abbr+".png")).into(itemView.icon_next_day_1)         // 아이콘
            itemView.next_day_1.text = SimpleDateFormat("E dd MMM").format(format.parse(weather[2].applicable_date))     // 날짜출력
            itemView.next_day_1_state.text = weather[2].weather_state_name                                                        // 날씨요약
            itemView.next_day_1_max_temp.text = weather[2].max_temp.toInt().toString()+DEGREE                                     // 최고온도
            itemView.next_day_1_min_temp.text = weather[2].min_temp?.toInt().toString()+DEGREE                                    // 최저온도

            /* two day after tomorrow */
            glide.load(Uri.parse(ICON_URL+weather[3].weather_state_abbr+".png")).into(itemView.icon_next_day_2)         // 아이콘
            itemView.next_day_2.text = SimpleDateFormat("E dd MMM").format(format.parse(weather[3].applicable_date))     // 날짜출력
            itemView.next_day_2_state.text = weather[3].weather_state_name                                                        // 날씨요약
            itemView.next_day_2_max_temp.text = weather[3].max_temp.toInt().toString()+DEGREE                                     // 최고온도
            itemView.next_day_2_min_temp.text = weather[3].min_temp?.toInt().toString()+DEGREE                                    // 최저온도

            /* three day after tomorrow */
            glide.load(Uri.parse(ICON_URL+weather[4].weather_state_abbr+".png")).into(itemView.icon_next_day_3)         // 아이콘
            itemView.next_day_3.text = SimpleDateFormat("E dd MMM").format(format.parse(weather[4].applicable_date))     // 날짜출력
            itemView.next_day_3_state.text = weather[4].weather_state_name                                                        // 날씨요약
            itemView.next_day_3_max_temp.text = weather[4].max_temp.toInt().toString()+DEGREE                                     // 최고온도
            itemView.next_day_3_min_temp.text = weather[4].min_temp?.toInt().toString()+DEGREE                                    // 최저온도

            /* four day after tomorrow */
            glide.load(Uri.parse(ICON_URL+weather[5].weather_state_abbr+".png")).into(itemView.icon_next_day_4)         // 아이콘
            itemView.next_day_4.text = SimpleDateFormat("E dd MMM").format(format.parse(weather[5].applicable_date))     // 날짜출력
            itemView.next_day_4_state.text = weather[5].weather_state_name                                                        // 날씨요약
            itemView.next_day_4_max_temp.text = weather[5].max_temp.toInt().toString()+DEGREE                                     // 최고온도
            itemView.next_day_4_min_temp.text = weather[5].min_temp?.toInt().toString()+DEGREE                                    // 최저온도
        }
    }

}