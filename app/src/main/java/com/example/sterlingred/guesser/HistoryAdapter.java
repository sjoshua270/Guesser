package com.example.sterlingred.guesser;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sterlingred.guesser.objects.HistoryItem;

import java.util.List;
import java.util.Locale;

/**
 * Created by Joshua on 3/5/2016.
 * com.example.sterlingred.guesser
 */
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private List<HistoryItem> history;

    public HistoryAdapter(List<HistoryItem> history) {
        this.history = history;
    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.ViewHolder holder, int position) {
        HistoryItem item = history.get(position);
        if (item.getGuess() > -1)
            holder.textView.setText(String.format(Locale.getDefault(), "%s %d, Rating: %d", item.getMessage(), item.getGuess(), item.getRating()));
        else
            holder.textView.setText(item.getMessage());
    }

    @Override
    public int getItemCount() {
        return history.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }
}
