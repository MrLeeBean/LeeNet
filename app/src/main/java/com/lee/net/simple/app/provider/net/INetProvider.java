package com.lee.net.simple.app.provider.net;


import com.lee.net.simple.app.provider.net.core.HttpInterface;
import com.lee.net.simple.app.ui.bean.CheckRomBean;

/**
 * 网络请求的ProviderInterface
 *
 * @author liyong
 */

public interface INetProvider {

    /**
     * 测试网络请求。
     *
     * @param dataCallback
     */
    void requestCheckRom(String app_source, boolean isCustomizeRom, String versionname, HttpInterface.DataCallback<CheckRomBean> dataCallback);


}
