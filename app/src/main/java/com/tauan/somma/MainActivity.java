package com.tauan.somma;

import static com.tauan.somma.util.Sharad.KEY_ID_USER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.tauan.somma.adapter.ViagemAdapter;
import com.tauan.somma.database.dao.CarDAO;
import com.tauan.somma.database.dao.UserDAO;
import com.tauan.somma.database.dao.ViagemDAO;
import com.tauan.somma.database.model.CarModel;
import com.tauan.somma.database.model.UserModel;
import com.tauan.somma.database.model.ViagemModel;
import com.tauan.somma.dialog.CarDialog;
import com.tauan.somma.util.Sharad;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView user, viagem, dest, numeroViagens, textCarro;
    private ImageView newViagem, suasViagens;
    private LinearLayout linearCarro, linearOrdem;

    private UserDAO userDAO;
    private ViagemDAO viagemDAO;
    private CarDAO carDAO;

    private UserModel userModel;
    private ViagemModel viagemModel;
    private ArrayList<ViagemModel> listViagem = new ArrayList<ViagemModel>();
    private CarModel carModel;

    private ListView listaViagem;
    private ViagemAdapter adapter;

    private Sharad sharad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userDAO = new UserDAO(MainActivity.this);
        carDAO = new CarDAO(MainActivity.this);
        viagemDAO = new ViagemDAO(MainActivity.this);

        sharad = new Sharad(MainActivity.this);

        user = findViewById(R.id.user);
        viagem = findViewById(R.id.viagem);
        dest = findViewById(R.id.dest);
        numeroViagens = findViewById(R.id.numeroViagens);
        suasViagens = findViewById(R.id.suasViagens);
        //textCarro = findViewById(R.id.textCarro);

        //linearCarro = findViewById(R.id.linearCarro);
        //linearOrdem = findViewById(R.id.linearOrdem);
        newViagem = findViewById(R.id.newViagem);

       // linearCarro.setOnClickListener(this);
       // linearOrdem.setOnClickListener(this);
        suasViagens.setOnClickListener(this);
        newViagem.setOnClickListener(this);

        carregarHeader();
        //carregarCarro();
        carregarAdapter();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

//            case R.id.linearCarro:
//                selecionarCarro();
//                break;

//            case R.id.linearOrdem:
//                ordemAdapter();////////////////////
//                break;

            case R.id.newViagem:
                criarViagem();
                break;

            case R.id.suasViagens:
                suasViagem();
                break;
        }

    }

    private void selecionarCarro() {
        if(sharad.getString(Sharad.KEY_CARRO).isEmpty()) {
            CarDialog carDialog = new CarDialog(MainActivity.this, "INFORME SEU CARRO");
            carDialog.show(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    CarModel carModel = new CarModel();
                    carModel.setName("" + carDialog.getCarro());
                    carModel.setKml(carDialog.getKml());

                    sharad.put(Sharad.KEY_CARRO, carDialog.getCarro());
                    carDAO.Insert(carModel);
                    carDialog.Cancel();
                }
            });
        }else {
            CarDialog carDialog = new CarDialog(MainActivity.this, "CADASTRE O CARRO NOVO");
            carDialog.show(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    CarModel carModel = new CarModel();
                    carModel.setName("" + carDialog.getCarro());
                    carModel.setKml(carDialog.getKml());

                    sharad.put(Sharad.KEY_CARRO, carDialog.getCarro());
                    carDAO.Update(carDialog.getCarro(), carDialog.getKml());
                    carDialog.Cancel();
                }
            });
        }

    }

    private void ordemAdapter() { }

    private void criarViagem() {
        startActivity(new Intent(MainActivity.this, DadosViagemActivity.class));
    }

    private void suasViagem() {
        startActivity(new Intent(MainActivity.this, SuasViagensActivity.class));
    }

    private void carregarHeader() {

        userModel = userDAO.Select(sharad.getString(Sharad.KEY_USER), sharad.getString(Sharad.KEY_SENHA));
        sharad.put(KEY_ID_USER, userModel.getId());
        user.setText(userModel.getName());

        carregarDestino();
        carregarNumeroViagem();
    }

    private void carregarDestino() {

        if(sharad.getString(Sharad.KEY_DESTINO).isEmpty()) {
            dest.setText("");
            viagem.setText("Venha somar seus sonhos com a gente!!");
        }else {
            viagemModel = viagemDAO.Select(sharad.getString(Sharad.KEY_DESTINO));
            dest.setText("DESTINO ATUAL: ");
            viagem.setText("" + viagemModel.getDestino());
        }
    }

    private void carregarNumeroViagem() {

        listViagem = (ArrayList<ViagemModel>) viagemDAO.SelectList(sharad.getLong(KEY_ID_USER));
        numeroViagens.setText("" + listViagem.size());
    }

    private void carregarCarro() {
        if(sharad.getString(Sharad.KEY_CARRO).isEmpty()) {
            textCarro.setText("Cadastre seu carro aqui!");
        }else {
            textCarro.setText(sharad.getString(Sharad.KEY_CARRO));
        }
    }

    private void carregarAdapter() {
        listaViagem = findViewById(R.id.listaViagem);
        List<ViagemModel> via = viagemDAO.SelectList(userModel.getId());

        adapter = new ViagemAdapter(MainActivity.this, via);
        listaViagem.setAdapter(adapter);
    }

}