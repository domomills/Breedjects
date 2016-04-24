package edu.lclark.dominic.breedjects;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by Dominic on 4/21/2016.
 */
public class BreedjectAdapter extends RecyclerView.Adapter<BreedjectAdapter.BreedjectViewHolder>{

    public static final String ARGS_POKEMON = "pokemon";
    public static final String ARGS_POSITION = "position";

    private ArrayList<Pokemon> mPokemon;
    private Context mParent;

    public BreedjectAdapter(Context mParent) {
        this.mParent = mParent;
        mPokemon = new ArrayList<>();
    }

    public static class BreedjectViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mName;
        TextView mBuild;

        public BreedjectViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView.findViewById(R.id.row_pokemon_imageview);
            mName = (TextView)itemView.findViewById(R.id.row_pokemon_name_textview);
            mBuild = (TextView)itemView.findViewById(R.id.row_pokemon_build_textview);
        }
    }


    @Override
    public BreedjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_row, parent, false);

        return new BreedjectViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(BreedjectViewHolder holder, final int position) {
        holder.mName.setText(mPokemon.get(position).name);
        holder.mBuild.setText(mPokemon.get(position).build);
        Picasso.with(mParent).load("http://img.pokemondb.net/artwork/" + mPokemon.get(position).name.toLowerCase() + ".jpg").into(holder.mImageView);
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRowClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("Adapter pokemon size", mPokemon.size()+"");
        return mPokemon.size();
    }

    public void addPokemon(Pokemon pokemon) {
        mPokemon.add(pokemon);
        notifyItemInserted(mPokemon.size()-1);
    }

    public void deletePokemon(int position){
        mPokemon.remove(position);
    }

    public void onRowClick(int position) {
        FragmentTransaction ft = ((MainActivity)mParent).getSupportFragmentManager().beginTransaction();

        BreedjectDetailFragment fragment = new BreedjectDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARGS_POKEMON, mPokemon.get(position));
        bundle.putInt(ARGS_POSITION, position);
        fragment.setArguments(bundle);

        ft.replace(R.id.activity_main_coordinator, fragment);
        ft.addToBackStack(null);
        ft.commit();

    }

}
