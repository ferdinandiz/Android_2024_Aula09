package com.fer.aula09_crudsimples;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fer.aula09_crudsimples.controller.PokemonAdapter;
import com.fer.aula09_crudsimples.controller.PokemonDatabase;
import com.fer.aula09_crudsimples.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editCod, editNome, editTipo, editNumero;
    Button btnSalvar, btnLimpar, btnExcluir;
    RecyclerView recyclerViewPkm;
    PokemonDatabase bdPokemon = new PokemonDatabase(this);
    PokemonAdapter pkmAdapter;
    ArrayList<Pokemon> pokemonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editCod = findViewById(R.id.edCodigo);
        editNome = findViewById(R.id.edNome);
        editNumero = findViewById(R.id.edNumero);
        editTipo = findViewById(R.id.edTipo);

        btnExcluir = findViewById(R.id.btnDeletar);
        btnSalvar = findViewById(R.id.btnCriar);
        btnLimpar = findViewById(R.id.btnLimpar);

        recyclerViewPkm = findViewById(R.id.lista);

        listarPokemon();

        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limparCampos();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cod = editCod.getText().toString();
                String nome = editNome.getText().toString();
                String tipo = editTipo.getText().toString();
                String numero = editNumero.getText().toString();

                if(nome.isEmpty()) editNome.setError("Campo Obrigatório");
                else if(cod.isEmpty()){
                    //INSERT
                    bdPokemon.addPokemon(new Pokemon(nome, tipo, numero));
                    Toast.makeText(MainActivity.this, "Inserido com Sucesso!", Toast.LENGTH_SHORT).show();
                    limparCampos();
                    listarPokemon();
                } else {
                    //UPDATE
                    Pokemon pkm = new Pokemon(Integer.parseInt(cod),nome, tipo, numero);
                    bdPokemon.updatePokemon(pkm);
                    Toast.makeText(MainActivity.this, "Atualizado com Sucesso!", Toast.LENGTH_SHORT).show();
                    limparCampos();
                    listarPokemon();
                }
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo = editCod.getText().toString();
                if (codigo.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Campo Vazio! Impossível Deletar!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Pokemon pkm = new Pokemon();
                    pkm.setCod(Integer.parseInt(codigo));
                    bdPokemon.excluirPokemon(pkm);
                    Toast.makeText(MainActivity.this, "Excluído com sucesso!", Toast.LENGTH_SHORT).show();
                    limparCampos();
                    listarPokemon();
                }
            }
        });

    }

    private void listarPokemon() {
        List<Pokemon> listaPokemon = bdPokemon.findAllPokemon();
        pokemonList = new ArrayList<>(listaPokemon);
        PokemonAdapter adapter = new PokemonAdapter(MainActivity.this, pokemonList);

        recyclerViewPkm.setHasFixedSize(true);
        recyclerViewPkm.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPkm.setAdapter(adapter);

        adapter.setOnItemClickListener(new PokemonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Pokemon pokemon) {
                editCod.setText(String.valueOf(pokemon.getCod()));
                editNumero.setText(String.valueOf(pokemon.getNumero()));
                editNome.setText(pokemon.getNome());
                editTipo.setText(pokemon.getTipo());
            }
        });
    }

    private void limparCampos() {
        editCod.setText("");
        editNome.setText("");
        editTipo.setText("");
        editNumero.setText("");
        editNome.requestFocus();
    }
}