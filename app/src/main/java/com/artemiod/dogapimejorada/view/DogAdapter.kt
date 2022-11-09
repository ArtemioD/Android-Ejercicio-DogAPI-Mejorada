package com.artemiod.dogapimejorada.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.artemiod.dogapimejorada.R
import com.artemiod.dogapimejorada.databinding.ItemListBinding

class DogAdapter: ListAdapter<String, DogAdapter.ViewHolder>(DiffCallBack) {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val binding = ItemListBinding.bind(view)

        fun bind(photo: String) {
            binding.img = photo
            binding.executePendingBindings() // hará que la actualización se ejecute de inmediato
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(photo)
    }

    companion object DiffCallBack: DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

    }
}