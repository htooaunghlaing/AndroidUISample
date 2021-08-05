package com.app.androiduitest.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import com.app.androiduitest.R
import com.app.androiduitest.databinding.FragmentHomeBinding
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.Marker

import com.app.androiduitest.util.CustomInfoWindowGoogleMap

import com.app.androiduitest.util.InfoWindowData










class HomeFragment : Fragment() {

    private lateinit var binding:  FragmentHomeBinding

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        val sydney = LatLng(21.906, 96.123)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_current_loc)))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(21.906, 96.123))
            .zoom(16f)
            .build()
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

//        googleMap.addMarker(
//            MarkerOptions().position(LatLng(21.907, 96.124)).icon(
//                BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView()
//                )
//            )
//        ).setTitle("6 Available")

       // googleMap.setInfoWindowAdapter(MarkerInfoWindowAdapter(requireContext()))

        val found_one = LatLng(21.907, 96.124)

        val markerOptions = MarkerOptions()
        markerOptions.position(found_one)
            .snippet("2")


        val info = InfoWindowData()
        info.image = "ic_car_white"
        info.hotel = "Hotel : excellent hotels available"
        info.food = "Food : all types of restaurants available"
        info.transport = "Reach the site by bus, car and train."

        val customInfoWindow = CustomInfoWindowGoogleMap(requireContext())
        googleMap.setInfoWindowAdapter(customInfoWindow)

        val m: Marker = googleMap.addMarker(markerOptions)
        m.tag = info
        m.showInfoWindow()

        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(found_one))

        val found_two = LatLng(21.909, 96.121)

        val markerOptionsTwo = MarkerOptions()
        markerOptionsTwo.position(found_two)
            .snippet("3")


        val infoTwo = InfoWindowData()
        infoTwo.image = "ic_car_white"
        infoTwo.hotel = "M2 Hotel"
        infoTwo.food = "Chinese Food"
        infoTwo.transport = "Reach the site by bus, car and train."

        val customInfoWindowTwo = CustomInfoWindowGoogleMap(requireContext())
        googleMap.setInfoWindowAdapter(customInfoWindowTwo)

        val m2: Marker = googleMap.addMarker(markerOptionsTwo)
        m2.tag = infoTwo
        m2.showInfoWindow()

        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(found_two))

//        val found_three = LatLng(21.907, 96.120)
//
//        val markerOptionsThree = MarkerOptions()
//        markerOptionsTwo.position(found_three)
//            .snippet("6")
//
//
//        val infoThree = InfoWindowData()
//        infoThree.image = "ic_car_white"
//        infoThree.hotel = "Hotel : excellent hotels available"
//        infoThree.food = "Food : all types of restaurants available"
//        infoThree.transport = "Reach the site by bus, car and train."
//
//        val customInfoWindowThree = CustomInfoWindowGoogleMap(requireContext())
//        googleMap.setInfoWindowAdapter(customInfoWindowThree)
//
//        val m3: Marker = googleMap.addMarker(markerOptionsThree)
//        m3.tag = infoThree
//        m3.showInfoWindow()
//
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(found_three))
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        binding.apply {
            btnGo.setOnClickListener {
                val modalbottomSheetFragment = SearchResultBottomSheetDialogFragment()
                modalbottomSheetFragment.show(childFragmentManager,modalbottomSheetFragment.tag)
            }
        }
    }


    private fun getMarkerBitmapFromView(): Bitmap? {
        val customMarkerView: View? = layoutInflater.inflate(R.layout.custom_marker, null)

        customMarkerView?.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED )
        customMarkerView?.layout(0, 0, customMarkerView.measuredWidth, customMarkerView.measuredHeight)
        //customMarkerView?.buildDrawingCache()
        val returnedBitmap = Bitmap.createBitmap(
            customMarkerView!!.measuredWidth, customMarkerView.measuredHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(returnedBitmap)
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN)
        val drawable = customMarkerView.background

        drawable?.draw(canvas)
        customMarkerView.draw(canvas)
        return returnedBitmap

    }
}