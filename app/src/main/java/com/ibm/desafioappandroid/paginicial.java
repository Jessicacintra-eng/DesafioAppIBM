package com.ibm.desafioappandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.ibm.desafioappandroid.databinding.ActivityHomeBinding;

import org.w3c.dom.Text;

public class paginicial extends AppCompatActivity {




    private ListView tarefasi;
    private ListView tarefasSelf;
    private ListView tarefasBadge;

    private String [] itensinduction = {
        "Welcome to IBM", "Problem solving","Cloud & Cognitive",
        "Understanding Delivery","Agile","Design Thinking", "Career Journey"
    };
    private String [] itensself = {
            "BCG Program", "Working with Integrity","Privacy and Data Protection",
            "Cybersecurity","Building Inclusion","Social Computing Guidelines", "Export@IBM 2021 - The 5W’s",
            "Procurement","Safe and Healthy IBMer", "Corporate Instruction HR116 e HR117"
    };
    private String [] itensbadge = {
            "Enterprise Design Thinking","IBM Agile Explorer", "Virtual Colaborator"
    };


    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHome.toolbar);
        binding.appBarHome.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //     Snackbar.make(view,"", Snackbar.LENGTH_LONG)
             //           .setAction("", null).show();

            }

        });



        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        TextView nome = findViewById(R.id.yourname);

       // Bundle extras = getIntent().getExtras();
        // nome.setText(user);



        tarefasi = findViewById(R.id.tarefasinduction);
        tarefasi.setVisibility(View.GONE);

        TextView induct = findViewById(R.id.induction2);

        induct.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(tarefasi.getVisibility()== View.GONE){
                 //  induct.setCompoundDrawablesRelative (getDrawable(R.drawable.ic_vector__close),null,null, null);
                    tarefasi.setVisibility(View.VISIBLE);
                } else {
                  //  induct.setCompoundDrawablesRelative (getDrawable(R.drawable.ic_vector_open),null,null, null);
                    tarefasi.setVisibility(View.GONE);
                }
            }


        });

        ArrayAdapter <String> adaptadorinduction = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1,
                itensinduction
        );

        tarefasi.setAdapter(adaptadorinduction);
        tarefasi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView check =(CheckedTextView)view;
                check.setChecked(!check.isChecked());
            }
        });
        tarefasSelf = findViewById(R.id.listaselfpaced);
        tarefasSelf.setVisibility(View.GONE);

        TextView self = findViewById(R.id.induction3);

        self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tarefasSelf.getVisibility()== View.GONE){
                    tarefasSelf.setVisibility(View.VISIBLE);
                } else {
                    tarefasSelf.setVisibility(View.GONE);
                }
            }


        });

        ArrayAdapter <String> adaptadorself = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1,
                itensself
        );

        tarefasSelf.setAdapter(adaptadorself);
        tarefasSelf.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView check =(CheckedTextView)view;
                check.setChecked(!check.isChecked());
            }
        });
        tarefasBadge = findViewById(R.id.listabadges);
        tarefasBadge.setVisibility(View.GONE);

        TextView badge = findViewById(R.id.induction4);

        badge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tarefasBadge.getVisibility()== View.GONE){
                    tarefasBadge.setVisibility(View.VISIBLE);
                } else {
                    tarefasBadge.setVisibility(View.GONE);
                }
            }


        });

        ArrayAdapter <String> adaptadorbadge = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_multiple_choice,
                android.R.id.text1,
                itensbadge
        );

        tarefasBadge.setAdapter(adaptadorbadge);
        tarefasBadge.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView check =(CheckedTextView)view;
                check.setChecked(!check.isChecked());
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }








}