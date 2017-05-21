package hiperactivo.cl.vistacontrol.Controllers;

import android.util.Log;

import hiperactivo.cl.vistacontrol.DAOS.PersonaDAO;
import hiperactivo.cl.vistacontrol.Models.PersonaModel;

/**
 * Created by hernanBeiza on 5/5/17.
 */

public class PersonaController {
    private static final String tag ="PersonaController";
    public PersonaController() {

    }

    /***
     * Validación del dato ingresado
     * @param nombre del usuario
     * @return true si está ok. false en caso contrario
     */
    public boolean validar(String nombre) {
        Log.d(tag,"validar");
        boolean estado = false;
        if(nombre.length()>0){
            estado = true;
        }
        return estado;
    }

    /***
     * Guardamos el dao
     * @param nombre del usuario
     * @return true si se guarda correctamente. false ne caso contrario
     */
    public boolean guardar(String nombre) {
        Log.d(tag,"guardar");
        boolean estado = false;

        if(nombre.length()>0){
            PersonaModel model = new PersonaModel(nombre);
            PersonaDAO dao = new PersonaDAO();
            if (dao.guardar(model)) {
                estado = true;
            } else {
                estado = false;
            }
        }
        return estado;
    }

}
