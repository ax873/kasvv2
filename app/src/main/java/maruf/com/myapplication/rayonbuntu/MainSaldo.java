package maruf.com.myapplication.rayonbuntu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

import maruf.com.myapplication.HomeFragmen;
import maruf.com.myapplication.R;
import maruf.com.myapplication.push.MainActivity;
import maruf.com.myapplication.rayonbuntu.coba.setsaldo;
import maruf.com.myapplication.rayonbuntu.tampilan.MainTampil;

public class MainSaldo extends AppCompatActivity {
    String idrayin;
    String as;
    EditText edisisaldo1;
    private String nua;
    public  static  final String KS_S="NAMA";
    TextView tunjuk,rayon;
    private TextView detailHarga;
    String Bu;
    DatabaseReference d,muncul,databasw1;
    Button edsimpansaldo1,edubahsaldo1,edhapussaldo1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_saldo);

        detailHarga = (TextView) findViewById(R.id.textw);
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        rayon=findViewById(R.id.ketrayon);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        d = database.getReference("Rayon").child("saldo");
        edsimpansaldo1 = (Button) findViewById(R.id.btsimpansaldo1);
        detailHarga = (TextView) findViewById(R.id.textw);
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
        Locale loca = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(loca);


        tunjuk = (TextView) findViewById(R.id.saldokamu);
        edisisaldo1 = (EditText) findViewById(R.id.idsaldo1);
        edsimpansaldo1 = (Button) findViewById(R.id.btsimpansaldo1);



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
        Bu=rayon.getText().toString();
        d.child(Bu).setValue(setsaldo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                edisisaldo1.setText("");

                Intent a=new Intent(MainSaldo.this, MainActivity.class);

                a.putExtra("KS","Saldo Awal "+Bu);
                a.putExtra("DES","Saldo diset awal sebesar Rp.  "+as);
                startActivity(a);



            }
        });

    }

    @Override
    protected void onStart() {

        Intent a = getIntent();
        String idu = a.getStringExtra(HomeFragmen.KS_S);
        rayon.setText(idu);

        String buu=rayon.getText().toString();
        d.child(buu).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                setsaldo mahasiswa = dataSnapshot.getValue(setsaldo.class);
                detailHarga.setText(mahasiswa.getSaldo());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }
}
