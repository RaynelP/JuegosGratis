package com.example.juegosgratis.util;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GenericViewModel extends ViewModel {

    private final MutableLiveData<Boolean> retry = new MutableLiveData<>(null);


    private final MutableLiveData<Boolean> error = new MutableLiveData<>(null);


    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>(null);

    public LiveData<Boolean> getRetry() {
        return retry;
    }

    public LiveData<Boolean> getError() {
        return error;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void startRetry(){
        retry.setValue(true);
    }

    public void doneRetry(){
        retry.setValue(false);
    }

    public void onError(){
        error.setValue(true);
    }

    public void doneError(){
        error.setValue(false);
    }

    public void onLoanding() {
        isLoading.setValue(true);
    }

    public void doneLoanding() {
        isLoading.setValue(false);
    }

}
