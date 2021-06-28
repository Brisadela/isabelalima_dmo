package br.edu.ifsp.arq.minhastarefas.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.edu.ifsp.arq.minhastarefas.R;
import br.edu.ifsp.arq.minhastarefas.adpater.ItemTarefaAdapter;
import br.edu.ifsp.arq.minhastarefas.constantes.Constantes;
import br.edu.ifsp.arq.minhastarefas.controller.TarefaController;
import br.edu.ifsp.arq.minhastarefas.model.Tarefa;

public class MainActivity extends AppCompatActivity {

    private List<Tarefa> mTarefas;
    private RecyclerView mTarefasRecylerView;
    private FloatingActionButton mActionButton;
    private ItemTarefaAdapter mItemTarefaAdapter;
    private ActionMode actionMode;

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTarefas = TarefaController.allTarefas(this);

        mItemTarefaAdapter = new ItemTarefaAdapter(this, mTarefas);
        mItemTarefaAdapter.setClickListener(position -> updateTarefa(position));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mTarefasRecylerView = findViewById(R.id.recycler_tarefas);
        mTarefasRecylerView.setLayoutManager(layoutManager);
        mTarefasRecylerView.setAdapter(mItemTarefaAdapter);

        mActionButton = findViewById(R.id.fab_add_tarefa);
        mActionButton.setOnClickListener(v -> newTarefa());

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHandler(0, ItemTouchHelper.RIGHT)

        );
        helper.attachToRecyclerView(mTarefasRecylerView);

        mItemTarefaAdapter.setListener(new ItemTarefaAdapter.TarefaAdapterListener() {

            @Override
            public void onItemLongClick(int position) {

                enableActionMode(position);
            }
        });
    }

    private void enableActionMode(int position) {
        if (actionMode == null) {
            actionMode = startSupportActionMode(new ActionMode.Callback() {

                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    mode.getMenuInflater().inflate(R.menu.menu_delete, menu);
                    mItemTarefaAdapter.setClickListener(null);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    if (item.getItemId() == R.id.action_delete) {
                        mItemTarefaAdapter.deleteTarefas();
                        mode.finish();
                        return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {
                    mItemTarefaAdapter.getselectedItens().clear();
                    List<Tarefa> tarefas = mItemTarefaAdapter.getmTarefa();
                    for (Tarefa t : tarefas) {
                        t.setSelected(false);
                    }
                    updateAdapter();
                    mItemTarefaAdapter.setClickListener(position -> updateTarefa(position));

                }
            });
            mItemTarefaAdapter.toggleSelection(position);
            final int size = mItemTarefaAdapter.getselectedItens().size();
            if (size == 0) {
                actionMode.finish();
            } else {
                actionMode.setTitle(size + "");
                actionMode.invalidate();
            }
            actionMode = null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent dados) {
        super.onActivityResult(requestCode, resultCode, dados);

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case Constantes.REQUEST_CODE_NEW_TAREFA:

                    TarefaController.addTarefa(
                            this,
                            dados.getStringExtra(Constantes.KEY_TITLE),
                            dados.getStringExtra(Constantes.KEY_DATA)
                    );

                    break;
                case Constantes.REQUEST_CODE_UPDATE_TAREFA:
                    String o = dados.getStringExtra(Constantes.KEY_OLD_TITLE);
                    String t = dados.getStringExtra(Constantes.KEY_TITLE);

                    String d = dados.getStringExtra(Constantes.KEY_DATA);

                    TarefaController.updateTarefa(this, o, t, d);
                    break;
            }
            updateAdapter();

        }
    }

    private void newTarefa() {
        Intent intent = new Intent(this, TarefaActivity.class);
        startActivityForResult(intent, Constantes.REQUEST_CODE_NEW_TAREFA);
    }

    private void updateTarefa(int position) {
        Bundle bundle = new Bundle();
        bundle.putString(Constantes.KEY_TITLE, mTarefas.get(position).getTitulo());
        bundle.putString(Constantes.KEY_DATA, mTarefas.get(position).getData());

        Intent intent = new Intent(this, TarefaActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, Constantes.REQUEST_CODE_UPDATE_TAREFA);
    }

    public void updateAdapter() {
        mItemTarefaAdapter.notifyDataSetChanged();
    }

    public void onStop() {
        super.onStop();
        TarefaController.finalizaAPP(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_listagem, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);

    }

    private class ItemTouchHandler extends ItemTouchHelper.SimpleCallback {

        public ItemTouchHandler(int dragDirs, int swipeDirs) {

            super(dragDirs, swipeDirs);
        }

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
            mItemTarefaAdapter.getmTarefa().remove(viewHolder.getAdapterPosition());
            mItemTarefaAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }
    }
}

