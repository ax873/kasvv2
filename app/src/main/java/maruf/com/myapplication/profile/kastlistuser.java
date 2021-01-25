package maruf.com.myapplication.profile;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import maruf.com.myapplication.R;

import maruf.com.myapplication.loginnomo.User;


public class kastlistuser extends ArrayAdapter<User> {

    private Activity context;
    private List<User> setlist;


    public kastlistuser(@NonNull Activity context, List<User> setlist) {
        super(context, R.layout.lih,setlist);
        this.context=context;
        this.setlist=setlist;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View Listviewitem=inflater.inflate(R.layout.lih,null,true);

        TextView tg=(TextView)Listviewitem.findViewById(R.id.TVHUTANG);
        TextView tf=(TextView)Listviewitem.findViewById(R.id.TV_nama);

        User kas=setlist.get(position);

        tg.setText(kas.getUsername());
        tf.setText(kas.getImage());
        return Listviewitem;
    }
}
