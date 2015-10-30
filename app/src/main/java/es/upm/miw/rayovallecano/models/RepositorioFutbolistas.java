package es.upm.miw.rayovallecano.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static es.upm.miw.rayovallecano.models.FutbolistaContract.tablaFutbolista;

public class RepositorioFutbolistas extends SQLiteOpenHelper {

    private static final String DATABASE_FILENAME = "futbolistas.db";

    private static final int DATABASE_VERSION = 1;

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     */
    public RepositorioFutbolistas(Context context) {
        super(context, DATABASE_FILENAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sentenciaSQL = "CREATE TABLE " + tablaFutbolista.TABLE_NAME + "("
                + tablaFutbolista.COL_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + tablaFutbolista.COL_NAME_NOMBRE + " TEXT, "
                + tablaFutbolista.COL_NAME_DORSAL + " INTEGER, "
                + tablaFutbolista.COL_NAME_LESIONADO + " INTEGER, "
                + tablaFutbolista.COL_NAME_EQUIPO + " TEXT, "
                + tablaFutbolista.COL_NAME_URL + " TEXT)";
        db.execSQL(sentenciaSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sentenciaSQL = "DROP TABLE IF EXISTS " + tablaFutbolista.TABLE_NAME;
        db.execSQL(sentenciaSQL);
        onCreate(db);
    }

    public long add(Futbolista futbolista) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        // valores.put(tablaFutbolista.COL_NAME_ID, futbolista.get_id());
        valores.put(tablaFutbolista.COL_NAME_NOMBRE, futbolista.get_nombre());
        valores.put(tablaFutbolista.COL_NAME_DORSAL, futbolista.get_dorsal());
        valores.put(tablaFutbolista.COL_NAME_LESIONADO, futbolista.is_lesionado());
        valores.put(tablaFutbolista.COL_NAME_EQUIPO, futbolista.get_equipo());
        valores.put(tablaFutbolista.COL_NAME_URL, futbolista.get_url_imagen());

        return db.insert(tablaFutbolista.TABLE_NAME, null, valores);
    }

    /**
     * Recupera todos los pilotos de la tabla ordenados por ID
     * @see #getAll(String , boolean)
     * @return Array de pilotos ordenados por ID
     */
    public ArrayList<Futbolista> getAll() {
        return getAll(tablaFutbolista.COL_NAME_ID, true);
    }

    /**
     * Recupera todos los pilotos de la tabla ordenados por la columna proporcionada
     * @return Array de pilotos ordenados
     */
    public ArrayList<Futbolista> getAll(String columna, boolean ordenAscendente) {
        ArrayList<Futbolista> resultado = new ArrayList<>();
        String consultaSQL = "SELECT * FROM " + tablaFutbolista.TABLE_NAME
                + " ORDER BY " + columna + (ordenAscendente ? " ASC" : " DESC");

        // TO DO abrir bd lectura
        // Accedo a la DB en modo lectura
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consultaSQL, null);

        // TO DO recorrer cursor asignando resultados
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                Futbolista futbolista = new Futbolista(
                        cursor.getInt(cursor.getColumnIndex(tablaFutbolista.COL_NAME_ID)),
                        cursor.getString(cursor.getColumnIndex(tablaFutbolista.COL_NAME_NOMBRE)),
                        cursor.getInt(cursor.getColumnIndex(tablaFutbolista.COL_NAME_DORSAL)),
                        cursor.getInt(cursor.getColumnIndex(tablaFutbolista.COL_NAME_LESIONADO)) != 0,
                        cursor.getString(cursor.getColumnIndex(tablaFutbolista.COL_NAME_EQUIPO)),
                        cursor.getString(cursor.getColumnIndex(tablaFutbolista.COL_NAME_URL))
                );
                resultado.add(futbolista);
                cursor.moveToNext();
            }
            cursor.close();
        }

        return resultado;
    }

    /**
     * Devuelve el número de futbolistas de la tabla
     * @return Número de futbolistas
     */
    public long count() {
        String consultaSQL = "SELECT COUNT(*) FROM " + tablaFutbolista.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(consultaSQL, null);
        cursor.moveToFirst();
        long numero = cursor.getLong(0);
        cursor.close();

        return numero;
    }

    /**
     * Elimina todos los futbolistas
     * @return long Número de filas eliminadas
     */
    public long deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tablaFutbolista.TABLE_NAME, "1", null);
    }

    /**
     * Recupera un futbolista por ID
     * @param id Identificador del futbolista
     * @return  futbolistas
     */
    public Futbolista getPilotoByID(int id) {
        String consultaSQL = "SELECT * FROM " + tablaFutbolista.TABLE_NAME
                + " WHERE " + tablaFutbolista.COL_NAME_ID + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Futbolista futbolista = null;
        Cursor cursor = db.rawQuery(
                consultaSQL,
                new String[]{ String.valueOf(id) }
        );

        if (cursor.moveToFirst()) {
            futbolista = new Futbolista(
                    cursor.getInt(cursor.getColumnIndex(tablaFutbolista.COL_NAME_ID)),           // id
                    cursor.getString(cursor.getColumnIndex(tablaFutbolista.COL_NAME_NOMBRE)),    // nombre
                    cursor.getInt(cursor.getColumnIndex(tablaFutbolista.COL_NAME_DORSAL)),       // dorsal
                    cursor.getInt(cursor.getColumnIndex(tablaFutbolista.COL_NAME_LESIONADO)) != 0,  // lesionado
                    cursor.getString(cursor.getColumnIndex(tablaFutbolista.COL_NAME_EQUIPO)),    // equipo
                    cursor.getString(cursor.getColumnIndex(tablaFutbolista.COL_NAME_URL))        // imagen_url
            );
            cursor.close();
        }

        return futbolista;
    }

}














