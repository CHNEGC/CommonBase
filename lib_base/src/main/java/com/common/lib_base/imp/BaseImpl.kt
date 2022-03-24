package com.common.lib_base.imp

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

interface BaseImpl {
    val layoutView: Int
    val isShowTitleBar: Boolean
    fun bindListener()
    fun initData()
    fun initLayoutView()
    val titleString: String?

    @DrawableRes
    fun setTitleBackIcon(): Int

    @ColorRes
    fun setTitleViewBarBg(): Int

    @ColorRes
    fun setTitleBarBg(): Int
}