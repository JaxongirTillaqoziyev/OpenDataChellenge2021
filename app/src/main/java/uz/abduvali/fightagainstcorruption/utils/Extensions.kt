package uz.abduvali.fightagainstcorruption.utils

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import uz.abduvali.fightagainstcorruption.database.entity.BookmarkEntity
import uz.abduvali.fightagainstcorruption.database.entity.NewEntity

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun ImageView.setImage(string: String) {
    Picasso.get().load(string).into(this)
}

fun TextView.setColor(context: Context, boolean: Boolean) {
    if (MySharedPreference(context).getPreferences("isDark") == "0") {
        if (boolean) {
            setTextColor(Color.BLACK)
        } else {
            setTextColor(Color.WHITE)
        }
    } else {
        if (boolean) {
            setTextColor(Color.WHITE)
        } else {
            setTextColor(Color.BLACK)
        }
    }
}

fun BookmarkEntity.toNewEntity(): NewEntity {
    return NewEntity(url, author, content, description, title, theme, urlToImage)
}

fun NewEntity.toBookmarkEntity(): BookmarkEntity {
    return BookmarkEntity(url, author, content, description, title, theme, urlToImage)
}