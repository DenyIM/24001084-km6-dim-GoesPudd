package com.example.goespudd.presentation.home.adapter.changelayoutmenu

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.goespudd.R
import com.example.goespudd.core.ViewHolderBinder
import com.example.goespudd.data.model.Menu
import com.example.goespudd.databinding.ItemMenuListBinding
import com.example.goespudd.utils.toIndonesianFormat

class MenuListItemViewHolder(
    private val binding: ItemMenuListBinding,
    private val listener: OnItemClickedListener<Menu>,
) : RecyclerView.ViewHolder(binding.root), ViewHolderBinder<Menu> {
    override fun bind(item: Menu) {
        item.let {
            binding.ivMenuImage.load(it.imgUrl) {
                crossfade(true)
                error(R.mipmap.ic_launcher)
            }
            binding.tvMenuName.text = it.name
            binding.tvMenuPrice.text = it.price.toIndonesianFormat()
            itemView.setOnClickListener {
                listener.onItemClicked(item)
            }
        }
    }
}
