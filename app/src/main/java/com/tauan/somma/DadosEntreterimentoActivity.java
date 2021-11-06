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

import com.tauan.somma.database.dao.EntreterimentoDAO;
import com.tauan.somma.database.model.EntreterimentoModel;
import com.tauan.somma.enums.EntreterimentoEnum;
import com.tauan.somma.enums.EntreterimentoImprevistosEnum;
import com.tauan.somma.enums.EntreterimentoVilaEnum;
import com.tauan.somma.enums.EntreterimentoZooEnum;
import com.tauan.somma.util.Sharad;

public class DadosEntreterimentoActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText vila, zoo, imprevistos;
    private Switch switchEntreterimento, switchVila, switchZoo, switchImprevistos;
    private TextView total5;
    private Button btnCalcular5;
    private View seta5, voltar5;
    private float valorFinal5;
    private float Vila_;
    private float Zoo_;
    private float Imprevistos_;

    private EntreterimentoEnum entreterimentoEnum = EntreterimentoEnum.RICO;
    private EntreterimentoVilaEnum entreterimentoVilaEnum = EntreterimentoVilaEnum.RICO;
    private EntreterimentoZooEnum entreterimentoZooEnum = EntreterimentoZooEnum.RICO;
    private EntreterimentoImprevistosEnum entreterimentoImprevistosEnum = EntreterimentoImprevistosEnum.RICO;

    private EntreterimentoDAO entreterimentoDAO;

    private Sharad sharad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_entreterimento);

        entreterimentoDAO = new EntreterimentoDAO(DadosEntreterimentoActivity.this);

        sharad = new Sharad(DadosEntreterimentoActivity.this);

        vila = findViewById(R.id.vila);
        zoo = findViewById(R.id.zoo);
        imprevistos = findViewById(R.id.imprevistos);
        total5 = findViewById(R.id.total5);

        switchEntreterimento = (Switch) findViewById(R.id.switchEntreterimento);
        switchVila = (Switch) findViewById(R.id.switchVila);
        switchZoo = (Switch) findViewById(R.id.switchZoo);
        switchImprevistos = (Switch) findViewById(R.id.switchImprevistos);
        btnCalcular5 = findViewById(R.id.btnCalcular5);
        seta5 = findViewById(R.id.seta5);
        voltar5 = findViewById(R.id.voltar5);

        switchEntreterimento.setOnClickListener(this);
        switchVila.setOnClickListener(this);
        switchZoo.setOnClickListener(this);
        switchImprevistos.setOnClickListener(this);
        btnCalcular5.setOnClickListener(this);
        seta5.setOnClickListener(this);
        voltar5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnCalcular5:
                calcular();
                break;
            case R.id.switchEntreterimento:
                setSwitchEntreterimentoEnum();
                break;
            case R.id.switchVila:
                setSwitchEntreterimentoVilaEnum();
                break;
            case R.id.switchZoo:
                setSwitchEntreterimentoZooEnum();
                break;
            case R.id.switchImprevistos:
                setSwitchEntreterimentoImprevistosEnum();
                break;
            case R.id.seta5:
                passaPag();
                break;
            case R.id.voltar5:
                voltarPag();
                break;

        }
    }

    public void calcular() {

        if(verificaCampos()) {
            String stringVila = vila.getText().toString();
            String strinZoo = zoo.getText().toString();
            String stringImprevistos = imprevistos.getText().toString();

            if(entreterimentoVilaEnum.equals(EntreterimentoVilaEnum.RICO)) {
                Vila_ = Float.valueOf(stringVila);
            }else {
                Vila_ = Float.valueOf(0);
            }
            if(entreterimentoZooEnum.equals(EntreterimentoZooEnum.RICO)) {
                Zoo_ = Float.valueOf(strinZoo);
            }else {
                Zoo_ = Float.valueOf(0);
            }
            if(entreterimentoImprevistosEnum.equals(EntreterimentoImprevistosEnum.RICO)) {
                Imprevistos_ = Float.valueOf(stringImprevistos);
            }else {
                Imprevistos_ = Float.valueOf(0);
            }

            valorFinal5 = Vila_ + Zoo_ + Imprevistos_;
            String fecho5 = String.valueOf(valorFinal5);
            String novo5 = fecho5.replace(".",",");
            total5.setText("R$ " + novo5);

            sharad.put(Sharad.KEY_ENTRETERIMENTO_TOTAL, valorFinal5);

        }
    }

    public void setSwitchEntreterimentoEnum() {

        switchEntreterimento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    EntreterimentoEnum entreterimentoEnum = EntreterimentoEnum.RICO;
                }
                EntreterimentoEnum entreterimentoEnum = EntreterimentoEnum.FALIDO;
            }
        });
    }

    public void setSwitchEntreterimentoVilaEnum() {

        switchEntreterimento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    EntreterimentoVilaEnum entreterimentoVilaEnum = EntreterimentoVilaEnum.RICO;
                }
                EntreterimentoVilaEnum entreterimentoVilaEnum = EntreterimentoVilaEnum.FALIDO;
            }
        });
    }

    public void setSwitchEntreterimentoZooEnum() {

        switchEntreterimento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    EntreterimentoZooEnum entreterimentoZooEnum = EntreterimentoZooEnum.RICO;
                }
                EntreterimentoZooEnum entreterimentoZooEnum = EntreterimentoZooEnum.FALIDO;
            }
        });
    }

    public void setSwitchEntreterimentoImprevistosEnum() {

        switchEntreterimento.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    EntreterimentoImprevistosEnum entreterimentoImprevistosEnum = EntreterimentoImprevistosEnum.RICO;
                }
                EntreterimentoImprevistosEnum entreterimentoImprevistosEnum = EntreterimentoImprevistosEnum.FALIDO;
            }
        });
    }

    public void passaPag() {

        if(entreterimentoEnum.equals(EntreterimentoEnum.RICO)) {

            if(verificaCampos()) {

                calcular();
                EntreterimentoModel model = new EntreterimentoModel();
                model.setIdViagem(sharad.getLong(Sharad.KEY_ID_VIAGEM));
                model.setPrecoEntreterimento(valorFinal5);

                if(entreterimentoVilaEnum.equals(EntreterimentoVilaEnum.RICO)) {
                    model.setVila(Float.valueOf(vila.getText().toString()));
                }else {
                    model.setVila(Float.valueOf(0));
                }

                if(entreterimentoZooEnum.equals(EntreterimentoZooEnum.RICO)) {
                    model.setZoo(Float.valueOf(zoo.getText().toString()));
                }else {
                    model.setZoo(Float.valueOf(0));
                }

                if(entreterimentoImprevistosEnum.equals(EntreterimentoImprevistosEnum.RICO)) {
                    model.setImprevisto(Float.valueOf(imprevistos.getText().toString()));
                }else {
                    model.setImprevisto(Float.valueOf(0));
                }

                if (entreterimentoDAO.Insert(model) != -1) {
                    startActivity(new Intent(DadosEntreterimentoActivity.this, ResumoActivity.class));
                }
            }
        }
        if(entreterimentoEnum.equals(EntreterimentoEnum.RICO)) {
            startActivity(new Intent(DadosEntreterimentoActivity.this, ResumoActivity.class));
        }
    }

    public void voltarPag() {
        startActivity(new Intent(DadosEntreterimentoActivity.this, DadosHospedagemActivity.class));
    }

    public Boolean verificaCampos() {

        if(entreterimentoVilaEnum.equals(EntreterimentoVilaEnum.RICO)) {
            if(vila.getText().toString().isEmpty()){
                vila.setError("Digite um valor");
                return false;
            }else if(Float.valueOf(vila.getText().toString()) <= 0){
                vila.setError("Digite um valor maior que 0 (zero)");
                return false;
            }else {}

        }
        if(entreterimentoZooEnum.equals(EntreterimentoZooEnum.RICO)) {
            if(zoo.getText().toString().isEmpty()){
                zoo.setError("Digite um valor");
                return false;
            }else if(Float.valueOf(zoo.getText().toString()) <= 0){
                zoo.setError("Digite um valor maior que 0 (zero)");
                return false;
            }else{
                return true;
            }
        }
        if(entreterimentoImprevistosEnum.equals(EntreterimentoImprevistosEnum.RICO)) {
            if(imprevistos.getText().toString().isEmpty()){
                imprevistos.setError("Digite um valor");
                return false;
            }else if(Float.valueOf(imprevistos.getText().toString()) <= 0){
                imprevistos.setError("Digite um valor maior que 0 (zero)");
                return false;
            }else {
                return true;
            }
        }
        return true;
    }
}