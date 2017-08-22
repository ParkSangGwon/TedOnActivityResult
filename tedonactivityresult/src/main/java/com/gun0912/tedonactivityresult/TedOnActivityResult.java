package com.gun0912.tedonactivityresult;

import android.content.Context;
import android.content.Intent;
import com.gun0912.tedonactivityresult.listener.OnActivityResultListener;

public class TedOnActivityResult {

  public static Builder with(Context context) {
    return new Builder(context);
  }


  public static class Builder {

    private Context context;
    private OnActivityResultListener listener;
    private Intent intent;

    public Builder(Context context) {
      this.context = context;
    }

    public void startActivityForResult() {
      ProxyActivity.startActivityForResult(context, intent, listener);
    }

    public Builder setListener(OnActivityResultListener listener) {
      this.listener = listener;
      return this;
    }

    public Builder setIntent(Intent intent) {
      this.intent = intent;
      return this;
    }

  }
}
