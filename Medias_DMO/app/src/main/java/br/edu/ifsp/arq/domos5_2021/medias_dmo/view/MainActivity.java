package br.edu.ifsp.arq.domos5_2021.medias_dmo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.edu.ifsp.arq.domos5_2021.medias_dmo.R;
import br.edu.ifsp.arq.domos5_2021.medias_dmo.controller.MediaController;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mNum1EditText;
    private EditText mNum2EditText;
    private EditText mNum3EditText;
    private EditText mNum4EditText;
    private EditText mNum5EditText;

    private Button mCalculaMediaAritimeticaButton;
    private Button mCalculaMediaHarmonicaButton;
    private Button mCalculaMediaPonderadaButton;


    //Referências para objetos de preferências do usuário
    private SharedPreferences mSharedPreferences;



    private MediaController mediaController;
    public double mresult;
    private List<Double> values;
    public double [] mNumEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //recuperando referencias do layout


        mNum1EditText = findViewById(R.id.edit_valor1);
        mNum2EditText = findViewById(R.id.edit_valor2);
        mNum3EditText = findViewById(R.id.edit_valor3);
        mNum4EditText = findViewById(R.id.edit_valor4);
        mNum5EditText = findViewById(R.id.edit_valor5);
        mCalculaMediaAritimeticaButton = findViewById(R.id.button_Media);
        mCalculaMediaHarmonicaButton = findViewById(R.id.button_MediaH);
        mCalculaMediaPonderadaButton = findViewById(R.id.button_MediaP);

        //configurando listener
        mCalculaMediaAritimeticaButton.setOnClickListener(this);
        mCalculaMediaHarmonicaButton.setOnClickListener(this);
       mCalculaMediaPonderadaButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view == mCalculaMediaAritimeticaButton){
              mediaAri();
        }else
            if(view == mCalculaMediaHarmonicaButton){
                mediaHar();
            }else
               if(view == mCalculaMediaPonderadaButton){
                mediaPon();
            }
    }

    private void mediaAri(){
        double num1,num2,num3,num4,num5;
        try {
            num1 = Double.valueOf(mNum1EditText.getText().toString()).doubleValue();
            num2 = Double.valueOf(mNum2EditText.getText().toString()).doubleValue();
            num3 = Double.valueOf(mNum3EditText.getText().toString()).doubleValue();
            num4 = Double.valueOf(mNum4EditText.getText().toString()).doubleValue();
            num5 = Double.valueOf(mNum5EditText.getText().toString()).doubleValue();

            mresult = mediaController.mediaArimetica(num1,num2,num3,num4,num5);
        }catch (NumberFormatException ex){
            num1 = 0.0;
            num2 = 0.0;
            num3 = 0.0;
            num4 = 0.0;
            num5 = 0.0;
        }
        if(mresult!= 0){
            Toast.makeText(this,getString(R.string.msg_Calculomedia_sucess)+ mresult, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,getString(R.string.msg_Calculomedia_erro), Toast.LENGTH_SHORT).show();
        }



        Bundle embrulho = new Bundle();
        embrulho.putDouble("KEY_NUM1", num1);
        embrulho.putDouble("KEY_NUM2", num2);
        embrulho.putDouble("KEY_NUM3", num3);
        embrulho.putDouble("KEY_NUM4", num4);
        embrulho.putDouble("KEY_NUM5", num5);
        embrulho.putDouble("KEY_RESUL", mresult);
        // Abrir a MediaAritimeticaActtivity
        Intent intencao = new Intent(this, MediaAritimeticaActivity.class);
        intencao.putExtras(embrulho);
        startActivity(intencao);
    }

    private void mediaHar(){
        double num1,num2,num3,num4,num5;
        values = new ArrayList<>();
       // int cont = 0;
        mNumEditText = new double[5];
        try {
            num1 = Double.valueOf(mNum1EditText.getText().toString()).doubleValue();
            num2 = Double.valueOf(mNum2EditText.getText().toString()).doubleValue();
            num3 = Double.valueOf(mNum3EditText.getText().toString()).doubleValue();
            num4 = Double.valueOf(mNum4EditText.getText().toString()).doubleValue();
            num5 = Double.valueOf(mNum5EditText.getText().toString()).doubleValue();


            mNumEditText[0] = num1;
            mNumEditText[1] = num2;
            mNumEditText[2] = num3;
            mNumEditText[3] = num4;
            mNumEditText[4] = num5;


            values.add(num1);
            values.add(num2);
            values.add(num3);
            values.add(num4);
            values.add(num5);

            mresult = mediaController.mediaHarmonica(values);
        }catch (NumberFormatException ex){
            num1 = 0.0;
            num2 = 0.0;
            num3 = 0.0;
            num4 = 0.0;
            num5 = 0.0;
        }

        Bundle embrulho = new Bundle();
        embrulho.putDoubleArray("Valores" ,mNumEditText);
        embrulho.putDouble("KEY_RESULH", mresult);
        // Abrir a MediaHarmonicaActtivity
        Intent intencao = new Intent(this, MediaHarmonicaActivity.class);
        intencao.putExtras(embrulho);
        startActivity(intencao);

        if(mresult!= 0){
            Toast.makeText(this,getString(R.string.msg_CalculoHarmonica_sucess)+ mresult, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,getString(R.string.msg_Calculomedia_erro), Toast.LENGTH_SHORT).show();
        }

    }

    private void mediaPon(){
        double num1,num2,num3,num4,num5;
        try{
            num1 = Double.valueOf(mNum1EditText.getText().toString()).doubleValue();
            num2 = Double.valueOf(mNum2EditText.getText().toString()).doubleValue();
            num3 = Double.valueOf(mNum3EditText.getText().toString()).doubleValue();
            num4 = Double.valueOf(mNum4EditText.getText().toString()).doubleValue();
            num5 = Double.valueOf(mNum5EditText.getText().toString()).doubleValue();



        }catch (NumberFormatException ex){
            num1 = 0.0;
            num2 = 0.0;
            num3 = 0.0;
            num4 = 0.0;
            num5 = 0.0;
        }
//        if(mresult == 0){
//            Toast.makeText(this,getString(R.string.msg_CalculoPonderada_sucess)+ mresult, Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(this,getString(R.string.msg_Calculomedia_erro), Toast.LENGTH_SHORT).show();
//        }

        //embrulho de dados para envio new Activity
        Bundle embrulho = new Bundle();
        embrulho.putDouble("KEY_NUMP1", num1);
        embrulho.putDouble("KEY_NUMP2", num2);
        embrulho.putDouble("KEY_NUMP3", num3);
        embrulho.putDouble("KEY_NUMP4", num4);
        embrulho.putDouble("KEY_NUMP5", num5);
        // Abrir a MediaPonderadaActtivity
        Intent intencao = new Intent(this, MediaPonderadaActivity.class);
        intencao.putExtras(embrulho);
        startActivity(intencao);
    }


}