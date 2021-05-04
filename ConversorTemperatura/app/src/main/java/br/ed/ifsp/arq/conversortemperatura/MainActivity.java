package br.ed.ifsp.arq.conversortemperatura;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText mTemperaturaText;
    private TextView mConversaoView;
    private Button mConverterFButton;
    private Button mConverterCButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTemperaturaText = findViewById(R.id.text_temp);
        mConversaoView = findViewById(R.id.view_saida_Conversao);

        mConverterCButton = findViewById(R.id.button_converterC);
        mConverterCButton.setOnClickListener(this);

        mConverterFButton = findViewById(R.id.button_converterF);
        mConverterFButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view == mConverterCButton) {
            converterCelsius();
        }else {
            converterFahrenheit();
        }
    }
    private void converterCelsius(){
    double temperaturaF, celcius;
        try {
            temperaturaF = Double.valueOf(mTemperaturaText.getText().toString());
        }catch (Exception ex){
            temperaturaF = 0.0;
        }
        if(temperaturaF != 0.0){
            celcius = (temperaturaF - 32)/1.8;
            mConversaoView.setText(String.format("%.2f %s", celcius,"°C "));
        }else {
            mConversaoView.setText("Dados informados são inválidos.");

        }
    }
    private void converterFahrenheit(){
        double temperaturaC, Fahrenheit;
        try {
            temperaturaC = Double.valueOf(mTemperaturaText.getText().toString());
        }catch (Exception ex){
            temperaturaC = 0.0;
        }
        if(temperaturaC != 0.0){
            Fahrenheit = (temperaturaC * 1.8)+ 32;
            mConversaoView.setText(String.format("%.2f %s", Fahrenheit,"°F "));
        }else {
            mConversaoView.setText("Dados informados são inválidos.");

        }
    }
}