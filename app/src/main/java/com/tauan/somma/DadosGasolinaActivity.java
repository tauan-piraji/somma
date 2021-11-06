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

import com.tauan.somma.database.dao.GasolinaDAO;
import com.tauan.somma.database.model.GasolinaModel;
import com.tauan.somma.enums.GasolinaEnum;
import com.tauan.somma.util.Sharad;

public class DadosGasolinaActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText totalKM, mediaKM, CustoMedio, TotalCarro;
    private Switch switchViagem;
    private TextView Total;
    private Button btnCalcular;
    private View seta1, voltar1;

    private GasolinaEnum gasolinaEnum = GasolinaEnum.RICO;
    private GasolinaDAO gasolinaDAO;

    private Sharad sharad;

    private float valorFinal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_gasolina);

        gasolinaDAO = new GasolinaDAO(DadosGasolinaActivity.this);

        sharad = new Sharad(DadosGasolinaActivity.this);

        totalKM = findViewById(R.id.totalKM);
        mediaKM = findViewById(R.id.mediaKM);
        CustoMedio = findViewById(R.id.CustoMedio);
        TotalCarro = findViewById(R.id.TotalCarro);
        Total = findViewById(R.id.Total);

        btnCalcular = findViewById(R.id.btnCalcular);
        switchViagem = (Switch) findViewById(R.id.switchViagem);
        seta1 = findViewById(R.id.seta1);
        voltar1 = findViewById(R.id.voltar1);

        switchViagem.setOnClickListener(this);
        btnCalcular.setOnClickListener(this);
        seta1.setOnClickListener(this);
        voltar1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnCalcular:
                calcular();
                break;
            case R.id.switchViagem:
                setSwitch();
                break;
            case R.id.seta1:
                passaPag();
                break;
            case R.id.voltar1:
                voltarPag();
                break;
        }
    }

    public void calcular() {

        if(totalKM.getText().toString().isEmpty()){
            totalKM.setError("Digite um valor");
        }else if(Float.valueOf(totalKM.getText().toString()) <= 0){
            totalKM.setError("Digite um valor maior que 0 (zero)");
        }else if(mediaKM.getText().toString().isEmpty()){
            mediaKM.setError(("Digite um valor"));
        }else if(Float.valueOf(mediaKM.getText().toString()) <= 0){
            mediaKM.setError("Digite um valor maior que 0 (zero)");
        }else if(CustoMedio.getText().toString().isEmpty()){
            CustoMedio.setError("Digite um valor");
        }else if(Float.valueOf(CustoMedio.getText().toString()) <= 0){
            CustoMedio.setError("Digite um valor maior que 0 (zero)");
        }else if(TotalCarro.getText().toString().isEmpty()){
            TotalCarro.setError("Digite um valor");
        }else if(Float.valueOf(TotalCarro.getText().toString()) <= 0){
            TotalCarro.setError("Digite um valor maior que 0 (zero)");
        }else{

            float total = Float.valueOf(totalKM.getText().toString());
            float media = Float.valueOf(mediaKM.getText().toString());
            float cust = Float.valueOf(CustoMedio.getText().toString());
            float totalCar = Float.valueOf(TotalCarro.getText().toString());

            valorFinal = ((total / media) * cust) * totalCar ;
            String fecho1 = String.valueOf(valorFinal);
            String novo1 = fecho1.replace(".", ",");
            Total.setText("R$ " + novo1);

        }
    }

    public void setSwitch() {

        switchViagem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    GasolinaEnum gasolinaEnum = GasolinaEnum.RICO;
                }else{
                    GasolinaEnum gasolinaEnum = GasolinaEnum.FALIDO;
                }
            }
        });
    }

    public void passaPag() {

        if(gasolinaEnum.equals(gasolinaEnum.RICO)){

            if(verificaCampos()){
                calcular();
                GasolinaModel model = new GasolinaModel();
                model.setIdViagem(sharad.getLong(Sharad.KEY_ID_VIAGEM));
                model.setTotalKm(Float.parseFloat(totalKM.getText().toString()));
                model.setKmL(Float.parseFloat(mediaKM.getText().toString()));
                model.setCustoL(Float.parseFloat(CustoMedio.getText().toString()));
                model.setTotalCarros(Float.parseFloat(TotalCarro.getText().toString()));
                model.setPrecoGasolina(valorFinal);

                if (gasolinaDAO.Insert(model) != -1) {
                    sharad.put(Sharad.KEY_GASOLINA_TOTAL, valorFinal);
                    startActivity(new Intent(DadosGasolinaActivity.this, DadosTarifaActivity.class));
                }
            }
        }
        if(gasolinaEnum.equals(gasolinaEnum.FALIDO)) {
            startActivity(new Intent(DadosGasolinaActivity.this, DadosTarifaActivity.class));
        }

    }

    public void voltarPag() {
        startActivity(new Intent(DadosGasolinaActivity.this, DadosViagemActivity.class));
    }

    public Boolean verificaCampos() {

        if(totalKM.getText().toString().isEmpty()){
            totalKM.setError("Digite um valor");
            return false;
        }else if(Float.valueOf(totalKM.getText().toString()) <= 0){
            totalKM.setError("Digite um valor maior que 0 (zero)");
            return false;
        }else if(mediaKM.getText().toString().isEmpty()){
            mediaKM.setError(("Digite um valor"));
            return false;
        }else if(Float.valueOf(mediaKM.getText().toString()) <= 0){
            mediaKM.setError("Digite um valor maior que 0 (zero)");
            return false;
        }else if(CustoMedio.getText().toString().isEmpty()){
            CustoMedio.setError("Digite um valor");
            return false;
        }else if(Float.valueOf(CustoMedio.getText().toString()) <= 0){
            CustoMedio.setError("Digite um valor maior que 0 (zero)");
            return false;
        }else if(TotalCarro.getText().toString().isEmpty()){
            TotalCarro.setError("Digite um valor");
            return false;
        }else if(Float.valueOf(TotalCarro.getText().toString()) <= 0){
            TotalCarro.setError("Digite um valor maior que 0 (zero)");
            return false;
        }else{
            return true;
        }

    }

}