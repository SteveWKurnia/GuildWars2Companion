package stephenwk.com.soa_guildwars2.app.home.dailyachievements.dailyachievementsdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soa_guildwars2.R
import com.example.soa_guildwars2.app.home.HomeViewModel
import com.example.soa_guildwars2.app.home.dailyachievements.DailyAchievementsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_daily_detail.*

@AndroidEntryPoint
class DailyDetailActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var achievementAdapter: DailyAchievementsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_detail)
        viewModel.getDailyAchievements()
    }

    override fun onStart() {
        super.onStart()
        setupRecycler()
        iv_back?.setOnClickListener {
            finish()
        }
        viewModel.dailyAchievements.observe(this, {
            hideProgressBar()
            achievementAdapter.setItems(it)
        })
    }

    private fun setupRecycler() {
        achievementAdapter = DailyAchievementsAdapter()
        rv_daily_detail?.apply {
            adapter = achievementAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun hideProgressBar() {
        pb_daily_detail?.visibility = View.GONE
        rv_daily_detail?.visibility = View.VISIBLE
    }
}