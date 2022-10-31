package com.tedpark.rxonactivityresult

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.gun0912.tedonactivityresult.model.ActivityResult
import kotlinx.coroutines.launch

class CoroutineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            val intent = Intent(this@CoroutineActivity, SampleActivity::class.java).apply {
                putExtra(SampleActivity.EXTRA_NAME, "gun0912")
                putExtra(SampleActivity.EXTRA_AGE, 19)
            }
            val activityResult: ActivityResult =
                com.gun0912.tedonactivityresult.coroutine.TedOnActivityResult.with(this@CoroutineActivity)
                    .startActivityForResult(intent)

            if (activityResult.resultCode == RESULT_OK) {
                val data = activityResult.data
                val name = data.getStringExtra(SampleActivity.EXTRA_NAME)
                val age = data.getIntExtra(SampleActivity.EXTRA_AGE, -1)
                Log.v("ted", "CoroutineActivity onActivityResult() name: $name, age: $age")
            }
        }

    }
}
