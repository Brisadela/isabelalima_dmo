package br.edu.ifsp.arq.minhastarefas.adpater;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.edu.ifsp.arq.minhastarefas.R;
import br.edu.ifsp.arq.minhastarefas.model.Tarefa;
import br.edu.ifsp.arq.minhastarefas.view.RecyclerItemClickListener;

public class ItemTarefaAdapter extends RecyclerView.Adapter<ItemTarefaAdapter.ViewHolder> {

    private Context mContext;
    private List<Tarefa> mTarefa;
    private SparseBooleanArray selectedItens = new SparseBooleanArray();
    private int currentSelectedPosition;
    private static RecyclerItemClickListener mClickListener;
    private TarefaAdapterListener listener;


    public ItemTarefaAdapter(Context context, List<Tarefa> tarefas){
        this.mContext = context;
        this.mTarefa = tarefas;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_tarefas, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemTarefaAdapter.ViewHolder holder, int position) {
        Tarefa tarefa = mTarefa.get(position);
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        holder.bind(tarefa);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (listener != null) {
                    listener.onItemLongClick(position);

                }
                return true;
            }
        });

        if (currentSelectedPosition == position) {
            currentSelectedPosition = -1;
        }

        holder.tituloTextView.setText(tarefa.getTitulo());

        try {
            data = formato.parse(tarefa.getData());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.dataTextView.setText(formato.format(data));

        trocaPrioridade(holder, position);
        trocaStatus(holder, position);


        holder.prioridadeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == holder.prioridadeImageView) {
                    heartClick(position);
                }
            }
        });

        holder.checkImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == holder.checkImageView) {
                    checkClick(position);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return mTarefa.size();
    }

    private void trocaPrioridade(ItemTarefaAdapter.ViewHolder holder, int position) {
        Tarefa tarefa = mTarefa.get(position);
        switch (tarefa.getPrioridade()) {
            case 1:
                holder.prioridadeImageView.setColorFilter(mContext.getResources().getColor(R.color.green, mContext.getTheme()));
                holder.prioridadeTextView.setText(mContext.getString(R.string.Pri_Importante));
                break;
            case 2:
                holder.prioridadeImageView.setColorFilter(mContext.getResources().getColor(R.color.yellow, mContext.getTheme()));
                holder.prioridadeTextView.setText(mContext.getString(R.string.Pri_Urgente));
                break;
            case 3:
                holder.prioridadeImageView.setColorFilter(mContext.getResources().getColor(R.color.red, mContext.getTheme()));
                holder.prioridadeTextView.setText(mContext.getString(R.string.Pri_Emergente));
                break;
            default:
                holder.prioridadeImageView.setColorFilter(mContext.getResources().getColor(R.color.black, mContext.getTheme()));
                holder.prioridadeTextView.setText(mContext.getString(R.string.Prio_SemImportancia));
                break;
        }
    }

    private void trocaStatus(ItemTarefaAdapter.ViewHolder holder, int position) {
        Tarefa tarefa = mTarefa.get(position);
        if (tarefa.isStatus()) {
            holder.checkImageView.setImageResource(R.drawable.ic_valid_v);
            holder.checkImageView.setColorFilter(mContext.getResources().getColor(R.color.orange, mContext.getTheme()));
            holder.statusTextView.setText(mContext.getString(R.string.status_completed));

        } else {
            holder.checkImageView.setImageResource(R.drawable.ic_valid_f);
            holder.checkImageView.setColorFilter(mContext.getResources().getColor(R.color.black, mContext.getTheme()));
            holder.statusTextView.setText(mContext.getString(R.string.status_not_completed));

        }
    }
    public void checkClick(int position) {
        Tarefa tarefa = mTarefa.get(position);
        tarefa.setStatus(!tarefa.isStatus());
        notifyDataSetChanged();
    }
    private void heartClick(int position) {
        Tarefa tarefa = mTarefa.get(position);
        if (tarefa.getPrioridade() == 4) {
            tarefa.setPrioridade(0);
        } else {
            tarefa.setPrioridade(tarefa.getPrioridade() + 1);
        }
        notifyDataSetChanged();
    }

    public void setClickListener(RecyclerItemClickListener clickListener) {
        this.mClickListener = clickListener;
        //mClickListener = clickListener;
    }

    public List<Tarefa> getmTarefa() {
        return mTarefa;
    }

    public SparseBooleanArray getselectedItens() {
        return selectedItens;
    }

    public void deleteTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        for (Tarefa t : this.mTarefa) {
            if (t.isSelected()) {
                tarefas.add(t);
            }
        }
        this.mTarefa.removeAll(tarefas);
        notifyDataSetChanged();
        currentSelectedPosition = -1;

    }

    public void toggleSelection(int position) {
        currentSelectedPosition = position;

        if (selectedItens.get(position)) {
            selectedItens.delete(position);
            mTarefa.get(position).setSelected(false);
        } else {
            selectedItens.put(position, true);
            mTarefa.get(position).setSelected(true);
        }
        notifyItemChanged(position);
    }

    public void setListener(TarefaAdapterListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView tituloTextView;
        public TextView dataTextView;
        public TextView prioridadeTextView;
        public TextView statusTextView;
        public ImageView prioridadeImageView;
        public ImageView checkImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            tituloTextView = itemView.findViewById(R.id.text_titulo);
            dataTextView = itemView.findViewById(R.id.text_data);
            prioridadeTextView = itemView.findViewById(R.id.text_prioridade);
            statusTextView = itemView.findViewById(R.id.text_status);
            prioridadeImageView = itemView.findViewById(R.id.img_icon_flag_24);
            checkImageView = itemView.findViewById(R.id.img_icon_checkBox);

            itemView.setOnClickListener(this);
        }

        public void bind(Tarefa tarefa) {

            if (tarefa.isSelected()) {
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setShape(GradientDrawable.RECTANGLE);
                gradientDrawable.setCornerRadius(32f);
                gradientDrawable.setColor(Color.rgb(232, 240, 253));
                itemView.setBackground(gradientDrawable);

            } else {
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setShape(GradientDrawable.RECTANGLE);
                gradientDrawable.setCornerRadius(32f);
                gradientDrawable.setColor(Color.WHITE);
                itemView.setBackground(gradientDrawable);
            }
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

    public interface TarefaAdapterListener {

        void onItemLongClick(int position);
    }

}

