package com.common.lib_base.util

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Process
import android.text.TextUtils

/**
 * @AUTHOR Shige Chen
 * @DESCRIPTION  工具类
 * @CREATETIME 2022年03月24日 09:13:00
 */
object SystemUtil {

    /**
     * 判断是不是UI主进程，因为有些东西只能在UI主进程初始化
     */
    fun isAppMainProcess(application: Application): Boolean {
        return try {
            val pid = Process.myPid()
            val process: String = getAppNameByPID(application, pid)
            if (TextUtils.isEmpty(process)) {
                true
            } else application.packageName.equals(process, ignoreCase = true)
        } catch (e: Exception) {
            e.printStackTrace()
            true
        }
    }

    /**
     * 根据Pid得到进程名
     */
    fun getAppNameByPID(context: Context, pid: Int): String {
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (processInfo in manager.runningAppProcesses) {
            if (processInfo.pid == pid) {
                return processInfo.processName
            }
        }
        return ""
    }
}