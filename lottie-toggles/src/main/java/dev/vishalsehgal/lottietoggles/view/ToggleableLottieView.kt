package dev.vishalsehgal.lottietoggles.view

import android.content.Context
import android.util.AttributeSet
import android.widget.Checkable
import com.airbnb.lottie.LottieAnimationView
import dev.vishalsehgal.lottietoggles.`interface`.OnCheckChangeListener

open class ToggleableLottieView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LottieAnimationView(context, attrs, defStyleAttr), Checkable {

    /**
     * Listener used to dispatch toggle events.
     *
     * Register a callback to be invoked when the boolean state of the ToggleableLottieView is changed.
     * If this toggle is not enabled, there won't be any event.
     */
    open var onCheckedChangeListener: OnCheckChangeListener? = null

    private var mBroadcasting = false

    private var _checked: Boolean = false

    override fun setChecked(state: Boolean) {
        if (isChecked != state) {
            _checked = state

            // To avoid infinite recursions if isChecked/setChecked() is called from a listener
            if (mBroadcasting) {
                return
            }
            mBroadcasting = true
            onCheckedChangeListener?.onCheckedChanged(this, isChecked)
            mBroadcasting = false
        }
    }

    override fun isChecked(): Boolean = _checked

    override fun toggle() {
        isChecked = !isChecked
    }

    override fun performClick(): Boolean {
        if(!isAnimating) toggle() // Changing toggle state on clicking the ToggleableLottieView.
        return super.performClick()
    }

}