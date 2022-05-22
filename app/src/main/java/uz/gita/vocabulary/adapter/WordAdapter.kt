package uz.gita.vocabulary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import uz.gita.vocabulary.R
import uz.gita.vocabulary.db.EntityDict

class WordAdapter(val listener: ClickItemListener) : RecyclerView.Adapter<WordViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<EntityDict>() {

        override fun areItemsTheSame(oldItem: EntityDict, newItem: EntityDict) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: EntityDict, newItem: EntityDict) =
            oldItem == newItem
    }

    var differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item, parent, false)
        return WordViewHolder(view)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val wordText:TextView = holder.itemView.findViewById(R.id.rv_item_text1)
        val transcript:TextView = holder.itemView.findViewById(R.id.rv_item_text2)
        wordText.text = differ.currentList[position].en_word
        transcript.text = differ.currentList[position].transcript
        holder.itemView.setOnClickListener {
          listener.onItemClickListener(differ.currentList[position])
        }
    }

    override fun getItemCount() = differ.currentList.size


}