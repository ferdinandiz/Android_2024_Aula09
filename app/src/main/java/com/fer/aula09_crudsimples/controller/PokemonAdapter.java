package com.fer.aula09_crudsimples.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fer.aula09_crudsimples.R;
import com.fer.aula09_crudsimples.model.Pokemon;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {

   Context contexto;
   ArrayList<Pokemon> itemDataPk;

   public PokemonAdapter(Context contexto, ArrayList<Pokemon> itemDataPk) {
      this.contexto = contexto;
      this.itemDataPk = itemDataPk;
   }

   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(contexto).inflate(R.layout.item_view, parent,false);
      return new ViewHolder(view);
   }

   @Override
   public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      holder.nome.setText(itemDataPk.get(position).getNome());
      holder.codigo.setText(String.valueOf(itemDataPk.get(position).getCod()));
      holder.numero.setText(String.valueOf(itemDataPk.get(position).getNumero()));
      holder.tipo.setText(itemDataPk.get(position).getTipo());
   }

   @Override
   public int getItemCount() {
      return itemDataPk.size();
   }

   private OnItemClickListener listener;

   public interface OnItemClickListener {
      void onItemClick(Pokemon pokemon);
   }

   public void setOnItemClickListener(OnItemClickListener listener){
      this.listener = listener;
   }

   public class ViewHolder extends RecyclerView.ViewHolder{
      TextView tipo, nome, numero, codigo;
      public ViewHolder(@NonNull View itemView) {
         super(itemView);
         nome = itemView.findViewById(R.id.nome);
         codigo = itemView.findViewById(R.id.codigo);
         numero = itemView.findViewById(R.id.numero);
         tipo = itemView.findViewById(R.id.tipo);

         itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if(listener != null && getAdapterPosition() != RecyclerView.NO_POSITION){
                  listener.onItemClick(itemDataPk.get(getAdapterPosition()));
               }
            }
         });
      }
   }
}
