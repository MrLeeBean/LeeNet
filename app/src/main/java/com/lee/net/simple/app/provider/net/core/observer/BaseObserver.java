package com.lee.net.simple.app.provider.net.core.observer;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;


import com.lee.net.simple.app.R;
import com.lee.net.simple.app.constants.MyConstants;
import com.lee.net.simple.app.provider.net.core.bean.BaseResponse;
import com.lee.net.simple.app.provider.net.core.error.MyErrorInfoConstant;
import com.lee.netlib.config.NetConfig;
import com.lee.netlib.net.exception.RequestException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;
import retrofit2.HttpException;


/**
 * 最底层Observer
 *
 * @param <T>
 */
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    private static final String TAG = BaseObserver.class.getSimpleName();
    private Context ctx;
    private CompositeDisposable compositeDisposable;

    public BaseObserver() {
    }

    public BaseObserver(Context ctx) {
        this.ctx = ctx.getApplicationContext();
    }

    public BaseObserver(Context ctx, CompositeDisposable compositeDisposable) {
        this.ctx = ctx.getApplicationContext();
        this.compositeDisposable = compositeDisposable;
    }


    // 错误处理
    public abstract void handleError(int errorCode, String errMsg, Throwable err);

    // 正常处理
    public abstract void handleData(BaseResponse<T> response);

    // 无论正常还是异常返回，都会走到这里
    public abstract void onFinally();


    // onComplete 调用之后再调用onNext或者onError都不会生效
    @Override
    public void onComplete() {
        onFinally();
    }

    @Override
    public void onError(Throwable e) {
        parseError(e);
        onFinally();
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (compositeDisposable != null) {
            compositeDisposable.add(d);
        }
    }

    @Override
    public void onNext(BaseResponse<T> baseResponse) {
        if (baseResponse != null) {
            if (NetConfig.getConfig().getBaseConfig().isDebug()) {
                Log.i(TAG, baseResponse.getStatus() + baseResponse.getErrMsg());
            }
            if (baseResponse.isSuccess()) {// 成功
                handleData(baseResponse);
            } else if (baseResponse.getStatus() == MyErrorInfoConstant.CODE_LOGIN_EXPIRED) { //登陆过期
                logout();
            } else { //其他错误
                int errorCode = baseResponse.getStatus();
                String errMsg = !TextUtils.isEmpty(baseResponse.getErrMsg()) ? baseResponse.getErrMsg() : MyErrorInfoConstant.MESSAGE_EXCEPTION;
                handleError(errorCode, errMsg, new RequestException(errMsg));
            }
        } else {
            int errorCode = MyErrorInfoConstant.CODE_RESPONSE_EMPTY;
            String errMsg = MyErrorInfoConstant.MESSAGE_RESPONSE_EMPTY;
            handleError(errorCode, errMsg, new NullPointerException());
        }
    }


    private void parseError(Throwable ex) {
        int errorCode;
        String errMsg;
        if (ex instanceof HttpException) {
            HttpException httpException = (HttpException) ex;
            errorCode = httpException.code();
            errMsg = httpException.message();
            if (httpException.response() != null) {
                ResponseBody responseBody = httpException.response().errorBody();
                try {
                    if (responseBody != null) {
                        String data = responseBody.string();
                        checkLoginExpired(data);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //DNS解析异常
        else if (ex instanceof UnknownHostException) {
            errorCode = MyErrorInfoConstant.CODE_UNKNOWN_HOST;
            errMsg = MyErrorInfoConstant.MESSAGE_UNKNOWN_HOST;
        }
        //请求超时
        else if (ex instanceof ConnectException) {
            errorCode = MyErrorInfoConstant.CODE_REQUEST_TIMEOUT;
            errMsg = MyErrorInfoConstant.MESSAGE_REQUEST_TIMEOUT;
        }
        //响应超时
        else if (ex instanceof SocketTimeoutException) {
            errorCode = MyErrorInfoConstant.CODE_RESPONSE_TIMEOUT;
            errMsg = MyErrorInfoConstant.MESSAGE_RESPONSE_TIMEOUT;
        }
        // 其他异常
        else {
            errorCode = MyErrorInfoConstant.CODE_OTHER_EXCEPTION;
            errMsg = NetConfig.getApp().getString(R.string.error_network_other_exception, ex.getLocalizedMessage());
        }
        handleError(errorCode, errMsg, ex);
    }

    private static boolean checkLoginExpired(String response) {
        if (TextUtils.isEmpty(response)) {
            return false;
        }
        try {
            JSONObject jsonObject = new JSONObject(response);
            int status = jsonObject.getInt(MyConstants.CommonKey.STATUS);
            if (status == MyErrorInfoConstant.CODE_LOGIN_EXPIRED) {
                logout();
                return true;
            }
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected static void logout() {

        // TODO :登录过期的方法

    }
}
