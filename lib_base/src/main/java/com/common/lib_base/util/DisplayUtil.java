package com.common.lib_base.util;

import android.content.Context;
import android.content.res.Resources;


public class DisplayUtil {

    private DisplayUtil() {

    }

    /**
     * 获取屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度
     *
     * @return
     */
    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    /**
     * 底部导航栏(navigation bar)
     */
    public static int getNavigationBarHeight() {
        Resources resources = Resources.getSystem();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

    /**
     * 获取屏幕物理像素密度
     *
     * @return
     */
    public static float getScreenDensity() {
        return Resources.getSystem().getDisplayMetrics().density;
    }

    /**
     * dp转px
     *
     * @param dp
     * @return
     */
    public static int dpToPx(float dp) {
        return Math.round(Resources.getSystem().getDisplayMetrics().density * dp + 0.5f);
    }

    /**
     * dp转px
     *
     * @param dp
     * @return
     */
    public static float dpToPxf(float dp) {
        return Math.round(Resources.getSystem().getDisplayMetrics().density * dp + 0.5f);
    }

    /**
     * sp转px
     *
     * @param sp
     * @return
     */
    public static int spToPx(int sp) {
        return Math.round(Resources.getSystem().getDisplayMetrics().scaledDensity * sp + 0.5f);
    }

    /**
     * px转dp
     *
     * @param px
     * @return
     */
    public static int pxToDp(int px) {
        return Math.round(px / Resources.getSystem().getDisplayMetrics().density + 0.5f);
    }

    /**
     * px转sp
     *
     * @param px
     * @return
     */
    public static int pxToSp(int px) {
        return Math.round(px / Resources.getSystem().getDisplayMetrics().scaledDensity + 0.5f);
    }

    ///**
    // * 获取状态栏的高度
    // * @param context
    // * @return
    // */
    //public static int getStatusHeight(Context context) {
    //	Rect rect = new Rect();
    //	((Activity)context).getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
    //	return rect.top;
    //}
    public static int getStatusBarHeight(Context context) {
        // 获得状态栏高度
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        return context.getResources().getDimensionPixelSize(resourceId);
    }

    /**
     * 获取屏幕参数
     *
     * @return
     */
    public static String getScreenParams() {
        return getScreenWidth() + "*" + getScreenHeight();
    }
}
