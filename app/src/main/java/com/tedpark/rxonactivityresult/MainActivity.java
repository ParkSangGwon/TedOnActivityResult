package com.tedpark.rxonactivityresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.gun0912.tedonactivityresult.TedOnActivityResult;
import com.tedpark.tedonactivityresult.rx2.TedRxOnActivityResult;

public class MainActivity extends AppCompatActivity {

  public static final String TAG = "ted";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Intent intent = new Intent(this, SampleActivity.class);
    intent.putExtra(SampleActivity.EXTRA_NAME, "gun0912");
    intent.putExtra(SampleActivity.EXTRA_AGE, 19);

    findViewById(R.id.btn_normal).setOnClickListener(view -> {

      TedOnActivityResult.with(this)
          .setIntent(intent)
          .setListener((resultCode, data) -> {

            if (resultCode == RESULT_OK) {
              String name = data.getStringExtra(SampleActivity.EXTRA_NAME);
              int age = data.getIntExtra(SampleActivity.EXTRA_AGE, -1);
              Log.v(TAG, "MainActivity normal onActivityResult() name: " + name + ", age: " + age);
            }

          })
          .startActivityForResult();

    });

    findViewById(R.id.btn_rx1).setOnClickListener(view -> {

      com.tedpark.tedonactivityresult.rx1.TedRxOnActivityResult.with(this)
          .startActivityForResult(intent)
          .subscribe(activityResult -> {

            if (activityResult.getResultCode() == RESULT_OK) {
              Intent data = activityResult.getData();
              String name = data.getStringExtra(SampleActivity.EXTRA_NAME);
              int age = data.getIntExtra(SampleActivity.EXTRA_AGE, -1);

              Log.v("ted", "MainActivity rx1 onActivityResult() name: " + name + ", age: " + age);
            }

          });

    });

    findViewById(R.id.btn_rx2).setOnClickListener(view -> {

      TedRxOnActivityResult.with(this)
          .startActivityForResult(intent)
          .subscribe(activityResult -> {

            if (activityResult.getResultCode() == RESULT_OK) {
              Intent data = activityResult.getData();
              String name = data.getStringExtra(SampleActivity.EXTRA_NAME);
              int age = data.getIntExtra(SampleActivity.EXTRA_AGE, -1);

              Log.v("ted", "MainActivity rx2 onActivityResult() name: " + name + ", age: " + age);
            }

          });

    });
  }

}
