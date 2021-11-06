package com.tauan.somma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tauan.somma.database.dao.ViagemDAO;
import com.tauan.somma.database.model.ViagemModel;
import com.tauan.somma.util.Sharad;

public class DadosViagemActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nomeViagem, totalPessoas, totalDias;
    private View voltar;
    private LinearLayout linearSeta;

    private ViagemDAO viagemDAO;

    private Sharad sharad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_viagem);

        viagemDAO = new ViagemDAO(DadosViagemActivity.this);

        sharad = new Sharad(DadosViagemActivity.this);

        nomeViagem = findViewById(R.id.nomeViagem);
        totalPessoas = findViewById(R.id.totalPessoas);
        totalDias = findViewById(R.id.totalDias);

        linearSeta = findViewById(R.id.linearSeta);
        voltar = findViewById(R.id.voltar);

        linearSeta.setOnClickListener(this);
        voltar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.linearSeta:
                passaPag();
                break;
            case R.id.voltar:
                voltarPag();
                break;

        }
    }

    public void passaPag() {

        if(nomeViagem.getText().toString().isEmpty()) {
            nomeViagem.setError("Campo Obrigatorio");
        }else if(totalPessoas.getText().toString().isEmpty()) {
            totalPessoas.setError("Campo Obrigatorio");
        }else if(totalDias.getText().toString().isEmpty()) {
            totalDias.setError("Campo Obrigatorio");
        }else{
            ViagemModel model = new ViagemModel();
            model.setIdUser(sharad.getLong(Sharad.KEY_ID_USER));
            model.setDestino(nomeViagem.getText().toString());
            model.setTotalPessoas(Float.parseFloat(totalPessoas.getText().toString()));
            model.setTotalDias(Float.parseFloat(totalDias.getText().toString()));

            if (viagemDAO.Insert(model) != -1) {
                model = viagemDAO.Select(model.getDestino());
                sharad.put(Sharad.KEY_ID_VIAGEM, model.getId());
                sharad.put(Sharad.KEY_DESTINO, model.getDestino());
                sharad.put(Sharad.KEY_DIAS, model.getTotalDias());
                sharad.put(Sharad.KEY_NUMERO_PESSOAS,model.getTotalPessoas());
                startActivity(new Intent(DadosViagemActivity.this, DadosGasolinaActivity.class));
            }
        }
    }

    public void voltarPag() {
        startActivity(new Intent(DadosViagemActivity.this, MainActivity.class));
    }
}
