package cibertec.edu.pe.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import cibertec.edu.pe.model.Persona;

public class PersonaDAO {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public PersonaDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null && database.isOpen()) {
            database.close();
        }
    }

    public long insertPersona(Persona persona) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NOMBRE, persona.getNombreCompleto());
        values.put(DatabaseHelper.COLUMN_DOCUMENTO, persona.getDocumentoIdentidad());
        values.put(DatabaseHelper.COLUMN_TELEFONO, persona.getTelefono());
        values.put(DatabaseHelper.COLUMN_DIRECCION, persona.getDireccion());
        values.put(DatabaseHelper.COLUMN_DISTRITO, persona.getDistrito());
        values.put(DatabaseHelper.COLUMN_ESTADO_CIVIL, persona.getEstadoCivil());
        values.put(DatabaseHelper.COLUMN_GENERO, persona.getGenero());
        return database.insert(DatabaseHelper.TABLE_PERSONAS, null, values);
    }

    public long updatePersona(Persona persona) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NOMBRE, persona.getNombreCompleto());
        values.put(DatabaseHelper.COLUMN_DOCUMENTO, persona.getDocumentoIdentidad());
        values.put(DatabaseHelper.COLUMN_TELEFONO, persona.getTelefono());
        values.put(DatabaseHelper.COLUMN_DIRECCION, persona.getDireccion());
        values.put(DatabaseHelper.COLUMN_DISTRITO, persona.getDistrito());
        values.put(DatabaseHelper.COLUMN_ESTADO_CIVIL, persona.getEstadoCivil());
        values.put(DatabaseHelper.COLUMN_GENERO, persona.getGenero());
        return database.update(DatabaseHelper.TABLE_PERSONAS, values,
                DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(persona.getId())});
    }

    public boolean existeDocumento(String documento) {
        Cursor cursor = database.query(DatabaseHelper.TABLE_PERSONAS,
                new String[]{DatabaseHelper.COLUMN_ID},
                DatabaseHelper.COLUMN_DOCUMENTO + "=?",
                new String[]{documento}, null, null, null);
        boolean existe = cursor.getCount() > 0;
        cursor.close();
        return existe;
    }

    public List<Persona> getAllPersonas() {
        List<Persona> personas = new ArrayList<>();
        Cursor cursor = database.query(DatabaseHelper.TABLE_PERSONAS,
                null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Persona persona = cursorToPersona(cursor);
                personas.add(persona);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return personas;
    }

    public List<Persona> buscarPersonas(String busqueda) {
        List<Persona> personas = new ArrayList<>();
        String selection = DatabaseHelper.COLUMN_NOMBRE + " LIKE ? OR " +
                DatabaseHelper.COLUMN_DOCUMENTO + " LIKE ?";
        String[] selectionArgs = {"%" + busqueda + "%", "%" + busqueda + "%"};
        Cursor cursor = database.query(DatabaseHelper.TABLE_PERSONAS,
                null, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Persona persona = cursorToPersona(cursor);
                personas.add(persona);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return personas;
    }

    public void deletePersona(Persona persona) {
        database.delete(DatabaseHelper.TABLE_PERSONAS,
                DatabaseHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(persona.getId())});
    }

    public Persona getPersonaById(int id) {
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_PERSONAS,
                null,
                DatabaseHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null
        );
        Persona persona = null;
        if (cursor.moveToFirst()) {
            persona = cursorToPersona(cursor);
        }
        cursor.close();
        return persona;
    }

    public Persona cursorToPersona(Cursor cursor) {
        Persona persona = new Persona();
        persona.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID)));
        persona.setNombreCompleto(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NOMBRE)));
        persona.setDocumentoIdentidad(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DOCUMENTO)));
        persona.setTelefono(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_TELEFONO)));
        persona.setDireccion(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DIRECCION)));
        persona.setDistrito(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DISTRITO)));
        persona.setEstadoCivil(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ESTADO_CIVIL)));
        persona.setGenero(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_GENERO)));
        return persona;
    }
    public String getDocumentoById(int id) {
        Cursor cursor = database.query(
                DatabaseHelper.TABLE_PERSONAS,
                new String[]{DatabaseHelper.COLUMN_DOCUMENTO},
                DatabaseHelper.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null
        );
        String documento = "";
        if (cursor.moveToFirst()) {
            documento = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_DOCUMENTO));
        }
        cursor.close();
        return documento;
    }

}