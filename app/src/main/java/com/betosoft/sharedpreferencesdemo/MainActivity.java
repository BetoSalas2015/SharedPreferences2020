package com.betosoft.sharedpreferencesdemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.service.autofill.TextValueSanitizer;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    SharedPreferences misDatos;
    EditText editNombre, editCoordX, edtReporte, editCoordY;
    String nombre;
    Float x, y;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editNombre = findViewById(R.id.edtNombre);
        editCoordX = findViewById(R.id.edtCordX);
        editCoordY = findViewById(R.id.edtCoordY);
        edtReporte = findViewById(R.id.edtReporte);


        misDatos = getSharedPreferences("Preferencias", MODE_PRIVATE);

        nombre = misDatos.getString("nombre", "Valor por defecto");
        x = misDatos.getFloat("x", 0);
        y = misDatos.getFloat("y", 0);

        editNombre.setText(nombre);
        editCoordX.setText("" + x);
        editCoordY.setText("" + y);
        edtReporte.append("Preferencias Restauradas al crear la actividad.\n");
    }

    @Override
    protected void onPause() {
        super.onPause();

        nombre = editNombre.getText().toString();
        x = Float.parseFloat(editCoordX.getText().toString());
        y = Float.parseFloat(editCoordY.getText().toString());

        SharedPreferences.Editor miEditor = misDatos.edit();
        miEditor.putString("nombre", nombre);
        miEditor.putFloat("x", x);
        miEditor.putFloat("y", y);

        miEditor.apply();
        edtReporte.append("Preferencia Guardadas en pausa: " + "Nombre: " + nombre + "X = " + x + ", Y = "+ y + "\n");
        Toast.makeText(this, "Preferencias Guardadas", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        misDatos = getSharedPreferences("Preferencias", MODE_PRIVATE);

        nombre = misDatos.getString("nombre", "Valor por defecto");
        x = misDatos.getFloat("x", 0);
        y = misDatos.getFloat("y", 0);

        edtReporte.append("Preferencias Restauradas al restaurar la actividad.\n");
    }
}