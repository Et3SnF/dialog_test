package com.ngynstvn.android.dialogtest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class CustomDialog extends DialogFragment {

    private static final String TAG = "Test (" + CustomDialog.class.getSimpleName() + "): ";

    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;

    // Important single instantiation of this class

    public static CustomDialog newInstance(int title) {

        CustomDialog customDialog = new CustomDialog();

        // Supply num input as an argument --> for retrieving stuff later

        Bundle bundle = new Bundle();
        bundle.putInt("title", title);

        customDialog.setArguments(bundle);

        return customDialog;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        return new AlertDialog.Builder(getActivity(), R.style.MaterialAlertDialogStyle)
                .setView(getActivity().getLayoutInflater().inflate(R.layout.dialog_layout, null))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v(TAG, "Positive Button Clicked");
                        Toast.makeText(getActivity(), "Positive Button Clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v(TAG, "Negative Button Clicked");
                        Toast.makeText(getActivity(), "Negative Button Clicked", Toast.LENGTH_SHORT).show();
                    }
                }).create();

    }

}
