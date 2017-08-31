# Getting Started Developing

# 一、简介

这是一个和Demos或者Api展示程序相关的Android Module。

依靠Activity的label名称管理```页面展示```、```页面跳转```等。

通过配置Activity Label，将展示、跳转交给Module，将更多的精力放在ApiSample的编写。

备注：参考谷歌Andriod ApiDemos源代码~

# 二、参考如下必要的配置步骤：

## 1、新建Android 工程。
[Android Studio 创建Android项目](http://www.jianshu.com/p/8ea262166fd1)

## 2、配置模块

在app模块的build.gradle中，添加配置如下：

```
dependencies {
    compile fileTree(include: ['*.jar'], dir: 'libs')
...
    compile 'com.github.hailouwang:DemosAndApis:1.0.3'
}
```

# 三、使用配置
## 1、配置app工程内的activity，增加如下```Category```:[hailouwang.demosforapi.intent.category.DEMOS_API]。

```
        <activity android:name=".MainActivity"
            android:label="app/MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <!--<category android:name="android.intent.category.LAUNCHER" />-->
                <category android:name="hailouwang.demosforapi.intent.category.DEMOS_API" />
            </intent-filter>
        </activity>
```

> 注意：app工程内，不需要Launcher配置。

## 2、配置Activity label。例如：
> android:label="I/MainActivityA"
> android:label="II/MainActivityB"
> android:label="III/MainActivityC"

附：
```
<activity-alias
    android:name="MainActivityA"
    android:label="I/MainActivityA"
    android:targetActivity=".MainActivity">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="hailouwang.demosforapi.intent.category.DEMOS_API" />
    </intent-filter>
</activity-alias>
<activity-alias
    android:name="MainActivityB"
    android:label="II/MainActivityB"
    android:targetActivity=".MainActivity">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />

        <category android:name="hailouwang.demosforapi.intent.category.DEMOS_API" />
    </intent-filter>
</activity-alias>
<activity-alias
    android:name="MainActivityC"
    android:label="III/MainActivityC"
    android:targetActivity=".MainActivity">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />

        <category android:name="hailouwang.demosforapi.intent.category.DEMOS_API" />
    </intent-filter>
</activity-alias>
```
> **注：**为方便演示,避免创建Activity，本例子使用了activity-alias，但与activity-alias或activity无关，只与Activity Label有关。

# 四、打赏

请扫描下方的二维码随意打赏，要是能打赏个 10.24，要是能打赏个 10.24 就太👍了。

![](https://github.com/HailouWang/testUrl/blob/master/erweima.png)
