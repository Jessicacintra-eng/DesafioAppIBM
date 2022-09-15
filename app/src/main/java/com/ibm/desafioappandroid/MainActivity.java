package com.ibm.desafioappandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.ibm.desafioappandroid.config.ConfiguracaoFirebase;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth autenticacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btnentrar = findViewById(R.id.btnEntrar);

        btnentrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Login.class);
                startActivity(i);
                Log.d("oi","entrounobtn");

            }


        });
        TextView create = findViewById(R.id.createland);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Cadastro.class);
                startActivity(i);
                Log.d("oi","entrounobtn");

            }


        });


    }

    @Override
    protected void onStart() {
        super.onStart();

       // verificarUsuaruioLogado();
    }

    public void verificarUsuaruioLogado(){
        autenticacao= ConfiguracaoFirebase.getFirebaseAutenticacao();
        if(autenticacao.getCurrentUser()!= null){
            abreMain();
        }
    }
    public void abreMain(){
        Intent i = new Intent(MainActivity.this, paginicial.class);
        startActivity(i);
    }




}