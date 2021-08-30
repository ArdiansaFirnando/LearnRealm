package com.example.learnrealm.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnrealm.Model.DataModel;
import com.example.learnrealm.R;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

public class MahasiswaAdapter extends RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder> {

    private List<DataModel> dataModels;
    private Callback callback;

    public interface Callback{
        void onClick(int position);
    }


    public MahasiswaAdapter(List<DataModel> dataModelList, Callback callback) {
        this.dataModels = dataModelList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MahasiswaAdapter.MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_mahasiswa, parent, false);
        return new MahasiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaAdapter.MahasiswaViewHolder holder, int position) {
//        holder.nim.setText(dataModels.get(position).getNim());
        holder.nama.setText(dataModels.get(position).getNama());
    }

    @Override
    public int getItemCount() {
        return (dataModels != null) ? dataModels.size() : 0;
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder {

        private TextView nim;
        private TextView nama;
        private CardView cardView;

        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);

            nim = itemView.findViewById(R.id.tvNim);
            nama = itemView.findViewById(R.id.tvNama);
            cardView = itemView.findViewById(R.id.cardview);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onClick(getLayoutPosition());
                }
            });
        }
    }
}
