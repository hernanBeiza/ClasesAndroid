package hiperactivo.cl.vistacontrol.Models;

/**
 * Created by hernanBeiza on 5/5/17.
 */

public class PersonaModel {

    private String nombre;

    public PersonaModel(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "PersonaModel{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
