package com.example.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class InputNumberView extends RelativeLayout {

    private static final String TAG = "InputNumberView";
    private int mCurrentNumber = 0;
    private TextView mMinusBtn;

    private EditText mValueEdit;
    private TextView mPlusBtn;
    private OnNumberChangeListener mOnNumberChangeListener = null;
    private int mMax;
    private int mMin;
    private int mStep;
    private int mDefaultValue;
    private boolean mDisable;
    private int mBtnBgRes;

    public InputNumberView(Context context) {
        this(context, null);
    }

    public InputNumberView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InputNumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取 attrs.xml 中自定义的属性
        initAttrs(context, attrs);
        //初始化视图
        initView(context);
        //设置事件
        setUpEvent();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.InputNumberView);
        mMax = a.getInteger(R.styleable.InputNumberView_max, 999);
        mMin = a.getInteger(R.styleable.InputNumberView_min, -999);
        mStep = a.getInteger(R.styleable.InputNumberView_step, 1);
        mDefaultValue = a.getInteger(R.styleable.InputNumberView_defaultValue, 0);
        this.mCurrentNumber = mDefaultValue;
        mDisable = a.getBoolean(R.styleable.InputNumberView_disable, false);
        mBtnBgRes = a.getResourceId(R.styleable.InputNumberView_btnBackground, -1);
        a.recycle();
        //打个日志看获取到了没有
        Log.d(TAG, "mMax --->" + mMax);
        Log.d(TAG, "mMin --->" + mMin);
        Log.d(TAG, "mStep --->" + mStep);
        Log.d(TAG, "mDefaultValue --->" + mDefaultValue);
        Log.d(TAG, "mDisable --->" + mDisable);
        Log.d(TAG, "mBtnBgRes --->" + mBtnBgRes);
    }

    private void setUpEvent() {
        mMinusBtn.setOnClickListener(v -> {
            mPlusBtn.setEnabled(true);
            mCurrentNumber -= mStep;
            if(mCurrentNumber <= mMin) {
                v.setEnabled(false);
                mCurrentNumber = mMin;
            }
            updateText();
        });

        mPlusBtn.setOnClickListener(v -> {
            mMinusBtn.setEnabled(true);
            mCurrentNumber += mStep;
            if(mCurrentNumber >= mMax) {
                v.setEnabled(false);
                mCurrentNumber = mMax;
            }
            updateText();
        });
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.input_number_view, this, false);
        addView(view);
        mMinusBtn = findViewById(R.id.minus_btn);
        mValueEdit = findViewById(R.id.value_edit);
        mPlusBtn = findViewById(R.id.plus_btn);
        //给 mValueEdit 赋值之后，再执行 updateText() 方法，防止 mValueEdit 为 null，发生空指针异常
        //初始化控件值
        updateText();
        //初始化 "-" 和 "+" 是否可与用户交互
        mMinusBtn.setEnabled(!mDisable);
        mPlusBtn.setEnabled(!mDisable);
    }

    private void updateText() {
        mValueEdit.setText(String.valueOf(mCurrentNumber));
        if(mOnNumberChangeListener != null) {
            mOnNumberChangeListener.onNumberChange(this.mCurrentNumber);
        }
    }

    public void setOnNumberChangeListener(OnNumberChangeListener listener) {
        this.mOnNumberChangeListener = listener;
    }

    public interface OnNumberChangeListener {
        void onNumberChange(int value);
    }


    public int getmMax() {
        return mMax;
    }

    public void setmMax(int mMax) {
        this.mMax = mMax;
    }

    public int getmMin() {
        return mMin;
    }

    public void setmMin(int mMin) {
        this.mMin = mMin;
    }

    public int getmStep() {
        return mStep;
    }

    public void setmStep(int mStep) {
        this.mStep = mStep;
    }

    public int getmDefaultValue() {
        return mDefaultValue;
    }

    public void setmDefaultValue(int mDefaultValue) {
        this.mDefaultValue = mDefaultValue;
        this.mCurrentNumber = mDefaultValue;
        this.updateText();
    }

    public boolean ismDisable() {
        return mDisable;
    }

    public void setmDisable(boolean mDisable) {
        this.mDisable = mDisable;
    }

    public int getmBtnBgRes() {
        return mBtnBgRes;
    }

    public void setmBtnBgRes(int mBtnBgRes) {
        this.mBtnBgRes = mBtnBgRes;
    }
}


