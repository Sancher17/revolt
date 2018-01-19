package com.prituladima.android.revolut.arch;

import com.prituladima.android.revolut.model.dto.Currency;

import java.util.List;

import rx.Observable;

public interface IRepository {

    Observable<List<Currency>> updateCurrencies(String base, Double amount);

}