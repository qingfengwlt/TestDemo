package library.wlt.com.openlibrary.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

/**
 * @version v1.0 <描述当前版本功能>
 * @fileName: pkg.shi.com.util.config.VersionUtils.java
 * @author: leitao
 * @date 2016-06-03 09:22
 * @update by wlt on  2016.7.1 //添加设备类型，设置版本号
 */
public class VersionUtils {

    /**
     * 返回当前程序版本名
     */
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
//            versioncode = pi.versionCode;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versionName;
    }

    public static String getVersionName(Context context) {
        String versionCode = "";
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            try {
                versionCode = context.getPackageManager().getPackageInfo("com.jsmy.jiayin", 0).versionName;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        return versionCode;
    }

    /**
     * 获取手机类型
     * @return
     */
    public static String getDeviceType(){
        return Build.BRAND+" "+ Build.MODEL;
    }

    /**
     * 获取手机版本号
     * @return
     */
    public static String getDeviceVersion(){
        return Build.VERSION.RELEASE;
    }

}
