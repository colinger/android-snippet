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