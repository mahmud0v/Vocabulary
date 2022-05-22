package uz.gita.vocabulary.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.gita.vocabulary.R
import uz.gita.vocabulary.adapter.ClickItemListener
import uz.gita.vocabulary.adapter.WordAdapter
import uz.gita.vocabulary.databinding.HistoryScreenBinding
import uz.gita.vocabulary.db.EntityDict
import uz.gita.vocabulary.ui.viewmodel.HistoryViewModel

class HistoryScreen : Fragment(), ClickItemListener {
    private var _binding: HistoryScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: WordAdapter
    private val viewModel:HistoryViewModel by viewModels()
    private lateinit var liveData:LiveData<List<EntityDict>>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = HistoryScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = WordAdapter(this)
        val str = "%1%"
        liveData = viewModel.getHistoryWords(requireContext(),str)
        liveData.observe(viewLifecycleOwner,observer)
        binding.historyRvView.adapter = adapter
        binding.historyRvView.layoutManager = LinearLayoutManager(requireContext())
    }


    private val observer = Observer<List<EntityDict>>{
        adapter.differ.submitList(it)
    }

    override fun onItemClickListener(data: EntityDict) {
        val bundle = Bundle().apply {
            putParcelable("key", data)
        }
        findNavController().navigate(R.id.action_historyFragment_to_wordInfoScreen, bundle)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}