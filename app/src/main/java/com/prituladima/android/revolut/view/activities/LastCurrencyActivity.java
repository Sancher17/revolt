package com.prituladima.android.revolut.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

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
import io.michaelrocks.paranoid.Obfuscate;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

@Obfuscate
public class LastCurrencyActivity extends AppCompatActivity
        implements LastCurrencyContract.ILastCurrencyView{

    private static Logger LOGGER = Logger.build(LastCurrencyActivity.class);

    @Inject
    LastCurrencyPresenter presenter;
    @Inject
    LastCurrencyAdapter adapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.empty_view)
    LinearLayout emptyView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LOGGER.log("onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RevolutApplication.getInjector().inject(this);
        ButterKnife.bind(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        emptyView.setVisibility(INVISIBLE);
    }

    @Override
    protected void onResume() {
        LOGGER.log("onStart");
        super.onResume();
        presenter.attachView(this);

    }

    @Override
    protected void onPause() {
        LOGGER.log("onStop");
        presenter.detachView();
        super.onPause();
    }

    @Override
    public void onCurrencyUpdated(List<Currency> list) {
        LOGGER.log("onCurrencyUpdated - " + list.toString());
        adapter.setVolatileCurrencyValues(list);
        emptyView.setVisibility(INVISIBLE);
    }

    @Override
    public void onNoDataAvailable() {
        LOGGER.log("onNoDataAvailable");
        emptyView.setVisibility(VISIBLE);
    }

}