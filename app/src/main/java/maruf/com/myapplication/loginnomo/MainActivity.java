package maruf.com.myapplication.loginnomo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

import maruf.com.myapplication.R;
import maruf.com.myapplication.Ranting.LoginActivity;
import maruf.com.myapplication.Ranting.MenuUtama;
import maruf.com.myapplication.Ranting.tampilan.Mantab;
import maruf.com.myapplication.menunavigasi;
import maruf.com.myapplication.rayonbuntu.tampilan.MainTampil;
import maruf.com.myapplication.tampil;

public class MainActivity extends AppCompatActivity {
EditText user,pass;
String codesend;
Button sign;
FirebaseDatabase databse;
DatabaseReference users;
FirebaseAuth mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        user = findViewById(R.id.iduss);
        databse=FirebaseDatabase.getInstance();
        users=databse.getReference("user");
        pass = findViewById(R.id.idpassss);
        mauth = FirebaseAuth.getInstance();
sign=findViewById(R.id.buttonsign);

sign.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String a =user.getText().toString();
        String b=  pass.getText().toString();
        signin(a,
                b);
    }
});

    }

    private void signin(final String username,final String password) {
        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(username).exists()){
                    if(!username.isEmpty()){
User login=snapshot.child(username).getValue(User.class);

if(login.getPass().equals(password)){
    Intent intent =new Intent(MainActivity.this, tampil.class);
    startActivity(intent);
}else{
    Toast.makeText(getApplicationContext(),"login ",Toast.LENGTH_LONG).show();

}
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"login ANJIRRRRRRR",Toast.LENGTH_LONG).show();
            }
        });
    }
}