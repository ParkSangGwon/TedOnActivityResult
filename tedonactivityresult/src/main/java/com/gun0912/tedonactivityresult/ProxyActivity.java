package com.gun0912.tedonactivityresult;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gun0912.tedonactivityresult.listener.OnActivityResultListener;
import com.gun0912.tedonactivityresult.model.ActivityRequest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class ProxyActivity extends Activity {

    private static Deque<ActivityRequest> activityRequestStack;


    public static void startActivityForResult(Context context, Intent intent, OnActivityResultListener listener) {

        if (activityRequestStack == null) {
            activityRequestStack = new ArrayDeque<>();
        }

        ActivityRequest activityRequest = new ActivityRequest(intent, listener);
        activityRequestStack.push(activityRequest);

        Intent tempIntent = new Intent(context, ProxyActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(tempIntent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (activityRequestStack == null) {
            finish();
            return;
        }
        ActivityRequest activityRequest = activityRequestStack.peek();

        Intent intent = activityRequest.getIntent();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.startActivityForResult(intent, new Random().nextInt(65536));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ActivityRequest activityRequest = activityRequestStack.pop();
        OnActivityResultListener listener = activityRequest.getListener();

        listener.onActivityResult(resultCode, data);

        if (activityRequestStack.size() == 0) {
            activityRequestStack = null;
        }

        finish();
    }
}
