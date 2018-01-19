package com.prituladima.android.revolut.presenter;

import android.content.Context;
import android.content.Intent;

import com.prituladima.android.revolut.RevolutApplication;
import com.prituladima.android.revolut.arch.BasePresenter;
import com.prituladima.android.revolut.arch.LastCurrencyContract;
import com.prituladima.android.revolut.model.Repository;
import com.prituladima.android.revolut.model.db.HawkLocalStorage;
import com.prituladima.android.revolut.services.UpdateService;
import com.prituladima.android.revolut.util.Logger;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.michaelrocks.paranoid.Obfuscate;
import rx.Subscription;

@Obfuscate
@Singleton
public class LastCurrencyPresenter extends BasePresenter<LastCurrencyContract.ILastCurrencyView>
        implements LastCurrencyContract.ILastCurrencyPresenter {

    private static Logger LOGGER = Logger.build(LastCurrencyPresenter.class);

    private Subscription subscription;

    @Inject
    Repository repository;
    @Inject
    Context context;
    @Inject
    HawkLocalStorage storage;

    @Inject
    public LastCurrencyPresenter() {
        RevolutApplication.getInjector().inject(this);
    }

    @Override
    public void attachView(LastCurrencyContract.ILastCurrencyView view) {
        LOGGER.log("attachView");
        super.attachView(view);
        getLastUpdatedCurrency();
        context.startService(new Intent(context, UpdateService.class));
    }

    @Override
    public void detachView() {
        LOGGER.log("detachView");
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        context.stopService(new Intent(context, UpdateService.class));
        super.detachView();
    }

    @Override
    public void getLastUpdatedCurrency(String code, Double amount) {
        LOGGER.log("getLastUpdatedCurrency");
        subscription = repository.updateCurrencies(code, amount)
                .subscribe(
                        list -> {
                            if (!list.isEmpty())
                                getMvpView().onCurrencyUpdated(list);
                            else
                                getMvpView().onNoData();
                        }
                );
    }

    @Override
    public void getLastUpdatedCurrency() {
        getLastUpdatedCurrency(storage.getMainCurrency().name(), storage.getMainCurrency().value());
    }


}