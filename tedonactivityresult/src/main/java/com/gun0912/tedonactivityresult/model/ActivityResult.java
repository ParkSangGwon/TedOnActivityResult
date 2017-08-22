package com.gun0912.tedonactivityresult.model;

import android.content.Intent;


public class ActivityResult {

  private int resultCode;
  private Intent data;

  public ActivityResult(int resultCode, Intent data) {
    this.resultCode = resultCode;
    this.data = data;
  }

  public int getResultCode() {
    return resultCode;
  }

  public Intent getData() {
    return data;
  }

  @Override
  public String toString() {
    return "ActivityResult{" +
        "resultCode=" + resultCode +
        ", data=" + data +
        '}';
  }
}
