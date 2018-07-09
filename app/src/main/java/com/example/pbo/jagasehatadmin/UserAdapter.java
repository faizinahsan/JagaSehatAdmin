package com.example.pbo.jagasehatadmin;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by M FaizinAhsan on 7/4/2018.
 */

public class UserAdapter extends ArrayAdapter<User> {
    public UserAdapter(Activity context, ArrayList<User> adapter){
        super(context,0,adapter);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,
                    false);
        }
        User currentUserAdapter = getItem(position);
        TextView nama = (TextView) listItemView.findViewById(R.id.nama_lengkap);
        TextView jk = (TextView) listItemView.findViewById(R.id.jk_lengkap);
        TextView email = (TextView) listItemView.findViewById(R.id.email_lenkap);
        TextView usia = (TextView) listItemView.findViewById(R.id.usia_lenkap);
        TextView pekerjaan = (TextView) listItemView.findViewById(R.id.pekerjaan_lenkap);
        TextView pendidikan = (TextView) listItemView.findViewById(R.id.pendidikan_lenkap);
        TextView date = (TextView) listItemView.findViewById(R.id.date_lenkap);
        nama.setText(currentUserAdapter.getNama());
        jk.setText(currentUserAdapter.getJk());
        email.setText(currentUserAdapter.getEmail());
        usia.setText(currentUserAdapter.getUsia());
        pekerjaan.setText(currentUserAdapter.getPekerjaan());
        pendidikan.setText(currentUserAdapter.getPendidikan());
        date.setText(currentUserAdapter.getDate());
        return listItemView;
    }
}
