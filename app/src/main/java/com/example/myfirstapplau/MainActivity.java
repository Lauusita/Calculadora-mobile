package com.example.myfirstapplau;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {

     EditText inputOne, inputTwo;
     TextView result;
     Button calculate;

     Spinner spinner;
     List<CharSequence> values;

     Float resultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputOne = findViewById(R.id.inputOne);
        inputTwo = findViewById(R.id.inputTwo);
        result = findViewById(R.id.result);
        calculate = findViewById(R.id.calculate);
        String[] v = {"+", "-", "x", "÷"};

        values = List.of("+", "-", "x", "÷");

        spinner = (Spinner) findViewById(R.id.options);
        ArrayAdapter<CharSequence> arrayAdapter = getCharSequenceArrayAdapter(values);

        processArrayOptions(spinner, arrayAdapter);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process();
            }
        });
    }

    private void processArrayOptions(@NonNull Spinner spinner, ArrayAdapter<CharSequence> arrayAdapter) {

        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected = (String) spinner.getItemAtPosition(i);
                Toast.makeText(adapterView.getContext(), selected, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(adapterView.getContext(), "Elige una opción", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    private ArrayAdapter<CharSequence> getCharSequenceArrayAdapter(List<CharSequence> values) {
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                values
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    private void process() {
        String num1 = inputOne.getText().toString();
        String num2 = inputTwo.getText().toString();
        String opt = spinner.getSelectedItem().toString();

        if (num1.isEmpty() || num2.isEmpty()) {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        } else {
            switch (opt) {
                case "+":
                    resultado = Float.parseFloat(num1) +  Float.parseFloat(num2);
                    result.setText(resultado+"");
                    return;

                case "-":
                    resultado = Float.parseFloat(num1) - Float.parseFloat(num2);
                    result.setText(resultado+"");
                    return;

                case "x":
                    resultado = Float.parseFloat(num1) * Float.parseFloat(num2);
                    result.setText(resultado+"");
                    return;

                case "÷":
                    resultado = Float.parseFloat(num1) / Float.parseFloat(num2);
                    result.setText(resultado+"");
                    return;
            }
        }
    }


}