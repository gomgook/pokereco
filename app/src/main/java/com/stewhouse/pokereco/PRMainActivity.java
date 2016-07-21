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

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;

public class PRMainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout = null;
    private ListView mDrawerList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initDrawer();

        try {
            InputStream is = getAssets().open("game_data_skill.json");
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(json).getAsJsonObject();
        } catch (Throwable e) {
            e.printStackTrace();
        }

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