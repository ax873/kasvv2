package maruf.com.myapplication.Ranting.Rekapitulasi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import maruf.com.myapplication.R;

public class RekapReportActivity extends AppCompatActivity {

    private Button btnExport;
    private EditText txtNotes;
    private Spinner spnBulan, spnTahun;

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
                if (spnBulan.getSelectedItemPosition()==0){
                    Toast.makeText(RekapReportActivity.this, "Pilih bulannya dulu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (spnTahun.getSelectedItemPosition()==0){
                    Toast.makeText(RekapReportActivity.this, "Pilih tahunnya dulu", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (txtNotes.getText().toString().equals("")){
                    txtNotes.setError("Harus terisi");
                    return;
                }
                setPreview();
            }
        });
    }

    private String timestamp() {
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return df.format(Calendar.getInstance().getTime());
    }

    private String buatText(){
        StringBuilder string = new StringBuilder();
        string.append("=====================================\n");
        string.append("LAPORAN KAS\n");
        string.append("=====================================\n");
        string.append("Rekap ").append(arrayBulan[spnBulan.getSelectedItemPosition()]).append(" ").append(arrayTahun[spnTahun.getSelectedItemPosition()]).append("\n");
        string.append("=====================================\n\n");

        string.append("Pemasukan\n");
        string.append("-------------------------------------\n");
        // Isian pemasukan
        string.append("- 01/01/2020 ").append("         ").append("Rp.10.000").append("\n");
        string.append("- 02/01/2020 ").append("         ").append("Rp.20.000").append("\n");
        string.append("- 03/01/2020 ").append("         ").append("Rp.30.000").append("\n");
        string.append("- 04/01/2020 ").append("         ").append("Rp.40.000").append("\n");
        string.append("\n");

        string.append("Pemasukan\n");
        string.append("-------------------------------------\n");
        // Isian pengeluaran
        string.append("- 05/01/2020 ").append("         ").append("Rp.10.000").append("\n");
        string.append("- 06/01/2020 ").append("         ").append("Rp.20.000").append("\n");
        string.append("- 07/01/2020 ").append("         ").append("Rp.30.000").append("\n");
        string.append("- 08/01/2020 ").append("         ").append("Rp.40.000").append("\n");
        string.append("\n");

        string.append("Total\n");
        string.append("-------------------------------------\n");
        string.append("- Pemasukan   : ").append("      ").append("Rp.100.000").append("\n");
        string.append("- Pengeluaran  : ").append("      ").append("Rp.100.000").append("\n");
        string.append("\n");

        string.append("-------------------------------------\n");
        string.append("Direkap pada ").append(timestamp()).append("\n");
        string.append(txtNotes.getText().toString()).append("\n");
        string.append("-------------------------------------\n");
        string.append("TERIMAKASIH\n");
        string.append("=====================================\n");
        string.append("=====================================");
        return string.toString();
    }

    private void setPreview(){
        Intent intent = new Intent(this, RekapPreviewActivity.class);
        intent.putExtra("report", buatText());
        startActivity(intent);
    }

}
