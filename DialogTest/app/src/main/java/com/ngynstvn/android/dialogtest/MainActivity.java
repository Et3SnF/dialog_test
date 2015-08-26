package com.ngynstvn.android.dialogtest;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button simpleButton;

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
