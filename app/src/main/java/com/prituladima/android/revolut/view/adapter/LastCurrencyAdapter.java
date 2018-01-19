package com.prituladima.android.revolut.view.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.prituladima.android.revolut.R;
import com.prituladima.android.revolut.RevolutApplication;
import com.prituladima.android.revolut.model.db.HawkLocalStorage;
import com.prituladima.android.revolut.model.dto.Currency;
import com.prituladima.android.revolut.util.LiteTextWatcher;
import com.prituladima.android.revolut.util.Logger;
import com.prituladima.android.revolut.util.Mappers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.michaelrocks.paranoid.Obfuscate;

import static com.prituladima.android.revolut.util.CurrencyUtil.getCurrencyNameResByISO;
import static com.prituladima.android.revolut.util.CurrencyUtil.getFlagResByISO;

@Obfuscate
public class LastCurrencyAdapter extends RecyclerView.Adapter<LastCurrencyAdapter.ViewHolder> {

    private static Logger LOGGER = Logger.build(LastCurrencyAdapter.class);

    private List<Currency> actualList = new ArrayList<>();
    private boolean binding;

    @Inject
    public Context context;
    @Inject
    public HawkLocalStorage storage;

    @Inject
    public LastCurrencyAdapter() {
        RevolutApplication.getInjector().inject(this);
        actualList.add(0, storage.getMainCurrency());
    }

    public void setZeroToALL() {
        List<Currency> zeroList = new ArrayList<>();
        for (int i = 1; i < actualList.size(); i++) {
            zeroList.add(Currency.create(actualList.get(i).name(), 0.0));
        }
        setData(zeroList);
    }

    public void setData(List<Currency> list) {
        for (int index = actualList.size() - 1; index > 0; index--)
            actualList.remove(index);
        this.actualList.addAll(1, new ArrayList<>(list));
        if (!binding) notifyItemRangeChanged(1, list.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false), new MyCustomEditTextListener());
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        binding = true;
        Currency current = actualList.get(position);
        holder.myCustomEditTextListener.updatePosition(position);

        holder.flag_image_view.setImageResource(getFlagResByISO(current.name()));
        holder.text_iso.setText(current.name());
        holder.text_name.setText(getCurrencyNameResByISO(current.name()));
        holder.card_view.setOnClickListener((view) -> moveToFirst(position));

        holder.currency_edit_text.setText(String.valueOf(current.value()));


        binding = false;
    }

    private void moveToFirst(int position) {
    }

    @Override
    public int getItemCount() {
        return actualList.size();
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

        public MyCustomEditTextListener myCustomEditTextListener;

        public ViewHolder(View itemView, MyCustomEditTextListener myCustomEditTextListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.myCustomEditTextListener = myCustomEditTextListener;
            this.currency_edit_text.addTextChangedListener(myCustomEditTextListener);
        }

    }


    private class MyCustomEditTextListener implements TextWatcher {
        private int position;

        public void updatePosition(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence s, int i, int i2, int i3) {

            if (position != 0) return;
            Double value = Mappers.parseDouble(s.toString());
            Currency updatedMainCurrency = Currency.create(storage.getMainCurrency().name(), value);
            storage.saveMainCurrency(updatedMainCurrency);
            actualList.remove(0);
            actualList.add(0, updatedMainCurrency);

            if (value.equals(0.0)) setZeroToALL();
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }

}