package id.ac.ui.cs.mobileprogramming.jeremy.memento;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class CreditActivity extends AppCompatActivity {

    private OpenGLView openGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        openGLView =(OpenGLView) findViewById(R.id.credit);
    }



}
