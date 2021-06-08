package br.ed.ifsp.arq.listacontatos.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import br.ed.ifsp.arq.listacontatos.R;
import br.ed.ifsp.arq.listacontatos.model.Contato;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;




import java.util.List;

import br.ed.ifsp.arq.listacontatos.R;
import br.ed.ifsp.arq.listacontatos.model.Contato;
import br.ed.ifsp.arq.listacontatos.view.RecyclerItemClickListener;

public class ItemContatoAdapter extends RecyclerView.Adapter<ItemContatoAdapter.ViewHolder> {

    private Context mContext;
    private List<Contato> mContatos;
    private static RecyclerItemClickListener mClickListener;


    public ItemContatoAdapter (Context context, List<Contato> data){
        this.mContext = context;
        this.mContatos = data;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_contato,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ItemContatoAdapter.ViewHolder holder, int position) {
        Contato contato = mContatos.get(position);
        holder.nomeTextView.setText(nome.getNome());
        holder.apelidoTextView.setText(apelido.getApelido);
        holder.telefoneTextView.setText(telefone.getTelefone);
        holder.emailTextView.setText(email.getEmail);
        if (contato.isFavorite())) {
            holder.favoritoImageView.setColorFilter(mContext.getResources().getColor(R.color.red, mContext.getTheme()));
        } else {
            holder.favoritoImageView.setColorFilter(mContext.getResources().getColor(R.color.gray, mContext.getTheme()));
        }
        holder.favoritoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == holder.favoritoImageView) {
                    heartClick(position);
                }
            }
        });


    public void setClickListener(RecyclerItemClickListener clickListener){
        }

    private void heartClick(int position) {
        Contato contato = mContatos.get(position);
            contato.setFavorite(!contato.isFavorite());
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView nomeTextView;
        public TextView apelidoTextView;
        public TextView telefoneTextView;
        public TextView emailTextView;
        public ImageView favoritoImageView;

        public ViewHolder (View itemView) {
            super(itemView);
            nomeTextView = itemView.findViewById(R.id.text_nome);
            apelidoTextView = itemView.findViewById(R.id.text_apelido);
            telefoneTextView = itemView.findViewById(R.id.text_telefone);
            favoritoImageView = itemView.findViewById(R.id.img_icon_favorite);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            if(mClickListener != null){
                mClickListener.onItemClick(getAdapterPosition());
            }
        }
    }
    }

    @Override
    public int getItemCount() {
        return mContatos.size();
    }



}

