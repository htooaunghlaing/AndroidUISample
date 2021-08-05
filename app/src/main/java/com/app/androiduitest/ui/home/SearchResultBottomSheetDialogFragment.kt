package com.app.androiduitest.ui.home

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import com.app.androiduitest.CarDetailsActivity
import com.app.androiduitest.R
import com.app.androiduitest.databinding.SearchResultBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchResultBottomSheetDialogFragment : BottomSheetDialogFragment(),CarItemClickListener {

    private lateinit var _binding: SearchResultBottomSheetBinding
    private lateinit var carAdapter: CarAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchResultBottomSheetBinding.inflate(inflater)

//        bottomSheetBehavior = BottomSheetBehavior.from(_binding.rootContainer)

        return _binding.root
    }

    override fun onStart() {
        super.onStart()
        val metrics = DisplayMetrics()
        requireActivity().windowManager?.defaultDisplay?.getMetrics(metrics)
        _binding.rootContainer.layoutParams.height = metrics.heightPixels
        _binding.rootContainer.requestLayout()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val items = mutableListOf<Item>()
        items.add(Item("Mazada 3",R.drawable.mazada3, "SMS1000Z", 5, 3.0f, 0.40f))
        items.add(Item("Honda Shuttle Hybrid",R.drawable.honda_shuttle_hybrid,"SMN7000B", 7, 3.0f, 0.40f))
        items.add(Item("Ssanyang Tivolo",R.drawable.ssanyang_tivoli,"SMN1234Z", 5, 3.0f, 0.40f))
        items.add(Item("Toyota Sienta Hybrid",R.drawable.toyota_sienta_hybrid,"SMN4412Z", 7, 3.0f, 0.40f))

        _binding.apply {
            carAdapter = CarAdapter(items, this@SearchResultBottomSheetDialogFragment)
            carRecycler.adapter = carAdapter

            val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            itemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_layer, null))
            carRecycler.addItemDecoration(itemDecoration)
        }
    }

    override fun onCarClickListener(item: Item) {
        Toast.makeText(requireContext(), item.name, Toast.LENGTH_LONG).show()

        startActivity(Intent(context, CarDetailsActivity::class.java))
    }

}

interface CarItemClickListener {
    fun onCarClickListener(item: Item)
}