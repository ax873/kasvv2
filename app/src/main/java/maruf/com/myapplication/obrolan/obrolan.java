package maruf.com.myapplication.obrolan;

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
import android.widget.ListView;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.List;

import maruf.com.myapplication.R;
import maruf.com.myapplication.Ranting.LoginActivity;
import maruf.com.myapplication.Ranting.coba.setsaldo;
import maruf.com.myapplication.Ranting.loadingdialog;
import maruf.com.myapplication.Ranting.setter.kaslist;
import maruf.com.myapplication.Ranting.setterr.setkas;
import maruf.com.myapplication.Ranting.tampilan.MainTampil;

public class obrolan extends AppCompatActivity {
Button kirimobrol;
EditText obrol,simpanpesan;
    private ListView listviewkas1;
    StorageReference mStorageRef;
ImageView ipimage;
    String uid,no;
    String uidid;
    DatabaseReference db;
    long maxid=0;
    String pesanana;
    TextView pesan;
    List<setobrol> kaslistobrol;
    DatabaseReference database,da;
     StorageReference fileref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obrolan);
        listviewkas1 = (ListView) findViewById(R.id.idlisviewkas);
        kaslistobrol = new ArrayList<>();
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        da = FirebaseDatabase.getInstance().getReference("Foto").child("obrolan");
        kirimobrol=findViewById(R.id.idkirimobrol);
        obrol =findViewById(R.id.editexobrol);
        simpanpesan =findViewById(R.id.edpesasimpan);
        pesan =findViewById(R.id.idnamapesan);
        ipimage=findViewById(R.id.ivimaage);
        final loadingdialog loadingdialog=new loadingdialog(obrolan.this);
        db= FirebaseDatabase.getInstance().getReference("Users").child(uid);


        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String ad = snapshot.child("username").getValue(String.class);
                pesan.setText(ad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        database = FirebaseDatabase.getInstance().getReference("Foto").child("obrolan");
database.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if(snapshot.exists())
            maxid= (snapshot.getChildrenCount());
       obrol.setText(""+maxid);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});

        fileref= FirebaseStorage.getInstance().getReference().child("foto");

//        no="0";
//        fileref.child(no+"/profile.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                Picasso.with(obrolan.this).load(uri).fit().centerCrop().into(ipimage);
//            }
//        });

         uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

kirimobrol.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
String namap=pesan.getText().toString();
 pesanana=simpanpesan.getText().toString();
        simpanobrolan(new setobrol(namap,uidid,pesanana));

    }
});
        mStorageRef = FirebaseStorage.getInstance().getReference();
        ipimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent opengaleri=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(opengaleri,1000);

            }
        });

    }

    private void simpanobrolan(setobrol setobrol) {


uidid=obrol.getText().toString();
        database
                .child(uidid)
                .setValue(setobrol)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {



                    }
                });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        loadingdialog.starts();

        uidid=obrol.getText().toString();
        if (requestCode==1000){
            if (resultCode== Activity.RESULT_OK){
                Uri imageuri=data.getData();

                upload(imageuri);

                String namap=pesan.getText().toString();
                String pesanana=simpanpesan.getText().toString();
                simpanobrolan(new setobrol(namap,uidid,pesanana));

                // ipprofile.setImageURI(imageuri);
            }
        }
    }

    private void upload(Uri imageuri) {
        no=obrol.getText().toString();
        fileref.child(no+"/profile.jpg").putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.with(obrolan.this).load(uri).fit().centerCrop().into(ipimage);

                    }
                });
                Toast.makeText(getApplicationContext(),"Berhasil Kirim Pesan",Toast.LENGTH_LONG).show();


                loadingdialog.dismisd();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(),"Gagal Kirim Pesan",Toast.LENGTH_LONG).show();


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();


        da.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                kaslistobrol.clear();

                for(DataSnapshot setnapasopt:dataSnapshot.getChildren()){
                    setobrol kas=setnapasopt.getValue(setobrol.class);
                    kaslistobrol.add(kas);
                }
                liskasobrolan adapter=new liskasobrolan(obrolan.this,kaslistobrol);
                listviewkas1.setAdapter(adapter);
//                Collections.reverse(kaslist);
//                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}