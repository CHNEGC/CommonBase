package com.common.lib_base.imp

import androidx.annotation.ColorRes

interface BaseImpl {
    val layoutView: Int
    val isShowTitleBar: Boolean
    fun bindListener()
    fun initData()
    fun initLayoutView()
    val titleString: String?

    @ColorRes
    fun setTitleViewBarBg(): Int

    @ColorRes
    fun setTitleBarBg(): Int
}