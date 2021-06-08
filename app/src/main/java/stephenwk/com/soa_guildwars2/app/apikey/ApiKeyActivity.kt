package stephenwk.com.soa_guildwars2.app.apikey

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import stephenwk.com.soa_guildwars2.R
import stephenwk.com.soa_guildwars2.app.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_api_key.*

@AndroidEntryPoint
class ApiKeyActivity : AppCompatActivity() {

    private val viewModel: ApiKeyViewModel by viewModels()

    private val url = "https://account.arena.net/applications"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api_key)
    }

    override fun onStart() {
        super.onStart()
        viewModel.authenticity.observe(this, apiKeyAuthenticity)
        setupHelpSpannable()
        setupSaveButton()
    }

    private fun setupSaveButton() {
        btn_save?.setOnClickListener{
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY)
            viewModel.apply {
                checkApiKeyAuthenticity(et_api?.text.toString())
            }
        }
    }

    private fun setupHelpSpannable() {

        val ss = SpannableString(resources.getString(R.string.api_help))

        val clickableSpan = object: ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.color = resources.getColor(R.color.design_default_color_error)
            }
        }

        ss.setSpan(clickableSpan, ss.length - 4, ss.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        tv_help?.text = ss
    }

    private var apiKeyAuthenticity: Observer<Boolean> = Observer {
        if (it == true) {
            Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        } else {
            Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
        }
    }

}