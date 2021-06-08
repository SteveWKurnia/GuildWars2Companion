package stephenwk.com.soa_guildwars2.app.home.worldboss.worldbossdetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.soa_guildwars2.R
import kotlinx.android.synthetic.main.chip_wb_detail.view.*

class DailyWorldBossTimerAdapter(
        var data: List<String>
): RecyclerView.Adapter<DailyWorldBossTimerAdapter.TimerViewHolder>() {

    class TimerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(str: String) {
            itemView.tv_time?.text = str
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimerViewHolder =
            TimerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.chip_wb_detail, parent, false))

    override fun onBindViewHolder(holder: TimerViewHolder, position: Int) = holder.bind(data[position])

    override fun getItemCount(): Int = data.size
}