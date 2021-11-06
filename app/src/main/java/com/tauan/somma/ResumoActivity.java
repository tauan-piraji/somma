package com.tauan.somma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tauan.somma.database.dao.EntreterimentoDAO;
import com.tauan.somma.database.dao.GasolinaDAO;
import com.tauan.somma.database.dao.HospedagemDAO;
import com.tauan.somma.database.dao.RefeicaoDAO;
import com.tauan.somma.database.dao.TarifaAereaDAO;
import com.tauan.somma.database.dao.ViagemDAO;
import com.tauan.somma.database.model.CarModel;
import com.tauan.somma.database.model.EntreterimentoModel;
import com.tauan.somma.database.model.GasolinaModel;
import com.tauan.somma.database.model.HospedagemModel;
import com.tauan.somma.database.model.RefeicaoModel;
import com.tauan.somma.database.model.TarifaAereaModel;
import com.tauan.somma.database.model.ViagemModel;
import com.tauan.somma.dialog.NotaDialog;
import com.tauan.somma.util.Sharad;

public class ResumoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView destino;
    private TextView nota;
    private TextView gasolina;
    private TextView tarifa;
    private TextView refeicao;
    private TextView hospedagem;
    private TextView entreterimento;
    private TextView total;
    private Button btnHome, btnNota;
    private float valorFinal;

    private ViagemDAO viagemDAO;
    private ViagemModel viagemModel;

    private GasolinaDAO gasolinaDAO;
    private GasolinaModel gasolinaModel;

    private TarifaAereaDAO tarifaAereaDAO;
    private TarifaAereaModel tarifaAereaModel;

    private RefeicaoDAO refeicaoDAO;
    private RefeicaoModel refeicaoModel;

    private HospedagemDAO hospedagemDAO;
    private HospedagemModel hospedagemModel;

    private EntreterimentoDAO entreterimentoDAO;
    private EntreterimentoModel entreterimentoModel;

    private Sharad sharad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);

        viagemDAO = new ViagemDAO(ResumoActivity.this);
        gasolinaDAO = new GasolinaDAO(ResumoActivity.this);
        tarifaAereaDAO = new TarifaAereaDAO(ResumoActivity.this);
        refeicaoDAO = new RefeicaoDAO(ResumoActivity.this);
        hospedagemDAO = new HospedagemDAO(ResumoActivity.this);
        entreterimentoDAO = new EntreterimentoDAO(ResumoActivity.this);

        sharad = new Sharad(ResumoActivity.this);

        destino = findViewById(R.id.destino);
        nota = findViewById(R.id.nota);
        gasolina = findViewById(R.id.gasolina);
        tarifa = findViewById(R.id.tarifa);
        refeicao = findViewById(R.id.refeicao);
        hospedagem = findViewById(R.id.hospedagem);
        entreterimento = findViewById(R.id.entreterimento);
        total = findViewById(R.id.total);

        btnNota = findViewById(R.id.btnNota);
        btnHome = findViewById(R.id.btnHome);

        btnNota.setOnClickListener(this);
        btnHome.setOnClickListener(this);

        carregarInf();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnNota:
                nota();
                break;
            case R.id.btnHome:
                home();
                break;
        }
    }

    private void nota() {
        NotaDialog notaDialog = new NotaDialog(ResumoActivity.this);
        notaDialog.show(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viagemDAO.UpdateNota(sharad.getString(Sharad.KEY_DESTINO), notaDialog.getNota());
                notaDialog.Cancel();
            }
        });
        carregarInf();
    }

    private void home() {
        startActivity(new Intent(ResumoActivity.this, MainActivity.class));
    }

    private void carregarInf() {

        destino.setText(sharad.getString(Sharad.KEY_DESTINO));
/*
        viagemModel = viagemDAO.Select(sharad.getString(Sharad.KEY_DESTINO));
        gasolinaModel = gasolinaDAO.Select(viagemModel.getId());
        tarifaAereaModel = tarifaAereaDAO.Select(viagemModel.getId());
        refeicaoModel = refeicaoDAO.Select(viagemModel.getId());
        hospedagemModel = hospedagemDAO.Select(viagemModel.getId());
        entreterimentoModel = entreterimentoDAO.Select(viagemModel.getId());

        float gasolinaTotal = gasolinaModel.getPrecoGasolina();
        float tarifaTotal = tarifaAereaModel.getPrecoTarifa();
        float refeicaoTotal = refeicaoModel.getPrecoRefeicao();
        float hospedagemTotal = hospedagemModel.getPrecoHospedagem();
        float entreterimentoTotal = entreterimentoModel.getPrecoEntreterimento();
*/
        float gasolinaTotal = sharad.getFloat(Sharad.KEY_GASOLINA_TOTAL);
        float tarifaTotal = sharad.getFloat(Sharad.KEY_TARIFA_TOTAL);
        float refeicaoTotal = sharad.getFloat(Sharad.KEY_REFEICAO_TOTAL);
        float hospedagemTotal = sharad.getFloat(Sharad.KEY_HOSPEDAGEM_TOTAL);
        float entreterimentoTotal = sharad.getFloat(Sharad.KEY_ENTRETERIMENTO_TOTAL);

        nota = findViewById(R.id.nota);
        gasolina = findViewById(R.id.gasolina);
        tarifa = findViewById(R.id.tarifa);
        refeicao = findViewById(R.id.refeicao);
        hospedagem = findViewById(R.id.hospedagem);
        entreterimento = findViewById(R.id.entreterimento);
        total = findViewById(R.id.total);


        gasolina.setText("R$ " + gasolinaTotal);
        tarifa.setText("R$ " + tarifaTotal);
        refeicao.setText("R$ " + refeicaoTotal);
        hospedagem.setText("R$ " + hospedagemTotal);
        entreterimento.setText("R$ " + entreterimentoTotal);

        valorFinal = gasolinaTotal + tarifaTotal + refeicaoTotal + hospedagemTotal + entreterimentoTotal;
        String fecho1 = String.valueOf(valorFinal);
        String novo1 = fecho1.replace(".", ",");
        total.setText("R$ " + novo1);

        viagemDAO.UpdateValor(sharad.getString(Sharad.KEY_DESTINO), valorFinal);

    }
}
