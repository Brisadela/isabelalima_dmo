package br.edu.ifsp.arq.meutreino.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.edu.ifsp.arq.meutreino.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private FloatingActionButton mAddActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}