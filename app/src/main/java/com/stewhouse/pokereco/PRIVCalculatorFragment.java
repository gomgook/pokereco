package com.stewhouse.pokereco;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_iv, container, false);

        mPokemonNameSpinner = (Spinner) view.findViewById(R.id.spinner_pokemon_name);
        if (mPokemonNameSpinner != null) {
            Gson gson = new Gson();
            PokemonList pokemonList = gson.fromJson(PRSharedPreference.getGameDataPokemonList(getActivity()), PokemonList.class);
            List<String> pokemonNameList = new ArrayList<>();

            for (Pokemon pokemon : pokemonList.getPokemonList()) {
                pokemonNameList.add(String.valueOf(pokemon.getId()) + ". " + pokemon.getName());
            }

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, pokemonNameList);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            mPokemonNameSpinner.setAdapter(arrayAdapter);
        }

        return view;
    }
}
