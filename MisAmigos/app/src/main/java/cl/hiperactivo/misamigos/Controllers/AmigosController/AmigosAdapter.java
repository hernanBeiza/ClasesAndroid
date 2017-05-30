package cl.hiperactivo.misamigos.Controllers.AmigosController;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import cl.hiperactivo.misamigos.Modelo.AmigoModel;
import cl.hiperactivo.misamigos.R;

/**
 * Created by hernanBeiza on 5/30/17.
 */

public class AmigosAdapter extends ArrayAdapter <AmigoModel> {


    public AmigosAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<AmigoModel> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AmigoModel amigo = getItem(position);
        //Log.d(tag,tarea.toString());

        // ¿Ponermos el layout a la vista? ¿Está creada?
        // Para la primera vez
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_amigo,null);
        }

        //Crear item de la lista de amigos
        if(amigo!=null){
            TextView nombreTextView = (TextView)convertView.findViewById(R.id.nombreTextView);
            nombreTextView.setText(amigo.getNombre());
            TextView telefonoTextView = (TextView)convertView.findViewById(R.id.telefonoTextView);
            telefonoTextView.setText(amigo.getTelefono());
            TextView cumpleanosTextView = (TextView)convertView.findViewById(R.id.cumpleanosTextView);
            cumpleanosTextView.setText(amigo.getCumpleanos());
        }
        return convertView;

    }
}
