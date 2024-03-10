package com.example.custom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements InputNumberView.OnNumberChangeListener{
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputNumberView inputNumberView = findViewById(R.id.input_number_view);
        inputNumberView.setOnNumberChangeListener(this);
    }

    @Override
    public void onNumberChange(int value) {
        Log.d(TAG, "current value is ===>" + value);
    }
}



