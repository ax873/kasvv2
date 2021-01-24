package maruf.com.myapplication.Ranting.coba;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import maruf.com.myapplication.R;


public class coba extends AppCompatActivity {
EditText edisisaldo;
//    DatabaseReference myRef;
TextView edtampilsaldo;
DatabaseReference databasw;
Button edsimpansaldo,edubahsaldo,edhapussaldo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coba);

        edisisaldo=(EditText)findViewById(R.id.idisisaldo);
        edtampilsaldo=(TextView) findViewById(R.id.susaldo);
        edsimpansaldo=(Button)findViewById(R.id.btsimpansaldo);
        edubahsaldo=(Button)findViewById(R.id.btubahsaldo);

        edhapussaldo=(Button)findViewById(R.id.bthapus);
databasw= FirebaseDatabase.getInstance().getReference("Saldo");
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Saldo");
//
////        myRef.addValueEventListener(new ValueEventListener() {
////            @Override
//////            public void onDataChange(DataSnapshot dataSnapshot) {
//////                // This method is called once with the initial value and again
//////                // whenever data at this location is updated.
//////                String value = dataSnapshot.getValue(String.class);
//////                edtampilsaldo.setText(value);
//////            }
//////
//////            @Override
//////            public void onCancelled(DatabaseError error) {
//////                // Failed to read value
//////
//////            }
//////        });aaaaaaaaaaaaaaaaaaaaaaa
////
//////fb=new FirebaseApp("https://bismilahprojekkas.firebaseio.com/Saldo/sekarang/saldo");
////
////        // Read from the database
//////        myRef.addValueEventListener(new ValueEventListener() {
//////            @Override
//////            public void onDataChange(DataSnapshot dataSnapshot) {
//////                // This method is called once with the initial value and again
//////                // whenever data at this location is updated.
//////                String value = dataSnapshot.getValue(String.class);
//////                edtampilsaldo.setText(value);
//////            }
//////
//////            @Override
//////            public void onCancelled(DatabaseError error) {
//////                // Failed to read value
//////
//////            }
//////        });
////
////edsimpansaldo.setOnClickListener(new View.OnClickListener() {
////    @Override
////    public void onClick(View v) {
////        String isisal=edisisaldo.getText().toString().trim();
////        simpansaldo(new setsaldo(isisal.toLowerCase()));
////
////
////
////    }
////});
////
////
////    }
////
////
////
//  private void simpansaldo(setsaldo setsaldo) {
//        databasw.setValue(setsaldo).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//             edisisaldo.setText("");
//
//                Toast.makeText(getApplicationContext(),"saldo tersimpan",Toast.LENGTH_LONG).show();
////tampil();
//
//
//            }
//        });

    }
}
