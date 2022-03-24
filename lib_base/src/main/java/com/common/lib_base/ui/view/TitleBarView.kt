package com.common.lib_base.ui.view

import android.content.Context
import android.support.annotation.ColorRes
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.common.lib_base.R
import com.common.lib_base.listener.SingleClickListener
import com.common.lib_base.util.DisplayUtil

/**
 * 自定义公共titleBar
 */
class TitleBarView : ConstraintLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        inflate(context, R.layout.view_title_layout, this)

        val layoutParams = this.titleViewBar.layoutParams
        layoutParams.height = DisplayUtil.getStatusBarHeight(context)
        titleViewBar.layoutParams = layoutParams

        //设置title 的高度
        val rootLayout = this.layoutParams
        rootLayout.height = R.dimen.dp_45
        this.layoutParams = rootLayout
    }


    private val titleBtnBack: ImageView by lazy {
        findViewById(R.id.titleBtnBack)
    }
    private val titleTvTitle: TextView by lazy {
        findViewById(R.id.titleTvTitle)
    }
    private val titleViewBar: View by lazy {
        findViewById(R.id.titleViewBar)
    }

    fun setOnBackFinish(listener: SingleClickListener?) {
        titleBtnBack.setOnClickListener(listener)
    }

    fun setOnTitle(title: String?) {
        titleTvTitle.text = title
    }

    fun setTitleViewBarBg(@ColorRes color: Int) {
        titleViewBar.setBackgroundColor(resources.getColor(color))
    }

    fun setTitleBarBg(@ColorRes colorId: Int) {
        setBackgroundResource(colorId)
    }
}