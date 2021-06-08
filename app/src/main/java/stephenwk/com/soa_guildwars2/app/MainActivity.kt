package stephenwk.com.soa_guildwars2.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.soa_guildwars2.R
import com.example.soa_guildwars2.app.guild.GuildFragment
import com.example.soa_guildwars2.app.home.HomeFragment
import com.example.soa_guildwars2.app.character.CharacterFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var fragment: Fragment

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
}