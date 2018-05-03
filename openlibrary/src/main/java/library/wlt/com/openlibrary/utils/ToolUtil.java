package library.wlt.com.openlibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Log;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author leitao
 * @fileName pkg.shi.com.util
 * @date 2016/4/13
 * @update by     on
 */
public class ToolUtil {

    /** 获取手机唯一标示*/
    public static String getPhoneImei(Context context){
       return  Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    /**
     * Map 转换为 RequestBody实体
     * @param map
     * @return
     */
    public static RequestBody getRequesBody(Map map){
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        Log.d("json", "mapToJson = " + mapToJson(map));
        return RequestBody.create(mediaType, mapToJson(map));
    }

    /**
     * map 转换为 json数据
     * @param map
     * @return
     */
    public static String mapToJson(Map<String,String> map){
        Set<String> keys = map.keySet();
        String key = "";
        String value = "";
        StringBuffer jsonBuffer = new StringBuffer();
        jsonBuffer.append("{");
        for(Iterator<String> it = keys.iterator(); it.hasNext();){
            key =  (String)it.next();
            value = map.get(key);
            jsonBuffer.append("\"");
            jsonBuffer.append(key+"\":\""+value+"\"");
            if(it.hasNext()){
                jsonBuffer.append(",");
            }
        }
        jsonBuffer.append("}");
        return jsonBuffer.toString();
    }

    /**
     * 是否有网络
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            Log.i("NetWorkState", "Unavailabel");
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        Log.i("NetWorkState", "Availabel");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //保留2位小数
    public static double get2Double(double a){
        DecimalFormat df=new DecimalFormat("0.00");
        return new Double(df.format(a).toString());
    }


    /// 转全角的函数(SBC case) ///
///任意字符串
/// 全角字符串 ///
///全角空格为12288,半角空格为32
///其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248 ///

    public static String ToSBC(String input)
    { //半角转全角：
        char[] c=input.toCharArray();
        for (int i = 0; i < c.length; i++)
        {
            if (c[i]==32)
            {
                c[i]=(char)12288; continue;
            }
            if (c[i]<127) c[i]=(char)(c[i]+65248);
        }
        return new String(c);
    }

    /// /// 转半角的函数(DBC case) ///
///任意字符串
/// 半角字符串 ///
///全角空格为12288，半角空格为32
///其他字符半角(33-126)与全角(65281-65374)的对应关系是：均相差65248 ///
    public static String ToDBC(String input)
    {
        char[] c=input.toCharArray();
        for (int i = 0; i < c.length; i++)
        {
            if (c[i]==12288)
            {
                c[i]= (char)32; continue;
            }
            if (c[i]>65280 && c[i]<65375)
                c[i]=(char)(c[i]-65248);
        }
        return new String(c);
    }
}
