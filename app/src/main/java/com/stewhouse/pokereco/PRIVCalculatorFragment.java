package com.stewhouse.pokereco;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.stewhouse.pokereco.model.LevelRangeList;
import com.stewhouse.pokereco.model.Pokemon;
import com.stewhouse.pokereco.model.PokemonList;
import com.stewhouse.pokereco.util.PRSharedPreference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gomguk on 16. 7. 21..
 */
public class PRIVCalculatorFragment extends Fragment {

    private LevelRangeList mLevelRanges = null;
    private PokemonList mPokemonList = null;
    private List<String> mPokemonNameList = null;
    private Pokemon mCurrentPokemon = null;

    private EditText mHPEdit = null;
    private EditText mCPEdit = null;
    private EditText mStardustEdit = null;
    private ListView mIVListView = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_iv, container, false);

        Spinner pokemonNameSpinner = (Spinner) view.findViewById(R.id.spinner_pokemon_name);
        mHPEdit = (EditText) view.findViewById(R.id.edit_hp);
        mCPEdit = (EditText) view.findViewById(R.id.edit_cp);
        mStardustEdit = (EditText) view.findViewById(R.id.edit_stardust);
        final Spinner levelGuessSpinner = (Spinner) view.findViewById(R.id.spinner_level_guess);

        mIVListView = (ListView) view.findViewById(R.id.list_iv);
        Button clearBtn = (Button) view.findViewById(R.id.btn_clear);

        setData();

        if (pokemonNameSpinner != null) {
            mPokemonNameList = new ArrayList<>();

            for (Pokemon pokemon : mPokemonList.getPokemonList()) {
                mPokemonNameList.add(String.valueOf(pokemon.getId()) + ". " + pokemon.getName());
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, mPokemonNameList);

            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            pokemonNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mCurrentPokemon = mPokemonList.getPokemonList().get(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            pokemonNameSpinner.setAdapter(arrayAdapter);
        }

        if (mStardustEdit != null) {
            mStardustEdit.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length() > 0) {
                        Integer stardust = Integer.parseInt(s.toString());
                        Integer[] levelRange = mLevelRanges.getLevelsWithStardust(stardust.intValue());

                        if (levelRange == null) {
                            levelGuessSpinner.setVisibility(View.GONE);

                            return;
                        } else {
                            ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, levelRange);

                            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            levelGuessSpinner.setAdapter(arrayAdapter);
                            levelGuessSpinner.setVisibility(View.VISIBLE);
                        }
                    } else {
                        levelGuessSpinner.setVisibility(View.GONE);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }

        if (clearBtn != null) {
            clearBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    mHPEdit.setText("");
                    mCPEdit.setText("");
                    mStardustEdit.setText("");
                }
            });
        }

        if (mIVListView != null) {
            mIVListView.setAdapter(new PRIVAdapter());
        }

        return view;
    }

    private void setData() {
        Gson gson = new Gson();
        mLevelRanges = gson.fromJson(PRSharedPreference.getGameDataStardustList(getActivity()), LevelRangeList.class);
        mPokemonList = gson.fromJson(PRSharedPreference.getGameDataPokemonList(getActivity()), PokemonList.class);
    }

    private class PRIVAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater layoutInflater = LayoutInflater.from(getContext());

                convertView = layoutInflater.inflate(R.layout.cell_list_iv_result, parent, false);
            }

            return convertView;
        }
    }
}