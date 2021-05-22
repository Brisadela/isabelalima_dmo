package br.edu.ifsp.arq.domos5_2021.medias_dmo.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.edu.ifsp.arq.domos5_2021.medias_dmo.R;
import br.edu.ifsp.arq.domos5_2021.medias_dmo.controller.MediaController;


public class MediaAritimeticaActivity extends AppCompatActivity {

    private TextView  mMensagem;

    private double mresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_aritimetica);

        mMensagem  = findViewById(R.id.text_mensagemAri);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //recuperando o bundle de dados
        Bundle bundle = getIntent().getExtras();

        double num1 = bundle.getDouble("KEY_NUM1");
        double num2 = bundle.getDouble("KEY_NUM2");
        double num3 = bundle.getDouble("KEY_NUM3");
        double num4 = bundle.getDouble("KEY_NUM4");
        double num5 = bundle.getDouble("KEY_NUM5");
        double resul = bundle.getDouble("KEY_RESUL");

        mresult = MediaController.mediaArimetica(num1,num2,num3,num4,num5);
        if(mresult != 0){
            //mMensagem.setText(String.format("Media %.2f", resul));
            //mMensagem.setText("Não há calculo");
            mMensagem.setText("Media Aritimetica"+ resul + "\n\n"+ "Numeros: "+ num1+"\n\n"+ num2+"\n\n"+ num3+"\n\n"+num4+"\n\n "+num5);
           // string_resource = R.string.msg_Calculomedia_sucess +"Media"+ resul + "Numeros: " + num1+ num2+ num3+num4+num5;
        }else{
            mMensagem.setText("Não há calculo");
           // mMensagem.setText("Media Aritimetica"+ resul +"\n\n\" + Numeros: "+ num1+"Numeros: "+ num2+"/n Numeros: "+ num3+"/n Numeros: "+num4+"/n Numeros: "+num5);
           // mMensagem.setText(String.format("Media %.2f Numeros: %.2f %.2f %.2f %.2f %.2f", resul,num1));
           // mMensagem.setText("R.string.msg_Calculomedia_erro");
        }


    }

}