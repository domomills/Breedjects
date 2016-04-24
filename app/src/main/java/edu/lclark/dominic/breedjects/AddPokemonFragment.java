package edu.lclark.dominic.breedjects;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Dominic on 4/22/2016.
 */
public class AddPokemonFragment extends Fragment {

    @Bind(R.id.fragment_add_pokemon_name) EditText mNameET;
    @Bind(R.id.fragment_add_pokemon_ability) EditText mAbilityET;
    @Bind(R.id.fragment_add_pokemon_build) EditText mBuildET;
    @Bind(R.id.fragment_add_pokemon_nature) EditText mNatureET;
    @Bind(R.id.fragment_add_pokemon_item) EditText mItemET;
    @Bind(R.id.fragment_add_pokemon_move1) EditText mMove1ET;
    @Bind(R.id.fragment_add_pokemon_move2) EditText mMove2ET;
    @Bind(R.id.fragment_add_pokemon_move3) EditText mMove3ET;
    @Bind(R.id.fragment_add_pokemon_move4) EditText mMove4ET;
    @Bind(R.id.fragment_add_pokemon_hpev) EditText mHpEvET;
    @Bind(R.id.fragment_add_pokemon_attackev) EditText mAttackEvET;
    @Bind(R.id.fragment_add_pokemon_defenseev) EditText mDefenseEvET;
    @Bind(R.id.fragment_add_pokemon_spattackev) EditText mSpAttackEvET;
    @Bind(R.id.fragment_add_pokemon_spdefenseev) EditText mSpDefenseEvET;
    @Bind(R.id.fragment_add_pokemon_speedev) EditText mSpeedEvET;
    @Bind(R.id.fragment_add_pokemon_confirm_button) Button mConfirmButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_pokemon, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.fragment_add_pokemon_confirm_button)
    void onAddPokemonClick(){
        ContentValues values = new ContentValues();
        Log.d("AddFragment", "Confirm button pressed");
        //add content values
        values.put(Pokemon.PokemonEntry.COLUMN_NAME_NAME, mNameET.getText().toString().toLowerCase());
        values.put(Pokemon.PokemonEntry.COLUMN_NAME_ABILITY, mAbilityET.getText().toString());
        values.put(Pokemon.PokemonEntry.COLUMN_NAME_BUILD, mBuildET.getText().toString());
        values.put(Pokemon.PokemonEntry.COLUMN_NAME_NATURE, mNatureET.getText().toString());
        values.put(Pokemon.PokemonEntry.COLUMN_NAME_ITEM, mItemET.getText().toString());
        values.put(Pokemon.PokemonEntry.COLUMN_NAME_MOVE1, mMove1ET.getText().toString());
        values.put(Pokemon.PokemonEntry.COLUMN_NAME_MOVE2, mMove2ET.getText().toString());
        values.put(Pokemon.PokemonEntry.COLUMN_NAME_MOVE3, mMove3ET.getText().toString());
        values.put(Pokemon.PokemonEntry.COLUMN_NAME_MOVE4, mMove4ET.getText().toString());
        values.put(Pokemon.PokemonEntry.COLUMN_NAME_HPEV, Integer.parseInt(mHpEvET.getText().toString()));
        values.put(Pokemon.PokemonEntry.COLUMN_NAME_ATTTACKEV, Integer.parseInt(mAttackEvET.getText().toString()));
        values.put(Pokemon.PokemonEntry.COLUMN_NAME_DEFENSEEV, Integer.parseInt(mDefenseEvET.getText().toString()));
        values.put(Pokemon.PokemonEntry.COLUMN_NAME_SPATTACKEV, Integer.parseInt(mSpAttackEvET.getText().toString()));
        values.put(Pokemon.PokemonEntry.COLUMN_NAME_SPDEFENSEEV, Integer.parseInt(mSpDefenseEvET.getText().toString()));
        values.put(Pokemon.PokemonEntry.COLUMN_NAME_SPEEDEV, Integer.parseInt(mSpeedEvET.getText().toString()));

        MainActivity mainActivity = ((MainActivity)getActivity());
        mainActivity.mDb.insert(Pokemon.PokemonEntry.TABLE_NAME, null, values);

        //add pokemon to array list
        ((BreedjectAdapter)mainActivity.mRecyclerView.getAdapter()).addPokemon(new Pokemon(
                values.getAsString(Pokemon.PokemonEntry.COLUMN_NAME_NAME),
                values.getAsString(Pokemon.PokemonEntry.COLUMN_NAME_BUILD),
                values.getAsString(Pokemon.PokemonEntry.COLUMN_NAME_ABILITY),
                values.getAsString(Pokemon.PokemonEntry.COLUMN_NAME_NATURE),
                values.getAsString(Pokemon.PokemonEntry.COLUMN_NAME_ITEM),
                values.getAsString(Pokemon.PokemonEntry.COLUMN_NAME_MOVE1),
                values.getAsString(Pokemon.PokemonEntry.COLUMN_NAME_MOVE2),
                values.getAsString(Pokemon.PokemonEntry.COLUMN_NAME_MOVE3),
                values.getAsString(Pokemon.PokemonEntry.COLUMN_NAME_MOVE4),
                values.getAsInteger(Pokemon.PokemonEntry.COLUMN_NAME_HPEV),
                values.getAsInteger(Pokemon.PokemonEntry.COLUMN_NAME_ATTTACKEV),
                values.getAsInteger(Pokemon.PokemonEntry.COLUMN_NAME_DEFENSEEV),
                values.getAsInteger(Pokemon.PokemonEntry.COLUMN_NAME_SPATTACKEV),
                values.getAsInteger(Pokemon.PokemonEntry.COLUMN_NAME_SPDEFENSEEV),
                values.getAsInteger(Pokemon.PokemonEntry.COLUMN_NAME_SPEEDEV)
                ));
        mainActivity.mFab.show();
        getActivity().onBackPressed();
    }
}
