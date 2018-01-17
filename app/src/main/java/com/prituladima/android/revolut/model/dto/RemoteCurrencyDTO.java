package com.prituladima.android.revolut.model.dto;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;

import io.michaelrocks.paranoid.Obfuscate;

@Obfuscate
@AutoValue
public abstract class RemoteCurrencyDTO {

    @SerializedName("base")
    public abstract String base();

    @SerializedName("date")
    public abstract String date();

    @SerializedName("rates")
    public abstract LinkedHashMap<String, Double> rates();

    public static TypeAdapter<RemoteCurrencyDTO> typeAdapter(Gson gson) {
        return new AutoValue_RemoteCurrencyDTO.GsonTypeAdapter(gson);
    }

}