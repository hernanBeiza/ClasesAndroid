package cl.hiperactivo.todoapp.controller.AgregarController;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import cl.hiperactivo.todoapp.R;
import cl.hiperactivo.todoapp.model.TareaModel;

public class AgregarActivity extends AppCompatActivity {

    private static final String tag ="AgregarActivity";

    private EditText tituloEditText;
    private EditText descripcionEditText;
    private EditText horaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag,"onCreate");
        setContentView(R.layout.activity_agregar);

        tituloEditText = (EditText)findViewById(R.id.tituloEditText);
        descripcionEditText = (EditText)findViewById(R.id.descripcionEditText);
        horaEditText = (EditText)findViewById(R.id.horaEditText);
    }

    public void onSalir(View v) {
        Log.d(tag,"onSalir");
        finish();
    }

    public void onAgregar(View v) {
        Log.d(tag,"onAgregar");
        TareaModel model = new TareaModel();
        model.setTitulo(tituloEditText.getText().toString());
        model.setDescripcion(descripcionEditText.getText().toString());
        model.setHora(horaEditText.getText().toString());
        //Se podrían compartir la información entre distintas aplicaciones. En este caso no
        //Ete sharpreferences son solamente mías
        //TODO pasar llaves a un XML y de ahí transformarlas a constantes
        SharedPreferences preferences = getSharedPreferences("tareas", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("titulo",model.getTitulo());
        editor.putString("descripcion",model.getDescripcion());
        editor.putString("hora",model.getHora());
        editor.commit();
        finish();
    }


}
