# Android Gist - Android代码小片段

> 复用率非常高的代码。

这是从我工作的项目中分离出来的，复用率非常高的代码。

## DelaySwitchActivity

#### 作用

作闪屏Activity功能用。设定跳转目标和延时时间。

#### 使用

```java

    public class SplashActivity extends DelaySwitchActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // CONFIG !!!
            getConfig().target(TargetActivity.class).delayed(2000);
        }

    }

```

----

## AppWideBroadcast

#### 作用

应用内部发送广播，与全局广播相比，提高性能和广播效率

#### 使用

静态方法

----

## InnerFragment

#### 作用

基于Support V4 的 Fragment，封装了一些经常用到的参数的方法

#### 使用

继承即可