package com.ngynstvn.android.dialogtest.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ngynstvn.android.dialogtest.R;
import com.ngynstvn.android.dialogtest.UIUtils;
import com.ngynstvn.android.dialogtest.application.DialogApplication;
import com.ngynstvn.android.dialogtest.model.Category;

public class AddCategoryDialog extends DialogFragment {

    private static final String TAG = "Test (" + AddCategoryDialog.class.getSimpleName() + "): ";

    private AlertDialog.Builder builder;
    private AlertDialog alertDialog;
    private EditText editText;

    // Important instance method with saved state

    public static AddCategoryDialog newInstance(int title) {

        AddCategoryDialog addCategoryDialog = new AddCategoryDialog();

        Bundle bundle = new Bundle();
        bundle.putInt("title", title);

        addCategoryDialog.setArguments(bundle);

        return addCategoryDialog;

    }

    // ----- Lifecycle Methods ------ //

    @Override
    public void onAttach(Activity activity) {
        Log.v(TAG, "onAttach() called");
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
        builder = new AlertDialog.Builder(getActivity(), R.style.MaterialAlertDialogStyle);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.v(TAG, "onCreateDialog() called");

        View view = getActivity().getLayoutInflater().inflate(R.layout.category_input, null);

        editText = (EditText) view.findViewById(R.id.et_category_input);

        builder.setTitle("Add Category")
                .setView(view)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v(TAG, "Positive Button clicked");
                        // Leave this blank. It is being handled somewhere else!
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v(TAG, "Negative Button clicked");
                        showCategoryDialog();
                    }
                });

        alertDialog = builder.create();

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

        // Overriding + button behavior

        final AlertDialog alertDialog = (AlertDialog) getDialog();

        if(alertDialog != null) {

            editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    alertDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            });

            Button okButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);

            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.v(TAG, "Positive Button Clicked");

                    boolean closeDialog = false;

                    String value = editText.getText().toString();

                    // Modify this to be database stuff later

                    if(value.equalsIgnoreCase("")) {
                        Toast.makeText(getActivity(), "Invalid entry. Please try again.", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    for(Category category : DialogApplication.getSharedFakeData().getCategoryArrayList()) {
                        if(value.equalsIgnoreCase(category.getCategoryName())) {
                            Toast.makeText(getActivity(), value + " is already a category", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }

                    DialogApplication.getSharedFakeData().getCategoryArrayList()
                            .add(new Category(value, UIUtils.generateRandomColor(android.R.color.white)));
                    closeDialog = true;

                    if(closeDialog) {
                        dismiss();
                        showCategoryDialog();
                    }
                }
            });
        }
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

    void showCategoryDialog(){
        CustomDialog customDialog = CustomDialog.newInstance(R.string.fbc_dialog_title);
        customDialog.show(getFragmentManager(), "category_dialog");
    }
}

