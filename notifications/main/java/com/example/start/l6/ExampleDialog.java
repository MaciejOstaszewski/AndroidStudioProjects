package com.example.start.l6;

        import android.app.AlertDialog;
        import android.app.Dialog;
        import android.app.DialogFragment;
        import android.app.FragmentManager;
        import android.app.FragmentTransaction;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Toast;

/**
 * Created by Admin on 22.11.2017.
 */

public class ExampleDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new
                AlertDialog.Builder(getActivity());
        builder.setMessage("Powiadomienie z interakcja")
                .setPositiveButton("Wow", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
//Toast pierwszy
                                Toast.makeText(getActivity(), "Wow", Toast.LENGTH_SHORT).show();
                            }
                        })
                .setNegativeButton("Tyle opcji", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
//Tost drugi
                                Toast.makeText(getActivity(), "Tyle opcji", Toast.LENGTH_SHORT).show();
                            }
                        });
        Dialog dialog = builder.create();
        return dialog;
    }

}
