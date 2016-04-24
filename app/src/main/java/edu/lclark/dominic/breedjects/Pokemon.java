package edu.lclark.dominic.breedjects;

import android.os.Parcelable;
import android.provider.BaseColumns;

import java.io.Serializable;

/**
 * Created by Dominic on 4/21/2016.
 */
public class Pokemon implements Serializable{

    public String name, build, ability, nature, item, move1, move2, move3, move4;
    public int hpEv, attackEv, defenseEv, spattackEv, spdefenceEv, speedEv;

    public static abstract class PokemonEntry implements BaseColumns {
        public static final String TABLE_NAME = "pokemon";
        public static final String COLUMN_NAME_ID = "ID";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_BUILD = "build";
        public static final String COLUMN_NAME_ABILITY = "ability";
        public static final String COLUMN_NAME_NATURE = "nature";
        public static final String COLUMN_NAME_ITEM = "item";
        public static final String COLUMN_NAME_MOVE1 = "move1";
        public static final String COLUMN_NAME_MOVE2 = "move2";
        public static final String COLUMN_NAME_MOVE3 = "move3";
        public static final String COLUMN_NAME_MOVE4 = "move4";
        public static final String COLUMN_NAME_HPEV = "hpev";
        public static final String COLUMN_NAME_ATTTACKEV = "attackev";
        public static final String COLUMN_NAME_DEFENSEEV = "defenseev";
        public static final String COLUMN_NAME_SPATTACKEV = "spattackev";
        public static final String COLUMN_NAME_SPDEFENSEEV = "spdefenseev";
        public static final String COLUMN_NAME_SPEEDEV = "speedev";
    }

    public Pokemon(String name, String build, String ability, String nature, String item, String move1, String move2, String move3, String move4, int hpEv, int attackEv, int defenseEv, int spattackEv, int spdefenceEv, int speedEv) {
        this.name = name;
        this.build = build;
        this.ability = ability;
        this.nature = nature;
        this.item = item;
        this.move1 = move1;
        this.move2 = move2;
        this.move3 = move3;
        this.move4 = move4;
        this.hpEv = hpEv;
        this.attackEv = attackEv;
        this.defenseEv = defenseEv;
        this.spattackEv = spattackEv;
        this.spdefenceEv = spdefenceEv;
        this.speedEv = speedEv;
    }

}
