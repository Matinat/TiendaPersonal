package com.example.tiendapersonal.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tiendapersonal.R
import java.util.ArrayList

class SliderImageAdapter(private val context: Context, private val imgs: ArrayList<String>): RecyclerView.Adapter<SliderImageAdapter.SliderImageViewHolder>(){

    class SliderImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardImg = view.findViewById<ImageView>(R.id.productImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_product_img, parent, false)
        return SliderImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: SliderImageViewHolder, position: Int) {
        val img = imgs[position]
        Glide.with(context)
            .load(img)
            .into(holder.cardImg)
    }

    override fun getItemCount(): Int {
       return imgs.size
    }
}