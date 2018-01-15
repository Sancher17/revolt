package com.prituladima.android.revolut.dagger;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.prituladima.android.revolut.view.adapter.LastCurrencyAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class UIModule {

//    @Provides
//    public LastCurrencyAdapter provideLastCurrencyAdapter() {
//        return new LastCurrencyAdapter();
//    }

    @Provides
    public LinearLayoutManager provideLinearLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    @Provides
    public DefaultItemAnimator provideDefaultItemAnimator() {
        return new DefaultItemAnimator();
    }
}