package hiperactivo.cl.constraints;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        CheckBox amorCheckBox = (CheckBox)findViewById(R.id.amorCheckBox);
        if(amorCheckBox.isChecked()){
            Log.d("amor", "seleccionado");
        }

        RadioButton hombreRadioButton = (RadioButton)findViewById(R.id.hombreRadioButton);
        RadioButton mujerRadioButton = (RadioButton)findViewById(R.id.mujerRadioButton);

        if(hombreRadioButton.isChecked()){

        }

    }

    //Pasar de una pantalla a otra, dentro del stack de Android
    public void onSiguienteEvent(View v) {
        //Los intent, son las intenciones de hacer alog
        // Intención de abrir una actividad diferente a la actual
        // Se le pasa la referencia a al clase
        Intent intencion = new Intent(this,ContactosActivity.class);
        startActivity(intencion);
    }

    //Terminé la ejecución, vuelvo al main activity
    public void onPrincipalEvent(View v){
        finish();
    }
}
