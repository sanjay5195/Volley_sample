package com.devsan.seenitassignment.util;

public interface DataResponseListener<T> {

    void onSuccess(T t);

    void onFailure(String message);
}
