package com.gun0912.tedonactivityresult.model;

import android.content.Intent;
import com.gun0912.tedonactivityresult.listener.OnActivityResultListener;

public class ActivityRequest {


  private Intent intent;
  private OnActivityResultListener listener;

  public ActivityRequest(Intent intent,
      OnActivityResultListener listener) {
    this.intent = intent;
    this.listener = listener;
  }

  public Intent getIntent() {
    return intent;
  }

  public OnActivityResultListener getListener() {
    return listener;
  }
}
