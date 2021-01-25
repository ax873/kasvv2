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

import maruf.com.myapplication.Ranting.setter.kaslist;
import maruf.com.myapplication.Ranting.setterr.setkas;
import maruf.com.myapplication.Ranting.tampilan.MainTampil;
import maruf.com.myapplication.Ranting.tampilan.Tampilhutang;
import maruf.com.myapplication.loginnomo.User;


public class semuauser extends AppCompatActivity {

    private ListView listview;

    private    List<User> htkaslist;
    DatabaseReference dat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semuauser);
        htkaslist = new ArrayList<>();
        listview = findViewById(R.id.lisviewkas);
        dat = FirebaseDatabase.getInstance().getReference("user");





    }


    @Override
    protected void onStart() {
        super.onStart();
        dat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                htkaslist.clear();

                for(DataSnapshot setnapasopt:dataSnapshot.getChildren()){
                    User kas=setnapasopt.getValue(User.class);
                    htkaslist.add(kas);
                }
                kastlistuser adapter=new kastlistuser(semuauser.this,htkaslist);
                listview.setAdapter(adapter);
//                Collections.reverse(kaslist);
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
