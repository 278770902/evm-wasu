package com.evmtv.cloudvideo.common.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.SendMessageEntity;
import com.evmtv.cloudvideo.common.model.local.LocalType;
import com.evmtv.cloudvideo.common.model.local.MySettingInfoEntity;
import com.evmtv.cloudvideo.common.persenter.login.LoginModelImpl;
import com.evmtv.cloudvideo.common.persenter.setting.SettingPresenterImpl;
import com.evmtv.cloudvideo.common.presenter.LocalTypeImpl;
import com.evmtv.cloudvideo.common.presenter.base.BaseMainTabFragment;
import com.evmtv.cloudvideo.common.presenter.login.LoginModel;
import com.evmtv.cloudvideo.common.presenter.login.BaseLoginModelImpl;
import com.evmtv.cloudvideo.common.presenter.setting.MessageRefreshListener;
import com.evmtv.cloudvideo.common.presenter.setting.OnSettingItemSelectedListener;
import com.evmtv.cloudvideo.common.presenter.setting.OnSettingTwoItemSelectedListener;
import com.evmtv.cloudvideo.common.presenter.setting.SettingPresenter;
import com.evmtv.cloudvideo.common.utils.ResConversionUtil;
import com.evmtv.cloudvideo.common.view.PlayVideoActivity;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.tool.GlideCircleTransform;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MyFragment extends BaseMainTabFragment implements OnSettingItemSelectedListener, MessageRefreshListener, OnSettingTwoItemSelectedListener, View.OnClickListener {
    //    private RecyclerView MainSettingRecNavViewID;
    private LocalType localType;
    private LoginModel loginModel;
    private SettingPresenter presenter;
    private LinearLayout mySettingBgViewID;
    private ImageButton mySettingIconViewID;
    private ListView MainSettingRecNavViewID;
    private TabLayout MainSettingTitleNavViewID;
    private TextView mySettingNameViewID, mySettingEditIconViewID, mySettingUserStateViewID;

    public MyFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    protected void initView() {
        presenter = new SettingPresenterImpl(getActivity(), beanItem, this, this);
        loginModel = new LoginModelImpl();
        presenter.InitDisplay(MainSettingTitleNavViewID, MainSettingRecNavViewID);
        localType = new LocalTypeImpl(getActivity());
        mySettingEditIconViewID.setOnClickListener(this);
        mySettingBgViewID.setBackgroundResource(presenter.getBackgroundIcon() == null ?
                R.drawable.my_setting_bg : ResConversionUtil.getDrawableOrMipmapId(presenter.getBackgroundIcon()));
        initUserInfo();
    }

    private void initUserInfo() {
        mySettingNameViewID.setText(SharedPreferencesUtil.getInstance().getUserName());
        mySettingUserStateViewID.setText(presenter.getUserState());
        showLoad(SharedPreferencesUtil.getInstance().getUserIcon());
    }

    @Override
    public void onResume() {
        super.onResume();
        mySettingNameViewID.setText(SharedPreferencesUtil.getInstance().getUserName());
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (this != null && !hidden) {
            presenter.UpdatePersonalInfo(this::MessageRefresh);
        }
    }

    @Override
    public void onNavigationItemSelected(MySettingInfoEntity.LevelOneBean bean) {
        localType.use(null, bean.getContent().getStartType(), bean.getContent().getValue());
    }

    @Override
    public void onNavigationItemSelected(MySettingInfoEntity.LevelTwoBean bean) {
        localType.use(null, bean.getContent().getStartType(), bean.getContent().getValue());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mySettingEditIconViewID:
                presenter.startEditPersonalActivity();
//                Intent intent = new Intent(getActivity(), PlayVideoActivity.class);
//                intent.putExtra("url", "http://www.bndtn.com:28088/vod/EVMTV_EVMTV_MOVIE-844FE306BD-20201022104642.m3u8?uid=123456&stype=app&sig=");
//                startActivity(intent);
                break;
        }
    }

    private void showLoad(String path) {
        Glide.with(getActivity())
                .load(path)
                .placeholder(R.drawable.my_setting_default_header)
                .transform(new GlideCircleTransform(getContext()))
                .into(mySettingIconViewID);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMsg(SendMessageEntity msg) {
        if (msg.getMsgId() == SendMessageEntity.MsgId.SEND_EDIT_PERSONAL) {
            if (msg.getData() != null) {
                String data = (String) msg.getData();
                if (data != null && data.length() > 0) {
                    showLoad(data);
                    loginModel.saveUserInfoIO(SharedPreferencesUtil.getInstance().getUserLoginName()
                            , SharedPreferencesUtil.getInstance().getUserLoginPassWorld());
                }
            }
        }
    }

    @Override
    public void MessageRefresh() {
        initUserInfo();
    }
}
