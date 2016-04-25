package com.filipkesteli.subactivitycalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String PARAMETER_FIRST = "com.filipkesteli.subactivitycalculator.first_number";
    public static final String PARAMETER_SECOND = "com.filipkesteli.subactivitycalculator.second_number";
    public static final int REQUEST_CODE = 0;
    public static final String PARAMETER_RETURN = "com.filipkesteli.subactivitycalculator.return_parameter";

    private EditText etFirst;
    private EditText etSecond;
    private Button btnPlus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWidgets();
        setupListeners();
    }

    private void initWidgets() {
        etFirst = (EditText) findViewById(R.id.etFirst);
        etSecond = (EditText) findViewById(R.id.etSecond);
        btnPlus = (Button) findViewById(R.id.btnPlus);
    }

    private void setupListeners() {
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendIntent();
            }
        });
    }

    private void sendIntent() {
        //Pitamo se da li je forma dobra (da li je sve u redu)
        if (isFormOK()) {
            Intent intent = new Intent(this, Subactivity.class);
            intent.putExtra(PARAMETER_FIRST, etFirst.getText().toString());
            intent.putExtra(PARAMETER_SECOND, etSecond.getText().toString());
            startActivityForResult(intent, REQUEST_CODE);
            clearForm();
        }
    }

    private void clearForm() {
        etFirst.setText("");
        etSecond.setText("");
        etFirst.requestFocus();
    }

    // Metoda koja provjerava da li je dobra forma:
    public boolean isFormOK() {
        if (etFirst.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, R.string.insert_first, Toast.LENGTH_SHORT).show();
            etFirst.requestFocus();
            return false;
        }
        if (etSecond.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, R.string.insert_second, Toast.LENGTH_SHORT).show();
            etFirst.requestFocus();
            return false;
        }
        return true;
    }

    //PODACI data
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(MainActivity.this, data.getStringExtra(PARAMETER_RETURN), Toast.LENGTH_SHORT).show();
                if (data != null && data.hasExtra(PARAMETER_RETURN)) {
                    double result = data.getDoubleExtra(PARAMETER_RETURN, 0.0);
                    Toast.makeText(MainActivity.this, result+"", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, R.string.data_is_not_available, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
