package com.ngynstvn.android.dialogtest.activity;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ngynstvn.android.dialogtest.adapter.Adapter;
import com.ngynstvn.android.dialogtest.fragment.CustomDialog;
import com.ngynstvn.android.dialogtest.R;

public class MainActivity extends AppCompatActivity {

    private Button simpleButton;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleButton = (Button) findViewById(R.id.btn_show_dialog);

        simpleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        showDialog();
    }

    void showDialog() {
        DialogFragment fragment = CustomDialog.newInstance(R.string.fbc_dialog_title);
        fragment.show(getFragmentManager(), "filter by category");
    }

}
