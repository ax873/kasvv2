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

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


import maruf.com.myapplication.R;
import maruf.com.myapplication.Ranting.coba.setsaldo;
import maruf.com.myapplication.Ranting.tampilan.MainTampil;
import maruf.com.myapplication.noprimary;
import maruf.com.myapplication.push.MainActivity;

public class Main2Activity extends AppCompatActivity {
    DatabaseReference databasekas;
    //pengeluaran
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Button btDatePicker;
    EditText edid;
    EditText ednama;
    private String KS="NAMA";
    EditText edranting;
    EditText edjumlah;
    EditText eddesk;
    DatabaseReference muncul;
    TextView isisaldo;
    TextView tampillsaldo;
    private String Sid;
    TextView title;
    int hari,bulan,tahun;
    String  amonth;
    Double hasil, v1, v2;
    int ang1, ang2, hasl;
    Button EDsIMPAN1;
    TextView idcurenc;
    DatabaseReference databasw1,noo;
    private String nua;
    private String KEYTITLE = "NAMA";
    long maxid=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        isisaldo = (TextView) findViewById(R.id.Idid);
        tampillsaldo = (TextView) findViewById(R.id.idtampilsaldo);
        edid = (EditText) findViewById(R.id.txtidkas);
        ednama = (EditText) findViewById(R.id.txtnama);
        databasekas = FirebaseDatabase.getInstance().getReference("pengeluaran");

        noo = FirebaseDatabase.getInstance().getReference("No_primary");
        noo.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                    maxid= (snapshot.getChildrenCount());
                edid.setText(""+maxid);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        edranting = (EditText) findViewById(R.id.txtranting);
        edjumlah = (EditText) findViewById(R.id.txtjumlah);
        eddesk = (EditText) findViewById(R.id.txtdesk);
        Button edccsimpan = (Button) findViewById(R.id.btsimpan);
        Button bthapus = (Button) findViewById(R.id.bthapus);
        Sid = getIntent().getStringExtra("id");
        idcurenc = (TextView) findViewById(R.id.idcurency);
        title = findViewById(R.id.txttitle);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databasw1 = database.getReference("message").child("ms");

        Calendar calendar=Calendar.getInstance();
        hari=calendar.get(Calendar.DAY_OF_MONTH);
        bulan=calendar.get(Calendar.MONTH);
        tahun=calendar.get(Calendar.YEAR);
        String  bln=""+bulan;
        if(bln.equals("3")){
            amonth=  "04";
        } else if(bln.equals("4")){
            amonth= "05";
        }else if(bln.equals("5")){
            amonth= "06";
        }else if(bln.equals("6")){
            amonth= "07";
        }else if(bln.equals("7")){
            amonth= "08";
        }else if(bln.equals("1")){
            amonth= "02";
        }else if(bln.equals("2")){
            amonth= "03";
        }else if(bln.equals("8")){
            amonth= "09";
        }else if(bln.equals("9")){
            amonth= "10";
        }else if(bln.equals("10")){
            amonth= "11";
        }
        else if(bln.equals("11")){
            amonth= "12";
        }
        else if(bln.equals("12")){
            amonth= "01";
        }
        else{
            Toast.makeText(getApplicationContext(),"Tanggal salah",Toast.LENGTH_LONG).show();
        }


        String findal= "0"+hari+"-"+amonth+"-"+tahun;

        edranting.setText(findal);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        btDatePicker = (Button) findViewById(R.id.bt_datepicker);
        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });


        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();



        muncul = FirebaseDatabase.getInstance().getReference("Users").child(uid);
        muncul.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                String mahasiswa = dataSnapshot.getValue(String.class);

                String user_id = dataSnapshot.child("username").getValue(String.class);

                ednama.setText(user_id);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        databasw1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setsaldo mahasiswa = dataSnapshot.getValue(setsaldo.class);
                isisaldo.setText(mahasiswa.getSaldo());
                isisaldo.setVisibility(View.INVISIBLE);
                tampillsaldo.setVisibility(View.INVISIBLE);
                Bundle extra = getIntent().getExtras();
                nua = extra.getString(KEYTITLE);
                title.setText(nua);
                int hs;
                NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
                hs = Integer.parseInt(isisaldo.getText().toString());
                Locale loca = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(loca);
                idcurenc.setText(formatRupiah.format((double) hs));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        edccsimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jumlah=edjumlah.getText().toString().trim();
                if(jumlah.isEmpty()){

                    Toast.makeText(getApplicationContext(),"Isi Jumlah Rupiah ",Toast.LENGTH_SHORT).show();

                } else {



                    convert();
                    String tit = title.getText().toString().trim();
                    String idd = edid.getText().toString().trim();
                    String nama = ednama.getText().toString().trim();
                    String ranting = edranting.getText().toString().trim();
                    simpado(new noprimary(idd.toLowerCase()));
                    String desk = eddesk.getText().toString().trim();
                    String idduuu = tampillsaldo.getText().toString().trim();
                    simpansaldo(new setsaldo(idduuu.toLowerCase()));
                    submitutuser(new setkas(

                            idd.toLowerCase(),
                            nama.toLowerCase(),
                            ranting.toLowerCase(),
                            jumlah.toLowerCase(),
                            desk.toLowerCase(),
                            tit.toLowerCase()));
                    Intent a=new Intent(Main2Activity.this, MainActivity.class);

                    a.putExtra("KS",tit+" Kas Ranting");
                    a.putExtra("DES","Jumlah "+jumlah+" untuk "+desk);

                    startActivity(a);}
            }

        });


    }

    private void simpado(noprimary noprimary) {
        String chid = edid.getText().toString().trim();
        noo
                .child(chid)
                .setValue(noprimary)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        edid.setText("");


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

                edranting.setText(""+dateFormatter.format(newDate.getTime()).toString());
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void convert() {

        ang1 = Integer.parseInt(isisaldo.getText().toString());
        ang2 = Integer.parseInt(edjumlah.getText().toString());
        hasl = ang1 - ang2;
        tampillsaldo.setText("" + hasl);
    }

    private void submitutuser(setkas setkas) {
        String chid = edid.getText().toString().trim();
        databasekas
                .child(chid)
                .setValue(setkas)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        edid.setText("");
                        ednama.setText("");
                        edranting.setText("");
                        edjumlah.setText("");
                        eddesk.setText("");


                    }
                });

    }


    private void simpansaldo(setsaldo setsaldo) {
        databasw1.setValue(setsaldo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {


                Toast.makeText(getApplicationContext(), "saldo tersimpan", Toast.LENGTH_LONG).show();
//tampil();


            }
        });
    }

    public void aaaa(View view) {

        Intent a = new Intent(Main2Activity.this, MainTampil.class);
        startActivity(a);
    }

}
