package cl.hiperactivo.misamigos.Controllers.ActualizarController;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import cl.hiperactivo.misamigos.DAO.AmigosOpenHelper;
import cl.hiperactivo.misamigos.Modelo.AmigoModel;
import cl.hiperactivo.misamigos.R;

public class ActualizarActivity extends AppCompatActivity {

    private static String tag ="ActualizarActivity";

    private EditText nombreEditText,telefonoEditText, cumpleanosEditText;

    private AmigosOpenHelper amigosOpenHelper;

    private AmigoModel amigoModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        amigosOpenHelper = new AmigosOpenHelper(this);

        nombreEditText = (EditText) findViewById(R.id.amigoEditText);
        telefonoEditText = (EditText) findViewById(R.id.telefonoEditText);
        cumpleanosEditText = (EditText) findViewById(R.id.cumpleanosEditText);

    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sp = getSharedPreferences(getString(R.string.sp), Context.MODE_PRIVATE);
        //Estamos manejando el id a nivel de controlador, debería ser en base de datos
        //Si no hay id, debo retornar 1, porque significa que es el primero
        int idamigo = sp.getInt(getString(R.string.idamigo),-1);
        //Log.d(tag,String.valueOf(idamigo));
        amigosOpenHelper = new AmigosOpenHelper(getApplicationContext());
        AmigoModel amigo = new AmigoModel(idamigo,null,null,null);

        this.amigoModel = amigosOpenHelper.obtenerAmigo(amigo);

        nombreEditText.setText(amigoModel.getNombre());
        telefonoEditText.setText(amigoModel.getTelefono());
        cumpleanosEditText.setText(amigoModel.getCumpleanos());
    }


    public void onActualizar(View v){

        String nombre = nombreEditText.getText().toString();
        String telefono = telefonoEditText.getText().toString();
        String cumpleanos = cumpleanosEditText.getText().toString();

        AmigoModel amigo = new AmigoModel(this.amigoModel.getId(),nombre,telefono,cumpleanos);
        this.amigosOpenHelper.cambiarAmigo(amigo);

        this.finish();
    }
}
