package maruf.com.myapplication.rayonbuntu;


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
import maruf.com.myapplication.push.MainActivity;
import maruf.com.myapplication.rayonbuntu.coba.setsaldo;
import maruf.com.myapplication.rayonbuntu.tampilan.Mantab;


public class hutang extends AppCompatActivity {
    DatabaseReference htdatabasekas,databasw1;
    EditText htedid;
    String nama;
    EditText  htednama;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Button btDatePicker;
    EditText   htedranting;
    EditText   htedjumlah;
    String desk;
    EditText   hteddesk;
TextView titlehutang,txapayah;
Button btspn;
    int hari,bulan,tahun;
DatabaseReference muncul;
String amonth,findal;
    private String nua;
    private String KEYTITLE = "NAMA";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actifiti_hutang);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        htdatabasekas = database.getReference("hutang");
        htedid = (EditText) findViewById(R.id.httxtidkas);
        htednama = (EditText) findViewById(R.id.httxtnama);
        htedranting = (EditText) findViewById(R.id.httxtranting);
        htedjumlah = (EditText) findViewById(R.id.httxtjumlah);
        hteddesk = (EditText) findViewById(R.id.httxtdesk);
        titlehutang = findViewById(R.id.httxttitle);
        btspn = findViewById(R.id.htbtsimpan);

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
        } else if(bln.equals("0")){
            amonth= "1";
        }
        else if(bln.equals("11")){
            amonth= "12";
        }
        else{
            Toast.makeText(getApplicationContext(),"Tanggal salah",Toast.LENGTH_LONG).show();
        }

        if(hari<10){
            findal= "0"+hari+"-"+amonth+"-"+tahun;
        } else{
            findal= ""+hari+"-"+amonth+"-"+tahun;
        }

        htedranting.setText(findal);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        btDatePicker = (Button) findViewById(R.id.bt_datepicker);
        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });


        FirebaseDatabase databas = FirebaseDatabase.getInstance();
        databasw1 = databas.getReference("message").child("ms");

        databasw1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                txapayah=findViewById(R.id.txpayah);
                setsaldo value = dataSnapshot.getValue(setsaldo.class);
                txapayah.setText(value.getSaldo());
//
                int hasil;
                TextView tnjuk = (TextView) findViewById(R.id.idapah1);
                NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
                hasil= Integer.parseInt(txapayah.getText().toString());
                Locale loca = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(loca);
                tnjuk.setText(formatRupiah.format((double)hasil));
                txapayah.setVisibility(View.INVISIBLE);


            }



            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        muncul = FirebaseDatabase.getInstance().getReference("Users").child(uid);
        muncul.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                String mahasiswa = dataSnapshot.getValue(String.class);
                String ids = dataSnapshot.child("id").getValue(String.class);
                String user_id = dataSnapshot.child("username").getValue(String.class);
               htedid.setText(ids);
                htednama.setText(user_id);




            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        btspn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tit=titlehutang.getText().toString().trim();
                String idd=htedid.getText().toString().trim();
                 nama=htednama.getText().toString().trim();
                String ranting=htedranting.getText().toString().trim();
                String jumlah=htedjumlah.getText().toString().trim();
                 desk=hteddesk.getText().toString().trim();

                if(jumlah.isEmpty()){

                    Toast.makeText(getApplicationContext(),"Isi Jumlah yang ingin anda pinjam ",Toast.LENGTH_LONG).show();
                } else{
                subtuser(new setkas(

                        idd,
                        nama.toLowerCase(),
                        ranting.toLowerCase(),
                        jumlah.toLowerCase(),
                        desk.toLowerCase(),
                        tit.toLowerCase()));
                Intent gjf =new Intent(hutang.this, Mantab.class);
                startActivity(gjf);}


            }
        });
htdatabasekas.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Bundle extra = getIntent().getExtras();
        nua = extra.getString(KEYTITLE);
        titlehutang.setText(nua);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

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

                htedranting.setText(""+dateFormatter.format(newDate.getTime()).toString());
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }
    private void subtuser(setkas setkas) {
        String htchid=htedid.getText().toString().trim();
        htdatabasekas
                .child(htchid)
                .setValue(setkas)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                        Intent a=new Intent(hutang.this, MainActivity.class);

                        a.putExtra("KS","Pengajuan Hutang dari "+nama);
                        a.putExtra("DES","Saldo diset awal sebesar Rp.  "+desk);
                        startActivity(a);

                        htedid.setText("");
                        htednama.setText("");
                        htedranting.setText("");
                        htedjumlah.setText("");
                        hteddesk.setText("");
                    }
                });

    }



}