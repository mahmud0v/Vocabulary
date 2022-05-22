package uz.gita.vocabulary.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import uz.gita.vocabulary.R
import uz.gita.vocabulary.databinding.WordInfoScreenBinding
import uz.gita.vocabulary.db.EntityDict
import uz.gita.vocabulary.ui.viewmodel.SearchViewModel

class WordInfoScreen : Fragment() {
    private var _binding: WordInfoScreenBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = WordInfoScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var starClick = false
        val data: EntityDict? = requireArguments().getParcelable("key")
        binding.word.text = data!!.en_word
        binding.wordTranscript.text = data!!.transcript
        binding.wordType.text = data!!.type
        binding.wordTranslation.text = data!!.translation
        binding.star.setOnClickListener {

            if (starClick) {
                data.favourite = 0
                viewModel.updateWord(data, requireContext())
                binding.star.setImageResource(R.drawable.hole_star)
                starClick = false
            } else {
                data.favourite = 1
                viewModel.updateWord(data, requireContext())
                binding.star.setImageResource(R.drawable.full_star)
                starClick = true
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}