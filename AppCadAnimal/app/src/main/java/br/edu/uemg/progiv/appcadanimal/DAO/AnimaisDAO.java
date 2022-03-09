package br.edu.uemg.progiv.appcadanimal.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import br.edu.uemg.progiv.appcadanimal.Model.AnimaisModel;

public class AnimaisDAO extends SQLiteOpenHelper {

    private static final String DATABASE = "animais"; //nome do banco de dados
    private static final int VERSION = 1; //versão do banco de dados

    public String msmErro = "";

    public AnimaisDAO(Context context) {
        super(context, DATABASE, null, VERSION);
        msmErro = "";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE animais(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "nomeDono TEXT NOT NULL, " +
                "nomeAnimal TEXT NOT NULL, " +
                "idadeAnimal INTEGER NOT NULL DEFAULT 0);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            String sql = "DROP TABLE IF EXISTS animais";
            db.execSQL(sql);
        }
    }


    public boolean salvarAnimal(AnimaisModel animais) {
        try {
            ContentValues values = new ContentValues();
            values.put("nomeDono", animais.getNomeDono());
            values.put("nomeAnimal", animais.getNomeAnimal());
            values.put("idadeAnimal", animais.getIdadeAnimal());
            getWritableDatabase().insert("animais", null, values);
            return true;
        } catch (Exception ex) {
            msmErro = ex.getMessage();
            return false;
        }
    }


    public boolean alterarAnimal(AnimaisModel animais) {
        try {
            ContentValues values = new ContentValues();
            values.put("nomeDono", animais.getNomeDono());
            values.put("nomeAnimal", animais.getNomeAnimal());
            values.put("idadeAnimal", animais.getIdadeAnimal());

            String[] args = {animais.getId().toString()};
            getWritableDatabase().update("animais", values, "id=?", args);
            return true;
        } catch (Exception ex) {
            msmErro = ex.getMessage();
            return false;
        }
    }


    public boolean deletarAnimal(AnimaisModel animais) {
        try {
            String[] args = {animais.getId().toString()};
            getWritableDatabase().delete("animais", "id=?", args);
            return true;
        } catch (Exception ex) {
            msmErro = ex.getMessage();
            return false;
        }
    }


    public ArrayList<AnimaisModel> listaAnimais() {
        ArrayList<AnimaisModel> lstAnimais = new ArrayList<>();
        String[] colums = {"id", "nomeDono", "nomeAnimal", "idadeAnimal"};
        Cursor cursor = getWritableDatabase().query("animais", colums, null, null, null, null, null);
        while (cursor.moveToNext()) {
            AnimaisModel animaisModel = new AnimaisModel();
            animaisModel.setId(cursor.getLong(0));
            animaisModel.setNomeDono(cursor.getString(1));
            animaisModel.setNomeAnimal(cursor.getString(2));
            animaisModel.setIdadeAnimal(cursor.getInt(3));
            lstAnimais.add(animaisModel);
        }
        return lstAnimais;
    }

}
