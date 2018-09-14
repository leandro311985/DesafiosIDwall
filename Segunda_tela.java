package com.cotacao.leandro.testeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Segunda_tela extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_tela);

        ImageView icone = findViewById(R.id.imgIcone);
        TextView nome = findViewById(R.id.txtNome);


        Bundle extras = getIntent().getExtras();

        if(extras != null){
            String txtNome = extras.getString("nome");

            int iconeid = extras.getInt("icone");

            icone.setImageResource(iconeid);
            nome.setText(txtNome);

        }
    }
}
