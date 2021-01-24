package maruf.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;
import maruf.com.myapplication.Ranting.LoginActivity;
import maruf.com.myapplication.Ranting.MenuUtama;
import maruf.com.myapplication.Ranting.RegisterActivity;
import maruf.com.myapplication.Ranting.tampilan.MainTampil;
import maruf.com.myapplication.obrolan.obrolan;

public class Fragmendashborh extends Fragment {
CircleImageView simpan;
DatabaseReference db;
    String stss;
    TextView keterangan;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        db= FirebaseDatabase.getInstance().getReference("Users").child(uid);
        View root= inflater.inflate(R.layout.fragment_dashboard, container, false);
        simpan=(CircleImageView) root.findViewById(R.id.batsimp);
        keterangan=(TextView) root.findViewById(R.id.idketerangan);



        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (stss.equals("Anggota")){

                    Intent a=new Intent(getActivity(), obrolan.class);
                    startActivity(a);
                    keterangan.setText("Konfirmasi Pembayaran");

                }
                if (stss.equals("Admin Rayon")){
                    Intent am=new Intent(getActivity(), obrolan.class);
                    startActivity(am);
                    keterangan.setText("Konfirmasi Pembayaran");
                }
                if (stss.equals("Admin Ranting")){
                    Intent ay=new Intent(getActivity(), MenuUtama.class);
                    startActivity(ay);
                    keterangan.setText("Menu Admin");
                } else{

keterangan.setText("Harap Tunggu Sebentar");
                }




            }
        });



        return root;

    }

    @Override
    public void onStart() {
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                stss = snapshot.child("status").getValue(String.class);
simpan.setVisibility(View.VISIBLE);

                if (stss.equals("Anggota")){


                    keterangan.setText("Konfirmasi Pembayaran");

                }
                if (stss.equals("Admin Rayon")){

                    keterangan.setText("Konfirmasi Pembayaran");
                }
                if (stss.equals("Admin Ranting")){
                   
                    keterangan.setText("Menu Admin");
                } else{

                    keterangan.setText("Harap Tunggu Sebentar");
                }




            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        super.onStart();
    }
}