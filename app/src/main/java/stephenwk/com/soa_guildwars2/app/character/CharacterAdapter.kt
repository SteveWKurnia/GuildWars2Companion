package stephenwk.com.soa_guildwars2.app.character

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import stephenwk.com.soa_guildwars2.R
import kotlinx.android.synthetic.main.character_recycler_item.view.*

class CharacterAdapter: RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private val data = mutableListOf<CharacterModel>()

    fun updateItems(newList: List<CharacterModel>) {
        data.apply {
            clear()
            addAll(newList)
        }
        notifyDataSetChanged()
    }

    class CharacterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: CharacterModel) {
            itemView.tv_character_name?.text = data.name
            val detail = "Lvl ${data.level} ${data.gender} ${data.race} ${data.profession}"
            itemView.tv_char_detail?.text = detail
            itemView.tv_char_title?.text = data.title
            when (data.profession) {
                "Necromancer" -> Glide.with(itemView).load(R.drawable.necromancer_icon).into(itemView.iv_class)
                "Mesmer" -> Glide.with(itemView).load(R.drawable.mesmer_icon).into(itemView.iv_class)
                "Elementalist" -> Glide.with(itemView).load(R.drawable.elementalist_icon).into(itemView.iv_class)
                "Thief" -> Glide.with(itemView).load(R.drawable.thief_icon).into(itemView.iv_class)
                "Engineer" -> Glide.with(itemView).load(R.drawable.engineer_icon).into(itemView.iv_class)
                "Ranger" -> Glide.with(itemView).load(R.drawable.ranger_icon).into(itemView.iv_class)
                "Guardian" -> Glide.with(itemView).load(R.drawable.guardian_icon).into(itemView.iv_class)
                "Revenant" -> Glide.with(itemView).load(R.drawable.revenant_icon).into(itemView.iv_class)
                "Warrior" -> Glide.with(itemView).load(R.drawable.warrior_icon).into(itemView.iv_class)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.character_recycler_item, parent,false))

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) = holder.bind(data[position])

    override fun getItemCount(): Int = data.size

}