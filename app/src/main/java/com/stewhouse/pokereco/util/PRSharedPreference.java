package com.stewhouse.pokereco.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.stewhouse.pokereco.PRConstants;

/**
 * Created by Gomguk on 16. 7. 22..
 */
public class PRSharedPreference {

    public static String getGameDataSkillVersion(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PRConstants.PREF_FILE_NAME, Context.MODE_PRIVATE);

        return preferences.getString(PRConstants.PREF_GAME_DATA_SKILL_VERSION, null);
    }

    public static void setGameDataSkillVersion(Context context, String version) {
        SharedPreferences preferences = context.getSharedPreferences(PRConstants.PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(PRConstants.PREF_GAME_DATA_SKILL_VERSION, version);
        editor.commit();
    }

    public static String getGameDataSkillList(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PRConstants.PREF_FILE_NAME, Context.MODE_PRIVATE);

        return preferences.getString(PRConstants.PREF_GAME_DATA_SKILL, null);
    }

    public static void setGameDataSkillList(Context context, String skillList) {
        SharedPreferences preferences = context.getSharedPreferences(PRConstants.PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(PRConstants.PREF_GAME_DATA_SKILL, skillList);
        editor.commit();
    }

    public static String getGameDataPokemonVersion(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PRConstants.PREF_FILE_NAME, Context.MODE_PRIVATE);

        return preferences.getString(PRConstants.PREF_GAME_DATA_POKEMON_VERSION, null);
    }

    public static void setGameDataPokemonVersion(Context context, String version) {
        SharedPreferences preferences = context.getSharedPreferences(PRConstants.PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(PRConstants.PREF_GAME_DATA_POKEMON_VERSION, version);
        editor.commit();
    }

    public static String getGameDataPokemonList(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PRConstants.PREF_FILE_NAME, Context.MODE_PRIVATE);

        return preferences.getString(PRConstants.PREF_GAME_DATA_POKEMON, null);
    }

    public static void setGameDataPokemonList(Context context, String skillList) {
        SharedPreferences preferences = context.getSharedPreferences(PRConstants.PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(PRConstants.PREF_GAME_DATA_POKEMON, skillList);
        editor.commit();
    }

    public static String getGameDataStardustVersion(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PRConstants.PREF_GAME_DATA_STARDUST, Context.MODE_PRIVATE);

        return preferences.getString(PRConstants.PREF_GAME_DATA_STARDUST_VERSION, null);
    }

    public static void setGameDataStardustVersion(Context context, String version) {
        SharedPreferences preferences = context.getSharedPreferences(PRConstants.PREF_GAME_DATA_STARDUST, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(PRConstants.PREF_GAME_DATA_STARDUST_VERSION, version);
        editor.commit();
    }

    public static String getGameDataStardustList(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PRConstants.PREF_GAME_DATA_STARDUST, Context.MODE_PRIVATE);

        return preferences.getString(PRConstants.PREF_GAME_DATA_STARDUST, null);
    }

    public static void setGameDataStardustList(Context context, String skillList) {
        SharedPreferences preferences = context.getSharedPreferences(PRConstants.PREF_GAME_DATA_STARDUST, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(PRConstants.PREF_GAME_DATA_STARDUST, skillList);
        editor.commit();
    }
}
