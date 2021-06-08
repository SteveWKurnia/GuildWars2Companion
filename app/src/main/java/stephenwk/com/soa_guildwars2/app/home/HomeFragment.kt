package stephenwk.com.soa_guildwars2.app.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import stephenwk.com.soa_guildwars2.R
import stephenwk.com.soa_guildwars2.app.apikey.ApiKeyActivity
import stephenwk.com.soa_guildwars2.app.home.dailyachievements.AchievementsModel
import stephenwk.com.soa_guildwars2.app.home.dailyachievements.DailyAchievementsAdapter
import stephenwk.com.soa_guildwars2.app.home.dailyachievements.dailyachievementsdetail.DailyDetailActivity
import stephenwk.com.soa_guildwars2.app.home.worldboss.WorldBossAdapter
import stephenwk.com.soa_guildwars2.app.home.worldboss.WorldBossModel
import stephenwk.com.soa_guildwars2.app.home.worldboss.worldbossdetail.WorldBossDetail
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.home_fragment.*

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private lateinit var wbAdapter: WorldBossAdapter

    private lateinit var dailyAdapter: DailyAchievementsAdapter

    private val viewModel: HomeViewModel by viewModels()

    private var isGemLoaded = false
    private var isGoldLoaded = false
    private var isSilverLoaded = false
    private var isCopperLoaded = false
    private var isKarmaLoaded = false
    private var isMainDataLoaded = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupLogoutButton()
        setupWorldBossRecycler()
        setupDailyAchievementsRecycler()
        setupDailySeeAll()
        setupWBSeeAll()
        setupViewModel()
    }

    private fun setupLogoutButton() {
        iv_logout?.setOnClickListener {
            MaterialAlertDialogBuilder(it.context)
                    .setTitle(resources.getString(R.string.logout))
                    .setMessage(resources.getString(R.string.logout_desc))
                    .setNegativeButton(resources.getString(R.string.stay)) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .setPositiveButton(resources.getString(R.string.logout)) { _, _ ->
                        viewModel.removeApiKey()
                        val intent = Intent(context, ApiKeyActivity::class.java)
                        context?.startActivity(intent)
                    }
                    .show()
        }
    }

    private fun setupWorldBossRecycler() {
        wbAdapter = WorldBossAdapter()
        rv_world_boss?.apply {
            adapter = wbAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setupDailyAchievementsRecycler() {
        dailyAdapter = DailyAchievementsAdapter()
        rv_daily?.apply {
            adapter = dailyAdapter
            layoutManager =LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            isNestedScrollingEnabled = false
            setHasFixedSize(true)
        }
    }

    private fun setupViewModel() {
        viewModel.getAccountName()
        viewModel.getAccountGold()
        viewModel.getAccountSilver()
        viewModel.getAccountCopper()
        viewModel.getAccountGem()
        viewModel.getAccountKarma()
        viewModel.getAllWorldBoss()
        viewModel.getDailyAchievements()
    }

    private fun observeViewModel() {
        viewModel.accountName.observe(viewLifecycleOwner, getAccountNameObserver)
        viewModel.gold.observe(viewLifecycleOwner, getAccountGoldObserver)
        viewModel.silver.observe(viewLifecycleOwner, getAccountSilverObserver)
        viewModel.copper.observe(viewLifecycleOwner, getAccountCopperObserver)
        viewModel.gem.observe(viewLifecycleOwner, getAccountGemObserver)
        viewModel.karma.observe(viewLifecycleOwner, getAccountKarmaObserver)
        viewModel.completeWorldBoss.observe(viewLifecycleOwner, getAllWorldBossList)
        viewModel.dailyAchievements.observe(viewLifecycleOwner, getTodayDailyAchievements)
    }

    private fun hideMainDataProgressbar() {
        if (
            isCopperLoaded and isGemLoaded and isGoldLoaded and
            isSilverLoaded and isMainDataLoaded and isKarmaLoaded
        ) {
            pb_wallet?.visibility = View.GONE
            rl_acc_data?.visibility = View.VISIBLE
        }
    }

    private fun setupDailySeeAll() {
        tv_see_all_daily?.setOnClickListener {
            val intent = Intent(context, DailyDetailActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupWBSeeAll() {
        tv_see_all_wb?.setOnClickListener {
            val intent = Intent(context, WorldBossDetail::class.java)
            startActivity(intent)
        }
    }

    private var getAccountNameObserver: Observer<String> = Observer {
        isMainDataLoaded = true
        hideMainDataProgressbar()
        tv_acc_name?.text = it
    }

    private var getAccountGoldObserver: Observer<Int> = Observer {
        isGoldLoaded = true
        hideMainDataProgressbar()
        tv_gold?.text = it.toString()
    }

    private var getAccountSilverObserver: Observer<Int> = Observer {
        isSilverLoaded = true
        hideMainDataProgressbar()
        tv_silver?.text = it.toString()
    }

    private var getAccountCopperObserver: Observer<Int> = Observer {
        isCopperLoaded = true
        hideMainDataProgressbar()
        tv_copper?.text = it.toString()
    }

    private var getAccountGemObserver: Observer<Int> = Observer {
        isGemLoaded = true
        hideMainDataProgressbar()
        tv_gem?.text = it.toString()
    }

    private var getAccountKarmaObserver: Observer<Int> = Observer {
        isKarmaLoaded = true
        hideMainDataProgressbar()
        tv_karma?.text = it.toString()
    }

    private var getAllWorldBossList: Observer<List<WorldBossModel>> = Observer {
        pb_world_boss?.visibility = View.GONE
        rv_world_boss?.visibility = View.VISIBLE
        wbAdapter.setItems(it)
    }

    private var getTodayDailyAchievements: Observer<List<AchievementsModel>> = Observer {
        pb_daily?.visibility = View.GONE
        rv_daily?.visibility = View.VISIBLE
        dailyAdapter.setItems(it.take(4))
    }
}