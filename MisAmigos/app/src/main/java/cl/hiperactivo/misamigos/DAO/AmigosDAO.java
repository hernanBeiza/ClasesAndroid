package cl.hiperactivo.misamigos.DAO;

import android.app.Service;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cl.hiperactivo.misamigos.Controllers.AmigosController.AmigosAdapter;
import cl.hiperactivo.misamigos.Modelo.AmigoModel;
import cl.hiperactivo.misamigos.R;

/**
 * Created by hernanBeiza on 6/3/17.
 */

public class AmigosDAO {
    private static final String tag = "AmigosDAO";
    private static final String serviceURI = "http://catalogos1.cloudapp.net/temp/amigos.json";

    private Context contexto;
    private ListView listView;
    private AmigosOpenHelper amigosOpenHelper;

    // A través de una tarea paralela, vamos a llenar la lista. Así no hay un LAG
    // Se delega el pintado de la lista
    public AmigosDAO(Context contexto, ListView listView, AmigosOpenHelper amigosOpenHelper) {
        this.contexto = contexto;
        this.listView = listView;
        this.amigosOpenHelper = amigosOpenHelper;
    }

    // Esta clase crea una tarea sincrónica. Así el programa principal no se pega (LA UI)
    private class CalllService extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            String nombreServicio = params[0];
            Log.d(tag,nombreServicio);
            StringBuilder resultString = new StringBuilder();
            try {
                URL urlSevice = new URL(serviceURI);
                HttpURLConnection conexion = (HttpURLConnection) urlSevice.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                // Leer línea por línea
                String line;
                while ((line = reader.readLine()) != null) {
                    resultString.append(line);
                }
            } catch (MalformedURLException e){
                e.printStackTrace();
            } catch (Exception ex){
                ex.printStackTrace();
            }

            //Le pasa este string a onPostExecute
            return resultString.toString();
        }

        //Luego de obtener los datos
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayList <AmigoModel> amigosList = new ArrayList<AmigoModel>();
            try{
                JSONObject jsonResponse = new JSONObject(s);
                JSONArray amigos = jsonResponse.getJSONArray("amigos");
                for (int i = 0;i<amigos.length();i++){
                    JSONObject amigo = amigos.getJSONObject(i);
                    AmigoModel amigoModel = new AmigoModel(amigo.getInt("id"),
                            amigo.getString("nom"),
                            amigo.getString("tel"),
                            amigo.getString("cum"));
                    amigosList.add(amigoModel);
                    Log.d(tag,amigo.toString());
                    //Guardo el amigo que viene de internet en la base de datos local del teléfono
                    amigosOpenHelper.agregarAmigo(amigoModel);
                }
            } catch (JSONException e){
                e.printStackTrace();
            }

            AmigosAdapter adapter = new AmigosAdapter(contexto,R.layout.layout_amigo,amigosList);
            listView.setAdapter(adapter);
        }
    }

    public void ejecutar() {
        Log.d(tag,"ejecutar");
        new CalllService().execute(serviceURI);

    }

}
