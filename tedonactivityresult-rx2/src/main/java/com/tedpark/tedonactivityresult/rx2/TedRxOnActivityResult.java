package com.tedpark.tedonactivityresult.rx2;

import android.content.Context;
import android.content.Intent;
import com.gun0912.tedonactivityresult.listener.OnActivityResultListener;
import com.gun0912.tedonactivityresult.TedOnActivityResult;
import com.gun0912.tedonactivityresult.model.ActivityResult;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class TedRxOnActivityResult {

  public static Builder with(Context context) {
    return new Builder(context);
  }

  public static class Builder extends TedOnActivityResult.Builder {

    private PublishSubject<ActivityResult> subject = PublishSubject.create();

    private Builder(Context context) {
      super(context);
      setListener(new OnActivityResultListener() {
        @Override
        public void onActivityResult(int resultCode, Intent data) {
          subject.onNext(new ActivityResult(resultCode, data));
          subject.onComplete();
        }
      });
    }

    public Observable<ActivityResult> startActivityForResult(Intent intent) {
      setIntent(intent);
      startActivityForResult();

      return subject;
    }


  }

}
