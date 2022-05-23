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
import uz.gita.vocabulary.databinding.BookmarkScreenBinding
import uz.gita.vocabulary.db.EntityDict
import uz.gita.vocabulary.ui.dialog.WordInfoDialog
import uz.gita.vocabulary.ui.viewmodel.BookmarkViewModel


class BookmarkScreen : Fragment(), ClickItemListener {
    private var _binding: BookmarkScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var bookmarkLiveData: LiveData<List<EntityDict>>
    private lateinit var adapter: WordAdapter
    private val viewModel: BookmarkViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = BookmarkScreenBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = WordAdapter(this)
        val str = "%1%"
        bookmarkLiveData = viewModel.bookmarkWords(requireContext(), str)
        bookmarkLiveData.observe(viewLifecycleOwner, observer)
        binding.bookmarkRvView.adapter = adapter
        binding.bookmarkRvView.layoutManager = LinearLayoutManager(requireContext())
    }

    private val observer = Observer<List<EntityDict>> {
        adapter.differ.submitList(it)
    }

    override fun onItemClickListener(data: EntityDict) {

        val bundle = Bundle().apply {
            putParcelable("key", data)
        }
        val dialog = WordInfoDialog()
        dialog.arguments = bundle
        dialog.isCancelable = false
        dialog.show(requireActivity().supportFragmentManager, "Dialog")
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}
