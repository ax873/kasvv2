package maruf.com.myapplication.rayonbuntu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.Locale;

import maruf.com.myapplication.R;
import maruf.com.myapplication.rayonbuntu.coba.setsaldo;

public class kaslain extends AppCompatActivity {
TextView buntu,smadipo,karangsari,gebang,smkplus,gunungnangka,smpnegriketingan,adisana,randegan;
  DatabaseReference database,datase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaslain);
database=FirebaseDatabase.getInstance().getReference("Rayon").child("saldo");
buntu=findViewById(R.id.rayonBuntu);
smadipo=findViewById(R.id.rayondipo);
karangsari=findViewById(R.id.karangsari);
gebang=findViewById(R.id.rayongebangsari);
smkplus=findViewById(R.id.rayontunasbangsa);
gunungnangka=findViewById(R.id.gunungnagnka);
smpnegriketingan=findViewById(R.id.rayonsmkketingan);
randegan=findViewById(R.id.randegan);
adisana=findViewById(R.id.smpadisana);

        datase=FirebaseDatabase.getInstance().getReference("message").child("ms");


database.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {

        setsaldo krsari = snapshot.child("Karangsari").getValue(setsaldo.class);
        karangsari.setText("Saldo Rayon Karangsari adalah "+krsari.getSaldo());





    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                setsaldo bnt = snapshot.child("Buntu").getValue(setsaldo.class);
                buntu.setText("Saldo Rayon Buntu adalah "+bnt.getSaldo());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                setsaldo rnd = snapshot.child("Randegan").getValue(setsaldo.class);
                randegan.setText("Saldo Rayon Randegan adalah "+rnd.getSaldo());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                setsaldo doip = snapshot.child("Gebang Sari").getValue(setsaldo.class);
                gebang.setText("Saldo Rayon Gebang Sari adalah "+doip.getSaldo());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                setsaldo dip = snapshot.child("SMA Diponegoro").getValue(setsaldo.class);
                smadipo.setText("Saldo Rayon SMA Diponegoro adalah "+dip.getSaldo());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                setsaldo tn = snapshot.child("SMK Plus Tunas Bangsa").getValue(setsaldo.class);
                smkplus.setText("Saldo Rayon SMK Plus Tunas Bangsa adalah "+tn.getSaldo());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                setsaldo gnn = snapshot.child("Gunung Nangka").getValue(setsaldo.class);
                gunungnangka.setText("Saldo Rayon Gunung nangka adalah "+gnn.getSaldo());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {



                setsaldo gh = snapshot.child("SMP Negeri 2 Ketingan").getValue(setsaldo.class);
                smpnegriketingan.setText("Saldo Rayon SMP Negeri 2 Ketingan adalah "+gh.getSaldo());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                setsaldo adi = snapshot.child("SMP Negeri 2 Adisana").getValue(setsaldo.class);
                adisana.setText("Saldo Rayon SMP Negeri 2 Adisana adalah "+adi.getSaldo());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}