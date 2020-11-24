package dev.vishalsehgal.lottietoggles

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

class LottieSwitch @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var animationFile: Int = -1
    private var toggleSpeed = 1f

    internal var isChecked: Boolean = false

    init {

        context.theme.obtainStyledAttributes(attrs, R.styleable.LottieSwitch, defStyleAttr, 0)
            .let { style ->

                animationFile = style.getResourceId(R.styleable.LottieSwitch_switch_lottieFile, -1)
                toggleSpeed = style.getFloat(R.styleable.LottieSwitch_switch_toggleSpeed, 3f)
                isChecked = style.getBoolean(R.styleable.LottieSwitch_switch_checked, false)

            }

    }

}