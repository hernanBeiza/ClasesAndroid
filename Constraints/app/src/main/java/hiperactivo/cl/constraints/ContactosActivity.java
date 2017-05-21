package hiperactivo.cl.constraints;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ContactosActivity extends AppCompatActivity {
    private static final String tag = "ContactosActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
        Log.d(tag,"onCreate");

        //Llenar la lista
        // Referencia a la lista
        ListView listaContactos = (ListView)findViewById(R.id.contactosListView);
        // Lista de contenidos
        List<String> contactos = new ArrayList<String>();
        contactos.add("Hernán");
        contactos.add("Domingo");
        contactos.add("Andrés");
        contactos.add("Mario");
        contactos.add("Graciela");
        contactos.add("Gioconda");

        //Cambiar a adaptaer
        ContactosListAdapter adapter = new ContactosListAdapter(getApplicationContext(),R.layout.layout_lista,contactos);
        // El adaptador va a contener los contenidos que yo queiro pintar
        //Una vez que se tiene el adaptador, se le dice a la lista, listaContactos
        listaContactos.setAdapter(adapter);
    }
}
