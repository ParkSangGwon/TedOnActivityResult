package com.tedpark.rxonactivityresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;

/**
 * Created by tedpark on 2017. 8. 9..
 */

public class SampleActivity extends Activity {

  public static final String EXTRA_NAME = "name";
  public static final String EXTRA_AGE = "age";

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    String name = getIntent().getStringExtra(EXTRA_NAME);
    int age = getIntent().getIntExtra(EXTRA_AGE, 0);
    Log.v(MainActivity.TAG, "name: " + name + ", age: " + age);

    Intent data = new Intent();
    data.putExtra(EXTRA_NAME, "Ted Park");
    data.putExtra(EXTRA_AGE, 32);

    setResult(RESULT_OK, data);
    finish();

  }
}
