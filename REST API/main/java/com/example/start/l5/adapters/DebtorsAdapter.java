package com.example.start.l5.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.start.l5.R;
import com.example.start.l5.models.Debtor;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by start on 2017-11-10.
 */

public class DebtorsAdapter extends BaseAdapter {
    private Context context;
    private List<Debtor> debtors = new ArrayList<>();
    private Debtor debtor;

    public DebtorsAdapter(Context context, List<Debtor> debtors) {
        this.context = context;
        this.debtors = debtors;

    }
    @Override
    public int getCount() {
        return debtors.size();
    }

    @Override
    public Object getItem(int i) {
        return debtors.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.list, null);
        debtor = (Debtor) getItem(i);
        ((TextView) row.findViewById(R.id.debtorName)).setText(debtor.getName());
        ((TextView) row.findViewById(R.id.debtorPhone)).setText(debtor.getPhoneNumber());

        Bitmap photo = null;
        String photoUri = debtor.getPhoto();

        try {
            if(photoUri != null){
                Log.d("LOGd", photoUri);
                Uri imageUri = Uri.parse(photoUri);
                photo = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
            }
        } catch (Exception e) {
            photo = null;
            Log.e("lab03Error", e.getStackTrace().toString());
        }
        if(photo == null){
            Log.d("Log", "!!!!!!");
//            photo = BitmapFactory.decodeResource(context.getResources(),
//                    R.drawable.user);
        }

        ((ImageView) row.findViewById(R.id.imageView3)).setImageBitmap(photo);
        return row;
    }
}
