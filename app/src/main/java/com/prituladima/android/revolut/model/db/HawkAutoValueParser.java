package com.prituladima.android.revolut.model.db;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.orhanobut.hawk.Parser;
import com.ryanharter.auto.value.gson.AutoValueGsonTypeAdapterFactory;

import java.lang.reflect.Type;

public class HawkAutoValueParser implements Parser {

    private final Gson gson;

    public HawkAutoValueParser() {
        gson = new GsonBuilder().registerTypeAdapterFactory(new AutoValueGsonTypeAdapterFactory()).create();
    }

    @Override
    public <T> T fromJson(String content, Type type) throws JsonSyntaxException {
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        return gson.fromJson(content, type);
    }

    @Override
    public String toJson(Object body) {
        return gson.toJson(body);
    }

}
