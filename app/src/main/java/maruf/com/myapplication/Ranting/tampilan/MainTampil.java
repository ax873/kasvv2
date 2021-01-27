package maruf.com.myapplication.Ranting.tampilan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import maruf.com.myapplication.R;
import maruf.com.myapplication.Ranting.MenuUtama;
import maruf.com.myapplication.Ranting.coba.setsaldo;
import maruf.com.myapplication.Ranting.setter.kaslist;
import maruf.com.myapplication.Ranting.setterr.setkas;

public class MainTampil extends AppCompatActivity {
    private ListView listviewkas,listvi;
    public static final String KS_NAMA = "nama";
    public static final String KS_ID = "id";
    public static final String KS_RANTING = "ranting";
    public static final String KS_JUMLAH = "jumlah";
    public static final String KS_DESK = "desk";
    public static final String KS_TITLE = "title";
    EditText caari;
    TextView txtapayah;
    DatabaseReference databasw1,dat;
    List<setkas> kaslist;
    List<setkas> kaslist1;
    Button klik;
    DatabaseReference databasekas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tampil);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databasw1 = database.getReference("message").child("ms");
        databasekas = FirebaseDatabase.getInstance().getReference("pemasukan");
        dat = FirebaseDatabase.getInstance().getReference("pengeluaran");
        kaslist = new ArrayList<>();
        kaslist1 = new ArrayList<>();
        listviewkas = (ListView) findViewById(R.id.lisviewkas);
        listvi = (ListView) findViewById(R.id.lisviewkasa);
        txtapayah = (TextView) findViewById(R.id.idapayah);
        caari = findViewById(R.id.iidcari);
        klik = findViewById(R.id.idklikcari);

        klik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cari();
            }
        });

        dat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                kaslist1.clear();

                for (int a = 0; a < dataSnapshot.getChildrenCount(); a++) {

                }

                for (DataSnapshot setnapasopt : dataSnapshot.getChildren()) {
                    setkas kas = setnapasopt.getValue(setkas.class);
                    kaslist1.add(kas);
                }
                maruf.com.myapplication.Ranting.setter.kaslist adapter = new kaslist(MainTampil.this, kaslist1);
                listvi.setAdapter(adapter);
//
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
                TextView tuunjuk = (TextView) findViewById(R.id.idapayah1);
                NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
                hasil = Integer.parseInt(txtapayah.getText().toString());
                Locale loca = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(loca);
                tuunjuk.setText(formatRupiah.format((double) hasil));
                txtapayah.setVisibility(View.INVISIBLE);


            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
listvi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        setkas kas = kaslist1.get(position);
        Intent a = new Intent(getApplicationContext(), MainUbah.class);
        a.putExtra(KS_ID, kas.getIdkas());
        a.putExtra(KS_NAMA, kas.getNama());
        a.putExtra(KS_RANTING, kas.getRanting());
        a.putExtra(KS_JUMLAH, kas.getJumlah());
        a.putExtra(KS_DESK, kas.getDesk());
        a.putExtra(KS_TITLE, kas.getTitle());
        startActivity(a);
    }
});
        listviewkas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                setkas kas = kaslist.get(position);
                Intent a = new Intent(getApplicationContext(), MainUbah.class);
                a.putExtra(KS_ID, kas.getIdkas());
                a.putExtra(KS_NAMA, kas.getNama());
                a.putExtra(KS_RANTING, kas.getRanting());
                a.putExtra(KS_JUMLAH, kas.getJumlah());
                a.putExtra(KS_DESK, kas.getDesk());
                a.putExtra(KS_TITLE, kas.getTitle());
                startActivity(a);


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

                for (int a = 0; a < dataSnapshot.getChildrenCount(); a++) {

                }

                for (DataSnapshot setnapasopt : dataSnapshot.getChildren()) {
                    setkas kas = setnapasopt.getValue(setkas.class);
                    kaslist.add(kas);
                }
                maruf.com.myapplication.Ranting.setter.kaslist adapter = new kaslist(MainTampil.this, kaslist);
                listviewkas.setAdapter(adapter);
//
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void dhfd(View view) {


    }

    private void cari() {
        String d = caari.getText().toString();
        DatabaseReference query = FirebaseDatabase.getInstance().getReference().child("pemasukan");
        query.orderByChild("desk").startAt(d).endAt(d + "\uf8ff").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                kaslist.clear();
                for (DataSnapshot setnapasopt : dataSnapshot.getChildren()) {
                    setkas kas = setnapasopt.getValue(setkas.class);
                    kaslist.add(kas);
                }
                maruf.com.myapplication.Ranting.setter.kaslist adapter = new kaslist(MainTampil.this, kaslist);
                listviewkas.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void hhome(View view) {

        Intent gjf = new Intent(MainTampil.this, MenuUtama.class);
        finish();
        startActivity(gjf);

    }
}

