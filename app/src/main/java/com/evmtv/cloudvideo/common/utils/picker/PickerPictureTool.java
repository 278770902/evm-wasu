package com.evmtv.cloudvideo.common.utils.picker;


import android.app.Activity;
import android.content.Intent;

import com.evmtv.cloudvideo.common.view.tool.GlideCircleTransform;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.loader.ImageLoader;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.util.ArrayList;

public class PickerPictureTool {
    private ImagePicker imagePicker;
    private Activity activity;

    private int outPutX = 900;           //裁剪保存宽度
    private int outPutY = 600;           //裁剪保存高度
    private int focusWidth = 600;         //焦点框的宽度
    private int focusHeight = 400;
    public static final int PICKER_PICTURE_REQUEST_CODE = 0x1234;

    private PickerPictureTool(Activity activity) {
        this.activity = activity;
        this.imagePicker = ImagePicker.getInstance();
    }

    private static PickerPictureTool instance;

    public static PickerPictureTool getInstance(Activity activity) {
        synchronized (ImagePicker.class) {
            if (activity != null)
                instance = new PickerPictureTool(activity);
        }
        return instance;
    }

    /**
     * 不需要裁剪
     *
     * @return
     */
    public PickerPicture initMultiMode(int selectLimit) {
//        return this.initMultiMode(new GlideImageLoader(new GlideCircleTransform(activity)));
        return this.initMultiMode(new GlideImageLoader(), selectLimit);
    }

    public PickerPicture initMultiMode(ImageLoader imageLoader, int selectLimit) {
        imagePicker.setImageLoader(imageLoader);//设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setMultiMode(true);
        imagePicker.setSelectLimit(9);    //选中数量限制
        return new PickerPicture();
    }


    /**
     * 需要裁剪
     *
     * @return
     */
    public PickerPicture initSingleChoiceMode() {
        return this.initSingleChoiceMode(new GlideImageLoader(new GlideCircleTransform(activity)));
    }

    public PickerPicture initSingleChoiceMode(ImageLoader imageLoader) {
        return this.initSingleChoiceMode(imageLoader, CropImageView.Style.RECTANGLE);
    }

    public PickerPicture initSingleChoiceMode(ImageLoader imageLoader
            , CropImageView.Style style) {
        return initSingleChoiceMode(imageLoader, style, focusWidth, focusHeight, outPutX, outPutY);
    }


    public PickerPicture initSingleChoiceMode(ImageLoader imageLoader, CropImageView.Style style
            , int focusWidth, int focusHeight, int outPutX, int outPutY) {
        if (imageLoader != null)
            imagePicker.setImageLoader(imageLoader);//设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setMultiMode(false);
        imagePicker.setCrop(true);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setStyle(style);  //裁剪框的形状
        imagePicker.setFocusWidth(focusWidth);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(focusHeight);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(outPutX);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(outPutY);
        return new PickerPicture();
    }

    public void startCamera() {
        Intent intent = new Intent(activity, ImageGridActivity.class);
        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
        activity.startActivityForResult(intent, PICKER_PICTURE_REQUEST_CODE);
    }


    /**
     * 不能直接创建
     */
    public class PickerPicture {
        public void start() {
            Intent intent = new Intent(activity, ImageGridActivity.class);
            activity.startActivityForResult(intent, PICKER_PICTURE_REQUEST_CODE);
        }
    }


    public ArrayList<ImageItem> onActivityResult(int requestCode, int resultCode, Intent data) {
        ArrayList<ImageItem> images = null;
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == PickerPictureTool.PICKER_PICTURE_REQUEST_CODE) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            }
        }
        return images;
    }
}
