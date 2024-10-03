package com.dicoding.quickkick

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.quickkick.databinding.ItemCardviewShoesBinding

class ListShoesAdapter(private val listShoes: ArrayList<Shoes>) : RecyclerView.Adapter<ListShoesAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallBack: OnItemClickCallBack

    fun setOnItemClickCallBack(onItemClickCallBack: OnItemClickCallBack) {
        this.onItemClickCallBack = onItemClickCallBack
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ListViewHolder {
        val binding = ItemCardviewShoesBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, type, price, color, description, photo) = listShoes[position]
        holder.binding.tvItemName.text = name
        holder.binding.tvItemType.text = type
        holder.binding.tvItemPrice.text = price
        holder.binding.imgItemPhoto.setImageResource(photo)
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("shoes", listShoes[holder.adapterPosition])
            }
            context.startActivity(intent)
            onItemClickCallBack.onItemClicked(listShoes[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listShoes.size

    class ListViewHolder (var binding: ItemCardviewShoesBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickCallBack {
        fun onItemClicked(data: Shoes)
    }
}