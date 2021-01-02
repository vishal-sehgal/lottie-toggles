package dev.vishalsehgal.lottietoggles.sample

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.ceil

class SpaceItemDecoration(
    private val space: Int,
    private val includeEdge: Boolean
) : RecyclerView.ItemDecoration() {

    init {
        require(space >= 0) { "Space between items can not be negative" }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemCount =
            parent.adapter?.itemCount ?: throw IllegalStateException("Adapter must not be null")
        val itemPosition = parent.getChildAdapterPosition(view)
        val layoutDirection = parent.layoutDirection
        val sideSize = if (layoutDirection == VERTICAL) view.width else view.height
        val (spans, orientation) = with(parent.layoutManager!!) {
            when (this) {
                is GridLayoutManager -> Pair(
                    this.spanCount,
                    this.orientation
                ) // grid is itself a linear!
                is LinearLayoutManager -> Pair(1, this.orientation)
                else -> throw IllegalArgumentException("For now, only LinearLayout and GridLayout managers are supported")
            }
        }

        val params = DecorationParams(
            itemCount = itemCount,
            itemPosition = itemPosition,
            spanCount = spans,
            itemSideSize = sideSize,
            layoutDirection = layoutDirection,
            orientation = orientation
        )
        setItemMargin(outRect, params, includeEdge)
    }

    private fun setItemMargin(outRect: Rect, params: DecorationParams, includeEdge: Boolean): Rect {

        val (top, right, bottom, left) = LinearLayoutMarginFormula.apply {
            updateItem(params, space, includeEdge)
        }.calculate()

        outRect.top = top
        outRect.right = right
        outRect.bottom = bottom
        outRect.left = left
        return outRect
    }

    internal data class DecorationParams(
        val itemCount: Int,
        val itemPosition: Int,
        val spanCount: Int,
        val itemSideSize: Int,
        val layoutDirection: Int,
        val orientation: Int,
    )

    // -----------------------------------------------------------------

    internal data class Margin(val top: Int, val right: Int, val bottom: Int, val left: Int)

    private object LinearLayoutMarginFormula {

        private fun findCorrectListVariation(
            layoutDirection: Int,
            orientation: Int
        ): LinearListVariation {
            return listVariations[layoutDirection + (2 * orientation)]
        }

        private var includeEdge: Boolean = false
        private var margin = 0
        private lateinit var params: DecorationParams

        // LTR = 0, RTL = 1, Horizontal = 0, Vertical = 1
        private val listVariations = arrayOf(
            HorizontalLTR(),
            HorizontalRTL(),
            VerticalLTR(),
            VerticalRTL(),
        )

        fun updateItem(params: DecorationParams, margin: Int, includeEdge: Boolean) {
            this.params = params
            this.margin = margin
            this.includeEdge = includeEdge
        }

        fun calculate(): Margin {
            val borderMargin = if (includeEdge) margin else 0
            val numberOfMargins = params.spanCount + (if (includeEdge) 1 else -1)
            val itemLayoutWidth =
                params.itemSideSize - ((numberOfMargins * margin) / params.spanCount)

            val top = topMargin(borderMargin)
            val left = leftMargin(params.itemSideSize, itemLayoutWidth, borderMargin)
            val right = rightMargin(params.itemSideSize, itemLayoutWidth, left)
            val bottom = bottomMargin(borderMargin)

            val list: LinearListVariation = findCorrectListVariation(
                layoutDirection = params.layoutDirection,
                orientation = params.orientation
            )
            return list.adaptVerticalLtrMargin(Margin(top, right, bottom, left))
        }

        private fun leftMargin(itemWidth: Int, layoutWidth: Int, borderMargin: Int): Int {
            fun spanIdx() = params.itemPosition % params.spanCount

            return spanIdx() * (margin + layoutWidth - itemWidth) + borderMargin
        }

        fun topMargin(borderMargin: Int): Int {
            fun isAtTop() = params.itemPosition < params.spanCount

            return if (isAtTop()) borderMargin else (margin / 2)
        }

        fun rightMargin(itemWidth: Int, layoutWidth: Int, leftMargin: Int): Int {
            return itemWidth - layoutWidth - leftMargin
        }

        fun bottomMargin(borderMargin: Int): Int {
            fun isAtBottom() = row(params.itemPosition, params.spanCount) ==
                    rows(params.itemCount, params.spanCount)

            return if (isAtBottom()) borderMargin else (margin / 2)
        }


        private fun row(position: Int, spanCount: Int): Int {
            return ceil((position + 1).toFloat() / spanCount.toFloat()).toInt()
        }

        private fun rows(itemCount: Int, spanCount: Int): Int {
            return ceil(itemCount.toFloat() / spanCount.toFloat()).toInt()
        }


        interface LinearListVariation {
            fun adaptVerticalLtrMargin(margin: Margin): Margin
        }

        class VerticalLTR : LinearListVariation {
            override fun adaptVerticalLtrMargin(margin: Margin) = margin
        }

        class VerticalRTL : LinearListVariation {
            override fun adaptVerticalLtrMargin(margin: Margin) = Margin(
                top = margin.top,
                right = margin.left,
                bottom = margin.bottom,
                left = margin.right
            )
        }

        class HorizontalLTR : LinearListVariation {
            override fun adaptVerticalLtrMargin(margin: Margin) = Margin(
                top = margin.left,
                right = margin.bottom,
                bottom = margin.right,
                left = margin.top
            )
        }

        class HorizontalRTL : LinearListVariation {
            override fun adaptVerticalLtrMargin(margin: Margin) = Margin(
                top = margin.left,
                right = margin.top,
                bottom = margin.right,
                left = margin.bottom
            )
        }
    }
}