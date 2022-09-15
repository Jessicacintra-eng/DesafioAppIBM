package com.ibm.desafioappandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ibm.desafioappandroid.config.ConfiguracaoFirebase;
import com.ibm.desafioappandroid.helper.Base64Custom;
import com.ibm.desafioappandroid.model.Usuario;

import java.util.Objects;

public class Cadastro extends AppCompatActivity {
    private EditText fName,fEmail, fPasscode,fPasscodeConfirmation;
    private Button btnRegister;
    private FirebaseAuth autenticacao;
    private Usuario usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        fName=findViewById(R.id.nameregister);
        fEmail=findViewById(R.id.emailregister);
        fPasscode=findViewById(R.id.registerpasscode);
        fPasscodeConfirmation=findViewById(R.id.registerpasscodeconfirmation);
        btnRegister=findViewById(R.id.btnregister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText=fName.getText().toString();
                String emailText=fEmail.getText().toString();
                String passText=fPasscode.getText().toString();
                String passTextConf=fPasscodeConfirmation.getText().toString();

                Log.d("teste1",nameText);
                Log.d("teste2",emailText);
                Log.d("teste1",passText);
                Log.d("teste1",passTextConf);

                if(!TextUtils.isEmpty(nameText) || !TextUtils.isEmpty(emailText) || !TextUtils.isEmpty(passText) || !TextUtils.isEmpty(passTextConf)){

                        usuario=new Usuario();
                        usuario.setName(nameText);
                        usuario.setEmail(emailText);
                        usuario.setSenha(passText);
                        usuario.setConfirmasenha(passTextConf);
                        registerUser();
                    //Intent i = new Intent(Cadastro.this, paginicial.class);
                    //i.putExtra("user",nameText);
                    //startActivity(i);

                }else{
                    Toast.makeText(Cadastro.this,"Fulfill all the fields",Toast.LENGTH_SHORT).show();
                }

            }
        });

        TextView jatenho = findViewById(R.id.jatenho);

        jatenho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Cadastro.this, Login.class);
                startActivity(i);
               // Log.d("oi","entrounobtn");

            }


        });
    }

    public void registerUser(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String idUser = Base64Custom.codificarbase64(usuario.getEmail());
                    Log.d("teste",idUser);
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("users");

                    myRef.setValue(idUser);

                    usuario.setIdUser(idUser);
                    usuario.salvar();
                    Intent i = new Intent(Cadastro.this, MainActivity.class);
                    startActivity(i);
                }else{
                    String exception ="";
                    try {
                        throw Objects.requireNonNull(task.getException());
                    }catch (FirebaseAuthWeakPasswordException e){
                        exception = "Use a stronger passcode";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        exception = "Use a valid email";
                    }catch (FirebaseAuthUserCollisionException e){
                        exception = "You already have an account";
                    }catch (Exception e){
                        exception ="Error: "+ e.getMessage();
                        e.printStackTrace();
                    }
                    Toast.makeText(Cadastro.this,exception,Toast.LENGTH_SHORT).show();
                }
            }
        }) ;
    }

}
