package maruf.com.myapplication.Ranting;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.Locale;


import maruf.com.myapplication.HomeFragmen;
import maruf.com.myapplication.R;
import maruf.com.myapplication.Ranting.coba.setsaldo;
import maruf.com.myapplication.Ranting.tampilan.Mantab;
import maruf.com.myapplication.Ranting.tampilan.Tampilhutang;
import maruf.com.myapplication.menunavigasi;

public class LoginActivity extends AppCompatActivity {
    EditText username,email,password;
Button btloginl;
    FirebaseAuth auth;
    TextView txapayah;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference,databasw1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth= FirebaseAuth.getInstance();
        auth= FirebaseAuth.getInstance();

        final loadingdialog loadingdialog=new loadingdialog(LoginActivity.this);

        email = findViewById(R.id.idemail);
        password = findViewById(R.id.idpass);
        btloginl=findViewById(R.id.btlogin);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databasw1 = database.getReference("message").child("ms");

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

        btloginl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               loadingdialog.starts();

                String txtpass=password.getText().toString();
                String txtemail=email.getText().toString();

                if(txtpass.isEmpty()||txtemail.isEmpty()){
                    Toast.makeText(getApplicationContext(),"username dan password harap di isi",Toast.LENGTH_SHORT).show();

                }else{
                auth.signInWithEmailAndPassword(txtemail,txtpass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                loadingdialog.dismisd();
                            }
                        },1);
                            Intent intent =new Intent(LoginActivity.this, menunavigasi.class);
                            startActivity(intent);



                        }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"username dan password harap di isi dengan benar ",Toast.LENGTH_SHORT).show();
                        TextView peringatan =findViewById(R.id.idperingatan);
                        peringatan.setText("Login Gagal Ulangi ");
                        peringatan.setTextColor(Color.parseColor("#FF0000"));
                            Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                loadingdialog.dismisd();
                            }
                        },1);
//peringatan.setTextColor(255);
                    }
                });}
            }
        });
    }


    public void kgddhs(View view) {

        Intent g =new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(g);
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user =firebaseAuth.getCurrentUser();
        if(user !=null){
            startActivity(new Intent(LoginActivity.this, menunavigasi.class));
//
        }
    }
}
