package com.example.pizzadam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class PersonalizadaActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    ArrayList<String> ingredientes = new ArrayList<>();
    private int checkBoxCounter = 0;
    String[] bases = {"Tomate","Carbonara","BBQ"};
    String[] tamanos = {"PEQUEÑA","MEDIANA","FAMILIAR"};

    private Pizza pizza;
    String base = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalizada);

        Spinner spinner = findViewById(R.id.spinnerBase);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bases);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Spinner spinner2 = findViewById(R.id.spinnerTamano);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tamanos);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        Intent i = getIntent();

        if (i.hasExtra("pizza")) {
            base = "Tomate";
            pizza = (Pizza) i.getSerializableExtra("pizza");

            Button btn = findViewById(R.id.btnPrecioAlGusto);
            btn.setText(pizza.getPrecio() + "€");


            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    base = bases[i];
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    pizza.setTamano(tamanos[i]);
                    pizza.ajustarPrecio();
                    btn.setText(pizza.getPrecio() + "€");
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }

        linearLayout = findViewById(R.id.tuLinearLayout);

        for (int j = 0; j < linearLayout.getChildCount(); j++) {
            View view = linearLayout.getChildAt(j);
            if (view instanceof CheckBox) {
                final CheckBox checkBox = (CheckBox) view;

                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        if (isChecked) {
                            if (checkBoxCounter >= 3) {
                                checkBox.setChecked(false);
                            } else {
                                checkBoxCounter++;
                                ingredientes.remove(checkBox.getText()+"");
                            }
                        } else {
                            checkBoxCounter--;
                            ingredientes.add(checkBox.getText()+"");
                        }
                    }
                });
            }
        }

    }

    public void salir(View v){
        finish();
    }

    public void nuevaPizza(View v){
        pizza.ajustarPrecio();

        ingredientes.add(0,base);
        pizza.setIngredientes(ingredientes);

        Servicio.getInstance().getPedido().add(pizza);
        Toast.makeText(this, "Pizza añadida a tu pedido.", Toast.LENGTH_LONG).show();
        finish();
    }
}