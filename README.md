# Android Gist - Android代码小片段

> 复用率非常高的代码。

这是从我工作的项目中分离出来的，复用率非常高的代码。

## Dependency

Add repository

```groovy

    allprojects {
     repositories {
         // !!!! ADD THIS !!!!
         maven{ url 'http://oss.sonatype.org/content/groups/public/' }
     }
    }

```

Add dependency

```groovy

    dependencies {
        compile 'com.github.chenyoca:android-snippet:1.1'
    }

```


----

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

<<<<<<< HEAD
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

----

## SimpleAdapter

#### 作用

经常用于ListView、GridView等，通过指定ViewCreator来创建子视图，简化ListView等组合的使用。

#### 使用

```java

    ViewCreator<ValueObject> viewCreator = new ViewCreator(){
        ...
    };

    SimpleAdapter<ValueObject> adapter = new SimpleAdapter(activity.getLayoutInflater(), viewCreator);

    ListView listView = ....

    listView.setAdapter(adapter);

    List<ValueObject> dataSet = ...

    adapter.update(dataSet);
    adapter.notifyDataSetChanged();

```

----

## SimplePagerAdapter

#### 作用

用于ViewPager，同SimpleAdapter

#### 使用

同SimpleAdapter

### 使用

	大部分是工具类，以静态方法方式调用。详细见各个包的说明及示例。

## 工具包说明

Library需要添加Android support v4 jar包。

### Adapter

	Android各个组件使用的Adapter都包含大量需要实现的接口。
	本类包内的Adapter对这些接口进行简化，并将Adapter的View创建过程通过ViewBuilder代理接口转移到Adapter类外实现，实现组件逻辑与界面创建分离。

##### ConverViewAdapter

	使用convertView作View缓存的Adapter实现。
	Convert View的相关知识，可参考 http://www.cnblogs.com/over140/archive/2011/03/23/1991100.html

##### HolderAdapter

	实现HolderView缓存方法的Adapter。这个类并没完全实现Holder模式，只对顶级View进行Holder缓存。

##### HolderViewFiller

	快速填充GridView或者ListView的辅助类

##### NoCachedAdapter

	没有使用任务View缓存的Adapter。

##### SimpleCursorAdapter

	游标适配器

-----

### Common

	通用工具类

##### InputStreamUtil

	输入流转换工具。可将InputStream转换成Bitmap,BitmapDrawable,String,StringBuffer,Byte数组等。

##### JSONAbility

	可将Java Bean的各个属性输出成JSON格式字符串。

##### RandomUtil

	生成最大值范围的随机数，指定长度的随机字符串。

##### ReflectUtility

	反射工具类

##### RegexUtil

	一些常用正则表达式

##### UnitUtil

	数量单位生成

##### ViewDoubleClick

	View双击处理。

### database

	数据库辅助工具类

##### AsynchronousCursorQueryTask

	执行一个异步查询任务，查询完成后，对Adapter更换Cursor。

##### AsynchronousCursorTreeQueryTask

	执行一个异步查询任务，适用于树形控件，查询完成后，对Adapter更换Cursor。

-----

### Encrypt

	加密处理

##### Base64

	Base64 加密解密

##### HashEncrypt

	Hash类加密：MD5，SHA-1, SHA-256

-----

### Resource

##### BitmapFillet

    图片圆角处理

##### BitmapScale

    图片缩放处理

##### FileUtility

    文件处理工具类。如复制、移动、创建文件，获取文件名，文件后缀等。

##### ResourceReader

    对Android资源的读取处理。如，将Assets内的图片文件读取成图片，文本内容等。

##### SDCard

    SDCard处理

-----

### Fragment

##### InnerFragment

	继承自support v4的Fragment，扩展了View创建功能。

###### FragmentUtility

	隐藏指定Fragment

-----

### System

##### ActivityUtility

	使用最多的工具类，包含Activity常用设置方法。如全屏，去掉标题栏，显示Toast，隐藏/显示输入法，计算px与dp等方法。

##### ApkUtility

	Apk文件检测是否存在，安装指定APK文件

##### DoubleClickExit

	双击退出应用

##### Exlog

	调试必备！可以输入调试信息在哪一个类哪一行的辅助工具类。包括：取得当前代码所在方法名、输出调用方法链等方法。

##### NetworkUtility

	网络工具类

##### PhoneUtility

	电话

-----

### View

##### DelaySwitchActivity

	延时跳转Activity，用于启动界面。

##### ViewController

	就是ViewController

##### ViewPagerScheduler

	如果你需要类似JQuery slideshow等图片轮播组件，在Android上你可能会自己实现一个。
	但是，ViewPager不就是一个现成的SlideShow组件么？只需要添加自动轮播功能即可。
	那么，ViewPagerScheduler就可以为你自动轮播ViewPager啦！


## 开源协议 Apache License 2.0

The code of this project is released under the Apache License 2.0, see [LICENSE](https://github.com/chenyoca/async-http-connection-core/blob/master/LICENSE)
=======
```
>>>>>>> c15cede0d1f8aa5935c661fd17905374ea7df77b
