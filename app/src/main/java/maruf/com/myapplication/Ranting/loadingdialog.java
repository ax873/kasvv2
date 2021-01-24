package maruf.com.myapplication.Ranting;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import maruf.com.myapplication.R;

public class loadingdialog {

     static Activity activity;
     static AlertDialog dialog;
    public loadingdialog(Activity myaktifity){
        activity =myaktifity;
    }
    public static void starts(){
        AlertDialog.Builder builder= new AlertDialog.Builder(activity);
        LayoutInflater inflater=activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.cuustom,null));
        builder.setTitle("Silahkan Tunggu ....");
        builder.setCancelable(true);
        dialog =builder.create();
        dialog.show();
    }
    public static void dismisd(){
        dialog.dismiss();
    }

}