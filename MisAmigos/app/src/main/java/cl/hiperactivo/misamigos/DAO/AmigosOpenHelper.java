package cl.hiperactivo.misamigos.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cl.hiperactivo.misamigos.Modelo.AmigoModel;

/**
 * Created by hernanBeiza on 5/27/17.
 */

public class AmigosOpenHelper extends SQLiteOpenHelper {

    private static final String tag = "AmigosOpenHelper";

    static public final String DATABASE_NAME ="amigosdb";
    static public final int DATABASE_VERSION = 1;

    //public AmigosOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

    /***
     * Dejar todo dentro de nuestra clase. Si quisiéramos parametrizar más nuestra clase, se puede ocupar un archivo XML
     y de ahí traer los datos
     * @param context Contexto de la aplicación
     */

    public AmigosOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //El constructor lo que devuleve internamente, es un objeto SQLDatabase
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE `amigos` ( `idamigo` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `nombre` TEXT NOT NULL, `telefono` TEXT, `cumpleanos` TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(tag,"onUpgrade();");
        if (oldVersion!=newVersion){
            db.execSQL("DROP TABLE amigos");
            Log.d(tag,"DROP TABLE");
            this.onCreate(db);
        }
    }

    /***
     * Crear un amigo en la base de datos
     * @param amigo AmigoModel
     */
    public void agregarAmigo(AmigoModel amigo) {
        //Obtenemos una database pero con permiso de escritura, para poder agregar dato
        SQLiteDatabase db = this.getWritableDatabase();
        //Objeto que permite guardar pares ordenados
        ContentValues values = new ContentValues();
        //values.put("idamigo",amigo.getId());
        values.put("nombre",amigo.getNombre());
        values.put("telefono",amigo.getTelefono());
        values.put("cumpleanos",amigo.getCumpleanos());
        //El insert se crea en base al ContentValues
        //Cualquier valor nulo, se pone como null
        //El insert se crea solo :)
        db.insert("amigos",null,values);
        db.close();
    }

    /***
     * Retorna la lista de amigos
     * @return List de AmigoModel
     */
    public ArrayList <AmigoModel> obtenerAmigos() {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<AmigoModel> amigos = new ArrayList<AmigoModel>();
        //Se puede ocupar algo similar a ContentValues
        //Con un arreglo de string, se puede decir a la db QUË DATOS sacaré de ella
        String[] columnas = {"idamigo","nombre","telefono","cumpleanos"};
        //Finalmente permite armar el query usando parámetros
        Cursor c = db.query("amigos",columnas,null,null,null,null,null);
        //Ojo tener en cuenta con la APIVersion. SQLite va cambiando según el APIVersion
        if(c!=null){
            //Si tengo datos
            if(c.moveToFirst()){
                Log.d(tag,"¡Datos encontrados!");
                do {
                    //debo reconocer las columnas
                    //nos permite trabajar con el tipo de dato directamente con el tipo que necesito
                    //no es necesario usar cast
                    AmigoModel amigo = new AmigoModel(c.getInt(0),c.getString(1),c.getString(2),c.getString(3));
                    amigos.add(amigo);
                } while (c.moveToNext());
            } else {
                //no tengo datos en mi cursor
                Log.d(tag,"No tengo datos");
                amigos = null;
            }
        } else {
            //Está vacío, no encotró la tabla,, etc
            Log.d(tag,"Error");
            amigos = null;
        }
        return amigos;
    }

    public void cambiarAmigo(AmigoModel amigo){
        Log.w(tag,"cambiarAmigo sin implementar");

    }

    public void borrarAmigo(AmigoModel amigo){
        Log.w(tag,"borrarAmigo sin implementar");
    }

}
