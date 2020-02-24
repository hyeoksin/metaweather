package com.khs.metaweather.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

data class Weather(
    var air_pressure: Double,                // 기압
    var applicable_date: String?,            // 적용날짜
    var created: Date,                       // 날씨 정보 생성 일자
    var humidity: Double,                    // 습도(%)
    var id: Long,                            // 인덱스
    var max_temp: Double,                    // 최고 기온
    var min_temp: Double?,                   // 최저 기온
    var predictability: Int,                 // 확률(%)
    var the_temp: Double,                     // 평균 기온
    var visibility: Double,                   // (miles)
    var weather_state_abbr: String?,          // 날씨 약어
    var weather_state_name: String?,          // 날씨 이름
    var wind_direction: Double,               // 풍향
    var wind_direction_compass: String?,      // 바람 방향의 나침반
    var wind_speed: Double                    // 풍속
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readDouble(),
        source.readString(),
        source.readSerializable() as Date,
        source.readDouble(),
        source.readLong(),
        source.readDouble(),
        source.readValue(Double::class.java.classLoader) as Double?,
        source.readInt(),
        source.readDouble(),
        source.readDouble(),
        source.readString(),
        source.readString(),
        source.readDouble(),
        source.readString(),
        source.readDouble()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeDouble(air_pressure)
        writeString(applicable_date)
        writeSerializable(created)
        writeDouble(humidity)
        writeLong(id)
        writeDouble(max_temp)
        writeValue(min_temp)
        writeInt(predictability)
        writeDouble(the_temp)
        writeDouble(visibility)
        writeString(weather_state_abbr)
        writeString(weather_state_name)
        writeDouble(wind_direction)
        writeString(wind_direction_compass)
        writeDouble(wind_speed)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<Weather> = object : Parcelable.Creator<Weather> {
            override fun createFromParcel(source: Parcel): Weather = Weather(source)
            override fun newArray(size: Int): Array<Weather?> = arrayOfNulls(size)
        }
    }
}