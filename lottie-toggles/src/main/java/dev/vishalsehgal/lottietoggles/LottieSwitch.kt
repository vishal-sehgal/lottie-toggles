package dev.vishalsehgal.lottietoggles

import android.content.Context
import android.icu.util.Measure
import android.util.AttributeSet
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import com.airbnb.lottie.LottieDrawable
import com.airbnb.lottie.RenderMode
import dev.vishalsehgal.lottietoggles.view.LottiefiedSwitchView
import java.lang.IllegalStateException
import kotlin.math.min

class LottieSwitch @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var animationFile: Int = -1
    private var toggleSpeed = 1f

    internal var isChecked: Boolean
        get() = this.lottieAnimationView.isChecked
        set(value) {
            this.lottieAnimationView.isChecked = value
        }

    private val lottieAnimationView by lazy {
        LottiefiedSwitchView(context).apply {
            if (animationFile == -1)
                throw IllegalStateException("Could not resolve the lottie switch toggle animation file")

            layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
            repeatMode = LottieDrawable.REVERSE
            setRenderMode(RenderMode.AUTOMATIC)
            speed = toggleSpeed
            setAnimation(animationFile)
        }
    }

    init {

        context.theme.obtainStyledAttributes(attrs, R.styleable.LottieSwitch, defStyleAttr, 0)
            .let { style ->

                animationFile = style.getResourceId(R.styleable.LottieSwitch_switch_lottieFile, -1)
                toggleSpeed = style.getFloat(R.styleable.LottieSwitch_switch_toggleSpeed, 3f)
                isChecked = style.getBoolean(R.styleable.LottieSwitch_switch_checked, false)
                post{ addView(lottieAnimationView)}
                style.recycle()

            }

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val desiredWidth = resources.getDimensionPixelSize(R.dimen.lottie_switch_default_width)
        val desiredHeight = resources.getDimensionPixelSize(R.dimen.lottie_switch_default_height)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)

        val measuredWidth = when (widthMode) {
            MeasureSpec.EXACTLY -> {
                widthSize
            }
            MeasureSpec.AT_MOST -> {
                min(desiredWidth, widthSize)
            }
            else -> {
                desiredWidth
            }
        }

        val measuredHeight = when(heightMode){
            MeasureSpec.EXACTLY ->{
                heightSize
            }
            MeasureSpec.AT_MOST ->{
                min(desiredHeight, heightSize)
            }
            else -> {
                desiredHeight
            }
        }

        setMeasuredDimension(measuredWidth, measuredHeight)
        measureChild(lottieAnimationView, measuredWidth, measuredHeight)
    }

}