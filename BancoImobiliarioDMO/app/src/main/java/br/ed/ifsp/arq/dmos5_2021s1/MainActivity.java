package br.ed.ifsp.arq.dmos5_2021s1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mQtdJogadoresText;
    private TextView mSaidaInicioView;
    private Button mIniciarButton;
    private LinearLayout mConteudoInicial;
    private LinearLayout mConteudoPosInicio;
    private LinearLayout mConteudoDentro;
    private EditText mDadoJogadorInicialText;
    private EditText mDadoJogadorOutoText;
    private EditText mValorText;
    private Button mOkButton;
    private Button mMButton;
    private Button mKButton;

    private final Double MAX = 25.000;



   private ArrayList<Double> list;

    public MainActivity() {
        list = new ArrayList <Double>();
    }

    double j1 = MAX;
    double j2 = MAX;
    double j3 = MAX;
    double j4 = MAX;
    double j5 = MAX;
    double j6 = MAX;

    int jogador1 ;
    int jogador2;
    double valor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQtdJogadoresText = findViewById(R.id.text_qtdJogadores);
        mSaidaInicioView = findViewById(R.id.view_saidaInicio);
        mIniciarButton = findViewById(R.id.button_iniciar);
        mConteudoInicial = findViewById(R.id.ll_conteudoInicial);
        mConteudoPosInicio = findViewById(R.id.l2_conteudoPos);
        mConteudoDentro = findViewById(R.id.l3_conteudoDentro);

        mDadoJogadorOutoText = findViewById(R.id.text_JogadorOutro);
        mDadoJogadorInicialText =  findViewById(R.id.text_JogadorInicial);
        mValorText = findViewById(R.id.text_valor);
        mOkButton = findViewById(R.id.button_ok);
        mMButton = findViewById(R.id.button_m);
        mKButton = findViewById(R.id.button_k);

        mIniciarButton.setOnClickListener(this);
        mOkButton.setOnClickListener(this);
        mMButton.setOnClickListener(this);
        mKButton.setOnClickListener(this);

        mConteudoDentro.setVisibility(View.INVISIBLE);
        mConteudoPosInicio.setVisibility(View.INVISIBLE);

    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_iniciar:
                iniciarJogadores();
                mConteudoInicial.setVisibility(View.INVISIBLE);
                mConteudoDentro.setVisibility(View.VISIBLE);


                break;
            case R.id.button_ok:

                registroJogadores();
                mConteudoDentro.setVisibility(View.INVISIBLE);
                mConteudoPosInicio.setVisibility(View.VISIBLE);


                break;
            case R.id.button_m:

                transacoesMJogadores();
               // mSaidaInicioView.setText("entrou ");
                mConteudoPosInicio.setVisibility(View.VISIBLE);
                break;
            case R.id.button_k:
                transacoesKJogadores();

                mConteudoPosInicio.setVisibility(View.VISIBLE);
                break;
        }

    }
    private void iniciarJogadores() {
        int qtd = Integer.valueOf(mQtdJogadoresText.getText().toString());


        switch (qtd){
            case 2:

                     list.add(j1);
                     list.add(j2);

                mSaidaInicioView.setText("Todos iniciados com 25.000");
                break;
            case 3:

                     list.add(j1);
                     list.add(j2);
                     list.add(j3);

                mSaidaInicioView.setText("Todos iniciados com 25.000");
                break;
            case 4:

                     list.add(j1);
                     list.add(j2);
                     list.add(j3);
                     list.add(j4);
                mSaidaInicioView.setText("Todos iniciados com 25.000");
                break;
            case 5:


                     list.add(j1);
                     list.add(j2);
                     list.add(j3);
                     list.add(j4);
                     list.add(j5);
                mSaidaInicioView.setText("Todos iniciados com 25.000");
                break;
            case 6:
                     list.add(j1);
                     list.add(j2);
                     list.add(j3);
                     list.add(j4);
                     list.add(j5);
                     list.add(j6);
                mSaidaInicioView.setText("Todos iniciados com 25.000");
                break;
            default:
                mSaidaInicioView.setText("Não eh posivel iniciar: escolha de 2 á 6 jogadores .");
        }

    }

    private void registroJogadores() {

        mSaidaInicioView.setText("Dados registrado pra transações");
        jogador1 = Integer.valueOf(mDadoJogadorInicialText.getText().toString());
        jogador2 = Integer.valueOf(mDadoJogadorOutoText.getText().toString());
    }
    private void transacoesMJogadores(){
       // mSaidaInicioView.setText("entrou ");

        jogador1 = Integer.valueOf(mDadoJogadorInicialText.getText().toString());
        jogador2 = Integer.valueOf(mDadoJogadorOutoText.getText().toString());
        valor = Double.valueOf(mValorText.getText().toString());

        double aux = 0.0;
        double valorJ1 = 0.0 ;
        double valorJ2 = 0.0;
        double atual;
        int i;
        //não concluido/sem finalização
        for( i = 0; i < list.size(); i++) {
             // aux = list.get(i);
            if( list.indexOf(i) == jogador1){
                aux = list.get(i);
                valorJ1 = aux - valor;
                list.add(jogador1,valorJ1);
              // mSaidaInicioView.setText("entrou ");//não esta entrando
                //mSaidaInicioView.setText(String.format("%.2f", valorJ1));
            }
            //mSaidaInicioView.setText(String.format("%.2f ", aux));

        }

       // mSaidaInicioView.setText(String.format("%.f ", list.get(i)));
       // mSaidaInicioView.setText("entrou ");
        for( i = 0; i< list.size();i++){
            if(list.get(i) == jogador2){
                valorJ2 = list.get(i);
                valorJ2 += valorJ1;
                list.add(jogador2,valorJ2);
               // mSaidaInicioView.setText("entrou ");
            }
        }
       // mSaidaInicioView.setText(String.format("%.2f ", list.get(i)));
       // mSaidaInicioView.setText("entrou ");
    }
    private void transacoesKJogadores(){
        //não concluido/sem finalização
         mSaidaInicioView.setText("entrou ");
    }
}
