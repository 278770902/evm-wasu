package com.evmtv.cloudvideo.common.utils.fir.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.utils.fir.Download;
import com.evmtv.cloudvideo.common.utils.fir.config.DownloadKey;

public class DownLoadDialog extends AppCompatActivity {

    private ImageView close;
    public ProgressBar progressBar;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_down_load);
        close = (ImageView) findViewById(R.id.downloaddialog_close);
        progressBar = (ProgressBar) findViewById(R.id.downloaddialog_progress);
        textView = (TextView) findViewById(R.id.downloaddialog_count);

        if (DownloadKey.interceptFlag) DownloadKey.interceptFlag = false;
        new Download(this).start();

        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                DownloadKey.TOShowDownloadView = 1;
                DownloadKey.interceptFlag = true;
                if (DownloadKey.ISManual) {
                    DownloadKey.LoadManual = false;
                }
                finish();
            }
        });

    }


}
