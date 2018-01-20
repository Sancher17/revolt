package com.prituladima.android.revolut.arch;

import com.prituladima.android.revolut.model.dto.Currency;

import java.util.List;

public interface LastCurrencyContract {

    interface ILastCurrencyView extends IView {
        void onCurrencyUpdated(List<Currency> list);
        void onNoDataAvailable();
    }

    interface ILastCurrencyPresenter {
        void getLastUpdatedCurrency(String code, Double amount);
        void getLastUpdatedCurrency();
    }

}