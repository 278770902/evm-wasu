package com.evmtv.cloudvideo.common.view.monitor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.presenter.base.BaseActivity;

public class KanJiaBaoShareMainActivity extends BaseActivity implements View.OnClickListener {
    private ImageButton backViewID;
    private View shareFriendsViewId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kan_jia_bao_share_main);
        initView();
    }

    private void initView() {
        backViewID.setOnClickListener(this::onClick);
        shareFriendsViewId.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backViewID:
                finish();
                break;
            case R.id.shareFriendsViewId:
                startActivity(new Intent(KanJiaBaoShareMainActivity.this, KanJiaBaoShareFriendsActivity.class));
                break;
        }
    }
}
