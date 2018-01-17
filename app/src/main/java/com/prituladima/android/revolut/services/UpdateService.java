package com.prituladima.android.revolut.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.prituladima.android.revolut.RevolutApplication;
import com.prituladima.android.revolut.presenter.LastCurrencyPresenter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;

public class UpdateService extends Service {
    public static final long PERIOD_MS = 1;
    Subscription subscription;

    @Inject
    LastCurrencyPresenter presenter;

    @Inject
    public UpdateService() {
        RevolutApplication.getInjector().inject(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        subscription = getPeriodicObservable().subscribe((t)->presenter.getLastUpdatedCurrency());
    }

    @Override
    public void onDestroy() {
        if(subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
        super.onDestroy();
    }

    public Observable<Long> getPeriodicObservable(){
        return Observable.interval(PERIOD_MS, TimeUnit.SECONDS);
    }
}
