package com.example.soa_guildwars2.app.home.dailyachievements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.soa_guildwars2.R
import kotlinx.android.synthetic.main.daily_achievements_recycler_item.view.*

class DailyAchievementsAdapter: RecyclerView.Adapter<DailyAchievementsAdapter.CustomViewHolder>() {

    private val data = mutableListOf<AchievementsModel>()

    fun setItems(ids: List<AchievementsModel>) {
        data.apply {
            clear()
            addAll(ids)
        }
        notifyDataSetChanged()
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(achievementsModel: AchievementsModel) {
            itemView.tv_daily_achievements?.text = achievementsModel.name
            itemView.tv_daily_description?.text = achievementsModel.description
            val text = "Lvl Requirement: ${achievementsModel.minLvl} - ${achievementsModel.maxLvl}"
            itemView.tv_lvl_requirement?.text = text
            itemView.iv_arrow_down?.setOnClickListener {
                achievementsModel.isExpanded = !achievementsModel.isExpanded
                itemView.rl_expandable?.visibility = if(achievementsModel.isExpanded){
                    Glide.with(itemView).load(R.drawable.ic_baseline_keyboard_arrow_up_24).into(itemView.iv_arrow_down)
                    View.VISIBLE
                } else {
                    Glide.with(itemView).load(R.drawable.ic_baseline_keyboard_arrow_down_24).into(itemView.iv_arrow_down)
                    View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder =
            CustomViewHolder(LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.daily_achievements_recycler_item, parent, false))

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) = holder.bind(data[position])

    override fun getItemCount(): Int = data.size

}