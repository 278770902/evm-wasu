package com.evmtv.cloudvideo.common.view.monitor;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.evmtv.cloudvideo.common.MainApplication;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.presenter.base.BaseFragment;
import com.evmtv.cloudvideo.common.presenter.monitor.KanJiaBaoPresenter;
import com.evmtv.cloudvideo.common.presenter.monitor.KanJiaBaoPresenterImpl;
import com.evmtv.cloudvideo.common.view.action.ActionName;


/**
 * A simple {@link Fragment} subclass.
 */
public class KanJiaBaoMainFragment extends BaseFragment implements View.OnClickListener {

    private ImageButton kanJiaBaoMainScanViewID, kanjiabaoMainBackLookBtnID, kanJiaBaoMainMoreBtnID, kanJiaBaoMainSetting;
    private TabLayout KanJiaBaoUserNavigationViewID;
    private RecyclerView kanJiaBaoInfoRecyclerViewID;
    private KanJiaBaoPresenter presenter;

    public KanJiaBaoMainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kan_jia_bao_main, container, false);
    }

    @Override
    protected void initView() {
        presenter = new KanJiaBaoPresenterImpl(getContext());
        presenter.InitDisplay(KanJiaBaoUserNavigationViewID, kanJiaBaoInfoRecyclerViewID);
        kanJiaBaoMainScanViewID.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.kanJiaBaoMainScanViewID:
                Intent intent = new Intent();
                intent.setAction(ActionName.ACTIVITY_ACTION_SCAN);
                intent.setPackage(MainApplication.initContext.getAppPackageName());
                startActivity(intent);
                break;
        }
    }
}
