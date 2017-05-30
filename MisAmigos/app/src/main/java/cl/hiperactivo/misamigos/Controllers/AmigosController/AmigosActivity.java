package cl.hiperactivo.misamigos.Controllers.AmigosController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import cl.hiperactivo.misamigos.Controllers.AgregarController.AgregarActivity;
import cl.hiperactivo.misamigos.DAO.AmigosOpenHelper;
import cl.hiperactivo.misamigos.Modelo.AmigoModel;
import cl.hiperactivo.misamigos.R;

public class AmigosActivity extends AppCompatActivity {
    private static final String tag ="AmigosActivity";

    private ArrayList<AmigoModel> amigos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigos);
        Log.d(tag,"onCreate");
        this.cargarAmigos();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.cargarAmigos();
    }

    public void onAgregar(View v){
        Log.d(tag,"onAgregar");

        Intent intent = new Intent(this,AgregarActivity.class);
        startActivity(intent);
    }

    private void cargarAmigos() {
        Log.d(tag,"cargarAmigos");
        AmigosOpenHelper amigosOpenHelper = new AmigosOpenHelper(getApplicationContext());
        //amigos = new ArrayList<AmigoModel>();
        amigos = amigosOpenHelper.obtenerAmigos();
        for (AmigoModel amigo: amigos){
            Log.d(tag,amigo.toString());
        }

        final AmigosAdapter adapter = new AmigosAdapter(this,R.layout.layout_amigo,amigos);
        ListView amigosListView = (ListView)findViewById(R.id.amigosListView);
        amigosListView.setAdapter(adapter);

    }

}
