package dev.vishalsehgal.lottietoggles.sample

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import dev.vishalsehgal.lottietoggles.LottieSwitch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val container = findViewById<ConstraintLayout>(R.id.container)

        findViewById<LottieSwitch>(R.id.lottie_switch).setOnCheckedChangedListener { toggleableView, isChecked ->
            Toast.makeText(this, isChecked.toString(), Toast.LENGTH_SHORT).show()

            container.setBackgroundColor(if (isChecked) Color.BLACK else Color.WHITE)
        }


    }
}