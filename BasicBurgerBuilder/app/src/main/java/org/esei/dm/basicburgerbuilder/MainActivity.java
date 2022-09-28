package org.esei.dm.basicburgerbuilder;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private String[] lugares = {"Terraza 1€","Barra 0.25€"};
    private String[] descuentos = {"Normal","Socio"};
    private BurgerBuilderConfigurator myBurgerBuilderConfigurator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myBurgerBuilderConfigurator = new BurgerBuilderConfigurator();

        Button button = findViewById(R.id.buttonIngredients);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showIngredientsDialog();
            }
        });
    }

    private void showIngredientsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);//Creamos el objeto de tipo alertDialog
        builder.setTitle("Select ingredients:");//asignamos titulo
        builder.setMultiChoiceItems(BurgerBuilderConfigurator.INGREDIENTS,//confguramos el contenido
                myBurgerBuilderConfigurator.getSelectedIngredients(),
                new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        myBurgerBuilderConfigurator.getSelectedIngredients()[which] = isChecked;

                    }
                });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                lugarConsumo();
            }
        });

        builder.create().show();
    }

    private void updateTotalCost(double lugar,double desc) {
        TextView totalTextView = findViewById(R.id.textViewTotal);
        double total = myBurgerBuilderConfigurator.calculatePrice() + lugar;
        double resta = total * desc;
        total = total - resta;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        totalTextView.setText(decimalFormat.format(total)+"€");
    }

    private void lugarConsumo(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.lugar);
        LayoutInflater inf =  getLayoutInflater();
        View v = inf.inflate(R.layout.select,null);
        builder.setView(v)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        double toret;
                       RadioButton r1 = v.findViewById(R.id.barra);
                       RadioButton r2 = v.findViewById(R.id.terraza);
                       if(r1.isChecked()){
                           Toast.makeText(MainActivity.this,"Pagas 0.25€ mas",Toast.LENGTH_LONG).show();
                           toret = 0.25;
                           descuentos(toret);
                       }else{
                           Toast.makeText(MainActivity.this,"Pagas 1€ mas",Toast.LENGTH_LONG).show();
                           toret = 0.1;
                           descuentos(toret);
                       }
                    }
                });
        builder.create().show();
    }
    private void descuentos(double lugar){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.desc);
        builder.setSingleChoiceItems(descuentos, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String opt = descuentos[i];
                if(opt.equals("Normal")){
                    updateTotalCost(lugar,0);
                }else{
                    updateTotalCost(lugar,0.10);
                }
            }
        });

    }
}