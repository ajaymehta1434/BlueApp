package com.example.blueapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.blueapp.R
import com.example.blueapp.databinding.BookCarouselViewItemBinding

class GenreCarouselAdapter(private val genreList: List<Int>) :
    RecyclerView.Adapter<GenreCarouselAdapter.PagerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val mBinding = DataBindingUtil.inflate<BookCarouselViewItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.book_carousel_view_item,
            parent,
            false
        )
        return PagerViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.setData(genreList.get(position))
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    inner class PagerViewHolder(private val mBinding: BookCarouselViewItemBinding) :
        RecyclerView.ViewHolder(mBinding.root) {

        fun setData(sliderImage: Int) {
            mBinding.ivBookCover.setBackgroundResource(sliderImage)
        }
    }
}