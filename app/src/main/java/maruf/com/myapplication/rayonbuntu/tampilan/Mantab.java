package maruf.com.myapplication.rayonbuntu.tampilan;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import maruf.com.myapplication.R;
import maruf.com.myapplication.rayonbuntu.hutang;


public class Mantab extends AppCompatActivity {
Button hut;
DatabaseReference databasw1,dbtoken,asd;
TextView txapayah,txttampil,tampilhutang;
    private String KEYTITLE="NAMA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mantab);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        hut=findViewById(R.id.idhhiutang);
        txttampil=findViewById(R.id.txttampil);
        dbtoken= FirebaseDatabase.getInstance().getReference().child("token");
        dbtoken.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                settertoken ambil=dataSnapshot.getValue(settertoken.class);
                txttampil.setText(ambil.getNamatoken());
                txttampil.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        asd = FirebaseDatabase.getInstance().getReference("pemasukan").child(uid);
//        asd.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                tampilhutang=findViewById(R.id.tamplhutang);
////                String mahasiswa = dataSnapshot.getValue(String.class);
//                String ad = dataSnapshot.child("jumlah").getValue(String.class);
//
//                tampilhutang.setText("hutang anda "+ad);
//                String tamp= tampilhutang.getText().toString();
//
//                if(tamp.equals("hutang anda null")){
//                    hut.setEnabled(true);
//                    tampilhutang.setVisibility(View.INVISIBLE);
//                }else if(tamp.equals("hutang anda 0")){
//
//                    hut.setVisibility(View.VISIBLE);
//                    hut.setText("HUTANG");
//                }
//
//                else{
//                    hut.setEnabled(false);
//                    hut.setText("Lunasi dulu");
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databasw1 = database.getReference("message").child("ms");

//        databasw1.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                txapayah=findViewById(R.id.txpayah);
//                setsaldo value = dataSnapshot.getValue(setsaldo.class);
//                txapayah.setText(value.getSaldo());
////
//                int hasil;
//                TextView tnjuk = (TextView) findViewById(R.id.idupa);
//                NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
//                hasil= Integer.parseInt(txapayah.getText().toString());
//                Locale loca = new Locale("in", "ID");
//                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(loca);
//                tnjuk.setText(formatRupiah.format((double)hasil));
//                txapayah.setVisibility(View.INVISIBLE);
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

        hut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nua="HUTANG";
                Intent ag =new Intent(Mantab.this, hutang.class);

                ag.putExtra(KEYTITLE,nua);
                startActivity(ag);
            }
        });
    }

    public void hhhhh(View view) {

    }



    public void admm(View view) {
String a=txttampil.getText().toString();
        String url = "https://api.whatsapp.com/send?phone="+a;

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }


}
