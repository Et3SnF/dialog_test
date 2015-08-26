package com.ngynstvn.android.dialogtest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;

public class CustomDialog extends DialogFragment {

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
        return new AlertDialog.Builder(getActivity())
                .setView(getActivity().getLayoutInflater().inflate(R.layout.dialog_layout, null))
                .create();
    }

}
