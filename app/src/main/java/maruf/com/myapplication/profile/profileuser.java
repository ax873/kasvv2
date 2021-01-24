package maruf.com.myapplication.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import maruf.com.myapplication.R;
import maruf.com.myapplication.Ranting.LoginActivity;
import maruf.com.myapplication.Ranting.loadingdialog;
import maruf.com.myapplication.Ranting.settersetkas;
import maruf.com.myapplication.obrolan.obrolan;
import maruf.com.myapplication.obrolan.setobrol;

public class profileuser extends AppCompatActivity {
EditText nama,hutang,email,rayon;
CircleImageView image;
    StorageReference fileref;
    String uid,pas,usernmae,ad,cid;
    Button simpan;
    DatabaseReference db,data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileuser);
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
nama=findViewById(R.id.pnama);
hutang=findViewById(R.id.phutang);
email=findViewById(R.id.pemail);
        fileref= FirebaseStorage.getInstance().getReference().child("profil");
        image=findViewById(R.id.pimage);
rayon=findViewById(R.id.prayon);
        data = FirebaseDatabase.getInstance().getReference("Users");
simpan=findViewById(R.id.psimpan);

        fileref.child(uid+"/profile.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(profileuser.this).load(uri).fit().centerCrop().into(image);
            }
        });

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent intent =new Intent(profileuser.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        db= FirebaseDatabase.getInstance().getReference("Users").child(uid);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                pas = snapshot.child("pass").getValue(String.class);
                cid = snapshot.child("id").getValue(String.class);

                 ad = snapshot.child("username").getValue(String.class);
                nama.setText(ad);
                String a = snapshot.child("rayon").getValue(String.class);
                rayon.setText(a);
                String aa = snapshot.child("status").getValue(String.class);
                email.setText(aa);
                String aza = snapshot.child("image").getValue(String.class);
                hutang.setText(aza);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opengaleri=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(opengaleri,1000);

            }
        });

    }

    private void Simpna(settersetkas settersetkas, String cid) {

                data.child(cid).setValue(settersetkas).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(getApplicationContext(),"Berhasil Memperbaharui  Profil ",Toast.LENGTH_LONG).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Gagal memperbaharui Profil ",Toast.LENGTH_LONG).show();

            }
        });
    }
//
//    private void Simpna(settersetkas settersetkas) {
//
//        data.child(cid).setValue(settersetkas).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                Toast.makeText(getApplicationContext(),"Berhasil Memperbaharui  Profil ",Toast.LENGTH_LONG).show();
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(getApplicationContext(),"Gagal memperbaharui Profil ",Toast.LENGTH_LONG).show();
//
//            }
//        });
//
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==1000){
            if (resultCode== Activity.RESULT_OK){
                Uri imageuri=data.getData();

                uploadyy(imageuri);

                // ipprofile.setImageURI(imageuri);
            }
        }
    }


    private void uploadyy(Uri imageuri) {

        fileref.child(uid+"/profile.jpg").putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                fileref.child(uid+"/profile.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.with(profileuser.this).load(uri).fit().centerCrop().into(image);
                    }
                });

                Toast.makeText(getApplicationContext(),"Berhasil Ubah Foto Profil ",Toast.LENGTH_LONG).show();



            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Gagal Kirim Pesan",Toast.LENGTH_LONG).show();


            }
        });

    }
}