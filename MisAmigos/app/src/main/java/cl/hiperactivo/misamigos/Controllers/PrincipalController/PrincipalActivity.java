package cl.hiperactivo.misamigos.Controllers.PrincipalController;

import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cl.hiperactivo.misamigos.Controllers.AmigosController.AmigosActivity;
import cl.hiperactivo.misamigos.R;

public class PrincipalActivity extends AppCompatActivity {

    private static final String tag ="PrincipalActivity";

    private EditText nombreUsuarioEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        nombreUsuarioEditText = (EditText) findViewById(R.id.nombreUsuarioEditText);
    }

    public void onIngresar(View v){
        Log.d(tag,"onIngresar");

        String nombre = nombreUsuarioEditText.getText().toString();

        if(nombre!=null && !nombre.equals("")){
            SharedPreferences sp = getSharedPreferences("amigos", ContextWrapper.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("nombre",nombre);
            editor.commit();

            Intent intent = new Intent(this,AmigosActivity.class);
            startActivity(intent);

        } else {
            Log.d(tag,"Error");
            //alerta abajo, barra negra
            Toast.makeText(this,"Debes ingresar un nombre",Toast.LENGTH_SHORT);
        }

    }
}
