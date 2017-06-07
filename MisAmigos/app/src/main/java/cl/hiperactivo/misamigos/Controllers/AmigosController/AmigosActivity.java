package cl.hiperactivo.misamigos.Controllers.AmigosController;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import cl.hiperactivo.misamigos.Controllers.ActualizarController.ActualizarActivity;
import cl.hiperactivo.misamigos.Controllers.AgregarController.AgregarActivity;
import cl.hiperactivo.misamigos.DAO.AmigosDAO;
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
        Log.d(tag, "cargarAmigos");

        AmigosOpenHelper amigosOpenHelper = new AmigosOpenHelper(getApplicationContext());
        //amigos = new ArrayList<AmigoModel>();
        amigos = amigosOpenHelper.obtenerAmigos();

        final AmigosAdapter adapter = new AmigosAdapter(this, R.layout.layout_amigo, amigos);
        ListView amigosListView = (ListView) findViewById(R.id.amigosListView);

        //Cargar amigos desde el JSON en INternet
        /*
        AmigosDAO dao = new AmigosDAO(getApplicationContext(),amigosListView,amigosOpenHelper);
        dao.ejecutar();
        */
        amigosListView.setAdapter(adapter);

        amigosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Log.d(tag, "onItemClick " + position);
                AmigoModel amigo = (AmigoModel) amigos.get(position);
                Log.d(tag, amigo.toString());
                SharedPreferences sp = getSharedPreferences(getString(R.string.sp),MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt(getString(R.string.idamigo),amigo.getId());
                editor.commit();

                Intent intent = new Intent(getApplicationContext(), ActualizarActivity.class);
                startActivity(intent);
            }
        });

    }

}
