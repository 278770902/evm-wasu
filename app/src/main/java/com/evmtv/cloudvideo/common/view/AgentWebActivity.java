package com.evmtv.cloudvideo.common.view;

import android.os.Bundle;
import android.view.View;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.local.IntentLocalBean;
import com.evmtv.cloudvideo.common.presenter.base.BaseAgentActivity;
import com.evmtv.cloudvideo.common.presenter.web.EWebViewPresenterImpl;

public class AgentWebActivity extends BaseAgentActivity {

    public static final String EXTRA_VALUE = "value";
    private View AgentWebViewID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_web);
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentLocalBean webPageEntity = getIntent().getParcelableExtra(EXTRA_VALUE);
        if (webPageEntity == null)
            return;
        presenter = new EWebViewPresenterImpl(this, title);
        presenter.attributeSetting(AgentWebViewID);
        presenter.loadPage(webPageEntity.getUrl());
//        presenter.loadPage("http://60.255.58.6:39005/cloudVideo12");
    }


}
