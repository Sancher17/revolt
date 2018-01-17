package com.prituladima.android.revolut.model;

import com.prituladima.android.revolut.RevolutApplication;
import com.prituladima.android.revolut.model.api.CurrencyAPI;
import com.prituladima.android.revolut.model.db.HawkLocalStorage;
import com.prituladima.android.revolut.model.dto.Currency;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Singleton
public class Repository {

    @Inject
    CurrencyAPI currencyAPI;
    @Inject
    HawkLocalStorage localStorage;

    @Inject
    public Repository() {
        RevolutApplication.getInjector().inject(this);
    }

    public Observable<List<Currency>> updateCurrencies(String base, Double amount) {
        return currencyAPI.getCurrencies(base, amount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(remoteCurrencyDTO -> localStorage.saveCurrencies(remoteCurrencyDTO))
                .onErrorReturn(throwable -> localStorage.getCurrencies())
                .map((remoteCurrencyDTO) -> {
                        List<Currency> list = new ArrayList<>();
                        for(Map.Entry<String, Double> current: remoteCurrencyDTO.rates().entrySet())
                            list.add(Currency.create(current.getKey(), current.getValue()));
                        return list;
                    }
                )
                .onErrorReturn(throwable -> new ArrayList<>());
    }

}