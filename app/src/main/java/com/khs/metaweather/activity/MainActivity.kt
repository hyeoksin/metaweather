package com.khs.metaweather.activity

import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.khs.metaweather.service.APIService
import com.khs.metaweather.R
import com.khs.metaweather.WeatherApplication
import com.khs.metaweather.adapter.MainRecyclerViewAdapter
import com.khs.metaweather.model.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.ref.WeakReference

class MainActivity : AppCompatActivity() {

    lateinit var service: APIService
    var cityList:HashMap<String,Int>?= hashMapOf()
    var task:MainActivityTask?=null
    var recyclerViewAdapter:MainRecyclerViewAdapter?=null

    var locationList:ArrayList<MetaWeather>?= arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        task?.execute()
    }

    private fun init() {
        service = (application as WeatherApplication).getAPIService()!!
        cityList?.put(ConstantsCity.SEOUL,ConstantsCode.RC_SEOUL)
        cityList?.put(ConstantsCity.LONDON,ConstantsCode.RC_LONDON)
        cityList?.put(ConstantsCity.CHICAGO,ConstantsCode.RC_CHICAGO)
        task = MainActivityTask()
    }

    inner class MainActivityTask: AsyncTask<Any, Void, ArrayList<MetaWeather>>() {

        private val weakReference: WeakReference<MainActivity>

        init {
            weakReference = WeakReference(this@MainActivity)
        }

        override fun doInBackground(vararg params: Any?): ArrayList<MetaWeather>? {
            return requestWeather()
        }

        private fun requestWeather(): ArrayList<MetaWeather>? {
            for(city in cityList!!){
                var woeid = city?.value.toString()
                service?.getWeatherInfoOfLocationOfDay(woeid)
                    ?.enqueue(object : Callback<MetaWeather> {
                        override fun onFailure(call: Call<MetaWeather>, t: Throwable) {
                            Log.d("DEBUG","Fail(): "+t.message)
                        }

                        override fun onResponse(call: Call<MetaWeather>, response: Response<MetaWeather>) {
                            locationList?.add(response.body()!!)
                            loadWeather(locationList)
                        }
                    })
            }
            return locationList
        }
    }

    private fun loadWeather(list: ArrayList<MetaWeather>?) {
        recyclerViewAdapter = MainRecyclerViewAdapter(this@MainActivity,list,weather_recyclerview)
        val linearLayoutManagerWrapepr = LinearLayoutManagerWrapper(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        weather_recyclerview.adapter = recyclerViewAdapter
        weather_recyclerview.layoutManager = linearLayoutManagerWrapepr
    }

    inner class LinearLayoutManagerWrapper: LinearLayoutManager {
        constructor(context: Context, orientation: Int, reverseLayout: Boolean) : super(context, orientation, reverseLayout) {}
        override fun supportsPredictiveItemAnimations(): Boolean { return false }
    }

    override fun onDestroy() {
        super.onDestroy()
        task?.cancel(true)
    }
}
