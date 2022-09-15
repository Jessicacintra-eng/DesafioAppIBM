package com.ibm.desafioappandroid.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.ibm.desafioappandroid.Login;
import com.ibm.desafioappandroid.MainActivity;
import com.ibm.desafioappandroid.R;
import com.ibm.desafioappandroid.config.ConfiguracaoFirebase;
import com.ibm.desafioappandroid.databinding.FragmentSlideshowBinding;
import com.ibm.desafioappandroid.paginicial;

import org.testng.reporters.jq.Main;

public class SlideshowFragment extends Fragment {
    private FirebaseAuth autenticacao;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
    }

    private void setContentView() {
        //autenticacao= ConfiguracaoFirebase.getFirebaseAutenticacao();
        //autenticacao.signOut();
        Intent i = new Intent(getContext(), MainActivity.class);
        startActivity(i);
        onDestroy();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }
}