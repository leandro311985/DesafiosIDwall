package com.cotacao.leandro.testeapp;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private Button btLogin;
    private Button btCadastrar;
    private EditText edtEmail;
    private EditText edtPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        btLogin = findViewById(R.id.btSignIn);
        btCadastrar = findViewById(R.id.btSignUp);

        edtEmail = findViewById(R.id.emailinput);
        edtPassword = findViewById(R.id.passwordinput);

        final DatabaseHelper dbHelper = new DatabaseHelper(this);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {

                if (!isValidEmail(edtEmail.getText().toString())){

                    Toast.makeText(LoginActivity.this, " Seu Email não é Válido", 2000).show();

                }else {

                    if (!validaçãovazia()) {
                        dbHelper.addUser(new Usuario(edtEmail.getText().toString(), edtPassword.getText().toString()));
                        Toast.makeText(LoginActivity.this, "\n" +
                                "Usuário adicionado", Toast.LENGTH_SHORT).show();
                        edtEmail.setText("");
                        edtPassword.setText("");
                }
                else {
                        Toast.makeText(LoginActivity.this, " Existe Campos vazios", Toast.LENGTH_SHORT).show();
                    }
            }
            }
        });
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validaçãovazia()) {
                    Usuario user = dbHelper.queryUser(edtEmail.getText().toString(), edtPassword.getText().toString());
                    if (user != null) {
                        Bundle mBundle = new Bundle();
                        mBundle.putString("user", user.getEmail());
                        Intent intent = new Intent(LoginActivity.this, UserActivity.class);
                        intent.putExtras(mBundle);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this, "Bem Vindo " + user.getEmail(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this, "Usuário não encontrado", Toast.LENGTH_SHORT).show();
                        edtPassword.setText("");
                    }
                }else{
                    Toast.makeText(LoginActivity.this, " Existe Campos vazios", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean validaçãovazia() {
        if (TextUtils.isEmpty(edtEmail.getText().toString()) || TextUtils.isEmpty(edtPassword.getText().toString())) {
            return true;
        }else {
            return false;
        }
    }


    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }


}

