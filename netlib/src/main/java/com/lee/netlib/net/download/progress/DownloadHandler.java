package com.lee.netlib.net.download.progress;

/**
 * Created by lee on 17/10/30.
 */

public interface DownloadHandler {
     void puase();
     boolean isPuaseed();
     void cancel();
     boolean isCanceled();
}
