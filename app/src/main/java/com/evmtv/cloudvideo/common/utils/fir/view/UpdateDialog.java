package com.evmtv.cloudvideo.common.utils.fir.view;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.utils.fir.config.DownloadKey;

/**
 * Created by hugeterry(http://hugeterry.cn)
 */
public class UpdateDialog extends Activity {

    private TextView yes, no;
    private TextView tv_version, tv_changelog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_fir_update);

        yes = (TextView) findViewById(R.id.updatedialog_yes);
        no = (TextView) findViewById(R.id.updatedialog_no);
        tv_version = (TextView) findViewById(R.id.title);
        tv_changelog = (TextView) findViewById(R.id.updatedialog_text_changelog);

        tv_version.setText("发现新版本: V" + DownloadKey.version);
        tv_changelog.setText("更新日志：\n" + DownloadKey.changeLog);

        yes.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DownloadKey.TOShowDownloadView = 2;
                finish();
            }
        });

        no.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DownloadKey.TOShowDownloadView = 1;
                if (DownloadKey.ISManual) {
                    DownloadKey.LoadManual = false;
                }
                finish();
            }
        });
    }


}
