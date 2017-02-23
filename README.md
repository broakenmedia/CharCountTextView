# CharCountTextView

[![](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
<a target="_blank" href="https://developer.android.com/reference/android/os/Build.VERSION_CODES.html#ICE_CREAM_SANDWICH"><img src="https://img.shields.io/badge/API-14%2B-blue.svg?style=flat" alt="API" /></a> 

A Twitter style post length counter which can be bound to an EditText and provide callbacks when hitting a max character count.

Once bound to an EditText the characters remaining will go down as the user types. If the user goes over the max limit
you will get notified and the view will show how much they are over (-5 etc.) where you can disable UI accordingly.

**Note:** When the user has 10% or less characters remaining the text color will change to use the "exceededTextColor" 
below (Default: RED)

Normal             |  Count Exceeded
:-------------------------:|:-------------------------:
![](http://i.imgur.com/VgpFwfy.png)  |  ![](http://i.imgur.com/A3NK4wC.png)


##Install

Add this to your project build.gradle
``` gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

Add this to your module build.gradle

``` gradle
dependencies {
    compile 'com.github.xbroak:CharCountTextView:v1.0'
}
```
###Usage

Add the following xml to your layout xml

```  xml
<com.wafflecopter.charcounttextview.CharCountTextView
    android:id="@+id/tvTextCounter"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:maxCharacters="150" //Optional - Defaults to 150 (Twitter Emulation)
    app:exceededTextColor="#ff0000" //Optional - Defaults to RED
/> 
```

Then in your activity/fragment:

```java
CharCountTextView tvCharCount = (CharCountTextView) findViewById(R.id.tvTextCounter);
tvCharCount.setEditText(EDITTEXT_I_WISH_TO_COUNT_FOR);
tvCharCount.setCharCountChangedListener(new CharCountTextView.CharCountChangedListener() {
    @Override
    public void onCountChanged(int countRemaining, boolean hasExceededLimit) {
        //Enable disable tweet button for example
    }
});
```

### Additonal Methods
To set the max no. of characters and character count exceeded text color dynamically:

```java
 - setMaxCharacters(int maxCharacters); 
 - setExceededTextColor(int colorRes); //Font color will change to this when count has 10% or less remaining
```


## License

```
Copyright (c) BroakenMedia 2016-2017

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
