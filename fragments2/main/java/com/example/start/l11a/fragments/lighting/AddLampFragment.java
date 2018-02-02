package com.example.start.l11a.fragments.lighting;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.start.l11a.R;
import com.example.start.l11a.domain.LightingContent;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddLampFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddLampFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddLampFragment extends Fragment {
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String DETAILS = "DETAILS";
    private static final String IP = "IP";

    private String id;
    private String name;
    private String details;
    private String ip;

    TextView editDescription;
    EditText deviceName;
    EditText deviceIp;
    EditText deviceDetails;

    private OnFragmentInteractionListener mListener;

    public AddLampFragment() {
        // Required empty public constructor
    }


    public static AddLampFragment newInstance(String id, String name, String details, String ip) {
        AddLampFragment fragment = new AddLampFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_lamp, container, false);

        editDescription = (TextView) view.findViewById(R.id.edit_description);
        deviceName = (EditText) view.findViewById(R.id.name);
        deviceIp = (EditText) view.findViewById(R.id.ip);
        deviceDetails = (EditText) view.findViewById(R.id.details);

        if (!id.isEmpty()) {
            editDescription.setText("Edytuj oświetlenie");
            deviceName.setText(name);
            deviceIp.setText(ip);
            deviceDetails.setText(details);
        }

        Button save = (Button) view.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LightingContent.LightingItem item = new LightingContent.LightingItem(
                        id
                        ,deviceName.getText().toString()
                        ,deviceDetails.getText().toString()
                        ,deviceIp.getText().toString());

                mListener.onSaveEdition(item);

            }
        });

        return view;
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
        void onSaveEdition(LightingContent.LightingItem item);
    }
}
