package maruf.com.myapplication.Ranting;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

import maruf.com.myapplication.R;

public class RegisterActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    EditText username,email,password;
    Button btregister;
    TextView trayon,status;
    RadioGroup rg,sts;
    FirebaseAuth auth;
    RadioButton buntu, krsari, ketingan, dipo, tunas, randegan,gebang,adisan,gunung,ranting,rayon,anggota;

    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.idusername);
        email = findViewById(R.id.email);
        trayon = findViewById(R.id.textrayon);
        password = findViewById(R.id.idpass);
        btregister = findViewById(R.id.btnregister);
     status= findViewById(R.id.idstatsu);
        auth = FirebaseAuth.getInstance();
        sts = (RadioGroup) findViewById(R.id.status);
        final loadingdialog loadingdialog=new loadingdialog(RegisterActivity.this);
        rg = (RadioGroup) findViewById(R.id.idrayon);
        rg.setOnCheckedChangeListener(this);
        buntu = (RadioButton) findViewById(R.id.idbuntu);
        krsari = (RadioButton) findViewById(R.id.idkarangsari);
        ketingan = (RadioButton) findViewById(R.id.idketingan2);
        dipo = (RadioButton) findViewById(R.id.idiponegoro);
        tunas = (RadioButton) findViewById(R.id.idtunas);
        randegan = (RadioButton) findViewById(R.id.idrandegan);
        gebang = (RadioButton) findViewById(R.id.idadisane);
        adisan = (RadioButton) findViewById(R.id.gebang);
        gunung = (RadioButton) findViewById(R.id.idgunung);

        sts.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.adminrnting) {
                    status.setText("Admin Ranting");     }
                if (checkedId == R.id.adminrayon) {
                    status.setText("Admin Rayon");    }
                if (checkedId == R.id.anggota) {
                    status.setText("Anggota");      }


            }

        });

    }



    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.idiponegoro) {
            trayon.setText("SMA Diponegoro");     }
        if (checkedId == R.id.idkarangsari) {
            trayon.setText("Karangsari");    }
        if (checkedId == R.id.idketingan2) {
            trayon.setText("SMP Negeri 2 Ketingan");      }
        if (checkedId == R.id.idtunas) {
            trayon.setText("SMK Plus Tunas Bangsa");     }
        if (checkedId == R.id.idrandegan) {
            trayon.setText("Randegan");     }
        if (checkedId == R.id.idadisane) {
            trayon.setText("SMP Negeri 2 Adisana");    }
        if (checkedId == R.id.gebang) {
            trayon.setText("Gebang Sari");    }
        if (checkedId == R.id.idgunung) {
            trayon.setText("Gunung Nangka");         }
        if (checkedId == R.id.idbuntu) {
            trayon.setText("Buntu");
        } if (checkedId == R.id.idpilihsalahsatu) {
            trayon.setText("");
        }

        btregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingdialog.starts();
                String txtusername=username.getText().toString();
                String txtpass=password.getText().toString();
                String txtemail=email.getText().toString();
                String ayon=trayon.getText().toString();
                String stus=status.getText().toString();
                if(txtusername.isEmpty()||txtpass.isEmpty()||txtemail.isEmpty()||ayon.isEmpty()||stus.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Semua kolom harap di isi dan password minimal 6 huruf",Toast.LENGTH_SHORT).show();
                }else{
                    register(txtusername,txtemail,txtpass,ayon,stus);
                }

            }
        });

    }

    private void register( final String username, String email, final String password, final String ayon,final String stus){
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser=auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid=firebaseUser.getUid();
                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);
                            HashMap<String, String> hashMap =new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("username",username);
                            hashMap.put("pass",password);
                            hashMap.put("image","default");
                            hashMap.put("rayon",ayon);
                            hashMap.put("status",stus);
                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Handler handler=new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {

                                                loadingdialog.dismisd();
                                            }
                                        },1);

                                        FirebaseAuth.getInstance().signOut();
                                        Intent intent =new Intent(RegisterActivity.this,LoginActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getApplicationContext(),"username dan password harap di isi dengan benar ",Toast.LENGTH_SHORT).show();


                                    Handler handler=new Handler();
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            loadingdialog.dismisd();
                                        }
                                    },1);


                                }
                            });
                        }

                    }
                });

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}







//
//    private Button btSignUp;
//    private EditText etEmail;
//    private EditText etPassword;
//
//    private FirebaseAuth fAuth;
//    private FirebaseAuth.AuthStateListener fStateListener;
//
//    private static final String TAG = RegisterActivity.class.getSimpleName();
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
//        /**
//         * Inisialisasi Firebase Auth
//         */
//        fAuth = FirebaseAuth.getInstance();
//
//        /**
//         * Cek apakah ada user yang sudah login
//         */
//        fStateListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null) {
//                    // User sedang login
//                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
//                } else {
//                    // User sedang logout
//                    Log.d(TAG, "onAuthStateChanged:signed_out");
//                }
//            }
//        };
//
//        btSignUp = (Button) findViewById(R.id.bt_signup);
//        etEmail = (EditText) findViewById(R.id.et_email);
//        etPassword = (EditText) findViewById(R.id.et_password);
//
//        btSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                /**
//                 * Lempar email dan password ketika tombol signup diklik
//                 */
//                signUp(etEmail.getText().toString(), etPassword.getText().toString());
//            }
//        });
//
//    }
//
//    private void signUp(final String email, String password){
//
//        fAuth.createUserWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());
//
//                        /**
//                         * Jika sign in gagal, tampilkan pesan ke user. Jika sign in sukses
//                         * maka auth state listener akan dipanggil dan logic untuk menghandle
//                         * signed in user bisa dihandle di listener.
//                         */
//                        if (!task.isSuccessful()) {
//                            task.getException().printStackTrace();
//                            Toast.makeText(RegisterActivity.this, "Proses Pendaftaran Gagal",
//                                    Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(RegisterActivity.this, "Proses Pendaftaran Berhasil\n" +
//                                            "Email "+email,
//                                    Toast.LENGTH_SHORT).show();
//                        }
//
//                        // rest of code
//                    }
//                });
//
//    }
//
//    @Overridehsa
//    protected void onStart() {
//        super.onStart();
//        fAuth.addAuthStateListener(fStateListener);
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (fStateListener != null) {
//            fAuth.removeAuthStateListener(fStateListener);
//        }
//    }
//}
//
