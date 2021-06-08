package stephenwk.com.soa_guildwars2.app.home.worldboss

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import stephenwk.com.soa_guildwars2.R
import stephenwk.com.soa_guildwars2.app.UtilityHelper.cleanWorldBossUnderscore
import stephenwk.com.soa_guildwars2.app.home.worldboss.worldbossdetail.WorldBossDetail
import kotlinx.android.synthetic.main.world_boss_home_recycler_item.view.*

class WorldBossAdapter: RecyclerView.Adapter<WorldBossAdapter.WorldBossViewHolder>() {

    private val data = mutableListOf<WorldBossModel>()

    fun setItems(worldBosses: List<WorldBossModel>) {
        data.apply {
            clear()
            addAll(worldBosses)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorldBossViewHolder =
            WorldBossViewHolder(LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.world_boss_home_recycler_item, parent, false))

    override fun onBindViewHolder(holder: WorldBossViewHolder, position: Int) = holder.bind(data[position], position)

    override fun getItemCount(): Int = data.size

    class WorldBossViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(wbModel: WorldBossModel, pos: Int) {
            itemView.apply {
                setOnClickListener {
                    val intent = Intent(context, WorldBossDetail::class.java)
                    intent.putExtra("WORLD_BOSS_ITEM", pos)
                    context.startActivity(intent)
                }
                val bossName = wbModel.world_boss.cleanWorldBossUnderscore()
                tv_boss_name?.text = bossName
                iv_boss_overlay?.visibility = if (wbModel.isDone) View.VISIBLE else View.INVISIBLE
                when(bossName) {
                    "Admiral Taidha Covington" -> Glide.with(this)
                            .load(R.drawable.taidha_covington)
                            .into(iv_boss)
                    "Claw Of Jormag" -> Glide.with(this)
                            .load(R.drawable.jormag)
                            .into(iv_boss)
                    "Tequatl The Sunless" -> Glide.with(this)
                            .load(R.drawable.tequatl)
                            .into(iv_boss)
                    "Drakkar" -> Glide.with(this)
                            .load(R.drawable.drakkar)
                            .into(iv_boss)
                    "Fire Elemental" -> Glide.with(this)
                            .load(R.drawable.fire_elemental)
                            .into(iv_boss)
                    "Great Jungle Wurm" -> Glide.with(this)
                            .load(R.drawable.jungle_wurm)
                            .into(iv_boss)
                    "Inquest Golem Mark Ii" -> Glide.with(this)
                            .load(R.drawable.golem_mark_ii)
                            .into(iv_boss)
                    "Karka Queen" -> Glide.with(this)
                            .load(R.drawable.karka_queen)
                            .into(iv_boss)
                    "Megadestroyer" -> Glide.with(this)
                            .load(R.drawable.megadestroyer)
                            .into(iv_boss)
                    "Modniir Ulgoth" -> Glide.with(this)
                            .load(R.drawable.modniir_ulgoth)
                            .into(iv_boss)
                    "Shadow Behemoth" -> Glide.with(this)
                            .load(R.drawable.shadow_behemoth)
                            .into(iv_boss)
                    "Svanir Shaman Chief" -> Glide.with(this)
                            .load(R.drawable.svanir_shaman)
                            .into(iv_boss)
                    "The Shatterer" -> Glide.with(this)
                            .load(R.drawable.shatterer)
                            .into(iv_boss)
                    "Triple Trouble Wurm" -> Glide.with(this)
                            .load(R.drawable.jungle_wurm)
                            .into(iv_boss)
                    else -> Glide.with(this)
                            .load(R.drawable.tequatl)
                            .into(iv_boss)
                }
            }
        }
    }

}