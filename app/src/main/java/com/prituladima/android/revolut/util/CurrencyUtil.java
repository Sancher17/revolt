package com.prituladima.android.revolut.util;

import com.mynameismidori.currencypicker.ExtendedCurrency;

public final class CurrencyUtil {

    private CurrencyUtil() {

    }

    public static int getFlagResByISO(String iso){
        for(ExtendedCurrency currency: ExtendedCurrency.getAllCurrencies())
            if(currency.getCode().equals(iso))
                return currency.getFlag();
        return -1;
    }

    public static String getCurrencyNameResByISO(String iso){
        for(ExtendedCurrency currency: ExtendedCurrency.getAllCurrencies())
            if(currency.getCode().equals(iso))
                return currency.getName();
        return "";
    }

}
