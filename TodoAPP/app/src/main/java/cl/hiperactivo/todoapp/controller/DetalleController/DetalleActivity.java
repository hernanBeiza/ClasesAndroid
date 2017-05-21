package cl.hiperactivo.todoapp.controller.DetalleController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import cl.hiperactivo.todoapp.R;

public class DetalleActivity extends AppCompatActivity {

    private static final String tag ="DetalleActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Log.d(tag, "onCreate");

        //Recibo la información pasada desde la vista anterior
        Intent intent = getIntent();

        String titulo = intent.getStringExtra("titulo");
        String descripcion = intent.getStringExtra("descripcion");
        String hora = intent.getStringExtra("hora");

        Log.d(tag, titulo);
        Log.d(tag, descripcion);
        Log.d(tag, hora);

        TextView tituloTextView = (TextView) findViewById(R.id.tituloTextView);
        tituloTextView.setText(titulo);
        TextView descripcionTextView = (TextView) findViewById(R.id.descripcionTextView);
        descripcionTextView.setText(descripcion);
        TextView horaTextView = (TextView) findViewById(R.id.horaTextView);
        horaTextView.setText(hora);
    }

    public void onVolver(View v) {
        finish();
    }

}
