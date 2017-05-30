package cl.hiperactivo.misamigos.Modelo;

/**
 * Created by hernanBeiza on 5/27/17.
 */

public class AmigoModel {

    private int id;
    private String nombre;
    private String telefono;
    private String cumpleanos;

    public AmigoModel(int id, String nombre, String telefono, String cumpleanos) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.cumpleanos = cumpleanos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCumpleanos() {
        return cumpleanos;
    }

    public void setCumple(String cumple) {
        this.cumpleanos = cumple;
    }

    @Override
    public String toString() {
        return "AmigoModel{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", cumpleanos='" + cumpleanos + '\'' +
                '}';
    }
}
