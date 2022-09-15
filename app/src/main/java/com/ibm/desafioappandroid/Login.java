package com.ibm.desafioappandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.ibm.desafioappandroid.config.ConfiguracaoFirebase;
import com.ibm.desafioappandroid.model.Usuario;

public class Login extends AppCompatActivity {
    private String msg = "";
    private Usuario usuario;
    private FirebaseAuth autenticacao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView criartxt = findViewById(R.id.jatenho);



        criartxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Cadastro.class);
                startActivity(i);

            }


        });

        EditText user =findViewById(R.id.loginemail);

        EditText passcode =findViewById(R.id.loginpasscode);

        Button login = findViewById(R.id.btnlogin);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = user.getText().toString();
                String senha =passcode.getText().toString();

                if( TextUtils.isEmpty(email) || TextUtils.isEmpty(senha)){
                    if (TextUtils.isEmpty(email)) {
                        user.setError("Your email is required!");
                    } else {
                        passcode.setError("Your passcode is required");
                    }

                }else{ //if(email.equals("jessica@ibm.com") && senha.equals("123456")){
                    //Intent i = new Intent(Login.this, paginicial.class);
                    //startActivity(i);
                    usuario=new Usuario();
                    usuario.setSenha(senha);
                    usuario.setEmail(email);
                    validarLogin();

                }//else{
                   // Toast.makeText(Login.this,"Invalid user or password",Toast.LENGTH_SHORT).show();
                //}




            }

            private void enviar() {
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }


        });

        TextView forgot = findViewById(R.id.fgtpass);

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog();
            }

            final EditText input = new EditText(Login.this);
            private void dialog(){
                final AlertDialog dialog = new AlertDialog.Builder(Login.this)
                        .setTitle("Forgot your password?")
                        .setMessage("Type in your e-mail and we’ll send you a new one!")
                        .setPositiveButton(android.R.string.ok, null) //Set to null. We override the onclick
                        .setNegativeButton(android.R.string.cancel, null)
                        .setView(input)
                        .setIcon(android.R.drawable.ic_dialog_email)
                        .create();

                        input.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                        input.setHint("Email");
                        input.setCursorVisible(true);
                        input.layout(200,8,20,8);
                        input.setMaxWidth(20);
                        input.setPadding(
                                40,
                                20,
                                20,
                                25
                        );


                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                     @Override
                    public void onShow(DialogInterface dialogInterface) {

                        Button button = ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE);
                        button.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                if(TextUtils.isEmpty(input.getText().toString())|| (input.getText().toString().indexOf("@")==-1 && input.getText().toString().indexOf("@")<3)){
                                    Log.d("teste1",String.valueOf(TextUtils.isEmpty(input.getText().toString())));
                                    Log.d("IF", String.valueOf(input.getText().toString().indexOf("@")));
                                    input.setError("Invalid email!");
                                }else {
                                    dialog.dismiss();
                                    new AlertDialog.Builder(Login.this)
                                            .setMessage("A new password was sent to your email.")
                                            .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent i = new Intent(Login.this, Login.class);
                                                    startActivity(i);
                                                }
                                            })
                                            .show();
                                }

                            }
                        });
                    }
                });
                dialog.show();

            }





        });



    }

public void validarLogin(){
autenticacao= ConfiguracaoFirebase.getFirebaseAutenticacao();
autenticacao.signInWithEmailAndPassword(
        usuario.getEmail(),
        usuario.getSenha()
).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()){
            Intent i = new Intent(Login.this, paginicial.class);
            startActivity(i);
            finish();
        }else{
            String exception =" ";
            try{
                throw task.getException();
            }catch (FirebaseAuthInvalidUserException e){
                exception = "You don't have an account.";
            }catch (FirebaseAuthInvalidCredentialsException e){
                exception = "Use a valid email or passcode";
            }catch (Exception e){
                exception ="Error: "+ e.getMessage();
                e.printStackTrace();
            }
            Toast.makeText(Login.this,exception,Toast.LENGTH_SHORT).show();

        }
    }
});
}

}
