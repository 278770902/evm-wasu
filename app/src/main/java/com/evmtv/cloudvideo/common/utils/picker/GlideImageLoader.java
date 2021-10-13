package com.evmtv.cloudvideo.common.utils.picker;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.evmtv.cloudvideo.common.R;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesText;
import com.evmtv.cloudvideo.common.view.SharedPreferences.SharedPreferencesUtil;
import com.evmtv.cloudvideo.common.view.tool.GlideCircleTransform;
import com.lzy.imagepicker.loader.ImageLoader;

import java.io.File;


public class GlideImageLoader implements ImageLoader {

    private BitmapTransformation transformation;


    public GlideImageLoader() {
    }

    public GlideImageLoader(BitmapTransformation transformation) {
        this.transformation = transformation;
    }

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {

        if (transformation != null) {
            Glide.with(activity)
                    .load(new File(path))
                    .placeholder(R.drawable.my_setting_default_header)
                    .transform(transformation)
//                .transform(new GlideCircleTransform(activity))
                    .override(width, height)
                    .centerCrop()
                    .into(imageView);
        } else {
            Glide.with(activity)
                    .load(new File(path))
                    .placeholder(R.drawable.my_setting_default_header)
//                .transform(new GlideCircleTransform(activity))
                    .override(width, height)
                    .centerCrop()
                    .into(imageView);
        }
    }

    @Override
    public void clearMemoryCache() {
        //这里是清除缓存的方法,根据需要自己实现
    }
}
