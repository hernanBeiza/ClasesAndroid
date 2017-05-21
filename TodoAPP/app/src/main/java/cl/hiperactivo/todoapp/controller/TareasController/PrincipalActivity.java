package cl.hiperactivo.todoapp.controller.TareasController;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import cl.hiperactivo.todoapp.R;
import cl.hiperactivo.todoapp.controller.AgregarController.AgregarActivity;
import cl.hiperactivo.todoapp.controller.DetalleController.DetalleActivity;
import cl.hiperactivo.todoapp.model.TareaModel;

public class PrincipalActivity extends AppCompatActivity {

    private ArrayList<TareaModel> tareasList;

    private static final String tag ="PrincipalActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        tareasList = new ArrayList<TareaModel>();
        TareaModel uno = new TareaModel("Acordar con amigos","Ponerse de acuerdo donde carretear","17:00");
        TareaModel dos = new TareaModel("Pedir permisos","Pedir permiso","17:30");
        TareaModel tres = new TareaModel("Buscar el lugar","Buscar un lugar para irse de parranda","19:00");
        tareasList.add(uno);
        tareasList.add(dos);
        tareasList.add(tres);
        this.cargar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.cargar();
    }

    public void cargar() {
        Log.d(tag,"cargar");
        // Primera Carga
        SharedPreferences sp = getSharedPreferences("tareas", Context.MODE_PRIVATE);
        String titulo = sp.getString("titulo",null);
        String descripcion = sp.getString("descripcion",null);
        String hora = sp.getString("hora",null);
        //Si no se encuentra, se asigna null a la variable
        if(titulo!=null){
            TareaModel tarea = new TareaModel();
            tarea.setTitulo(titulo);
            tarea.setDescripcion(descripcion);
            tarea.setHora(hora);
            tareasList.add(tarea);
            //Borrar todas las preferencias guardadas. Si no se empiezan a agregar una tras otra
            sp.edit().clear().commit();
        }

        final TareasAdapter adapter = new TareasAdapter(this,R.layout.layout_tarea,tareasList);
        ListView listaTareas = (ListView)findViewById(R.id.tareasListView);

        // Tap en un item
        // Ir al detalle
        listaTareas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Log.d(tag,"onItemClick "+position);
                TareaModel tarea = (TareaModel)tareasList.get(position);
                Log.d(tag,tarea.toString());
                //Ir a la vista detalle
                Intent detalleIntent = new Intent(getApplicationContext(), DetalleActivity.class);
                //Pasarle strings o datos la tarea
                detalleIntent.putExtra("titulo", tarea.getTitulo());
                detalleIntent.putExtra("descripcion", tarea.getDescripcion());
                detalleIntent.putExtra("hora", tarea.getHora());
                //Ir a la otra vista
                startActivity(detalleIntent);
                //Idea para borrar
                /*
                AlertDialog.Builder adb = new AlertDialog.Builder(PrincipalActivity.this);
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + position);
                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        tareasList.remove(positionToRemove);
                        adapter.notifyDataSetChanged();
                    }});
                adb.show();
                */
            }
        });
        // Tap Largo
        /*
        listaTareas.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long arg3) {
                AlertDialog.Builder adb = new AlertDialog.Builder(PrincipalActivity.this);
                adb.setTitle("Atención");
                TareaModel tarea = (TareaModel)tareasList.get(position);
                adb.setMessage("¿Quieres borrar " + tarea.getTitulo());
                final int positionToRemove = position;
                adb.setNegativeButton("Cancelar", null);
                adb.setPositiveButton("Borrar", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Eliminar
                        tareasList.remove(positionToRemove);
                        //Avisar al adaptador para que redibuje la lista
                        adapter.notifyDataSetChanged();
                    }});
                adb.show();

                return false;
            }
        });
        */

        // El adaptador va a contener los contenidos que yo quiero pintar
        // Una vez que se tiene el adaptador, se le dice a la lista, listaTareas, que pinte con tareasList
        listaTareas.setAdapter(adapter);
    }

    public void onSalir(View v) {
        Log.d(tag,"onSalir");
        finish();
    }

    public void onAgregar(View v) {
        Log.d(tag,"onAgregar");
        Intent intentar = new Intent(this,AgregarActivity.class);
        startActivity(intentar);
    }

}
