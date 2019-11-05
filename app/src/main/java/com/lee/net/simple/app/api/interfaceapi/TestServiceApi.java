package com.lee.net.simple.app.api.interfaceapi;

import com.lee.net.simple.app.api.ServerField;
import com.lee.net.simple.app.constants.MyConstants;
import com.lee.net.simple.app.provider.net.core.bean.BaseResponse;
import com.lee.net.simple.app.ui.bean.CheckRomBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface TestServiceApi {


    @Headers({"Domain-Host:" + MyConstants.ServerDomainKey.URL1})// 加上 Domain-Host header
    @GET(ServerField.CHECK_ROM)
    Observable<BaseResponse<CheckRomBean>> testRequestGet(@Query("app_source") String app_source, @Query("isCustomizeRom") boolean isCustomizeRom, @Query("versionname") String versionname);


}