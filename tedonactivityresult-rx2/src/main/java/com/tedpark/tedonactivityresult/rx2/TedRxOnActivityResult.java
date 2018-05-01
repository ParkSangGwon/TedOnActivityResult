package com.tedpark.tedonactivityresult.rx2;

import android.content.Context;
import android.content.Intent;

import com.gun0912.tedonactivityresult.ProxyActivity;
import com.gun0912.tedonactivityresult.listener.OnActivityResultListener;
import com.gun0912.tedonactivityresult.TedOnActivityResult;
import com.gun0912.tedonactivityresult.model.ActivityResult;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.subjects.PublishSubject;

public final class TedRxOnActivityResult {
  public static Builder with(Context context) {
    return new Builder(context);
  }

  public final static class Builder {
    private final Context context;
    private final OnActivityResultListener listener;
    private final PublishSubject<ActivityResult> subject = PublishSubject.create();

    private Builder(final Context context) {
      this.context = context;
      this.listener = (new OnActivityResultListener() {
        @Override
        public void onActivityResult(int resultCode, Intent data) {
          subject.onNext(new ActivityResult(resultCode, data));
          subject.onComplete();
        }
      });
    }

    public Single<ActivityResult> startActivityForResult(final Intent intent) {
      ProxyActivity.startActivityForResult(context, intent, listener);
      return subject.singleOrError();
    }
  }
}
