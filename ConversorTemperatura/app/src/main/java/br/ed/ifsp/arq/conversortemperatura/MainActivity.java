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
    private Button mConverterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTemperaturaText = findViewById(R.id.text_temperatura);
        mConversaoView = findViewById(R.id.view_saida_Conversao);
        mConverterButton = findViewById(R.id.button_converterC);

        mConverterButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view == mConverterButton) {
            converterCelsius();
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
}