package maruf.com.myapplication.Ranting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import maruf.com.myapplication.R;
import maruf.com.myapplication.Ranting.tampilan.settertoken;

public class tokenbayar extends AppCompatActivity {
DatabaseReference dbtoken;
TextView txttampil;
Button buttonsim;
EditText nilaitoken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tokenbayar);

        txttampil=findViewById(R.id.txtidtoken);
        buttonsim=findViewById(R.id.simpntoken);
        nilaitoken=findViewById(R.id.edidtooken);
        dbtoken= FirebaseDatabase.getInstance().getReference().child("token");
        dbtoken.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                settertoken ambil=dataSnapshot.getValue(settertoken.class);
                txttampil.setText(ambil.getNamatoken());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        buttonsim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbtoken.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String aaa=nilaitoken.getText().toString().trim();
                        seser(new settertoken(aaa.toLowerCase()));
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

    }

    private void seser(settertoken settertoken) {
        dbtoken.setValue(settertoken).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getApplicationContext(),"uwuuwuwuwuuw berhasil",Toast.LENGTH_LONG).show();

            }
        });
    }
}
