package com.stewhouse.pokereco;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.stewhouse.pokereco.model.Pokemon;
import com.stewhouse.pokereco.model.PokemonList;
import com.stewhouse.pokereco.util.PRSharedPreference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gomguk on 16. 7. 21..
 */
public class PRIVCalculatorFragment extends Fragment {

    private Spinner mPokemonNameSpinner = null;
    private Spinner mPokemonLevelGuessSpinner = null;

    private PokemonList mPokemonList = null;
    private List<String> mPokemonNameList = null;
    private Pokemon mCurrentPokemon = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_iv, container, false);

        mPokemonNameSpinner = (Spinner) view.findViewById(R.id.spinner_pokemon_name);
        mPokemonLevelGuessSpinner = (Spinner) view.findViewById(R.id.spinner_level_guess);

        if (mPokemonNameSpinner != null) {
            Gson gson = new Gson();
            mPokemonList = gson.fromJson(PRSharedPreference.getGameDataPokemonList(getActivity()), PokemonList.class);
            mPokemonNameList = new ArrayList<>();

            for (Pokemon pokemon : mPokemonList.getPokemonList()) {
                mPokemonNameList.add(String.valueOf(pokemon.getId()) + ". " + pokemon.getName());
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, mPokemonNameList);

            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mPokemonNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mCurrentPokemon = mPokemonList.getPokemonList().get(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            mPokemonNameSpinner.setAdapter(arrayAdapter);
        }

        if (mPokemonLevelGuessSpinner != null) {
            
        }

        return view;
    }
}
