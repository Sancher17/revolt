package com.prituladima.android.revolut.model.db;

import com.orhanobut.hawk.Hawk;
import com.prituladima.android.revolut.model.dto.RemoteCurrencyDTO;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class HawkLocalStorage {

    @Inject
    public HawkLocalStorage() {
    }

    public RemoteCurrencyDTO saveCurrencies(RemoteCurrencyDTO remoteCurrencyDTO) {
        Hawk.put(HawkKeyStorage.getCurrencyKey(), remoteCurrencyDTO);
        return remoteCurrencyDTO;
    }

    public RemoteCurrencyDTO getCurrencies() {
        return Hawk.get(HawkKeyStorage.getCurrencyKey());
    }

}