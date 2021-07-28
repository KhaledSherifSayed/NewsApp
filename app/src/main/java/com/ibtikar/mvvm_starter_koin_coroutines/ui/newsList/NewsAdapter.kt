package com.ibtikar.mvvm_starter_koin_coroutines.ui.newsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ibtikar.mvvm_starter_koin_coroutines.data.models.NewsModelResponse
import com.ibtikar.mvvm_starter_koin_coroutines.databinding.ItemArticleBinding

/**
 * Created by Meslmawy on 6/10/2021
 */

class ArticlesAdapter(val callback: NewsItemClick) : ListAdapter<NewsModelResponse, ArticlesAdapter.ArticleViewHolder>(DiffCallback) {

    /**
     * Callback for calculating the diff between two non-null items in a list.
     *
     * Used by ListAdapter to calculate the minumum number of changes between and old list and a new
     * list that's been passed to `submitList`.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<NewsModelResponse>() {
        override fun areItemsTheSame(oldItem: NewsModelResponse, newItem: NewsModelResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NewsModelResponse, newItem: NewsModelResponse): Boolean {
            return newItem.author + newItem.title == oldItem.author + oldItem.title
        }
    }

    /**
     * ViewHolder for Groups items. All work is done by data binding.
     */
    class ArticleViewHolder(val viewDataBinding: ItemArticleBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {

        fun bind(listener: NewsItemClick, news: NewsModelResponse) {
            viewDataBinding.itemClick = listener
            viewDataBinding.item = news
            viewDataBinding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ArticleViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemArticleBinding.inflate(layoutInflater, parent, false)
                return ArticleViewHolder(binding)
            }
        }
    }

    /**
     * Part of the RecyclerView adapter, called when RecyclerView needs a new [ViewHolder].
     *
     * A ViewHolder holds a view for the [RecyclerView] as well as providing additional information
     * to the RecyclerView such as where on the screen it was last drawn during scrolling.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder.from(parent)
    }

    /**
     * Part of the RecyclerView adapter, called when RecyclerView needs to show an item.
     *
     * The ViewHolder passed may be recycled, so make sure that this sets any properties that
     * may have been set previously.
     */

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.viewDataBinding.also {
            holder.bind(callback,getItem(position))
        }
    }
}


/**
 * Click listener for Groups. By giving the block a name it helps a reader understand what it does.
 */
class NewsItemClick(val block: (NewsModelResponse,Int) -> Unit) {
    /**
     * Called when a video is clicked
     * @param video the video that was clicked
     */
    fun onClick(item: NewsModelResponse,version : Int) = block(item,version)
    fun onFavoriteClick(item: NewsModelResponse,version: Int) = block(item,version)

}