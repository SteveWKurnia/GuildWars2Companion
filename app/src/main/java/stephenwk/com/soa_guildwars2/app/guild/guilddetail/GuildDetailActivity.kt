package stephenwk.com.soa_guildwars2.app.guild.guilddetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import stephenwk.com.soa_guildwars2.R
import stephenwk.com.soa_guildwars2.app.guild.guildmember.GuildMemberActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.guild_detail_activity.*

@AndroidEntryPoint
class GuildDetailActivity : AppCompatActivity() {

    private val viewModel: GuildDetailViewModel by viewModels()

    private lateinit var memberAdapter: GuildMemberAdapter

    private var isFgEmblemLoaded = false
    private var isBgEmblemLoaded = false

    private var isNameLoaded = false
    private var isTagLoaded = false
    private var isMotdLoaded = false
    private var isMemberLoaded = false

    private var guildId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.guild_detail_activity)
        getBundle()
    }

    override fun onStart() {
        super.onStart()
        reinitializeVariables()
        setupBackButton()
        setupSeeAllMember()
        setupViewModel()
        observeViewModel()
        setupRecycler()
    }

    private fun reinitializeVariables() {
        showProgressBar()
        isFgEmblemLoaded = false
        isBgEmblemLoaded = false
        isNameLoaded = false
        isTagLoaded = false
        isMotdLoaded = false
        isMemberLoaded = false
    }

    private fun setupSeeAllMember() {
        tv_member_see_all?.setOnClickListener {
            val intent = Intent(this, GuildMemberActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getBundle() {
        intent.extras?.let {
            guildId = it.getString("GUILD_ID", "")
        }
    }

    private fun hideMainProgressBar() {
        if (isMemberLoaded and isNameLoaded and isTagLoaded
            and isMotdLoaded and isFgEmblemLoaded and isBgEmblemLoaded) {
            pb_guild_fragment?.visibility = View.GONE
            cl_container?.visibility = View.VISIBLE
        }
    }

    private fun showProgressBar(){
        pb_guild_fragment?.visibility = View.VISIBLE
        cl_container?.visibility = View.GONE
    }

    private fun setupRecycler() {
        memberAdapter = GuildMemberAdapter()
        rv_guild_member_data?.apply {
            adapter = memberAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        }
    }

    private fun setupViewModel() {
        viewModel.apply {
            getGuildMemberData()
            getGuildData(guildId)
        }
    }

    private fun observeViewModel() {
        viewModel.apply {
            guildMembers.observe(this@GuildDetailActivity, getGuildMemberData)
            guildName.observe(this@GuildDetailActivity, getGuildNameObserver)
            guildTag.observe(this@GuildDetailActivity, getGuildTagObserver)
            guildForegroundEmblem.observe(this@GuildDetailActivity, getGuildFgEmblemObserver)
            guildBackgroundEmblem.observe(this@GuildDetailActivity, getGuildBgEmblemObserver)
            guildMotd.observe(this@GuildDetailActivity, getGuildMotdObserver)
        }
    }

    private fun setupBackButton() {
        iv_back?.setOnClickListener {
            finish()
        }
    }

    private val getGuildNameObserver: Observer<String> = Observer {
        isNameLoaded = true
        hideMainProgressBar()
        tv_guild_name?.text = it
    }

    private val getGuildTagObserver: Observer<String> = Observer {
        isTagLoaded = true
        hideMainProgressBar()
        val txt = "<${it}>"
        tv_guild_tag?.text = txt
    }

    private val getGuildMotdObserver: Observer<String> = Observer {
        isMotdLoaded = true
        hideMainProgressBar()
        tv_guild_motd?.text = it
    }

    private val getGuildMemberData: Observer<List<GuildMemberModel>> = Observer {
        isMemberLoaded = true
        hideMainProgressBar()
        memberAdapter.updateData(it.take(3))
    }

    private val getGuildFgEmblemObserver: Observer<String> = Observer {
        isFgEmblemLoaded = true
        hideMainProgressBar()
        Glide.with(this)
            .load(it)
            .into(iv_emblem_fg)
    }

    private val getGuildBgEmblemObserver: Observer<String> = Observer {
        isBgEmblemLoaded = true
        hideMainProgressBar()
        Glide.with(this)
            .load(it)
            .into(iv_emblem_bg)
    }


}