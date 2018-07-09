package com.example.pbo.jagasehatadmin;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class MainActivity extends AppCompatActivity {
    Button downloadBtn,statistikDataBtn;
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("user");
    int size,i=0;
    int jmlSD,jmlSMP,jmlSMA,jmlPT;
    TextView jmlPengguna;
    Query queryMahasiswa,query;
    Query querySD,querySMP,querySMA,queryPT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jmlPengguna = (TextView) findViewById(R.id.jmlPengguna);
        downloadBtn = (Button) findViewById(R.id.download_data);
        statistikDataBtn = (Button) findViewById(R.id.data_statistik);
        final String data_uri = "https://jaga-sehat-db.firebaseio.com/" +
                ".json?print=pretty&format=export&download=jaga-sehat-db-export.json&auth=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpYXQiOjE1MzA2Mjg2MzQsImV4cCI6MTUzMDYzMjIzNCwidiI6MCwiYWRtaW4iOnRydWV9.M_duwvjDjLA8IK6YuSE0avPaZiU5CeyEdcUxqYL3K2Y";

        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(data_uri));
                startActivity(i);
            }
        });
        statistikDataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,DataPengguna.class));
            }
        });
        // in this example, a LineChart is initialized from xml
        getJumlahPengguna();
//        getJumlahPekerjaan();
        getJumlahPendidikan();
        getJumlahPekerjaan();
        getJK();

    }


    public void getJumlahPengguna(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                User user = dataSnapshot.getValue(User.class);
                //Now you have an object of the User class and can use its getters like this
//                tv1.setText(user.getNama());
//                tv2.setText(user.getUsia());
                //Jika diluar method onChanged maka data tidak akan terbaca
                size = (int) dataSnapshot.getChildrenCount();
                jmlPengguna.setText(size+" Orang");
                for (DataSnapshot penggunaSnap: dataSnapshot.getChildren()){
                    Map<String, Object> newPost = (Map<String, Object>) penggunaSnap.getValue();
//                    mahasiswa.add(i,newPost.get("pekerjaan").toString());
//                    i++;
//                    Log.v("KEY",penggunaSnap.getKey());
//                    Log.v("USIA", ""+penggunaSnap.child("usia").getValue());
                }
//                Toast.makeText(MainActivity.this, "Jumlah Orang dengan " +
//                        "Pekerjaan:"+mahasiswa.size(), Toast
//                        .LENGTH_SHORT)
//                        .show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();

    }

    public void getJumlahPekerjaan(){
        DatabaseReference hello = FirebaseDatabase.getInstance().getReference().child("user");
        final TextView pnsTniPorli = (TextView) findViewById(R.id.pns_porli_tni);
        final TextView pegawaiSwasta = (TextView) findViewById(R.id.pegawai_swasta);
        final TextView wiraswasta = (TextView) findViewById(R.id.Wiraswasta);
        final TextView mahasiswa = (TextView) findViewById(R.id.mahasiswa);
        final TextView pelajar = (TextView) findViewById(R.id.pelajar);
        final TextView lainnya = (TextView) findViewById(R.id.lainnya);
//        https://stackoverflow.com/questions/42580323/how-to-get-the-number-of-children-with-specific-value-in-firebase-android
        hello.orderByChild("pekerjaan").equalTo("PNS/TNI/Porli").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pnsTniPorli.setText("PNS/TNI/Porli:\n"+dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        hello.orderByChild("pekerjaan").equalTo("Pegawai Swasta").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pegawaiSwasta.setText("Pegawai Swasta:\n"+dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        hello.orderByChild("pekerjaan").equalTo("Wiraswasta").addValueEventListener(new
                                                                                       ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                wiraswasta.setText("Wiraswasta:\n"+dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        hello.orderByChild("pekerjaan").equalTo("Mahasiswa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mahasiswa.setText("Mahasiswa:\n"+dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        hello.orderByChild("pekerjaan").equalTo("Pelajar").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pelajar.setText("Pelajar:\n"+dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        hello.orderByChild("pekerjaan").equalTo("Lainnya").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lainnya.setText("Lainnya:\n"+dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getJumlahPendidikan(){
        DatabaseReference hello = FirebaseDatabase.getInstance().getReference().child("user");
        final TextView sd = (TextView) findViewById(R.id.sd);
        final TextView smp = (TextView) findViewById(R.id.smp);
        final TextView sma = (TextView) findViewById(R.id.sma);
        final TextView pt = (TextView) findViewById(R.id.pt);
        hello.orderByChild("pendidikan").equalTo("SD").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sd.setText("SD: "+dataSnapshot.getChildrenCount());
            }
             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
        });
        hello.orderByChild("pendidikan").equalTo("SMP").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                smp.setText("SMP: "+dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        hello.orderByChild("pendidikan").equalTo("SMA").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sma.setText("SMA: "+dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        hello.orderByChild("pendidikan").equalTo("PT").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pt.setText("PT: "+dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void getJK(){
        final TextView pria = (TextView) findViewById(R.id.jmlPria);
        final TextView wanita = (TextView) findViewById(R.id.jmlWanita);
        DatabaseReference hello = FirebaseDatabase.getInstance().getReference().child("user");
        hello.orderByChild("jk").equalTo("Laki-Laki").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pria.setText("Pria: "+dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        hello.orderByChild("jk").equalTo("perempuan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                wanita.setText("Wanita: "+dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
