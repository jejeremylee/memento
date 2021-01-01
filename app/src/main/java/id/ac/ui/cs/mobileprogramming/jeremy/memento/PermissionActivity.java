package id.ac.ui.cs.mobileprogramming.jeremy.memento;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.widget.Button;


public class PermissionActivity extends AppCompatActivity {

    private PermissionDescription permFrag = new PermissionDescription();

    private static int RESULT_LOAD_IMAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perm);

        Button allowButton = findViewById(R.id.allow_button);
        allowButton.setOnClickListener(click -> {

            ActivityCompat.requestPermissions(PermissionActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, RESULT_LOAD_IMAGE);
            PermissionActivity.this.finish();

        });

        Button denyButton = findViewById(R.id.deny_button);
        denyButton.setOnClickListener(click -> PermissionActivity.this.finish());

        getSupportFragmentManager().beginTransaction()
                .add(R.id.permAct, permFrag)
                .commit();
    }



}