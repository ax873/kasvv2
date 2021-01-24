package maruf.com.myapplication.rayonbuntu;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import maruf.com.myapplication.R;

public class loadingdialog {

     static Activity activity;
     static AlertDialog dialog;
    loadingdialog(Activity myaktifity){
        activity =myaktifity;
    }
    static void starts(){
        AlertDialog.Builder builder= new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.cuustom,null));
        builder.setCancelable(true);
        dialog =builder.create();
        dialog.show();
    }
    static void dismisd(){
        dialog.dismiss();
    }

}