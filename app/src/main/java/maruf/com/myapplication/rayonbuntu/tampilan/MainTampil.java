package maruf.com.myapplication.rayonbuntu.tampilan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import maruf.com.myapplication.HomeFragmen;
import maruf.com.myapplication.R;
import maruf.com.myapplication.menunavigasi;
import maruf.com.myapplication.rayonbuntu.coba.setsaldo;
import maruf.com.myapplication.rayonbuntu.setter.kaslist;
import maruf.com.myapplication.rayonbuntu.setterr.setkas;

public class MainTampil extends AppCompatActivity {
    private ListView listviewkas;
    public  static  final String KS_NAMA="nama";
    public  static  final String KS_ID="id";
    public  static  final String KS_S="NAMA";
    public  static  final String KS_RANTING="ranting";
    public  static  final String KS_JUMLAH="jumlah";
    public  static  final String KS_DESK="desk";
    public  static  final String KS_TITLE="title";
    EditText caari;
    TextView txtapayah;
    DatabaseReference databasw1;
    List<setkas> kaslist;
    Button klik;
    String buu;
    TextView rayon;
    DatabaseReference databasekas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tampil);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        rayon=findViewById(R.id.yeray);
        Intent a = getIntent();
        String idu = a.getStringExtra(HomeFragmen.KS_S);
        rayon.setText(idu);

        buu=rayon.getText().toString();
        databasw1 = database.getReference("Rayon").child("saldo");
        databasekas = FirebaseDatabase.getInstance().getReference(buu);
        kaslist = new ArrayList<>();
      rayon=findViewById(R.id.yeray);
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




//        databasw1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//
//
//                int hasil;
//             TextView   tuunjuk = (TextView) findViewById(R.id.idapayah1);
//                NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
//                hasil= Integer.parseInt(txtapayah.getText().toString());
//                Locale loca = new Locale("in", "ID");
//                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(loca);
//                tuunjuk.setText(formatRupiah.format((double)hasil));
//                txtapayah.setVisibility(View.INVISIBLE);
//
//
//            }
//
//
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//
//            }
//        });

        listviewkas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                setkas kas=kaslist.get(position);
                Intent a =new Intent(getApplicationContext(), MainUbah.class);
                a.putExtra(KS_ID,kas.getIdkas());
                a.putExtra(KS_NAMA,kas.getNama());
                a.putExtra(KS_RANTING,kas.getRanting());
                a.putExtra(KS_JUMLAH,kas.getJumlah());
                a.putExtra(KS_DESK,kas.getDesk());
                a.putExtra(KS_TITLE,kas.getTitle());
                String nua=rayon.getText().toString();
                a.putExtra(KS_S,nua);
                startActivity(a);


            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();



        String buu=rayon.getText().toString();
        databasw1.child(buu).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setsaldo mahasiswa = dataSnapshot.getValue(setsaldo.class);
                txtapayah.setText(mahasiswa.getSaldo());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        databasekas.child(buu).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                kaslist.clear();

                for(DataSnapshot setnapasopt:dataSnapshot.getChildren()){
                    setkas kas=setnapasopt.getValue(setkas.class);
                    kaslist.add(kas);
                }
              kaslist adapter=new kaslist(MainTampil.this,kaslist);
                listviewkas.setAdapter(adapter);
//                Collections.reverse(kaslist);
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    private void cari() {
        String d=caari.getText().toString();

        String buu=rayon.getText().toString();
        DatabaseReference query = FirebaseDatabase.getInstance().getReference(buu).child(buu);
        query.orderByChild("desk").startAt(d).endAt(d+"\uf8ff").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                kaslist.clear();
                for(DataSnapshot setnapasopt:dataSnapshot.getChildren()){
                    setkas kas=setnapasopt.getValue(setkas.class);
                    kaslist.add(kas);
                }
                kaslist adapter=new kaslist(MainTampil.this,kaslist);
                listviewkas.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }



    }

