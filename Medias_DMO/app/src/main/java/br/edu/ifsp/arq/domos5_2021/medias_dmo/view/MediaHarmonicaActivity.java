package br.edu.ifsp.arq.domos5_2021.medias_dmo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import br.edu.ifsp.arq.domos5_2021.medias_dmo.R;

public class MediaHarmonicaActivity extends AppCompatActivity {

    private TextView mMensagem;
    private Double mresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_harmonica);

        mMensagem  = findViewById(R.id.text_mensagemHar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle = getIntent().getExtras();

        double valores[] = bundle.getDoubleArray("Valores");
        double resul = bundle.getDouble("KEY_RESULH");

        if(resul != 0){
            mMensagem.setText("Media Harmonica "+ resul  +"\n\n"+"\n\n"+ "Numeros: "+ "\n\n"+valores[0]+"\n\n"+ valores[1]+"\n\n"+valores[2]+"\n\n"+valores[3]+"\n\n"+valores[4]);
        }else{
            mMensagem.setText("Não Valores para calculo");
        }


    }
}