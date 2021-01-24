package maruf.com.myapplication.obrolan;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

import maruf.com.myapplication.R;


public class
liskasobrolan extends ArrayAdapter<setobrol> {

    private Activity context;
    private List<setobrol> setlist;
    StorageReference mStorageRef;
    StorageReference fileref;
    public liskasobrolan(@NonNull Activity context, List<setobrol> setlist) {
        super(context, R.layout.list_lay,setlist);
        this.context=context;
        this.setlist=setlist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View Listviewitem=inflater.inflate(R.layout.list_lay,null,true);
        TextView id=(TextView)Listviewitem.findViewById(R.id.tid);
        ImageView image=(ImageView)Listviewitem.findViewById(R.id.idimegobrolan);
        TextView texyjumlah=(TextView)Listviewitem.findViewById(R.id.tv_jumlah);
        TextView uwwwwwwwwww=(TextView)Listviewitem.findViewById(R.id.iddeskkks);
        setobrol kas=setlist.get(position);
        CardView card=(CardView)Listviewitem.findViewById(R.id.idcar22);
        LinearLayout linier=(LinearLayout)Listviewitem.findViewById(R.id.idlinier);
        texyjumlah.setText(kas.getNama());
        uwwwwwwwwww.setText(kas.getPesan());
        id.setText(kas.getGambar());
        fileref= FirebaseStorage.getInstance().getReference().child("foto");
        String namanama=texyjumlah.getText().toString();
if(namanama.equals("Admin Ranting")){
    texyjumlah.setGravity(Gravity.LEFT | Gravity.BOTTOM);
    card.setCardBackgroundColor(Color.parseColor("#FF0000"));
    texyjumlah.setPadding(30, 0, 0, 0);
   linier.setPadding(0,0,90,0);
}else{
    texyjumlah.setGravity(Gravity.RIGHT | Gravity.BOTTOM);
    texyjumlah.setPadding(0, 0, 100, 0);
    linier.setPadding(90,0,0,0);
}
        String no=id.getText().toString();
        fileref.child(no+"/profile.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getContext()).load(uri).fit().centerCrop().into(image);
            }
        });
        return Listviewitem;
    }
}