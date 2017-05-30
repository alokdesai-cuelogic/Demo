package com.alok.pp.interfaces;

public interface ResponseStatusListener {
    void onRequestSuccess(Object response);
    void onRequestFailure(int statusCode, Object responseData);
}
