package com.example.pizzadam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuarioDBHelper {

	private static final String NOMBRE_BASEDATOS = "UsuariosDB";
	private static final int VERSION_BASEDATOS = 1;

	private static final String TABLA_USUARIOS = "usuarios";
	private static final String KEY_ID = "id";
	private static final String KEY_NOMBRE = "nombre";
	private static final String KEY_CONTRASENA = "contrasena";

	private static final String CREAR_TABLA_USUARIOS =
			"CREATE TABLE " + TABLA_USUARIOS + "("
					+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ KEY_NOMBRE + " TEXT,"
					+ KEY_CONTRASENA + " TEXT"
					+ ")";


	private static final String TABLA_PIZZAS = "pizzas";
	private static final String KEY_ID_PIZZA = "id";
	private static final String KEY_NOMBRE_PIZZA = "nombre";
	private static final String KEY_PRECIO = "precio";
	private static final String KEY_INGREDIENTES = "ingredientes";
	private static final String KEY_TAMANO = "tamano";

	private static final String CREAR_TABLA_PIZZA =
			"CREATE TABLE " + TABLA_PIZZAS + "("
					+ KEY_ID_PIZZA + " INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ KEY_NOMBRE_PIZZA + " TEXT,"
					+ KEY_PRECIO + " INTEGER,"
					+ KEY_INGREDIENTES + " TEXT,"
					+ KEY_TAMANO + " TEXT"
					+ ")";
	

	private final Context context;
	private DatabaseHelper DBHelper;
	private SQLiteDatabase db;

	public UsuarioDBHelper(Context ctx) {
		this.context = ctx;
		DBHelper = new DatabaseHelper(context);
	}

	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, NOMBRE_BASEDATOS, null, VERSION_BASEDATOS);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			try {
				db.execSQL(CREAR_TABLA_USUARIOS);
				db.execSQL(CREAR_TABLA_PIZZA);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLA_USUARIOS);
			db.execSQL("DROP TABLE IF EXISTS " + TABLA_PIZZAS);
			onCreate(db);
		}
	}

	public UsuarioDBHelper abrir() throws SQLException {
		db = DBHelper.getWritableDatabase();
		return this;
	}

	public void cerrar() {
		DBHelper.close();
	}

	public long agregarUsuario(String nombre, String contrasena) {
		ContentValues valores = new ContentValues();
		valores.put(KEY_NOMBRE, nombre);
		valores.put(KEY_CONTRASENA, contrasena);
		return db.insert(TABLA_USUARIOS, null, valores);
	}

	public boolean borrarUsuario(long id) {
		return db.delete(TABLA_USUARIOS, KEY_ID + "=" + id, null) > 0;
	}

	public Cursor obtenerUsuarioPorCredenciales(String nombreUsuario, String contrasena) throws SQLException {
		String[] columns = {KEY_ID, KEY_NOMBRE, KEY_CONTRASENA};
		String selection = KEY_NOMBRE + "=? AND " + KEY_CONTRASENA + "=?";
		String[] selectionArgs = {nombreUsuario, contrasena};

		return db.query(TABLA_USUARIOS, columns, selection, selectionArgs, null, null, null);
	}

}
