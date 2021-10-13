package com.evmtv.cloudvideo.common.presenter.qr;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.utils.AppUtils;
import com.evmtv.cloudvideo.common.utils.thread.AppExecutors;
import com.evmtv.cloudvideo.common.view.MyQRCodeActivity;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesText;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.tool.CodeUtils;
import com.google.zxing.WriterException;

import java.util.HashMap;
import java.util.Map;

public class QRPresenterImpl implements QRPresenter {
    private Context mContext;

    public QRPresenterImpl(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public void InitDisplay(final ImageView RQCodeViewID) {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {

            @Override
            public void run() {
                Bitmap beforeLogoBitmap = CodeUtils.getInstance().getUrlToBitmap(mContext);
                if (beforeLogoBitmap == null)
                    beforeLogoBitmap = AppUtils.getInstance(null).getBitmap();

                Bitmap bgBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.setting_code_fillet);
                Bitmap afterBitmap = CodeUtils.getInstance().modifyLogo(bgBitmap, beforeLogoBitmap);
                Map<String, String> content = new HashMap<>();
                content.put("type", "account");
                content.put("GUID", (String) SharedPreferencesUtil.getInstance()
                        .getData(SharedPreferencesText.LOGIN_CPN_USER_GUID, ""));
                content.put("name", (String) SharedPreferencesUtil.getInstance()
                        .getData(SharedPreferencesText.LOGIN_CPN_USER_NAME, ""));
                Bitmap lastBitmap = null;
                try {
                    lastBitmap = CodeUtils.getInstance().createCode(JSON.toJSONString(content), afterBitmap);
                } catch (WriterException e) {
                    Log.e("error", e.getMessage());
                    Toast.makeText(mContext, "图片加载失败", Toast.LENGTH_SHORT).show();
                }

                if (lastBitmap != null) {
                    final Bitmap finalLastBitmap = lastBitmap;
                    AppExecutors.getInstance().mainThread().execute(new Runnable() {
                        @Override
                        public void run() {
                            RQCodeViewID.setImageBitmap(finalLastBitmap);
                        }
                    });
                }
            }
        });
    }
}
