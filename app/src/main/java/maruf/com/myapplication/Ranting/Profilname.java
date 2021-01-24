package maruf.com.myapplication.Ranting;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import maruf.com.myapplication.R;
import maruf.com.myapplication.Ranting.setterr.setkas;
import maruf.com.myapplication.Ranting.tampilan.MainTampil;

public class Profilname extends AppCompatActivity {
ListView listb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilname);
        listb=findViewById(R.id.idliste);
        DatabaseReference databasw1;
        List<settersetkas> kalist;
        DatabaseReference dataas;
        dataas = FirebaseDatabase.getInstance().getReference("Users");
        kalist = new ArrayList<>();

        dataas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        kalist.clear();

                for(DataSnapshot setnapasopt:dataSnapshot.getChildren()){
                    settersetkas kas=setnapasopt.getValue(settersetkas.class);
                    kalist.add(kas);
                }
                kaslisthutang adapter=new kaslisthutang(Profilname.this,kalist);
                listb.setAdapter(adapter);
//                Collections.reverse(kaslist);
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

    }
}
