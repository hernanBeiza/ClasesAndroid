package cl.hiperactivo.tiendas.Controllers.Principal;

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

import cl.hiperactivo.tiendas.Model.TiendaModel;
import cl.hiperactivo.tiendas.R;

/**
 * Created by hernanBeiza on 6/10/17.
 */

public class TiendasAdapter extends ArrayAdapter <TiendaModel> {

    public TiendasAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<TiendaModel> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TiendaModel tienda = getItem(position);
        //Log.d(tag,tarea.toString());

        // ¿Ponermos el layout a la vista? ¿Está creada?
        // Para la primera vez
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.tienda_layout,null);
        }

        //Crear item de la lista de tiendas
        if(tienda!=null){
            TextView nombreTextView = (TextView)convertView.findViewById(R.id.nombreTextView);
            nombreTextView.setText(tienda.getNombre());
            TextView direccionTextView = (TextView)convertView.findViewById(R.id.direccionTextView);
            direccionTextView.setText(tienda.getDireccion());
        }
        return convertView;

    }


}
