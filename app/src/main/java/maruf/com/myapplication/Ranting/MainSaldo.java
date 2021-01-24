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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.Locale;

import maruf.com.myapplication.R;
import maruf.com.myapplication.Ranting.coba.setsaldo;
import maruf.com.myapplication.push.MainActivity;

public class MainSaldo extends AppCompatActivity {
    String as;
    EditText edisisaldo1,etnumber;
    TextView tunjuk;
    private TextView detailHarga;
    DatabaseReference databasw1;
    Button edsimpansaldo1,edubahsaldo1,edhapussaldo1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_saldo);
        detailHarga = (TextView) findViewById(R.id.textw);

        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);


        etnumber=findViewById(R.id.etnumber);
        Locale loca = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(loca);


        tunjuk = (TextView) findViewById(R.id.saldokamu);
        edisisaldo1 = (EditText) findViewById(R.id.idsaldo1);


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
        edisisaldo1.addTextChangedListener(new TextWatcher() {
            private  String ditext=edisisaldo1.getText().toString().trim();
            private  String sexview;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                etnumber.setText(edisisaldo1.getText().toString());

            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databasw1 = database.getReference("message").child("ms");
        edsimpansaldo1 = (Button) findViewById(R.id.btsimpansaldo1);
//        edubahsaldo1 = (Button) findViewById(R.id.btubahsaldo1);
//        edhapussaldo1 = (Button) findViewById(R.id.bthapussaldo1);

        databasw1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setsaldo mahasiswa = dataSnapshot.getValue(setsaldo.class);
                tunjuk.setText(mahasiswa.getSaldo());

                int hasil;
                detailHarga = (TextView) findViewById(R.id.textw);
                NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
                hasil = Integer.parseInt(tunjuk.getText().toString());
                Locale loca = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(loca);
                detailHarga.setText(formatRupiah.format((double) hasil));
                tunjuk.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        edsimpansaldo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           as=edisisaldo1.getText().toString();
              if (as.isEmpty()) {
                  Toast.makeText(getApplicationContext(),"Saldonya di isi",Toast.LENGTH_LONG).show();
//tampil();
              }
              else{
                  String idduuu = edisisaldo1.getText().toString().trim();
                  simpansaldo(new setsaldo(idduuu.toLowerCase()));
              }
            }
        });
    }



    private void simpansaldo(setsaldo setsaldo) {
        databasw1.setValue(setsaldo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                Intent a=new Intent(MainSaldo.this, MainActivity.class);

                a.putExtra("KS"," Set Saldo Awal Ranting");
                a.putExtra("DES","Saldo diset awal sebesar Rp.  "+as);
                startActivity(a);


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
