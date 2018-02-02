package com.example.start.l11a.fragments.lighting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import com.example.start.l11a.R;
import com.example.start.l11a.domain.LightingContent;
import com.example.start.l11a.tasks.SendSettingsTask;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LightSettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LightSettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LightSettingsFragment extends Fragment {

    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String DETAILS = "DETAILS";
    private static final String IP = "IP";

    private String id;
    private String name;
    private String details;
    private String ip;


    Button changeButton;
    Button editButton;
    Button animButton;
    Button brightnessButton;

    SeekBar redBar;
    SeekBar greenBar;
    SeekBar blueBar;
    SeekBar brightnessBar;
    SeekBar anim1Bar;
    SeekBar anim2Bar;

    private OnFragmentInteractionListener mListener;

    public LightSettingsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static LightSettingsFragment newInstance(String id, String name, String details, String ip) {
        LightSettingsFragment fragment = new LightSettingsFragment();
        Bundle args = new Bundle();
        args.putString(ID, id);
        args.putString(NAME, name);
        args.putString(DETAILS, details);
        args.putString(IP, ip);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString(ID);
            name = getArguments().getString(NAME);
            details = getArguments().getString(DETAILS);
            ip = getArguments().getString(IP);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_light_settings, container, false);

        redBar = view.findViewById(R.id.red);
        greenBar = view.findViewById(R.id.green);
        blueBar = view.findViewById(R.id.blue);
        brightnessBar = view.findViewById(R.id.brightness);

        changeButton = view.findViewById(R.id.changeButton);
        editButton = view.findViewById(R.id.editButton);
        animButton = view.findViewById(R.id.animButton);
        brightnessButton = view.findViewById(R.id.brightnessButton);


        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] data = new byte[]{2, (byte) redBar.getProgress(), (byte) greenBar.getProgress(), (byte) blueBar.getProgress()};
                String str = Base64.encodeToString(data,0);


                byte[] data2 = new byte[]{5, (byte) brightnessBar.getProgress()};
                String str2 = Base64.encodeToString(data2,0);


                SendSettingsTask sendSettingsTask = new SendSettingsTask();

                sendSettingsTask.execute(ip, str);
                sendSettingsTask.execute(ip, str2);

            }
        });

        brightnessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] data = new byte[]{5, (byte) brightnessBar.getProgress()};
                String str = Base64.encodeToString(data,0);

                SendSettingsTask sendSettingsTask = new SendSettingsTask();

                sendSettingsTask.execute(ip, str);
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddLampFragment addLampFragment = AddLampFragment.newInstance(id, name, details, ip);
                FragmentManager fragmentManager = getFragmentManager();
                Fragment oldFragment = fragmentManager.findFragmentByTag("DEVICE_EDIT");

                if (oldFragment != null) {
                    fragmentManager.beginTransaction().remove(oldFragment).commit();

                }
                fragmentManager.beginTransaction().replace(R.id.fragmenEdit, addLampFragment, "DEVICE_EDIT").commit();
            }
        });


        animButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                byte[] data = new byte[]{1, (byte) redBar.getProgress(), (byte) greenBar.getProgress(), (byte) blueBar.getProgress(), (byte) anim1Bar.getProgress(), (byte) anim2Bar.getProgress()};
                String str = Base64.encodeToString(data,0);

                SendSettingsTask sendSettingsTask = new SendSettingsTask();

                sendSettingsTask.execute(ip, str);

            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
