package library.wlt.com.openlibrary.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by PC_WLT on 2017/5/12.
 *
 * fragment封装 添加butternife
 */

public abstract class KBaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=null;
        if (resLayout()>0){
             v=inflater.inflate(resLayout(),container,false);
//            ButterKnife.bind(this,v);
            butterKnifeFragment(this,v);
        }
        return resLayout()>0?super.onCreateView(inflater, container, savedInstanceState):v;
    }
    protected abstract int resLayout();
    protected abstract void butterKnifeFragment(Fragment fragment, View v);

}
