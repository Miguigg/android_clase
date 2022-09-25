package com.example.ej4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner s1;
    TextView consumo,distancia,tiempo,res;
    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s1 = findViewById(R.id.s1);
        String [] operaciones = {"autopista","nacional","comarcal"};//lista para el spinner
        ArrayAdapter<String> adapter1=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,operaciones);
        s1.setAdapter(adapter1);
        b1 = findViewById(R.id.tiempo_empleado);
        b2 = findViewById(R.id.consumo_total);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tiempoTotal();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consumo();
            }
        });
    }
    public void tiempoTotal(){
        distancia = findViewById(R.id.distancia);
        double result;
        res = findViewById(R.id.res);
        try {
            String via = s1.getSelectedItem().toString();
            double valorDist =  Double.parseDouble(distancia.getText().toString());
            switch (via){
                case "autopista":
                    result = valorDist / 100;
                    res.setText("El resulado es "+String.valueOf(result) + "hora(s)");
                    break;
                case "nacional":
                    result = valorDist / 80;
                    res.setText("El resulado es "+String.valueOf(result) + "hora(s)");
                    break;
                case "comarcal":
                    result = valorDist / 50;
                    res.setText("El resulado es "+String.valueOf(result) + "hora(s)");
                    break;
            }
        }catch (Exception e) {
            Toast.makeText(this,"Debes introducir un numero en distancia" + e,Toast.LENGTH_LONG).show();
        }
    }
    public void consumo(){
        tiempo = findViewById(R.id.tiempo_empleado);
        consumo = findViewById(R.id.consumo_total);
        double result;

    }
}