package stephenwk.com.soa_guildwars2.app.guild.guilddetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.soa_guildwars2.R
import kotlinx.android.synthetic.main.guild_member_recycler_item.view.*

class GuildMemberAdapter: RecyclerView.Adapter<GuildMemberAdapter.MemberViewHolder>() {

    private val data = mutableListOf<GuildMemberModel>()

    fun updateData(newData: List<GuildMemberModel>) {
        data.apply {
            clear()
            addAll(newData)
        }
        notifyDataSetChanged()
    }

    class MemberViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(guildMemberModel: GuildMemberModel) {
            itemView.apply {
                tv_member_name?.text = guildMemberModel.name
                tv_member_rank?.text = guildMemberModel.rank
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberViewHolder =
            MemberViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.guild_member_recycler_item, parent, false))

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) = holder.bind(data[position])

    override fun getItemCount(): Int = data.size
}