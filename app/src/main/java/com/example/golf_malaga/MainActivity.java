package com.example.golf_malaga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.golf_malaga.AllCampsGolf.CampsGolfList;
import com.example.golf_malaga.Login.LoginClass;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    RecyclerView campbeginner, campadvance;
    ArrayList<CampsAdvance_MODEL> campbeginner_models;
    ArrayList<CampsHoyos_MODEL> campadvance_models;
    CampsAdvance_ADAPTER campbeginner_adapter;
    CampsHoyos_ADAPTER campadvance_adapter;
    LinearLayoutManager manager;
    TextView seeallcamps;
    Button explore;
    ImageView imageprofile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        campbeginner = findViewById(R.id.campsbeginners_recyclerview);

        campbeginner_models = new ArrayList<>();
        campbeginner_models.add(new CampsAdvance_MODEL(R.drawable.casares, "Casares Golf Club", "Casares"));
        campbeginner_models.add(new CampsAdvance_MODEL(R.drawable.estepona, "Valle Romano Golf", "Estepona"));
        campbeginner_models.add(new CampsAdvance_MODEL(R.drawable.mijas, "Miraflores Golf Club", "Mijas"));
        campbeginner_models.add(new CampsAdvance_MODEL(R.drawable.marbella, "Golf Río Real", "Marbella"));

        campbeginner_adapter = new CampsAdvance_ADAPTER(this, campbeginner_models);
        manager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        campbeginner.setAdapter(campbeginner_adapter);
        campbeginner.setLayoutManager(manager);

        campadvance = findViewById(R.id.campsadvanced_recyclerview);

        campadvance_models = new ArrayList<>();
        campadvance_models.add(new CampsHoyos_MODEL(R.drawable.laquinagolf, "La Quinta Golf", "Benahavís"));
        campadvance_models.add(new CampsHoyos_MODEL(R.drawable.laurogolf, "Lauro Golf", "Alhaurín de la Torre"));
        campadvance_models.add(new CampsHoyos_MODEL(R.drawable.atalayagolf, "Atalaya Golf", "Estepona"));
        campadvance_models.add(new CampsHoyos_MODEL(R.drawable.villapadierna, "Villa Padierna Golf", "Benahavís"));
        campadvance_models.add(new CampsHoyos_MODEL(R.drawable.mijasgolf, "Mijas Golf Club", "Mijas"));

        campadvance_adapter = new CampsHoyos_ADAPTER(this, campadvance_models);
        manager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);

        campadvance.setAdapter(campadvance_adapter);
        campadvance.setLayoutManager(manager);

        imageprofile = findViewById(R.id.imageprofile);


        explore = findViewById(R.id.explorecampsgolf_button);
        seeallcamps = findViewById(R.id.seeallcampsgolf_link);


        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CampsGolfList.class));
            }
        });

        seeallcamps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CampsGolfList.class));
            }
        });
        imageprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginClass.class));
            }
        });

    }


}