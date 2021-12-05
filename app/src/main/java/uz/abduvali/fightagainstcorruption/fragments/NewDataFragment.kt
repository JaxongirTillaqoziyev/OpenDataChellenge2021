package uz.abduvali.fightagainstcorruption.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.zhuinden.fragmentviewbindingdelegatekt.viewBinding
import uz.abduvali.fightagainstcorruption.R
import uz.abduvali.fightagainstcorruption.database.entity.NewEntity
import uz.abduvali.fightagainstcorruption.databinding.FragmentNewDataBinding

class NewDataFragment : Fragment(R.layout.fragment_new_data) {

    private val binding by viewBinding(FragmentNewDataBinding::bind)
    private lateinit var newEntity: NewEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            newEntity = it?.getSerializable("new") as NewEntity
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            web.loadUrl(newEntity.url)
        }
    }
}