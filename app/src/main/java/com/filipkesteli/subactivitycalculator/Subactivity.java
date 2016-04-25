package com.filipkesteli.subactivitycalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Subactivity extends AppCompatActivity {

    private EditText etFirstSub;
    private EditText etSecondSub;
    private Button btnPlusSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subactivity);

        initWidgets();
        setupListeners();

        handleIntents();
    }

    private void handleIntents() {
        Intent intent = getIntent();
        if (intent.hasExtra(MainActivity.PARAMETER_FIRST) && intent.hasExtra(MainActivity.PARAMETER_SECOND)) {
            etFirstSub.setText(intent.getStringExtra(MainActivity.PARAMETER_FIRST));
            etSecondSub.setText(intent.getStringExtra(MainActivity.PARAMETER_SECOND));
        }
    }

    private void initWidgets() {
        etFirstSub = (EditText) findViewById(R.id.etFirstSub);
        etSecondSub = (EditText) findViewById(R.id.etSecondSub);
        btnPlusSub = (Button) findViewById(R.id.btnPlusSub);
    }

    private void setupListeners() {
        btnPlusSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnSum();
            }
        });
    }

    private void returnSum() {
        double first = Double.parseDouble(etFirstSub.getText().toString());
        double second = Double.parseDouble(etSecondSub.getText().toString());
        double result = first + second;

        //implicitni intent, ali ne u manifestu
        Intent data = new Intent();
        data.putExtra(MainActivity.PARAMETER_RETURN, result);
        setResult(RESULT_OK, data); //vraca se. ali nije umro!
        finish();
    }

    @Override
    public void finish() {
        super.finish();
    }
}
