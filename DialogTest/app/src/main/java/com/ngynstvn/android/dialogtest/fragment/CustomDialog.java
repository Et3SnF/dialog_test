package com.ngynstvn.android.dialogtest.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ngynstvn.android.dialogtest.R;
import com.ngynstvn.android.dialogtest.adapter.Adapter;
import com.ngynstvn.android.dialogtest.helper.ItemTouchHelperCallback;

public class CustomDialog extends DialogFragment {

    private static final String TAG = "Test (" + CustomDialog.class.getSimpleName() + "): ";

    private Button btnAddCategory;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private ItemTouchHelper.Callback callback;
    private ItemTouchHelper touchHelper;

    // Important single instantiation of this class

    public static CustomDialog newInstance(int title) {

        CustomDialog customDialog = new CustomDialog();

        // Supply num input as an argument --> for retrieving stuff later

        Bundle bundle = new Bundle();
        bundle.putInt("title", title);

        customDialog.setArguments(bundle);

        return customDialog;

    }

    // ----- Lifecycle Methods ----- //

    @Override
    public void onAttach(Activity activity) {
        Log.v(TAG, "onAttach() called");
        super.onAttach(activity);
        // Hmm...to attach or to not attach....
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
        adapter = new Adapter();
        callback = new ItemTouchHelperCallback(adapter, getActivity());
        touchHelper = new ItemTouchHelper(callback);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Log.v(TAG, "onCreateDialog() called");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),
                R.style.MaterialAlertDialogStyle);

        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_layout, null);

        // Inflate anything specifically in this lifecycle method

        btnAddCategory = (Button) view.findViewById(R.id.btn_add_category);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_custom_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        touchHelper.attachToRecyclerView(recyclerView);

        builder.setView(view)
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
                })
                .setNeutralButton("Reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.v(TAG, "Reset Button Clicked");
                        Toast.makeText(getActivity(), "Reset Button Clicked", Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        Log.v(TAG, "onActivityCreated() called");

        super.onActivityCreated(savedInstanceState);

        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "Add Category Button Clicked");
                Toast.makeText(getActivity(), "Add Category Button Clicked", Toast.LENGTH_SHORT).show();
                dismiss();
                showAddCategoryDialog();
            }
        });

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

    // ---------------- //

    void showAddCategoryDialog() {
        AddCategoryDialog addCategoryDialog = AddCategoryDialog.newInstance(1);
        addCategoryDialog.show(getFragmentManager(), "add_category");
    }

}
