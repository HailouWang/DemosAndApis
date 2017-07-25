# DemosForApi
ApiDemos 框架，根据Activity 名称，自动加载测试界面

# 一、简介

这是一个和Demos或者Api展示程序相关的Android Module。

依靠Activity的label名称管理```页面展示```、```页面跳转```等。

通过配置Activity Label，将展示、跳转交给Module，将更多的精力放在ApiSample的编写。

# 二、参考如下必要的配置步骤：

## 1、新建Android 工程。
[Android Studio 创建Android项目](http://www.jianshu.com/p/8ea262166fd1)

## 2、克隆如下项目到任意目录，建议在工程目录。

```
ifei$: cd 工程目录
ifei$: git clone https://github.com/HailouWang/DemosAndApis.git
```
目录中会产生一个DemosAndApis的文件夹。

## 3、加载Androd Module到工程中。
- 3.1、如果不在工程目录，需要将模块Import 进来。
  - 3.1、点击 File > New > Import Module。
  - 3.2、输入库模块目录的位置，然后点击 Finish。

- 3.2、工程gradle配置
在工程的settings.gradle中，增加Module(demosandapis)配置：

```
include ':app',':demosandapis'
```

- 3.3、app目录下Gradle配置
在app目录下的build.gradle中，增加Module(demosandapis)编译依赖：

```
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
...
    compile project(":demosandapis") 
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

