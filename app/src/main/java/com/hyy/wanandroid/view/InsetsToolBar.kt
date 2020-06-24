package com.hyy.wanandroid.view

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.widget.LinearLayout
import com.google.android.material.appbar.MaterialToolbar

class InsetsToolBar(context: Context, attrs: AttributeSet?) : MaterialToolbar(context, attrs) {

    private var mApply = false

    private var mInsetTop = 0
    constructor(context: Context): this(context, null)

    override fun onApplyWindowInsets(insets: WindowInsets): WindowInsets {
        if (mApply.not()) {
            mApply = !mApply
            val insetTop = insets.systemWindowInsetTop
            mInsetTop = insetTop
            setPadding(paddingLeft, paddingTop + insetTop, paddingRight, paddingBottom)
        }
        return super.onApplyWindowInsets(insets)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    companion object {
        const val TAG = "InsetsToolBar"
    }
}