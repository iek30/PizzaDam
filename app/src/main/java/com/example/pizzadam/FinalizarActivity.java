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
import android.widget.Toast;

public class FinalizarActivity extends AppCompatActivity{

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
        TareaAsincrona t = new TareaAsincrona();
        t.execute();
        dialog.show();

    }

    public void mensaje(String m) throws InterruptedException {
        Thread.sleep(2000);
        Toast.makeText(this, m, Toast.LENGTH_SHORT).show();
    }

    String m = "Cocinando...";

    private class TareaAsincrona extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            // No se necesita hacer nada aquí, ya que se ejecuta en segundo plano
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mensaje(m);
                        m = "Terminando...";
                        mensaje(m);
                        m = "Repartiendo...";
                        mensaje(m);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
    }


}