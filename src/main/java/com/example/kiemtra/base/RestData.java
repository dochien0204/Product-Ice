package com.example.kiemtra.base;

import com.fasterxml.jackson.annotation.JsonInclude;

public class RestData<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private RestStatus restStatus;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userMessage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String devMessage;

    private T data;

    public RestData()
    {

    }

    public RestData(T data)
    {
        this.data = data;
    }

    public RestData(RestStatus restStatus, String userMessage, T data)
    {
        this.restStatus = restStatus;
        this.userMessage = userMessage;
        this.data = data;
    }

    public RestData(RestStatus restStatus, String userMessage, String devMessage, T data)
    {
        this.restStatus = restStatus;
        this.userMessage = userMessage;
        this.devMessage = devMessage;
        this.data = data;
    }

    public RestStatus getRestStatus() {
        return restStatus;
    }

    public void setRestStatus(RestStatus restStatus) {
        this.restStatus = restStatus;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getDevMessage() {
        return devMessage;
    }

    public void setDevMessage(String devMessage) {
        this.devMessage = devMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static RestData<?> error(String userMessage, String devMessage) {
        return new RestData<>(RestStatus.ERROR, userMessage, devMessage, null);
    }

    public static RestData<?> error(String userMessage) {
        return new RestData<>(RestStatus.ERROR, userMessage, null);
    }

    public static RestData<?> ok(String userMessage, String devMessage) {
        return new RestData<>(RestStatus.SUCCESS, userMessage, devMessage, null);
    }

    public static RestData<?> ok(String userMessage) {
        return new RestData<>(RestStatus.SUCCESS, userMessage, null);
    }
}
