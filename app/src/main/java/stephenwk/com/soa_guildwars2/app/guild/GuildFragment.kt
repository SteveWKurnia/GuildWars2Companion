package stephenwk.com.soa_guildwars2.app.guild

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.soa_guildwars2.R
import com.example.soa_guildwars2.domain.datamodel.GuildDataModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.guild_detail_activity.*
import kotlinx.android.synthetic.main.guild_fragment.*

@AndroidEntryPoint
class GuildFragment: Fragment() {

    private val viewModel: GuildViewModel by viewModels()

    private lateinit var adapter: GuildAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
                R.layout.guild_fragment,
                container,
                false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllGuildData()
        viewModel.allGuildData.observe(viewLifecycleOwner, getAllGuildData)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = GuildAdapter()
        rv_guild?.apply {
            adapter = this@GuildFragment.adapter
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL,false)
        }
    }

    private fun showGuildRecycler() {
        rv_guild?.visibility = View.VISIBLE
        pb_guild?.visibility = View.GONE
        empty_state?.visibility = View.GONE
    }

    private fun showEmptyState() {
        rv_guild?.visibility = View.GONE
        pb_guild?.visibility = View.GONE
        empty_state?.visibility = View.VISIBLE
    }

    private var getAllGuildData: Observer<List<GuildDataModel>> = Observer {
        if (it.isEmpty()) {
            showEmptyState()
        } else {
            adapter.setData(it)
            showGuildRecycler()
        }
    }

}