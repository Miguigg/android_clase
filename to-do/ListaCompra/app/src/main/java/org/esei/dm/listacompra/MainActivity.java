package org.esei.dm.listacompra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.esei.dm.listacompra.mode.productos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
  private ArrayAdapter<productos> itemsAdapter;
  private ArrayList<productos> items;
  private String[] opt = {"Seguro", "Cancelar"};

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    this.items = new ArrayList<productos>();
    this.itemsAdapter =
        new ArrayAdapter<productos>(
            MainActivity.this, android.R.layout.simple_list_item_1, this.items);
            
    Button btAdd = (Button) this.findViewById(R.id.btAdd);
    ListView lvItems = (ListView) this.findViewById(R.id.lvItems);

    lvItems.setLongClickable(true);


    // creamos el evento para el longclick
    lvItems.setAdapter(this.itemsAdapter);

    lvItems.setOnItemLongClickListener(
        new AdapterView.OnItemLongClickListener() {
          @Override
          public boolean onItemLongClick(AdapterView<?> adapterView, View view, int pos, long l) {
            showPopUp(pos);
            return false;
          }
        });

    // Creamos el evento para el click simple
    lvItems.setOnItemClickListener(
        new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
            updateElement(pos);
          }
        });
    btAdd.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            MainActivity.this.onAdd();
          }
        });
  }

  @Override
  public void onResume() {

    super.onResume();
      try {
          testDates();
      } catch (ParseException e) {
          e.printStackTrace();
      }
  }

    private void testDates() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        String str = formatter.format(date);

        Date actual = formatter.parse(str);

        for(int i = 0; i<items.size();i++){
            Date temp = items.get(i).getFecha();
            if(temp.compareTo(actual)>0){
                MainActivity.this.items.remove(i);
                MainActivity.this.itemsAdapter.notifyDataSetChanged();
                MainActivity.this.updateStatus();
            }
        }
    }

    private void updateElement(int pos) {
    LayoutInflater inf = getLayoutInflater();
    View v = inf.inflate(R.layout.popup, null);
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("A comprar...");
    builder.setView(v);
    builder.setPositiveButton(
        "+",
        new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            // borramos la anterior
            MainActivity.this.items.remove(pos);
            MainActivity.this.itemsAdapter.notifyDataSetChanged();
            MainActivity.this.updateStatus();

            // metemos la nueva
            EditText et = v.findViewById(R.id.nombre);
            EditText date = v.findViewById(R.id.date);
            EditText cantidad = v.findViewById(R.id.cantidad);



              String pattern = "MM-dd-yyyy";
              SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
              Date formatDate = null;
              try {
                  formatDate = simpleDateFormat.parse(date.getText().toString());
              } catch (ParseException e) {
                  Toast.makeText(
                                  MainActivity.this, "El formato de la fecha no es correcto", Toast.LENGTH_LONG)
                          .show();
                  dialogInterface.dismiss();
              }

            int numero = Integer.parseInt(cantidad.getText().toString());
            String text = et.getText().toString();
            String fecha = date.toString();

            productos p = new productos(text, numero , formatDate);

            MainActivity.this.itemsAdapter.add(p);
            MainActivity.this.itemsAdapter.notifyDataSetChanged();
            MainActivity.this.updateStatus();
          }
        });
    builder.setNegativeButton("Cancel", null);
    builder.create().show();
  }

  private void showPopUp(int pos) {
    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
    builder.setTitle("Estas seguro?");
    builder.setSingleChoiceItems(
        opt,
        -1,
        new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            String selected = opt[i];
            if (selected.equals("Seguro")) {
              if (pos >= 0) {
                MainActivity.this.items.remove(pos);
                MainActivity.this.itemsAdapter.notifyDataSetChanged();
                MainActivity.this.updateStatus();
                Toast.makeText(MainActivity.this, "Borrado", Toast.LENGTH_LONG).show();
                dialogInterface.dismiss();
              }
            } else {
              Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
              dialogInterface.dismiss();
            }
          }
        });
    builder.create().show();
  }

  private void onAdd() {
    LayoutInflater inf = LayoutInflater.from(MainActivity.this);
    View v = inf.inflate(R.layout.popup, null);
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle("A comprar...");
    builder.setView(v);
    builder.setPositiveButton(
        "+",
        new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            EditText et = v.findViewById(R.id.nombre);
            EditText date = v.findViewById(R.id.date);
            EditText cantidad = v.findViewById(R.id.cantidad);

            String pattern = "MM/dd/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            Date formatDate = null;
            try {
              formatDate = simpleDateFormat.parse(date.getText().toString());
            } catch (ParseException e) {
              Toast.makeText(
                      MainActivity.this, "El formato de la fecha no es correcto", Toast.LENGTH_LONG)
                  .show();
              dialogInterface.dismiss();
            }
            int numero = Integer.parseInt(cantidad.getText().toString());
            String text = et.getText().toString();
            productos p = new productos(text, numero, formatDate);
            items.add(p);
            MainActivity.this.itemsAdapter.notifyDataSetChanged();
            MainActivity.this.updateStatus();
          }
        });
    builder.setNegativeButton("-", null);
    builder.create().show();
  }

  private void updateStatus() {
    TextView txtNum = (TextView) this.findViewById(R.id.lblNum);
    txtNum.setText(Integer.toString(this.itemsAdapter.getCount()));
  }
}
