package com.prituladima.android.revolut.dagger;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import dagger.Module;
import dagger.Provides;

@Module
public class UIModule {

    @Provides
    public LinearLayoutManager provideLinearLayoutManager(Context context) {
        return new LinearLayoutManager(context);
    }

    @Provides
    public DefaultItemAnimator provideDefaultItemAnimator() {
        return new DefaultItemAnimator();
    }
}