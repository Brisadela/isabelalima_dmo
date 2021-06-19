package br.ed.ifsp.arq.tempoestado;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tempoexecucaoTextView;
    private TextView tempoparadoTextView;
    private TextView tempopausadoTextView;
    private Button mbuttontexecucao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tempoexecucaoTextView = findViewById(R.id.view_tempo_criacao);
        tempoparadoTextView = findViewById(R.id.view_tempo_parado);
        tempopausadoTextView = findViewById(R.id.view_tempo_pausado);

        mbuttontexecucao = findViewById(R.id.button_Execucao);
        mbuttontexecucao.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == mbuttontexecucao ){
            tempoexecucaoTextView.setText("eh neh");
        }
        /*então : não consegui compreeder o que foi pedido
         percebi a relação que Bundle e savedInstanceState, ajudariam
         na manipulaçõa pra recuperar essas mudanças de estado, olhando desta maneira
         não consegui ver essa posibilidade */
    }

}

/*
App não foi implementado.
Nota: 2.0
 */