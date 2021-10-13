package com.evmtv.cloudvideo.common.view.tool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.evmtv.cloudvideo.common.R;

/**
 * Created by Administrator on 2019/12/18.
 */

@SuppressLint("AppCompatCustomView")
public class XEditText extends EditText {

    private DrawableLeftListener mLeftListener;
    private DrawableRightListener mRightListener;

    private boolean visiblePass;
    final int DRAWABLE_LEFT = 0;
    final int DRAWABLE_TOP = 1;
    final int DRAWABLE_RIGHT = 2;
    final int DRAWABLE_BOTTOM = 3;

    @SuppressLint("NewApi")
    public XEditText(Context context, AttributeSet attrs, int defStyleAttr,
                     int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public XEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public XEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public XEditText(Context context) {
        super(context);
    }

    public void setDrawableLeftListener(DrawableLeftListener listener) {
        this.mLeftListener = listener;
    }

    public void setDrawableRightListener(DrawableRightListener listener) {
        this.mRightListener = listener;
    }

    public interface DrawableLeftListener {
        public void onDrawableLeftClick(View view);
    }

    public interface DrawableRightListener {
        public void onDrawableRightClick(View view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
                NewPassRightListener();
                if (mRightListener != null) {
                    Drawable drawableRight = getCompoundDrawables()[DRAWABLE_RIGHT];
                    if (drawableRight != null && event.getRawX() >= (getRight() - drawableRight.getBounds().width())) {
                        mRightListener.onDrawableRightClick(this);
                        return true;
                    }
                }

                if (mLeftListener != null) {
                    Drawable drawableLeft = getCompoundDrawables()[DRAWABLE_LEFT];
                    if (drawableLeft != null && event.getRawX() <= (getLeft() + drawableLeft.getBounds().width())) {
                        mLeftListener.onDrawableLeftClick(this);
                        return true;
                    }
                }
                break;
        }

        return super.onTouchEvent(event);
    }

    private void NewPassRightListener() {
        if (mRightListener != null)
            return;
        setDrawableRightListener(new DrawableRightListener() {
            @Override
            public void onDrawableRightClick(View view) {
                if (!visiblePass) {
                    //设置EditText文本为可见的
                    setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    visiblePass = true;
                    // 使用代码设置drawableleft
                    Drawable drawable = getResources().getDrawable(
                            R.drawable.icon_message_off);
                    // / 这一步必须要做,否则不会显示.
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                            drawable.getMinimumHeight());

//                    Drawable drawableLeft = getResources().getDrawable(
//                            R.drawable.sc_icon_message);
//                    drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(),
//                            drawableLeft.getMinimumHeight());
//                    setCompoundDrawables(drawableLeft, null, drawable, null);
                } else {
                    //设置EditText文本为隐藏的
                    setTransformationMethod(PasswordTransformationMethod.getInstance());
                    visiblePass = false;
                    // 使用代码设置drawableleft
                    Drawable drawable = getResources().getDrawable(
                            R.drawable.icon_message_on);
                    // / 这一步必须要做,否则不会显示.
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(),
                            drawable.getMinimumHeight());
//                    Drawable drawableLeft = getResources().getDrawable(
//                            R.drawable.sc_icon_message);
                    // / 这一步必须要做,否则不会显示.
//                    drawableLeft.setBounds(0, 0, drawableLeft.getMinimumWidth(),
//                            drawableLeft.getMinimumHeight());
//                    setCompoundDrawables(drawableLeft, null, drawable, null);
                }
                postInvalidate();
                //切换后将EditText光标置于末尾
                CharSequence charSequence = getText();
                if (charSequence instanceof Spannable) {
                    Spannable spanText = (Spannable) charSequence;
                    Selection.setSelection(spanText, charSequence.length());
                }
            }
        });
    }

}
