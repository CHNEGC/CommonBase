package com.common.lib_base.base

import android.app.Application
import com.common.lib_base.util.SystemUtil

abstract class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
        if (SystemUtil.isAppMainProcess(this)) {
            initAppMainProcess()
        }
    }

    companion object {
        var application: BaseApplication? = null
            private set
    }


    /**
     * 在主线程做一些优化
     */
    abstract fun initAppMainProcess();
}