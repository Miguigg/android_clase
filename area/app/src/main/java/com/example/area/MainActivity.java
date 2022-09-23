package com.example.area;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText e1;
    EditText e2;
    Button btn;
    TextView res;
    Spinner sp1;
    TextView dg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.calc);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcular();
            }
        });
        sp1 = findViewById(R.id.sp1);
        String [] opt = {"celsius","farenheit"};
        ArrayAdapter<String>adapter1=new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,opt);
        sp1.setAdapter(adapter1);
        dg = findViewById(R.id.degrees);
        dg.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                transforma();
            }
        });
    }
    public void calcular(){
        try {
            e1 = findViewById(R.id.ancho);
            e2 = findViewById(R.id.largo);
            res = findViewById(R.id.res);
            double result = (Integer.parseInt((e1.getText().toString()))) * (Integer.parseInt((e2.getText().toString()))) ;
            res.setText(String.valueOf(result));
        }catch (Exception e){
            Toast.makeText(this,"Los dos campos deben estar cubiertos",Toast.LENGTH_LONG).show();
        }
    }
    public void transforma(){
        String opcion = sp1.getSelectedItem().toString();
        double valor = Double.parseDouble((dg.getText().toString()));
        double result = 0;
        try {
            switch (opcion) {
                case "celsius":
                    result = (valor - 32) * 5;
                    break;
                case "farenheit":
                    result = (valor * (9 / 5) + 32);
                    break;
            }
            //Toast.makeText(this,, Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(this,"Los dos campos deben estar cubiertos",Toast.LENGTH_LONG).show();
        }
    }
}