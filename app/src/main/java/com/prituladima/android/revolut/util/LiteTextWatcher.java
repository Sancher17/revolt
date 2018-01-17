package com.prituladima.android.revolut.util;

import android.text.Editable;
import android.text.TextWatcher;

//todo remove
public interface LiteTextWatcher extends TextWatcher {

    @Override
    public default void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public abstract void onTextChanged(CharSequence s, int start, int before, int count);

    @Override
    public default void afterTextChanged(Editable s) {

    }
}
