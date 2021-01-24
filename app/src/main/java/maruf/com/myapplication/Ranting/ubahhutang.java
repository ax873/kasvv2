package maruf.com.myapplication.Ranting;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import maruf.com.myapplication.R;
import maruf.com.myapplication.Ranting.coba.setsaldo;
import maruf.com.myapplication.Ranting.tampilan.Tampilhutang;
import maruf.com.myapplication.push.MainActivity;

public class ubahhutang extends AppCompatActivity {
EditText htid,htranting,htnama,htjumlah,htdesk;
Button simpanht,hapusht;
TextView titlthe,saldosemua,bantuan,is,ups;
    String uid,nama;
    String adoi,rays;
    DatabaseReference databasekas;
    DatabaseReference datab,datb;
    DatabaseReference databasek;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Button btDatePicker;
    String htiduht;
    DatabaseReference muncul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubahhutang);
is=findViewById(R.id.id1user);
ups=findViewById(R.id.id2pass);
       uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        databasekas = FirebaseDatabase.getInstance().getReference("pemasukan");
        databasek = FirebaseDatabase.getInstance().getReference("hutang");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        datab = database.getReference("message").child("ms");
        Intent a = getIntent();
         htiduht = a.getStringExtra(Tampilhutang.KS_D);
        String htnamaht = a.getStringExtra(Tampilhutang.KS_AMA);
        String htrantinght = a.getStringExtra(Tampilhutang.KS_ANTING);
        String htjumlahht = a.getStringExtra(Tampilhutang.KS_UMLAH);
        String htdeskht = a.getStringExtra(Tampilhutang.KS_ESK);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        btDatePicker = (Button) findViewById(R.id.bt_datepicker);
        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });



        htdesk=(EditText) findViewById(R.id.htdesk);
htid=findViewById(R.id.htid);
htranting=findViewById(R.id.htranting);
htnama=findViewById(R.id.htnama);
htjumlah=findViewById(R.id.htjumlah);
titlthe=findViewById(R.id.htidtitl);
simpanht=findViewById(R.id.htsimpan);
hapusht=findViewById(R.id.hthapus);
bantuan=findViewById(R.id.htbantuan);
        datb=FirebaseDatabase.getInstance().getReference("Users").child(htiduht);

saldosemua=findViewById(R.id.htuwuht);
htdesk.setText(htdeskht);

htranting.setText(htrantinght);
htnama.setText(htnamaht);
htjumlah.setText(htjumlahht);

        datab.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setsaldo mahasiswa = dataSnapshot.getValue(setsaldo.class);
                saldosemua.setText(mahasiswa.getSaldo());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        muncul = FirebaseDatabase.getInstance().getReference("Users").child(uid);
        muncul.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                String mahasiswa = dataSnapshot.getValue(String.class);

                String user_id = dataSnapshot.child("id").getValue(String.class);

                htid.setText(user_id);
saldosemua.setVisibility(View.INVISIBLE);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        datb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                settersetkas mahasiswa = dataSnapshot.getValue(settersetkas.class);
               is.setText(mahasiswa.getUsername());
                ups.setText(mahasiswa.getPass());

                 rays = dataSnapshot.child("rayon").getValue(String.class);
                 adoi = dataSnapshot.child("status").getValue(String.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        simpanht.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convert();
                String tit=titlthe.getText().toString().trim();
                String idd=htid.getText().toString().trim();
                 nama=htnama.getText().toString().trim();
                String ranting=htranting.getText().toString().trim();
                String jumlah=htjumlah.getText().toString().trim();
                String desk=htdesk.getText().toString().trim();

                String use=is.getText().toString().trim();
                String pass=ups.getText().toString().trim();

                String idduuu = bantuan.getText().toString().trim();
                simpansaldo(new setsaldo(idduuu.toLowerCase()));

                panhutang(new settersetkas(htiduht,jumlah,pass,rays,adoi,use));
                subuser(new setkas(

                        htiduht,
                        nama.toLowerCase(),
                        ranting.toLowerCase(),
                        jumlah.toLowerCase(),
                        desk.toLowerCase(),
                        tit.toLowerCase())); hpusii(htiduht);
                Intent a=new Intent(ubahhutang.this, MainActivity.class);
                a.putExtra("KS"," Pengajuan Hutang "+nama);
                a.putExtra("DES","DI TERIMA ");
                startActivity(a);

            }

        });

     //
    }

    private void panhutang(settersetkas settersetkas) {
DatabaseReference datb=FirebaseDatabase.getInstance().getReference("Users").child(htiduht);
        datb.setValue(settersetkas).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Toast.makeText(getApplicationContext(),"saldo tersimpan",Toast.LENGTH_LONG).show();
//tampil();


            }
        });

    }

    private void simpansaldo(setsaldo setsaldo) {
        datab.setValue(setsaldo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                bantuan.setText("");

                Toast.makeText(getApplicationContext(),"saldo tersimpan",Toast.LENGTH_LONG).show();
//tampil();


            }
        });


    }


    private void hpusii(String sid) {

        databasek
                .child(sid)
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                    }
                });

    }
    private void showDateDialog(){

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                htranting.setText(""+dateFormatter.format(newDate.getTime()).toString());
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    private void subuser(setkas setkas) {
       // String chid = htid.getText().toString().trim();
        databasekas
                .child(htiduht)
                .setValue(setkas)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                    }
                });

    }

    private void convert() {
        int ang1,ang2,hasl;
        ang1 = Integer.parseInt(saldosemua.getText().toString());
        ang2 = Integer.parseInt(htjumlah.getText().toString());
        hasl = ang1 - ang2;
bantuan.setText(""+hasl);

    }


    public void httolakutangf(View view) {
        String idd=htid.getText().toString().trim();
        hpusii(idd);
        nama=htnama.getText().toString().trim();
        Intent a=new Intent(ubahhutang.this, MainActivity.class);

        a.putExtra("KS"," Pengajuan Hutang "+nama);
        a.putExtra("DES","DI TOLAK ");
        startActivity(a);
    }
}

