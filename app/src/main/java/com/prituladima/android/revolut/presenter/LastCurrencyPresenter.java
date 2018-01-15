package com.prituladima.android.revolut.presenter;

import com.prituladima.android.revolut.RevolutApplication;
import com.prituladima.android.revolut.arch.BasePresenter;
import com.prituladima.android.revolut.arch.LastCurrencyContract;
import com.prituladima.android.revolut.model.Repository;
import com.prituladima.android.revolut.model.dto.Currency;
import com.prituladima.android.revolut.util.Logger;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Subscription;
import rx.functions.Action1;

@Singleton
public class LastCurrencyPresenter extends BasePresenter<LastCurrencyContract.ILastCurrencyView>
        implements LastCurrencyContract.ILastCurrencyPresenter {

    private static Logger LOGGER = Logger.build(LastCurrencyPresenter.class);

    private Subscription subscription;

    @Inject
    Repository repository;

    @Inject
    public LastCurrencyPresenter() {
        RevolutApplication.getInjector().inject(this);
    }

    @Override
    public void attachView(LastCurrencyContract.ILastCurrencyView view) {
        super.attachView(view);
        getLastUpdatedCurrency();
    }

    @Override
    public void detachView() {
        super.detachView();
        if (subscription != null && subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void getLastUpdatedCurrency() {
        subscription = repository.updateCurrencies("USD", 1)
                .subscribe(new Action1<List<Currency>>() {
                    @Override
                    public void call(List<Currency> list) {
                        getMvpView().onCurrencyUpdated(list);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
    }
}