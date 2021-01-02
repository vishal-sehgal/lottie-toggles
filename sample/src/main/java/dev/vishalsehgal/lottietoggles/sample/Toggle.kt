package dev.vishalsehgal.lottietoggles.sample

import androidx.recyclerview.widget.DiffUtil

data class Toggle(
    var id: Int,
    var title: String,
    var author: String,
    var authorLottieFilesProfile: String,
    var toggleFile: Int,
    var modifiedBy: String? = null,
    var isSelected: Boolean = false
)

class ToggleDiffUtil : DiffUtil.ItemCallback<Toggle>() {
    override fun areItemsTheSame(oldItem: Toggle, newItem: Toggle) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Toggle, newItem: Toggle) = oldItem == newItem
}