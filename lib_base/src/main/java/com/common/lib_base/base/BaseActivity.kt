package com.common.lib_base.base

import android.R
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.common.lib_base.imp.BaseImpl
import com.common.lib_base.listener.SingleClickListener
import com.common.lib_base.ui.view.TitleBarView
import com.gyf.immersionbar.ImmersionBar

abstract class BaseActivity : AppCompatActivity(), BaseImpl {
    private var mTitleBarView: TitleBarView? = null
    private var isFirst = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutView)
        ImmersionBar.with(this)
            .barAlpha(0.0f)
            .transparentStatusBar() //透明状态栏，不写默认透明色
            .init()
        if (isShowTitleBar) {
            mTitleBarView = TitleBarView(BaseApplication.application)
            mTitleBarView.setTitleViewBarBg(setTitleViewBarBg())
            mTitleBarView.setTitleBarBg(setTitleBarBg())
            mTitleBarView.setOnTitle(titleString)
            rootView.addView(mTitleBarView, 0)
        }
        initLayoutView()
        bindListener()
    }

    /**
     * 获取根视图，就是我们自己设置的视图的父容器，例如这里就是titlebar的容器
     */
    private val rootView: ViewGroup
        private get() = androidContentView.parent as ViewGroup

    /**
     * 获取android content view
     */
    private val androidContentView: View
        private get() = findViewById(R.id.content)

    /**
     * 获取底层的容器
     */
    private val contentView: FrameLayout
        private get() = androidContentView as FrameLayout

    /**
     * 展示标题栏
     *
     * @return true：添加标题栏，否则不添加
     */
    val isShowTitleBar: Boolean
        get() = true

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus && isFirst) {
            rootView.postDelayed(Runnable { initData() }, 500)
        }
        isFirst = false
    }

    fun bindListener() {
        mTitleBarView.setOnBackFinish(SingleClickListener(object : OnClickListener() {
            fun onClick(view: View?) {
                finish()
            }
        }))
    }

    fun setTitleViewBarBg(): Int {
        return R.color.color_128EE8
    }

    fun setTitleBarBg(): Int {
        return R.color.color_128EE8
    }
}