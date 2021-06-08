package stephenwk.com.soa_guildwars2.app.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.soa_guildwars2.R
import com.example.soa_guildwars2.app.MainActivity
import com.example.soa_guildwars2.app.apikey.ApiKeyActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {

    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

    override fun onStart() {
        super.onStart()
        viewModel.getApiKey()
        viewModel.api_key.observe(this, getApiKey)
    }

    private val getApiKey: Observer<String> = Observer {
        if (it != "") {
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        } else {
            val intent = Intent(this, ApiKeyActivity::class.java)
            this.startActivity(intent)
        }
    }
}