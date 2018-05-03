package library.wlt.com.openlibrary.base;

import android.app.Application;


/**
 * Created by PC_WLT on 2017/4/26.
 */

public class KBaseApplication extends Application {

    public static KBaseApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }
}
