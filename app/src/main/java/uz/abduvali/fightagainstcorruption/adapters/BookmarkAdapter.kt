package uz.abduvali.fightagainstcorruption.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.abduvali.fightagainstcorruption.database.entity.BookmarkEntity
import uz.abduvali.fightagainstcorruption.databinding.ItemBookmarkBinding
import uz.abduvali.fightagainstcorruption.utils.setImage

class BookmarkAdapter(
    private val onItemClickListener: OnItemClickListener
) : ListAdapter<BookmarkEntity, BookmarkAdapter.Vh>(MyDiffUtil()) {

    class MyDiffUtil : DiffUtil.ItemCallback<BookmarkEntity>() {
        override fun areItemsTheSame(oldItem: BookmarkEntity, newItem: BookmarkEntity): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: BookmarkEntity, newItem: BookmarkEntity): Boolean {
            return oldItem == newItem
        }
    }

    class Vh(val binding: ItemBookmarkBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onItemClick(bookmarkEntity: BookmarkEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.binding.apply {
            val item = getItem(position)
            image.setImage(
                item.urlToImage
                    ?: "https://upload.wikipedia/commons/8/89/HD_transparent_picture.png"
            )
            title.text = item.title
            theme.text = item.theme.uppercase()
            root.setOnClickListener {
                onItemClickListener.onItemClick(item)
            }
        }
    }
}