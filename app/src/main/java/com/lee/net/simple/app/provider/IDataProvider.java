package com.lee.net.simple.app.provider;


import com.shuai.csnet.example.app.provider.common.ICommonProvider;
import com.shuai.csnet.example.app.provider.net.INetProvider;

/**
 * interface - 基础信息分类提供者接口,用于分类DataProvider
 *
 * @author changshuai
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
