package com.evmtv.cloudvideo.common.presenter.monitor;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.presenter.base.BaseActivity;
import com.evmtv.cloudvideo.common.presenter.base.BaseVideoActivity;
import com.evmtv.cloudvideo.common.presenter.monitor.live.MonitorLiveControllerView;
import com.evmtv.cloudvideo.common.presenter.monitor.live.MonitorLiveEntity;
import com.evmtv.cloudvideo.common.presenter.monitor.live.MonitorLivePresenter;
import com.evmtv.cloudvideo.common.presenter.monitor.live.MonitorLivePresenterImpl;
import com.evmtv.cloudvideo.common.presenter.monitor.live.MonitorLiveTool;
import com.evmtv.cloudvideo.common.utils.Text;
import com.evmtv.cloudvideo.common.utils.permissions.PermissionsUtils;
import com.evmtv.cloudvideo.common.view.tool.EvmControllerPlayerViewA;

public class MonitorLiveActivity extends BaseVideoActivity implements View.OnClickListener, PermissionsUtils.IPermissionsResult {
    private ImageView backViewID;
    private TextView titleViewID;
    private MonitorLivePresenter presenter;
    private EvmControllerPlayerViewA videoViewID;
    private TabLayout monitorLiveNavigationViewID;
    private ConstraintLayout monitorLiveLayoutViewID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_live);
        initVideoPlay();
    }

    private void initVideoPlay() {
        MonitorLiveTool.getInstance(this).checkPermissions(this, this);
        backViewID.setOnClickListener(this);
        mediaInter = new MonitorLiveControllerView(this, "", "");
        MonitorLiveEntity.getInstance().setVideoViewID(videoViewID);
        presenter = new MonitorLivePresenterImpl(this);
        presenter.validateCredentials((MonitorLiveControllerView) mediaInter);
        presenter.initNavigation(monitorLiveNavigationViewID, monitorLiveLayoutViewID);
        titleViewID.setText(MonitorLiveEntity.getInstance().getDeviceName());
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MonitorLiveTool.getInstance(this).onRequestPermissionsResult(this,requestCode,permissions,grantResults);
    }

    @Override
    public void passPermissions() {
        MonitorLiveEntity.getInstance().setPassPermissionRecord(true);
    }

    @Override
    public void forbidPermissions() {
        MonitorLiveEntity.getInstance().setPassPermissionRecord(false);
    }
}
