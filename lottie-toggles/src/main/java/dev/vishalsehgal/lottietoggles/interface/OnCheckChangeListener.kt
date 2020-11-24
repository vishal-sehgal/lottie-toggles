package dev.vishalsehgal.lottietoggles.`interface`

import dev.vishalsehgal.lottietoggles.view.ToggleableLottieView

interface OnCheckChangeListener {

    /**
     * Called when a LottieToggleableView changes it's state.
     *
     * @param toggleableLottieView The view which is either on/off.
     * @param isChecked The on/off state of toggle, true when toggle turns on.
     */
    fun onCheckedChanged(toggleableLottieView: ToggleableLottieView, isChecked: Boolean)
}