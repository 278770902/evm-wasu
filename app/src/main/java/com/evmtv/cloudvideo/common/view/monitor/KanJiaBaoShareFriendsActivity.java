package com.evmtv.cloudvideo.common.view.monitor;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.presenter.base.BaseActivity;
import com.evmtv.cloudvideo.common.presenter.monitor.share.ShareFriendsPresenter;
import com.evmtv.cloudvideo.common.presenter.monitor.share.ShareFriendsPresenterImpl;

public class KanJiaBaoShareFriendsActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton backViewID;
    private ShareFriendsPresenter presenter;
    private RecyclerView shareFriendsRecyclerViewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kan_jia_bao_share_friends);
        initView();
    }

    private void initView() {
        presenter = new ShareFriendsPresenterImpl(this);
        presenter.InitDisplay(shareFriendsRecyclerViewId);
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
}
