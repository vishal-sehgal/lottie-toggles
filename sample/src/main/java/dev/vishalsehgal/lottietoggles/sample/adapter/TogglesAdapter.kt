package dev.vishalsehgal.lottietoggles.sample.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import dev.vishalsehgal.lottietoggles.sample.R
import dev.vishalsehgal.lottietoggles.sample.Toggle
import dev.vishalsehgal.lottietoggles.sample.ToggleDiffUtil
import dev.vishalsehgal.lottietoggles.sample.databinding.ItemTogglesListBinding

class TogglesAdapter : ListAdapter<Toggle, BaseViewHolder<*>>(ToggleDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val view: ItemTogglesListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_toggles_list, parent, false
        )
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ItemViewHolder -> {
                getItem(holder.adapterPosition).let { holder.bind(it) }
            }
        }
    }

    inner class ItemViewHolder(private val binding: ItemTogglesListBinding) :
        BaseViewHolder<Toggle>(binding.root) {
        override fun bind(item: Toggle) {
            binding.item = item
            binding.lottieSwitch.isChecked = getItem(adapterPosition).isSelected

            binding.lottieSwitch.setOnCheckedChangedListener { toggleableLottieView, isChecked ->
                getItem(adapterPosition).isSelected = isChecked
                updateViews()
            }
            updateViews()
            binding.executePendingBindings()
        }

        private fun updateViews() {
            binding.itemCard.setCardBackgroundColor(if (binding.lottieSwitch.isChecked) Color.BLACK else Color.WHITE)
            binding.title.setTextColor(if (binding.lottieSwitch.isChecked) Color.WHITE else Color.BLACK)
            binding.status.setTextColor(if (binding.lottieSwitch.isChecked) Color.WHITE else Color.BLACK)
            binding.status.text = getItem(adapterPosition).isSelected.toString()
        }
    }
}