package com.example.soa_guildwars2.app.home.worldboss.worldbossdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.soa_guildwars2.R
import com.example.soa_guildwars2.app.UtilityHelper.cleanWorldBossUnderscore
import com.example.soa_guildwars2.app.home.worldboss.WorldBossModel
import kotlinx.android.synthetic.main.wb_detail_recycler_item.view.*
import kotlinx.android.synthetic.main.world_boss_home_recycler_item.view.*

class DailyWorldBossAdapter: RecyclerView.Adapter<DailyWorldBossAdapter.DailyWBViewHolder>() {

    private val data = mutableListOf<WorldBossModel>()

    fun updateItems(newData: List<WorldBossModel>) {
        data.apply {
            clear()
            addAll(newData)
        }
        notifyDataSetChanged()
    }

    class DailyWBViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(model: WorldBossModel) {
            itemView.apply {
                val bossName = model.world_boss.cleanWorldBossUnderscore()
                tv_wb_detail_name?.text = bossName
                when(bossName) {
                    "Admiral Taidha Covington" -> Glide.with(this)
                            .load(R.drawable.taidha_covington)
                            .into(iv_wb_detail)
                    "Claw Of Jormag" -> Glide.with(this)
                            .load(R.drawable.jormag)
                            .into(iv_wb_detail)
                    "Tequatl The Sunless" -> Glide.with(this)
                            .load(R.drawable.tequatl)
                            .into(iv_wb_detail)
                    "Drakkar" -> Glide.with(this)
                            .load(R.drawable.drakkar)
                            .into(iv_wb_detail)
                    "Fire Elemental" -> Glide.with(this)
                            .load(R.drawable.fire_elemental)
                            .into(iv_wb_detail)
                    "Great Jungle Wurm" -> Glide.with(this)
                            .load(R.drawable.jungle_wurm)
                            .into(iv_wb_detail)
                    "Inquest Golem Mark Ii" -> Glide.with(this)
                            .load(R.drawable.golem_mark_ii)
                            .into(iv_wb_detail)
                    "Karka Queen" -> Glide.with(this)
                            .load(R.drawable.karka_queen)
                            .into(iv_wb_detail)
                    "Megadestroyer" -> Glide.with(this)
                            .load(R.drawable.megadestroyer)
                            .into(iv_wb_detail)
                    "Modniir Ulgoth" -> Glide.with(this)
                            .load(R.drawable.modniir_ulgoth)
                            .into(iv_wb_detail)
                    "Shadow Behemoth" -> Glide.with(this)
                            .load(R.drawable.shadow_behemoth)
                            .into(iv_wb_detail)
                    "Svanir Shaman Chief" -> Glide.with(this)
                            .load(R.drawable.svanir_shaman)
                            .into(iv_wb_detail)
                    "The Shatterer" -> Glide.with(this)
                            .load(R.drawable.shatterer)
                            .into(iv_wb_detail)
                    "Triple Trouble Wurm" -> Glide.with(this)
                            .load(R.drawable.jungle_wurm)
                            .into(iv_wb_detail)
                    else -> Glide.with(this)
                            .load(R.drawable.tequatl)
                            .into(iv_wb_detail)
                }
                rv_boss_timer?.apply {
                    layoutManager = GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false)
                    adapter = DailyWorldBossTimerAdapter(model.timer)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyWBViewHolder =
            DailyWBViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.wb_detail_recycler_item, parent, false))

    override fun onBindViewHolder(holder: DailyWBViewHolder, position: Int) = holder.bind(data[position])

    override fun getItemCount(): Int = data.size

}