package com.app.androiduitest

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.viewbinding.ViewBinding
import com.app.androiduitest.databinding.ActivityCarDetailsBinding
import com.app.androiduitest.databinding.ItemCustomFixedSizeLayout3Binding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.imaginativeworld.whynotimagecarousel.utils.setImage
import org.imaginativeworld.whynotimagecarousel.listener.CarouselListener
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class CarDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCarDetailsBinding

    private val images = arrayListOf(
        R.drawable.mazada3,
        R.drawable.toyota_sienta_hybrid, R.drawable.ssanyang_tivoli
    )

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)



// Register lifecycle. For activity this will be lifecycle/getLifecycle() and for fragment it will be viewLifecycleOwner/getViewLifecycleOwner().
        binding.carouselView.registerLifecycle(lifecycle)

        // Custom view
        binding.carouselView.carouselListener = object : CarouselListener {
            override fun onCreateViewHolder(
                layoutInflater: LayoutInflater,
                parent: ViewGroup
            ): ViewBinding? {
                return ItemCustomFixedSizeLayout3Binding.inflate(
                    layoutInflater,
                    parent,
                    false
                )
            }

            override fun onBindViewHolder(
                binding: ViewBinding,
                item: CarouselItem,
                position: Int
            ) {
                val currentBinding = binding as ItemCustomFixedSizeLayout3Binding

                currentBinding.imageView.apply {
                    scaleType = ImageView.ScaleType.CENTER_CROP

                    setImage(item, R.drawable.mazada3)
                }
            }
        }

        val list = mutableListOf<CarouselItem>()
        list.add(
            CarouselItem(
                imageDrawable = R.drawable.mazada3,
                caption = "Mazada3"
            )
        )

        list.add(
            CarouselItem(
                imageDrawable = R.drawable.toyota_sienta_hybrid,
                caption = "Toyota Sienta Hybrid"
            )
        )

        list.add(
            CarouselItem(
                imageDrawable = R.drawable.ssanyang_tivoli,
                caption = "Ssanyang Tivioli"
            )
        )

        binding.carouselView.setData(list)

        // Custom indicator
        binding.carouselView.setIndicator(binding.customIndicator)

        binding.imageButton3.setOnClickListener{
            finish()
        }

        //load map
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

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
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        val cameraPosition = CameraPosition.Builder()
            .target(LatLng(21.906, 96.123))
            .zoom(16f)
            .build()
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        googleMap.addMarker(
            MarkerOptions().position(LatLng(21.906, 96.123)).icon(
                BitmapDescriptorFactory.fromBitmap(
                    createCustomMarker(this@CarDetailsActivity,  "6")
                )
            )
        )

    }

    fun createCustomMarker(context: Context, _name: String?): Bitmap? {
        val marker: View =
            (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater).inflate(
                R.layout.car_marker,
                null
            )
//        val markerImage: ImageView =
//            marker.findViewById<View>(com.app.androiduitest.R.id.user_dp) as CircleImageView
//        markerImage.setImageResource(resource)
        val displayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        marker.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels)
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels)
        marker.buildDrawingCache()
        val bitmap = Bitmap.createBitmap(
            marker.measuredWidth,
            marker.measuredHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        marker.draw(canvas)
        return bitmap
    }
}