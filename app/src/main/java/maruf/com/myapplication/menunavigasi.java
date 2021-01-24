package maruf.com.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import maruf.com.myapplication.Ranting.tampilan.MainTampil;

public class menunavigasi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menunavigasi);


        BottomNavigationView navView = findViewById(R.id.bottom_navigation);
           navView.setOnNavigationItemSelectedListener(navlistenet);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmen_contarinet,new HomeFragmen()).commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navlistenet=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedfragmen=null;
            switch (item.getItemId()){
                case R.id.nav_home: selectedfragmen =new HomeFragmen();
                    break;
                case R.id.nav_dasgbore: selectedfragmen =new Fragmendashborh();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragmen_contarinet, selectedfragmen).commit();
            return true;
        }
    };

}