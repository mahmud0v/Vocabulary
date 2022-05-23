package uz.gita.vocabulary.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import uz.gita.vocabulary.R
import uz.gita.vocabulary.databinding.WordInfoDialogBinding
import uz.gita.vocabulary.db.EntityDict
import uz.gita.vocabulary.ui.viewmodel.BookmarkViewModel
import uz.gita.vocabulary.ui.viewmodel.SearchViewModel


class WordInfoDialog : DialogFragment() {
    private var _binding: WordInfoDialogBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SearchViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = WordInfoDialogBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawableResource(R.drawable.word_dialog_bg)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val data: EntityDict? = requireArguments().getParcelable("key")
        binding.word.text = data!!.en_word
        binding.wordTranscript.text = data!!.transcript
        binding.wordType.text = data!!.type
        binding.wordTranslation.text = data!!.translation
        lookStar(data?.favourite, binding.star)
        binding.closeBtn.setOnClickListener {
            dialog?.dismiss()
        }
        binding.star.setOnClickListener {
            setBookmark(it, data)
        }

    }

    private fun setBookmark(view: View, data: EntityDict?) {
        val imageView: ImageView = view as ImageView
        val bookmarkStatus = data?.favourite
        if (bookmarkStatus == 1) {
            imageView.setImageResource(R.drawable.hole_star)
            data.favourite = 0
            viewModel.updateWord(data, requireContext())
        } else {
            imageView.setImageResource(R.drawable.full_star)
            data?.favourite = 1
            viewModel.updateWord(data, requireContext())
        }

    }


    private fun lookStar(favouriteStatus: Int?, view: View) {
        val imageView = view as ImageView
        if (favouriteStatus == 1) {
            view.setImageResource(R.drawable.full_star)
        } else {
            view.setImageResource(R.drawable.hole_star)
        }

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}