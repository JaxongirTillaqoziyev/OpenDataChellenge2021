package uz.abduvali.fightagainstcorruption.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.abduvali.fightagainstcorruption.database.entity.NewEntity
import uz.abduvali.fightagainstcorruption.databinding.ItemNewDataBinding
import uz.abduvali.fightagainstcorruption.utils.setImage

class NewDataAdapter(
    private val onItemClickListener: OnItemClickListener
) : ListAdapter<NewEntity, NewDataAdapter.Vh>(MyDiffUtil()) {

    class MyDiffUtil : DiffUtil.ItemCallback<NewEntity>() {
        override fun areItemsTheSame(oldItem: NewEntity, newItem: NewEntity): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: NewEntity, newItem: NewEntity): Boolean {
            return oldItem == newItem
        }
    }

    class Vh(val binding: ItemNewDataBinding) : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickListener {
        fun onItemClick(newEntity: NewEntity)
        fun onBookmarkClick(newEntity: NewEntity, imageView: ImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemNewDataBinding.inflate(LayoutInflater.from(parent.context), parent, false))
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
            bookmark.setOnClickListener {
                onItemClickListener.onBookmarkClick(item, bookmark)
            }
        }
    }
}