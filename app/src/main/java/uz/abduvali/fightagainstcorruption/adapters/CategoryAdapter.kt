package uz.abduvali.fightagainstcorruption.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.abduvali.fightagainstcorruption.R
import uz.abduvali.fightagainstcorruption.database.entity.BookmarkEntity
import uz.abduvali.fightagainstcorruption.database.entity.NewEntity
import uz.abduvali.fightagainstcorruption.databinding.ItemCategoryBinding
import uz.abduvali.fightagainstcorruption.utils.setImage

class CategoryAdapter(
    private val list: ArrayList<BookmarkEntity>,
    private val onItemClickListener: OnItemClickListener
) : ListAdapter<NewEntity, CategoryAdapter.Vh>(MyDiffUtil()) {

    class MyDiffUtil : DiffUtil.ItemCallback<NewEntity>() {
        override fun areItemsTheSame(oldItem: NewEntity, newItem: NewEntity): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: NewEntity, newItem: NewEntity): Boolean {
            return oldItem == newItem
        }
    }

    class Vh(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onItemClick(newEntity: NewEntity)
        fun onBookmarkClick(newEntity: NewEntity, imageView: ImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.binding.apply {
            val item = getItem(position)
            if (list.any { it.url == item.url }) {
                bookmark.setImageResource(R.drawable.ic_bookmarked)
            } else {
                bookmark.setImageResource(R.drawable.ic_bookmark)
            }
            image.setImage(
                item.urlToImage
                    ?: "https://upload.wikipedia/commons/8/89/HD_transparent_picture.png"
            )
            title.text = item.title
            theme.text = item.theme.uppercase()
            root.setOnClickListener {
                onItemClickListener.onItemClick(item)
            }
            bookmark.setOnClickListener {
                onItemClickListener.onBookmarkClick(item, bookmark)
            }
        }
    }
}