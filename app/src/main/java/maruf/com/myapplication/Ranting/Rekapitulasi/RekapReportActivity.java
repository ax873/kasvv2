package maruf.com.myapplication.Ranting.Rekapitulasi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import maruf.com.myapplication.R;
import maruf.com.myapplication.Ranting.setterr.setkas;

public class RekapReportActivity extends AppCompatActivity {

    private Button btnExport;
    private EditText txtNotes;
    private Spinner spnBulan, spnTahun;
    private String strPemasukan,st;

    private static String[] arrayBulan = new String[]{
            "Pilih Bulan",
            "Januari",
            "Februari",
            "Maret",
            "April",
            "Mei",
            "Juni",
            "Juli",
            "Agustus",
            "September",
            "Oktober",
            "Nopember",
            "Desember"
    };

    private static String[] arrayTahun = new String[]{
            "Pilih Tahun",
            "2018",
            "2019",
            "2020",
            "2021",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap_report);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Rekapitulasi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtNotes = findViewById(R.id.txtKeterangan);
        spnBulan = findViewById(R.id.spnBulan);
        spnTahun = findViewById(R.id.spnTahun);
        btnExport = findViewById(R.id.btnExport);

        //Set Spinner Bulan
        final List<String> listBulan = new ArrayList<>(Arrays.asList(arrayBulan));
        final ArrayAdapter<String> spinnerAdapterBulan = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, listBulan);
        spinnerAdapterBulan.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnBulan.setAdapter(spinnerAdapterBulan);
        spnBulan.setSelection(0);

        //Set Spinner Tahun
        final List<String> listTahun = new ArrayList<>(Arrays.asList(arrayTahun));
        final ArrayAdapter<String> spinnerAdapterTahun = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, listTahun);
        spinnerAdapterTahun.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnTahun.setAdapter(spinnerAdapterTahun);
        spnTahun.setSelection(0);

        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spnBulan.getSelectedItemPosition() == 0) {
                    Toast.makeText(RekapReportActivity.this, "Pilih bulannya dulu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (spnTahun.getSelectedItemPosition() == 0) {
                    Toast.makeText(RekapReportActivity.this, "Pilih tahunnya dulu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txtNotes.getText().toString().equals("")) {
                    txtNotes.setError("Harus terisi");
                    return;
                }
                strPemasukan = buatText();

            }
        });
    }

    private String timestamp() {
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return df.format(Calendar.getInstance().getTime());
    }

    double pemasukan = 0;



    private StringBuilder textHeader(){
        StringBuilder string = new StringBuilder();
        string.append("=====================================\n");
        string.append("LAPORAN KAS\n");
        string.append("=====================================\n");
        string.append("Rekap ").append(arrayBulan[spnBulan.getSelectedItemPosition()]).append(" ").append(arrayTahun[spnTahun.getSelectedItemPosition()]).append("\n");
        string.append("=====================================\n\n");
        return string;
    }

    private StringBuilder textFooter(){
        StringBuilder string = new StringBuilder();
        string.append("-------------------------------------\n");
        string.append("Direkap pada ").append(timestamp()).append("\n");
        string.append(txtNotes.getText().toString()).append("\n");
        string.append("-------------------------------------\n");
        string.append("TERIMAKASIH\n");
        string.append("=====================================\n");
        string.append("=====================================");
        return string;
    }

    private String buatText(){
        StringBuilder sb = new StringBuilder();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("pemasukan");
        ref.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sb.append(textHeader());
                sb.append("Pemasukan\n");
                sb.append("-------------------------------------\n");
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    setkas sk = postSnapshot.getValue(setkas.class);

                    String[] dateStr = sk.getRanting().split("-");
                    if (spnBulan.getSelectedItemPosition()==Integer.parseInt(dateStr[1]) && Integer.parseInt(arrayTahun[spnTahun.getSelectedItemPosition()])==Integer.parseInt(dateStr[2])){
                        pemasukan += Double.parseDouble(sk.getJumlah());

                        sb.append("- ").append(sk.getRanting()).append("\t\t\t\t\t\t\t").append(formatrupiah(Double.parseDouble(sk.getJumlah()))).append("\n");
                    }
                    sb.append("\n");

                    /*
                     *
                     * TAMBAHKAN retrieve data pengeluaran disini
                     * Kreasikan sendiri
                     *
                     **/

                }

                sb.append("Total\n");
                sb.append("-------------------------------------\n");
                sb.append("- Pemasukan   : ").append("\t\t\t").append(formatrupiah(pemasukan)).append("\n");

                sb.append(textFooter());
                Intent intent = new Intent(RekapReportActivity.this, RekapPreviewActivity.class);
                intent.putExtra("report", sb.toString());
                startActivity(intent);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("dbPemasukan", error.toString());
            }
        });

        return sb.toString();
    }





    private String formatrupiah(Double number){
        Locale localeID=new Locale("IDN","ID");
        NumberFormat numberFormat=NumberFormat.getCurrencyInstance(localeID);
        String formatrupiah=numberFormat.format(number);
        String[] split = formatrupiah.split(",");
        int length=split[0].length();
        return split[0].substring(0,2)+". "+split[0].substring(2,length);
    }

    /**
    private String buatText() {
        Log.i("listPemasukan", strPemasukan);
        string.append(strPemasukan);


        string.append("Pemasukan\n");
        string.append("-------------------------------------\n");
        // Isian pengeluaran
        string.append("- ").append("         ").append("\n");
        string.append("\n");

        string.append("Total\n");
        string.append("-------------------------------------\n");
        string.append("- Pemasukan   : ").append("      ").append("Rp.100.000").append("\n");
        string.append("- Pengeluaran  : ").append("      ").append("Rp.100.000").append("\n");
        string.append("\n");


        return string.toString();
    }
**/

}
