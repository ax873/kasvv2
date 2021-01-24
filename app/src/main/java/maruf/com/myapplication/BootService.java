package maruf.com.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import maruf.com.myapplication.push.MainActivity;

public class BootService extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
//            Toast.makeText(context,"Hay sayang",Toast.LENGTH_LONG).show();
//Intent a=new Intent(context, coba.class);
//context.startActivity(a);
//        }

        if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())){
            Intent i=new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          //  context.startActivity(i);
        }
    }
}
