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

import maruf.com.myapplication.Ranting.setterr.setkas;
import maruf.com.myapplication.loginnomo.User;

public class kastlistuser extends ArrayAdapter<User> {

    private Activity context;
    private List<User> selist;


    public kastlistuser(@NonNull Activity context, List<User> selist) {
        super(context, R.layout.list_layout,selist);
        this.context=context;
        this.selist=selist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View Listviewitem=inflater.inflate(R.layout.list_layout2,null,true);
        TextView textnama=(TextView)Listviewitem.findViewById(R.id.tv_namaprofil);
        TextView texyjumlah=(TextView)Listviewitem.findViewById(R.id.tv_jumlahprofil);

        User kas=selist.get(position);
        textnama.setText(kas.getUsername());
        texyjumlah.setText(kas.getImage());

        return Listviewitem;
    }
}
