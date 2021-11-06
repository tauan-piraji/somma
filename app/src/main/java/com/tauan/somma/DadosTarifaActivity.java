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

import com.tauan.somma.database.dao.TarifaAereaDAO;
import com.tauan.somma.database.model.TarifaAereaModel;
import com.tauan.somma.enums.TarifaEnum;
import com.tauan.somma.util.Sharad;

public class DadosTarifaActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText custoPessoa, aluguelCar;
    private Switch switchTarifa;
    private TextView total2;
    private Button btnCalcular2;
    private View seta2, voltar2;

    private TarifaEnum tarifaEnum = TarifaEnum.RICO;

    private TarifaAereaDAO tarifaAereaDAO;

    private Sharad sharad;

    private float valorFina2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_tarifa);

        tarifaAereaDAO = new TarifaAereaDAO(DadosTarifaActivity.this);

        sharad = new Sharad(DadosTarifaActivity.this);

        custoPessoa = findViewById(R.id.custoPessoa);
        aluguelCar = findViewById(R.id.aluguelCar);
        total2 = findViewById(R.id.total2);

        btnCalcular2 = findViewById(R.id.btnCalcular2);
        switchTarifa = (Switch) findViewById(R.id.switchTarifa);
        seta2 = findViewById(R.id.seta2);
        voltar2 = findViewById(R.id.voltar2);

        btnCalcular2.setOnClickListener(this);
        switchTarifa.setOnClickListener(this);
        seta2.setOnClickListener(this);
        voltar2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnCalcular2:
                calcular();
                break;
            case R.id.switchTarifa:
                setSwitch();
                break;
            case R.id.seta2:
                passaPag();
                break;
            case R.id.voltar2:
                voltarPag();
                break;

        }
    }

    public void calcular() {

        if(custoPessoa.getText().toString().isEmpty()){
            custoPessoa.setError("Digite um valor");
        }else if (Float.valueOf(custoPessoa.getText().toString()) <= 0){
            custoPessoa.setError("Digite um valor maior que 0 (zero)");
        }else if(aluguelCar.getText().toString().isEmpty()){
            aluguelCar.setError("Digite um valor");
        }else{
            String stringCusto = custoPessoa.getText().toString();
            String stringAluguel = aluguelCar.getText().toString();

            float custo = Float.valueOf(custoPessoa.getText().toString());
            float aluguel = Float.valueOf(aluguelCar.getText().toString());

            valorFina2 = (custo * sharad.getFloat(Sharad.KEY_NUMERO_PESSOAS)) + aluguel;
            String fecho2 = String.valueOf(valorFina2);
            String novo2 = fecho2.replace(".",",");
            total2.setText("R$ " + novo2);

        }

    }

    public void setSwitch() {

        switchTarifa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    TarifaEnum tarifaEnum = TarifaEnum.RICO;
                }
                TarifaEnum tarifaEnum = TarifaEnum.FALIDO;
            }
        });
    }

    public void passaPag() {

        if(tarifaEnum.equals(tarifaEnum.RICO)) {

            if(verificaCampos()){
                calcular();
                TarifaAereaModel model = new TarifaAereaModel();
                model.setIdViagem(sharad.getLong(Sharad.KEY_ID_VIAGEM));
                model.setCustoPessoa(Float.parseFloat(custoPessoa.getText().toString()));
                model.setAlugaCarro(Float.parseFloat(aluguelCar.getText().toString()));
                model.setPrecoTarifa(valorFina2);

                if (tarifaAereaDAO.Insert(model) != -1) {
                    sharad.put(Sharad.KEY_TARIFA_TOTAL, valorFina2);
                    startActivity(new Intent(DadosTarifaActivity.this, DadosRefeicaoActivity.class));
                }
            }
        }
        if(tarifaEnum.equals(tarifaEnum.FALIDO)) {
            startActivity(new Intent(DadosTarifaActivity.this, DadosRefeicaoActivity.class));
        }

    }

    public void voltarPag() {
        startActivity(new Intent(DadosTarifaActivity.this, DadosGasolinaActivity.class));
    }


    public Boolean verificaCampos() {

        if(custoPessoa.getText().toString().isEmpty()){
            custoPessoa.setError("Digite um valor");
            return false;
        }else if (Float.valueOf(custoPessoa.getText().toString()) <= 0){
            custoPessoa.setError("Digite um valor maior que 0 (zero)");
            return false;
        }else if(aluguelCar.getText().toString().isEmpty()){
            aluguelCar.setError("Digite um valor");
            return false;
        }else {
            return true;
        }
    }
}