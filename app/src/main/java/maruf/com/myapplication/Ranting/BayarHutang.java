package maruf.com.myapplication.Ranting;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
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
import java.util.Locale;

import maruf.com.myapplication.push.MainActivity;
import maruf.com.myapplication.R;
import maruf.com.myapplication.Ranting.coba.setsaldo;
import maruf.com.myapplication.Ranting.tampilan.settertoken;

public class BayarHutang extends AppCompatActivity {
TextView saldohutang,txthasil,saldoasli,arepdisimpan,iyd,nama,ranting,deskkripsi,vtit,tampiltok,iser,ipass;
DatabaseReference dbasd,dabasw1,databasekas,dbtoken,tb;
EditText jumlahbayar,tokenomi,etnumber;
String rays ,adoi,uid;
Button Simpon,lunas; String idd;
int saldokas,dohutng,hasill,arepdi,hasil2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar_hutang);
saldohutang=findViewById(R.id.saldohutang);
        databasekas = FirebaseDatabase.getInstance().getReference("pemasukan");
        jumlahbayar=findViewById(R.id.tjumlahbayar);
        txthasil=findViewById(R.id.idhsil);
        saldoasli=findViewById(R.id.xtxsaldo);
        etnumber=findViewById(R.id.etnumber);
        arepdisimpan=findViewById(R.id.arepdisimpan);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dabasw1 = database.getReference("message").child("ms");
         uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        etnumber.addTextChangedListener(new TextWatcher() {
            private  String seteditext=etnumber.getText().toString().trim();
            //private  String settexview;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().equals(seteditext)){
                    etnumber.removeTextChangedListener(this);
                    String replace=s.toString().replaceAll("[Rp. ]","");
                    if(!replace.isEmpty()){
                        seteditext=formatrupiah(Double.parseDouble(replace));
                        // settexview=seteditext;
                    }else{
                        seteditext="";
                        // settexview="hasil input";

                    }
                    etnumber.setText(seteditext);
                    //  tvNumber.setText(settexview);
                    etnumber.setSelection(seteditext.length());
                    etnumber.addTextChangedListener(this);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        jumlahbayar.addTextChangedListener(new TextWatcher() {
            private  String ditext=jumlahbayar.getText().toString().trim();
            private  String sexview;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                etnumber.setText(jumlahbayar.getText().toString());

            }
        });

        lunas=findViewById(R.id.hapsu);
iser=findViewById(R.id.id1userruser);
ipass=findViewById(R.id.i2paspas);

iyd=findViewById(R.id.vid);
nama=findViewById(R.id.vnama);
         vtit=findViewById(R.id.vtit);
ranting=findViewById(R.id.vranting);
deskkripsi=findViewById(R.id.vdesk);
tampiltok=findViewById(R.id.dtampiltokenn);
tokenomi=findViewById(R.id.didtoken);

        Intent agg=getIntent();
        final String jum = agg.getStringExtra(maruf.com.myapplication.Ranting.tampilan.MainUbah.KJUMLAH);
        String idhut = agg.getStringExtra(maruf.com.myapplication.Ranting.tampilan.MainUbah.KID);
        String kdes = agg.getStringExtra(maruf.com.myapplication.Ranting.tampilan.MainUbah.KDESK);
        String kran = agg.getStringExtra(maruf.com.myapplication.Ranting.tampilan.MainUbah.KRANTING);
        String knam = agg.getStringExtra(maruf.com.myapplication.Ranting.tampilan.MainUbah.KNAMA);
        String ktit = agg.getStringExtra(maruf.com.myapplication.Ranting.tampilan.MainUbah.KTITLE);
        saldohutang.setText(jum);
        nama.setText(knam);
        iyd.setText(idhut);
        ranting.setText(kran);
        deskkripsi.setText(kdes);
        vtit.setText(ktit);
        tb=FirebaseDatabase.getInstance().getReference("Users").child(idhut);

        tb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                settersetkas mahasiswa = dataSnapshot.getValue(settersetkas.class);
                iser.setText(mahasiswa.getUsername());
                ipass.setText(mahasiswa.getPass());
                rays = dataSnapshot.child("rayon").getValue(String.class);
                adoi = dataSnapshot.child("status").getValue(String.class);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



lunas.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


       jumlahbayar.setText(jum);
mantab();
       lunas.setVisibility(View.INVISIBLE);
//

        String uss=iser.getText().toString();
        String pass=ipass.getText().toString();

        panhutang(new settersetkas(idhut,"0",pass,rays,adoi,uss));
        hapusii(idd);
    }
});
        dbtoken= FirebaseDatabase.getInstance().getReference().child("token");
        dbtoken.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                settertoken ambil=dataSnapshot.getValue(settertoken.class);
           //     tampiltok.setText(ambil.getNamatoken());
             //   tokenomi.setVisibility(View.INVISIBLE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




Simpon=findViewById(R.id.idbayar);
Simpon.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

      String nj=tokenomi.getText().toString();
      String nu=tampiltok.getText().toString();

        String uss=iser.getText().toString();
        String pass=ipass.getText().toString();
//        if(nj.equals(nu)) {

            saldokas = Integer.parseInt(saldohutang.getText().toString());
            dohutng = Integer.parseInt(jumlahbayar.getText().toString());
            hasill = saldokas - dohutng;
            txthasil.setText("" + hasill);
            penguranganhutng();
            jumlahbayar.setText("");

            String tit = vtit.getText().toString().trim();
            String idd = iyd.getText().toString().trim();
            String naa = nama.getText().toString().trim();
            String rantng = ranting.getText().toString().trim();
            String jumlh = txthasil.getText().toString().trim();
            String dsk = deskkripsi.getText().toString().trim();


        panhutang(new settersetkas(idd,jumlh,pass,rays,adoi,uss));
            sutusr(new setkas(
                    idd,
                    naa.toLowerCase(),
                    rantng.toLowerCase(),
                    jumlh.toLowerCase(),
                    dsk.toLowerCase(),
                    tit.toLowerCase()), idd);

        Intent a=new Intent(BayarHutang.this, MainActivity.class);

        a.putExtra("KS",tit+" Kas Ranting");
        a.putExtra("DES","Jumlah "+jumlh+" untuk "+dsk);
        startActivity(a);
//        }

//       else{
//           Toast.makeText(getApplicationContext(),"hubungi admin untuk memperoleh token",Toast.LENGTH_LONG).show();
//////tampil();
//       }

    }
});
        dabasw1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setsaldo mahasiswa = dataSnapshot.getValue(setsaldo.class);
                saldoasli.setText(mahasiswa.getSaldo());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void panhutang(settersetkas settersetkas) {
        tb.setValue(settersetkas).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {


                Toast.makeText(getApplicationContext(),"saldo tersimpan",Toast.LENGTH_LONG).show();
//tampil();


            }
        });

    }


    private  void mantab(){

        String nj=tokenomi.getText().toString();
        String nu=tampiltok.getText().toString();

//        if(nj.equals(nu)) {
        Toast.makeText(getApplicationContext(),"hubungi admin untuk memperoleh token",Toast.LENGTH_LONG).show();
        saldokas = Integer.parseInt(saldohutang.getText().toString());
        dohutng = Integer.parseInt(jumlahbayar.getText().toString());
        hasill = saldokas - dohutng;
        txthasil.setText("" + hasill);
        penguranganhutng();
        jumlahbayar.setText("");

        String tit = vtit.getText().toString().trim();
         idd = iyd.getText().toString().trim();
        String naa = nama.getText().toString().trim();
        String rantng = ranting.getText().toString().trim();
        String jumlh = txthasil.getText().toString().trim();
        String dsk = deskkripsi.getText().toString().trim();


        sutusr(new setkas(
                idd,
                naa.toLowerCase(),
                rantng.toLowerCase(),
                jumlh.toLowerCase(),
                dsk.toLowerCase(),
                tit.toLowerCase()), idd);
        Intent a=new Intent(BayarHutang.this, MainActivity.class);

        a.putExtra("KS",tit+" Kas Ranting");
        a.putExtra("DES","Jumlah "+jumlh+" untuk "+dsk);
        startActivity(a);
//        }
    }
    private void simpansaldo(setsaldo setsaldo) {
        dabasw1.setValue(setsaldo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {


                Toast.makeText(getApplicationContext(),"saldo tersimpan",Toast.LENGTH_LONG).show();
//tampil();


            }
        });}

    private void penguranganhutng(){


        arepdi=Integer.parseInt(saldoasli.getText().toString());
        hasil2=arepdi+dohutng;

arepdisimpan.setText(""+hasil2);
//


  String idduuu = arepdisimpan.getText().toString().trim();
      simpansaldo(new setsaldo(idduuu.toLowerCase()));

    }


    private void hapusii(String idd) {

        databasekas
                .child(idd)
                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                    }
                });
    }
    private void sutusr(setkas setkas, String idd) {
        databasekas
                .child(idd)
                .setValue(setkas)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {



                    }
                });

}
    private String formatrupiah(Double number){
        Locale localeID=new Locale("IDN","ID");
        NumberFormat numberFormat=NumberFormat.getCurrencyInstance(localeID);
        String formatrupiah=numberFormat.format(number);
        String[] split = formatrupiah.split(",");
        int length=split[0].length();
        return split[0].substring(0,2)+". "+split[0].substring(2,length);
    }


}
