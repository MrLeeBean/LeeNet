package com.lee.net.simple.app.provider;


import com.shuai.csnet.example.app.provider.common.ICommonProvider;
import com.shuai.csnet.example.app.provider.net.INetProvider;

/**
 * 基础信息分类提供者实现类
 *
 * @author changshuai
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
