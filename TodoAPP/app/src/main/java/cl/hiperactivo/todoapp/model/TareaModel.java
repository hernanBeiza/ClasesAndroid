package cl.hiperactivo.todoapp.model;

/**
 * Created by hernanBeiza on 5/20/17.
 */

public class TareaModel {

    private String titulo;
    private String descripcion;
    private String hora;

    public TareaModel() { }

    public TareaModel(String titulo, String descripcion, String hora) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.hora = hora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "TareaModel{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }

}
