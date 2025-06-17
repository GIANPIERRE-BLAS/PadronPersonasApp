
package cibertec.edu.pe.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "padron_personas.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PERSONAS = "personas";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMBRE = "nombre_completo";
    public static final String COLUMN_DOCUMENTO = "documento_identidad";
    public static final String COLUMN_TELEFONO = "telefono";
    public static final String COLUMN_DIRECCION = "direccion";
    public static final String COLUMN_DISTRITO = "distrito";
    public static final String COLUMN_ESTADO_CIVIL = "estado_civil";
    public static final String COLUMN_GENERO = "genero";

    private static final String CREATE_TABLE_PERSONAS = "CREATE TABLE " + TABLE_PERSONAS + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_NOMBRE + " TEXT NOT NULL,"
            + COLUMN_DOCUMENTO + " TEXT UNIQUE NOT NULL,"
            + COLUMN_TELEFONO + " TEXT NOT NULL,"
            + COLUMN_DIRECCION + " TEXT NOT NULL,"
            + COLUMN_DISTRITO + " TEXT,"
            + COLUMN_ESTADO_CIVIL + " TEXT,"
            + COLUMN_GENERO + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PERSONAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONAS);
        onCreate(db);
    }
}
