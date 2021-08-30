package com.example.learnrealm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.learnrealm.Adapter.MahasiswaAdapter;
import com.example.learnrealm.JavaClass.RealmHelper;
import com.example.learnrealm.Model.DataModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MahasiswaActivity extends AppCompatActivity {

    private Realm realm;
    private RealmHelper realmHelper;

    private RecyclerView recyclerView;
    private MahasiswaAdapter mahasiswaAdapter;
    private List<DataModel> dataModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);

        recyclerView = findViewById(R.id.recyclerview);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        //Setup Realm
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(realmConfiguration);

        realmHelper = new RealmHelper(realm);
        dataModelArrayList = new ArrayList<>();

        dataModelArrayList = realmHelper.getAllData();

        show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mahasiswaAdapter.notifyDataSetChanged();
        show();
    }

    public void show(){
        mahasiswaAdapter = new MahasiswaAdapter(dataModelArrayList, new MahasiswaAdapter.Callback() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getApplicationContext(), "posisi saat ini : "+ dataModelArrayList.get(position), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("id", dataModelArrayList.get(position).getId());
                intent.putExtra("nama", dataModelArrayList.get(position).getNim());
                intent.putExtra("nama", dataModelArrayList.get(position).getNama());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mahasiswaAdapter);
    }
}