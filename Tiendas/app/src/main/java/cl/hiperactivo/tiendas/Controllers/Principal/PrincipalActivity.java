package cl.hiperactivo.tiendas.Controllers.Principal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import cl.hiperactivo.tiendas.DAO.TiendaDAO;
import cl.hiperactivo.tiendas.R;

public class PrincipalActivity extends AppCompatActivity {

    private static final String tag = "PrincipalActivity";
    private ListView tiendasListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        cargarTiendas();
    }

    private void cargarTiendas(){
        Log.d(tag,"cargarTiendas");
        tiendasListView = (ListView) findViewById(R.id.tiendasListView);

        TiendaDAO dao = new TiendaDAO();
        dao.obtenerTiendas(getApplicationContext(),tiendasListView);
    }

    public void onActualizar(View v){
        Log.d(tag,"onActualizar");
    }
}
