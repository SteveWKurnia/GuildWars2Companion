package stephenwk.com.soa_guildwars2.app.guild

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import stephenwk.com.soa_guildwars2.R
import stephenwk.com.soa_guildwars2.app.guild.guilddetail.GuildDetailActivity
import stephenwk.com.soa_guildwars2.domain.datamodel.GuildDataModel
import kotlinx.android.synthetic.main.guild_member_recycler_item.view.*
import kotlinx.android.synthetic.main.guild_recycler_item.view.*

class GuildAdapter: RecyclerView.Adapter<GuildAdapter.GuildViewHolder>() {

    private val data = mutableListOf<GuildDataModel>()

    fun setData(newData: List<GuildDataModel>) {
        data.apply {
            clear()
            addAll(newData)
        }
        notifyDataSetChanged()
    }

    class GuildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: GuildDataModel) {
            itemView.apply {
                tv_guild_name?.text = data.name
                val tag = "<${data.tag}>"
                tv_guild_tag?.text = tag
                Glide.with(this).load(data.foregroundEmblemId).into(iv_emblem_fg)
                Glide.with(this).load(data.backgroundEmblemId).into(iv_emblem_bg)
                setOnClickListener {
                    val intent = Intent(context, GuildDetailActivity::class.java)
                    intent.putExtra("GUILD_ID", data.id)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuildViewHolder = GuildViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.guild_recycler_item, parent, false)
    )

    override fun onBindViewHolder(holder: GuildViewHolder, position: Int) = holder.bind(data[position])

    override fun getItemCount() = data.size
}