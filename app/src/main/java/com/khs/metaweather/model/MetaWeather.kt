package com.khs.metaweather.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.ArrayList

data class MetaWeather(
    @SerializedName("consolidated_weather")
    var consolidatedWeather: ArrayList<Weather>? = arrayListOf(),
    var title: String?
) : Parcelable {
    constructor(source: Parcel) : this(
        source.createTypedArrayList(Weather.CREATOR),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeTypedList(consolidatedWeather)
        writeString(title)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MetaWeather> = object : Parcelable.Creator<MetaWeather> {
            override fun createFromParcel(source: Parcel): MetaWeather = MetaWeather(source)
            override fun newArray(size: Int): Array<MetaWeather?> = arrayOfNulls(size)
        }
    }
}