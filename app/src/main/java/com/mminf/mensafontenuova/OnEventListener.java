package com.mminf.mensafontenuova;

public interface OnEventListener<T> {

    void onSuccess(T Integer);

    void onFailure(Exception e);
}
