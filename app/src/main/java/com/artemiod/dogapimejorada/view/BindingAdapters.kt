package com.artemiod.dogapimejorada.view

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.artemiod.dogapimejorada.R
import com.artemiod.dogapimejorada.viewmodel.DogApiStatus
import com.squareup.picasso.Picasso

@BindingAdapter("imgUrlPhoto")
fun bindImgUrlPhoto(imageView: ImageView, utl: String?) {
    utl?.let {
        Picasso.get().load(it)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .into(imageView)
    }
}

@BindingAdapter("listImg")
fun bindRecyclerView(recyclerView: RecyclerView, list: List<String>?) {
    val adapter = recyclerView.adapter as DogAdapter
    adapter.submitList(list)
}

@BindingAdapter("dogApiStatus")
fun bindStatus(statusImageView: ImageView, status: DogApiStatus) {
    when (status) {
        DogApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        DogApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
            Toast.makeText(statusImageView.context, "Internet connection error", Toast.LENGTH_LONG).show()
        }
        DogApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        DogApiStatus.START -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.dog_api)
            Toast.makeText(statusImageView.context, "Choose a breed of dog !!", Toast.LENGTH_LONG).show()
        }
    }
}

