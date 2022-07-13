package com.example.tiendapersonal.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import java.util.*
import android.widget.*
import com.bumptech.glide.Glide
import com.example.tiendapersonal.R
import com.example.tiendapersonal.common.domainclasses.Card


class CardAdapter(
    private val context: Context,
    private val cards: ArrayList<Card>,
    private val onFullScreen: (card: Card) -> Unit
) :
    Adapter<CardAdapter.CardViewHolder>(){

    class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cardTitle = view.findViewById<TextView>(R.id.productTitle)
        val cardImg = view.findViewById<ImageView>(R.id.productImg)
        val cardInstallmentsTag = view.findViewById<TextView>(R.id.installmentsTag)
        val cardTopTag = view.findViewById<TextView>(R.id.topTag)
        val mView = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_card, parent, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cards[position]

        holder.mView.setOnClickListener {
            onFullScreen(card)
        }

        Glide.with(context)
            .load(card.mainImage.url)
            .into(holder.cardImg)
        holder.cardTitle.text = card.name
        holder.cardInstallmentsTag.text = card.installmentsTag
        holder.cardTopTag.text = card.topTag
    }

    override fun getItemCount(): Int {
        return cards.size
    }
}