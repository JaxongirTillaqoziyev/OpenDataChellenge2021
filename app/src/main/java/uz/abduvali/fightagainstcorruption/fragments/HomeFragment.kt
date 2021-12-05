package uz.abduvali.fightagainstcorruption.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.abduvali.fightagainstcorruption.R
import uz.abduvali.fightagainstcorruption.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tests.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_testsFragment)
            }
            news.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_newsFragment)
            }
            bookmark.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_bookmarksFragment)
            }
        }
    }
}