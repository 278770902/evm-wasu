package com.evmtv.cloudvideo.common.presenter.monitor;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.evmtv.cloudvideo.common.http.OrderMethodInter;
import com.evmtv.cloudvideo.common.http.csm.CsmInteractive;
import com.evmtv.cloudvideo.common.http.ums.UMSInteractive;
import com.evmtv.cloudvideo.common.model.http.csm.AlarmDeviceStatusEntity;
import com.evmtv.cloudvideo.common.model.http.ums.BindUserEntity;
import com.evmtv.cloudvideo.common.model.http.ums.BindedUserInfoEntity;
import com.evmtv.cloudvideo.common.model.http.ums.CallTempletListEntity;
import com.evmtv.cloudvideo.common.model.http.ums.MonitorCameraInfoEntity;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;

public class KanJiaBaoPresenterImpl implements KanJiaBaoPresenter {

    private String TAG = "KanJiaBao";
    private Context mContext;


    public KanJiaBaoPresenterImpl(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void InitDisplay(Object view, RecyclerView recyclerView) {
        String guid = SharedPreferencesUtil.getInstance().getUserGuid(true);
        AppExecutors.getInstance().networkIOToMain(mContext,new OrderMethodInter() {
            BindedUserInfoEntity bindedUserInfoEntity;
            CallTempletListEntity callTempletListEntity;

            @Override
            public void IO() {
                String cameraShareJson = UMSInteractive.getInstance().getUserCameraShareDetail(guid);
                bindedUserInfoEntity = UserSTBTabTool.getInstance(mContext).parseObject(UMSInteractive.getInstance().getBindedUserInfo(guid));
                if (!UserSTBTabTool.getInstance(mContext).IsPass(bindedUserInfoEntity))
                    return;
                callTempletListEntity = TerminalCameraTool.getInstance(mContext).parseCallTemp(UMSInteractive.getInstance().getCallTempletList(guid));
                String STBGuid = bindedUserInfoEntity.getBindUserArray().get(bindedUserInfoEntity.getBindUserArray().size() >= UserSTBTabTool.getInstance(mContext).getCurrentIndex()
                        ? UserSTBTabTool.getInstance(mContext).getCurrentIndex() : 0).getGUID();
                //获取摄像头列表
                initMonitorCameraInfo(STBGuid);
            }

            @Override
            public void Main() {
                InitSTBDisplay(view, bindedUserInfoEntity);

                initKanJiaBaoCameraRecyclerView(recyclerView);
            }
        });
    }

    public void initKanJiaBaoCameraRecyclerView(RecyclerView recyclerView) {
        KanJiaBaoCameraAdapter adapter = new KanJiaBaoCameraAdapter(mContext, KanJiaBaoCameraEntity.getInstance());
        TerminalCameraTool.getInstance(mContext).setCameraAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
    }


    /**
     * 获取机顶盒下摄像头信息
     *
     * @param STBGuid
     */
    public void initMonitorCameraInfo(String STBGuid) {
        BindUserEntity bindUserEntity = TerminalCameraTool.getInstance(mContext).parseBindUser(UMSInteractive.getInstance().getBindedUser("", STBGuid));
        AlarmDeviceStatusEntity alarmDeviceStatusEntity = TerminalCameraTool.getInstance(mContext).parseAlarmStatus(CsmInteractive.getInstance().getAlarmDeviceStatus(STBGuid));
        MonitorCameraInfoEntity cameraInfoEntity = TerminalCameraTool.getInstance(mContext).parseMonitorCameraInfo(UMSInteractive.getInstance().getMonitorCameraInfo(STBGuid));

        if (!TerminalCameraTool.getInstance(null).isPass(cameraInfoEntity))
            return;

        KanJiaBaoCameraEntity.getInstance().setCameraInfoEntity(cameraInfoEntity);
        KanJiaBaoCameraEntity.getInstance().setCameraState(alarmDeviceStatusEntity);
        KanJiaBaoCameraEntity.getInstance().setSTBGuid(STBGuid);
        if (bindUserEntity.getIsSuccess() == 1)
            KanJiaBaoCameraEntity.getInstance().setUserEntity(bindUserEntity);
    }

    /**
     * @param view                 机顶盒用户显示view
     * @param bindedUserInfoEntity
     */
    public void InitSTBDisplay(Object view, BindedUserInfoEntity bindedUserInfoEntity) {
        if (view instanceof TabLayout) {
            UserSTBTabTool.getInstance(mContext).initUserNavigationTabLayout((TabLayout) view, bindedUserInfoEntity, this);
        }
    }
}
