# AnimTextView (below chart)
#传入数据,执行动画 （下面有效果图）修改分支


# 简单示例

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




# ScreenShot

![Image][1]


[1]: http://img.blog.csdn.net/20140210222131968
