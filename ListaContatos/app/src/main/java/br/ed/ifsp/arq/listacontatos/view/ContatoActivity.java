package br.ed.ifsp.arq.listacontatos.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ed.ifsp.arq.listacontatos.R;
import br.ed.ifsp.arq.listacontatos.constantes.Constantes;

public class ContatoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mNomeEditText;
    private EditText mApelidoEditText;
    private EditText mTelefoneEditText;
    private EditText mEmailEditText;

    private Button mButton;
    private String mOldNome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mNomeEditText = findViewById(R.id.edit_nome);
        mApelidoEditText = findViewById(R.id.edit_apelido);
        mTelefoneEditText = findViewById(R.id.edit_telefone);
        mEmailEditText = findViewById(R.id.edit_email);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mOldNome = bundle.getString(Constantes.KEY_NOME);
            String a = bundle.getString(Constantes.KEY_APELIDO);
            String t = bundle.getString(Constantes.KEY_TELEFONE);
            String e = bundle.getString(Constantes.KEY_EMAIL);
            mNomeEditText.setText(mOldNome);
            mApelidoEditText.setText(a);
            mTelefoneEditText.setText(t);
            mEmailEditText.setText(e);

        } else {
            mOldNome = "";
        }

        mButton = findViewById(R.id.button_save);
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mButton) {
            Intent intent = new Intent();
            intent.putExtra(Constantes.KEY_NOME, mNomeEditText.getText().toString());
            intent.putExtra(Constantes.KEY_APELIDO, mApelidoEditText.getText().toString());
            intent.putExtra(Constantes.KEY_TELEFONE, mTelefoneEditText.getText().toString());
            intent.putExtra(Constantes.KEY_EMAIL, mEmailEditText.getText().toString());
            intent.putExtra(Constantes.KEY_OLD_NOME, mOldNome);
            setResult(Activity.RESULT_OK, intent);
            Toast.makeText(this, getString(R.string.success_message), Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            setResult(RESULT_CANCELED);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

