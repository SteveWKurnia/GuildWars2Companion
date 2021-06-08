package stephenwk.com.soa_guildwars2.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import stephenwk.com.soa_guildwars2.R
import stephenwk.com.soa_guildwars2.app.apikey.ApiKeyActivity
import stephenwk.com.soa_guildwars2.app.character.CharacterFragment
import stephenwk.com.soa_guildwars2.app.guild.GuildFragment
import stephenwk.com.soa_guildwars2.app.home.HomeFragment
import stephenwk.com.soa_guildwars2.app.home.HomeViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var fragment: Fragment

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.frame_fragment, HomeFragment()).commit()

        bottom_nav?.apply {
            setOnNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.nav_home -> {
                        fragment = HomeFragment()
                    }
                    R.id.nav_guild -> {
                        fragment = GuildFragment()
                    }
                    R.id.nav_trading_post -> {
                        fragment = CharacterFragment()
                    }
                }
                supportFragmentManager.beginTransaction().replace(R.id.frame_fragment, fragment).commit()
                return@setOnNavigationItemSelectedListener true
            }

        }
    }

    override fun onBackPressed() {
        MaterialAlertDialogBuilder(this@MainActivity)
            .setTitle(resources.getString(R.string.logout))
            .setMessage(resources.getString(R.string.logout_desc))
            .setNegativeButton(resources.getString(R.string.stay)) { dialog, _ ->
                dialog.dismiss()
            }
            .setPositiveButton(resources.getString(R.string.logout)) { _, _ ->
                viewModel.removeApiKey()
                val intent = Intent(this@MainActivity, ApiKeyActivity::class.java)
                this@MainActivity.startActivity(intent)
            }
            .show()
    }
}