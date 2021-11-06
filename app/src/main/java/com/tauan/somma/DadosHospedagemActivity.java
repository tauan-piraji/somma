package com.tauan.somma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tauan.somma.database.dao.HospedagemDAO;
import com.tauan.somma.database.model.HospedagemModel;
import com.tauan.somma.enums.HospedagemEnum;
import com.tauan.somma.util.Sharad;

public class DadosHospedagemActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText custoMedio, totalNoites, totalQuartos;
    private Switch switchHospedagem;
    private TextView total4;
    private Button btnCalcular4;
    private View seta4, voltar4;

    private HospedagemEnum hospedagemEnum = HospedagemEnum.RICO;

    private HospedagemDAO hospedagemDAO;

    private Sharad sharad;

    private float valorFinal4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_hospedagem);

        hospedagemDAO = new HospedagemDAO(DadosHospedagemActivity.this);

        sharad = new Sharad(DadosHospedagemActivity.this);

        custoMedio = findViewById(R.id.custoMedio);
        totalNoites = findViewById(R.id.totalNoites);
        totalQuartos = findViewById(R.id.totalQuartos);
        total4 = findViewById(R.id.total4);

        switchHospedagem = (Switch) findViewById(R.id.switchHospedagem);
        btnCalcular4 = findViewById(R.id.btnCalcular4);
        seta4 = findViewById(R.id.seta4);
        voltar4 = findViewById(R.id.voltar4);

        switchHospedagem.setOnClickListener(this);
        btnCalcular4.setOnClickListener(this);
        seta4.setOnClickListener(this);
        voltar4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnCalcular4:
                calcular();
                break;
            case R.id.switchHospedagem:
                setSwitch();
                break;
            case R.id.seta4:
                passaPag();
                break;
            case R.id.voltar4:
                voltarPag();
                break;
        }
    }

    public void calcular() {

        if(custoMedio.getText().toString().isEmpty()) {
            custoMedio.setError("Digite um valor");
        }else if(Float.valueOf(custoMedio.getText().toString()) <= 0) {
            custoMedio.setError("Digite um valor maior que 0 (zero)");
        }else if(totalNoites.getText().toString().isEmpty()){
            totalNoites.setError("Digite um valor");
        }else if (Float.valueOf(totalNoites.getText().toString()) <= 0) {
            totalNoites.setError("Digite um valor maior que 0 (zero)");
        }else if(totalQuartos.getText().toString().isEmpty()){
            totalQuartos.setError("Digite um valor");
        }else if (Float.valueOf(totalQuartos.getText().toString()) <= 0) {
            totalQuartos.setError("Digite um valor maior que 0 (zero)");
        }else{

            String stringMedio = custoMedio.getText().toString();
            String stringNoites = totalNoites.getText().toString();
            String stringQuartos = totalQuartos.getText().toString();

            float medio = Float.valueOf(stringMedio);
            float noites = Float.valueOf(stringNoites);
            float quartos = Float.valueOf(stringQuartos);

            valorFinal4 = (medio * noites) * quartos;
            String fecho4 = String.valueOf(valorFinal4);
            String novo4 = fecho4.replace(".",",");
            total4.setText("R$ " + novo4);

        }
    }

    public void setSwitch() {

        switchHospedagem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    HospedagemEnum hospedagemEnum = HospedagemEnum.RICO;
                }
                HospedagemEnum hospedagemEnum = HospedagemEnum.FALIDO;
            }
        });
    }

    public void passaPag() {

        if(hospedagemEnum.equals(HospedagemEnum.RICO)) {

            if(verificaCampos()) {
                calcular();
                HospedagemModel model = new HospedagemModel();
                model.setIdViagem(sharad.getLong(Sharad.KEY_ID_VIAGEM));
                model.setNumeroNoites(Integer.valueOf(totalNoites.getText().toString()));
                model.setCustoNoite(Float.valueOf(custoMedio.getText().toString()));
                model.setQuantidadeQuartos(Integer.valueOf(totalQuartos.getText().toString()));
                model.setPrecoHospedagem(valorFinal4);

                if (hospedagemDAO.Insert(model) != -1) {
                    sharad.put(Sharad.KEY_HOSPEDAGEM_TOTAL, valorFinal4);
                    startActivity(new Intent(DadosHospedagemActivity.this, DadosEntreterimentoActivity.class));
                }
            }
        }

        if(hospedagemEnum.equals(HospedagemEnum.FALIDO)) {
            startActivity(new Intent(DadosHospedagemActivity.this, DadosEntreterimentoActivity.class));
        }

    }

    public void voltarPag() {
        startActivity(new Intent(DadosHospedagemActivity.this, DadosRefeicaoActivity.class));
    }

    public Boolean verificaCampos() {

        if(custoMedio.getText().toString().isEmpty()) {
            custoMedio.setError("Digite um valor");
            return false;
        }else if(Float.valueOf(custoMedio.getText().toString()) <= 0) {
            custoMedio.setError("Digite um valor maior que 0 (zero)");
            return false;
        }else if(totalNoites.getText().toString().isEmpty()){
            totalNoites.setError("Digite um valor");
            return false;
        }else if (Float.valueOf(totalNoites.getText().toString()) <= 0) {
            totalNoites.setError("Digite um valor maior que 0 (zero)");
            return false;
        }else if(totalQuartos.getText().toString().isEmpty()){
            totalQuartos.setError("Digite um valor");
            return false;
        }else if (Float.valueOf(totalQuartos.getText().toString()) <= 0) {
            totalQuartos.setError("Digite um valor maior que 0 (zero)");
            return false;
        }else {
            return true;
        }

    }
}