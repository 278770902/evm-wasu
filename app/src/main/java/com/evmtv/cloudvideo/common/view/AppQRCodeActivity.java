package com.evmtv.cloudvideo.common.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.presenter.base.BaseActivity;
import com.evmtv.cloudvideo.common.utils.AppUtils;
import com.evmtv.cloudvideo.common.utils.ReadLocalJsonFile;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

public class AppQRCodeActivity extends BaseActivity implements View.OnClickListener {

    private View backViewID;
    private ImageView QRCodeImageViewID;
    private Button shareQRCodeViewID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_qrcode);
        initView();
    }

    private void initView() {
        backViewID.setOnClickListener(this);
        QRCodeImageViewID.setImageResource(ReadLocalJsonFile.getDownloadAppQRID());
        shareQRCodeViewID.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backViewID:
                finish();
                break;
            case R.id.shareQRCodeViewID:
                UMImage image = new UMImage(AppQRCodeActivity.this, AppUtils.getInstance(null).getBitmap());//分享图标
                final UMWeb web = new UMWeb(ReadLocalJsonFile.getDownloadAppUrl()); //切记切记 这里分享的链接必须是http开头
                web.setTitle("客户端下载界面");//标题
                web.setThumb(image);  //缩略图
                web.setDescription("点击跳转...");//描述
                new UMShareUtil().ShareWebAction(AppQRCodeActivity.this, web);
                break;
        }
    }
}
