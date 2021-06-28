package br.edu.ifsp.arq.minhastarefas.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.edu.ifsp.arq.minhastarefas.R;
import br.edu.ifsp.arq.minhastarefas.constantes.Constantes;



public class TarefaActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mTitleEditText;
    private EditText mDateEditText;
    private Button mButton;

    private String mOldTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mTitleEditText = findViewById(R.id.edit_titulo);
        mDateEditText = findViewById(R.id.edit_date);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mOldTitle = bundle.getString(Constantes.KEY_TITLE);
            mTitleEditText.setText(mOldTitle);

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String dataEntrada = bundle.getString(Constantes.KEY_DATA);
            Date data = null;
            try {
                data = formato.parse(dataEntrada);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            mDateEditText.setText(formato.format(data));
        } else {
            mOldTitle = "";
        }

        mButton = findViewById(R.id.button_save);
        mButton.setOnClickListener(this);
}

    @Override
    public void onClick(View view) {

        if (view == mButton) {
          //  if (!validaCampoTitulo()) {
          if (!validaCampoTitulo() && !validaCampoData()) {

                Intent intent = new Intent();
                intent.putExtra(Constantes.KEY_TITLE, mTitleEditText.getText().toString().trim());
                intent.putExtra(Constantes.KEY_DATA, mDateEditText.getText().toString());
                intent.putExtra(Constantes.KEY_OLD_TITLE, mOldTitle);
                setResult(Activity.RESULT_OK, intent);
                Toast.makeText(this, getString(R.string.success_message), Toast.LENGTH_SHORT).show();
                finish();

            } else {

                Toast.makeText(this, "campo de titulo ou data invalidos", Toast.LENGTH_SHORT).show();
            }
        }

    }



    private boolean validaCampoTitulo() {

        return  mTitleEditText.getText().toString().trim().isEmpty();
    }
    private boolean validaCampoData() {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        formato.setLenient(false);
        int cont = 0;
        try {
            formato.parse(mDateEditText.getText().toString());
        } catch (ParseException e) {
            cont++;
            e.printStackTrace();
        }

        char[] array = mDateEditText.getText().toString().toCharArray();
        return array[6] == 'y' || cont > 0;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            setResult(RESULT_CANCELED);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}