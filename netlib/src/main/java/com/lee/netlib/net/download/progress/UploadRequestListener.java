package com.lee.netlib.net.download.progress;

/**
 * Created by lee on 17/10/9.
 */

public interface UploadRequestListener {
    void onProgress(String filePath, float progress);
    void onUploadDone(String filePath);
    void onUploadFail(String filePath);
}
