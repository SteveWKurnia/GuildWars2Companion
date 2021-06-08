package stephenwk.com.soa_guildwars2.app.home.worldboss.worldbossdetail

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.example.soa_guildwars2.R
import com.example.soa_guildwars2.app.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_daily_detail.*
import kotlinx.android.synthetic.main.wb_detail_activity.*
import kotlinx.android.synthetic.main.wb_detail_activity.iv_back

@AndroidEntryPoint
class WorldBossDetail: AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var adapter: DailyWorldBossAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.wb_detail_activity)
        setupRecycler()
    }

    override fun onStart() {
        super.onStart()
        val pos = checkIntentExtras()
        setupBackButton()
        setupViewModel(pos)
    }

    private fun checkIntentExtras(): Int {
        return if (intent.hasExtra("WORLD_BOSS_ITEM")) {
            intent.getIntExtra("WORLD_BOSS_ITEM", 0)
        } else 0
    }

    private fun setupBackButton() = iv_back?.setOnClickListener { finish() }

    private fun setupRecycler() {
        adapter = DailyWorldBossAdapter()
        rv_wb_detail?.apply {
            adapter = this@WorldBossDetail.adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setupViewModel(pos: Int) {
        viewModel.getAllWorldBoss()
        viewModel.completeWorldBoss.observe(this, {
            hideProgressBar()
            adapter.updateItems(it)
            rv_wb_detail?.betterSmoothScrollToPosition(pos)
        })

    }

    private fun hideProgressBar() {
        pb_wb_detail?.visibility = View.GONE
        rv_wb_detail?.visibility = View.VISIBLE
    }

    private fun RecyclerView.betterSmoothScrollToPosition(targetItem: Int) {
        val smoothScroller = object: LinearSmoothScroller(context) {
            override fun getVerticalSnapPreference(): Int {
                return SNAP_TO_START
            }
        }
        layoutManager?.apply {
            val maxScroll = 10
            when (this) {
                is LinearLayoutManager -> {
                    val topItem = findFirstVisibleItemPosition()
                    val distance = topItem - targetItem
                    val anchorItem = when {
                        distance > maxScroll -> targetItem + maxScroll
                        distance < -maxScroll -> targetItem - maxScroll
                        else -> topItem
                    }
                    if (anchorItem != topItem) {
                        smoothScroller.targetPosition = anchorItem
                        startSmoothScroll(smoothScroller)
                    }
                    post {
                        smoothScroller.targetPosition = targetItem
                        startSmoothScroll(smoothScroller)
                    }
                }
            }
        }
    }
}