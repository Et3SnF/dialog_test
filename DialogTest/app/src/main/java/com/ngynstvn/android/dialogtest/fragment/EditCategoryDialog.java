package com.ngynstvn.android.dialogtest.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ngynstvn.android.dialogtest.R;

public class EditCategoryDialog extends DialogFragment {

    private static String TAG = "Test (" + EditCategoryDialog.class.getSimpleName() + "): ";

    private AlertDialog.Builder builder;

    // Important instance method with saved state

    public static EditCategoryDialog newInstance(int title) {

        EditCategoryDialog editCategoryDialog = new EditCategoryDialog();

        Bundle bundle = new Bundle();
        bundle.putInt("title", title);

        editCategoryDialog.setArguments(bundle);

        return editCategoryDialog;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        builder = new AlertDialog.Builder(getActivity(), R.style.MaterialAlertDialogStyle);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.category_input, null);

        builder.setTitle("Add Category")
                .setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        Log.v(TAG, "onActivityCreated() called");

        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onStart() {
        Log.v(TAG, "onStart() called");
        super.onStart();
        // This is where the fragment stops at when I first load it.
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        Log.v(TAG, "onCancel() called");
        // This is called only if I touched outside of the dialog to dismiss
        super.onCancel(dialog);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        Log.v(TAG, "onDismiss() called");
        super.onDismiss(dialog);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.v(TAG, "onSaveInstanceState() called");
        super.onSaveInstanceState(outState);

        // Called probably whenever I have another dialog and that I want to call to do something
        // to update information here
    }

    @Override
    public void onStop() {
        Log.v(TAG, "onStop() called");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.v(TAG, "onDestroyView() called");
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        Log.v(TAG, "onDetach() called");
        super.onDetach();
    }

    // ------------------------- //

}
