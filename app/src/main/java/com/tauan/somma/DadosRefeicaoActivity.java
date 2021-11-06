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

import com.tauan.somma.database.dao.RefeicaoDAO;
import com.tauan.somma.database.model.RefeicaoModel;
import com.tauan.somma.enums.RefeicaoEnum;
import com.tauan.somma.util.Sharad;

public class DadosRefeicaoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText custoRefeicao, refeicaoDia;
    private Switch switchRefeicao;
    private Button btnCalcular3;
    private TextView total3;
    private View seta3, voltar3;
    private float valorFinal3;

    private RefeicaoEnum refeicaoEnum = RefeicaoEnum.RICO;

    private RefeicaoDAO refeicaoDAO;

    private Sharad sharad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_refeicao);

        refeicaoDAO = new RefeicaoDAO(DadosRefeicaoActivity.this);

        sharad = new Sharad(DadosRefeicaoActivity.this);

        custoRefeicao = findViewById(R.id.custoRefeicao);
        refeicaoDia = findViewById(R.id.refeicaoDia);
        total3 = findViewById(R.id.total3);

        switchRefeicao = (Switch) findViewById(R.id.switchRefeicao);
        btnCalcular3 = findViewById(R.id.btnCalcular3);
        seta3 = findViewById(R.id.seta3);
        voltar3 = findViewById(R.id.voltar3);

        switchRefeicao.setOnClickListener(this);
        btnCalcular3.setOnClickListener(this);
        seta3.setOnClickListener(this);
        voltar3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnCalcular3:
                calcular();
                break;
            case R.id.switchRefeicao:
                setSwitch();
                break;
            case R.id.seta3:
                passaPag();
                break;
            case R.id.voltar3:
                voltarPag();
                break;

        }
    }

    public void calcular(){

        if(custoRefeicao.getText().toString().isEmpty()){
            custoRefeicao.setError("Digite um valor");
        }else if (Float.valueOf(custoRefeicao.getText().toString())  <= 0){
            custoRefeicao.setError("Digite um valor maior que 0 (zero)");
        }else if(refeicaoDia.getText().toString().isEmpty()){
            refeicaoDia.setError("Digite um valor");
        }else if (Float.valueOf(refeicaoDia.getText().toString()) <= 0){
            refeicaoDia.setError("Digite um valor maior que 0 (zero)");
        }else{

            float custoRef = Float.valueOf(custoRefeicao.getText().toString());
            float dia = Float.valueOf(refeicaoDia.getText().toString());

            valorFinal3 = ((dia * sharad.getFloat(Sharad.KEY_NUMERO_PESSOAS)) * custoRef) * sharad.getFloat(Sharad.KEY_DIAS);
            String fecho3 = String.valueOf(valorFinal3);
            String novo3 = fecho3.replace(".",",");
            total3.setText("R$ " + novo3);

        }
    }

    public void setSwitch() {

        switchRefeicao.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    RefeicaoEnum refeicaoEnum = RefeicaoEnum.RICO;
                }
                RefeicaoEnum refeicaoEnum = RefeicaoEnum.FALIDO;
            }
        });
    }

    public void passaPag() {

        if (refeicaoEnum.equals(RefeicaoEnum.RICO)) {

            if(verificaCampos()){
                calcular();
                RefeicaoModel model = new RefeicaoModel();
                model.setIdViagem(sharad.getLong(Sharad.KEY_ID_VIAGEM));
                model.setQuantidadeRefeicoes(Integer.valueOf(refeicaoDia.getText().toString()));
                model.setCustoRefeicao(Float.parseFloat(custoRefeicao.getText().toString()));
                model.setPrecoRefeicao(valorFinal3);

                if (refeicaoDAO.Insert(model) != -1) {
                    sharad.put(Sharad.KEY_REFEICAO_TOTAL, valorFinal3);
                    startActivity(new Intent(DadosRefeicaoActivity.this, DadosHospedagemActivity.class));
                }
            }
        }
        if (refeicaoEnum.equals(RefeicaoEnum.FALIDO)) {
            startActivity(new Intent(DadosRefeicaoActivity.this, DadosHospedagemActivity.class));
        }

    }

    public void voltarPag() {
        startActivity(new Intent(DadosRefeicaoActivity.this, DadosTarifaActivity.class));
    }

    public Boolean verificaCampos() {

        if(custoRefeicao.getText().toString().isEmpty()){
            custoRefeicao.setError("Digite um valor");
            return false;
        }else if (Float.valueOf(custoRefeicao.getText().toString())  <= 0){
            custoRefeicao.setError("Digite um valor maior que 0 (zero)");
            return false;
        }else if(refeicaoDia.getText().toString().isEmpty()){
            refeicaoDia.setError("Digite um valor");
            return false;
        }else if (Float.valueOf(refeicaoDia.getText().toString()) <= 0){
            refeicaoDia.setError("Digite um valor maior que 0 (zero)");
            return false;
        }else{
            return true;
        }
    }
}