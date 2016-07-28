package com.stewhouse.pokereco;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.stewhouse.pokereco.util.PRSharedPreference;

import java.io.InputStream;

public class PRMainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout = null;
    private ListView mDrawerList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init Layouts.
        setContentView(R.layout.activity_main);
        initDrawer();

        // Init data.
        checkGameData();
    }

    private void initDrawer() {
        mDrawerList = (ListView) findViewById(R.id.drawer_list);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (mDrawerList != null) {
            mDrawerList.setAdapter(new PRDrawerAdapter());
            mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    if (position == 0) {    // IV calculator Cell.
                        Fragment fragment = new PRIVCalculatorFragment();
                        FragmentManager fragmentManager = getFragmentManager();

                        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                    }

                    mDrawerLayout.closeDrawers();
                }
            });
        }
    }

    private void checkGameData() {
        if (PRSharedPreference.getGameDataSkillVersion(this) == null) { // Check skill data version.
            PRSharedPreference.setGameDataSkillVersion(this, PRConstants.GAME_DATA_SKILL_VERSION);
        } else if (PRSharedPreference.getGameDataSkillVersion(this) != null && !PRSharedPreference.getGameDataSkillVersion(this).equals(PRConstants.PREF_GAME_DATA_SKILL_VERSION)) {
            PRSharedPreference.setGameDataSkillVersion(this, PRConstants.GAME_DATA_SKILL_VERSION);
        }

        if (PRSharedPreference.getGameDataPokemonVersion(this) == null) {   // Check pokemon data version.
            PRSharedPreference.setGameDataPokemonVersion(this, PRConstants.GAME_DATA_POKEMON_VERSION);
        } else if (PRSharedPreference.getGameDataPokemonVersion(this) != null && !PRSharedPreference.getGameDataPokemonVersion(this).equals(PRConstants.PREF_GAME_DATA_POKEMON_VERSION)) {
            PRSharedPreference.setGameDataPokemonVersion(this, PRConstants.GAME_DATA_POKEMON_VERSION);
        }

        if (PRSharedPreference.getGameDataStardustVersion(this) == null) {   // Check stardust data version.
            PRSharedPreference.setGameDataStardustVersion(this, PRConstants.GAME_DATA_STARDUST_VERSION);
        } else if (PRSharedPreference.getGameDataStardustVersion(this) != null && !PRSharedPreference.getGameDataStardustVersion(this).equals(PRConstants.GAME_DATA_STARDUST_VERSION)) {
            PRSharedPreference.setGameDataStardustVersion(this, PRConstants.GAME_DATA_STARDUST_VERSION);
        }

        readGameData();
    }

    // TODO: Need to refactoring.
    private void readGameData() {
        try {
            InputStream is = getAssets().open("game_data_skill_" + PRConstants.GAME_DATA_SKILL_VERSION + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            if (json != null) {
                PRSharedPreference.setGameDataSkillList(this, json);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        try {
            InputStream is = getAssets().open("game_data_pokemon_" + PRConstants.GAME_DATA_POKEMON_VERSION + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            if (json != null) {
                PRSharedPreference.setGameDataPokemonList(this, json);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        try {
            InputStream is = getAssets().open("game_data_stardust_" + PRConstants.GAME_DATA_STARDUST_VERSION + ".json");
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            if (json != null) {
                PRSharedPreference.setGameDataStardustList(this, json);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private class PRDrawerAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 1;
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
                LayoutInflater layoutInflater = LayoutInflater.from(PRMainActivity.this);

                if (position == 0) {    // IV calculator Cell Setting.
                    convertView = layoutInflater.inflate(R.layout.cell_drawer_iv, parent, false);
                }
            }

            return convertView;
        }
    }
}