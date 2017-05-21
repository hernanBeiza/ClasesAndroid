package cl.hiperactivo.todoapp.controller.TareasController;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cl.hiperactivo.todoapp.R;
import cl.hiperactivo.todoapp.model.TareaModel;

/**
 * Created by hernanBeiza on 5/20/17.
 */

public class TareasAdapter extends ArrayAdapter <TareaModel> {
    private static final String tag ="TareasAdapter";


    public TareasAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<TareaModel> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TareaModel tarea = getItem(position);
        //Log.d(tag,tarea.toString());

        // ¿Ponermos el layout a la vista? ¿Está creada?
        // Para la primera vez
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.layout_tarea,null);
        }

        if(tarea!=null){
            TextView tituloTextView = (TextView)convertView.findViewById(R.id.tituloTextView);
            tituloTextView.setText(tarea.getTitulo());
            TextView descripcionTextView = (TextView)convertView.findViewById(R.id.descripcionTextView);
            descripcionTextView.setText(tarea.getDescripcion());
        }
        return convertView;

    }
}
