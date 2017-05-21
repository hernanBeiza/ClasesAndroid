package hiperactivo.cl.vistacontrol;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import hiperactivo.cl.vistacontrol.Controllers.PersonaController;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
    }

    public void onGuardar(View view){
        Log.d("PrincipalActivity","onGuardar");
        EditText nombreEditText = (EditText) this.findViewById(R.id.nombreEditText);
        PersonaController controller = new PersonaController();
        AlertDialog.Builder dialog = new AlertDialog.Builder(PrincipalActivity.this);
        dialog.setCancelable(false);
        dialog.setTitle("Atención");
        String mensaje = "";
        dialog.setCancelable(true);
        if(controller.validar(nombreEditText.getText().toString())){
            if(controller.guardar(nombreEditText.getText().toString())){
                Log.d("PrincipalActivity","Guardar con éxito");
                mensaje = "Guardar con éxito.";
            } else {
                Log.d("PrincipalActivity","Guardar sin éxito");
                mensaje = "Guardar sin éxito.";
            }
        } else {
            Log.d("PrincipalActivity","Guardar sin éxito. No ingresaste un nombre");
            mensaje = "Guardar sin éxito. No ingresaste un nombre";
        }
        dialog.setMessage(mensaje);
        dialog.show();
    }
}
