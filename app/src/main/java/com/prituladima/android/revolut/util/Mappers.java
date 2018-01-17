package com.prituladima.android.revolut.util;

import com.prituladima.android.revolut.model.dto.Currency;
import com.prituladima.android.revolut.model.dto.RemoteCurrencyDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Mappers {

    public static List<Currency> mapRemoteToLocal(RemoteCurrencyDTO remoteCurrencyDTO){
        List<Currency> list = new ArrayList<>();
        for(Map.Entry<String, Double> current: remoteCurrencyDTO.rates().entrySet())
            list.add(Currency.create(current.getKey(), current.getValue()));
        return list;
    }

}