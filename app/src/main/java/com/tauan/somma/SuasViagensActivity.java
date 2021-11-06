package com.tauan.somma;

import static com.tauan.somma.util.Sharad.KEY_ID_USER;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.tauan.somma.adapter.ViagemAdapter;
import com.tauan.somma.database.dao.UserDAO;
import com.tauan.somma.database.dao.ViagemDAO;
import com.tauan.somma.database.model.ViagemModel;
import com.tauan.somma.util.Sharad;

import java.util.ArrayList;
import java.util.List;

public class SuasViagensActivity extends AppCompatActivity implements View.OnClickListener {

    private Button retornarMain;

    private ViagemDAO viagemDAO;

    private ArrayList<ViagemModel> listViagem = new ArrayList<ViagemModel>();

    private ListView listaSuasViagem;
    private ViagemAdapter adapter;

    private Sharad sharad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suas_viagens);

        viagemDAO = new ViagemDAO(SuasViagensActivity.this);

        sharad = new Sharad(SuasViagensActivity.this);

        retornarMain = findViewById(R.id.retornarMain);

        retornarMain.setOnClickListener(this);

        carregarAdapter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.retornarMain:
                retornMain();
                break;
        }

    }

    private void retornMain() {
        startActivity(new Intent(SuasViagensActivity.this, MainActivity.class));
    }

    private void carregarAdapter() {
        listaSuasViagem = findViewById(R.id.listaSuasViagem);
        List<ViagemModel> via = viagemDAO.SelectList(sharad.getLong(KEY_ID_USER));

        adapter = new ViagemAdapter(SuasViagensActivity.this, via);
        listaSuasViagem.setAdapter(adapter);
    }

}