package maruf.com.myapplication.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import maruf.com.myapplication.R;

import maruf.com.myapplication.loginnomo.User;
import maruf.com.myapplication.rayonbuntu.setter.kaslist;
import maruf.com.myapplication.rayonbuntu.setterr.setkas;
import maruf.com.myapplication.rayonbuntu.tampilan.MainTampil;


public class semuauser extends AppCompatActivity {

    private ListView listviewkas;
    List<User> kaslist;
    DatabaseReference dat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semuauser);
        kaslist = new ArrayList<>();
        listviewkas = findViewById(R.id.idtampilpengguna);
        dat = FirebaseDatabase.getInstance().getReference("Users");





    }

    @Override
    protected void onStart() {
        super.onStart();
        dat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                kaslist.clear();

                for(DataSnapshot setnapasopt:dataSnapshot.getChildren()){
                    User kas=setnapasopt.getValue(User.class);
                    kaslist.add(kas);
                }
                kastlistuser adapter=new kastlistuser(semuauser.this,kaslist);
                listviewkas.setAdapter(adapter);


//
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}