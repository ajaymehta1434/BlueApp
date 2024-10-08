package com.example.blueapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.blueapp.R
import com.example.blueapp.data.model.Book
import com.example.blueapp.databinding.BookItemViewBinding

class BooksDataAdapter(private val booksList: List<Book>) :
    RecyclerView.Adapter<BooksDataAdapter.BooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val mBinding = DataBindingUtil.inflate<BookItemViewBinding>(
            LayoutInflater.from(parent.context),
            R.layout.book_item_view,
            parent,
            false
        )
        return BooksViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        holder.setData(booksList[position])
    }

    override fun getItemCount(): Int {
        return booksList.size
    }

    inner class BooksViewHolder(private var mBinding: BookItemViewBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun setData(book: Book) {
            mBinding.tvBookTitle.text = book.name
            mBinding.tvBookAuthor.text = book.author
            mBinding.ivBookItemCover.setBackgroundResource(book.image)
        }

    }

}
