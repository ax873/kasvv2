package maruf.com.myapplication.Ranting.tampilan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import maruf.com.myapplication.R;
import maruf.com.myapplication.Ranting.coba.setsaldo;
import maruf.com.myapplication.Ranting.setter.kaslist;
import maruf.com.myapplication.Ranting.setterr.setkas;
import maruf.com.myapplication.Ranting.ubahhutang;


public class Tampilhutang extends AppCompatActivity {
    private ListView htlistviewkas;
    public  static  final String KS_AMA="nama";
    public  static  final String KS_D="id";
    public  static  final String KS_ANTING="ranting";
    public  static  final String KS_UMLAH="jumlah";
    public  static  final String KS_ESK="desk";
    public  static  final String KS_ITLE="title";
   private EditText caari;



    List<setkas> kaslist;
   private TextView httxtapayah;
  private DatabaseReference htdatabasw1;
 private    List<setkas> htkaslist;
   private DatabaseReference htdatabasekas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hutang_tampil);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        htdatabasw1 = database.getReference("message").child("ms");
        htdatabasekas = FirebaseDatabase.getInstance().getReference("hutang");
        htkaslist = new ArrayList<>();
        htlistviewkas = (ListView) findViewById(R.id.htlisviewkas);
        httxtapayah=(TextView) findViewById(R.id.htidapayah);


        htdatabasw1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                setsaldo value = dataSnapshot.getValue(setsaldo.class);
                httxtapayah.setText(value.getSaldo());

                int hasil;
                TextView   tuunjuk = (TextView) findViewById(R.id.htidapayah1);
                NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
                hasil= Integer.parseInt(httxtapayah.getText().toString());
                Locale loca = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(loca);
                tuunjuk.setText(formatRupiah.format((double)hasil));
                httxtapayah.setVisibility(View.INVISIBLE);


            }



            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        htlistviewkas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                setkas kas=htkaslist.get(position);
                Intent a =new Intent(getApplicationContext(), ubahhutang.class);
                a.putExtra(KS_D,kas.getIdkas());
                a.putExtra(KS_AMA,kas.getNama());
                a.putExtra(KS_ANTING,kas.getRanting());
                a.putExtra(KS_UMLAH,kas.getJumlah());
                a.putExtra(KS_ESK,kas.getDesk());
                a.putExtra(KS_ITLE,kas.getTitle());
                startActivity(a);


            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        htdatabasekas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                htkaslist.clear();

                for(DataSnapshot setnapasopt:dataSnapshot.getChildren()){
                    setkas kas=setnapasopt.getValue(setkas.class);
                    htkaslist.add(kas);
                }
              maruf.com.myapplication.Ranting.setter.kaslist adapter=new kaslist(Tampilhutang.this,htkaslist);
                htlistviewkas.setAdapter(adapter);
//                Collections.reverse(kaslist);
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}
