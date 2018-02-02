package com.example.start.l10;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.start.l10.dummy.LightingContent;
import com.example.start.l10.dummy.SensorContent;

public class MainActivity extends AppCompatActivity implements DeviceTypeFragment.OnFragmentInteractionListener, LightingListFragment.OnListFragmentInteractionListener, SensorListFragment.OnListFragmentInteractionListener {

    Button addButton;
    LightingListFragment fragment;
    SensorListFragment Sfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addButton = (Button) findViewById(R.id.addButton);

    }

    @Override
    public void onFragmentInteraction(int deviceType) {
        addButton.setVisibility(View.VISIBLE);

        if (deviceType == 0) {


            fragment = LightingListFragment.newInstance(1);
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment oldFragment = fragmentManager.findFragmentByTag("DEVICE_LIST");

            if (oldFragment != null) {
                fragmentManager.beginTransaction().remove(oldFragment).commit();
            }
            fragmentManager.beginTransaction().replace(R.id.fragment, fragment, "DEVICE_LIST").commit();

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LightingContent.addItem(new LightingContent.LightnigItem(String.valueOf(LightingContent.COUNT), "text", "text", 1, 1, 1, 1, "2"));
                    fragment.myLightingRecyclerViewAdapter.notifyDataSetChanged();
                }
            });

        } else if (deviceType == 1) {


            Sfragment = SensorListFragment.newInstance(1);
            FragmentManager fragmentManager = getSupportFragmentManager();
            Fragment oldFragment = fragmentManager.findFragmentByTag("DEVICE_LIST");

            if (oldFragment != null) {
                fragmentManager.beginTransaction().remove(oldFragment).commit();
            }
            fragmentManager.beginTransaction().replace(R.id.fragment, Sfragment, "DEVICE_LIST").commit();

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SensorContent.addItem(new SensorContent.SensorItem(String.valueOf(SensorContent.COUNT), "text", "text"));
                    Sfragment.mySensorRecyclerViewAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    @Override
    public void onListFragmentInteraction(LightingContent.LightnigItem item) {
        LightingContent.removeItem(item);
        fragment.myLightingRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onListFragmentInteraction(SensorContent.SensorItem item) {
        SensorContent.removeItem(item);
        Sfragment.mySensorRecyclerViewAdapter.notifyDataSetChanged();
    }
}
