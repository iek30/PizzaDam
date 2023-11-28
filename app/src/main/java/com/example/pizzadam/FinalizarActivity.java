package com.example.pizzadam;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

public class FinalizarActivity extends AppCompatActivity implements Runnable{

    final int duracion = 5000; // 5 segundos en milisegundos
    final int intervalo = 50; // Intervalo para actualizar la barra de progreso
    final int paso = (int) (((float) intervalo / duracion) * 100);
    ArrayAdapter arrayAdapter;
    ListView lista;
    Button btn;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar);

        if (Servicio.getInstance().getPedido().isEmpty()){
            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Servicio.getInstance().getUltima());
            lista = findViewById(R.id.listaFinalizar);
            lista.setAdapter(arrayAdapter);
            btn = findViewById(R.id.btnFinalizar);
            btn.setText("Pagar " + Servicio.getInstance().obtenerPrecio(Servicio.getInstance().getUltima().get(0))+"€");
        }
        else{
            arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Servicio.getInstance().getPedido());
            lista = findViewById(R.id.listaFinalizar);
            lista.setAdapter(arrayAdapter);
            btn = findViewById(R.id.btnFinalizar);
            btn.setText("Pagar " + Servicio.getInstance().calcularTotal()+"€");
        }


        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                showDeleteConfirmationDialog(position);
                return true;
            }
        });
    }

    public void salir(View v){
        finish();
    }

    private void showDeleteConfirmationDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("¡Atención!");
        builder.setMessage("¿Estás seguro de que deseas eliminar esta pizza?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Eliminar el elemento del ArrayList
                Servicio.getInstance().getPedido().remove(position);
                // Notificar al adapter que los datos han cambiado
                arrayAdapter.notifyDataSetChanged();
                btn.setText("Pagar " + Servicio.getInstance().calcularTotal()+"€");
                if (Servicio.getInstance().getPedido().size()<1) finish();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Cerrar el diálogo sin hacer nada
                dialog.dismiss();
            }
        });

        // Crea y muestra el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void finalizar(View v){

        Servicio.getInstance().crearPedido();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("El pedido se ha realizado correctamente.");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Si el usuario hace clic en "Sí", aquí puedes agregar la lógica para continuar
                Servicio.getInstance().crearPedido();
                finish();
            }
        });

        // Crea y muestra el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
        new Thread(this).start();

    }

    private void mostrarVentanaEmergente() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Tu lógica para mostrar el diálogo y actualizar la barra de progreso
                final Dialog dialog = new Dialog(FinalizarActivity.this);
                // ... (resto del código)

                // En lugar de usar directamente progressBar.setProgress(progreso);
                // utiliza runOnUiThread para actualizar la barra de progreso en el hilo principal

                final ProgressBar progressBar = dialog.findViewById(R.id.progressBar);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int progreso = progressBar.getProgress() + paso;
                        if (progreso <= 100) {
                            // Actualizar la barra de progreso en el hilo principal
                            final int finalProgreso = progreso;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(finalProgreso);
                                }
                            });
                            handler.postDelayed(this, intervalo);
                        } else {
                            dialog.dismiss(); // Cerrar la ventana emergente al completarse el progreso
                        }
                    }
                }, intervalo);

                dialog.show();
            }
        });
    }


    @Override
    public void run() {
        mostrarVentanaEmergente();
    }
}