package com.example.soa_guildwars2.app.guild.guildmember

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.soa_guildwars2.R
import com.example.soa_guildwars2.app.guild.guilddetail.GuildDetailViewModel
import com.example.soa_guildwars2.app.guild.guilddetail.GuildMemberAdapter
import com.example.soa_guildwars2.app.guild.guilddetail.GuildMemberModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_guild_member.*
import kotlinx.android.synthetic.main.guild_detail_activity.*
import kotlinx.android.synthetic.main.guild_detail_activity.iv_back
import kotlinx.android.synthetic.main.guild_detail_activity.rv_guild_member_data

@AndroidEntryPoint
class GuildMemberActivity : AppCompatActivity() {

    private val viewModel: GuildDetailViewModel by viewModels()

    private lateinit var adapter: GuildMemberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guild_member)
    }

    override fun onStart() {
        super.onStart()
        setupBackButton()
        setupRecyclerView()
        setupViewModel()
    }

    private fun setupRecyclerView() {
        adapter = GuildMemberAdapter()
        rv_guild_member_data?.apply {
            adapter = this@GuildMemberActivity.adapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun setupViewModel() {
        viewModel.getGuildMemberData()
        viewModel.guildMembers.observe(this, getMembersData)
    }

    private fun setupBackButton() = iv_back?.setOnClickListener { finish() }

    private fun hideProgressBar() {
        pb_guild_member?.visibility = View.GONE
        rv_guild_member_data?.visibility = View.VISIBLE
    }

    private var getMembersData: Observer<List<GuildMemberModel>> = Observer {
        hideProgressBar()
        adapter.updateData(it)
    }
}