package org.esei.dm.calcularletranif.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.esei.dm.calcularletranif.R;
import org.esei.dm.calcularletranif.core.CalculoLetraNif;

public class MainActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle appState) {
        super.onCreate( appState );
        this.setContentView( R.layout.activity_main );

        // Link to events
        Button calcular = (Button) this.findViewById(R.id.calculo);
        EditText edDni = (EditText) this.findViewById( R.id.edDni );//recoje el valor del dni
       /* edDni.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                MainActivity.this.calcula();
            }
        });*/
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcula();
            }
        });
    }

    private void calcula() {
        EditText edDni = (EditText) this.findViewById( R.id.edDni );
        TextView lblResult = (TextView) this.findViewById( R.id.lblResult );

        try {
            String value = edDni.getText().toString();
            int dni = Integer.parseInt( value );
            String result = value;
            result += Character.toString(CalculoLetraNif.calculaLetraNif(dni));
            lblResult.setText( result );
        }
        catch(NumberFormatException exc) {
            lblResult.setText( R.string.fallo );
            Toast.makeText( this, R.string.fallo, Toast.LENGTH_SHORT ).show();
        }
    }

    private int opc;
}
