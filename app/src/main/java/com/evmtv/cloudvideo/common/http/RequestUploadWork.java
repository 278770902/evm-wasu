package com.evmtv.cloudvideo.common.http;


import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RequestUploadWork {

    public static MultipartBody addFormDataPart(String key,
                                         String filePath,
                                         MediaType imageType) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        File file = new File(filePath);
        RequestBody requestBody = RequestBody.create(imageType, file);
        builder.addFormDataPart(key, file.getName(), requestBody);
        builder.setType(MultipartBody.FORM);
        return builder.build();
    }
}
