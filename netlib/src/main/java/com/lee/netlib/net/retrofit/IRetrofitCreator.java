package com.lee.netlib.net.retrofit;


import retrofit2.Retrofit;

/**
 * @author liyong
 */
public interface IRetrofitCreator {

    Retrofit create();

    Retrofit create(String baseUrl);

}
