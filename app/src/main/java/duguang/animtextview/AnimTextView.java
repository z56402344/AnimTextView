package duguang.animtextview;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.Scroller;
import android.widget.TextView;


/**
 * Created by duguang on 15-12-8.
 */
public class AnimTextView extends TextView {

    private static final String TAG = AnimTextView.class.getSimpleName();
    private float mFold = 1.3f;//其他列的动画时常的倍数
    private float mW;//单列的宽
    private int mLayoutH;//StaticLayout高
    private float mSpacingmult = 1.0f;//StaticLayout行间距的倍数1.0为正常值

    private int mAddMax = 3;//控制递增动画,递增的量
    private ArrayList<String> mStrList = new ArrayList<String>();//生成的String集合
    private ArrayList<Scroller> mScrList = new ArrayList<Scroller>();//滚动类集合
    private ArrayList<StaticLayout> mLayoutList = new ArrayList<StaticLayout>();//绘制String的Layout集合

    public AnimTextView(Context context) {
        super(context);
    }

    public AnimTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 开启新数据替换老数据的动画
     * @param oldNum 老数据
     * @param newNum 新数据
     */
    public void setText(String oldNum, String newNum) {
        super.setText(newNum);
        if (TextUtils.isEmpty(oldNum) || TextUtils.isEmpty(newNum)) {
            return;
        }
        clearList();
        makeUnequalData(newNum, oldNum);
    }

    private void makeUnequalData(String oldNum, String newNum){
        StringBuilder sb = new StringBuilder();;
        int l1 = oldNum.length()-1;
        int l2 = newNum.length()-1;
        for (; l1>-1 ||l2 > -1; --l1,--l2) {
            sb.setLength(0);
            mStrList.add(0,sb.append(l1>-1?oldNum.charAt(l1):'0').append("\n").append(l2>-1?newNum.charAt(l2):'0').toString());
        }
    }

    /**
     * 开启递增动画
     * 例:传入值528,会生成三组String,分别是:
     * "5\n6\n7\n8","2\n3\n4\n5","8\n9\n0\n1"
     * 这里的规则是按照mAddMax的条件,生成一串递增+1的字符串
     */
    public void setText(String content, boolean isAnim) {
        super.setText(content);
        clearList();
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            makeData(Integer.parseInt(c + ""));
        }
    }

    public void startAnim() {
        if (mStrList.size() == 0)return;
        int mDur = 1500;//第一列动画的时间基数
        mW = (float) ((getWidth() / mStrList.size())* 1.25);
        mLayoutH = 0;
        TextPaint p = getPaint();
        p.setColor(getCurrentTextColor());
        for (int i = 0; i < mStrList.size(); i++) {
            if (!TextUtils.isEmpty(mStrList.get(i))) {
                StaticLayout layout = new StaticLayout(mStrList.get(i), p, (int) mW, Layout.Alignment.ALIGN_CENTER, mSpacingmult, 0.0F, true);
                mLayoutList.add(layout);
                Scroller scroller = new Scroller(getContext());
                mLayoutH = layout.getHeight();
                scroller.startScroll(0, -mLayoutH, 0, mLayoutH, mDur);
                mScrList.add(scroller);
                mDur = (int) (mDur * mFold);
            }
        }
    }

    private void clearList(){
        mStrList.clear();
        mScrList.clear();
        mLayoutList.clear();
    }

    //按照字体大小+includpad的和返回控件的大小(消除background是大图片的时候是按照图片大小)
    @Override
    protected int getSuggestedMinimumHeight() {
        return Build.VERSION.SDK_INT >16?getMinimumHeight():0;
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return Build.VERSION.SDK_INT >16?getMinimumWidth():0;
    }

    private void makeData(int data) {
        StringBuilder sb = new StringBuilder();
        sb.append(data);
        for (int i = 1; i <= mAddMax; i++) {
            int num = data + i;
            if (num > 9) num = num % 10;
            sb.append("\n" + num);
        }
        mStrList.add(sb.toString());
    }

    private String mLast = null;
    @Override
    protected void onDraw(Canvas canvas) {
        CharSequence str = getText();
        if (str == null)return;
        if (str != mLast){
            mLast = str.toString();
            startAnim();
            postInvalidate();
            return;
        }
        if (mStrList.size() == 0 ||mScrList.size() == 0 || mLayoutList.size() == 0){
            super.onDraw(canvas);
            return;
        }
        try {
            boolean invalidate = false;
            for (int i = 0; i < mStrList.size(); i++) {
                canvas.save();
                canvas.translate(i * 3 * mW / 4, 0);
                Scroller scroller = mScrList.get(i);
                if (scroller != null && scroller.computeScrollOffset()) {
                    canvas.translate(0, scroller.getCurrY());
                    invalidate = true;
                }
                StaticLayout layout = mLayoutList.get(i);
                if (layout != null) layout.draw(canvas);
                canvas.restore();
            }
            if (invalidate) postInvalidate();
        }catch (Exception e){
            e.printStackTrace();
        }

        //super.onDraw(canvas);
    }

    public void clearLast(){
        mLast = null;
    }
}
