package maruf.com.myapplication.Ranting.Rekapitulasi;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import maruf.com.myapplication.R;

public class RekapPreviewActivity extends AppCompatActivity {

    private TextView tvPreview;
    private String textReport;
    final private int REQUEST_CODE_PERMISSION = 111;
    File pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap_preview);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Preview");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tvPreview = findViewById(R.id.tvPreview);
        textReport = getIntent().getExtras().getString("report");
        tvPreview.setText(textReport);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void createPDF() {
        int hasWritePermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWritePermission != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    new AlertDialog.Builder(this)
                            .setMessage("Access Storage Permission")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .create()
                            .show();
                    return;
                }
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
            }
            return;
        }
            //File docPath = new File(Environment.getExternalStorageDirectory(), "documents");
            //File docPath = this.getDir("documents", Context.MODE_PRIVATE);
            File docPath = new File(Environment.getExternalStorageDirectory(), Environment.DIRECTORY_DOCUMENTS);
            if (!docPath.exists()) {
                docPath.mkdir();
            }
            String fileName = FirebaseAuth.getInstance().getUid();
            pdf = new File(docPath.getAbsolutePath(),  fileName+ ".pdf");
            try {
                OutputStream stream = new FileOutputStream(pdf);
                Document document = new Document();
                PdfWriter.getInstance(document, stream);
                document.open();
                document.add(new Paragraph(textReport));
                document.close();
                Snackbar snacbar = Snackbar.make(findViewById(android.R.id.content), fileName + " Saved: " + pdf.toString(), Snackbar.LENGTH_SHORT);
                snacbar.show();
                Log.d("makePdf", fileName+ "\nSaved: "+pdf.toString());
                snacbar.setAction("Open",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showPDF();
                            }
                        });
            } catch (Exception e) {
                Log.e("makePdf", e.toString());
            }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                try {
                    createPDF();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, "WRITE_External Permission Denied", Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void showPDF() {
        PackageManager manager = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType("application/pdf");
        @SuppressLint("QueryPermissionsNeeded") List list = manager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (list.size()>0) {
            Intent intent1 = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            Uri uri = Uri.fromFile(pdf);
            intent1.setDataAndType(uri, "application/pdf");
            startActivity(intent1);
        } else {
            Toast.makeText(this, "Download any PDF Viewer to Open the Document", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_preview, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        int id = item.getItemId();
        if (id == R.id.item_save){
            createPDF();
        }

        return true;
    }
}