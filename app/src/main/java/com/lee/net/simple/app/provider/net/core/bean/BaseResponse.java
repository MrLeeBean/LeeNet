package com.lee.net.simple.app.provider.net.core.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author liyong
 */
public class BaseResponse<T> extends MyResponse implements Serializable {

    @Expose
    @SerializedName("data")
    private T data;

    public T getData() {
        return data;
    }

}
