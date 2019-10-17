package com.vsegura15dev.imageslider

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout

class CursorSliderImageView : LinearLayout {

    var currentCursorPosition = 0
    var amount: Int = 0

    constructor(context: Context?, amount: Int, current: Int = 0) : super(context) {
        this.currentCursorPosition = current
        this.amount = amount
    }

    constructor(context: Context?, attrs: AttributeSet?, amount: Int, current: Int = 0) : super(
        context,
        attrs
    ) {
        this.currentCursorPosition = current
        this.amount = amount
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        amount: Int,
        current: Int = 0
    ) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        this.currentCursorPosition = current
        this.amount = amount
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    fun addCursorImages() {

        for (position in 0 until amount) {

            val cursorView = getCursorView(position)

            if (cursorView.parent != null)
                (cursorView.parent as ViewGroup).removeView(cursorView)

            this.addView(cursorView)
        }

    }

    fun updateCursor(position: Int) {

        if (position != currentCursorPosition) {

            if (position == currentCursorPosition + 1)
                next()

            if (position == currentCursorPosition - 1)
                previous()
        }
    }

    private fun next() {

        disableCursor(currentCursorPosition)
        enableCursor(currentCursorPosition + 1)
        this.currentCursorPosition++

    }

    private fun previous() {

        disableCursor(currentCursorPosition)
        enableCursor(currentCursorPosition - 1)
        this.currentCursorPosition--
    }

    private fun disableCursor(position: Int) {

        this.findViewWithTag<ImageView>(position).setImageResource(R.drawable.image_unselected)
    }

    private fun enableCursor(position: Int) {

        this.findViewWithTag<ImageView>(position).setImageResource(R.drawable.image_selected)
    }

    private fun getCursorView(position: Int): View {

        val id =
            if (position == currentCursorPosition) R.drawable.image_selected else R.drawable.image_unselected

        val containerView = RelativeLayout(context)
        val cursorView =
            LayoutInflater.from(context).inflate(R.layout.image_cursor, containerView, false)
        containerView.addView(cursorView)

        return containerView.findViewById<ImageView>(R.id.cursorImageView).also {
            it.setImageResource(id)
            it.tag = position
        }
    }
}