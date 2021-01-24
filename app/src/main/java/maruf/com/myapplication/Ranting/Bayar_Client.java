package maruf.com.myapplication.Ranting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
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
import maruf.com.myapplication.Ranting.coba.setsaldo;
import maruf.com.myapplication.Ranting.tampilan.Mantab;

public class Bayar_Client extends AppCompatActivity {
    private ListView listView;
  private   TextView txtapayah,tampilhutang;
   private List<setkas> kaslist;
   private DatabaseReference databasekas,dbas,databasw1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar__client);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        databasw1 = database.getReference("message").child("ms");
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databasekas = FirebaseDatabase.getInstance().getReference("pemasukan");
dbas= FirebaseDatabase.getInstance().getReference("Users").child(uid);
        kaslist = new ArrayList<>();
        listView = (ListView) findViewById(R.id.liwkas);
        txtapayah = (TextView) findViewById(R.id.txtapayah);
        onStart();

        dbas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                tampilhutang=findViewById(R.id.ididan);
//                String mahasiswa = dataSnapshot.getValue(String.class);
                String ad = dataSnapshot.child("username").getValue(String.class);

                tampilhutang.setText(""+ad);
                Animation animation= AnimationUtils.loadAnimation(Bayar_Client.this,R.anim.sliderig);
                tampilhutang.startAnimation(animation);
                Animation animationd= AnimationUtils.loadAnimation(Bayar_Client.this,R.anim.slidd);
                txtapayah.startAnimation(animationd);
                tampilhutang.setText(""+ad);
                Animation animtion= AnimationUtils.loadAnimation(Bayar_Client.this,R.anim.slidelleft);
                listView.startAnimation(animtion);
                Animation nimtion= AnimationUtils.loadAnimation(Bayar_Client.this,R.anim.sliderig);
                listView.startAnimation(nimtion);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databasw1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                setsaldo value = dataSnapshot.getValue(setsaldo.class);
                txtapayah.setText(value.getSaldo());

                int hasil;
                TextView tuunjuk = (TextView) findViewById(R.id.tamplhutang);
                NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
                hasil = Integer.parseInt(txtapayah.getText().toString());
                Locale loca = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(loca);
                tuunjuk.setText("Saldo Kas Sekarang = "+formatRupiah.format((double) hasil));
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
                kaslist adapter=new kaslist(Bayar_Client.this,kaslist);
                listView.setAdapter(adapter);
//                Collections.reverse(kaslist);
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void mantab(View view) {
        Intent g =new Intent(Bayar_Client.this, Mantab.class);
        startActivity(g);

    }
}

