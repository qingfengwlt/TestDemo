package library.wlt.com.openlibrary.listener;

/**
 * Created by PC_WLT on 2017/9/12.
 */

public interface OnBaseObservableListener<T> {

    void onStart();
    void onSuccess(T response);
    //        void onSuccess(String response, int position);
    void onFailure(String msg);
}
