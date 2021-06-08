package stephenwk.com.soa_guildwars2.app.home.dailyachievements

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
            itemView.apply {
                tv_daily_achievements?.text = achievementsModel.name
                tv_daily_description?.text = achievementsModel.description
                val text = "Lvl Requirement: ${achievementsModel.minLvl} - ${achievementsModel.maxLvl}"
                tv_lvl_requirement?.text = text
                cv_daily?.setOnClickListener {
                    achievementsModel.isExpanded = !achievementsModel.isExpanded
                    rl_expandable?.visibility = if(achievementsModel.isExpanded){
                        iv_arrow_down?.visibility = View.GONE
                        iv_arrow_up?.visibility = View.VISIBLE
                        View.VISIBLE
                    } else {
                        iv_arrow_up?.visibility = View.GONE
                        iv_arrow_down?.visibility = View.VISIBLE
                        View.GONE
                    }
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