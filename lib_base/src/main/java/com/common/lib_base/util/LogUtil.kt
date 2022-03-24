package com.common.lib_base.util

import android.util.Log
import com.common.lib_dimens.BuildConfig

/**
 * Log工具类
 */
object LogUtil {
    private const val TAG = "DEFAULT"

    /**
     * 是否需要开启Log
     */
    private const val NEED_LOG = BuildConfig.DEBUG

    fun i(content: String) {
        if (NEED_LOG) {
            Log.i(TAG, logPrefix + content)
        }
    }

    fun i(tag: String?, content: String) {
        if (NEED_LOG) {
            Log.i(tag, logPrefix + content)
        }
    }

    fun d(content: String) {
        if (NEED_LOG) {
            Log.d(TAG, logPrefix + content)
        }
    }

    fun d(tag: String?, content: String) {
        if (NEED_LOG) {
            Log.d(tag, logPrefix + content)
        }
    }

    fun e(content: String) {
        if (NEED_LOG) {
            Log.e(TAG, logPrefix + content)
        }
    }

    fun e(tag: String?, content: String) {
        if (NEED_LOG) {
            Log.e(tag, logPrefix + content)
        }
    }

    fun v(content: String) {
        if (NEED_LOG) {
            Log.v(TAG, logPrefix + content)
        }
    }

    fun v(tag: String?, content: String) {
        if (NEED_LOG) {
            Log.v(tag, logPrefix + content)
        }
    }

    fun w(content: String) {
        if (NEED_LOG) {
            Log.w(TAG, logPrefix + content)
        }
    }

    fun w(tag: String?, content: String) {
        if (NEED_LOG) {
            Log.w(tag, logPrefix + content)
        }
    }

    private val logPrefix: String
        get() = "$className($lineNumber)$$methodName: "

    /**
     * 获取Log所在的类名 （getStackTrace的索引根据调用的顺序来决定，可通过打印Log栈来获取）
     */
    private val className: String?
        get() {
            try {
                val classPath = Thread.currentThread().stackTrace[5].className
                return classPath.substring(classPath.lastIndexOf(".") + 1)
            } catch (e: Exception) {
                e.printStackTrace(System.out)
            }
            return null
        }

    /**
     * 获取Log所在的方法名
     */
    private val methodName: String?
        get() {
            try {
                return Thread.currentThread().stackTrace[5].methodName
            } catch (e: Exception) {
                e.printStackTrace(System.out)
            }
            return null
        }

    /**
     * 获取Log所在的行
     */
    private val lineNumber: Int
        get() {
            try {
                return Thread.currentThread().stackTrace[5].lineNumber
            } catch (e: Exception) {
                e.printStackTrace(System.out)
            }
            return 0
        }
}