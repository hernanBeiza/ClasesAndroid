package hiperactivo.cl.constraints;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import hiperactivo.cl.constraints.R;

/**
 * Created by hernanBeiza on 5/13/17.
 * Permitirá pintar la vistas de contactos
 */

public class ContactosListAdapter extends ArrayAdapter <String> {

    public ContactosListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View v = convertView;
        if(v == null){
            // Terminar
            LayoutInflater inflater = LayoutInflater.from(getContext());
            v = inflater.inflate(R.layout.layout_lista,null);
        }
        String s = getItem(position);
        if(s!=null){
            // Terminar
            TextView nombreTextView = (TextView) v.findViewById(R.id.nombreTextView);
            nombreTextView.setText(s);
        }
        return v;
    }
}
