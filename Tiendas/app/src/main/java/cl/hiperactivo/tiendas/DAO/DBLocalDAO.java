package cl.hiperactivo.tiendas.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import cl.hiperactivo.tiendas.Model.TiendaModel;

/**
 * Created by hernanBeiza on 6/10/17.
 */

public class DBLocalDAO extends SQLiteOpenHelper {

    private static final String tag = "DBLocalDAO";
    static public final String DATABASE_NAME ="tiendasdb";
    static public final int DATABASE_VERSION = 1;

    public DBLocalDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.d(tag,"onOpen");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(tag,"onCreate");
        db.execSQL("CREATE TABLE 'tienda' ('direccion' TEXT, 'nombre' TEXT,'id' INTEGER UNIQUE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(tag,"onUpgrade");

        if (oldVersion!=newVersion){
            Log.d(tag,"Nueva versión. Crear nuevamente");
            //db.execSQL("DROP TABLE amigos");
            //Log.d(tag,"DROP TABLE");
            this.onCreate(db);
        }

    }

    /***
     * Guarda una tienda en la db local
     * @param model de tipo TiendaModel
     * @return true en caso de que guarde la tienda. false en caso contrario, por ejemplo que ya exista
     */
    public boolean guardarTienda(TiendaModel model){
        if(obtenerTienda(model)==null){
            Log.d(tag,"No existe tienda: " + String.valueOf(model.getId()));
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("id",model.getId());
            values.put("nombre",model.getNombre());
            values.put("direccion",model.getDireccion());
            db.insert("tienda",null,values);
            db.close();
            return true;
        }
        return false;
    }

    /***
     * Edita una tienda en la db local
     * @param model
     * @return
     */
    public boolean editarTienda(TiendaModel model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id",model.getId());
        values.put("nombre",model.getNombre());
        values.put("direccion",model.getDireccion());
        String[] whereString = {String.valueOf(model.getId())};
        db.update("tienda",values,"id=?",whereString);
        db.close();
        return true;
    }

    public ArrayList<TiendaModel> obtenerTiendas(){
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<TiendaModel> tiendas = new ArrayList<TiendaModel>();
        String[] columnas = {"id","nombre","direccion"};
        Cursor c = db.query("tienda",columnas,null,null,null,null,null);

        if(c!=null){
            if(c.moveToFirst()){
                Log.d(tag,"¡Datos encontrados!");
                do {
                    TiendaModel model = new TiendaModel(c.getInt(0),c.getString(1),c.getString(2));
                    tiendas.add(model);
                } while (c.moveToNext());
            } else {
                //no tengo datos en mi cursor
                Log.d(tag,"No tengo datos");
                tiendas = null;
            }
        } else {
            //Está vacío, no encotró la tabla,, etc
            Log.d(tag,"Error");
            tiendas = null;
        }
        return tiendas;
    }

    public TiendaModel obtenerTienda(TiendaModel model){
        SQLiteDatabase db = getReadableDatabase();
        String[] columnas = {"id","nombre","direccion"};
        String[] whereString = {String.valueOf(model.getId())};
        Cursor c = db.query("tienda", columnas, "id=?", whereString, null, null, null);
        if(c!=null){
            if(c.moveToFirst()){
                Log.d(tag,"¡Datos encontrados!");
                do {
                    TiendaModel tienda = new TiendaModel(c.getInt(0),c.getString(1),c.getString(2));
                    return tienda;
                } while (c.moveToNext());
            } else {
                Log.d(tag,"No tengo datos");
                return null;
            }
        } else {
            Log.d(tag,"Error");
            return null;
        }
    }

    public boolean eliminarTienda(TiendaModel model){
        return false;
    }

    public boolean eliminarTiendas(){
        return false;
    }



}
