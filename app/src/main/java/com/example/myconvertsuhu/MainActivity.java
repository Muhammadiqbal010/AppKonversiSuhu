package com.example.myconvertsuhu;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etSuhu, etHasilKonversi;
    private Spinner spOpsiInput, spOpsiOutput;
    private Button btnKonversi, btnReset;
    private TextView tvHasilHitung, tvConvertRumus, tvRumusnya;
    private View layoutHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSuhu = findViewById(R.id.Suhu);
        etHasilKonversi = findViewById(R.id.HasilKonversi);
        spOpsiInput = findViewById(R.id.OpsiInput);
        spOpsiOutput = findViewById(R.id.OpsiOutput);
        btnKonversi = findViewById(R.id.btnKonversi);
        btnReset = findViewById(R.id.btnReset);
        layoutHasil = findViewById(R.id.layoutHasil);
        tvHasilHitung = findViewById(R.id.hasilHitung);
        tvConvertRumus = findViewById(R.id.convertRumus);
        tvRumusnya = findViewById(R.id.rumusnya);

        String[] temperatureUnits = {"Celsius (°C)", "Reaumur (°R)", "Kelvin (°K)", "Fahrenheit (°F)"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, temperatureUnits);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spOpsiInput.setAdapter(adapter);
        spOpsiOutput.setAdapter(adapter);

        btnKonversi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                konversiSuhu();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetFields();
            }
        });
    }

    private void konversiSuhu() {
        String suhuInput = etSuhu.getText().toString().trim();

        if (suhuInput.isEmpty()) {
            Toast.makeText(MainActivity.this, "Masukkan suhu yang valid", Toast.LENGTH_SHORT).show();
            return;
        }

        double suhu = Double.parseDouble(suhuInput);

        String inputUnit = spOpsiInput.getSelectedItem().toString().split(" ")[0];
        String outputUnit = spOpsiOutput.getSelectedItem().toString().split(" ")[0];

        double hasilKonversi = 0;
        String rumus = "";

        if (inputUnit.equals("Celsius") && outputUnit.equals("Fahrenheit")) {
            hasilKonversi = (suhu * 9 / 5) + 32;
            rumus = "F = (C * 9/5) + 32\nF = (" + suhu + " * 9/5) + 32\nF = " + hasilKonversi;
        } else if (inputUnit.equals("Celsius") && outputUnit.equals("Kelvin")) {
            hasilKonversi = suhu + 273.15;
            rumus = "K = C + 273.15\nK = " + suhu + " + 273.15\nK = " + hasilKonversi;
        } else if (inputUnit.equals("Celsius") && outputUnit.equals("Reaumur")) {
            hasilKonversi = suhu * 4 / 5;
            rumus = "R = C * 4/5\nR = " + suhu + " * 4/5\nR = " + hasilKonversi;
        } else if (inputUnit.equals("Fahrenheit") && outputUnit.equals("Celsius")) {
            hasilKonversi = (suhu - 32) * 5 / 9;
            rumus = "C = (F - 32) * 5/9\nC = (" + suhu + " - 32) * 5/9\nC = " + hasilKonversi;
        } else if (inputUnit.equals("Fahrenheit") && outputUnit.equals("Kelvin")) {
            hasilKonversi = (suhu - 32) * 5 / 9 + 273.15;
            rumus = "K = (F - 32) * 5/9 + 273.15\nK = (" + suhu + " - 32) * 5/9 + 273.15\nK = " + hasilKonversi;
        } else if (inputUnit.equals("Fahrenheit") && outputUnit.equals("Reaumur")) {
            hasilKonversi = (suhu - 32) * 4 / 9;
            rumus = "R = (F - 32) * 4/9\nR = (" + suhu + " - 32) * 4/9\nR = " + hasilKonversi;
        } else if (inputUnit.equals("Kelvin") && outputUnit.equals("Celsius")) {
            hasilKonversi = suhu - 273.15;
            rumus = "C = K - 273.15\nC = " + suhu + " - 273.15\nC = " + hasilKonversi;
        } else if (inputUnit.equals("Kelvin") && outputUnit.equals("Fahrenheit")) {
            hasilKonversi = (suhu - 273.15) * 9 / 5 + 32;
            rumus = "F = (K - 273.15) * 9/5 + 32\nF = (" + suhu + " - 273.15) * 9/5 + 32\nF = " + hasilKonversi;
        } else if (inputUnit.equals("Kelvin") && outputUnit.equals("Reaumur")) {
            hasilKonversi = (suhu - 273.15) * 4 / 5;
            rumus = "R = (K - 273.15) * 4/5\nR = (" + suhu + " - 273.15) * 4/5\nR = " + hasilKonversi;
        } else if (inputUnit.equals("Reaumur") && outputUnit.equals("Celsius")) {
            hasilKonversi = suhu * 5 / 4;
            rumus = "C = R * 5/4\nC = " + suhu + " * 5/4\nC = " + hasilKonversi;
        } else if (inputUnit.equals("Reaumur") && outputUnit.equals("Fahrenheit")) {
            hasilKonversi = (suhu * 9 / 4) + 32;
            rumus = "F = (R * 9/4) + 32\nF = (" + suhu + " * 9/4) + 32\nF = " + hasilKonversi;
        } else if (inputUnit.equals("Reaumur") && outputUnit.equals("Kelvin")) {
            hasilKonversi = (suhu * 5 / 4) + 273.15;
            rumus = "K = (R * 5/4) + 273.15\nK = (" + suhu + " * 5/4) + 273.15\nK = " + hasilKonversi;
        } else {
            hasilKonversi = suhu;
            rumus = "Suhu sudah dalam satuan yang diinginkan";
        }

        inputUnit = inputUnit.equals("Celsius") ? "°" :
                inputUnit.equals("Fahrenheit") ? "°" :
                        inputUnit.equals("Kelvin") ? "°" :
                                inputUnit.equals("Reaumur") ? "°" : "";

        outputUnit = outputUnit.equals("Celsius") ? "°" :
                outputUnit.equals("Fahrenheit") ? "°" :
                        outputUnit.equals("Kelvin") ? "°" :
                                outputUnit.equals("Reaumur") ? "°" : "";

        etHasilKonversi.setText(String.format("%.2f %s", hasilKonversi, outputUnit));
        tvHasilHitung.setVisibility(View.VISIBLE);
        layoutHasil.setVisibility(View.VISIBLE);
        tvConvertRumus.setText("Rumus:");
        tvRumusnya.setText(rumus);
    }

    private void resetFields() {
        etSuhu.setText("");
        etHasilKonversi.setText("");
        spOpsiInput.setSelection(0);
        spOpsiOutput.setSelection(0);
        tvHasilHitung.setVisibility(View.GONE);
        layoutHasil.setVisibility(View.GONE);
    }
}
