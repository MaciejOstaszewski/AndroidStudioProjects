package com.example.start.l11a;


import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.start.l11a.domain.LightingContent;
import com.example.start.l11a.fragments.lighting.AddLampFragment;
import com.example.start.l11a.fragments.lighting.LampListFragment;
import com.example.start.l11a.fragments.lighting.LightSettingsFragment;

public class MainActivity extends AppCompatActivity implements LampListFragment.OnListFragmentInteractionListener, LightSettingsFragment.OnFragmentInteractionListener, AddLampFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeFragment();


    }

    public void initializeFragment() {
        LampListFragment lampListFragment = LampListFragment.newInstance(1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment oldFragment = fragmentManager.findFragmentByTag("DEVICE_LIST");

        if (oldFragment != null) {
            fragmentManager.beginTransaction().remove(oldFragment).commit();

        }
        fragmentManager.beginTransaction().replace(R.id.fragmentList, lampListFragment, "DEVICE_LIST").commit();

    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onListFragmentInteraction(LightingContent.LightingItem item) {
        LightSettingsFragment lightSettingsFragment = LightSettingsFragment.newInstance(item.id, item.content, item.details, item.ip);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment oldFragment = fragmentManager.findFragmentByTag("LIGHT_SETTINGS");

        if (oldFragment != null) {
            fragmentManager.beginTransaction().remove(oldFragment).commit();

        }

        fragmentManager.beginTransaction().replace(R.id.fragmenEdit, lightSettingsFragment, "LIGHT_SETTINGS").commit();

    }

    @Override
    public void onAddNewLamp() {
        AddLampFragment addLampFragment = AddLampFragment.newInstance("", "", "", "");
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment oldFragment = fragmentManager.findFragmentByTag("DEVICE_EDIT");

        if (oldFragment != null) {
            fragmentManager.beginTransaction().remove(oldFragment).commit();

        }
        fragmentManager.beginTransaction().replace(R.id.fragmenEdit, addLampFragment, "DEVICE_EDIT").commit();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onSaveEdition(LightingContent.LightingItem item) {
        LightingContent.addOrModifyItem(item);

        LampListFragment lampListFragment = LampListFragment.newInstance(1);
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment oldFragment = fragmentManager.findFragmentByTag("DEVICE_LIST");

        if (oldFragment != null) {
            fragmentManager.beginTransaction().remove(oldFragment).commit();

        }
        fragmentManager.beginTransaction().replace(R.id.fragmentList, lampListFragment, "DEVICE_LIST").commit();


    }
}
