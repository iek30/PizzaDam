package com.example.pizzadam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void volver(View v){
        startActivity(new Intent(MenuActivity.this,MainActivity.class));
    }

    public void abrirWeb(View v){
        String url = "https://macanafood.com/index.php/home";
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    public void comenzarPedido(View v){
        startActivity(new Intent(MenuActivity.this,PedidoActivity.class));
    }
}