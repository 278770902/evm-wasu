package com.evmtv.cloudvideo.common.view;

import android.os.Bundle;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.presenter.base.BaseActivity;
import com.evmtv.cloudvideo.common.view.tool.FragmentTransactionUtils;

public class WeChatActivity extends BaseActivity {

    FragmentTransactionUtils.TransactionOneUtils transactionOneUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_chat);
        transactionOneUtils = new FragmentTransactionUtils(this, R.id.fragmentViewID)
                .OneTool("com.evmtv.cloudvideo.common.view.fragment.WeChatFragment", getSupportFragmentManager());
        transactionOneUtils.show();
    }
}
