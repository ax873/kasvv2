package maruf.com.myapplication.Ranting;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import maruf.com.myapplication.R;
import maruf.com.myapplication.cabangrincian;


public class splace extends AppCompatActivity {


    private static int splashInterval = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splace);


        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                Intent a = new Intent(splace.this, cabangrincian.class);
                splace.this.finish();
                startActivity(a); // menghubungkan activity splashscren ke main activity dengan intent


                //jeda selesai Splashscreen

            }

            private void finish() {
                // TODO Auto-generated method stub

            }
        }, splashInterval);

    };}

