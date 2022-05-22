package uz.gita.vocabulary.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import uz.gita.vocabulary.R
import uz.gita.vocabulary.adapter.ClickItemListener
import uz.gita.vocabulary.adapter.WordAdapter
import uz.gita.vocabulary.databinding.SearchScreenBinding
import uz.gita.vocabulary.db.EntityDict
import uz.gita.vocabulary.ui.viewmodel.SearchViewModel

class SearchScreen : Fragment(), ClickItemListener {
    private var _binding: SearchScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var wordLiveData: LiveData<List<EntityDict>>
    private lateinit var searchWordLiveData: LiveData<List<EntityDict>>
    private lateinit var adapter: WordAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = SearchScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = WordAdapter(this)
        wordLiveData = viewModel.getAllWords(requireContext())
        wordLiveData.observe(viewLifecycleOwner, wordObserver)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        searchWord(view)
    }


    private fun searchWord(view:View) {

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = true

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchWord = "%$newText%"
                searchWordLiveData = viewModel.getSearchWord(searchWord)
                searchWordLiveData.observe(viewLifecycleOwner, searchWordObserver)
                return true
            }


        })
    }

    private val wordObserver = Observer<List<EntityDict>> {
        adapter.differ.submitList(it)
    }


    private val searchWordObserver = Observer<List<EntityDict>> {

        adapter.differ.submitList(it)
    }


    override fun onItemClickListener(data: EntityDict) {
        data.countable = "1"
        viewModel.updateWord(data,requireContext())
        val bundle = Bundle().apply {
            putParcelable("key", data)
        }
        findNavController().navigate(R.id.action_searchFragment_to_wordInfoScreen, bundle)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding == null
    }


}