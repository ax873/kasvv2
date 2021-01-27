package maruf.com.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import maruf.com.myapplication.Ranting.LoginActivity;
import maruf.com.myapplication.loginnomo.MainActivity;
import maruf.com.myapplication.pushdaftar.cobapush;

public class cabangrincian extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cabangrincian);
    }

    public void log(View view) {
        Intent a=new Intent(cabangrincian.this, LoginActivity.class);
        startActivity(a);
    }

    public void nkn(View view) {
        Intent a=new Intent(cabangrincian.this, MainActivity.class);
        startActivity(a);
    }
    public void daftar(View view) {
        Intent g =new Intent(cabangrincian.this, cobapush.class);
        startActivity(g);
    }
}