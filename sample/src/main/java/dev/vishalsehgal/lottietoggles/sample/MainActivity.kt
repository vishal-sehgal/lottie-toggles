package dev.vishalsehgal.lottietoggles.sample

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.internal.ViewUtils.dpToPx
import dev.vishalsehgal.lottietoggles.LottieSwitch
import dev.vishalsehgal.lottietoggles.sample.adapter.TogglesAdapter
import java.util.*


class MainActivity : AppCompatActivity() {

    companion object {
        const val MARGIN = 16
    }

    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.apply {
            adapter = TogglesAdapter()
            addItemDecoration(SpaceItemDecoration(dpToPx(MARGIN), true))
        }

        addListItems()

    }

    private fun addListItems() {
        val toggleItems = arrayListOf<Toggle>().apply {
            add(
                Toggle(
                    1,
                    "Monster Toggle",
                    "Vishal Sehgal",
                    "",
                    R.raw.monster_toggle,
                    "Vishal Sehgal"
                )
            )
            add(
                Toggle(
                    2,
                    "Day Night Toggle",
                    "Vishal Sehgal",
                    "",
                    R.raw.day_night_toggle,
                    "Vishal Sehgal"
                )
            )
            add(
                Toggle(
                    3,
                    "Christmas Toggle",
                    "Vishal Sehgal",
                    "",
                    R.raw.christmas_toggle,
                    "Vishal Sehgal"
                )
            )

            add(
                Toggle(
                    4,
                    "Gender Toggle",
                    "Vishal Sehgal",
                    "",
                    R.raw.gender_switch,
                    "Vishal Sehgal"
                )
            )
            add(
                Toggle(
                    5,
                    "Heart Pop Toggle",
                    "Vishal Sehgal",
                    "",
                    R.raw.heart_pop_switch,
                    "Vishal Sehgal"
                )
            )
            add(
                Toggle(
                    6,
                    "Jelly Switch",
                    "Vishal Sehgal",
                    "",
                    R.raw.jelly_switch,
                    "Vishal Sehgal"
                )
            )
            add(
                Toggle(
                    7,
                    "Jello Switch",
                    "Vishal Sehgal",
                    "",
                    R.raw.jelly_switch_two,
                    "Vishal Sehgal"
                )
            )
            add(
                Toggle(
                    8,
                    "Lemon Orange Switch",
                    "Vishal Sehgal",
                    "",
                    R.raw.lemon_orange_toggle,
                    "Vishal Sehgal"
                )
            )
            add(
                Toggle(
                    9,
                    "Location Service Toggle",
                    "Vishal Sehgal",
                    "",
                    R.raw.location_service_switch,
                    "Vishal Sehgal"
                )
            )
            add(
                Toggle(
                    10,
                    "Morphing Toggle",
                    "Vishal Sehgal",
                    "",
                    R.raw.morphing_switch,
                    "Vishal Sehgal"
                )
            )
            add(
                Toggle(
                    11,
                    "Simple Toggle",
                    "Vishal Sehgal",
                    "",
                    R.raw.simple_toggle,
                    "Vishal Sehgal"
                )
            )
            add(
                Toggle(
                    12,
                    "Squashy Toggle",
                    "Vishal Sehgal",
                    "",
                    R.raw.squashy_toggle,
                    "Vishal Sehgal"
                )
            )
            add(
                Toggle(
                    13,
                    "Christmas Bauble Toggle",
                    "Vishal Sehgal",
                    "",
                    R.raw.christmas_bauble_toggle,
                    "Vishal Sehgal"
                )
            )
            add(
                Toggle(
                    14,
                    "Christmas Decoration Toggle",
                    "Vishal Sehgal",
                    "",
                    R.raw.christmas_decoration_toggle,
                    "Vishal Sehgal"
                )
            )
        }

        toggleItems.sortBy { it.id }
        (recyclerView.adapter as TogglesAdapter).submitList(toggleItems)
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

    fun Context.dpToPx(dp: Int) = (resources.displayMetrics.density * dp).toInt()
}