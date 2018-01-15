package com.prituladima.android.revolut.dagger;

import dagger.Module;
import dagger.Provides;
import rx.subjects.PublishSubject;

@Module
public class BusModule {

    @Provides
    public PublishSubject provideLastCurrencyAdapter() {
        return PublishSubject.create();
    }

}