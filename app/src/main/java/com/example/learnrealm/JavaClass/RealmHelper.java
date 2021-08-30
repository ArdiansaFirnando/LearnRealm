package com.example.learnrealm.JavaClass;

import android.util.Log;

import com.example.learnrealm.Model.DataModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm) {

        this.realm = realm;
    }

    //Function untuk menyimpan data
    public void Save(DataModel dataModel){

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (realm != null){
                    Log.d("Create", "execute: Database telah dibuat");

                    Number current_id = realm.where(DataModel.class).max("id");
                    int nextId;
                    if (current_id == null){
                        nextId = 1;
                    }
                    else {
                        nextId = current_id.intValue() + 1;
                    }

                    dataModel.setId(nextId);
                    DataModel model = realm.copyToRealm(dataModel);
                }
                else {
                    Log.d("Create", "execute: Database gagal dibuat");
                }
            }
        });
    }


    //Function untuk Memanggil data
    public List<DataModel> getAllData(){
        RealmResults<DataModel> results = realm.where(DataModel.class).findAll();
        return results;
    }

    //Function untuk mengupdate data
    public void update(final Integer id, final Integer nim, final String nama){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                DataModel model = realm.where(DataModel.class)
                        .equalTo("id", id)
                        .findFirst();
                model.setNim(nim);
                model.setNama(nama);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.e("pppp", "onSuccess: Update Successfully");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }

    //Function untuk menghapus data

    public void Delete(int id){
        
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<DataModel> results = realm.where(DataModel.class).equalTo("id", id).findAll();
                results.deleteFromRealm(0);
            }
        });
    }
}
