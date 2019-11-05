package com.lee.net.simple.app.provider;


import com.lee.net.simple.app.provider.common.ICommonProvider;
import com.lee.net.simple.app.provider.net.INetProvider;

/**
 * interface - 基础信息分类提供者接口,用于分类DataProvider
 *
 * @author liyong
 */

public interface IDataProvider {


    /**
     * 获取公共内容的数据提供者
     *
     * @return
     */
    ICommonProvider getCommonProvider();

    /**
     * 获取网络请求的数据提供者
     *
     * @return
     */
    INetProvider getNetProvider();

}
