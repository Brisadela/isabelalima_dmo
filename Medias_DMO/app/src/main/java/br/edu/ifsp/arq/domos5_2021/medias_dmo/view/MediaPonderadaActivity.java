package br.edu.ifsp.arq.domos5_2021.medias_dmo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.arq.domos5_2021.medias_dmo.R;
import br.edu.ifsp.arq.domos5_2021.medias_dmo.controller.MediaController;

import static br.edu.ifsp.arq.domos5_2021.medias_dmo.controller.MediaController.mediaPonderada;

public class MediaPonderadaActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mPeso1EditText;
    private TextView mPeso2EditText;
    private TextView mPeso3EditText;
    private TextView mPeso4EditText;
    private TextView mPeso5EditText;


    private Button mmCalculaMediaButton;
    private double num1,num2,num3,num4,num5;
    private double mresult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_ponderada);

        mPeso1EditText = findViewById(R.id.edit_peso1);
        mPeso2EditText = findViewById(R.id.edit_peso2);
        mPeso3EditText = findViewById(R.id.edit_peso3);
        mPeso4EditText = findViewById(R.id.edit_peso4);
        mPeso5EditText = findViewById(R.id.edit_peso5);
        mmCalculaMediaButton = findViewById(R.id.button_MediaPO);

        mmCalculaMediaButton.setOnClickListener(this);

        //recuperando o bundle de dados
        Bundle bundle = getIntent().getExtras();

         num1 = bundle.getDouble("KEY_NUMP1");
         num2 = bundle.getDouble("KEY_NUMP2");
         num3 = bundle.getDouble("KEY_NUMP3");
         num4 = bundle.getDouble("KEY_NUMP4");
         num5 = bundle.getDouble("KEY_NUMP5");
    }
    public void onClick(View view) {
        if (view == mmCalculaMediaButton){
            mediaPond();

        }
    }
    private void mediaPond(){
        int peso1, peso2,peso3,peso4,peso5;
        try {
            peso1 = Integer.valueOf(mPeso1EditText.getText().toString()).intValue();
            peso2 = Integer.valueOf(mPeso2EditText.getText().toString()).intValue();
            peso3 = Integer.valueOf(mPeso3EditText.getText().toString()).intValue();
            peso4 = Integer.valueOf(mPeso4EditText.getText().toString()).intValue();
            peso5 = Integer.valueOf(mPeso5EditText.getText().toString()).intValue();
        }catch (NumberFormatException ex){
            peso1 = 0;
            peso2 = 0;
            peso3 = 0;
            peso4 = 0;
            peso5 = 0;
        }
        mresult = MediaController.mediaPonderada(num1,num2,num3,num4,num5,peso1,peso2,peso3,peso4,peso5);
        if(mresult!= 0){
            Toast.makeText(this,getString(R.string.msg_CalculoPonderada_sucess)+ mresult+ "\n\n"+ num1+ "-->"+ peso1+"\n\n"+ num2+"-->"+peso2+"\n\n"+ num3+ "-->"+ peso3+ "\n\n"+ num4+ "-->"+ peso4+"\n\n"+ num5+ "-->"+ peso5, Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(this,getString(R.string.msg_Calculomedia_erro), Toast.LENGTH_SHORT).show();
        }
    }
}