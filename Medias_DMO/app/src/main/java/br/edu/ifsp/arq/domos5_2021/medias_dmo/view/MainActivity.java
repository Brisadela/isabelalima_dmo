package br.edu.ifsp.arq.domos5_2021.medias_dmo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


    private MediaController mediaController;
    private double mresult;
    private List<Double> values;

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

        //configurando listener
        mCalculaMediaAritimeticaButton.setOnClickListener(this);
        mCalculaMediaHarmonicaButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mCalculaMediaAritimeticaButton){
              mediaAri();
        }else
            if(view == mCalculaMediaHarmonicaButton){
                mediaHar();
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
    }

    private void mediaHar(){
        double num1,num2,num3,num4,num5;
        try {
            num1 = Double.valueOf(mNum1EditText.getText().toString()).doubleValue();
            num2 = Double.valueOf(mNum2EditText.getText().toString()).doubleValue();
            num3 = Double.valueOf(mNum3EditText.getText().toString()).doubleValue();
            num4 = Double.valueOf(mNum4EditText.getText().toString()).doubleValue();
            num5 = Double.valueOf(mNum5EditText.getText().toString()).doubleValue();

            values = new ArrayList<>();

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
        if(mresult!= 0){
            Toast.makeText(this,getString(R.string.msg_CalculoHarmonica_sucess)+ mresult, Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,getString(R.string.msg_Calculomedia_erro), Toast.LENGTH_SHORT).show();
        }
    }
}