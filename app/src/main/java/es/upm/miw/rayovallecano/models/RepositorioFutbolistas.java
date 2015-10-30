package es.upm.miw.rayovallecano.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

}














