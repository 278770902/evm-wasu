package com.evmtv.cloudvideo.common.view.monitor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.presenter.base.BaseActivity;
import com.evmtv.cloudvideo.common.presenter.monitor.live.MorePlayPresenter;
import com.evmtv.cloudvideo.common.presenter.monitor.live.MorePlayPresenterImpl;
import com.evmtv.cloudvideo.common.presenter.monitor.live.multi.MorePlayTool;

public class MorePlayActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView morePlayCameraListRecyclerViewId;
    private MorePlayPresenter presenter;
    private ImageButton backViewID;

    //validateCredentials
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_more_play);
        initView();
    }

    private void initView() {
        presenter = new MorePlayPresenterImpl(this);
        presenter.validateCredentials(morePlayCameraListRecyclerViewId);
        backViewID.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backViewID:
                finish();
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        MorePlayTool.getInstance(this).stopAllVideoPlay();
    }
}
