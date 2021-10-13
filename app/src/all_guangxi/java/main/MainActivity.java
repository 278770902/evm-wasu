package main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.widget.FrameLayout;

import com.alibaba.fastjson.JSON;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.model.local.NavigationBean;
import com.evmtv.cloudvideo.common.presenter.base.BaseActivity;
import com.evmtv.cloudvideo.common.presenter.main.MainPresenter;
import com.evmtv.cloudvideo.common.presenter.main.MainPresenterImpl;
import com.evmtv.cloudvideo.common.presenter.main.OnNavigationItemSelectedListener;
import com.evmtv.cloudvideo.common.utils.AnalysisLocalJsonIntentUtil;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.dialog.LogOutDialog;
import com.evmtv.cloudvideo.common.view.tool.NoScrollViewPager;


public class MainActivity extends BaseActivity implements OnNavigationItemSelectedListener {
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
    public void onNavigationItemSelected(int position, Object value) {
        this.beanItem = (NavigationBean.MainNavigationBean.NavigationContentBean) value;
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
}
