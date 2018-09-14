package com.cotacao.leandro.testeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;



public class UserActivity extends AppCompatActivity {

    String[] listaNomes = {"HUSK","HOUND","LABRADOR",
            "PUG"};

    int[] listaIcones = {R.drawable.husky,R.drawable.hound,R.drawable.labrador,R.drawable.pug};

    String[] listaDescricoes = {"Apesar da sua cara de lobo, o Husky Siberiano é um cão muito sociável e adora estar na companhia de outros animais ou seres humanos."
             ,

            "O hound, galgo ou cão de sala, como é mais conhecido em Portugal, é um tipo de cão que auxilia os caçadores em rastrear ou perseguir o animal a ser caçado.",


                    "Os filhotes de Labrador são muito fofos e cativantes. E quando adultos eles continuam tão simpáticos quanto antes." ,

                  "Os Pugs apareceram, na Europa, inicialmente na Holanda, possivelmente em consequência da famosa companhia mercantil."
                          };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        ListView minhaLista = findViewById(R.id.minhaLista);

        final MeuAdapatador meuAdaptador;
        meuAdaptador = new MeuAdapatador(getApplicationContext(), R.layout.minha_celula);

        int i = 0;
        for(String nome:listaNomes){
            DadosPersonagem dadosPersonagem;
            dadosPersonagem = new DadosPersonagem(listaIcones[i],nome,listaDescricoes[i]);
            meuAdaptador.add(dadosPersonagem);
            i++;
        }

        minhaLista.setAdapter(meuAdaptador);

        minhaLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                DadosPersonagem dadosPersonagem;
                dadosPersonagem = (DadosPersonagem) meuAdaptador.getItem(i);

                Intent intent = new Intent(UserActivity.this, Segunda_tela.class);
                intent.putExtra("nome", dadosPersonagem.getNome());
                intent.putExtra("icone", dadosPersonagem.getIcone());
                startActivity(intent);

            }
        });
    }


}
