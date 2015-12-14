package duguang.animtextview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity implements View.OnClickListener{

    private AnimTextView mTvBig,mTvSmall,mTvBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        case R.id.mBtn:
            clearAnim();
            initData();
            break;
        default:

            break;
        }
    }

    public void clearAnim(){
        mTvBig.clearLast();
        mTvSmall.clearLast();
        mTvBg.clearLast();
    }
}
