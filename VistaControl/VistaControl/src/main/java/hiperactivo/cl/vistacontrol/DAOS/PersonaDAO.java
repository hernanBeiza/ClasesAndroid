package hiperactivo.cl.vistacontrol.DAOS;

import android.util.Log;

import hiperactivo.cl.vistacontrol.Models.PersonaModel;

/**
 * Created by hernanBeiza on 5/5/17.
 */

public class PersonaDAO extends DBDAO {

    public PersonaDAO() {}

    public boolean guardar(PersonaModel model){
        Log.d("PersonaDAO","guardar");

        boolean estado = false;
        if(super.conectar()){
            estado = true;
            //Guardar los datos en la DB acá
        } else {
            estado = false;
        }
        return estado;
    }

}
