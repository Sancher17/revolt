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

    public RemoteCurrencyDTO saveTop(RemoteCurrencyDTO remoteCurrencyDTO, int amount, String page) {
        Hawk.put(HawkKeyStorage.getRedditTopKey(amount, page), remoteCurrencyDTO);
        return remoteCurrencyDTO;
    }

    public RemoteCurrencyDTO getTop(int amount, String page) {
        return Hawk.get(HawkKeyStorage.getRedditTopKey(amount, page));
    }

}