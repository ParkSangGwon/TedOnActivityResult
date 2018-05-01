# What is TedRxOnActivityResult
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

If you use [RxJava](https://github.com/ReactiveX/RxJava), you want chaining like this.
```java
  AA()
  .filter(...)
  .subscribeOn(...)
  .observeOn(...)
  .subscribe(...);
```

**TedRxOnActivityResult** can make `startActivityForResult()` chaining.

<br><br><br><br>

# Setup
You can select library according to your `RxJava` version.

## RxJava1
[ ![Download](https://api.bintray.com/packages/tkdrnjs0912/maven/tedonactivityresult-rx1/images/download.svg) ](https://bintray.com/tkdrnjs0912/maven/tedonactivityresult-rx1/_latestVersion)
```java
dependencies {
    compile 'gun0912.ted:tedonactivityresult-rx1:x.y.z'
}
```
[ ![Download](https://api.bintray.com/packages/tkdrnjs0912/maven/tedonactivityresult-rx2/images/download.svg) ](https://bintray.com/tkdrnjs0912/maven/tedonactivityresult-rx2/_latestVersion)
## RxJava2
```java
dependencies {
    compile 'gun0912.ted:tedonactivityresult-rx2:x.y.z'
}
```

## Normal
Even if you don't use RxJava, you can use this library.
<br><br>
[ ![Download](https://api.bintray.com/packages/tkdrnjs0912/maven/tedonactivityresult/images/download.svg) ](https://bintray.com/tkdrnjs0912/maven/tedonactivityresult/_latestVersion)
```java
dependencies {
    compile 'gun0912.ted:tedonactivityresult:x.y.z'
}
```

<br><br><br><br>

# How to use

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
