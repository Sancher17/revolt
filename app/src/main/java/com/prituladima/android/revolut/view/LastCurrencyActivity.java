package com.prituladima.android.revolut.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.prituladima.android.revolut.R;
import com.prituladima.android.revolut.RevolutApplication;
import com.prituladima.android.revolut.arch.LastCurrencyContract;
import com.prituladima.android.revolut.model.dto.Currency;
import com.prituladima.android.revolut.presenter.LastCurrencyPresenter;
import com.prituladima.android.revolut.util.Logger;
import com.prituladima.android.revolut.view.adapter.LastCurrencyAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class LastCurrencyActivity extends AppCompatActivity
        implements LastCurrencyContract.ILastCurrencyView, Action1<Void> {

    private static Logger LOGGER = Logger.build(LastCurrencyActivity.class);

    @Inject
    LastCurrencyPresenter lastCurrencyPresenter;
    @Inject
    LastCurrencyAdapter adapter;
    @Inject
    LinearLayoutManager linearLayoutManager;
    @Inject
    DefaultItemAnimator defaultItemAnimator;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.empty_view)
    LinearLayout empty_view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LOGGER.log("onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RevolutApplication.getInjector().inject(this);
        ButterKnife.bind(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(defaultItemAnimator);
        empty_view.setVisibility(INVISIBLE);
    }

    @Override
    protected void onStart() {
        LOGGER.log("onStart");
        super.onStart();
        lastCurrencyPresenter.attachView(this);

    }

    @Override
    protected void onStop() {
        LOGGER.log("onStop");
        lastCurrencyPresenter.detachView();
        super.onStop();
    }

    @Override
    public void onCurrencyUpdated(List<Currency> list) {
        LOGGER.log("onCurrencyUpdated - " + list.toString());
        adapter.setData(list);
    }

    @Override
    public void onNoData() {
        LOGGER.log("onNoData");
        empty_view.setVisibility(VISIBLE);
    }

    @Override
    public void call(Void aVoid) {
        LOGGER.log("updating data");
        lastCurrencyPresenter.getLastUpdatedCurrency();
    }
}