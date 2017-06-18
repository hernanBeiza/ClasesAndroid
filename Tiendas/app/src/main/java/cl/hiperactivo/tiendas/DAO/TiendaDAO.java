package cl.hiperactivo.tiendas.DAO;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import cl.hiperactivo.tiendas.Controllers.Principal.TiendasAdapter;
import cl.hiperactivo.tiendas.Model.TiendaModel;
import cl.hiperactivo.tiendas.R;
import cl.hiperactivo.tiendas.libs.Utilidades;

/**
 * Created by hernanBeiza on 6/10/17.
 */

public class TiendaDAO {

    private static final String tag = "TiendaDAO";

    private static final String serviceURI = "http://api-car.azurewebsites.net/stores/CL";

    private Context contexto;

    private ListView tiendasListView;


    public TiendaDAO() { }

    public void obtenerTiendas(Context contexto, ListView tiendasListView) {
        Log.d(tag,"obtenerTiendas");

        this.contexto = contexto;
        this.tiendasListView = tiendasListView;

        //Internet
        if(Utilidades.isConnected(contexto)){
            Log.d(tag,"¡Mirah mirah! Tengo internet :)");

            new CalllService().execute(serviceURI,"CL","-33","-77","100");
            //url,country,lat,lon,distancia

        } else {
            //Local
            Log.d(tag,"No tengo internet :(");
            DBLocalDAO dao = new DBLocalDAO(contexto);
            ArrayList<TiendaModel> tiendasList = new ArrayList<TiendaModel>();
            tiendasList = dao.obtenerTiendas();
            TiendasAdapter adapter = new TiendasAdapter(contexto, R.layout.tienda_layout, tiendasList);
            tiendasListView.setAdapter(adapter);
        }
    }

    // Esta clase crea una tarea sincrónica. Así el programa principal no se pega (LA UI)
    private class CalllService extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String nombreServicio = params[0];
            Log.d(tag, nombreServicio);
            StringBuilder ruta = new StringBuilder();
            ruta.append(nombreServicio);

            StringBuilder resultString = new StringBuilder();
            try {
                URL urlSevice = new URL(serviceURI);
                HttpURLConnection conexion = (HttpURLConnection) urlSevice.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    resultString.append(line);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            //Le pasa este string a onPostExecute
            return resultString.toString();
        }

        //Luego de obtener los datos
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ArrayList <TiendaModel> tiendasList = new ArrayList<TiendaModel>();
            try{
                JSONObject jsonResponse = new JSONObject(s);
                JSONArray tiendas = jsonResponse.getJSONArray("items");
                //Log.d(tag, tiendas.toString());
                DBLocalDAO dao = new DBLocalDAO(contexto);
                for (int i = 0;i<tiendas.length();i++){
                    JSONObject item = tiendas.getJSONObject(i);
                    //Log.d(tag,item.toString());
                    TiendaModel tienda = new TiendaModel(item.getInt("id"),item.getString("name"),item.getString("address"));
                    //Log.d(tag,tienda.toString());
                    tiendasList.add(tienda);
                    if(!dao.guardarTienda(tienda)){
                        //Log.d(tag,"Tienda ya existe");
                        //Actualizar en caso necesario
                        dao.editarTienda(tienda);
                    }
                }
                //Log.d(tag,tiendasList.toString());
                TiendasAdapter adapter = new TiendasAdapter(contexto,R.layout.tienda_layout,tiendasList);
                tiendasListView.setAdapter(adapter);
            } catch (JSONException e){
                e.printStackTrace();
            }
        }

    }

}
