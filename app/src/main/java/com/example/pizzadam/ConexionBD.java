package com.example.pizzadam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionBD extends SQLiteOpenHelper {

    private String crearTablaPersona(){
        String tabla = "create table persona(nombre varchar(50) not null, contrasena varchar(50) not null)";
        return tabla;
    }

    public ConexionBD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(crearTablaPersona());
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void abrir(){
        this.getWritableDatabase();
    }


    public void insertarPersona(String nombre, String contrasena){
        ContentValues valores = new ContentValues();
        valores.put("nombre",nombre);
        valores.put("contrasena",contrasena);
        this.getWritableDatabase().insert("persona",null,valores);
    }

    public Boolean obtenerUsuarioPorCredenciales(String usuario, String contrasena){
        Cursor cursor = null;
        cursor = this.getWritableDatabase().rawQuery("select * from persona where nombre='"+usuario+"' and contrasena='"+contrasena+"'",null);
        return cursor.getCount()>0;
    }

}
