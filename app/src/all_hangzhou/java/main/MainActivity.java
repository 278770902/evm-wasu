package main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.alibaba.fastjson.JSON;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.SendMessageEntity;
import com.evmtv.cloudvideo.common.model.local.NavigationBean;
import com.evmtv.cloudvideo.common.presenter.base.BaseActivity;
import com.evmtv.cloudvideo.common.presenter.base.BaseMainActivity;
import com.evmtv.cloudvideo.common.presenter.main.MainPresenter;
import com.evmtv.cloudvideo.common.presenter.main.MainPresenterImpl;
import com.evmtv.cloudvideo.common.presenter.main.OnNavigationItemSelectedListener;
import com.evmtv.cloudvideo.common.utils.AnalysisLocalJsonIntentUtil;
import com.evmtv.cloudvideo.common.utils.fir.UpdateFunGO;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.dialog.LogOutDialog;
import com.evmtv.cloudvideo.common.view.fragment.FragmentKeyCodeBackTool;
import com.evmtv.cloudvideo.common.view.tool.NoScrollViewPager;
import com.evmtv.cloudvideo.common.view.tool.XLog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MainActivity extends BaseMainActivity implements OnNavigationItemSelectedListener {
    private String TAG = getClass().getName();
    private MainPresenter mainPresenter;
    //        private NoScrollViewPager MainViewPagerViewID;
    private Object MainViewPagerViewID;
    //    private LazyViewPager MainViewPagerViewID;
//    private BottomNavigationView MainBottomNavigationViewID;
    private TabLayout MainBottomNavigationViewID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        MainViewPagerViewID = findViewById(R.id.MainViewPagerViewID);
        mainPresenter = new MainPresenterImpl();
        mainPresenter.InitDisplay(this, MainBottomNavigationViewID, MainViewPagerViewID, this);
    }

    @Override
    public void onNavigationItemSelected(int position, Object object) {
        this.beanItem = (NavigationBean.MainNavigationBean.NavigationContentBean) object;
        Log.i(TAG, "onNavigationItemSelected=== n" + JSON.toJSONString(beanItem));
        if (beanItem.getType().contains("fragment")) {
            String guid = SharedPreferencesUtil.getInstance().getUserGuid(false);
            if (beanItem.getType().contains("login") && (guid == null || guid.isEmpty())) {
                new LogOutDialog(MainActivity.this).show();
                return;
            }
            if (MainViewPagerViewID instanceof NoScrollViewPager) {
                NoScrollViewPager MainViewPagerViewID = (NoScrollViewPager) this.MainViewPagerViewID;
                MainViewPagerViewID.setCurrentItem(beanItem.getOrder());
            }

            if (MainViewPagerViewID instanceof FrameLayout) {
                mainPresenter.getFragmentTransactionUtils().show(position);
            }
            return;
        }
        if (beanItem.getType().equals("intent")) {
            AnalysisLocalJsonIntentUtil.getInstance().startActivity(MainActivity.this, beanItem.getContent());
            return;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            XLog.e("FragmentKeyCodeBackTool", "入 KEYCODE_BACK");
            FragmentKeyCodeBackTool.getInstance(MainActivity.this).KeyCodeBack(mainPresenter.getFragmentTransactionUtils().getBaseFragment().backTimeRunBle);
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMsg(SendMessageEntity msg) {
        if (msg.getMsgId() == SendMessageEntity.MsgId.HIDE_BOTTOM_TAB) {
            MainBottomNavigationViewID.setVisibility(View.GONE);
        }

        if (msg.getMsgId() == SendMessageEntity.MsgId.SHOW_BOTTOM_TAB) {
            MainBottomNavigationViewID.setVisibility(View.VISIBLE);
        }
    }
}
