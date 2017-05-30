package cl.hiperactivo.misamigos.Controllers.AgregarController;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import cl.hiperactivo.misamigos.DAO.AmigosOpenHelper;
import cl.hiperactivo.misamigos.Modelo.AmigoModel;
import cl.hiperactivo.misamigos.R;

public class AgregarActivity extends AppCompatActivity {

    private static String tag ="AgregarActivity";

    private EditText nombreEditText,telefonoEditText, cumpleanosEditText;

    private AmigosOpenHelper amigosOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        amigosOpenHelper = new AmigosOpenHelper(this);

        nombreEditText = (EditText) findViewById(R.id.amigoEditText);
        telefonoEditText = (EditText) findViewById(R.id.telefonoEditText);
        cumpleanosEditText = (EditText) findViewById(R.id.cumpleanosEditText);
    }

    public void onGuardar(View v){
        SharedPreferences sp = getSharedPreferences("amigos", Context.MODE_PRIVATE);
        //Estamos manejando el id a nivel de controlador, debería ser en base de datos
        //Si no hay id, debo retornar 1, porque significa que es el primero
        int id = sp.getInt("nextId",1);

        String nombre = nombreEditText.getText().toString();
        String telefono = telefonoEditText.getText().toString();
        String cumpleanos = cumpleanosEditText.getText().toString();

        AmigoModel amigo = new AmigoModel(id,nombre,telefono,cumpleanos);
        amigosOpenHelper.agregarAmigo(amigo);

        Log.d(tag,amigosOpenHelper.obtenerAmigos().toString());
        SharedPreferences.Editor ed = sp.edit();
        ed.putInt("nextId",id++);
        ed.commit();

        this.finish();
    }
}
