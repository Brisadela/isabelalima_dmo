package br.edu.ifsp.arq.meutreino.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemTreinosAdapter extends RecyclerView.Adapter<ItemTreinosAdapter.ViewHolder> {

    public ItemTreinosAdapter(@NonNull @org.jetbrains.annotations.NotNull Context context) {
        super(context);
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ItemTreinosAdapter.ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ItemTreinosAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
