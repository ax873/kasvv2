package maruf.com.myapplication.profile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
import maruf.com.myapplication.push.pushdaftar;
import maruf.com.myapplication.pushdaftar.cobapush;


public class semuauser extends AppCompatActivity {

    private ListView listview;
    public  static  final String KS_AMA="nama";
    public  static  final String KS_D="id";
    public  static  final String KS_ANTING="ranting";
    public  static  final String KS_UMLAH="jumlah";
    public  static  final String KS_ESK="desk";
    public  static  final String KS_ITLE="title";
    private    List<User> htkaslist;
    DatabaseReference dat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semuauser);
        htkaslist = new ArrayList<>();
        listview = findViewById(R.id.lisviewkas);
        dat = FirebaseDatabase.getInstance().getReference("Users");

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                User kas=htkaslist.get(position);
                Intent a =new Intent(getApplicationContext(), pushdaftar.class);
                a.putExtra("KS",kas.getId());
                a.putExtra("KSuser",kas.getUsername());
                a.putExtra("KShutang",kas.getImage());
                a.putExtra("KSstatus",kas.getStatus());
                a.putExtra("KSray",kas.getRayon());
                startActivity(a);


            }
        });



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
