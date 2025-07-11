package com.example.dogexamplekf.dogs.presentation.doglist

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogexamplekf.R
import com.example.dogexamplekf.databinding.ItemDogBinding
import com.example.roomlib.entity.DogModel

class DogListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dogList: MutableList<DogModel> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDogBinding.inflate(inflater, parent, false)
        return ItemDogViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dogList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        dogList[position].let { item ->
            (holder as ItemDogViewHolder).bind(item)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(dogList: List<DogModel>) {
        this.dogList.clear()
        this.dogList.addAll(dogList)
        notifyDataSetChanged()
    }

    inner class ItemDogViewHolder(
        private val binding: ItemDogBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        private val context = binding.root.context

        fun bind(item: DogModel) {
            binding.apply {
                tvDogName.text = item.dogName
                tvDogDescription.text = item.description
                tvDogAge.text = context.getString(R.string.lbl_age, item.age)
                Glide.with(context)
                    .load(item.imageUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(ivDogPhoto)
            }
        }
    }
}