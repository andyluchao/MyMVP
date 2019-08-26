package com.yundotech.mymvp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.yundotech.mymvp.ui.example.ExampleFragment;

public class ExampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ExampleFragment.newInstance())
                    .commitNow();
        }
    }
}
