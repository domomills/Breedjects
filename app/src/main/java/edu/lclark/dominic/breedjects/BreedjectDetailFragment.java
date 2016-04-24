package edu.lclark.dominic.breedjects;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Dominic on 4/24/2016.
 */
public class BreedjectDetailFragment extends Fragment {

    @Bind(R.id.fragment_breedject_detail_name)
    TextView mName;
    @Bind(R.id.fragment_breedject_detail_ability)
    TextView mAbility;
    @Bind(R.id.fragment_breedject_detail_build) TextView mBuild;
    @Bind(R.id.fragment_breedject_detail_nature) TextView mNature;
    @Bind(R.id.fragment_breedject_detail_item) TextView mItem;
    @Bind(R.id.fragment_breedject_detail_move1) TextView mMove1;
    @Bind(R.id.fragment_breedject_detail_move2) TextView mMove2;
    @Bind(R.id.fragment_breedject_detail_move3) TextView mMove3;
    @Bind(R.id.fragment_breedject_detail_move4) TextView mMove4;
    @Bind(R.id.fragment_breedject_detail_allevs) TextView mEvs;
//    @Bind(R.id.fragment_breedject_detail_hpev) TextView mHpEv;
//    @Bind(R.id.fragment_breedject_detail_attackev) TextView mAttackEv;
//    @Bind(R.id.fragment_breedject_detail_defenseev) TextView mDefenseEv;
//    @Bind(R.id.fragment_breedject_detail_spattackev) TextView mSpAttackEv;
//    @Bind(R.id.fragment_breedject_detail_spdefenseev) TextView mSpDefenseEv;
//    @Bind(R.id.fragment_breedject_detail_speedev) TextView mSpeedEv;
    @Bind(R.id.fragment_breedject_detail_delete_button)
    Button mDeleteButton;

    int mPosition;
    Pokemon mPokemon;

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_breedject_detail, container, false);
        ButterKnife.bind(this, rootView);
        mPosition = getArguments().getInt(BreedjectAdapter.ARGS_POSITION);
        mPokemon = (Pokemon)getArguments().get(BreedjectAdapter.ARGS_POKEMON);

        mName.setText(mPokemon.name.toUpperCase());
        mAbility.setText("Ability: " + mPokemon.ability);
        mBuild.setText("Build: " + mPokemon.build);
        mNature.setText("Nature: " + mPokemon.nature);
        mItem.setText("Item: " + mPokemon.item);
        mMove1.setText(mPokemon.move1);
        mMove2.setText(mPokemon.move2);
        mMove3.setText(mPokemon.move3);
        mMove4.setText(mPokemon.move4);
        mEvs.setText(mPokemon.hpEv + "/" + mPokemon.attackEv + "/" + mPokemon.defenseEv + "/" + mPokemon.spattackEv + "/" + mPokemon.spdefenceEv + "/" + mPokemon.speedEv);
//        mHpEv.setText(mPokemon.hpEv+"");
//        mAttackEv.setText(mPokemon.attackEv+"");
//        mDefenseEv.setText(mPokemon.defenseEv+"");
//        mSpAttackEv.setText(mPokemon.spattackEv+"");
//        mSpDefenseEv.setText(mPokemon.spdefenceEv+"");
//        mSpeedEv.setText(mPokemon.speedEv+"");

        return rootView;
    }

    @OnClick(R.id.fragment_breedject_detail_delete_button)
    public void onDeleteButtonClicked(){
        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.mDb.delete(Pokemon.PokemonEntry.TABLE_NAME,
                Pokemon.PokemonEntry.COLUMN_NAME_NAME + " = ? AND " + Pokemon.PokemonEntry.COLUMN_NAME_BUILD + " = ?", new String[] {mPokemon.name, mPokemon.build});
        ((BreedjectAdapter)mainActivity.mRecyclerView.getAdapter()).deletePokemon(mPosition);
        mainActivity.mRecyclerView.getAdapter().notifyItemRemoved(mPosition);
        mainActivity.onBackPressed();
    }
}
