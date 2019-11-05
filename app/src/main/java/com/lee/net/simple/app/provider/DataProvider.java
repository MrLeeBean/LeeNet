package com.lee.net.simple.app.provider;


import com.lee.net.simple.app.provider.common.ICommonProvider;
import com.lee.net.simple.app.provider.net.INetProvider;

/**
 * 基础信息分类提供者实现类
 *
 * @author liyong
 */

public class DataProvider implements IDataProvider {

    private INetProvider mINetProvider;
    private ICommonProvider mICommonProvider;

    public DataProvider(INetProvider mINetProvider, ICommonProvider mICommonProvider) {
        this.mINetProvider = mINetProvider;
        this.mICommonProvider = mICommonProvider;
    }

    @Override
    public ICommonProvider getCommonProvider() {
        return mICommonProvider;
    }

    @Override
    public INetProvider getNetProvider() {
        return mINetProvider;
    }
}
