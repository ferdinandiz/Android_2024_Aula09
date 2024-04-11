package com.fer.aula09_crudsimples.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.fer.aula09_crudsimples.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class PokemonDatabase extends SQLiteOpenHelper {
   private static final int versao = 1;
   private static final String nomeDB = "bb_pokemon";
   private static final String c_cod = "cod";
   private static final String c_nome = "nome";
   private static final String c_tipo = "tipo";
   private static final String c_numero = "numero";
   private static final String tb_pokemon = "pokedex";
   public static Context contexto;

   public PokemonDatabase(@Nullable Context context) {
      super(context, nomeDB, null, versao);
      contexto = context;
   }


   @Override
   public void onCreate(SQLiteDatabase sqLiteDatabase) {
      //CREATE TABLE pokemon (cod INTEGER PRIMARY KEY,....)
      String query = "CREATE TABLE "+tb_pokemon + "(" +
              c_cod + " INTEGER PRIMARY KEY, "+
              c_nome + " TEXT, "+
              c_tipo + " TEXT, "+
              c_numero + " TEXT)";
      sqLiteDatabase.execSQL(query);
   }

   @Override
   public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
   }

   public Pokemon findOnePokemon(int cod){
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor cursor = db.query(
              tb_pokemon,
              new String[]{c_cod, c_nome, c_tipo, c_numero},
              c_cod+" = ?",
              new String[]{String.valueOf(cod)},
              null, null,null);
      if(cursor != null) {
         cursor.moveToFirst();
      }
      else {
         return null;
      }
      Pokemon pkm = new Pokemon(
              Integer.parseInt(cursor.getString(0)),
              cursor.getString(1),
              cursor.getString(2),
              cursor.getString(3)
      );
      return pkm;
   }

   public void addPokemon(Pokemon pkm){
      SQLiteDatabase db = getWritableDatabase();
      ContentValues values = new ContentValues();
      values.put(c_nome, pkm.getNome());
      values.put(c_numero, pkm.getNumero());
      values.put(c_tipo, pkm.getTipo());
      db.insert(tb_pokemon,null, values);
      db.close();
   }

   public void excluirPokemon(Pokemon pkm){
      SQLiteDatabase db = getWritableDatabase();
      db.delete(
              tb_pokemon,
              c_cod+" = ?",
              new String[]{String.valueOf(pkm.getCod())}
      );
      db.close();
   }

   public void updatePokemon(Pokemon pkm){
      SQLiteDatabase db = getWritableDatabase();
      ContentValues values = new ContentValues();
      values.put(c_nome, pkm.getNome());
      values.put(c_numero, pkm.getNumero());
      values.put(c_tipo, pkm.getTipo());
      db.update(
              tb_pokemon,
              values,
              c_cod+" = ?",
              new String[]{String.valueOf(pkm.getCod())}
      );
   }

   public List<Pokemon> findAllPokemon(){
      SQLiteDatabase db = this.getReadableDatabase();
      List<Pokemon> pkmLista = new ArrayList<>();

      String query = "SELECT * FROM "+tb_pokemon;
      Cursor cursor = db.rawQuery(query, null);
      if(cursor.moveToFirst()){
         do{
            Pokemon pkm = new Pokemon();
            pkm.setCod(Integer.parseInt(cursor.getString(0)));
            pkm.setNome(cursor.getString(1));
            pkm.setTipo(cursor.getString(2));
            pkm.setNumero(cursor.getString(3));
            pkmLista.add(pkm);
         }while (cursor.moveToNext());
      }
      return pkmLista;
   }
}
