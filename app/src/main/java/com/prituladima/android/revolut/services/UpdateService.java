package com.prituladima.android.revolut.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.prituladima.android.revolut.RevolutApplication;
import com.prituladima.android.revolut.presenter.LastCurrencyPresenter;
import com.prituladima.android.revolut.util.Logger;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.michaelrocks.paranoid.Obfuscate;
import rx.Observable;
import rx.Subscription;

@Obfuscate
public class UpdateService extends Service {

    private static Logger LOGGER = Logger.build(UpdateService.class);

    public static final long PERIOD_MS = 1;
    private Subscription subscription;

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
        LOGGER.log("onCreate");
        super.onCreate();
        subscription = getPeriodicObservable().subscribe((t)->presenter.getLastUpdatedCurrency());
    }

    @Override
    public void onDestroy() {
        LOGGER.log("onDestroy");
        if(subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();
        super.onDestroy();
    }

    public Observable<Long> getPeriodicObservable(){
        return Observable.interval(PERIOD_MS, TimeUnit.SECONDS);
    }
}