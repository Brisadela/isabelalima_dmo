package br.ed.ifsp.arq.listacontatos.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.ed.ifsp.arq.listacontatos.R;
import br.ed.ifsp.arq.listacontatos.adapter.ItemContatoAdapter;
import br.ed.ifsp.arq.listacontatos.constantes.Constantes;
import br.ed.ifsp.arq.listacontatos.controller.ContatoController;
import br.ed.ifsp.arq.listacontatos.model.Contato;

public class MainActivity extends AppCompatActivity {

    private List<Contato> mContatos;
    private RecyclerView mContatosRecyclerView;
    private FloatingActionButton mActionButton;
    private ItemContatoAdapter mItemContatoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContatos = ContatoController.allContatos();

        mItemContatoAdapter = new ItemContatoAdapter(this, mContatos);
        mItemContatoAdapter.setClickListener(position -> updateContato(position));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mContatosRecyclerView = findViewById(R.id.recycler_contatos);
        mContatosRecyclerView.setLayoutManager(layoutManager);
        mContatosRecyclerView.setAdapter(mItemContatoAdapter);

        mActionButton = findViewById(R.id.fab_add_contato);
        mActionButton.setOnClickListener(v -> newContato());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constantes.REQUEST_CODE_NEW_CONTATO:
                    ContatoController.addContato(
                            data.getStringExtra(Constantes.KEY_NOME),
                            data.getStringExtra(Constantes.KEY_APELIDO),
                            data.getStringExtra(Constantes.KEY_TELEFONE),
                            data.getStringExtra(Constantes.KEY_EMAIL)
                    );
                    break;
                case Constantes.REQUEST_CODE_UPDATE_CONTATO:
                    String o = data.getStringExtra(Constantes.KEY_OLD_NOME);
                    String n = data.getStringExtra(Constantes.KEY_NOME);
                    String a = data.getStringExtra(Constantes.KEY_APELIDO);
                    String t = data.getStringExtra(Constantes.KEY_TELEFONE);
                    String e = data.getStringExtra(Constantes.KEY_EMAIL);
                    ContatoController.updateContato(o, n, a, t, e);
                    break;
            }
            updateAdapter();
        }
    }

    private void newContato() {
        Intent intent = new Intent(this, ContatoActivity.class);
        startActivityForResult(intent, Constantes.REQUEST_CODE_NEW_CONTATO);
    }

    private void updateContato(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(Constantes.KEY_NOME, mContatos.get(position).getNome());
        bundle.putString(Constantes.KEY_APELIDO, mContatos.get(position).getApelido());
        bundle.putString(Constantes.KEY_TELEFONE, mContatos.get(position).getTelefone());
        bundle.putString(Constantes.KEY_EMAIL, mContatos.get(position).getEmail());

        Intent intent = new Intent(this, ContatoActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, Constantes.REQUEST_CODE_UPDATE_CONTATO);
    }

    private void updateAdapter() {
        mItemContatoAdapter.notifyDataSetChanged();
    }
}