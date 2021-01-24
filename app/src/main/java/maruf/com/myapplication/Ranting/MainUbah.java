package maruf.com.myapplication.Ranting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import maruf.com.myapplication.push.MainActivity;
import maruf.com.myapplication.R;
import maruf.com.myapplication.Ranting.coba.setsaldo;
import maruf.com.myapplication.Ranting.tampilan.MainTampil;

public class MainUbah extends AppCompatActivity {
    DatabaseReference databasekas;
    EditText edid1;
    EditText  ednama1;
    EditText   edranting1;
    EditText   edjumlah1;
    EditText   eddesk1;
    EditText bayar;
    private String Sid;
    Button EDsIMPAN1;
    TextView iiidd;
    TextView ppid;
    DatabaseReference databasw1;
TextView title;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ubah);


        title=findViewById(R.id.tcttitle);
        edid1 = (EditText) findViewById(R.id.txtidkas1);
        ednama1 = (EditText) findViewById(R.id.txtnama1);
        databasekas = FirebaseDatabase.getInstance().getReference("pemasukan");
        edranting1 = (EditText) findViewById(R.id.txtranting1);
        edjumlah1 = (EditText) findViewById(R.id.txtjumlah1);
        eddesk1 = (EditText) findViewById(R.id.txtdesk1);
        FirebaseDatabase database = FirebaseDatabase.getInstance();


        databasw1 = database.getReference("message").child("ms");
        Button edccsimpan = (Button) findViewById(R.id.btsimpan1);
        Button bthapus = (Button) findViewById(R.id.bthapus);
         ppid= (TextView)findViewById(R.id.Iid1ubah);
         iiidd= (TextView)findViewById(R.id.Idid1ubah);
        Sid=getIntent().getStringExtra("id");
        Intent a = getIntent();
        String idu = a.getStringExtra(MainTampil.KS_ID);
        String nama = a.getStringExtra(MainTampil.KS_NAMA);
        String rANTING = a.getStringExtra(MainTampil.KS_RANTING);
        String JUMLAH = a.getStringExtra(MainTampil.KS_JUMLAH);
        String DESK = a.getStringExtra(MainTampil.KS_DESK);



        edid1.setText(idu);
        ednama1.setText(nama);
        edranting1.setText(rANTING);
        edjumlah1.setText(JUMLAH);
        eddesk1.setText(DESK);
        databasw1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setsaldo mahasiswa = dataSnapshot.getValue(setsaldo.class);
                iiidd.setText(mahasiswa.getSaldo());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        bthapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapusii(Sid);
                Intent a=new Intent(MainUbah.this, MainTampil.class);

                startActivity(a);
            }
        });





        edccsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tit=title.getText().toString().trim();
                String idd=edid1.getText().toString().trim();
                String nama=ednama1.getText().toString().trim();
                String ranting=edranting1.getText().toString().trim();
                String jumlah=edjumlah1.getText().toString().trim();
                String desk=eddesk1.getText().toString().trim();


                sutuser(new setkas(
                        idd.toLowerCase(),
                        nama.toLowerCase(),
                        ranting.toLowerCase(),
                        jumlah.toLowerCase(),
                        desk.toLowerCase(),
                        tit.toLowerCase()),Sid);
                Intent a=new Intent(MainUbah.this, MainActivity.class);

                a.putExtra("KS",tit+" Kas Ranting");
                a.putExtra("DES","Jumlah "+jumlah+" untuk "+desk);
                startActivity(a);
            }

        });


    }

    private void sutuser(setkas setkas, String id) {
        databasekas
                .child(id)
                .setValue(setkas)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        edid1.setText("");
                        ednama1.setText("");
                        edranting1.setText("");
                        edjumlah1.setText("");
                        eddesk1.setText("");


                    }
                });
    }



    private void hapusii(String sid) {
        convert();
        databasekas
                .child(sid)
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        edid1.setText("");
                        ednama1.setText("");
                        edranting1.setText("");
                        edjumlah1.setText("");
                        eddesk1.setText("");


                    }
                });
    }
    private void convert() {
        Double hasil,v1,v2;
        int ang1,ang2,hasl;
        ang1 = Integer.parseInt(ppid.getText().toString());
        ang2 = Integer.parseInt(edjumlah1.getText().toString());
        hasl = ang1 - ang2;
        ppid.setText(""+hasl);

//
//        v1 = Double.parseDouble(isisaldo.getText().toString());
//        v2 = Double.parseDouble(edjumlah.getText().toString());
//        hasil= v1+v2;
//        tampillsaldo.setText(Double.toString(hasil));
    }

}
