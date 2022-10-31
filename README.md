# What is TedOnActivityResult
When we use `startActivityForResult()`, we have to receive result/data in `onActivityResult()`.<br>

```java
 @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ...
    
    startActivityForResult(intent,REQ_CODE_AAA);
    
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    switch (requestCode){
      case REQ_CODE_AAA:
        if(resultCode==RESULT_OK){
          doSomething();
        }
        break;
      default:
        super.onActivityResult(requestCode, resultCode, data);    
    }
    
  }


```


This is very complex and inconvenience to us.<br><br>



If you use [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html), you want synchronized like this.
```kotlin
val activityResult: ActivityResult = TedOnActivityResult.with().startActivityForResult(intent)
```

If you use [RxJava](https://github.com/ReactiveX/RxJava), you want chaining like this.
```java
  AA()
  .filter(...)
  .subscribeOn(...)
  .observeOn(...)
  .subscribe(...);
```

**TedOnActivityResult** can make `startActivityForResult()` chaining.

<br><br><br><br>

# Setup
- Edit `root/app/build.gradle` like below.
- You can choose only one library depend on your code style `normal`/`coroutine`/`rxJava1`/`rxJava2`
- Replace `x.y.z` with the version shown in the 'Maven Central' button below, or the specific version you want (e.g. replace `x.y.z` with `3.3.0` if you want v3.3.0).

[![Maven Central](https://img.shields.io/maven-central/v/io.github.ParkSangGwon/tedonactivityresult-rx1.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22io.github.ParkSangGwon%22%20AND%20a:%tedonactivityresult-rx1%22)

```gradle
repositories {
  google()
  mavenCentral()
}

dependencies {
    // Normal
    implementation 'io.github.ParkSangGwon:tedonactivityresult:x.y.z'
    
    // Coroutine
    implementation 'io.github.ParkSangGwon:tedonactivityresult-coroutine:x.y.z'

    // RxJava2
    implementation 'io.github.ParkSangGwon:tedonactivityresult-rx1:x.y.z'
    // RxJava3
    implementation 'io.github.ParkSangGwon:tedonactivityresult-rx2:x.y.z'
}
```

If you think this library is useful, please press the star button at the top.
<br/>
<img src="https://phaser.io/content/news/2015/09/10000-stars.png" width="200">


<br><br>

# How to use

## Normal
Also you can use `OnActivityResultListener` not RxJava's chaining style
```java
TedOnActivityResult.with(this)
    .setIntent(intent)
    .setListener((resultCode, data) -> {
       if (resultCode == RESULT_OK) {
        String name = data.getStringExtra(SampleActivity.EXTRA_NAME);
        int age = data.getIntExtra(SampleActivity.EXTRA_AGE, -1);
      }

    })
    .startActivityForResult();
```
<br/><br/>

## Coroutine
If you use kotlin and coroutine, You can use suspend function.
```kotlin
val activityResult: ActivityResult = TedOnActivityResult.with(this).startActivityForResult(intent)
```
<br/><br/>


## RxJava
RxJava1/RxJava2 can write code like this.
```java
TedRxOnActivityResult.with(this)
    .startActivityForResult(intent)
    .subscribeOn(...)
    .observeOn(...)
    .subscribe(activityResult -> {

      if (activityResult.getResultCode() == RESULT_OK) {
        Intent data = activityResult.getData();
        String name = data.getStringExtra(SampleActivity.EXTRA_NAME);
        int age = data.getIntExtra(SampleActivity.EXTRA_AGE, -1);
      }

    });
```

<br><br><br><br>

# License 
 ```code
Copyright 2017 Ted Park

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
