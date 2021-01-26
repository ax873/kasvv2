package maruf.com.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import maruf.com.myapplication.Ranting.LoginActivity;
import maruf.com.myapplication.Ranting.MenuUtama;
import maruf.com.myapplication.Ranting.coba.setsaldo;
import maruf.com.myapplication.Ranting.hutang;
import maruf.com.myapplication.obrolan.obrolan;
import maruf.com.myapplication.profile.profileuser;
import maruf.com.myapplication.rayonbuntu.Main2Activity;
import maruf.com.myapplication.rayonbuntu.MainActivity;
import maruf.com.myapplication.rayonbuntu.MainSaldo;
import maruf.com.myapplication.rayonbuntu.kaslain;
import maruf.com.myapplication.rayonbuntu.tampilan.MainTampil;
import maruf.com.myapplication.tampilhutang.hutangtampil;

public class HomeFragmen extends Fragment {
    public  static  final String KS_S="NAMA";
    DatabaseReference database,asd,db;
    String uid;
    String rays;
    StorageReference fileref;
    CircleImageView gambar;
Button kasranting,hutang,kasrayonlain,bayarhutang,rayonkita,th,keluar,masuk,rayonini,logot,huttang;
TextView txapayah,tnjuk,tampilhutang,bnma,ma,status;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
         uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db= FirebaseDatabase.getInstance().getReference("Users").child(uid);
        database= FirebaseDatabase.getInstance().getReference("message").child("ms");
        huttang=(Button) root.findViewById(R.id.idhutkonfir);
         kasranting = (Button) root.findViewById(R.id.idkasranting);
        gambar = (CircleImageView) root.findViewById(R.id.idgambar);
        status = (TextView) root.findViewById(R.id.stastus);
          hutang = (Button) root.findViewById(R.id.idhhiutang);
         kasrayonlain = (Button) root.findViewById(R.id.idlihatkasrayonlain);
        bayarhutang = (Button) root.findViewById(R.id.idbayarhutang);
        th = (Button) root.findViewById(R.id.idth);
        keluar = (Button) root.findViewById(R.id.idkeluarrayon);
        rayonkita = (Button) root.findViewById(R.id.rincianrayon);
        txapayah = (TextView) root.findViewById(R.id.txpayah);
        tnjuk = (TextView) root.findViewById(R.id.txttampil);
        bnma = (TextView) root.findViewById(R.id.idupa);


huttang.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
      String n=status.getText().toString();
        Intent a =new Intent(getActivity(), hutangtampil.class);
        a.putExtra(KS_S,rays);
        startActivity(a);
    }
});


        ma = (TextView) root.findViewById(R.id.idr);
        masuk = (Button) root.findViewById(R.id.idmsukrayon);
        rayonini=(Button) root.findViewById(R.id.rincianrayon);
        gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(getActivity(), profileuser.class);

                startActivity(a);
            }
        });
        tampilhutang = (TextView) root.findViewById(R.id.tamplhutang);
        kasranting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(getActivity(), tampil.class);
                startActivity(a);
            }
        });
        fileref= FirebaseStorage.getInstance().getReference().child("profil");
        fileref.child(uid+"/profile.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getContext()).load(uri).fit().centerCrop().into(gambar);
            }
        });
        kasrayonlain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a=new Intent(getActivity(), kaslain.class);

                startActivity(a);
            }
        });

        th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a=new Intent(getActivity(), MainSaldo.class);
                String nua=ma.getText().toString();
                a.putExtra(KS_S,nua);
                startActivity(a);
            }
        });
        keluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a=new Intent(getActivity(), Main2Activity.class);
                String nua=ma.getText().toString();
                a.putExtra(KS_S,nua);
                startActivity(a);


            }
        });

        hutang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a=new Intent(getActivity(), maruf.com.myapplication.Ranting.hutang.class);
                String nua="hutang";
                a.putExtra(KS_S,nua);

                startActivity(a);
            }
        });

        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a=new Intent(getActivity(), MainActivity.class);
                String nua=ma.getText().toString();
                a.putExtra(KS_S,nua);
                startActivity(a);
            }
        });
        rayonini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent a=new Intent(getActivity(), MainTampil.class);
                String nua=ma.getText().toString();
                a.putExtra(KS_S,nua);
                startActivity(a);
            }
        });





        return root;
    }

    @Override
    public void onStart() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                 rays = snapshot.child("rayon").getValue(String.class);
                String ad = snapshot.child("username").getValue(String.class);

                ma.setText(rays);
                bnma.setText(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                setsaldo value = dataSnapshot.getValue(setsaldo.class);
                txapayah.setText(value.getSaldo());

                int hasil;

                NumberFormat format = NumberFormat.getCurrencyInstance(Locale.ENGLISH);
                hasil= Integer.parseInt(txapayah.getText().toString());
                Locale loca = new Locale("in", "ID");
                NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(loca);
                tnjuk.setText("Kas Ranting Tersisa ="+formatRupiah.format((double)hasil));

                txapayah.setVisibility(View.INVISIBLE);
            }



            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


bayarhutang.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent a=new Intent(getActivity(), obrolan.class);
        String nua=ma.getText().toString();
        a.putExtra(KS_S,nua);
        startActivity(a);
    }
});

       // asd = FirebaseDatabase.getInstance().getReference("pemasukan").child(uid);
        asd = FirebaseDatabase.getInstance().getReference("Users").child(uid);
        asd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String ad = dataSnapshot.child("image").getValue(String.class);

                tampilhutang.setText("hutang anda "+ad);
                String tamp= tampilhutang.getText().toString();

                if(tamp.equals("hutang anda null")){
                    hutang.setEnabled(true);
                    tampilhutang.setVisibility(View.INVISIBLE);
                }else if(tamp.equals("hutang anda 0")){
                    hutang.setVisibility(View.VISIBLE);
                    hutang.setText("HUTANG");

                }
                if(tamp.equals("hutang anda default")){
                    hutang.setEnabled(true);
                   tampilhutang.setVisibility(View.INVISIBLE);
                    }

                else{
                    hutang.setEnabled(true);
                    hutang.setText("HUTANG");
                 //   hutang.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String stss = snapshot.child("status").getValue(String.class);
                status.setText(stss);
                String s=status.getText().toString();
              if (s.equals("Anggota")){
                  rayonini.setEnabled(true);
                  kasranting.setEnabled(true);

                  huttang.setEnabled(false);
                  kasrayonlain.setEnabled(true);
                  bayarhutang.setEnabled(true);
              }
                if (s.equals("Admin Rayon")){
                    rayonini.setEnabled(true);
                    huttang.setEnabled(true);
                    kasranting.setEnabled(true);
                    kasrayonlain.setEnabled(true);
                    bayarhutang.setEnabled(true);
                    keluar.setEnabled(true);
                    masuk.setEnabled(true);
                    th.setEnabled(true);
                }
                if (s.equals("Admin Ranting")){
                    rayonini.setEnabled(true);
                    huttang.setEnabled(false);
                    kasranting.setEnabled(true);
                    kasrayonlain.setEnabled(true);
                    bayarhutang.setEnabled(true);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        super.onStart();
    }
}