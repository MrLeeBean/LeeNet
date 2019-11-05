package com.lee.net.simple.app.provider.common.model;


import com.lee.net.simple.app.provider.common.ICommonProvider;

/**
 * 公共信息的ICommonProvider实现类
 *
 * @author liyong
 */

public class CommonProvider implements ICommonProvider {

    /**
     * 测试数据
     *
     * @return
     */
    @Override
    public String providerTest() {
        return "HELLO WORLD!";
    }
}
