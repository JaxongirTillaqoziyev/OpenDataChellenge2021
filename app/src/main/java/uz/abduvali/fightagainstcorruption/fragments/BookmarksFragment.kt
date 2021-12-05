package uz.abduvali.fightagainstcorruption.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.abduvali.fightagainstcorruption.R
import uz.abduvali.fightagainstcorruption.adapters.BookmarkAdapter
import uz.abduvali.fightagainstcorruption.database.AppDatabase
import uz.abduvali.fightagainstcorruption.database.dao.BookmarkDao
import uz.abduvali.fightagainstcorruption.database.entity.BookmarkEntity
import uz.abduvali.fightagainstcorruption.databinding.FragmentBookmarksBinding
import uz.abduvali.fightagainstcorruption.utils.hide
import uz.abduvali.fightagainstcorruption.utils.show
import uz.abduvali.fightagainstcorruption.utils.toNewEntity

class BookmarksFragment : Fragment(R.layout.fragment_bookmarks) {

    lateinit var bookmarkDao: BookmarkDao

    private val binding by viewBinding(FragmentBookmarksBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bookmarkDao = AppDatabase.getInstance(requireContext()).bookmarkDao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val bookmarkAdapter = BookmarkAdapter(object : BookmarkAdapter.OnItemClickListener {
                override fun onItemClick(bookmarkEntity: BookmarkEntity) {
                    findNavController().navigate(
                        R.id.action_bookmarksFragment_to_newDataFragment,
                        Bundle().apply {
                            putSerializable("new", bookmarkEntity.toNewEntity())
                        }
                    )
                }
            })
            val list = bookmarkDao.getBookmarks()
            if (list.isNotEmpty()) {
                rv.show()
                noItem.hide()
                bookmarkAdapter.submitList(list)
                rv.adapter = bookmarkAdapter
            } else {
                rv.hide()
                noItem.show()
            }
        }
    }
}