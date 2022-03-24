package com.common.lib_base.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

public class AppUtil {
    /**
     * 开启自启动管理
     *
     * @param activity context
     * @return 返回自启动管理页面的Intent
     */
    public static void openAutoStart(Activity activity) {
        try {
//            ComponentName componentName = null;
            String brand = Build.MANUFACTURER;
//            Intent intent = new Intent();
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            switch (brand.toLowerCase()) {
                case "samsung"://三星
//                    componentName = new ComponentName("com.samsung.android.sm", "com.samsung.android.sm.app.dashboard.SmartManagerDashBoardActivity");
                    break;
                case "huawei"://华为
                    onHuaWei(activity);
                    //荣耀V8，EMUI 8.0.0，Android 8.0上，以下两者效果一样
//                    componentName = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity");
                    break;
                case "xiaomi"://小米
                    onXIAOMI(activity);
//                    componentName = new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity");
                    break;
                case "vivo"://VIVO
                    onVivo(activity);
//                    componentName = new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity");
                    break;
                case "oppo"://OPPO
//                    componentName = new ComponentName("com.coloros.oppoguardelf", "com.coloros.powermanager.fuelgaue.PowerUsageModelActivity");
                    break;
                case "yulong":
                case "360"://360
//                    componentName = new ComponentName("com.yulong.android.coolsafe", "com.yulong.android.coolsafe.ui.activity.autorun.AutoRunListActivity");
                    break;
                case "meizu"://魅族
//                    componentName = new ComponentName("com.meizu.safe", "com.meizu.safe.permission.SmartBGActivity");
                    break;
                case "oneplus"://一加
//                    componentName = new ComponentName("com.oneplus.security", "com.oneplus.security.chainlaunch.view.ChainLaunchAppListActivity");
                    break;
                case "letv"://乐视
//                    intent.setAction("com.letv.android.permissionautoboot");
                default://其他
//                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
//                    intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
                    break;
            }
//            intent.setComponent(componentName);
//            activity.startActivity(intent);
        } catch (Exception e) {
            jumpStartInterface(activity);
        }
    }

    //获取手机类型
    private static String getMobileType() {
        return Build.MANUFACTURER;
    }

    //跳转至授权页面
    private static void jumpStartInterface(Context context) {
        Intent intent = new Intent();
        try {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Log.e("HLQ_Struggle", "******************当前手机型号为：" + getMobileType());
            ComponentName componentName = null;
            if (getMobileType().equals("Xiaomi")) { // 红米Note4测试通过
                componentName = new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity");
            } else if (getMobileType().equals("Letv")) { // 乐视2测试通过
                intent.setAction("com.letv.android.permissionautoboot");
            } else if (getMobileType().equals("samsung")) { // 三星Note5测试通过
                //componentName = new ComponentName("com.samsung.android.sm_cn", "com.samsung.android.sm.ui.ram.AutoRunActivity");
                //componentName = ComponentName.unflattenFromString("com.samsung.android.sm/.ui.ram.RamActivity");// Permission Denial not exported from uid 1000，不允许被其他程序调用
                componentName = ComponentName.unflattenFromString("com.samsung.android.sm/.app.dashboard.SmartManagerDashBoardActivity");
            } else if (getMobileType().equals("HUAWEI")) { // 华为测试通过
                //componentName = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity");//锁屏清理
                componentName = ComponentName.unflattenFromString("com.huawei.systemmanager/.startupmgr.ui.StartupNormalAppListActivity");//跳自启动管理
                //SettingOverlayView.show(context);
            } else if (getMobileType().equals("vivo")) { // VIVO测试通过
                componentName = ComponentName.unflattenFromString("com.iqoo.secure/.safeguard.PurviewTabActivity");
            } else if (getMobileType().equals("Meizu")) { //万恶的魅族
                //componentName = ComponentName.unflattenFromString("com.meizu.safe/.permission.PermissionMainActivity");//跳转到手机管家
                componentName = ComponentName.unflattenFromString("com.meizu.safe/.permission.SmartBGActivity");//跳转到后台管理页面
            } else if (getMobileType().equals("OPPO")) { // OPPO R8205测试通过
                componentName = ComponentName.unflattenFromString("com.oppo.safe/.permission.startup.StartupAppListActivity");
            } else if (getMobileType().equals("ulong")) { // 360手机 未测试
                componentName = new ComponentName("com.yulong.android.coolsafe", ".ui.activity.autorun.AutoRunListActivity");
            } else {
                // 将用户引导到系统设置页面
                if (Build.VERSION.SDK_INT >= 9) {
                    Log.e("HLQ_Struggle", "APPLICATION_DETAILS_SETTINGS");
                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.fromParts("package", context.getPackageName(), null));
                } else if (Build.VERSION.SDK_INT <= 8) {
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
                    intent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
                }
            }
            intent.setComponent(componentName);
            context.startActivity(intent);
            if (getMobileType().equals("Xiaomi")) {
                showtip();//显示弹窗（**特别注意**）
            }
            if (getMobileType().equals("samsung")) {
//                new SettingOverlayView().show(context);//显示悬浮窗
            }

        } catch (Exception e) {//抛出异常就直接打开设置页面
            Log.e("HLQ_Struggle", e.getLocalizedMessage());
            intent = new Intent(Settings.ACTION_SETTINGS);
            context.startActivity(intent);
        }
    }

    //小米手机显示弹窗
    private static void showtip() {
//        try {
//            dialog_per=new SettingDialogPermision(context, R.style.CustomDialog4);
//            dialog_per.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);//注意这里改成吐司类型
//            dialog_per.show();
//            Log.e("HLQ_Struggle","显示弹窗");
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.e("HLQ_Struggle", "没有显示弹窗"+e.getMessage());
//        }
    }

    private static void onHuaWei(Activity context) {
        ComponentName componentName = null;
        int sdkVersion = Build.VERSION.SDK_INT;
        try {
            Intent intent = new Intent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //跳自启动管理
            if (sdkVersion >= 28) {//9:已测试
                componentName = ComponentName.unflattenFromString("com.huawei.systemmanager/.startupmgr.ui.StartupNormalAppListActivity");//跳自启动管理
            } else if (sdkVersion >= 26) {//8：已测试
                componentName = ComponentName.unflattenFromString("com.huawei.systemmanager/.appcontrol.activity.StartupAppControlActivity");
            } else if (sdkVersion >= 23) {//7.6：已测试
                componentName = ComponentName.unflattenFromString("com.huawei.systemmanager/.startupmgr.ui.StartupNormalAppListActivity");
            } else if (sdkVersion >= 21) {//5
                componentName = ComponentName.unflattenFromString("com.huawei.systemmanager/com.huawei.permissionmanager.ui.MainActivity");
            }
            //componentName = new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity");//锁屏清理
            intent.setComponent(componentName);
            context.startActivity(intent);
        } catch (Exception e) {
            //跳转失败
        }
    }

    private static void onVivo(Activity context) {
        Intent localIntent;
        try {
            if (((Build.MODEL.contains("Y85")) && (!Build.MODEL.contains("Y85A"))) || (Build.MODEL.contains("vivo Y53L"))) {
                localIntent = new Intent();
                localIntent.setClassName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.PurviewTabActivity");
                localIntent.putExtra("packagename", context.getPackageName());
                localIntent.putExtra("tabId", "1");
                context.startActivity(localIntent);
            } else {
                localIntent = new Intent();
                localIntent.setClassName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.SoftPermissionDetailActivity");
                localIntent.setAction("secure.intent.action.softPermissionDetail");
                localIntent.putExtra("packagename", context.getPackageName());
                context.startActivity(localIntent);
            }
        } catch (Exception e) {
            // 否则跳转到应用详情
            localIntent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", context.getPackageName(), null);
            localIntent.setData(uri);
            context.startActivity(localIntent);
        }
    }

    private static void onXIAOMI(Activity context) {
        openAtutoStart(context);
//        try {
//             MIUI 8
//            Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
//            localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
//            localIntent.putExtra("extra_pkgname", context.getPackageName());
//            context.startActivity(localIntent);
//        } catch (Exception e) {
//            try {
//                 MIUI 5/6/7
//                Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
//                localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
//                localIntent.putExtra("extra_pkgname", context.getPackageName());
//                context.startActivity(localIntent);
//            } catch (Exception e1) {
//                openAtutoStart(context);
//                Intent intent = new Intent();
//                try {
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    ComponentName componentName = null;
//                    componentName = new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity");
//                    intent.setComponent(componentName);
//                    context.startActivity(intent);
//                } catch (Exception e2) {//抛出异常就直接打开设置页面
//                    // 否则跳转到应用详情
//                    Intent intent2 = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
//                    Uri uri = Uri.fromParts("package", context.getPackageName(), null);
//                    intent2.setData(uri);
//                    context.startActivity(intent2);
//                }
//            }
//        }
    }

    private static void openAtutoStart(Activity activity) {
        Intent intent = new Intent();
        intent.setAction("miui.intent.action.OP_AUTO_START");
        intent.addCategory("android.intent.category.DEFAULT");
        activity.startActivity(intent);
    }
}
