# AnimTextView (below chart)
#传入数据,执行动画 （下面有效果图）


# ScreenShot

![Image][1]


[1]: http://img.blog.csdn.net/20151214223641061


# 简单示例


# 代码:

    private void initView() {
        mTvBig = (AnimTextView) findViewById(R.id.mTvBig);
        mTvSmall = (AnimTextView) findViewById(R.id.mTvSmall);
        mTvBg = (AnimTextView) findViewById(R.id.mTvBg);
    }

    private void initData() {
        mTvBig.setText("2015","2016");

        mTvSmall.setText("2016",true);

        mTvBg.setText("2016",true);
    }
    
    
# xml

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:tools="http://schemas.android.com/tools"
                android:layout_width="match_parent"
                android:layout_height="match_parent">

    <Button
        android:id="@+id/mBtn"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Start Anim"
        android:onClick="onClick"
        android:layout_alignParentBottom="true"
        android:layout_marginBottom="10dp"
        android:layout_centerHorizontal="true"/>

    <duguang.animtextview.AnimTextView
        android:id="@+id/mTvBig"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="60dp"
        android:minWidth="88dp"
        android:minHeight="77dp"
        android:layout_marginTop="70dp"
        android:layout_centerHorizontal="true"
        android:background="@drawable/bg_lesson_head_cont"/>

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/mTvBig"
        android:gravity="center_horizontal"
        android:layout_centerHorizontal="true"
        android:text="动画模式一:开启新数据替换老数据的动画\n背景是图片"/>

    <duguang.animtextview.AnimTextView
        android:id="@+id/mTvSmall"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="30dp"
        android:layout_below="@id/mTvBig"
        android:layout_marginTop="70dp"
        android:layout_centerHorizontal="true"
        android:background="@drawable/bg_lesson_head_cont"/>
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/mTvSmall"
        android:gravity="center_horizontal"
        android:layout_centerHorizontal="true"
        android:text="动画模式二:开启递增动画\n背景是图片"/>

    <duguang.animtextview.AnimTextView
        android:id="@+id/mTvBg"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/mTvSmall"
        android:layout_marginTop="70dp"
        android:textSize="45dp"
        android:layout_centerHorizontal="true"
        android:background="@android:color/white"/>

    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_below="@id/mTvBg"
        android:gravity="center_horizontal"
        android:layout_centerHorizontal="true"
        android:text="动画模式二:开启递增动画\n背景是color"/>

</RelativeLayout>


