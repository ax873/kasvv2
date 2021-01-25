package maruf.com.myapplication.loginnomo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import maruf.com.myapplication.R;

public class bismila extends AppCompatActivity {
    EditText id,nhutang,npass,nrayon,nstatus,nusername;
    Button simpan;
    DatabaseReference databas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bismila);

        nhutang=findViewById(R.id.rregimaga);
        databas = FirebaseDatabase.getInstance().getReference("Users");
        nstatus=findViewById(R.id.rregstats);
        nrayon=findViewById(R.id.rregray);
        npass=findViewById(R.id.rregpass);
        nusername=findViewById(R.id.rregnama);
        simpan=findViewById(R.id.btregregnum);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a=nusername.getText().toString();
                String b=npass.getText().toString();
                String c=nrayon.getText().toString();
                String d=nstatus.getText().toString();
                String e=nhutang.getText().toString();

                simpain(new User(a,e,b,c,d,a));

            }

        });


    }

    private void simpain(User user) {

        String cid = nusername.getText().toString().trim();
        databas
                .child(cid)
                .setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                    }
                });



    }
}