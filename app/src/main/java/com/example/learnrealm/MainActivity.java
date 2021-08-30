package com.example.learnrealm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learnrealm.JavaClass.RealmHelper;
import com.example.learnrealm.Model.DataModel;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText input_nama;
    private EditText input_nim;
    private Button btn_save;
    private Button btn_show;

    private int nim;
    private String nama;
    private Realm realm;
    private RealmHelper realmHelper;
    private DataModel dataModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inisisalisasi
        input_nama = findViewById(R.id.nama);
        input_nim = findViewById(R.id.nim);
        btn_save = findViewById(R.id.btnSimpan);
        btn_show = findViewById(R.id.btnTampil);

        //Set up realm
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(realmConfiguration);

        //Press button
        btn_show.setOnClickListener(this);
        btn_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        //Button Save
        if (view == btn_save){
            nim = Integer.parseInt(input_nim.getText().toString());
            nama = input_nama.getText().toString();

            if (!input_nim.equals("") && !input_nama.equals("")){
                dataModel = new DataModel();
                dataModel.setNim(nim);
                dataModel.setNama(nama);

                realmHelper = new RealmHelper(realm);
                realmHelper.Save(dataModel);

                Toast.makeText(getApplicationContext(), "Berhasil di simpan!", Toast.LENGTH_SHORT).show();

            }
            else {
                Toast.makeText(MainActivity.this, "Terdapat inputan yang kosong", Toast.LENGTH_SHORT).show();
            }
        }

        //Button Show
        if (view == btn_show){
            Intent intent = new Intent(getApplicationContext(), MahasiswaActivity.class);
            startActivity(intent);
        }
    }
}