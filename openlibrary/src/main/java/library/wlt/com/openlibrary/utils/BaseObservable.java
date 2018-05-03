package library.wlt.com.openlibrary.utils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import pc.wlt.com.superlibrary.listener.OnBaseObservableListener;

/**
 * Created by PC_WLT on 2017/9/12.
 */

public class BaseObservable<T> {

    public BaseObservable(Observable<T> observable, final OnBaseObservableListener listener) {
        listener.onStart();
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<T>() {

            @Override
            public void accept(@NonNull T t) throws Exception {
                listener.onSuccess(t);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                listener.onFailure(throwable.getMessage());
            }
        });
    }

}
