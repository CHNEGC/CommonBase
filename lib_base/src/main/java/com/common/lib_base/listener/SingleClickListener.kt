package com.common.lib_base.listener

import android.view.View
import kotlin.math.abs

/**
 * 禁止段时间内双击
 */
class SingleClickListener(private val listener: ((View) -> Unit)?) : View.OnClickListener {
    private var mLastClickTime = 0L
    override fun onClick(view: View) {
        val time = System.currentTimeMillis()
        if (abs(time - mLastClickTime) < 1000) {
            return
        }
        mLastClickTime = time
        listener?.invoke(view)
    }
}