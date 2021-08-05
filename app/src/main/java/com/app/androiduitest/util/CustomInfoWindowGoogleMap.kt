package com.app.androiduitest.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.app.androiduitest.R
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter
import com.google.android.gms.maps.model.Marker
import java.util.*


class CustomInfoWindowGoogleMap(private val context: Context) : InfoWindowAdapter {
    override fun getInfoWindow(marker: Marker): View? {
        return null
    }

    override fun getInfoContents(marker: Marker): View {
        val view: View = (context as Activity).layoutInflater
            .inflate(R.layout.custom_marker, null)
//        val name_tv = view.findViewById<TextView>(R.id.name)
        val details_tv = view.findViewById<TextView>(R.id.txtCarNumber)
        val img = view.findViewById<ImageView>(R.id.imageView4)
//        val hotel_tv = view.findViewById<TextView>(R.id.hotels)
//        val food_tv = view.findViewById<TextView>(R.id.food)
//        val transport_tv = view.findViewById<TextView>(R.id.transport)
       // name_tv.text = marker.title
        details_tv.text = marker.snippet
        val infoWindowData: InfoWindowData? = marker.tag as InfoWindowData?
        val imageId = context.getResources().getIdentifier(
            infoWindowData?.image?.toLowerCase(Locale.ROOT),
            "drawable", context.getPackageName()
        )
        img.setImageResource(imageId)
//        hotel_tv.text = infoWindowData?.hotel
//        food_tv.text = infoWindowData?.food
//        transport_tv.text = infoWindowData?.transport
        return view
    }
}