package com.gpjypt.dome1.http;

public class HttpRootResult<T> {
    private String reason;
    private T result;
    private String error_code;

    public String getReason() {
        return reason;
    }

    public T getResult() {
        return result;
    }

    public String getError_code() {
        return error_code;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }
}
