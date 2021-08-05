package com.app.androiduitest.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.androiduitest.R
import com.app.androiduitest.databinding.CarItemBinding

class CarAdapter constructor(val items: List<Item>, private val carClickListener: CarItemClickListener) : RecyclerView.Adapter<CarViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)
        return CarViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(items[position], carClickListener)
    }

}

class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val binding = CarItemBinding.bind(itemView)

    fun bind(item: Item, carClickListener: CarItemClickListener) = with(itemView) {
        binding.txtName.text = item.name
        binding.imgCar.background = ContextCompat.getDrawable(itemView.context, item.image)
        binding.txtModel.text = item.model
        binding.txtSeater.text = "${item.seat} Seater"
        binding.txtRentalFee.text = "Fr $${item.rentFee}/hr"
        binding.txtMileageFee.text = "$${item.mileageFee}/km"


        itemView.setOnClickListener {
            carClickListener.onCarClickListener(item)
        }
    }
}

