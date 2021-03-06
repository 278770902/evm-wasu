package com.evmtv.cloudvideo.common.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.utils.toast.ToastUtil;
import com.evmtv.cloudvideo.common.view.tool.XLog;
import com.evmtv.util.view.EvmPlayerView;

public class VideoService extends Service implements View.OnTouchListener, View.OnClickListener {
    WindowManager.LayoutParams wmParams;
    //创建浮动窗口设置布局参数的对象
    WindowManager mWindowManager;
    private LinearLayout mFloatLayout;
    private String TAG = "VideoService";
    private EvmPlayerView playerView;

    @Override
    public void onCreate() {
        super.onCreate();
        createFloatView();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void createFloatView() {
        wmParams = new WindowManager.LayoutParams();
        //获取的是WindowManagerImpl.CompatModeWrapper
        mWindowManager = (WindowManager) getApplication().getSystemService(getApplication().WINDOW_SERVICE);
        XLog.i(TAG, "mWindowManager--->" + mWindowManager);
        //设置window type
        wmParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        //设置图片格式，效果为背景透明
        wmParams.format = PixelFormat.RGBA_8888;
        //设置浮动窗口不可聚焦（实现操作除浮动窗口外的其他可见窗口的操作）
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        //调整悬浮窗显示的停靠位置为左侧置顶
        wmParams.gravity = Gravity.LEFT | Gravity.TOP;
        // 以屏幕左上角为原点，设置x、y初始值，相对于gravity
        wmParams.x = 0;
        wmParams.y = 0;

        //设置悬浮窗口长宽数据
        wmParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wmParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
		 /*// 设置悬浮窗口长宽数据
        wmParams.width = 200;
        wmParams.height = 80;*/

        LayoutInflater inflater = LayoutInflater.from(getApplication());
        //获取浮动窗口视图所在布局
        mFloatLayout = (LinearLayout) inflater.inflate(R.layout.item_service_video, null);
        //添加mFloatLayout
        mWindowManager.addView(mFloatLayout, wmParams);
        //浮动窗口按钮
        playerView = (EvmPlayerView) mFloatLayout.findViewById(R.id.evmVideoViewId);

        mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED), View.MeasureSpec
                .makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        XLog.i(TAG, "Width/2--->" + playerView.getMeasuredWidth() / 2);
        XLog.i(TAG, "Height/2--->" + playerView.getMeasuredHeight() / 2);
        //设置监听浮动窗口的触摸移动
        playerView.setOnTouchListener(this::onTouch);
        playerView.setOnClickListener(this::onClick);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        // TODO Auto-generated method stub
        //getRawX是触摸位置相对于屏幕的坐标，getX是相对于按钮的坐标
        wmParams.x = (int) event.getRawX() - playerView.getMeasuredWidth() / 2;
        XLog.i(TAG, "RawX" + event.getRawX(), "X" + event.getX());
        //减25为状态栏的高度
        wmParams.y = (int) event.getRawY() - playerView.getMeasuredHeight() / 2 - 25;
        XLog.i(TAG, "RawY" + event.getRawY(), "Y" + event.getY());
        //刷新
        mWindowManager.updateViewLayout(mFloatLayout, wmParams);
        return false;  //此处必须返回false，否则OnClickListener获取不到监听
    }

    @Override
    public void onClick(View v) {
        ToastUtil.setToast("onClick");
    }
}
