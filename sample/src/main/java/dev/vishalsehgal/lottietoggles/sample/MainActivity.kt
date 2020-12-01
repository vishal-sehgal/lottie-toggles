package dev.vishalsehgal.lottietoggles.sample

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import dev.vishalsehgal.lottietoggles.LottieSwitch


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val container = findViewById<ConstraintLayout>(R.id.container)

        findViewById<LottieSwitch>(R.id.lottieSwitch0).setOnCheckedChangedListener { toggleableView, isChecked ->
            Toast.makeText(this, isChecked.toString(), Toast.LENGTH_SHORT).show()

            container.setBackgroundColor(if (isChecked) Color.BLACK else Color.WHITE)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_github_repo_link -> {
                val url = "https://lottietoggles.vishalsehgal.dev"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}