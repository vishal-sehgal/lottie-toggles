package dev.vishalsehgal.lottietoggles.view

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.view.MotionEvent
import dev.vishalsehgal.lottietoggles.LottieSwitch
import kotlin.math.abs
import kotlin.math.min

class LottiefiedSwitchView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ToggleableLottieView(context, attrs, defStyleAttr) {

    private var tapStartTime = 0L
    private var padding = 0

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        val parentHeight = MeasureSpec.getSize(heightMeasureSpec)
        this.setMeasuredDimension(parentWidth, parentHeight)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return if (isEnabled) {
            val x = event.x

            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    tapStartTime = System.currentTimeMillis()
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    val centerRadii = (min(this.width, this.height) / 2.88f).toInt()
                    val min = x - (centerRadii ushr 1)
                    val max = x + (centerRadii ushr 1)

                    if (min > padding && max < this.width - padding) {
                        val progress = (x) / (this.width - padding - centerRadii)
                        if (!isAnimating) {
                            this.progress = progress
                            invalidate()
                        }
                    }

                    true

                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    val tapEndTime = System.currentTimeMillis()
                    val span = tapEndTime - tapStartTime

                    when {
                        span < 100 -> performClick()
                        else -> {
                            if (!isAnimating) {
                                if (x >= this.width ushr 1) {
                                    progress = 1f
                                    super.setChecked(true)
                                } else {
                                    progress = 0f
                                    super.setChecked(false)
                                }
                            }
                        }
                    }

                    invalidate()
                    true
                }
                else -> super.onTouchEvent(event)
            }
        } else {
            false
        }

    }

    override fun performClick(): Boolean {
        super.performClick()
        if (!isAnimating) {
            speed = if (isChecked) -abs(speed) else abs(speed)
            playAnimation()
        }
        return true
    }

    override fun setChecked(state: Boolean) {
        super.setChecked(state)
        if (!isAnimating) {
            speed = if (isChecked) abs(speed) else -abs(speed)
            playAnimation()
        }
    }

    override fun setEnabled(enabled: Boolean) {
        if (this.isEnabled != enabled)
            if (enabled) this.alpha = 1f else this.alpha = 0.5f
        super.setEnabled(enabled)
    }

    override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()
        val ss = LottieSwitch.SavedState(superState)
        ss.checked = isChecked
        ss.progress = progress
        return ss
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val ss = state as LottieSwitch.SavedState
        super.onRestoreInstanceState(ss.superState)
        isChecked = ss.checked
        progress = ss.progress
        requestLayout()
    }

}