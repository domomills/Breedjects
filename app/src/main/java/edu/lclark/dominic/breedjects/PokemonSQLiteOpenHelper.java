package edu.lclark.dominic.breedjects;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dominic on 4/21/2016.
 */
public class PokemonSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Pokemon.db";
    private static final String COMMA_SEP = ",";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";

    public PokemonSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Pokemon.PokemonEntry.TABLE_NAME + " (" +
                Pokemon.PokemonEntry._ID + " INT PRIMARY KEY," +
                Pokemon.PokemonEntry.COLUMN_NAME_ABILITY + TEXT_TYPE + COMMA_SEP +
                Pokemon.PokemonEntry.COLUMN_NAME_BUILD + TEXT_TYPE + COMMA_SEP +
                Pokemon.PokemonEntry.COLUMN_NAME_ITEM + TEXT_TYPE + COMMA_SEP +
                Pokemon.PokemonEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                Pokemon.PokemonEntry.COLUMN_NAME_NATURE + TEXT_TYPE + COMMA_SEP +
                Pokemon.PokemonEntry.COLUMN_NAME_MOVE1 + TEXT_TYPE + COMMA_SEP +
                Pokemon.PokemonEntry.COLUMN_NAME_MOVE2 + TEXT_TYPE + COMMA_SEP +
                Pokemon.PokemonEntry.COLUMN_NAME_MOVE3 + TEXT_TYPE + COMMA_SEP +
                Pokemon.PokemonEntry.COLUMN_NAME_MOVE4 + TEXT_TYPE + COMMA_SEP +
                Pokemon.PokemonEntry.COLUMN_NAME_HPEV + INTEGER_TYPE + COMMA_SEP +
                Pokemon.PokemonEntry.COLUMN_NAME_ATTTACKEV + INTEGER_TYPE + COMMA_SEP +
                Pokemon.PokemonEntry.COLUMN_NAME_DEFENSEEV + INTEGER_TYPE + COMMA_SEP +
                Pokemon.PokemonEntry.COLUMN_NAME_SPATTACKEV + INTEGER_TYPE + COMMA_SEP +
                Pokemon.PokemonEntry.COLUMN_NAME_SPDEFENSEEV + INTEGER_TYPE + COMMA_SEP +
                Pokemon.PokemonEntry.COLUMN_NAME_SPEEDEV + INTEGER_TYPE + " )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Pokemon.PokemonEntry.TABLE_NAME);
        onCreate(db);
    }
}
