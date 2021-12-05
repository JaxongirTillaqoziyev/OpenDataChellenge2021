package uz.abduvali.fightagainstcorruption.fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import uz.abduvali.fightagainstcorruption.R
import uz.abduvali.fightagainstcorruption.adapters.CategoryAdapter
import uz.abduvali.fightagainstcorruption.adapters.NewDataAdapter
import uz.abduvali.fightagainstcorruption.database.AppDatabase
import uz.abduvali.fightagainstcorruption.database.entity.BookmarkEntity
import uz.abduvali.fightagainstcorruption.database.entity.NewEntity
import uz.abduvali.fightagainstcorruption.databinding.FragmentNewsBinding
import uz.abduvali.fightagainstcorruption.networking.ApiClient
import uz.abduvali.fightagainstcorruption.repository.NewDataRepository
import uz.abduvali.fightagainstcorruption.utils.NetworkHelper
import uz.abduvali.fightagainstcorruption.utils.toBookmarkEntity
import uz.abduvali.fightagainstcorruption.viewmodels.NewDataResource
import uz.abduvali.fightagainstcorruption.viewmodels.NewViewModel
import kotlin.coroutines.CoroutineContext

class NewsFragment : Fragment(R.layout.fragment_news), CoroutineScope {

    private lateinit var newViewModel: NewViewModel
    private val binding by viewBinding(FragmentNewsBinding::bind)
    private lateinit var appDatabase: AppDatabase
    private lateinit var bookmarks: ArrayList<BookmarkEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appDatabase = AppDatabase.getInstance(requireContext())
        bookmarks = appDatabase.bookmarkDao().getBookmarks().toMutableList() as ArrayList
        newViewModel = NewViewModel(
            NewDataRepository(
                ApiClient.apiService,
                appDatabase.newDao()
            ),
            NetworkHelper(requireContext())
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val categoryAdapter = CategoryAdapter(bookmarks,
                object : CategoryAdapter.OnItemClickListener {
                    override fun onItemClick(newEntity: NewEntity) {
                        onItemClicked(newEntity)
                    }

                    override fun onBookmarkClick(newEntity: NewEntity, imageView: ImageView) {
                        onBookmarkClicked(newEntity, imageView)
                    }
                })
//            val newDataAdapter = NewDataAdapter(object : NewDataAdapter.OnItemClickListener {
//                override fun onItemClick(newEntity: NewEntity) {
//                    onItemClicked(newEntity)
//                }
//
//                override fun onBookmarkClick(newEntity: NewEntity, imageView: ImageView) {
//                    onBookmarkClicked(newEntity, imageView)
//                }
//            })
            launch {
                newViewModel.getNewsData("anti corruption").collect {
                    when (it) {
                        is NewDataResource.Success -> {
                            categoryAdapter.submitList(it.list)
                            rv1.adapter = categoryAdapter
                        }
                        is NewDataResource.Error -> {
                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                        }
                        else -> {}
                    }
                }
//                newViewModel.getNewsData("law").collect {
//                    when (it) {
//                        is NewDataResource.Success -> {
//                            newDataAdapter.submitList(it.list)
//                            rv.adapter = newDataAdapter
//                        }
//                        is NewDataResource.Error -> {
//                            Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
//                        }
//                        else -> {}
//                    }
//                }
            }
        }
    }

    private fun onBookmarkClicked(newEntity: NewEntity, imageView: ImageView) {
        val bookmark = newEntity.toBookmarkEntity()
        if (bookmarks.any { it.url == newEntity.url }) {
            appDatabase.bookmarkDao().deleteBookmark(bookmark)
            bookmarks.remove(bookmark)
            imageView.setImageResource(R.drawable.ic_bookmark_black)
        } else {
            appDatabase.bookmarkDao().addBookmark(bookmark)
            bookmarks.add(bookmark)
            imageView.setImageResource(R.drawable.ic_bookmarked_black)
        }
    }

    private fun onItemClicked(newEntity: NewEntity) {
        findNavController().navigate(
            R.id.action_newsFragment_to_newDataFragment,
            Bundle().apply {
                putSerializable("new", newEntity)
            }
        )
    }

    override val coroutineContext: CoroutineContext
        get() = Job() + Dispatchers.Main
}