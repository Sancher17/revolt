package com.prituladima.android.revolut.model.db;

import io.michaelrocks.paranoid.Obfuscate;

@Obfuscate
public class HawkKeyStorage {

    private static final String CURRENCY_KEY = "CURRENCY_KEY";

    static String getCurrencyKey() {
        return CURRENCY_KEY;
    }

}