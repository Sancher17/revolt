package com.prituladima.android.revolut.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.prituladima.android.revolut.R;
import com.prituladima.android.revolut.RevolutApplication;
import com.prituladima.android.revolut.model.dto.Currency;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.prituladima.android.revolut.util.CurrencyUtil.getCurrencyNameResByISO;
import static com.prituladima.android.revolut.util.CurrencyUtil.getFlagResByISO;

public class LastCurrencyAdapter extends RecyclerView.Adapter<LastCurrencyAdapter.ViewHolder> {

    private List<Currency> list = new ArrayList<>();
    @Inject
    public Context context;

    @Inject
    public LastCurrencyAdapter() {
        RevolutApplication.getInjector().inject(this);
    }

    public void setData(List<Currency> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.currency_edit_text.setText(String.valueOf(list.get(position).value()));
        holder.flag_image_view.setImageResource(getFlagResByISO(list.get(position).name()));
        holder.text_iso.setText(list.get(position).name());
        holder.text_name.setText(getCurrencyNameResByISO(list.get(position).name()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_view)
        CardView card_view;

        @BindView(R.id.currency_edit_text)
        EditText currency_edit_text;

        @BindView(R.id.flag_image_view)
        ImageView flag_image_view;

        @BindView(R.id.text_iso)
        TextView text_iso;

        @BindView(R.id.text_name)
        TextView text_name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}