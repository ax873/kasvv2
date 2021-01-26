package maruf.com.myapplication.tampilhutang;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import maruf.com.myapplication.R;

public class hutangtampil extends AppCompatActivity {
TextView hut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hutangtampil);
        hut=findViewById(R.id.idhutrevisi);





    }
}