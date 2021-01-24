package maruf.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import maruf.com.myapplication.Ranting.MenuUtama;
import maruf.com.myapplication.Ranting.coba.setsaldo;
import maruf.com.myapplication.Ranting.setter.kaslist;
import maruf.com.myapplication.Ranting.setterr.setkas;
import maruf.com.myapplication.Ranting.tampilan.MainUbah;

public class tampil extends AppCompatActivity {
    private ListView listviewkas;
    public  static  final String KS_NAMA="nama";
    public  static  final String KS_ID="id";
    public  static  final String KS_RANTING="ranting";
    public  static  final String KS_JUMLAH="jumlah";
    public  static  final String KS_DESK="desk";
    public  static  final String KS_TITLE="title";
    EditText caari;
    TextView txtapayah;
    DatabaseReference databasw1;
    List<setkas> kaslist;
    Button klik;
    DatabaseReference databasekas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tampil);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databasw1 = database.getReference("message").child("ms");
        databasekas = FirebaseDatabase.getInstance().getReference("pemasukan");
        kaslist = new ArrayList<>();
        listviewkas = (ListView) findViewById(R.id.lisviewkas);
        txtapayah=(TextView) findViewById(R.id.idapayah);
caari=findViewById(R.id.iidcari);
klik=findViewById(R.id.idklikcari);

klik.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        cari();
    }
});




        databasw1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                setsaldo value = dataSnapshot.getValue(setsaldo.class);
                txtapayah.setText(value.getSaldo());

                int hasil;
             TextView   tuunjuk = (TextView) findViewById(R.id.idapayah1);
                NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
                hasil= Integer.parseInt(txtapayah.getText().toString());
                Locale loca = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(loca);
                tuunjuk.setText(formatRupiah.format((double)hasil));
                txtapayah.setVisibility(View.INVISIBLE);


            }



            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();


        databasekas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                kaslist.clear();

                for(DataSnapshot setnapasopt:dataSnapshot.getChildren()){
                    setkas kas=setnapasopt.getValue(setkas.class);
                    kaslist.add(kas);
                }
               kaslist adapter=new kaslist(tampil.this,kaslist);
                listviewkas.setAdapter(adapter);
//
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    private void cari() {
        String d=caari.getText().toString();
        DatabaseReference query = FirebaseDatabase.getInstance().getReference().child("pemasukan");
        query.orderByChild("desk").startAt(d).endAt(d+"\uf8ff").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                kaslist.clear();
                for(DataSnapshot setnapasopt:dataSnapshot.getChildren()){
                    setkas kas=setnapasopt.getValue(setkas.class);
                    kaslist.add(kas);
                }
             kaslist adapter=new kaslist(tampil.this,kaslist);
                listviewkas.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void hhome(View view) {

        Intent gjf =new Intent(tampil.this, MenuUtama.class);
        finish();
        startActivity(gjf);

    }
    }

