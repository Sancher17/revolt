package com.prituladima.android.revolut.util;

import android.text.Editable;
import android.text.TextWatcher;

import com.prituladima.android.revolut.model.dto.Currency;

public abstract class LiteTextWatcher implements TextWatcher {


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public abstract void onTextChanged(CharSequence s, int start, int before, int count);

    @Override
    public  void afterTextChanged(Editable s) {

    }
}
