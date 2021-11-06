package com.tauan.somma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tauan.somma.database.dao.UserDAO;
import com.tauan.somma.database.model.UserModel;
import com.tauan.somma.util.Sharad;

public class CadastroActivity extends AppCompatActivity {

    private EditText edtCadUsuario, edtEmail, edtSenha, edtConfSenha;
    private Button btnCadastrar, btnVoltar;
    private UserDAO dao;

    private Sharad sharad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        sharad = new Sharad(CadastroActivity.this);

        // Instancia o banco de dados.
        dao = new UserDAO(CadastroActivity.this);

        // Cria os componentes.
        edtCadUsuario = findViewById(R.id.edtCadUsuario);
        edtEmail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        edtConfSenha = findViewById(R.id.edtConfSenha);

        // Grava um novo usário.
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtCadUsuario.getText().toString().isEmpty()){
                    edtCadUsuario.setError("Digite um usuário");
                }else if(edtEmail.getText().toString().isEmpty()) {
                    edtEmail.setError("Digite um email");
                }else if(edtSenha.getText().toString().isEmpty()) {
                    edtSenha.setError("Digite uma senha");
                }else if(edtConfSenha.getText().toString().isEmpty()) {
                    edtConfSenha.setError("Digite uma senha");
                }else if(!edtSenha.getText().toString().equalsIgnoreCase(edtConfSenha.getText().toString())) {
                    edtConfSenha.setError("As senhas não correspondem");
                }else {

                    UserModel model = new UserModel();
                    model.setName(edtCadUsuario.getText().toString());
                    model.setEmail(edtEmail.getText().toString());
                    model.setSenha(edtSenha.getText().toString());

                    if (dao.Insert(model) != -1) {
                        Toast.makeText(CadastroActivity.this, "Usuario cadastrado com sucesso!", Toast.LENGTH_LONG).show();
                        //seta KEY_USER e ID_USER
                        sharad.put(Sharad.KEY_USER, edtCadUsuario);
                        sharad.put(Sharad.KEY_ID_USER, model.getId());
                        startActivity(new Intent(CadastroActivity.this, MainActivity.class));
                    }
                }
            }
        });

        btnVoltar = findViewById(R.id.btnVoltar);
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CadastroActivity.this, LoginActivity.class));
            }
        });
    }

}