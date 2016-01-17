###1. 实现用户按下按钮的效果：
`drawable`文件下新建`selector`文件可以实现手指按下效果, 否则用户按下按钮的时候感觉比较生硬，代码如下：

```
<?xml version="1.0" encoding="UTF-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@mipmap/xxx_press" android:state_focused="true" />
    <item android:drawable="@mipmap/xxx_press" android:state_pressed="true" />
    <item android:drawable="@mipmap/xxx_press" android:state_selected="true" />
    <item android:drawable="@mipmap/xxx_normal" />
</selector>
```


###2. 实现自定义CheckBox效果：
首先新建`selector`文件** selector_checkbox_style.xml **，内容如下：

```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:drawable="@mipmap/ic_checked" android:state_checked="true" />
    <item android:drawable="@mipmap/ic_normal" android:state_checked="false" />
    <item android:drawable="@mipmap/ic_normal" />
</selector>
```
然后在`style.xml文`件中添加样式：

```
 <style name="CustomCheckboxTheme" parent="@android:style/Widget.CompoundButton.CheckBox">
   <item name="android:button">@drawable/selector_checkbox_style</item>
 </style>
```

在checkbox布局文件可以使用该style了

```
 <CheckBox
     android:id="@+id/cb_default"
     style="@style/CustomCheckboxTheme"
     android:layout_width="wrap_content"
     android:layout_height="wrap_content"
     android:background="@null"/>

```

###3. 实现灰色不可能效果
在我们的实际开发中常常遇到 按钮有`圆角`、`按下效果`、`不可能`效果。这个时候该怎么通过drawable方式来实现呢？线上代码 然后分析：

```
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_enabled="false">
        <shape>
            <solid android:color="" />
            <stroke android:width="1dp" android:color="" />
            <corners android:radius="1dp" />
        </shape>
    </item>
    
    <item android:state_enabled="true">
        <shape>
            <solid android:color="#ff9900" />
            <stroke android:width="1dp" android:color="#ff9900" />
            <corners android:radius="2dp" />
        </shape>
    </item>
    
    <item>
        <shape>
            <solid android:color="#ff9900" />
            <stroke android:width="1dp" android:color="#ff9900" />
            <corners android:radius="2dp" />
        </shape>
    </item>

</selector>

```
- ** android:state_enabled="false" ** 表示不可用状态下的效果，一般设置为灰色。
- ** android:state_enabled="true" ** 表示可用状态下的效果。
可通过布局文件、Java code设置是否可用: `android:enabled="true/false"`; `widget.setEnabled(true/false)`

- 最后还要有个** 默认状态 **就是`<item>`标签里不加状态属性。

- `<shape> `标签顾名思义表示** 形状 **,可以通过android:shape指定形状，可指定的形状有：
	** rectangle **、 ** oval **、** line **、** ring **.

- `<solid>` 标签表示形状里填充的颜色
- `<stroke>` 用来画边框的画笔 可指定画笔的 **颜色** 和  **宽度**。
- `<corners>` 用来指定四周的**圆角**角度











