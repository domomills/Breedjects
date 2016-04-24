package edu.lclark.dominic.breedjects;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase mDb;
    PokemonSQLiteOpenHelper mPokemonSQLiteOpenHelper;
    public final String TAG = this.getClass().getSimpleName();

    @Bind(R.id.activity_main_fab)
    FloatingActionButton mFab;


    RecyclerView mRecyclerView;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mFab.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.activity_main_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new BreedjectAdapter(this));
        mFab.setImageResource(android.R.drawable.ic_menu_add);

        //get pokemon
        mPokemonSQLiteOpenHelper = new PokemonSQLiteOpenHelper(this);
        mDb = mPokemonSQLiteOpenHelper.getReadableDatabase();
        Cursor cursor = mDb.rawQuery("SELECT * FROM " + Pokemon.PokemonEntry.TABLE_NAME, null);
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            Log.d(TAG, "adding pokemon");
            do {
                ((BreedjectAdapter) mRecyclerView.getAdapter()).addPokemon(new Pokemon(
                        cursor.getString(cursor.getColumnIndexOrThrow(Pokemon.PokemonEntry.COLUMN_NAME_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Pokemon.PokemonEntry.COLUMN_NAME_BUILD)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Pokemon.PokemonEntry.COLUMN_NAME_ABILITY)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Pokemon.PokemonEntry.COLUMN_NAME_NATURE)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Pokemon.PokemonEntry.COLUMN_NAME_ITEM)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Pokemon.PokemonEntry.COLUMN_NAME_MOVE1)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Pokemon.PokemonEntry.COLUMN_NAME_MOVE2)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Pokemon.PokemonEntry.COLUMN_NAME_MOVE3)),
                        cursor.getString(cursor.getColumnIndexOrThrow(Pokemon.PokemonEntry.COLUMN_NAME_MOVE4)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(Pokemon.PokemonEntry.COLUMN_NAME_HPEV)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(Pokemon.PokemonEntry.COLUMN_NAME_ATTTACKEV)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(Pokemon.PokemonEntry.COLUMN_NAME_DEFENSEEV)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(Pokemon.PokemonEntry.COLUMN_NAME_SPATTACKEV)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(Pokemon.PokemonEntry.COLUMN_NAME_SPDEFENSEEV)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(Pokemon.PokemonEntry.COLUMN_NAME_SPEEDEV))
                ));
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        Log.d(TAG,mRecyclerView.getAdapter().getItemCount()+"");
    }

    @OnClick(R.id.activity_main_fab) void onFabPressed(){
        mFab.hide();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.activity_main_coordinator, new AddPokemonFragment());
        ft.addToBackStack(null);
        ft.commit();
    }

}
