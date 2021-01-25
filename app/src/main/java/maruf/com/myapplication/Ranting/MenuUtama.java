package maruf.com.myapplication.Ranting;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import maruf.com.myapplication.R;
import maruf.com.myapplication.Ranting.Rekapitulasi.RekapReportActivity;
import maruf.com.myapplication.Ranting.tampilan.MainTampil;
import maruf.com.myapplication.Ranting.tampilan.Tampilhutang;
import maruf.com.myapplication.loginnomo.bismila;
import maruf.com.myapplication.obrolan.obrolan;
import maruf.com.myapplication.rayonbuntu.kaslain;


public class MenuUtama extends AppCompatActivity {
    private String KEYTITLE = "NAMA";
    DatabaseReference muncul, asd, dbasd;
    TextView ididan, tampilhutang;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    LinearLayout mylayout;
    Button amasuk, akeluar, arinci, aset, akonfir, apengumuman;
    AnimationDrawable animationDrawable;
    Button hutang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_utama);
        hutang = findViewById(R.id.idhutng);
        amasuk = findViewById(R.id.amasuk);
        akeluar = findViewById(R.id.akeluar);
        arinci = findViewById(R.id.arinci);
        aset = findViewById(R.id.aset);
        akonfir = findViewById(R.id.akonfir);
        apengumuman = findViewById(R.id.apengumuman);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        dbasd = FirebaseDatabase.getInstance().getReference("pemasukan").child(uid);
//        mylayout=(LinearLayout) findViewById(R.id.mylayout);
//        animationDrawable=(AnimationDrawable)mylayout.getBackground();
//        animationDrawable.setEnterFadeDuration(4500);
//        animationDrawable.setExitFadeDuration(4500);
//        animationDrawable.start();
//        onStart();
        muncul = FirebaseDatabase.getInstance().getReference("Users").child(uid);
        muncul.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ididan = findViewById(R.id.ididan);
                tampilhutang = findViewById(R.id.tamplhutang);

                Button but = findViewById(R.id.arinci);
                Animation animation = AnimationUtils.loadAnimation(MenuUtama.this, R.anim.sliderig);
                but.startAnimation(animation);
                arinci.setVisibility(View.VISIBLE);

                Button b = findViewById(R.id.amasuk);
                Animation animationb = AnimationUtils.loadAnimation(MenuUtama.this, R.anim.sliderig);
                amasuk.setVisibility(View.VISIBLE);
                b.startAnimation(animationb);

                Button ba = findViewById(R.id.akeluar);
                Animation animationba = AnimationUtils.loadAnimation(MenuUtama.this, R.anim.sliderig);
                akeluar.setVisibility(View.VISIBLE);
                ba.startAnimation(animationba);

                Button be = findViewById(R.id.aset);
                Animation animationbe = AnimationUtils.loadAnimation(MenuUtama.this, R.anim.sliderig);
                aset.setVisibility(View.VISIBLE);
                be.startAnimation(animationbe);

                Button ber = findViewById(R.id.akonfir);
                Animation animationber = AnimationUtils.loadAnimation(MenuUtama.this, R.anim.sliderig);
                akonfir.setVisibility(View.VISIBLE);
                ber.startAnimation(animationber);


                Button bet = findViewById(R.id.apengumuman);
                Animation animationbet = AnimationUtils.loadAnimation(MenuUtama.this, R.anim.sliderig);
                apengumuman.setVisibility(View.VISIBLE);
                bet.startAnimation(animationbet);


                Animation animationdu = AnimationUtils.loadAnimation(MenuUtama.this, R.anim.sliderig);
                hutang.setVisibility(View.VISIBLE);
                hutang.startAnimation(animationdu);
//                String mahasiswa = dataSnapshot.getValue(String.class);
                String id = dataSnapshot.child("id").getValue(String.class);
                String user_id = dataSnapshot.child("username").getValue(String.class);

                ididan.setText(user_id);
                // tampilhutang.setText(id);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        asd = FirebaseDatabase.getInstance().getReference("pemasukan").child(uid);
        asd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                tampilhutang = findViewById(R.id.tamplhutang);
//                String mahasiswa = dataSnapshot.getValue(String.class);
                String ad = dataSnapshot.child("jumlah").getValue(String.class);

                tampilhutang.setText("hutang anda " + ad);
                String tamp = tampilhutang.getText().toString();

                if (tamp.equals("hutang anda null")) {
                    hutang.setEnabled(true);
                    tampilhutang.setVisibility(View.INVISIBLE);
                } else if (tamp.equals("hutang anda 0")) {
                    hapusii();
                    hutang.setVisibility(View.VISIBLE);
                    hutang.setText("HUTANG");

                } else {
                    hutang.setEnabled(false);
                    hutang.setText("Lunasi dulu");
                    hutang.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        findViewById(R.id.btnRekap).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MenuUtama.this, RekapReportActivity.class));
            }
        });
    }

//    @Override
//    protected void onStart() {
//        hutang.setVisibility(View.INVISIBLE);
//        amasuk.setVisibility(View.INVISIBLE);
//        akeluar.setVisibility(View.INVISIBLE);
//        arinci.setVisibility(View.INVISIBLE);
//        aset.setVisibility(View.INVISIBLE);
//        akonfir.setVisibility(View.INVISIBLE);
//        apengumuman.setVisibility(View.INVISIBLE);
//        akontak.setVisibility(View.INVISIBLE);
//        super.onStart();
//    }

    private void hapusii() {
        dbasd

                .removeValue()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {


                    }
                });
    }

    public void uwhjk(View view) {

        Intent a = new Intent(MenuUtama.this, MainActivity.class);
        String nua = "PEMASUKAN";
        a.putExtra(KEYTITLE, nua);
        startActivity(a);
    }

    public void gtdjg(View view) {
        String nua = "PENGELUARAN";
        Intent a = new Intent(this, Main2Activity.class);
        a.putExtra(KEYTITLE, nua);
        startActivity(a);
    }

    public void uwuwuwuw(View view) {

        Intent g = new Intent(MenuUtama.this, MainSaldo.class);
        startActivity(g);
    }

    public void jsjhihf(View view) {
//        if(tampilhutang.equals("HUTANG"))
//        {
        String nua = "HUTANG";
        Intent a = new Intent(MenuUtama.this, hutang.class);
        a.putExtra(KEYTITLE, nua);
        startActivity(a);
//        }else{
//            Toast.makeText(getApplicationContext(),"Bayar Hutang dulu Baru Hutang lagi ",Toast.LENGTH_LONG).show();
//        }

    }

    public void a(View view) {

        Intent gj = new Intent(MenuUtama.this, Tampilhutang.class);
        startActivity(gj);
    }

    public void ihrbr(View view) {
        Intent gjf = new Intent(MenuUtama.this, BayarHutang.class);
        startActivity(gjf);
    }

    public void jkkjn(View view) {
        Intent gjf = new Intent(MenuUtama.this, tokenbayar.class);
        startActivity(gjf);
    }

    public void kkfd(View view) {
        Intent gjf = new Intent(MenuUtama.this, MainTampil.class);
        startActivity(gjf);
    }

    public void hjdgh(View view) {

    }

    public void wuhh(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MenuUtama.this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    public void nnd(View view) {
        Intent gjf = new Intent(MenuUtama.this, kaslain.class);
        startActivity(gjf);

    }

    public void jsdfjkb(View view) {
        Intent gjf = new Intent(MenuUtama.this, RegisterActivity.class);
        startActivity(gjf);
    }

    public void obro(View view) {
        Intent gsf = new Intent(MenuUtama.this, obrolan.class);
        startActivity(gsf);
    }

    public void registrasinohp(View view) {
        Intent gsf = new Intent(MenuUtama.this, bismila.class);
        startActivity(gsf);
    }
}
