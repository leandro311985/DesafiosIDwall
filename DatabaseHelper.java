package com.cotacao.leandro.testeapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper  extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, DatabaseOptions.DB_NAME, null, DatabaseOptions.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Criar tabela
        db.execSQL(DatabaseOptions.CREATE_USERS_TABLE_);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Soltar tabela mais velha, se existisse
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseOptions.USERS_TABLE);

        //Crie tabelas novamente
        onCreate(db);
    }

    public Usuario queryUser(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        Usuario user = null;

        Cursor cursor = db.query(DatabaseOptions.USERS_TABLE, new String[]{DatabaseOptions.ID,
                        DatabaseOptions.EMAIL, DatabaseOptions.PASSWORD}, DatabaseOptions.EMAIL + "=? and " + DatabaseOptions.PASSWORD + "=?",
                new String[]{email, password}, null, null, null, "1");
        if (cursor != null)
            cursor.moveToFirst();
        if (cursor != null && cursor.getCount() > 0) {
            user = new Usuario(cursor.getString(1), cursor.getString(2));
        }
        // return usuario
        return user;
    }

    public void addUser(Usuario user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseOptions.EMAIL, user.getEmail());
        values.put(DatabaseOptions.PASSWORD, user.getSenha());

        // Inserindo linha
        db.insert(DatabaseOptions.USERS_TABLE, null, values);
        db.close(); // Fechando conexão com o banco de dados
    }

}
