package maruf.com.myapplication.rayonbuntu.tampilan;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import maruf.com.myapplication.HomeFragmen;
import maruf.com.myapplication.R;
import maruf.com.myapplication.Ranting.BayarHutang;
import maruf.com.myapplication.Ranting.coba.setsaldo;
import maruf.com.myapplication.Ranting.setterr.setkas;
import maruf.com.myapplication.Ranting.tampilan.MainTampil;


public class MainUbah extends AppCompatActivity {
    DatabaseReference databasekas,databasek;
    EditText edid1;
    EditText  ednama1;
    EditText   edranting1;
    EditText   edjumlah1;
    EditText   eddesk1;
    public  static  final String KS_S="NAMA";
    EditText bayarhutang;
    private String Sid;
    DatabaseReference databasw1;
    TextView iiidd;
    TextView title,rayon;
    Button edupdate;
    TextView ppid;
    String buu;
    int ang1,ang2,hasl,ang3,hasbantu,has0l, h1asbantu;
    TextView uwu;
    Button EDsIMPAN1;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private Button btDatePicker;
    private String nua;
    private String KEYTITLE="NAMA";
    public  static  final String KNAMA="nama";
    public  static  final String KID="id";
    public  static  final String KRANTING="ranting";
    public  static  final String KJUMLAH="jumlah";
    public  static  final String KDESK="desk";
    public  static  final String KTITLE="title";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ubah);

        rayon= (TextView)findViewById(R.id.ketrayon);
        edid1 = (EditText) findViewById(R.id.txtidkas1);
        ednama1 = (EditText) findViewById(R.id.txtnama1);
        Intent a = getIntent();
        buu = a.getStringExtra(HomeFragmen.KS_S);
        rayon.setText(buu);
        databasekas = FirebaseDatabase.getInstance().getReference(buu).child(buu);

        databasek = FirebaseDatabase.getInstance().getReference("hutang");
        edranting1 = (EditText) findViewById(R.id.txtranting1);
        edjumlah1 = (EditText) findViewById(R.id.txtjumlah1);
        eddesk1 = (EditText) findViewById(R.id.txtdesk1);
        edupdate = (Button) findViewById(R.id.btsimpan1);
        final Button bthapus = (Button) findViewById(R.id.bthapus);
        Sid=getIntent().getStringExtra("id");
        title=findViewById(R.id.tcttitle);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databasw1 = database.getReference("Rayon").child("saldo").child(buu);

        iiidd= (TextView)findViewById(R.id.Idid1ubah);
        ppid= (TextView)findViewById(R.id.Iid1ubah);
        uwu= (TextView)findViewById(R.id.uwu);
        final String idu = a.getStringExtra(maruf.com.myapplication.rayonbuntu.tampilan.MainTampil.KS_ID);
        String nama = a.getStringExtra(maruf.com.myapplication.rayonbuntu.tampilan.MainTampil.KS_NAMA);




        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        btDatePicker = (Button) findViewById(R.id.bt_datepicker);
        btDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });


        String rANTING = a.getStringExtra(maruf.com.myapplication.Ranting.tampilan.MainTampil.KS_RANTING);
        final String JUMLAH = a.getStringExtra(maruf.com.myapplication.Ranting.tampilan.MainTampil.KS_JUMLAH);
        String DESK = a.getStringExtra(maruf.com.myapplication.Ranting.tampilan.MainTampil.KS_DESK);
        final String titll=a.getStringExtra(maruf.com.myapplication.Ranting.tampilan.MainTampil.KS_TITLE);

        edid1.setText(idu);
        ednama1.setText(nama);
        edranting1.setText(rANTING);
        edjumlah1.setText(JUMLAH);
        ppid.setText(JUMLAH);
        title.setText(titll);
        eddesk1.setText(DESK);

        databasw1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               setsaldo mahasiswa = dataSnapshot.getValue(maruf.com.myapplication.Ranting.coba.setsaldo.class);
                iiidd.setText(mahasiswa.getSaldo());
                uwu.setText(mahasiswa.getSaldo());
                if(titll.equals("hutang")){
                    bthapus.setEnabled(false);
                    bthapus.setVisibility(View.INVISIBLE);
                    edupdate.setText("Bayar Hutang");
                }
                //  iiidd.setVisibility(View.INVISIBLE);
                //iiidd.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        bthapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(titll.equals("pemasukan")){

                    ang1 = Integer.parseInt(iiidd.getText().toString());
                    ang2 = Integer.parseInt(edjumlah1.getText().toString());
                    hasl = ang1 - ang2;
                    ppid.setText(""+hasl);

                    hapusii(Sid);
                    String idduuu = ppid.getText().toString().trim();
                    simpansaldo(new maruf.com.myapplication.Ranting.coba.setsaldo(idduuu.toLowerCase()));
                    Intent a=new Intent(MainUbah.this, maruf.com.myapplication.Ranting.tampilan.MainTampil.class);
                    startActivity(a);
                } else if(titll.equals("pengeluaran")){
                    ang1 = Integer.parseInt(iiidd.getText().toString());
                    ang2 = Integer.parseInt(edjumlah1.getText().toString());
                    hasl = ang1 + ang2;
                    ppid.setText(""+hasl);

                    hapusii(Sid);
                    String idduuu = ppid.getText().toString().trim();
                    simpansaldo(new maruf.com.myapplication.Ranting.coba.setsaldo(idduuu.toLowerCase()));
                    Intent a=new Intent(MainUbah.this, maruf.com.myapplication.Ranting.tampilan.MainTampil.class);
                    startActivity(a);
                    Toast.makeText(getApplicationContext(),"Data Telah terhapus",Toast.LENGTH_SHORT).show();
                } else {
                    bthapus.setEnabled(false);

                }


            }
        });



        edupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//              if(JUMLAH.isEmpty()){
//
//                  Toast.makeText(getApplicationContext(),"nduwur",Toast.LENGTH_SHORT).show();
//                  finish();
//              }
                if(titll.equals("pemasukan")){

                    String tit=title.getText().toString().trim();
                    String idd=edid1.getText().toString().trim();
                    String nama=ednama1.getText().toString().trim();
                    String ranting=edranting1.getText().toString().trim();
                    String jumlah=edjumlah1.getText().toString().trim();
                    String desk=eddesk1.getText().toString().trim();


                    ang1 = Integer.parseInt(iiidd.getText().toString());
                    ang2 = Integer.parseInt(ppid.getText().toString());
                    ang3 = Integer.parseInt(edjumlah1.getText().toString());


                    if (ang2>ang3){

                        ang1 = Integer.parseInt(iiidd.getText().toString());
                        ang2 = Integer.parseInt(ppid.getText().toString());
                        hasl = ang1 - ang2;
                        uwu.setText(""+hasl);

                        hapusii(Sid);
                        has0l=hasl+ang3;

                        ppid.setText(""+has0l);

                        String idduuu = ppid.getText().toString().trim();

                        simpansaldo(new maruf.com.myapplication.Ranting.coba.setsaldo(idduuu.toLowerCase()));

                        Toast.makeText(getApplicationContext(),"nduwur",Toast.LENGTH_SHORT).show();
                    } else if(ang2<ang3){
                        hasbantu=ang3-ang2;
                        hasl = ang1+hasbantu ;
                        ppid.setText(""+hasl);
                        String idduuu = ppid.getText().toString().trim();
                        simpansaldo(new maruf.com.myapplication.Ranting.coba.setsaldo(idduuu.toLowerCase()));
                        Toast.makeText(getApplicationContext(),"ngisor banfgetttttttttttt",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"njirrrr",Toast.LENGTH_LONG).show();
                    }




                    sutuser(new setkas(

                            idd,
                            nama.toLowerCase(),
                            ranting.toLowerCase(),
                            jumlah.toLowerCase(),
                            desk.toLowerCase(),
                            tit.toLowerCase()),Sid);
                }

                else if(titll.equals("pengeluaran")){

                    String tit=title.getText().toString().trim();
                    String idd=edid1.getText().toString().trim();
                    String nama=ednama1.getText().toString().trim();
                    String ranting=edranting1.getText().toString().trim();
                    String jumlah=edjumlah1.getText().toString().trim();
                    String desk=eddesk1.getText().toString().trim();


                    ang1 = Integer.parseInt(iiidd.getText().toString());
                    ang2 = Integer.parseInt(ppid.getText().toString());
                    ang3 = Integer.parseInt(edjumlah1.getText().toString());



                    if (ang2>ang3){

                        ang1 = Integer.parseInt(iiidd.getText().toString());
                        ang3 = Integer.parseInt(edjumlah1.getText().toString());
                        ang2 = Integer.parseInt(ppid.getText().toString());
                        hasl = ang2 - ang3;
                        ppid.setText(""+hasl);
                        hapusii(Sid);
                        has0l=hasl+ang1;

                        ppid.setText(""+has0l);

                        String idduuu = ppid.getText().toString().trim();

                        simpansaldo(new maruf.com.myapplication.Ranting.coba.setsaldo(idduuu.toLowerCase()));

                        Toast.makeText(getApplicationContext(),"Selesai",Toast.LENGTH_SHORT).show();
                    } else if(ang2<ang3){
                        hasbantu=ang3-ang2;
                        hasl = ang1-hasbantu ;
                        ppid.setText(""+hasl);
                        String idduuu = ppid.getText().toString().trim();
                        simpansaldo(new maruf.com.myapplication.Ranting.coba.setsaldo(idduuu.toLowerCase()));
                        Toast.makeText(getApplicationContext(),"Selesai",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"gagal ",Toast.LENGTH_LONG).show();
                    }





                    sutuser(new setkas(

                            idd,
                            nama.toLowerCase(),
                            ranting.toLowerCase(),
                            jumlah.toLowerCase(),
                            desk.toLowerCase(),
                            tit.toLowerCase()),Sid);
                    Intent a=new Intent(MainUbah.this, MainTampil.class);
                    startActivity(a);


                }

                else {
                    bthapus.setEnabled(false);
                    String ajum =edjumlah1.getText().toString();
                    Intent a=new Intent(MainUbah.this, BayarHutang.class);
                    a.putExtra(KID,edid1.getText().toString());
                    a.putExtra(KNAMA,ednama1.getText().toString());
                    a.putExtra(KTITLE,title.getText().toString());
                    a.putExtra(KDESK,eddesk1.getText().toString());
                    a.putExtra(KJUMLAH,ajum);
                    a.putExtra(KRANTING,edranting1.getText().toString());
                    startActivity(a);
                }
            }

        });


    }

    private void convert() {

        ang1 = Integer.parseInt(iiidd.getText().toString());
        ang2 = Integer.parseInt(edjumlah1.getText().toString());
        hasl = ang1 + ang2;
        uwu.setText(""+hasl);

    }

    private void simpansaldo(setsaldo setsaldo) {
        databasw1.setValue(setsaldo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                ppid.setText("");
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


    private void showDateDialog(){

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                edranting1.setText(""+dateFormatter.format(newDate.getTime()).toString());
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }


    private void hapusii(String sid) {

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


}
